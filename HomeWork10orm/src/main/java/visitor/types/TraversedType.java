package visitor.types;

import visitor.Service;

public interface TraversedType {
    void accept(Service service);
}
