package hello.hellospring.controller.dto;

public class Response<T> {

    private T data;

    private int status;
    private int code;
    private String message;

    public Response(final T body) {
        this.data = body;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}