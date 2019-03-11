package visitor.types;

import visitor.Service;

import java.lang.reflect.Field;

public class TraversedPrimitive extends TraversedField {

    public TraversedPrimitive(Field field) {
        super(field);
    }

    @Override
    public void accept(Service service) {
        service.visit(this);
    }
}
