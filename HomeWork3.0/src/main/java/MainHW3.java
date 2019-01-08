import java.util.Collections;
import java.util.List;

public class MainHW3 {
    public static void main(String[] args) {
        List<Integer> testArray = new MyArrayList<>();
        //testing (size, add, get) methods of my array
        System.out.println(testArray.size() + " Elements"); // 0 elements
        testArray.add(100); //adding 1-t element
        System.out.println(testArray.get(0)); // Iterator + get
        System.out.println(testArray.size() + " Element");// checking size
        testArray.add(99);// 2-nd
        System.out.println(testArray.get(1));
        System.out.println(testArray.size() + " Elements");

        //Collections.addAll
        List<String> firstArray = new MyArrayList<>(100); // creating first array
        for (int i = 0; i < firstArray.size(); i++) { // filling 1st array
            firstArray.set(i, (firstArray.size() - i) + "firstArray");
            System.out.println(firstArray.get(i));
        }
        System.out.println("---------------------------------------");
        System.out.println("Collections.addAll");
        Collections.addAll(firstArray, "1st addAll Element", "2nd addAll Element", "3d addAll Element");
        for (String s: firstArray) {
            System.out.println(s);
        }

        //Collections.copy
        List<String> secondArray = new MyArrayList<>(50);// creating 2nd array
        for (int i = 0; i < secondArray.size(); i++) { // filling 2nd array
            secondArray.set(i, i + "secondArray");
            System.out.println(secondArray.get(i));
        }
        System.out.println("---------------------------------------");
        System.out.println("Collections.copy");
        Collections.copy(firstArray,secondArray);
        for (String s: firstArray) {
            System.out.println(s);
        }

        //Collections.sort
        System.out.println("---------------------------------------");
        System.out.println("Collections.sort");
        Collections.sort(firstArray);
        for (String s: firstArray) {
            System.out.println(s);
        }
    }
}
