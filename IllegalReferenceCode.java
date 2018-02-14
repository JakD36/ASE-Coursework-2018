public class IllegalReferenceCode extends RuntimeException{
    public IllegalReferenceCode(String refCode){
        super("This reference Code does not exist within lists"+refCode);
    }
}