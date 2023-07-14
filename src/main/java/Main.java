import java.io.File;

public class Main {
    public static void main(String[] args) {
        File currentDirectory = new File("");
        System.out.println("Текущая папка: " + currentDirectory.getAbsolutePath());
    }
}
