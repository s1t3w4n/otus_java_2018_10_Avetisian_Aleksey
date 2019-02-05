import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class MainHW03 {
    public static void main(String[] args) {
        List<Integer> testArray = new MyArrayList<>();
        //testing (size, add, get) methods of my array
        System.out.println(testArray.size() + " Elements"); // 0 elements
        testArray.add(100); //adding 1-t element
        System.out.println(testArray.get(0)); // Iterator + get
        System.out.println(testArray.size() + " Element");// checking size
        testArray.add(99);// 2-nd
        System.out.println(testArray.get(1));
        testArray.add(null);// null
        System.out.println(testArray.get(2));
        System.out.println(testArray.size() + " Elements");

        //Collections.addAll
        List<String> firstArray = new MyArrayList<>(100); // creating first array
        IntStream.range(0, firstArray.size()).forEach(i -> firstArray.set(i, (firstArray.size() - i) + "firstArray"));
        System.out.println(String.join("\n", firstArray));

        System.out.println("---------------------------------------");
        System.out.println("Collections.addAll");
        Collections.addAll(firstArray, "1st addAll Element", "2nd addAll Element", "3d addAll Element");
        System.out.println(String.join("\n", firstArray));

        //Collections.copy
        List<String> secondArray = new MyArrayList<>(50);// creating 2nd array
//        for (int i = 0; i < secondArray.size(); i++) { // filling 2nd array
//            secondArray.set(i, i + "secondArray");
//            System.out.println(secondArray.get(i));
//        }
        IntStream.range(0, secondArray.size()).forEach(i -> secondArray.set(i, (secondArray.size() - i) + "secondArray"));
        System.out.println(String.join("\n", secondArray));
        System.out.println("---------------------------------------");
        System.out.println("Collections.copy");
        Collections.copy(firstArray,secondArray);
        System.out.println(String.join("\n", firstArray));
        //Collections.sort
        System.out.println("---------------------------------------");
        System.out.println("Collections.sort");
            Collections.sort(firstArray);
        System.out.println(String.join("\n", firstArray));
    }
}
