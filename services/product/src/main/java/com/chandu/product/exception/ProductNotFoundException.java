package com.chandu.product.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.ExceptionHandler;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductNotFoundException extends RuntimeException {
    private final String msg;

}
