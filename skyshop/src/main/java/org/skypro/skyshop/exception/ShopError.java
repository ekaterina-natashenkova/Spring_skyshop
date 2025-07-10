package org.skypro.skyshop.exception;

import java.util.Objects;

public class ShopError {

    private final String code;
    private final String message;


    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ShopError{" + "code='" + code + '\'' + ", message='" + message + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShopError shopError)) return false;
        return Objects.equals(code, shopError.code) && Objects.equals(message, shopError.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

}
