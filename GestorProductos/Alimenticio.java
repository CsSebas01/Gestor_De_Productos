/**
 * @author Sebastian Escobar Claros
 * 
 * Clase alimencticio que representa un producto alimenticio heredado.
 * Lo que representa es el producto alimenticio con fecha de caducidad.
 */

public class Alimenticio extends Producto {
    private String fechaCaducidad; 

    // Constructor que inicializa todos los atributos de la clase Alimenticio
    public Alimenticio(int id, String nombre, double precio, int stock, String fechaCaducidad) {
        super(id, nombre, precio, stock);
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }
    
    // Métodos para obtener y establecer la fecha de caducidad del producto alimenticio
    @Override
    public String toString() {
        return super.toString() + " | Fecha de caducidad: " + fechaCaducidad + " | Tipo: Alimenticio";
    }
}
