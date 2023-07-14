import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatServerTest {

    @Test
    public void testGetCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expectedTimestamp = dateFormat.format(new Date());
        String actualTimestamp = ChatServer.getCurrentTimestamp();
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testReadSettingsFromSettingsFile() {
        String[] expectedSettings = {"localhost", "8080"};

        String[] actualSettings = ChatServer.readSettingsFromSettingsFile();

        assertEquals(expectedSettings[0], actualSettings[0]);
        assertEquals(expectedSettings[1], actualSettings[1]);
    }

    @Test
    public void testCreateServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        SocketAddress expected = new InetSocketAddress("localhost", 8080);
        SocketAddress actual = serverSocket.getLocalSocketAddress();

        assertEquals(expected, actual);
    }

    @Test
    public void testReadClientMessages() throws IOException {
        Socket clientSocket = Mockito.mock(Socket.class);
        InputStream inputStream = new ByteArrayInputStream("Test message".getBytes());
        Mockito.when(clientSocket.getInputStream()).thenReturn(inputStream);

        BufferedReader in = Mockito.mock(BufferedReader.class);
        Mockito.when(in.readLine()).thenReturn("Test message", null);

        String expected = "Test message";
        String actual = in.readLine();
        assertEquals(expected, actual);
    }
}