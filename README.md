# MiniCore MVC · Cálculo de comisiones

Spring Boot 3 + Thymeleaf + JPA (H2). Patrón MVC.

## Funcionalidad
Tres tablas relacionadas: **Vendedor**, **Venta**, **Regla**.
El usuario ingresa una fecha de inicio y una fecha fin, y el mini-core:

1. Filtra las ventas dentro del rango.
2. Suma las ventas de cada vendedor.
3. Busca la regla de mayor monto mínimo que el total alcance.
4. Calcula `comisión = total × porcentaje / 100`.

## Estructura MVC
| Capa | Ubicación |
|---|---|
| Modelo | `model/` (Vendedor, Venta, Regla, ComisionDTO) |
| Repositorios | `repository/` (Spring Data JPA) |
| Core / lógica | `service/ComisionService.java` |
| Controlador | `controller/ComisionController.java` |
| Vista | `resources/templates/index.html` |
| Datos semilla | `resources/data.sql` |

## Ejecutar local
```bash
./mvnw spring-boot:run   # o: mvn spring-boot:run
```
Abre http://localhost:8080. Consola de la base de datos: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:minicore`, usuario `sa`).

## Deploy en Render
1. Sube el proyecto a GitHub.
2. En Render: **New → Web Service** → elige el repo.
3. Language/Runtime: **Docker** (usa el `Dockerfile`). Plan: Free.
4. Deploy. La URL queda tipo `https://minicore-xxxx.onrender.com`.

## Ejemplo de prueba (01/06/2025 – 30/06/2025)
| Vendedor | Total | Regla | Comisión |
|---|---|---|---|
| Aquiles C | 1100 | 15% | 165.00 |
| Johny Melas | 1000 | 15% | 150.00 |
| Perico P | 750 | 6% | 45.00 |
| Zoila Baca | 300 | 0% | 0.00 |

## Links
- Deploy: https://minicore-gabriel.onrender.com

- Video: https://youtu.be/V6v9NTh0xrs
