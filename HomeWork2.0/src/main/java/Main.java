import java.util.*;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        comparePrimitives();
        compareVoidCollections();
        compareString();
        compareFilledCollections();
    }


    private static void compareFilledCollections() {
        System.out.println("Comparing Collections with elements...");
        Stand.factoryArrayList(() -> new String());
        Stand.factoryArrayList(() -> new String("Not empty String"));
        Stand.factoryArrayList(() -> new Integer(random.nextInt()));
        Stand.factoryArrayList(() -> new PriorityQueue());
    }
    private static void compareString() {
        System.out.println("Comparing Strings...");
        System.out.println("empty String() element size:" + Stand.factory(() ->
                new String()));// empty String
        System.out.println("not empty String() element size:" + Stand.factory(() ->
                new String("String Element"))); // filled String
    }
    private static void compareVoidCollections() {
        System.out.println("Comparing void collections...");

        System.out.println("ArrayList() element size:" + Stand.factory(() -> new ArrayList())); // ArrayList()
        System.out.println("LinkedList() element size:" + Stand.factory(() -> new LinkedList())); // LinkedList()
        System.out.println("TreeSet() element size:" + Stand.factory(() -> new TreeSet())); // TreeSet()
        System.out.println("LinkedHashSet() element size:" + Stand.factory(() -> new LinkedHashSet())); // LinkedHashSet()
        System.out.println("Vector() element size:" + Stand.factory(() -> new Vector())); // Vector()
        System.out.println("PriorityQueue() element size:" + Stand.factory(() -> new PriorityQueue())); // PriorityQueue()

        System.out.println("HashMap() element size:" + Stand.factory(() -> new HashMap())); // HashMap()
        System.out.println("LinkedHashMap() element size:" + Stand.factory(() -> new LinkedHashMap())); // LinkedHashMap()
        //System.out.println("Hashtable() element size:" + Stand.factory(() -> new Hashtable())); // Hashtable()
        System.out.println("TreeMap() element size:" + Stand.factory(() -> new TreeMap())); // TreeMap()
    }

    private static void comparePrimitives() {
        System.out.println("Comparing primitives with objects alternatives...");

        System.out.println("byte element size:" + Stand.byteMemCheck()); // byte
        System.out.println("Byte() element size:" + Stand.factory(() -> new Byte("111"))); // Byte()

        System.out.println("short element size:" + Stand.shortMemCheck()); // short
        System.out.println("Short() element size:" + Stand.factory(() -> new Short(
                (short)(Math.random()*Short.MIN_VALUE)))); // Short()


        System.out.println("int element size:" + Stand.intMemCheck()); // int
        System.out.println("Integer() element size:" + Stand.factory(() -> new Integer(random.nextInt()))); // Integer()

        System.out.println("long element size:" + Stand.longMemCheck()); // long
        System.out.println("Long() element size:" + Stand.factory(() -> new Long(Long.MIN_VALUE))); // Long()

        System.out.println("boolean element size:" + Stand.booleanMemCheck()); // boolean
        System.out.println("Boolean() element size:" + Stand.factory(() -> new Boolean(random.nextBoolean()))); // Boolean()

        System.out.println("char element size:" + Stand.charMemCheck()); // char
        System.out.println("Character() element size:" + Stand.factory(() -> new Character(
                (char)(random.nextInt(65535))))); // Character()

        System.out.println("float element size:" + Stand.floatMemCheck()); // float
        System.out.println("Float() element size:" + Stand.factory(() -> new Float(random.nextFloat()))); // Float()


        System.out.println("double element size:" + Stand.doubleMemCheck()); // double
        System.out.println("Double() element size:" + Stand.factory(() -> new Double(random.nextFloat()))); // Double()
    }
}
