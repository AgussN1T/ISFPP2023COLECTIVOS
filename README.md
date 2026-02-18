# ISFPP2023COLECTIVOS
# Sistema de Cálculo de Recorridos de Colectivos Urbanos

Aplicación de escritorio desarrollada en Java para el cálculo de recorridos óptimos en una red de colectivos urbanos.
El sistema permite consultar el camino más corto entre dos paradas, considerando horarios, frecuencias y la cantidad máxima de cambios de línea, brindando al usuario distintas alternativas de recorrido.
Además, incluye funcionalidades para la administración de líneas, paradas y tramos, modelando la red de transporte mediante grafos dirigidos.

Contexto Académico
Este proyecto fue desarrollado como parte de una instancia supervisada de formación y prácticas profesionales, correspondiente a la materia Programación Orientada a Objetos.
Se aplican principios de POO y patrones de diseño como MVC, DAO, Singleton y Observer.
El sistema fue realizado íntegramente como proyecto académico universitario.

Funcionalidades Principales
Cálculo de recorridos óptimos entre dos paradas.
Posibilidad de limitar la cantidad de cambios de línea.
Estimación de horarios de llegada según frecuencia y duración de tramos.
Visualización clara de los recorridos sugeridos.
Administración de líneas, paradas y tramos (ABM).
Validación de datos de entrada y mensajes de error amigables.
Persistencia de datos mediante el patrón DAO.

Tecnologías Utilizadas
Lenguaje de programación: Java SE
Interfaz gráfica: Java Swing
Base de datos: PostgreSQL
Acceso a datos: DAO
Modelado de grafos: JGraphT
Patrones de diseño: MVC, DAO, Singleton, Observer, Factory
IDE: Eclipse IDE

Requisitos para Ejecución
Java: Java SE 8 o superior
Eclipse IDE: versión 2022-03 o superior
Base de datos: PostgreSQL 12 o superior
Sistema operativo compatible con Java (Windows, Linux o macOS)


Estructura del Proyecto
El proyecto se organiza siguiendo el patrón MVC, separando:
Modelo: lógica de negocio y estructura de la red de colectivos.
Vista: interfaz gráfica desarrollada en Swing.
Controlador: gestión de eventos y comunicación entre modelo y vista.

Alcance y Limitaciones
El sistema no contempla tráfico en tiempo real ni retrasos imprevistos.
La red de colectivos debe estar previamente cargada.
La aplicación está orientada a entornos de escritorio y no cuenta con soporte móvil.
El cálculo de recorridos asume disponibilidad constante dentro del horario definido por cada línea.

Autor

Agustín Rivero
Proyecto académico universitario
