package cn.gzsendi.huangyj.util;

/**
 * 响应实体类
 *
 * @author yangziqun
 * @version 1.0
 * @date 2019/11/22 10:00
 */
public class ResponseEntity<T> {

    private Integer code;
    private String message;
    private T data;

    public ResponseEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseEntity(ResponseCode responseCode) {
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    public static <T> ResponseEntity<T> SUCCESS() {
        return new ResponseEntity<T>(ResponseCode.SUCCESS);
    }

    public static <T> ResponseEntity<T> SUCCESS(T data) {
        return new ResponseEntity<T>(ResponseCode.SUCCESS).data(data);
    }

    public static <T> ResponseEntity<T> ERROR(T data) {
        return new ResponseEntity<T>(ResponseCode.ERROR).data(data);
    }

    public static <T> ResponseEntity<T> ERROR() {
        return new ResponseEntity<T>(ResponseCode.ERROR);
    }

    public static <T> ResponseEntity<T> ERROR(String message) {
        return new ResponseEntity<T>(ResponseCode.ERROR).message(message);
    }

    public T getData() {
        return this.data;
    }

    public ResponseEntity<T> data(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseEntity<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseEntity<T> message(String message) {
        this.message = message;
        return this;
    }

}
