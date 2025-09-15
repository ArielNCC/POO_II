# Documentación del Proyecto GestionInventario

## Clases Principales

### 1. Producto
- **Atributos:**
  - `codigo`: int
  - `nombre`: String
  - `precio`: double
  - `stock`: int
- **Métodos:**
  - `getCodigo()`, `setCodigo(int)`
  - `getNombre()`, `setNombre(String)`
  - `getPrecio()`, `setPrecio(double)`
  - `getStock()`, `setStock(int)`
  - `toString()`

### 2. Inventario
- **Atributos:**
  - `productos`: List<Producto>
- **Métodos:**
  - `agregarProducto(Producto)`
  - `eliminarProducto(int codigo)`
  - `buscarProducto(int codigo)`
  - `listarProductos()`

### 3. MenuPrincipal
- **Métodos:**
  - `mostrarMenu()`
  - `main(String[] args)`

## Diagrama UML (simplificado)

@startuml
class Producto {
  - codigo: int
  - nombre: String
  - precio: double
  - stock: int
  + getCodigo()
  + setCodigo(int)
  + getNombre()
  + setNombre(String)
  + getPrecio()
  + setPrecio(double)
  + getStock()
  + setStock(int)
  + toString()
}

class Inventario {
  - productos: List<Producto>
  + agregarProducto(Producto)
  + eliminarProducto(int)
  + buscarProducto(int)
  + listarProductos()
}

class MenuPrincipal {
  + mostrarMenu()
  + main(String[] args)
}

Inventario "1" -- "*" Producto : contiene
MenuPrincipal ..> Inventario : usa
@enduml

Puedes visualizar el diagrama UML usando PlantUML.
