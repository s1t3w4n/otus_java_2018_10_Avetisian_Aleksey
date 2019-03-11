package visitor.types;

import visitor.Service;

import java.lang.reflect.Field;

public class TraversedString extends TraversedField {
    public TraversedString(Field field) {
        super(field);
    }

    @Override
    public void accept(Service service) {
        service.visit(this);
    }
}
