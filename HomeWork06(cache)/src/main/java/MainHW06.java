public class MainHW06 {

    public static void main(String[] args) {
        new MainHW06().demo();
    }

    private void demo() {
        HwCache<Integer,Integer> cache = new MyCache<>();
        HwListener<Integer, Integer> listener =
                (key, value, action) -> System.out.println("key:" + key + ", value:" + value + ", action:" + action);
        cache.addListener(listener);
        cache.put(1,1);
        //System.out.println(cache.get(1));
        System.out.println(cache.get(0));
        cache.remove(1);
        cache.removeListener(listener);

    }
}
