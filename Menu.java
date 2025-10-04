import java.io.*;
import java.time.LocalDate;

public class Menu {

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final PrintStream out = System.out;
    private final listaProductos lista; // atributo para manejar productos

    // 🔹 Constructor que recibe la lista
    public Menu(listaProductos lista) {
        this.lista = lista;
    }

    // 🔹 Método público que ejecuta el menú
    public void run() throws IOException {
        int opcion;
        do {
            opcion = mostrarMenuYLeerOpcion();

            switch (opcion) {
                case 1 -> ingresarDatos();
                case 2 -> buscarProducto();
                case 3 -> eliminarProducto();
                case 4 -> mostrarProductos();
                case 5 -> out.println("Saliendo...");
                default -> out.println("❗ Opción inválida. Intenta de nuevo.");
            }

            out.println(); // línea en blanco
        } while (opcion != 5);
    }

    // ================== Opciones del menú ==================

    private void ingresarDatos() throws IOException {
        out.println("=== Ingresar datos del producto ===");
        String nombre = leerTexto("Nombre: ");
        double precio = leerDouble("Precio: ");
        String categoria = leerTexto("Categoría: ");
        String fechaTexto = leerTexto("Fecha de vencimiento (AAAA-MM-DD o vacío si no aplica): ");
        LocalDate fechaVenc = fechaTexto.isBlank() ? null : LocalDate.parse(fechaTexto);
        int cantidad = leerEntero("Cantidad: ");

        int donde;
        do {
            out.println("\n--- ¿Dónde insertar? ---");
            out.println("1) Insertar al INICIO");
            out.println("2) Insertar al FINAL");
            out.println("0) Cancelar");
            donde = leerEntero("Opción: ");

            if (donde == 1) {
                lista.insertarProductosInicio(nombre, precio, categoria, fechaVenc, cantidad);
                out.println("✔ Producto insertado al INICIO.");
            } else if (donde == 2) {
                lista.insertarProductosFinal(nombre, precio, categoria, fechaVenc, cantidad);
                out.println("✔ Producto insertado al FINAL.");
            } else if (donde == 0) {
                out.println("Operación cancelada.");
            } else {
                out.println("❗ Opción inválida.");
            }
        } while (donde != 0 && donde != 1 && donde != 2);
    }

    private void buscarProducto() throws IOException {
        String nombre = leerTexto("Nombre a buscar: ");
        productos encontrado = lista.buscaProductos(nombre);
        out.println(encontrado != null ? encontrado : "No se encontró el producto.");
    }

    private void eliminarProducto() throws IOException {
        String nombre = leerTexto("Nombre a eliminar: ");
        lista.eliminaProductos(nombre);
    }

    private void mostrarProductos() {
        lista.MostrarProductos();
    }

    // ================== Helpers ==================

    private int mostrarMenuYLeerOpcion() throws IOException {
        out.println("============ Supermercado ===========");
        out.println("1) Ingresar datos (con submenú de inserción)");
        out.println("2) Buscar producto");
        out.println("3) Eliminar un producto");
        out.println("4) Mostrar la lista de productos");
        out.println("5) Salir");
        return leerEntero("Opción: ");
    }

    private String leerTexto(String msg) throws IOException {
        out.print(msg);
        return in.readLine().trim();
    }

    private int leerEntero(String msg) throws IOException {
        while (true) {
            try {
                out.print(msg);
                return Integer.parseInt(in.readLine().trim());
            } catch (NumberFormatException e) {
                out.println("Ingrese un número entero válido.");
            }
        }
    }

    private double leerDouble(String msg) throws IOException {
        while (true) {
            try {
                out.print(msg);
                return Double.parseDouble(in.readLine().trim());
            } catch (NumberFormatException e) {
                out.println("Ingrese un número decimal válido (use punto).");
            }
        }
    }
}
