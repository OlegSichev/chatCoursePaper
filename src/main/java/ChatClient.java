import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChatClient {
    private static final String SETTINGS_FILE = "settings.txt";
    private static final String LOG_FILE = "file.log";
    private static final String EXIT_COMMAND = "/exit";

    public static void main(String[] args) {
        // Чтение настроек клиента из файла
        String[] settings = readSettingsFromSettingsFile();
        String serverAddress = settings[0];
        int serverPort = Integer.parseInt(settings[1]);

        try {
            // Подключение к серверу
            Socket socket = new Socket(serverAddress, serverPort);

            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter serverOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedWriter logOut = new BufferedWriter(new FileWriter(LOG_FILE, true));

            // Чтение имени пользователя
            System.out.print("Введите ваше имя: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.println("Добро пожаловать в чат, " + username + "!");

            // Отправка имени пользователя серверу
            serverOut.write(username);
            serverOut.newLine();
            serverOut.flush();

            // Создание отдельного потока для получения сообщений от сервера
            Thread serverThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = serverIn.readLine()) != null) {
                        // Запись полученного сообщения в лог-файл
                        String logEntry = "[" + getCurrentTimestamp() + "] " + message + System.lineSeparator();
                        logOut.write(logEntry);
                        logOut.flush();

                        // Вывод сообщения на консоль
                        System.out.println(message);
                    }

                    // Закрытие потоков ввода-вывода и сокета при завершении связи с сервером
                    serverIn.close();
                    serverOut.close();
                    logOut.close();
                    socket.close();

                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            serverThread.start();

            String message;
            while (true) {
                // Ввод сообщения с консоли
                message = scanner.nextLine();

                // Отправка сообщения серверу
                serverOut.write(message);
                serverOut.newLine();
                serverOut.flush();

                // Проверка на команду выхода из чата
                if (message.equals(EXIT_COMMAND)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String[] readSettingsFromSettingsFile() {
        String[] settings = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            settings[0] = reader.readLine(); // Первая строка - IP adress
            settings[1] = reader.readLine(); // Вторая строка - Port
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return settings;
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}