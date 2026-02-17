# Taller de Sockets en Java

## Objetivo
Aprender a implementar aplicaciones cliente-servidor con sockets en Java a partir de ejemplos base y modificarlos para crear servidores con distintas funcionalidades (eco, operaciones matemáticas y servidor HTTP).

## Estructura del proyecto
src/main/java/arsw/demo/
- ejercicio3/           # Ejercicios de sockets TCP básicos
  - EchoServer.java     # Servidor eco (ejemplo base)
  - EchoClient.java     # Cliente para probar
  - SquareServer.java   # Servidor que devuelve cuadrados (ej. 5 -> 25)
  - FunctionServer.java # Servidor de funciones trigonométricas (sin/cos/tan)
- ejercicio4/           # Ejercicios de servidor web
  - HttpServer.java     # Servidor HTTP básico (una solicitud)
  - MultiHttpServer.java# Servidor HTTP mejorado (múltiples solicitudes seguidas)

---

## Ejercicio 3 — Sockets TCP

### EchoServer y EchoClient (base)
- Propósito: entender la creación de ServerSocket y Socket, lectura/escritura por streams.
- Comportamiento: el servidor escucha en el puerto 35000; el cliente envía líneas y el servidor responde con `Respuesta: <mensaje>`.

Por qué sirve: muestra aceptación de conexiones, lectura de inputLine y escritura de respuesta.

### SquareServer (ejercicio 4.3.1)
- Requisito: recibir un número y devolver su cuadrado.
- Cambios clave respecto al EchoServer:
  - Parsear la entrada con `Integer.parseInt(...)`.
  - Responder con `Respuesta: <número * número>`.

Ejemplo: enviar `5` → respuesta `Respuesta: 25`.

### FunctionServer (ejercicio 4.3.2)
- Requisito: mantener una función activa (por defecto `cos`) y aplicar `sin`, `cos` o `tan` al número recibido. Comandos: `fun:sin`, `fun:cos`, `fun:tan`.
- Cambios clave:
  - Mantener estado `operacion` para la sesión.
  - Detectar mensajes que comienzan con `fun:` para cambiar la operación.
  - Usar `Math.sin`, `Math.cos`, `Math.tan` para el cálculo.

Ejemplo:
- Enviar `0` → con `cos` por defecto responde `1.0`.
- Enviar `fun:sin` → cambia a seno.
- Enviar `0` → responde `0.0`.

---

## Ejercicio 4 — Servidor Web

### HttpServer (ejercicio 4.4)
- Requisito: servir una respuesta HTTP al conectar desde un navegador.
- Cambios importantes:
  - Enviar cabeceras HTTP correctas (`Content-Type`, `Content-Length`) y usar `OutputStream` para escribir la respuesta.
  - Evitar concatenar variables nulas (por ejemplo `inputLine` cuando es null).

Nota: muchos navegadores requieren cabeceras válidas para mostrar contenido correctamente.

### MultiHttpServer (ejercicio 4.5.1)
- Requisito: soportar múltiples solicitudes secuenciales (no necesariamente concurrentes).
- Cambios clave:
  - Mantener un `while (true)` para aceptar nuevas conexiones.
  - No cerrar el `ServerSocket` tras la primera petición.
  - Para simplificar se puede devolver un HTML fijo o implementar lectura de archivos solicitados.

---

## Cómo probar (resumen rápido)

1. Elegir el servidor (EchoServer, SquareServer, FunctionServer, HttpServer o MultiHttpServer) y ejecutarlo.
2. Para ejercicios TCP (puerto 35000):
   - Ejecutar EchoClient y enviar mensajes según el servidor esperado.
   - SquareServer: enviar números (ej. `5`) → `Respuesta: 25`.
   - FunctionServer: enviar comandos `fun:sin` y números.
3. Para HTTP:
   - Abrir el navegador en `http://localhost:35000`.
   - HttpServer atenderá una solicitud; MultiHttpServer seguirá activo para más peticiones.

---

