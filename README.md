# CONVERSOR DE MONEDAS

Este proyecto es un **Conversor de Monedas** desarrollado en Java, utilizando la API de **ExchangeRate-API** para obtener tasas de cambio en tiempo real y cambiar divisas directamente en la consola.


## 🔑 ¿Cómo Obtener tu Clave API?

Para que el conversor funcione, necesitas una **clave API gratuita** de ExchangeRate-API. Sigue estos sencillos pasos:

1.   Visita https://www.exchangerate-api.com/.
2.   Haz clic en "Get Free API Key" o regístrate en la plataforma.
3.   Una vez registrado, tu clave API será visible en tu panel de control.
4.   Copia esta clave y pégala en el archivo `ClienteAPI.java` donde dice `"YOU_API_KEY"`.


## ⚙️ Instalación y Configuración

Configurar el conversor es **rápido y sencillo**:

1.  **Clona el repositorio** o descarga el proyecto.
2.  **Abre el proyecto en IntelliJ IDEA.**
3.  **Añade la librería Gson:**
    * En IntelliJ, ve a `File` > `Project Structure...` (`Ctrl+Alt+Shift+S`).
    * Selecciona `Proyect Settings` (o `Modules` > `Dependencies` si tu versión de IntelliJ lo presenta así).
    * Haz clic en el botón `+` y elige `Library...`
    * Busca `gson:2.10.1` (o la versión más reciente) y añádela.
4.  **Inserta tu clave API:**
    * Abre el archivo `src/com/alura/conversormonedas/service/ClienteAPI.java`.
    * Busca la línea `private static final String BASE_API_URL = "YOU_API_KEY";`
    * **Reemplaza `"YOU_API_KEY"` con tu clave API real.**
5.  **Ejecuta `Principal.java`:**
    * Abre el archivo `src/com/alura/conversormonedas/principal/Principal.java`.
    * Haz clic en el botón de `Run` (triángulo verde).


## 💡Usos

Una vez ejecutado, sigue las instrucciones en la consola para seleccionar la operación deseada. Podrás elegir entre convertir moneda, consultar tasas de cambio o salir del programa.


## 💻 Tecnologías Utilizadas

* **Java JDK 11+**
* **Gson 2.10.1+**
* **ExchangeRate-API**
* **IntelliJ IDEA Community Edition**


## 🪙 Challenge

El Challenge Conversor de Monedas es una excelente vía para el aprendizaje práctico y la aplicación de conocimientos que ofrece el programa **ONE - Oracle Next Education** y **Alura Latam**.

Conocimiento adquirido de los siguientes cursos:
* **Java: creando tu primera aplicación**
* **Java: aplicando la Orientación a Objetos**
* **Java: consumir API, escribir archivos y manejar errores**
* **Java: trabajar con listas y colecciones de datos**


## 🔹 Desarrollador del Proyecto

**Nayet Ancape**