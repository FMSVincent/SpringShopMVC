package fr.fms.errors;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
