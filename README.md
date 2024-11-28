# Android Movie App

## Descripción

Esta aplicación Android permite consultar información sobre películas utilizando la API de TMDb (The Movie Database). La app está construida siguiendo la arquitectura **MVVM** y principios **SOLID** para garantizar un código limpio, escalable y mantenible.

## Características

- **Retrofit**: Realiza solicitudes HTTP para obtener datos de la API de TMDb.
- **SQLite**: Almacena en caché información sobre las películas para su consulta sin conexión.
- **SharedPreferences**: Guarda configuraciones del usuario, como el idioma preferido.
- **Arquitectura MVVM**: Mantiene la lógica de negocio separada de la UI, mejorando la escalabilidad y testabilidad.
- **Principios SOLID**: El código sigue los principios SOLID para asegurar un diseño orientado a objetos claro y flexible.

## Tecnologías

- **Kotlin**: Lenguaje de programación utilizado para la aplicación.
- **Retrofit**: Librería para hacer solicitudes HTTP de manera sencilla.
- **SQLite**: Base de datos local para almacenar datos de las películas.
- **SharedPreferences**: Almacena configuraciones de usuario.
- **Jetpack Compose**: Librería para la construcción de UI declarativa.
- **MVVM**: Arquitectura utilizada para separar la lógica de la UI.
