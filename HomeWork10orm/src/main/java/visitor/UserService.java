package visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import visitor.types.TraversedArray;
import visitor.types.TraversedObject;
import visitor.types.TraversedPrimitive;
import visitor.types.TraversedString;


public class UserService implements Service {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public void visit(TraversedArray value) {
        logger.info("Array : " + value.getField().getType());
    }

    @Override
    public void visit(TraversedPrimitive value) {
        logger.info("Primitive : " + value.getField().getType());
    }

    @Override
    public void visit(TraversedObject value) {
        logger.info("Object : " + value.getField().getType());
    }

    @Override
    public void visit(TraversedString value) {
        logger.info("String : " + value.getField().getType());
    }
}
