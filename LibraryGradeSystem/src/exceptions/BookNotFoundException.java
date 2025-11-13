package exceptions;

@SuppressWarnings("serial")
public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String msg) { super(msg); }
}
