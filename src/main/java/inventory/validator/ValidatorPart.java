package inventory.validator;
import inventory.exception.ValidateException;
import inventory.model.Part;
public class ValidatorPart implements Validator<Part> {
    @Override
    public void validate(Part part) throws ValidateException {
        String errorMessage = "";
        if(part.getName().equals("")) {
            errorMessage += "A name has not been entered. ";
        }
        if(part.getPrice() < 0.01) {
            errorMessage += "The price must be greater than 0. ";
        }
        if(part.getInStock() < 1) {
            errorMessage += "Inventory level must be greater than 0. ";
        }
        if(part.getMin() > part.getMax()) {
            errorMessage += "The Min value must be less than the Max value. ";
        }
        if(part.getInStock() < part.getMin()) {
            errorMessage += "Inventory level is lower than minimum value. ";
        }
        if(part.getInStock() > part.getMax()) {
            errorMessage += "Inventory level is higher than the maximum value. ";
        }
        if(!errorMessage.isEmpty()){
            throw new ValidateException(errorMessage);
        }
    }
}