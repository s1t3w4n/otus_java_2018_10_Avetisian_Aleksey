package visitor;

import visitor.types.TraversedArray;
import visitor.types.TraversedObject;
import visitor.types.TraversedPrimitive;
import visitor.types.TraversedString;

public interface Service {

    void visit(TraversedArray value);

    void visit(TraversedPrimitive value);

    void visit(TraversedObject value);

    void visit(TraversedString value);
}
