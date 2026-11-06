
package elcevichazo;


public abstract class Plato {
    private String codigo;
    private String nombre;
    private double precioBase;
    private boolean disponible;
    private String categoria;

    public Plato(String codigo, String nombre, double precioBase, boolean disponible, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getCategoria() {
        return categoria;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Plato plato = (Plato) obj;
        return codigo.equalsIgnoreCase(plato.codigo);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | Cat: %-12s | Precio: $%-6.2f | Disponible: %s",
                codigo, nombre, categoria, precioBase, (disponible ? "SI" : "NO"));
    }    
    
}
