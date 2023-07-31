package com.ecommerceservice.application.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse implements Serializable {

    private String status;
    private String message;

}
