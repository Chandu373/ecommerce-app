package com.chandu.order.exception;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class OrderNotFoundException extends RuntimeException{
    private final String msg;
}
