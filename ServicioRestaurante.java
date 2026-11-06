
package elcevichazo;
import java.util.ArrayList;

public class ServicioRestaurante {
    private ArrayList<Plato> catalogoMenu;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Factura> facturas;
    private int secuencialPedido;

    public ServicioRestaurante(){
        this.catalogoMenu = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.secuencialPedido = 1;
        cargarDatosIniciales();
    }
    
    private void cargarDatosIniciales(){
        catalogoMenu.add(new Entrada("E01", "Chifle y Canguil", 0.75, true));
        catalogoMenu.add(new Entrada("E02", "Porcion de Patacones", 1.50, true));
        catalogoMenu.add(new Entrada("E03","Porcion de Arroz", 0.50, true));
        catalogoMenu.add(new PlatoPrincipal("PP01","Ceviche Mixto", 3.50, true));
        catalogoMenu.add(new PlatoPrincipal("PP02","Ceviche de Camaron", 3.00, true));
        catalogoMenu.add(new PlatoPrincipal("PP03","Encebollado de Albacora", 2.50, true));
        catalogoMenu.add(new Bebida("B01","Coca-Cola 500ml", 0.50, true));
        catalogoMenu.add(new Bebida("B02","Jugo Naranja", 0.75, true));
        
        clientes.add(new Cliente("9999999999","Consumidor Final"));
    }
    
    public boolean registrarPlato(Plato plato){
        for (Plato p: catalogoMenu){
            if (p.getCodigo().equalsIgnoreCase(plato.getCodigo())) return false;
        }
        catalogoMenu.add(plato);
        return true;
    }
    
    public Plato buscarPlatoPorCodigo(String codigo){
        for (Plato p: catalogoMenu){
            if (p.getCodigo().equalsIgnoreCase(codigo)) return p;
        }
        return null;
    }
    
    public ArrayList<Plato> obtenerMenuCompleto(){
        return catalogoMenu;
    }
    
    public ArrayList<Plato> obtenerPlatosDisponibles(){
        ArrayList<Plato> disp = new ArrayList();
        for (Plato p: catalogoMenu){
            if (p.isDisponible()) disp.add(p);
        }
        return disp;
    }
    
    public boolean registrarCliente(Cliente cliente){
        for (Cliente c: clientes){
            if (c.getId().equalsIgnoreCase(cliente.getId()));
        }
        clientes.add(cliente);
        return true;
    }
    
    public Cliente buscarClientePorIdentificacion(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }
    
    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
    
    public Pedido crearPedido(Cliente cliente) {
        Pedido nuevo = new Pedido(secuencialPedido++, cliente);
        pedidos.add(nuevo);
        return nuevo;
    }
    
    public Pedido buscarPedidoPorNumero(int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumeroPedido() == numero) return p;
        }
        return null;
    }
    
    public ArrayList<Pedido> obtenerPedidosActivos() {
        ArrayList<Pedido> activos = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.getEstado().equals("Pagado")) activos.add(p);
        }
        return activos;
    }
    
    public Factura facturarPedido(Pedido pedido, double porcentajeIva) {
        if (pedido == null || pedido.getDetalles().isEmpty()) return null;
        if (pedido.getEstado().equals("Pagado")) return null;
        
        pedido.avanzarEstado(); 
        Factura f = new Factura(pedido, porcentajeIva);
        facturas.add(f);
        return f;
    }
    
    public void generarReporteCierreDiario() {
        System.out.println("\n=======================================================");
        System.out.println("             REPORTE DE CIERRE DIARIO DE VENTAS         ");
        System.out.println("=======================================================");
        int totalAtendidos = facturas.size();
        double subtotalAcumulado = 0;
        double ivaAcumulado = 0;
        double totalNeto = 0;

        for (Factura f : facturas) {
            subtotalAcumulado += f.calcularSubTotal();
            ivaAcumulado += f.calcularImpuesto();
            totalNeto += f.calcularTotal();
        }
        
        System.out.println(String.format("Total Pedidos Pagados Atendidos: %d", totalAtendidos));
        System.out.println(String.format("Subtotal General Acumulado:    $%.2f", subtotalAcumulado));
        System.out.println(String.format("Total IVA Recaudado:           +$%.2f", ivaAcumulado));
        System.out.println("-------------------------------------------------------");
        System.out.println(String.format("MONTO TOTAL NETO VENDIDO:        $%.2f", totalNeto));
        System.out.println("=======================================================\n");
    }
        
}
