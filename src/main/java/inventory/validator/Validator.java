package inventory.validator;
import inventory.exception.ValidateException;
@FunctionalInterface
public interface Validator<T> {
    void validate(T object) throws ValidateException;
}