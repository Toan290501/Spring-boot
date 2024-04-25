package org.example.spring_boot.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseObject {
    private String message;
    private String status;
    private Object data;

    public ResponseObject(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }


}
