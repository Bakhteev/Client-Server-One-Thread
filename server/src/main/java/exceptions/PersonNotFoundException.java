package exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String message){
        super(message);
    }
}
