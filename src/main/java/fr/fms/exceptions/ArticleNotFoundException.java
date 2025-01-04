package fr.fms.exceptions;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException() {
        super("Article not found !");
    }
}
