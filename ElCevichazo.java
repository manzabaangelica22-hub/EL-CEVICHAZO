
package elcevichazo;
import java.util.Scanner;


public class ElCevichazo {
    private static Scanner sc = new Scanner(System.in);
    private static ServicioRestaurante servicio = new ServicioRestaurante();
    
    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenuPrincipal();
            opcion = leerEnteroValido("Seleccione una opcion: ");
            switch (opcion){
                case 1: menuGestionMenu(); break;
                case 2: menuGestionClientes(); break;
                case 3: menuGestionPedidos(); break;
                case 4: menuFacturacion(); break;
                case 5: menuConsultasReportes(); break;
                case 6: System.out.println("\nGracias por usar el sistema |El Cevichazo|!\n"); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 6);
    }
    
    private static void mostrarMenuPrincipal(){
        System.out.println("\n=======================================================");
        System.out.println("        SISTEMA PICANTERIA |EL CEVICHAZO| - MAIN MENU     ");
        System.out.println("=======================================================");
        System.out.println("1. Gestion de Menu (Platos)");
        System.out.println("2. Gestion de Clientes");
        System.out.println("3. Gestion de Pedidos");
        System.out.println("4. Facturacion y Cobro");
        System.out.println("5. Consultas y Reportes");
        System.out.println("6. Salir del Sistema");
        System.out.println("=======================================================");
    }
    
   private static void menuGestionMenu() {
        System.out.println("\n--- GESTION DE MENU ---");
        System.out.println("1. Registrar nuevo plato");
        System.out.println("2. Consultar menu completo");
        int op = leerEnteroValido("Opcion: ");

        if (op == 1) {
            System.out.print("Ingrese codigo unico del plato: ");
            String cod = sc.nextLine().trim();
            if (servicio.buscarPlatoPorCodigo(cod) != null) {
                System.out.println("Error: Codigo duplicado.");
                return;
            }
            System.out.print("Nombre del plato: ");
            String nombre = sc.nextLine().trim();
            double precio = leerDoublePositivo("Precio base ($): ");

            System.out.println("Seleccione Categoria: 1. Entrada | 2. Plato Principal | 3. Bebida | 4. Extra");
            int catOp = leerEnteroValido("Categoria: ");
            Plato nuevoPlato = null;
            switch (catOp) {
                case 1: nuevoPlato = new Entrada(cod, nombre, precio, true); break;
                case 2: nuevoPlato = new PlatoPrincipal(cod, nombre, precio, true); break;
                case 3: nuevoPlato = new Bebida(cod, nombre, precio, true); break;
                default: System.out.println("Categoria invalida."); return;
            }

            if (servicio.registrarPlato(nuevoPlato)) System.out.println("Plato registrado!");
        } else if (op == 2) {
            for (Plato p : servicio.obtenerMenuCompleto()) System.out.println(p);
        }
    }
    
    private static void menuGestionClientes() {
        System.out.println("\n--- GESTION DE CLIENTES ---");
        System.out.println("1. Registrar nuevo cliente");
        System.out.println("2. Listar clientes");
        int op = leerEnteroValido("Opcion: ");

        if (op == 1) {
            System.out.print("Ingrese Cedula / RUC: ");
            String id = sc.nextLine().trim();
            if (id.isEmpty() || servicio.buscarClientePorIdentificacion(id) != null) {
                System.out.println("Identificacion vacia o ya registrada.");
                return;
            }
            System.out.print("Nombre completo: ");
            String nom = sc.nextLine().trim();
            if (servicio.registrarCliente(new Cliente(id, nom))) System.out.println("Cliente registrado!");
        } else if (op == 2) {
            for (Cliente c : servicio.getClientes()) System.out.println(c);
        }
    }
    
    private static void menuGestionPedidos() {
        System.out.println("\n--- GESTION DE PEDIDOS ---");
        System.out.println("1. Crear nuevo pedido");
        System.out.println("2. Agregar plato a un pedido");
        System.out.println("3. Avanzar estado del pedido (Pendiente -> En preparacion -> Servido)");
        int op = leerEnteroValido("Opcion: ");

        if (op == 1) {
            System.out.print("Cedula/RUC del cliente (Presione ENTER para Consumidor Final): ");
            String id = sc.nextLine().trim();
            Cliente cliente = id.isEmpty() ? servicio.buscarClientePorIdentificacion("9999999999") : servicio.buscarClientePorIdentificacion(id);
            if (cliente == null) {
                System.out.print("Cliente no registrado. Nombre: ");
                String nom = sc.nextLine().trim();
                cliente = new Cliente(id, nom);
                servicio.registrarCliente(cliente);
            }
            Pedido p = servicio.crearPedido(cliente);
            System.out.println("Pedido #" + p.getNumeroPedido() + " creado!");
        } else if (op == 2) {
            int numP = leerEnteroValido("Numero de pedido: ");
            Pedido p = servicio.buscarPedidoPorNumero(numP);
            if (p == null || !p.getEstado().equals("Pendiente")) {
                System.out.println("Pedido no encontrado o ya no esta en estado Pendiente.");
                return;
            }
            System.out.print("Codigo del plato: ");
            String cod = sc.nextLine().trim();
            Plato plato = servicio.buscarPlatoPorCodigo(cod);
            if (plato == null || !plato.isDisponible()) {
                System.out.println("Plato no disponible.");
                return;
            }
            int cantidad = leerEnteroPositivo("Cantidad: ");
            p.agregarDetalle(plato, cantidad);
                System.out.println("Plato agregado!");
        } else if (op == 3) {
            int numP = leerEnteroValido("Numero de pedido: ");
            Pedido p = servicio.buscarPedidoPorNumero(numP);
            if (p != null) {
                String previo = p.getEstado();
                if (p.avanzarEstado()) 
                    System.out.println("Estado actualizado: " + previo + " -> " + p.getEstado());
                else 
                    System.out.println("No se puede avanzar mas el estado.");
            }
        }
    }

    private static void menuFacturacion() {
        System.out.println("\n--- FACTURACION ---");
        int numP = leerEnteroValido("Numero de pedido a facturar: ");
        Pedido p = servicio.buscarPedidoPorNumero(numP);
        if (p == null || p.getDetalles().isEmpty()) {
            System.out.println("Pedido invalido o sin items.");
            return;
        }
        
        Factura f = servicio.facturarPedido(p, 0.15);
        if (f != null) f.generarComprobante();
        else 
            System.out.println("Error al emitir factura.");
    }

    private static void menuConsultasReportes() {
        System.out.println("\n--- CONSULTAS Y REPORTES ---");
        System.out.println("1. Platos disponibles");
        System.out.println("2. Pedidos activos");
        System.out.println("3. Total vendido (Cierre diario)");
        int op = leerEnteroValido("Opcion: ");

        if (op == 1) for (Plato p : servicio.obtenerPlatosDisponibles()) System.out.println(p);
        else if (op == 2) for (Pedido p : servicio.obtenerPedidosActivos()) System.out.println(p);
        else if (op == 3) servicio.generarReporteCierreDiario();
    }

    private static int leerEnteroValido(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        int v;
        do {
            v = leerEnteroValido(mensaje);
            if (v <= 0) System.out.println("Debe ser mayor a 0.");
        } while (v <= 0);
        return v;
    }

    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double v = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (v >= 0) return v;
                System.out.println("No puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un decimal valido.");
            }
        }
    }
    
}
