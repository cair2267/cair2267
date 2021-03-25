package tasks.model.validator;/*@author: Andrei Chiritoiu*/

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
