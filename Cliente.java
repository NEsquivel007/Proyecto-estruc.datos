public class Cliente {

    private String nombre;
    private String id;
    private int prioridad; // 1, 2 o 3
    private ListaProductos carrito;

    // Constructor
    public Cliente(String nombre, String id, int prioridad) {
        this.nombre = nombre;
        this.id = id;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    // Setters
    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    public void setId(String nuevoId) {
        id = nuevoId;
    }

    public void setPrioridad(int nuevaPrioridad) {
        prioridad = nuevaPrioridad;
    }

    // Agregar producto al carrito (sobrecarga 1)
    public void agregarProductoAlCarrito(Productos prodInventario, int cantidad) {
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

    // Agregar producto al carrito (sobrecarga 2)
    public void agregarProductoAlCarrito(Productos producto) {
        if (producto == null) {
            System.out.println("No se puede agregar un producto nulo al carrito.");
            return;
        }
        carrito.insertarProductosFinal(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCategoria(),
                producto.getFechaVencimiento(),
                producto.getCantidad()
        );
    }

    // Imprimir carrito
    public void imprimirCarrito() {
        System.out.println("Carrito de " + nombre);
        carrito.mostrarProductos();
    }

    // Calcular total del carrito
    public double calcularTotal() {
        double total = 0.0;

        NodoListaP actual = carrito.getPrimero();
        while (actual != null) {
            Productos prod = actual.getProducto();
            total += prod.getPrecio() * prod.getCantidad();
            actual = actual.getSiguiente();
        }

        return total;
    }

    // Imprimir factura
    public void imprimirFactura() {
        System.out.println("\n========== FACTURA ==========");
        System.out.println("Cliente: " + nombre);
        System.out.println("ID: " + id);

        String tipoPrioridad = switch (prioridad) {
            case 3 -> "Premium";
            case 2 -> "Afiliado";
            case 1 -> "Básico";
            default -> "Desconocido";
        };
        System.out.println("Tipo: " + tipoPrioridad);
        System.out.println("=============================");
        System.out.println("PRODUCTOS:");
        System.out.println("=============================");

        double total = 0.0;
        NodoListaP actual = carrito.getPrimero();

        while (actual != null) {
            Productos prod = actual.getProducto();
            double subtotal = prod.getPrecio() * prod.getCantidad();
            total += subtotal;

            System.out.println(prod.getNombre());
            System.out.printf("  Cantidad: %d  Precio: $%.2f%n", prod.getCantidad(), prod.getPrecio());
            System.out.printf("  Subtotal: $%.2f%n", subtotal);
            System.out.println();

            actual = actual.getSiguiente();
        }

        System.out.println("=============================");
        System.out.printf("TOTAL A PAGAR: $%.2f%n", total);
        System.out.println("=============================");
    }

    @Override
    public String toString() {
        String tipoPrioridad = switch (prioridad) {
            case 3 -> "Premium";
            case 2 -> "Afiliado";
            case 1 -> "Básico";
            default -> "Desconocido";
        };

        return String.format("Cliente: %s (ID: %s) - Tipo: %s - Total en carrito: $%.2f",
                           nombre, id, tipoPrioridad, calcularTotal());
    }
}