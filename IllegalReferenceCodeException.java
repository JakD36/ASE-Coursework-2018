public class IllegalReferenceCodeException extends RuntimeException{
    public IllegalReferenceCodeException(String refCode){
        super("This reference Code does not exist within lists"+refCode);
    }
}