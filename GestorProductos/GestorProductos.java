import java.io.*;
import java.util.*;

/**
 * @author Sebastian Escobar Claros
 * 
 * Clase que gestiona una colección de productos,
 * permitiendo agregarlos, buscarlos, eliminarlos,
 * listarlos y guardar/cargar desde un archivo.
 */
public class GestorProductos {
    private ArrayList<Producto> productos;
    private final String archivo = "productos.txt";
    // Contenedor para almacenar los productos
    public GestorProductos() {
        productos = new ArrayList<>();
    }
    // Método para agregar un producto a la lista
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    // Método para buscar un producto por su ID
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    // Método para buscar productos por su nombre
    public ArrayList<Producto> buscarPorNombre(String nombre) {
        ArrayList<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }
    // Método para eliminar un producto por su ID
    public boolean eliminarProducto(int id) {
        Producto p = buscarPorId(id);
        if (p != null) {
            productos.remove(p);
            return true;
        }
        return false;
    }
    // Método para listar todos los productos
    public ArrayList<Producto> listarProductos() {
        return productos;
    }
    // Método para listar productos por tipo
    public void guardarEnArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) { // Abrir el archivo en modo escritura
            for (Producto p : productos) {
                String tipo = (p instanceof Electronico) ? "Electronico" : "Alimenticio";
                writer.print(p.getId() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + p.getStock() + ";" + tipo + ";");
                if (p instanceof Electronico) {
                    writer.println(((Electronico) p).getGarantia());
                } else if (p instanceof Alimenticio) {
                    writer.println(((Alimenticio) p).getFechaCaducidad());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
    // Método para cargar productos desde un archivo
    public void cargarDesdeArchivo() {
        File file = new File(archivo);
        if (!file.exists()) {
            return;
        }

        // Verifica si el archivo existe
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea; // Variable para almacenar cada línea del archivo
            while ((linea = reader.readLine()) != null) {

                String[] datos = linea.split(";"); // Divide la línea en partes usando el separador ";"
                int id = Integer.parseInt(datos[0]); // Convierte el primer dato a entero
                String nombre = datos[1]; // Obtiene el segundo dato como nombre
                double precio = Double.parseDouble(datos[2]); // Convierte el tercer dato a doble
                int stock = Integer.parseInt(datos[3]); // Convierte el cuarto dato a entero
                String tipo = datos[4];// Obtiene el quinto dato como tipo

                if (tipo.equals("Electronico")) {
                    int garantia = Integer.parseInt(datos[5]);
                    productos.add(new Electronico(id, nombre, precio, stock, garantia));
                } else if (tipo.equals("Alimenticio")) {
                    String fechaCaducidad = datos[5];
                    productos.add(new Alimenticio(id, nombre, precio, stock, fechaCaducidad));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar desde archivo: " + e.getMessage());
        }
    }
}
