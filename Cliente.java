public class Cliente {

    private String nombre;
    private int prioridad; // 1, 2 o 3
    private int ordenLlegada; // para desempatar en la cola de prioridad
    private ListaProductos carrito; // lista de productos del cliente

    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
    }

    // Getters y setters básicos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public int getOrdenLlegada() {
        return ordenLlegada;
    }

    public void setOrdenLlegada(int ordenLlegada) {
        this.ordenLlegada = ordenLlegada;
    }

    // -------------------------------------------------
    //   MÉTODOS DEL ENUNCIADO
    // -------------------------------------------------

    // Agregar un producto del inventario al carrito del cliente, con cantidad
    public void agregarProductoAlCarrito(productos prodInventario, int cantidad) {
        if (prodInventario == null) {
            System.out.println("No se puede agregar un producto nulo al carrito.");
            return;
        }
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return;
        }

        carrito.insertarProductosFinal(
                prodInventario.getNombre(),
                prodInventario.getPrecio(),
                prodInventario.getCategoria(),
                prodInventario.getFechaVencimiento(),
                cantidad
        );

        System.out.println("Producto agregado al carrito de " + nombre + ".");
    }

    // Imprimir el contenido del carrito
    public void imprimirCarrito() {
        System.out.println("=== Carrito de " + nombre + " ===");
        carrito.MostrarProductos();
    }

    // Calcular el total del carrito recorriendo la lista
    public double calcularTotal() {
        double total = 0.0;

        productos actual = carrito.getPrimero();
        while (actual != null) {
            total += actual.getPrecio() * actual.getCantidad();
            actual = actual.getSiguiente();
        }

        return total;
    }

    // Imprimir factura
    public void imprimirFactura() {
        System.out.println("========== FACTURA ==========");
        System.out.println("Cliente: " + nombre + " (Prioridad " + prioridad + ")");
        System.out.println("-----------------------------");
        imprimirCarrito();
        System.out.println("-----------------------------");
        System.out.println("TOTAL A PAGAR: ₡" + calcularTotal());
        System.out.println("=============================");
    }

    @Override
    public String toString() {
        return "Cliente{nombre='" + nombre + "', prioridad=" + prioridad + "}";
    }
}
