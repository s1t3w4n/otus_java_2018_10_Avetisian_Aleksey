package visitor.types;

import visitor.Service;

import java.lang.reflect.Field;

public class TraversedArray extends TraversedField {
    private final Object array;

    public TraversedArray(Field field, Object array) {
        super(field);
        this.array = array;
    }

    public Object getArray() {
        return array;
    }

    @Override
    public void accept(Service service) {
        service.visit(this);
    }
}
