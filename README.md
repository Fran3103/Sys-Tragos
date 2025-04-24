# 📦 SYS TomateAlgo - Backend

**SYS TomateAlgo** es un sistema de gestión para barras de eventos, desarrollado para automatizar la planificación, control de insumos y organización de eventos en el rubro de coctelería y servicios móviles.  
Este repositorio corresponde al **backend** desarrollado en **Java + Spring Boot**.

---

## 🚀 Funcionalidades principales

- 📋 Gestión de productos (bebidas, insumos, cristalería, mobiliario, etc.)
- 🍸 Gestión de cócteles con sus ingredientes
- 🧾 Creación de cartas (servicios) y asignación de cócteles
- 📅 Administración de eventos y clientes
- ⚙️ Generación automática de pedidos a partir del servicio seleccionado y la cantidad de invitados
- 📡 API REST consumible desde frontend React

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

## 🧱 Estructura de entidades

Producto <|-- Cocktail Cocktail <|-- Service Event --> Client Event --> Service Event --> Barra Event --> Pedido Pedido --> PedidoItem PedidoItem --> Producto

---

## ⚙️ Cómo correrlo localmente

### ✅ Requisitos

- Java 17
- Maven
- MySQL instalado y corriendo

### 🧪 Pasos

1. Clonar el repositorio:
:
``bash
  git clone https://github.com/tuusuario/sys-tomatealgo-backend.git 

2. Crear la base de datos en MySQL:
- CREATE DATABASE TomateAlgoDB
  
3. Configurar application.properties con tus credenciales:

spring.datasource.url=jdbc:mysql://localhost:3306/TomateAlgoDB

spring.datasource.username=root

spring.datasource.password=tu_contraseña

4. Ejecutar con:
   ./mvnw spring-boot:run
El backend estará disponible en: http://localhost:3000

📫 Endpoints principales
Método | Endpoint | Descripción
GET | /products | Listar productos
POST | /cocktails | Crear cóctel con ingredientes
POST | /events | Crear evento
POST | /services/{id}/cocktails/{id} | Asociar cóctel a servicio


👨‍💻 Autor
Franco Aguirre -
Desarrollador Web Full Stack



