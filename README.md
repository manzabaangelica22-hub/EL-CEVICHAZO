# EL-CEVICHAZO
GRUPO 3 POO PROYECTO

# Sistema de Picantería | EL CEVICHAZO |

Este sistema de gestión en consola permite administrar las operaciones diarias de una picantería, abarcando desde el registro de menús y clientes hasta la facturación y el control de pedidos.

---

## 🚀 Características del Sistema

* **Gestión de Menú (Platos):** Registro, consulta y administración de platos disponibles en el restaurante (Platos principales, entradas y bebidas).
* **Gestión de Clientes:** Registro de clientes por Cédula o RUC y control de datos asociados.
* **Gestión de Pedidos:** Creación de pedidos por cliente, adición de ítems con validación de cantidades y actualización secuencial de estados (*Pendiente -> En preparación -> Servido*).
* **Facturación y Cobro:** Emisión de comprobantes de venta aplicando el porcentaje de impuesto (IVA) correspondiente y cálculo automático de subtotales.
* **Consultas y Reportes:** Visualización de platos disponibles, pedidos activos y generación de reportes de cierre diario.

---



## 📂 Estructura del Código Fuente

El proyecto incluye las siguientes clases principales:

* `ElCevichazo.java`: Clase principal que contiene el método `main` y la interfaz de consola mediante menús interactivos.
* `ServicioRestaurante.java`: Controlador lógico que administra las colecciones de datos y reglas de negocio.
* `Pedido.java` y `DetallePedido.java`: Gestión de comandas y relación con los ítems solicitados.
* `Factura.java` e interfaz `Facturable`: Emisión de comprobantes y cálculo de totales.
* `Plato.java`, `PlatoPrincipal.java`, `Bebida.java`, `Entrada.java`: Jerarquía de clases para los productos del menú.
* `Cliente.java`: Entidad para la gestión de compradores.

