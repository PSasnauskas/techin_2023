package lt.techin.zoo.api;

import lt.techin.zoo.api.dto.ErrorDto;
import lt.techin.zoo.api.dto.ErrorFieldDto;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        return "database_error";
    }

//    @ExceptionHandler(IOException.class)
//    public void zooNotFoundException() {
//        logger.error("IOException handler executed");
//        //returning 404 error code
//    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        logger.error("IOException handler executed");
        //returning 404 error code
    }

    //@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "SQL query error")

    //    public void handleSQLGrammarException(SQLGrammarException exception) {
//        logger.error("All Exceptions handler executed: {}. SQL: {}. Get SQL: {}",
//                exception.getMessage(), exception.getSQLException(), exception.getSQL());
//        //returning 404 error code
//    }
    //@ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDto> handleSQLGrammarException(HttpServletRequest request, DataAccessException dataAccessException) {
//        logger.error("All Exceptions handler executed: {}. SQL: {}. SQL was: {}",
//                dataAccessException.getMessage(), dataAccessException.getSQLException(), sqlGrammarException.getSQL());
        logger.error("DataAccessException: {}. Cause?: {}",
                dataAccessException.getMessage(), dataAccessException.getMostSpecificCause().getMessage());

        var errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorFields = List.of(
                new ErrorFieldDto("sql", dataAccessException.getMostSpecificCause().getMessage())
        );
        var errorDto = new ErrorDto(request.getRequestURL().toString(),
                errorFields,
                dataAccessException.getMessage(),
                errorStatus.value(),
                errorStatus.getReasonPhrase(),
                request.getRequestURL().toString(),
                LocalDateTime.now());
        return ResponseEntity.internalServerError().body(errorDto);
    }

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException notValidException) {
//

        //    logger.error("MethodArgumentNotValidException: {}. Fields: {}",
//            notValidException.getMessage(), notValidException.getAllErrors().getMostSpecificCause().getMessage());

    var errorStatus = HttpStatus.BAD_REQUEST;

    var errorFields = notValidException.getBindingResult()
            .getAllErrors().stream()
            .map(error -> new ErrorFieldDto(error.get .getObjectName(), errorStatus.getReasonPhrase()))
            .collect(toList());

    var errorDto = new ErrorDto(request.getRequestURL().toString(),
            errorFields,
            notValidException.getMessage(),
            errorStatus.value(),
            errorStatus.getReasonPhrase(),
            request.getRequestURL().toString(),
            LocalDateTime.now());
    return ResponseEntity.badRequest().body(errorDto);

//        {
//            "timestamp": "2023-01-10T17:04:12.995+00:00",
//                "status": 500,
//                "error": "Internal Server Error",
//                "path": "/api/v1/animals/marked"

//        }
}

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something really bad happened")
    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(Exception exception) {
        logger.error("All Exceptions handler executed: {}", exception.getMessage());
        //returning 404 error code
    }


}