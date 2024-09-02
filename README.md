<br />
<div align="center">
<h3 align="center">FRANQUICIAS</h3>
  <p align="center">
    API para administrar franquicias junto con sus sucursales y productos. 
  </p>
</div>

<!-- GETTING STARTED -->
## Guía de inicio

Para poner en funcionamiento una copia local del proyecto, siga estos pasos.

### Prerequisitos

* JDK 11 [https://jdk.java.net/java-se-ri/11](https://jdk.java.net/java-se-ri/11)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Herramientas recomendadas
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Instalación

1. Clone el repositorio
2. Cree una base de datos in MySQL llamada franchise
4. Actualice la configuración de la conexión a base de datos 
   ```yml
   # src/main/resources/application.yml   
   spring:
      r2dbc:
          url: ${DATABASE_URL:r2dbc:mysql://localhost/franchise}
          username: ${DB_USERNAME:root}
          password: ${DB_PASSWORD:root}
   ```

<!-- USAGE -->
## Uso

1. Oprima click derecho in la clase PowerUpApplication y seleccione Run
2. Importe en Postman la colección FranchisePostman.postman_collection.json y pruebe los diferentes servicios
