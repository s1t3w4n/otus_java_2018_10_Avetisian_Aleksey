import com.google.gson.Gson;
import examples.Arrays;
import examples.TestCollections;
import examples.Objects;
import examples.Primitives;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;


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
        assertEquals(gson.toJson(primitives), myJson.toJson(primitives));
    }

    @Test
    void checkArrays() {
        Arrays object = new Arrays();
        assertEquals(gson.toJson(object), myJson.toJson(object));
    }

    @Test
    void checkCollections() {
        TestCollections object = new TestCollections();
        assertEquals(gson.toJson(object), myJson.toJson(object));
    }

    @Test
    void checkObjects() {
        Objects object = new Objects();
        assertEquals(gson.toJson(object), myJson.toJson(object));
    }

    @Test
    void checkNull() {
        assertEquals(gson.toJson(null), myJson.toJson(null));
    }

    @Test
    void checkPrimitiveArray() {
        assertEquals(gson.toJson(new int[]{1, 2, 3}), myJson.toJson(new int[]{1, 2, 3}));
        assertEquals(gson.toJson(new char[]{'a', 's', 3}), myJson.toJson(new char[]{'a', 's', 3}));
    }

    @Test
    void checkCollectionsSingle() {
        assertEquals(gson.toJson(Collections.singletonList(1)), myJson.toJson(Collections.singletonList(1)));
    }


    /*@Test
    void checkPrimitiveArray() {
        Objects object = new Objects();
        Assertions.assertEquals(gson.toJson, myJson.toJson(new int[] {1, 2, 3}));
    }*/


    @AfterEach
    void clean() throws Exception {
        myJson = null;
        gson = null;
    }
}
