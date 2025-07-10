package org.skypro.skyshop.exception;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException() {
        super("Нет такого продукта"); // или лучше тут более конкретный текст ошибки задать, как было раньше ("Отсутствует продукт с id: " + id)
    }

}
