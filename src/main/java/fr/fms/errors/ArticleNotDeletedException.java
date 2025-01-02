package fr.fms.errors;

public class ArticleNotDeletedException extends RuntimeException {
    public ArticleNotDeletedException(String message) {
        super(message);
    }
}
