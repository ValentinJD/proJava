package ru.java.pro.logging;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class LogUtil {
    // Извлекаем тело запроса из InputStream
    public static String extractBody(HttpServletRequest request) {
        try {
            return new BufferedReader(new InputStreamReader(request.getInputStream()))
                    .lines().collect(Collectors.joining("\\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
