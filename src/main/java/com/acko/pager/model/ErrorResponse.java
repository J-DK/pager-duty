package com.acko.pager.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {

    String errorMessage;
    HttpStatus status;
}
