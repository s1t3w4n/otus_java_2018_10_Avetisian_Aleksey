package visitor.types;

import java.lang.reflect.Field;

public abstract class TraversedField implements TraversedType {
    private final Field field;

    protected TraversedField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
