package fr.fms.exceptions;

public class ArticleNotDeletedException extends RuntimeException {
    public ArticleNotDeletedException(String message) {
        super(message);
    }
}
