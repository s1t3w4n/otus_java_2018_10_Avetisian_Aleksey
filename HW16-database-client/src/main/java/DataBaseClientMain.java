import resource.DataBaseWrapper;

import java.io.IOException;

public class DataBaseClientMain {
    public static void main(String[] args) throws IOException {
        new DataBaseWrapper(5051);
        System.out.println("Data Base Client Started");
    }
}
