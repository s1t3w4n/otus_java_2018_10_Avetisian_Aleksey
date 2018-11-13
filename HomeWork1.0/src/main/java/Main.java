import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        printSomething(setString());
        System.out.println("Hello, my first obfuscated jar file!");
    }
    private static List<String> setString(){
        List<String> lists = new ArrayList();
        lists.add("there");
        lists.add("is");
        lists.add("my");
        lists.add("dependencies");

        return lists;
    }
    private static void printSomething(List<String> list) {
        for (String s:list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
