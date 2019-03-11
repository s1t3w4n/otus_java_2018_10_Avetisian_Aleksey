package visitor.types;

import visitor.Service;

import java.lang.reflect.Field;

public class TraversedObject extends TraversedField {

    public TraversedObject(Field field) {
        super(field);
    }

    @Override
    public void accept(Service service) {
        service.visit(this);
    }
}
