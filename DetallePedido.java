
package elcevichazo;


public class DetallePedido {
    private Plato plato;
    private int cantidad;

    public DetallePedido(Plato plato, int cantidad) {
        this.plato = plato;
        this.cantidad = cantidad;
    }
    
    public double calcularSubTotalDetalle(){
        return plato.getPrecioBase() * cantidad;
    }

    public Plato getPlato() {
        return plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString() {
        return String.format("%-22s x %-2d | P.Unit: $%-6.2f | Subtotal: $%.2f",
                plato.getNombre(), cantidad, plato.getPrecioBase(), calcularSubTotalDetalle());
    }
    
}
