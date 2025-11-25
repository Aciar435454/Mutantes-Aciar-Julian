# 🧬 X-MEN DNA ANALYZER - PROYECTO INTEGRADOR

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
