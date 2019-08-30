import resource.DataBaseWrapper;

import java.io.IOException;

public class DataBaseClientMain {
    public static void main(String[] args) throws IOException {
        new DataBaseWrapper(Integer.valueOf(args[1]));
        System.out.println("Data Base Client Started");
    }
}
