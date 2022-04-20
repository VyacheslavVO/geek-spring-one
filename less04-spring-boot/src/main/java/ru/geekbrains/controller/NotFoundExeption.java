package ru.geekbrains.controller;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String message) {
        super(message);
    }
}
