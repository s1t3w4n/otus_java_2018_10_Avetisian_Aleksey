import java.util.ArrayList;
import java.util.List;

public class MainHW13fjp {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        ListHelper.fillList(100, list);
        ListHelper.printArray(list);
        new MySort(list).go();
        ListHelper.printArray(list);
    }

}
