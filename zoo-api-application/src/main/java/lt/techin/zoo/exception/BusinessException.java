package lt.techin.zoo.exception;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

}
