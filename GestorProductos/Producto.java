/**
 * @author Sebastian Escobar Claros
 * 
 * Clase base que representa un producto.
 * Contiene atributos y métodos comunes a todos los productos.
 */

public class Producto {
    protected int id;
    protected String nombre;
    protected double precio;
    protected int stock;

    // Constructor para crear un producto
    public Producto (int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    //Acá es para que no se pueda modificar el id, ya que es un atributo unico
    public int getId() {
         return id;}

    // Métodos para obtener los atributos del producto
    public String getNombre() {
         return nombre; }

    // Métodos para obtener los atributos del producto
    public double getPrecio() {
         return precio; }

    // Métodos para obtener los atributos del producto
    public int getStock() {
         return stock; }

/**
     * Actualiza la cantidad en stock del producto.
     * @param La cantidad a agregar o quitar del stock.
*/

// Método para actualizar el stock del producto
    public void setStock(int stock) { this.stock = stock; }

    @Override // Método para representar el producto como una cadena
    public String toString() { 
        return "ID: " + id +  
        " | Nombre: " + nombre +
        " | Precio: " + precio +
        " | Stock: " + stock;
    }
}
