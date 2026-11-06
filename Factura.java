
package elcevichazo;


public class Factura implements Facturable{
    private static int contadorFacturas = 1;
    private int numeroFactura;
    private Pedido pedido;
    private double porcentajeIva;

    public Factura(Pedido pedido, double porcentajeIva) {
        this.pedido = pedido;
        this.porcentajeIva = porcentajeIva;
    }
   
    @Override
    public double calcularSubTotal(){
        return pedido.calcularSubTotal();
    }
    @Override
    public double calcularImpuesto(){
        return calcularSubTotal() * porcentajeIva;
    }
    @Override
    public double calcularTotal(){
        return calcularSubTotal() + calcularImpuesto();
    }
    
    public void generarComprobante(){
        System.out.println("========================================");
        System.out.println("       PICANTERIA 'EL CEVICHAZO'        ");
        System.out.println("           FACTURA N " + numeroFactura);
        System.out.println("========================================");
        System.out.println(pedido.getCliente());
        System.out.println("----------------------------------------");
        System.out.println("Cant\tPlato\t\tP.Unit\tSubtotal");
        for (DetallePedido d : pedido.getDetalles()) {
            System.out.println(d.getCantidad() + "\t" + d.getPlato().getNombre() + "\t\t$" + d.getPlato().getPrecioBase() + "\t$" + d.calcularSubTotalDetalle());
        }
        System.out.println("----------------------------------------");
        System.out.println(String.format("Subtotal: $%.2f", calcularSubTotal()));
        System.out.println(String.format("IVA (%%%.0f): $%.2f", (porcentajeIva * 100), calcularImpuesto()));
        System.out.println(String.format("TOTAL A PAGAR: $%.2f", calcularTotal()));
        System.out.println("========================================");
    }
    
    public Pedido getPedido(){
        return pedido;
    }
}
