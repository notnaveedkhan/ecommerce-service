package com.ecommerceservice.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse extends BasicResponse {

    private String path;
    private String timestamp;

    public ExceptionResponse(String status, String message, String path, String timestamp) {
        super(status, message);
        this.path = path;
        this.timestamp = timestamp;
    }
}
