package vn.techmaster.woodshop.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String message) {
        super(message);
    }
}
