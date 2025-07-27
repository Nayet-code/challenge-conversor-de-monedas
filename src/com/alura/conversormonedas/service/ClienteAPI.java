package com.alura.conversormonedas.service;

import com.google.gson.Gson;
import com.alura.conversormonedas.model.TasaDeCambio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteAPI {
    // URL base de la API. Reemplaza "YOU_API_KEY" por tu clave API auténtica.
    private static final String BASE_API_URL = "https://v6.exchangerate-api.com/v6/YOU-API-KEY/latest/";
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();


    public TasaDeCambio obtenerTasasDeCambio(String monedaBase) throws IOException, InterruptedException {
        // Crea la URL para la solicitud.
        String url = BASE_API_URL + monedaBase;

        // Crea una solicitud HTTP GET.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Envío y recepción de la respuesta.
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // 1.- Éxito: código 200 OK.
        // 2.- Gson: JSON a TasaDeCambio.
        // 3.- Si la API falla, lanza un error.
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), TasaDeCambio.class);
        } else {
            throw new IOException("Error al obtener tasas de cambio. Código de estado: " + response.statusCode() +
                    ". Cuerpo de respuesta: " + response.body());
        }
    }
}
