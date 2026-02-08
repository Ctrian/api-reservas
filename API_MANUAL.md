# ✈️ Manual de API - Sistema de Reservas

Este documento proporciona una guía completa para interactuar con la API REST del Sistema de Reservas.

## 📋 Información General

- **Base URL**: `http://localhost:8081`
- **Swagger UI**: `http://localhost:8081/q/swagger-ui` (Documentación interactiva)
- **Formato de Peticiones**: JSON (`application/json`)
- **Base de Datos**: PostgreSQL

## 📦 Entidades y Endpoints

### 1. Aviones 🛫

Representa las aeronaves disponibles en el sistema.

#### **Esquema JSON**
```json
{
  "id": 1, 
  "modelo": "Boeing 737",
  "capacidad": 180,
  "aerolinea": "Avianca",
  "clase": "Económica",
  "espacioEquipaje": "23kg"
}
```

#### **Endpoints Disponibles**

| Método | Endpoint | Descripción | Body Requerido |
| :--- | :--- | :--- | :--- |
| **GET** | `/aviones` | Listar todos los aviones | No |
| **GET** | `/aviones/{id}` | Obtener un avión por ID | No |
| **POST** | `/aviones` | Crear un nuevo avión | Sí |
| **PUT** | `/aviones/{id}` | Actualizar un avión completo | Sí |
| **PATCH** | `/aviones/{id}` | Actualización parcial | Sí |
| **DELETE** | `/aviones/{id}` | Eliminar un avión | No |

#### **Ejemplo de Creación (POST)**
**Endpoint**: `/aviones`
**Body**:
```json
{
  "modelo": "Airbus A320",
  "capacidad": 150,
  "aerolinea": "LATAM",
  "clase": "Business",
  "espacioEquipaje": "32kg"
}
```

---

### 2. Pasajeros 👤

Representa a los clientes que realizan reservas.

#### **Esquema JSON**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "cedula": "0102345678",
  "telefono": "+593987654321",
  "correo": "juan.perez@email.com"
}
```

#### **Endpoints Disponibles**

| Método | Endpoint | Descripción | Body Requerido |
| :--- | :--- | :--- | :--- |
| **GET** | `/pasajeros` | Listar todos los pasajeros | No |
| **GET** | `/pasajeros/{id}` | Obtener un pasajero por ID | No |
| **POST** | `/pasajeros` | Registrar un nuevo pasajero | Sí |
| **PUT** | `/pasajeros/{id}` | Actualizar datos de un pasajero | Sí |
| **DELETE** | `/pasajeros/{id}` | Eliminar un pasajero | No |

#### **Ejemplo de Creación (POST)**
**Endpoint**: `/pasajeros`
**Body**:
```json
{
  "nombre": "María",
  "apellido": "Gómez",
  "correo": "maria.gomez@email.com"
}
```

---

### 3. Reservas 🎫

Gestiona las reservas de vuelos, vinculando un Pasajero con un Avión.

#### **Esquema JSON**
```json
{
  "id": 1,
  "fecha": "2026-03-15",
  "hora": "08:45",
  "origen": "Quito",
  "destino": "Guayaquil",
  "precio": 120.50,
  "estado": "CONFIRMADA",
  "asiento": 12,
  "idAvion": 1,
  "idPasajero": 1
}
```

#### **Endpoints Disponibles**

| Método | Endpoint | Descripción | Body Requerido |
| :--- | :--- | :--- | :--- |
| **GET** | `/reservas` | Listar todas las reservas | No |
| **GET** | `/reservas/{id}` | Obtener una reserva por ID | No |
| **POST** | `/reservas` | Crear una nueva reserva | Sí |
| **PUT** | `/reservas/{id}` | Actualizar una reserva | Sí |
| **DELETE** | `/reservas/{id}` | Cancelar/Eliminar una reserva | No |

#### **Ejemplo de Creación (POST)**
**Endpoint**: `/reservas`
**Body**:
```json
{
  "fecha": "2026-03-15",
  "hora": "14:30",
  "origen": "Guayaquil",
  "destino": "Miami",
  "precio": 450.00,
  "estado": "PENDIENTE",
  "asiento": 5,
  "idAvion": 2,      
  "idPasajero": 3    
}
```
> **Nota**: `idAvion` e `idPasajero` deben corresponder a IDs existentes en la base de datos.
> **Nota**: Los formatos de fecha (`fecha`) deben ser `YYYY-MM-DD` y hora (`hora`) `HH:mm`.

---

## 🚦 Códigos de Estado HTTP Comunes

- **200 OK**: Petición exitosa.
- **201 Created**: Recurso creado exitosamente (respuesta a POST).
- **204 No Content**: Petición exitosa sin contenido en la respuesta (común en DELETE/PUT).
- **400 Bad Request**: La petición tiene un formato incorrecto o faltan datos.
- **404 Not Found**: El recurso solicitado (ID) no existe.
- **500 Internal Server Error**: Error en el servidor.
