import java.time.LocalDate;

public class ListaProductos {

    private NodoListaP primero; //  CAMBIADO: ahora usa NodoListaP

    public ListaProductos() {
        primero = null;
    }

    // Getters
    public NodoListaP getPrimero() { //  CAMBIADO: retorna NodoListaP
        return primero;
    }

    // Setter
    public void setPrimero(NodoListaP nuevoPrimero) { //  CAMBIADO: recibe NodoListaP
        primero = nuevoPrimero;
    }

    // ⭐ Método insertar al inicio
    public void insertarProductosInicio(String nombreProducto, double precioProducto,
                                        String categoriaProducto, LocalDate fechaVencimientoProducto,
                                        int cantidadProducto) {
        Productos nuevoProducto = new Productos(nombreProducto, precioProducto,
                                                categoriaProducto, fechaVencimientoProducto,
                                                cantidadProducto);
        NodoListaP nuevoNodo = new NodoListaP(nuevoProducto);
        nuevoNodo.setSiguiente(primero);
        setPrimero(nuevoNodo);
    }

    // ⭐ Método buscar producto
    public Productos buscarProducto(String nombreBuscar) {
        NodoListaP actual = primero;
        while (actual != null && !actual.getProducto().getNombre().equalsIgnoreCase(nombreBuscar)) {
            actual = actual.getSiguiente();
        }
        return (actual != null) ? actual.getProducto() : null;
    }

    // ⭐ Insertar al final
    public void insertarProductosFinal(String nombreProducto, double precioProducto,
                                       String categoriaProducto, LocalDate fechaVencimientoProducto,
                                       int cantidadProducto) {
        Productos nuevoProducto = new Productos(nombreProducto, precioProducto,
                                                categoriaProducto, fechaVencimientoProducto,
                                                cantidadProducto);
        NodoListaP nuevoNodo = new NodoListaP(nuevoProducto);

        if (primero == null) {
            setPrimero(nuevoNodo);
            return;
        }

        NodoListaP temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nuevoNodo);
    }

    // ⭐ Eliminar producto
    public boolean eliminarProducto(String nombreEliminar) {
        if (primero == null) {
            System.out.println(" La lista está vacía.");
            return false;
        }

        // Caso: el primero es el que se elimina
        if (primero.getProducto().getNombre().equalsIgnoreCase(nombreEliminar)) {
            primero = primero.getSiguiente();
            System.out.println(" Producto eliminado correctamente.");
            return true;
        }

        // Búsqueda en el resto
        NodoListaP actual = primero;
        while (actual.getSiguiente() != null &&
               !actual.getSiguiente().getProducto().getNombre().equalsIgnoreCase(nombreEliminar)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null) {
            System.out.println(" El producto no se ha encontrado.");
            return false;
        }

        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        System.out.println(" Producto eliminado correctamente.");
        return true;
    }

    //  Mostrar productos
    public void mostrarProductos() {
        if (primero == null) {
            System.out.println(" La lista está vacía.");
            return;
        }

        NodoListaP actual = primero;
        int contador = 1;
        System.out.println("\n========== LISTA DE PRODUCTOS ==========");
        while (actual != null) {
            System.out.println("\n--- Producto " + contador + " ---");
            System.out.println(actual.getProducto());
            actual = actual.getSiguiente();
            contador++;
        }
        System.out.println("=========================================\n");
    }

    // ⭐ Mostrar reporte de costos (REQUERIDO POR EL PROYECTO)
    public void mostrarReporteCostos() {
        if (primero == null) {
            System.out.println("🛒 La lista está vacía.");
            return;
        }

        System.out.println("\n========== REPORTE DE COSTOS ==========");
        NodoListaP actual = primero;
        double total = 0;

        while (actual != null) {
            Productos prod = actual.getProducto();
            double costoProducto = prod.getPrecio() * prod.getCantidad();
            total += costoProducto;

            System.out.printf("%s - Cantidad: %d - Precio unitario: $%.2f - Subtotal: $%.2f%n",
                            prod.getNombre(), prod.getCantidad(), prod.getPrecio(), costoProducto);
            actual = actual.getSiguiente();
        }

        System.out.printf("%nTOTAL ACUMULADO: $%.2f%n", total);
        System.out.println("=========================================\n");
    }

    // ⭐ Verificar si está vacía
    public boolean estaVacia() {
        return primero == null;
    }
}