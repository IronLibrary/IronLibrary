# 📚 IronLibrary

IronLibrary es un sistema de gestión de biblioteca desarrollado en Java con arquitectura MVC. Permite realizar operaciones básicas como agregar libros, prestar ejemplares a estudiantes y consultar información sobre autores, estudiantes y préstamos. Toda la persistencia se maneja mediante archivos CSV.

## 🚀 Características

- 📖 Gestión de libros (crear, buscar por título, buscar por autor, listar todos).
- 🧑‍🏫 Gestión de estudiantes.
- 🔄 Préstamo de libros a estudiantes.
- 📂 Persistencia de datos mediante archivos CSV.
- ✅ Validación de formato y datos en los archivos de entrada.
- 🧱 Arquitectura basada en MVC.
- 💡 Menú interactivo por consola (con submenús).

## 🗂️ Estructura del Proyecto

```
src/
 ├── main/
 │   ├── controller/
 │   │   ├── MenuController.java
 │   │   └── IssueController.java
 │   ├── model/
 │   │   ├── Book.java
 │   │   ├── Author.java
 │   │   ├── Student.java
 │   │   └── Issue.java
 │   ├── util/
 │   │   ├── CsvLoader.java
 │   │   ├── CsvWriterUtil.java
 │   │   ├── CsvReaderUtil.java
 │   │   └── CsvValidator.java
 │   ├── view/
 │   │   └── Menu.java
 │   └── App.java
 └── data/
     ├── book.csv
     ├── author.csv
     ├── student.csv
     └── issue.csv
```

## 📥 Instalación y Ejecución

### 🔧 Requisitos

- Java 17+
- Maven 3.8+
- Un IDE compatible (IntelliJ IDEA, Eclipse, etc.)
- Archivos CSV de datos iniciales en la carpeta `data/`
- Conexión a internet para descargar dependencias de Maven
- Consola de comandos para ejecutar el programa

## 📌 Formatos esperados en CSV

| Campo       | Formato esperado                         |
|-------------|------------------------------------------|
| ISBN        | Solo números o guiones (ej: `978-123456`)|
| USN         | Alfanumérico (ej: `ST1234`)              |
| Email       | Formato válido de correo electrónico     |
| Fechas      | `YYYY-MM-DD`                             |

Los archivos `.csv` deben contener una línea de encabezado.

## 🧪 Validaciones

El sistema valida los siguientes elementos:

- ISBN único por libro.
- Un solo autor por libro.
- Email válido para autores y estudiantes.
- No duplicación de estudiantes por USN.
- No se permite emitir un libro si no hay copias disponibles.

## 🛠️ Funcionalidades próximas (ideas)

- Implementación con base de datos real (MySQL o PostgreSQL).
- Interfaz gráfica (Swing o JavaFX).
- Exportación de reportes PDF o CSV.

## 👨‍💻 Autores

- [Salva Marchese](https://github.com/Salva985)
- [Rafaela Ridolphi](https://github.com/Ridolphi)
- [Felix Rodriguez](https://github.com/FelixRodriguezG)
