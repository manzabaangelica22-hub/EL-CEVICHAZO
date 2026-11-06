
package elcevichazo;


public class Cliente {
    private String id;
    private String nombre;
    
    public Cliente(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return id.equalsIgnoreCase(cliente.id);
    }

    @Override
    public String toString() {
        return String.format("Cliente: %-25s | C.I./RUC: %s", nombre, id);
    }
    
}
