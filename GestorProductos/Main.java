import java.util.*;

/**
 * @author Sebastian Escobar Claros
 * 
 * Clase principal con menú de consola para gestionar productos.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GestorProductos gestor = new GestorProductos();
    private static int contadorId = 1; // Contador para asignar ID a los productos

    public static void main(String[] args) {
        gestor.cargarDesdeArchivo();
        boolean salir = false; // Para controlar el bucle del menú

        while (!salir) {
            System.out.println("\n*** GESTOR DE PRODUCTOS ***");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Guardar y salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        listarProductos();
                        break;
                    case 3:
                        buscarProducto();
                        break;
                    case 4:
                        eliminarProducto();
                        break;
                    case 5:
                        gestor.guardarEnArchivo();
                        salir = true;
                        System.out.println("Datos guardados. Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
            }}}

/// Método para agregar un producto al gestor
    private static void agregarProducto() {
        System.out.print("Ingrese el tipo de producto (1. Electrónico, 2. Alimenticio): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        double precio = leerDouble("Precio: ");
        int stock = leerEntero("Stock: ");

// Validar que el stock sea mayor o igual a 0
        if (tipo == 1) {
            int garantia = leerEntero("Garantía (meses): ");
            Producto electronico = new Electronico(contadorId++, nombre, precio, stock, garantia);
            gestor.agregarProducto(electronico);
        } else if (tipo == 2) {
            System.out.print("Fecha de caducidad (ej: 2025-12-31): ");
            String fechaCaducidad = scanner.nextLine();
            Producto alimenticio = new Alimenticio(contadorId++, nombre, precio, stock, fechaCaducidad);
            gestor.agregarProducto(alimenticio);
        } else {
            System.out.println("Tipo no válido.");
            return;
        }

        System.out.println("Producto agregado con éxito.");
    }

// Método para listar todos los productos registrados
    private static void listarProductos() {
        ArrayList<Producto> productos = gestor.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (Producto p : productos) {
                System.out.println(p);
            }}}

// Método para buscar un producto por ID o nombre
    private static void buscarProducto() {
        System.out.print("Buscar por (1. ID, 2. Nombre): ");
        int opcion = Integer.parseInt(scanner.nextLine());

        if (opcion == 1) {
            int id = leerEntero("Ingrese ID: ");
            Producto p = gestor.buscarPorId(id);
            if (p != null) {
                System.out.println("Producto encontrado:\n" + p);
            } else {
                System.out.println("Producto no encontrado.");
            }
        } else if (opcion == 2) {
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();
            ArrayList<Producto> resultados = gestor.buscarPorNombre(nombre);
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron productos con ese nombre.");
            } else {
                System.out.println("--- Productos encontrados ---");
                for (Producto p : resultados) {
                    System.out.println(p);
                }
            }
        } else {
            System.out.println("Opción no válida.");
        } }

// Método para eliminar un producto por ID
    private static void eliminarProducto() {
        int id = leerEntero("Ingrese ID del producto a eliminar: ");
        boolean eliminado = gestor.eliminarProducto(id);
        if (eliminado) {
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }}

// Método para leer un entero con manejo de excepciones
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }}}

// Método para leer un double con manejo de excepciones
    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número decimal.");
            }}}}
