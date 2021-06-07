package Lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {

    private JTextField inputField; // текстовое поле для ввода сообщений
    private JTextArea chatArea; // текстовое поле для вывода сообщений

    private Socket socket; //соединение с сервером
    private DataInputStream inputStream; //поток ввода
    private DataOutputStream outputStream; //поток вывода

    public EchoClient() {
        try {
            openConnection();
        } catch(IOException e) {
            e.printStackTrace();
        }
        initGUI();
    }

    //открытие соединения
    public void openConnection() throws IOException{
        socket = new Socket(EchoConstants.SERVER_ADDER, EchoConstants.SERVER_PORT); //открытие сокета
        inputStream = new DataInputStream(socket.getInputStream()); //доступ к исходящему потоку сервера
        outputStream = new DataOutputStream(socket.getOutputStream()); //доступ к входящему потоку сервера

        //входящие сообщения
        Thread thread = new Thread(() -> {
            try {
                while(true) {
                    String strFromServer = inputStream.readUTF(); //получить сообщение от сервера
                    if(strFromServer.equals(EchoConstants.STOP_WORD)) {
                        break;
                    }
                    chatArea.append(strFromServer); //добавить сообщение от сервера в ТА
                    chatArea.append("\n"); //перейти на новую строчку в TA
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    //закрытие соединения
    public void closeConnection() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //исходящие сообщения
    public void sendMessage() {
        while(!inputField.getText().trim().isEmpty()) {
            try {
                outputStream.writeUTF(inputField.getText()); //отправить текст из TF на сервер
                chatArea.append("Клиент: " + inputField.getText() + "\n");
                inputField.setText(""); //очистить TF
                inputField.grabFocus(); //установить курсор на TF
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения"); //всплывающее окно с текстом ошибки
            }
        }
    }

    //окна c чатом
    public void initGUI() {
        //параметры окна
        setBounds(600,300,500,500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //текстовое поле для вывода сообщений
        chatArea = new JTextArea();
        chatArea.setEditable(false); //запрет на редактирование текста
        chatArea.setLineWrap(true); //перенос строк
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        //нижняя панель: поле для ввода ссобщений + кнопка для отправки сообщений
        JPanel panel = new JPanel(new BorderLayout());
        //кнопка для отправки сообщений
        JButton button = new JButton("Отправить");
        panel.add(button, BorderLayout.EAST);
        button.addActionListener(e -> sendMessage()); //нажатие на кнопку - отправка сообщения из TF в TA
        //текстовое поле для ввода сообщений
        inputField = new JTextField();
        panel.add(inputField, BorderLayout.CENTER);
        inputField.addActionListener(e -> sendMessage()); //нажатие на enter в TF - отправка сообщения из TF в TA

        add(panel, BorderLayout.SOUTH);

        //действие на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    outputStream.writeUTF(EchoConstants.STOP_WORD);
                    closeConnection();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        //открыть новый поток, запуская клиента
        SwingUtilities.invokeLater(EchoClient::new);
    }

}
