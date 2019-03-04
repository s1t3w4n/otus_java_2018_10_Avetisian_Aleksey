import annotations.After;
import annotations.Before;
import annotations.Test;
@SuppressWarnings("Everything is fine")
public class ClassTest {
    public ClassTest(){
        System.out.println("Creating ClassTest");
    }
    @Test(developer = "test developer")
    public void testAnnotationMethod3() {
        System.out.println("I am testing the code");
    }
    public void noAnnotationMethod(){
        System.out.println("I have no annotation!");
    }
    @After(developer = "after developer")
    public void afterAnnotationMethod() {
        System.out.println("I am running after all others methods");
    }
    @Before(developer = "before developer")
    public void beforeAnnotationMethod1() {
        System.out.println("I am running before all others methods");
    }
    @Before(developer = "before developer")
    public void beforeAnnotationMethod2() {
        System.out.println("I am running before all others methods");
    }
    @Test(developer = "test developer")
    public void testAnnotationMethod1() {
        System.out.println("I am testing the code");
    }
    @Test(developer = "test developer")
    public void testAnnotationMethod2() {
        System.out.println("I am testing the code");
    }
    @Before(developer = "before developer")
    public void beforeAnnotationMethod3() {
        System.out.println("I am running before all others methods");
    }

    @Override
    public String toString() {
        return "I am ClassTest";
    }
}
