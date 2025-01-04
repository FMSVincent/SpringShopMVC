package fr.fms.exceptions;

public class ArticleNotDeletedException extends RuntimeException {
    public ArticleNotDeletedException() {
        super("impossible a supprimer !");
    }
}
