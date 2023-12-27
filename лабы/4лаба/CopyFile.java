import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

public class CopyFile {

    public static void copyContent(File a, File b) throws Exception {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);

        try {
            int n;
            out.close();
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        } catch (IOException e) {
            System.out.println("Player file wouldn't load!");
            return;
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }

    public static void main(String[] args) throws Exception {
        File x = new File("4лаба.docx");
        File y = new File("new.docx");
        copyContent(x, y);
    }
}