package com.advise_clothes.project_advise_clothes.controller.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpMethod<T>{
    private T t;

    public T getT() { return t; }
    public void setT(T t) { this.t = t; }

    public ResponseEntity<T> ok (T object) {
        return ResponseEntity.status(HttpStatus.OK).body(object == null? t : object);
    }
}
