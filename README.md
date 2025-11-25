# 🧬 X-MEN DNA ANALYZER - PROYECTO INTEGRADOR

* **Desarrollador:** Julián Aciar
* **Comisión:** 3K09
* **Institución:** Universidad Tecnológica Nacional

## 📝 Descripción del Proyecto

Este proyecto fue desarrollado bajo encargo de **Magneto** para crear un sistema eficiente que determine si un humano es mutante, basándose en el análisis de su secuencia de ADN.

Una persona es considerada mutante si se encuentra **más de una** secuencia de cuatro letras iguales (A, T, C, o G) en su matriz de ADN, buscadas de forma:

* **Horizontal**.
* **Vertical**.
* **Oblicua** (Diagonales).

El proyecto cumple con los tres niveles de desafío propuestos, incluyendo la implementación de una API REST, persistencia de datos con H2, y un servicio de estadísticas.

---

## 🛠️ Stack Tecnológico

| Categoría | Tecnología |
| :--- | :--- |
| **Lenguaje** | Java |
| **Framework** | Spring Boot |
| **Persistencia** | Spring Data JPA |
| **Base de Datos** | H2 Database (Embedida) |
| **Build Tool** | **Gradle** |
| **Cloud** | Render |

---

## ⚙️ Requisitos Previos

Para ejecutar la aplicación localmente, se requiere tener instalado:

* **Java Development Kit (JDK):** Versión 17 o superior.
* **Gradle:** Para la gestión de dependencias (se recomienda usar el wrapper `./gradlew`).

---

## 🚀 Instrucciones de Ejecución Local

Sigue estos pasos para levantar el proyecto en tu máquina:

1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/Aciar435454/Mutantes-Aciar-Julian.git](https://github.com/Aciar435454/Mutantes-Aciar-Julian.git)
    cd Mutantes-Aciar-Julian
    ```

2.  **Compilar y Empaquetar:**
    Utiliza el wrapper de **Gradle** para limpiar el proyecto y construir el JAR.
    ```bash
    ./gradlew clean build
    ```

3.  **Ejecutar la Aplicación Spring Boot:**
    La aplicación se iniciará en el puerto 8080 (por defecto).
    ```bash
    ./gradlew bootRun
    # Alternativa: java -jar build/libs/mutantes-aciar-julian-0.0.1-SNAPSHOT.jar
    ```

---

## 🌐 Endpoints de la API REST

La API expone los siguientes servicios REST:

### 1. POST /mutant/ (Detección de Mutantes)

Este servicio verifica si una secuencia de ADN corresponde a un mutante.

* **Método:** `POST`.
* **Ruta:** `/mutant/`.

#### **Ejemplo de Petición:**
```json
{
    "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```
#### **Respuestas HTTP:**

* **200 OK:** Si la secuencia de ADN pertenece a un Mutante.
* **403 Forbidden:** Si la secuencia de ADN pertenece a un Humano (No Mutante).

### 2. GET /stats (Estadísticas de Verificación)

Este servicio expone las estadísticas acumuladas de las verificaciones de ADN persistidas en la base de datos.

* **Método:** `GET`.
* **Ruta:** `/stats/`.
  
#### **Respuesta HTTP: 200 OK con un cuerpo JSON:**
```json
{
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
}
```
* **Campos de Respuesta:** Incluye el conteo de ADN mutantes `count_mutant_dna`, el conteo de ADN humanos `count_human_dna`, y el ratio entre ellos.
---

## ☁️ Despliegue en Cloud (Nivel 2)

**Requisito de Entrega:** URL de la API.

El servicio ha sido hosteado en la plataforma de *cloud computing libre* **Render**.

* **URL de la API Desplegada:**
    * **`https://mutantes-aciar-julian.onrender.com`**

---

## ✅ Consideraciones del Nivel 3

* **Persistencia de Datos:** Se utiliza **H2** como base de datos embebida para almacenar solo **un registro por secuencia de ADN** verificado, evitando duplicados.
* **Optimización del Algoritmo:** La función `isMutant` está optimizada y utiliza **Terminación Anticipada**, deteniendo la búsqueda y devolviendo `true` tan pronto como se encuentran las dos secuencias requeridas.
* **Calidad de Código:** El proyecto incluye **Tests Unitarios** y de **Integración** con un **Code Coverage superior al 80%**.

---

## 🖼️ Documentos de Entrega y Diagramas

**Requisito de Entrega:** El código fuente, las instrucciones, la URL de la API y el Diagrama de Secuencia en formato PDF.

* **Diagrama de Secuencia:** Se anexa el documento en formato **PDF** con el diagrama UML de los flujos de la API (`/mutant/` y `/stats`).

