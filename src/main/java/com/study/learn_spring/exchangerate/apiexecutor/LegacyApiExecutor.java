package com.study.learn_spring.exchangerate.apiexecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class LegacyApiExecutor implements ApiExecutor {
    @Override
    public String execute(URI uri) throws IOException {
        String body;
        URLConnection urlConnection = uri.toURL().openConnection();
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            body = buff.lines().collect(Collectors.joining());
        }
        return body;
    }
}
