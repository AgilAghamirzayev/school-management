package com.example.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionHandling {

    private Date timeStamp;
    private String error;
    private Integer statusCode;
    private String message;
    private String path;

}
