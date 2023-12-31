import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ChatServer {
    private static final String SETTINGS_FILE = "settings.txt";
    private static final String LOG_FILE = "file.log";
    private static List<PrintWriter> clients = new ArrayList<>();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        String[] settings = readSettingsFromSettingsFile();
        String ipAddress = settings[0];
        int port = Integer.parseInt(settings[1]);

        try {
            ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(ipAddress));
            System.out.println("Сервер чата запущен на IP " + ipAddress + " и порту " + port);

            Thread serverInputThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (isRunning) {
                    String serverMessage = scanner.nextLine();

                    for (PrintWriter client : clients) {
                        client.println("[Server]: " + serverMessage);
                    }
                }
            });
            serverInputThread.start();

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новый клиент подключился: " + clientSocket);

                Thread clientThread = new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FILE, true));

                        String username = in.readLine();
                        System.out.println("Пользователь " + username + " присоединился к чату");

                        clients.add(out);

                        String message;
                        while ((message = in.readLine()) != null) {
                            // Запись сообщения в лог-файл
                            String logEntry = "[" + getCurrentTimestamp() + "] " + username + ": " + message + System.lineSeparator();
                            logWriter.write(logEntry);
                            logWriter.flush();

                            for (PrintWriter client : clients) {
                                client.println(logEntry);
                            }

                            System.out.println(logEntry);
                        }

                        clientSocket.close();
                        in.close();
                        out.close();
                        logWriter.close();

                        removeClient(out);

                        System.out.println("Пользователь " + username + " покинул чат");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String[] readSettingsFromSettingsFile() {
        String[] settings = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            settings[0] = reader.readLine();
            settings[1] = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return settings;
    }

    static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    private synchronized static void removeClient(PrintWriter client) {
        Iterator<PrintWriter> iterator = clients.iterator();
        while (iterator.hasNext()) {
            PrintWriter c = iterator.next();
            if (c == client) {
                iterator.remove();
                return;
            }
        }
    }

    public static void stopServer() {
        isRunning = false;
    }
}