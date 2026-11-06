
package elcevichazo;
import java.util.ArrayList;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private ArrayList<DetallePedido> detalles;
    private String estado;

    public Pedido(int numeroPedido, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.estado = "Pendiente";
    }
    
    public void agregarDetalle(Plato plato, int cantidad){
        if (plato.isDisponible()){
            detalles.add(new DetallePedido(plato, cantidad));
            
        }
    }
    
    public boolean avanzarEstado(){
        if  (estado.equals("Pendiente")){
            estado = "En preparacion";
            return true;
        }else if(estado.equals("En preparacion")){
           estado = "Servido";
           return true;
    }else if(estado.equals("Servido")){
        estado = "Pagado";
        return true;
    }
    return false;
    }
    
    public double calcularSubTotal(){
        double subtotal = 0;
        for (DetallePedido detalle : detalles){
            subtotal =+ detalle.calcularSubTotalDetalle();
        }
        return subtotal;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public String getEstado() {
        return estado;
    }
    
    @Override
    public String toString() {
        return String.format("Pedido #%03d | %s | Estado: [%-14s] | Items: %d | Subtotal: $%.2f",
                numeroPedido, cliente.getNombre(), estado, detalles.size(), calcularSubTotal());
    }
    
}
