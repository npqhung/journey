package my.boot.journey.utils;

import lombok.Data;

@Data
public class AppResponse<T> {
    private boolean success;
    private T data;
    private String errorMessage;
}
