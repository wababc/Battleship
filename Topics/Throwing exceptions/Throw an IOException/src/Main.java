import java.io.IOException;

public class Main {

    // change this method
    public static void method() throws IOException {
        boolean found=false;

        if (!found) {
            throw new IOException("The file  is not found");
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        try {
            method();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}