package newsapi;

public class NewsException extends Exception {
    public NewsException(String error){
        super(error);
    }
}
