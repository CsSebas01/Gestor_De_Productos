/**
 * @author Sebastian Escobar Claros
 * 
 * Clase que representa un producto electrónico heredado.
 * Hereda de Producto e incluye información adicional sobre la garantía.
 */

public class Electronico extends Producto {
    private int garantia; // se añade nuevo atributo en MESES

    // Constructor que inicializa todos los atributos de la clase Electronico
    public Electronico(int id, String nombre, double precio, int stock, int garantia) {
        super(id, nombre, precio, stock); // Llama al constructor de la clase base Producto
        this.garantia = garantia;
    }

    // Métodos para obtener y establecer la garantía del producto electrónico
    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

/*
 * Retorna una representación en cadena del producto electrónico,
 * incluyendo la información de garantía.
 *
 * @return, Cadena con la información del producto electrónico.
 */

    @Override
    public String toString() {
        return super.toString() + " | Garantía: " + garantia + " meses | Tipo: Electrónico";
    } }

