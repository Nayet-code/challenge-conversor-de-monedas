package com.alura.conversormonedas.principal;

import com.alura.conversormonedas.model.TasaDeCambio;
import com.alura.conversormonedas.service.ClienteAPI;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    // Lista específica
    private static final List<String> MONEDAS_SOPORTADAS = Arrays.asList(
            "ARS", "BOB", "BRL", "CLP", "COP", "USD"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteAPI clienteAPI = new ClienteAPI();

        System.out.println("********************************************************************");
        System.out.println("          Bienvenido al Conversor de Monedas:");
        System.out.println("         Acceso instantáneo a Tasas de Cambio.");
        System.out.println("********************************************************************");
        System.out.println("Monedas soportadas: " + String.join(", ", MONEDAS_SOPORTADAS));

        // Menú principal y acciones.
        while (true) {
            mostrarMenu();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        convertirMoneda(scanner, clienteAPI);
                        break;
                    case 2:
                        mostrarTasasDeCambio(scanner, clienteAPI);
                        break;
                    case 3:
                        System.out.println("Proceso de conversión concluido. ¡Gracias por utilizar el Conversor de Monedas!");
                        return; // Sale del programa.
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 3.");
                }
            } catch (InputMismatchException e) {
                // Requiere solo números.
                System.out.println("Entrada inválida. Por favor, ingrese un número para seleccionar una opción.");
                scanner.nextLine(); // Limpia y evita bucles.
            } catch (IOException | InterruptedException e) {
                // Monitorea errores de API.
                System.err.println("Error al comunicarse con la API: " + e.getMessage());
                System.out.println("Asegúrese de tener conexión a internet y que su clave API sea correcta.");
            }
            System.out.println("\nPresione Enter para continuar...");
            scanner.nextLine(); // Enter para regresar.
        }
    }

    //Muestra el menú de opciones al usuario.

    private static void mostrarMenu() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Convertir moneda");
        System.out.println("2. Consultar tasas de cambio");
        System.out.println("3. Salir");
        System.out.print("Ingrese su opción aquí: ");
    }


    private static void convertirMoneda(Scanner scanner, ClienteAPI clienteAPI) throws IOException, InterruptedException {
        System.out.print("Para iniciar, ingrese el código de la moneda de origen: (ej. USD, ARS): ");
        String monedaBase = scanner.nextLine().toUpperCase();

        // Validar moneda de origen (monedaBase).
        if (!MONEDAS_SOPORTADAS.contains(monedaBase)) {
            System.out.println("Moneda de origen errónea. Por favor, elija entre: " + String.join(", ", MONEDAS_SOPORTADAS));
            return;
        }

        System.out.print("Ingrese el código de la moneda de destino (ej. EUR, CLP): ");
        String monedaObjetivo = scanner.nextLine().toUpperCase();

        // Validar moneda de destino (monedaObjetivo)
        if (!MONEDAS_SOPORTADAS.contains(monedaObjetivo)) {
            System.out.println("Moneda de destino errónea. Por favor, elija entre: " + String.join(", ", MONEDAS_SOPORTADAS));
            return;
        }

        System.out.print("Ingrese la cifra monetaria a convertir: ");
        double cantidad;
        try {
            cantidad = scanner.nextDouble(); // Lee la cantidad como un número.
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Cantidad inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpia el buffer.
            return;
        }

        // Obtiene las tasas de cambio de la API.
        TasaDeCambio tasas = clienteAPI.obtenerTasasDeCambio(monedaBase);

        // Verifica si la respuesta de la API fue exitosa.
        if (tasas != null && "success".equals(tasas.getResult())) {
            Map<String, Double> conversionRates = tasas.getConversionRates();
            if (conversionRates.containsKey(monedaObjetivo)) {
                double tasa = conversionRates.get(monedaObjetivo);
                double cantidadConvertida = cantidad * tasa;
                System.out.printf("\n%.2f %s equivale a %.2f %s%n", cantidad, monedaBase, cantidadConvertida, monedaObjetivo);
            } else {
                System.out.println("No se encontró la tasa de cambio para " + monedaObjetivo + " desde " + monedaBase + ".");
            }
        } else {
            System.out.println("No se pudieron obtener las tasas de cambio para " + monedaBase + ". Verifique el código de la moneda o su clave API.");
        }
    }

    private static void mostrarTasasDeCambio(Scanner scanner, ClienteAPI clienteAPI) throws IOException, InterruptedException {
        System.out.print("Ingrese el código de la moneda base para ver sus tasas (ej. USD, BRL): ");
        String monedaBase = scanner.nextLine().toUpperCase();

        // Verifica si la moneda base es compatible.
        if (!MONEDAS_SOPORTADAS.contains(monedaBase)) {
            System.out.println("Moneda base no soportada. Por favor, elija entre: " + String.join(", ", MONEDAS_SOPORTADAS));
            return;
        }

        // Obtiene las tasas de cambio de la API.
        TasaDeCambio tasas = clienteAPI.obtenerTasasDeCambio(monedaBase);

        // Verifica si la respuesta de la API fue exitosa.
        if (tasas != null && "success".equals(tasas.getResult())) {
            System.out.println("\nTasas de cambio para " + monedaBase + ":");
            // Imprime las tasas del mapa.
            tasas.getConversionRates().forEach((moneda, tasa) -> {
                // Opcional: Filtrar monedas soportadas.
                if (MONEDAS_SOPORTADAS.contains(moneda)) {
                    System.out.printf("%s: %.4f%n", moneda, tasa);
                }
            });
        } else {
            System.out.println("Tasas de cambio no disponible para " + monedaBase + ". Verifique el código de la moneda o su clave API.");
        }
    }
}
