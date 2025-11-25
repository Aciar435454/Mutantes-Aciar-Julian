# 🧬 X-MEN DNA ANALYZER - DETECTOR DE MUTANTES

**Proyecto Integrador para MercadoLibre**

## 📜 Descripción del Proyecto

[cite_start]Este proyecto fue desarrollado por encargo de **Magneto** [cite: 2] [cite_start]para crear un sistema eficiente que determine si un humano es mutante [cite: 3] [cite_start]basándose en el análisis de su secuencia de ADN[cite: 3].

[cite_start]Una persona es considerada mutante si su matriz de ADN contiene **más de una** secuencia de cuatro letras iguales [cite: 44] encontradas de forma:
* [cite_start]Horizontal[cite: 43].
* [cite_start]Vertical[cite: 43].
* [cite_start]Oblicua (Diagonal)[cite: 43].

El proyecto cumple con los tres niveles de desafío propuestos, incluyendo la implementación de una API REST, persistencia de datos y un servicio de estadísticas.

## 🛠️ Stack Tecnológico

* [cite_start]**Lenguaje:** Java [cite: 113]
* [cite_start]**Framework:** Spring Boot [cite: 20, 48]
* [cite_start]**Base de Datos:** H2 Database (Embedida) [cite: 61]
* [cite_start]**Persistencia:** Spring Data JPA [cite: 115]
* **Build Tool:** Maven

## 💻 Requisitos Previos

Para ejecutar la aplicación localmente, se requiere:

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Maven:** Para la gestión de dependencias y compilación.

## 🚀 Instrucciones de Ejecución Local

Sigue estos pasos para levantar el proyecto en tu máquina:

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/Aciar435454/Mutantes-Aciar-Julian.git](https://github.com/Aciar435454/Mutantes-Aciar-Julian.git)
    cd Mutantes-Aciar-Julian
    ```

2.  **Compilar y Empaquetar:**
    ```bash
    ./mvnw clean install
    ```

3.  **Ejecutar la Aplicación Spring Boot:**
    ```bash
    java -jar target/mutantes-aciar-julian-0.0.1-SNAPSHOT.jar
    # El puerto por defecto es 8080.
    ```
    La aplicación estará disponible en `http://localhost:8080`.

## 🌐 Endpoints de la API REST

La API expone los siguientes servicios:

### 1. POST /mutant/ - Verificación de Mutante

Permite enviar una secuencia de ADN para su verificación.

* [cite_start]**Método:** `POST` [cite: 51]
* [cite_start]**Ruta:** `/mutant/` [cite: 50]
* **Cuerpo de la Petición (JSON):**
    ```json
    {
        "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    }
    ```
    [cite_start](Ejemplo tomado de las consignas [cite: 25])

* **Respuestas HTTP:**
    * [cite_start]**200 OK:** Si la secuencia de ADN pertenece a un **Mutante**[cite: 54].
    * [cite_start]**403 Forbidden:** Si la secuencia de ADN pertenece a un **Humano** (No Mutante)[cite: 55].

### 2. GET /stats - Estadísticas de Verificación

Devuelve las estadísticas acumuladas de todas las verificaciones de ADN.

* [cite_start]**Método:** `GET` [cite: 65]
* [cite_start]**Ruta:** `/stats` [cite: 64]
* **Respuesta HTTP:** **200 OK** con un cuerpo JSON:
    ```json
    {
        "count_mutant_dna": 40,
        "count_human_dna": 100,
        "ratio": 0.4
    }
    ```
    [cite_start](Ejemplo tomado de las consignas [cite: 30])
    [cite_start]**Nota:** El ratio se calcula como `count_mutant_dna / count_human_dna`.

## ☁️ Despliegue en Cloud (Nivel 2)

[cite_start]El servicio ha sido hosteado en la plataforma de *cloud computing libre* **Render**.

* **URL de la API Desplegada:**
    * [cite_start]**[PENDIENTE: COLOCAR AQUÍ LA URL DE TU API EN RENDER]** 

## 📋 Consideraciones Adicionales (Nivel 3)

* [cite_start]**Persistencia de Datos:** Se utiliza **H2** como base de datos embebida [cite: 61] [cite_start]para almacenar un único registro por cada secuencia de ADN verificada[cite: 63].
* [cite_start]**Optimización del Algoritmo:** La detección de mutantes implementa la **Terminación Anticipada**[cite: 98], deteniendo la búsqueda y devolviendo `true` tan pronto como se encuentran las dos secuencias requeridas.
* [cite_start]**Tests y Calidad de Código:** El proyecto incluye **Tests Unitarios** y de **Integración** [cite: 128, 129] [cite_start]con un **Code Coverage superior al 80%**.

## 🖼️ Documentos Adicionales

* [cite_start]**Diagrama de Secuencia:** Se anexa el documento en formato PDF con el diagrama UML de los flujos de la API (para `/mutant/` y `/stats`)[cite: 36].

---

¿Necesitas ayuda para completar la sección de la URL de Render o prefieres que revisemos alguna otra parte de la documentación?
