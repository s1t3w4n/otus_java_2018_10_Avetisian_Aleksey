import com.google.gson.Gson;
import examples.Arrays;
import examples.Collections;
import examples.Objects;
import examples.Primitives;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyJsonTest {
    Gson gson;
    MyJson myJson;

    @BeforeEach
    void configure() throws Exception {
        myJson = new MyJson();
        gson = new Gson();
    }

    @Test
    void checkPrimitives() {
        Primitives primitives = new Primitives();
        Assertions.assertEquals(gson.toJson(primitives), myJson.toJson(primitives));
    }

    @Test
    void checkArrays() {
        Arrays object = new Arrays();
        Assertions.assertEquals(gson.toJson(object), myJson.toJson(object));
    }

    @Test
    void checkToJsonCollections() {
        Collections object = new Collections();
        Assertions.assertEquals(gson.toJson(object), myJson.toJson(object));
    }

    @Test
    void checkObjects() {
        Objects object = new Objects();
        Assertions.assertEquals(gson.toJson(object), myJson.toJson(object));
    }



    @AfterEach
    void clean() throws Exception {
        myJson = null;
        gson = null;
    }
}
