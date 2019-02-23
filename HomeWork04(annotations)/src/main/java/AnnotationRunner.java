import annotations.*;
import chain.AfterChain;
import chain.BeforeChain;
import chain.Chain;
import chain.TestChain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationRunner {
    public static void run(Class<?> clazz) {
        List<Method> methods = Arrays.asList(clazz.getMethods());

        System.out.println("Checking annotations...");
        System.out.println("Number of methods: " + methods.size());
        System.out.println("Number of Before methods: " + annotationCounter(Before.class, methods));
        System.out.println("Number of After methods: " + annotationCounter(After.class, methods));
        System.out.println("Number of Test methods: " + annotationCounter(Test.class, methods));

        Chain before = new BeforeChain();
        List<TestChain> tests = new ArrayList<>();
        Chain after = new AfterChain();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                before.fill(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                after.fill(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                tests.add(new TestChain(method));
            }
        }

        for (TestChain test : tests) {
            try {
                Object temporaryObject = clazz.getConstructor().newInstance();
                if (!methods.isEmpty()) {
                    before.setNext(test);
                    test.setNext(after);

                    before.run(temporaryObject);


                    // the order
                    /*invokeMethods(Before.class,methods,temporaryObject); // Before methods
                    invokeMethods(Test.class,methods,temporaryObject); // Test methods
                    invokeMethods(After.class,methods,temporaryObject); // After methods*/

                } else {
                    System.out.println("No methods in class");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* private static void invokeMethods(Class<? extends Annotation> a, List<Method> methods,Object object) throws InvocationTargetException, IllegalAccessException {
             for (Method m : methods) {
                 if (m.isAnnotationPresent(a)) {
                     methodInfo(m);
                     System.out.println("My name is: " + m.getName());
                     m.invoke(object);
                 }
             }
     }*/
    private static void methodInfo(Method method) {
        if (method.isAnnotationPresent(Before.class)) {
            Before before = method.getAnnotation(Before.class);
            System.out.println('\n' + "My Developer is: " + before.developer());
        }
        if (method.isAnnotationPresent(After.class)) {
            After after = method.getAnnotation(After.class);
            System.out.println('\n' + "My Developer is: " + after.developer());
        }

        if (method.isAnnotationPresent(Test.class)) {
            Test test = method.getAnnotation(Test.class);
            System.out.println('\n' + "My Developer is: " + test.developer());
        }
    }

    //just for me
    private static int annotationCounter(Class<? extends Annotation> a, List<Method> methods) {
        int count = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(a)) {
                count++;
            }
        }
        return count;
    }
}
