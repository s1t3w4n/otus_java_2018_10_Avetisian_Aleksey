import com.google.gson.Gson;


/*
Домашнее задание.
        Напишите свой json object writer (object to JSON string) аналогичный gson на основе javax.json.

        Поддержите:
        - массивы объектов и примитивных типов
        - коллекции из стандартной библиотерки.
*/
public class MainHW09 {

    public static void main(String[] args) {
        //Object obj = new Object();
        //boolean obj = true;
        //char[] obj = {'a', 'b', 'c'};
        String obj = "asdg";
        Gson gson = new Gson();
        MyJson myJson = new MyJson();
        String json = myJson.toJson(obj);
        System.out.println("json" + json);
        String gsont = gson.toJson(obj);
        System.out.println("gson " + gsont);
        gson.fromJson(json, Object.class);
    }
}
