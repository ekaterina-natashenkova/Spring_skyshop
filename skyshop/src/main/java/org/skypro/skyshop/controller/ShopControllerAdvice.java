package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.exception.ShopError;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> NoSuchProductHandler (NoSuchProductException exception){
        ShopError shopError = new ShopError("NO_SUCH_PRODUCT_WITH_THIS_ID", "Нет продукта с данным id");
        return new ResponseEntity<ShopError>(shopError, HttpStatusCode.valueOf(404));
    }

}
