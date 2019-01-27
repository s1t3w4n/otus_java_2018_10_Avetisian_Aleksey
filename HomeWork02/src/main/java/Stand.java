import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class Stand {

    private final static int LENGTH = 10_000_000;
    private static Random random = new Random();


    static <T> void factoryArrayList(Supplier<T> objectGetter)  {
        long start = getMem();
        ArrayList array = new ArrayList();
        long ref = getMem();
        long size;

        for (int i = 0; i < LENGTH; i++) {
            array.add(objectGetter.get());

            if ((i + 1) % 1_000_000 == 0) {
                ref = getMem();
                size = (ref - start) / array.size();
                System.out.println("Added " + (i + 1) + " elements, size of element: " + size);
                System.out.println("ArrayList() size: " + (ref - start));
            }
        }
        long end = getMem();
        System.out.println("Final Element in ArrayList() size: " + (end - start) / array.size());
        System.out.println("Final ArrayList() size: " + (end - start));
    }

    static <T> long factory(Supplier<T> objectGetter)  {
        Object[] array = new Object[LENGTH];
        long start = getMem();

        for (int i = 0; i < array.length; i++) {
            array[i] = objectGetter.get();
        }
        long end = getMem();
        long size = (end - start) / array.length;
        return size;
    }

   static long byteMemCheck() {
        long start = getMem();
        byte[] byteArr = new byte[LENGTH];


        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = 100;
        }
        long end = getMem();
        long size = (end - start) / byteArr.length;
        return size;
    }

    static long shortMemCheck() {
        long start = getMem();
        short[] shortArr = new short[LENGTH];


        for (int i = 0; i < shortArr.length; i++) {
            shortArr[i] = Short.MAX_VALUE;
        }
        long end = getMem();
        long size = (end - start) / shortArr.length;
        return size;
    }

    static long intMemCheck() {
        long start = getMem();
        int[] intArr = new int[LENGTH];


        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = (int) Math.random()*Integer.MAX_VALUE;
        }
        long end = getMem();
        long size = (end - start) / intArr.length;
        return size;
    }

    static long longMemCheck() {
        long start = getMem();
        long[] longArr = new long[LENGTH];


        for (int i = 0; i < longArr.length; i++) {
            longArr[i] = (long) Math.random()*Long.MAX_VALUE;
        }
        long end = getMem();
        long size = (end - start) / longArr.length;
        return size;
    }
    static long booleanMemCheck() {
        long start = getMem();
        boolean[] booleanArr = new boolean[LENGTH];


        for (int i = 0; i < booleanArr.length; i++) {
            booleanArr[i] = ((i % 2) > 0) ? true: false;
        }
        long end = getMem();
        long size = (end - start) / booleanArr.length;
        return size;
    }

    static long charMemCheck() {
        long start = getMem();
        char[] charArr = new char[LENGTH];


        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = (char) (Math.random()*65535);
        }
        long end = getMem();
        long size = (end - start) / charArr.length;
        return size;
    }

    static long floatMemCheck() {
        long start = getMem();
        float[] floatArr = new float[LENGTH];


        for (int i = 0; i < floatArr.length; i++) {
            floatArr[i] = (float) (i - 0.1);
        }
        long end = getMem();
        long size = (end - start) / floatArr.length;
        return size;
    }

    static long doubleMemCheck() {
        long start = getMem();
        double[] doubleArr = new double[LENGTH];


        for (int i = 0; i < doubleArr.length; i++) {
            doubleArr[i] = random.nextDouble();
        }
        long end = getMem();
        long size = (end - start) / doubleArr.length;
        return size;
    }

    private static long getMem() {
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
