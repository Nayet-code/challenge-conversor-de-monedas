package com.alura.conversormonedas.model;

import java.util.Map;

public class TasaDeCambio {
    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;


    public TasaDeCambio() {
    }

    // Gson usa Getters y Setters para serializar y deserializar.

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }


    // Metodo toString para una representación legible del objeto (opcional, pero útil para depuración).
    //@return Una cadena que representa el objeto TasaDeCambio.

    @Override
    public String toString() {
        return "TasaDeCambio{" +
                "result='" + result + '\'' +
                ", base_code='" + base_code + '\'' +
                ", conversion_rates=" + conversion_rates +
                '}';
    }
}
