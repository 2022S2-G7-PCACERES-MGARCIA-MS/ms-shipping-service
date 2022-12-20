package uy.edu.ort.devops.shippingserviceexample.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uy.edu.ort.devops.shippingserviceexample.domain.Shipping;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShippingLogic {
    private static Logger logger = LoggerFactory.getLogger(ShippingLogic.class);

    private Map<String, Shipping> testShipping;

    public ShippingLogic() {
        testShipping = new HashMap<>();
        testShipping.put("a", new Shipping("Delivered", "a"));
        testShipping.put("b", new Shipping("In transit", "b"));
        testShipping.put("c", new Shipping("Preparing", "c"));
    }

    public void addShipping(String id) {
        logger.info("Adding shipping: " + id);

        int code = id.hashCode() % 10;

        switch (code) {
            case 0: case 1: case 2: testShipping.put(id, new Shipping("Delivered", id)); break;
            case 3: case 4: case 5: case 6: testShipping.put(id, new Shipping("In transit", id)); break;
            case 7: case 8: case 9: testShipping.put(id, new Shipping("Preparing", id)); break;
        }  
    }

    public boolean hasShipping(String id) {
        return testShipping.containsKey(id);
    }

    public Shipping getShipping(String id) {
        logger.info("Getting shipping: " + id);
        return testShipping.get(id);
    }
}
