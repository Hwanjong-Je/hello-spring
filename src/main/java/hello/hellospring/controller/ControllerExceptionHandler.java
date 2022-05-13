package hello.hellospring.controller;

import hello.hellospring.controller.dto.Response;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.LimitExceededException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {BeanCreationNotAllowedException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Response<?> beanCreationNotAllowedException(Exception ex, WebRequest req) {
        final Response response = new Response<>(ex.toString());
        response.setCode(1000);
        response.setStatus(503);
        response.setMessage("BeanCreationNotAllowedException");

        return response;
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<?> notFoundException(Exception ex, WebRequest req) {
        final Response response = new Response<>(ex.toString());
        response.setCode(2500);
        response.setStatus(404);
        response.setMessage(ex.getMessage());

        return response;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class,
            InvalidDataAccessApiUsageException.class,
            MethodArgumentTypeMismatchException.class,
            BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> methodArgumentTypeMismatchException(Exception ex, WebRequest req) {
        final Response response = new Response<>(ex.toString());
        response.setCode(1400);
        response.setStatus(400);
        response.setMessage("Method Argument Type Mismatch");

        return response;
    }

    @ExceptionHandler(value = {LimitExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> argumentSizeLimitException(Exception ex, WebRequest req) {
        final Response response = new Response<>(ex.toString());
        response.setCode(1400);
        response.setStatus(400);
        response.setMessage(ex.getMessage());

        return response;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> unknownException(Exception ex, WebRequest req) {
        final Response response = new Response<>(ex.toString());
        response.setCode(1500);
        response.setStatus(500);
        response.setMessage("UncaughtException");

        return response;
    }

}