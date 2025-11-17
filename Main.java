import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.ejecutar();
    }

    public static class Menu {

        private Tienda tienda;
        private ColaClientes cola;
        private Scanner scanner;

        public Menu() {
            tienda = new Tienda();
            cola = new ColaClientes();
            scanner = new Scanner(System.in);
        }

        public void ejecutar() {
            int opcion;
            do {
                mostrarMenuPrincipal();
                opcion = leerEntero("Digite una opción: ");

                switch (opcion) {
                    case 1 -> gestionarInventario();
                    case 2 -> gestionarClientes();
                    case 3 -> gestionarCarrito();
                    case 4 -> atenderSiguienteCliente();
                    case 5 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida, intente de nuevo.");
                }

                System.out.println();

            } while (opcion != 5);

            scanner.close();
        }

        // ================ MENÚ PRINCIPAL ================

        private void mostrarMenuPrincipal() {
            System.out.println("\nSISTEMA DE GESTIÓN TIENDA");
            System.out.println("1. Gestionar Inventario");
            System.out.println("2. Gestionar Clientes (Cola)");
            System.out.println("3. Gestionar Carrito (Cliente Actual)");
            System.out.println("4. Atender Siguiente Cliente");
            System.out.println("5. Salir");
        }

        // ================ 1. GESTIONAR INVENTARIO ================

        private void gestionarInventario() {
            int opcion;
            do {
                mostrarMenuInventario();
                opcion = leerEntero("Digite una opción: ");

                switch (opcion) {
                    case 1 -> insertarProductoInventario();
                    case 2 -> buscarProductoInventario();
                    case 3 -> eliminarProductoInventario();
                    case 4 -> modificarCantidadInventario();
                    case 5 -> mostrarInventarioCompleto();
                    case 6 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }

                System.out.println();

            } while (opcion != 6);
        }

        private void mostrarMenuInventario() {
            System.out.println("\nGESTIÓN DE INVENTARIO");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Modificar cantidad");
            System.out.println("5. Mostrar inventario completo");
            System.out.println("6. Volver al menú principal");
        }

        // --- 1.1: Insertar producto ---
        private void insertarProductoInventario() {
            System.out.println("\n--- AGREGAR PRODUCTO AL INVENTARIO ---");
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return;
            }

            double precio = leerDouble("Precio: $");

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine().trim();

            System.out.print("¿Tiene fecha de vencimiento? (s/n): ");
            String tieneVenc = scanner.nextLine().trim().toLowerCase();

            LocalDate fechaVencimiento = null;
            if (tieneVenc.equals("s") || tieneVenc.equals("si") || tieneVenc.equals("sí")) {
                fechaVencimiento = leerFecha("Fecha de vencimiento (dd/MM/yyyy): ");
            }

            int cantidad = leerEntero("Cantidad en inventario: ");

            Productos nuevoProducto = new Productos(nombre, precio, categoria, fechaVencimiento, cantidad);

            // Preguntar si desea agregar imágenes
            System.out.print("¿Desea agregar rutas de imágenes? (s/n): ");
            String respImg = scanner.nextLine().trim().toLowerCase();

            if (respImg.equals("s") || respImg.equals("si") || respImg.equals("sí")) {
                String continuar;
                do {
                    System.out.print("Ruta de la imagen: ");
                    String ruta = scanner.nextLine().trim();
                    if (!ruta.isEmpty()) {
                        nuevoProducto.getListaImagenes().add(ruta);
                    }
                    System.out.print("¿Agregar otra imagen? (s/n): ");
                    continuar = scanner.nextLine().trim().toLowerCase();
                } while (continuar.equals("s") || continuar.equals("si") || continuar.equals("sí"));
            }

            tienda.insertar(nuevoProducto);
            System.out.println("Producto agregado exitosamente al inventario.");
        }

        // --- 1.2: Buscar producto ---
        private void buscarProductoInventario() {
            System.out.println("\n--- BUSCAR PRODUCTO EN INVENTARIO ---");
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del producto a buscar: ");
            String nombre = scanner.nextLine().trim();

            Productos producto = tienda.buscar(nombre);

            if (producto == null) {
                System.out.println("Producto no encontrado en el inventario.");
            } else {
                System.out.println("Producto encontrado:");
                System.out.println(producto);
            }
        }

        // --- 1.3: Eliminar producto ---
        private void eliminarProductoInventario() {
            System.out.println("\n--- ELIMINAR PRODUCTO DEL INVENTARIO ---");
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del producto a eliminar: ");
            String nombre = scanner.nextLine().trim();

            Productos producto = tienda.buscar(nombre);

            if (producto == null) {
                System.out.println("Producto no encontrado en el inventario.");
                return;
            }

            System.out.println("Producto a eliminar:");
            System.out.println(producto);

            System.out.print("¿Está seguro que desea eliminarlo? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
                tienda.eliminar(nombre);
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }

        // --- 1.4: Modificar cantidad ---
        private void modificarCantidadInventario() {
            System.out.println("\n--- MODIFICAR CANTIDAD EN INVENTARIO ---");
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();

            Productos producto = tienda.buscar(nombre);

            if (producto == null) {
                System.out.println("Producto no encontrado en el inventario.");
                return;
            }

            System.out.println("Producto encontrado:");
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Cantidad actual: " + producto.getCantidad());

            int nuevaCantidad = leerEntero("Nueva cantidad: ");

            tienda.modificarCantidad(nombre, nuevaCantidad);
            System.out.println("Cantidad actualizada exitosamente.");
        }

        // --- 1.5: Mostrar inventario completo ---
        private void mostrarInventarioCompleto() {
            System.out.println("\n--- INVENTARIO COMPLETO (ORDEN ALFABÉTICO) ---");
            if (tienda.estaVacio()) {
                System.out.println("El inventario está vacío.");
                return;
            }
            tienda.listarInorden();
        }

        // ================ 2. GESTIONAR CLIENTES (COLA) ================

        private void gestionarClientes() {
            int opcion;
            do {
                mostrarMenuClientes();
                opcion = leerEntero("Digite una opción: ");

                switch (opcion) {
                    case 1 -> insertarClienteEnCola();
                    case 2 -> verColaClientes();
                    case 3 -> verSiguienteCliente();
                    case 4 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }

                System.out.println();

            } while (opcion != 4);
        }

        private void mostrarMenuClientes() {
            System.out.println("\nGESTIÓN DE CLIENTES");
            System.out.println("1. Agregar cliente a la cola");
            System.out.println("2. Ver cola completa");
            System.out.println("3. Ver siguiente cliente");
            System.out.println("4. Volver al menú principal");
        }

        // --- 2.1: Insertar cliente ---
        private void insertarClienteEnCola() {
            System.out.println("\n--- AGREGAR CLIENTE A LA COLA ---");
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return;
            }

            System.out.print("ID del cliente: ");
            String id = scanner.nextLine().trim();

            int prioridad;
            do {
                System.out.println("\nTipos de cliente:");
                System.out.println("  1 - Básico");
                System.out.println("  2 - Afiliado");
                System.out.println("  3 - Premium");
                prioridad = leerEntero("Prioridad (1, 2 o 3): ");
                if (prioridad < 1 || prioridad > 3) {
                    System.out.println("Prioridad inválida. Debe ser 1, 2 o 3.");
                }
            } while (prioridad < 1 || prioridad > 3);

            Cliente cliente = new Cliente(nombre, id, prioridad);
            cola.encolar(cliente);

            System.out.println("Cliente agregado a la cola exitosamente.");
        }

        // --- 2.2: Ver cola completa ---
        private void verColaClientes() {
            cola.mostrarCola();
        }

        // --- 2.3: Ver siguiente cliente ---
        private void verSiguienteCliente() {
            System.out.println("\n--- SIGUIENTE CLIENTE EN LA COLA ---");
            if (cola.estaVacia()) {
                System.out.println("No hay clientes en la cola.");
                return;
            }

            Cliente siguiente = cola.verSiguiente();
            System.out.println("Próximo a ser atendido:");
            System.out.println(siguiente);
        }

        // ================ 3. GESTIONAR CARRITO ================

        private void gestionarCarrito() {
            if (cola.estaVacia()) {
                System.out.println("No hay clientes en la cola.");
                return;
            }

            Cliente cliente = cola.verSiguiente();
            System.out.println("\nGestionando carrito de: " + cliente.getNombre() +
                    " (Prioridad " + cliente.getPrioridad() + ")");

            int opcion;
            do {
                mostrarMenuCarrito();
                opcion = leerEntero("Digite una opción: ");

                switch (opcion) {
                    case 1 -> agregarProductoAlCarrito(cliente);
                    case 2 -> buscarProductoEnCarrito(cliente);
                    case 3 -> eliminarProductoDelCarrito(cliente);
                    case 4 -> verCarritoCompleto(cliente);
                    case 5 -> verTotalCarrito(cliente);
                    case 6 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }

                System.out.println();

            } while (opcion != 6);
        }

        private void mostrarMenuCarrito() {
            System.out.println("\nGESTIÓN DE CARRITO");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto en carrito");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Ver carrito completo");
            System.out.println("5. Ver total del carrito");
            System.out.println("6. Volver al menú principal");
        }

        // --- 3.1: Agregar producto al carrito ---
        private void agregarProductoAlCarrito(Cliente cliente) {
            System.out.println("\n--- AGREGAR PRODUCTO AL CARRITO ---");

            if (tienda.estaVacio()) {
                System.out.println("El inventario está vacío.");
                return;
            }

            // Mostrar inventario
            System.out.println("\nInventario disponible:");
            tienda.listarInorden();

            scanner.nextLine(); // limpiar buffer
            System.out.print("\nNombre del producto a agregar: ");
            String nombre = scanner.nextLine().trim();

            Productos prodInventario = tienda.buscar(nombre);

            if (prodInventario == null) {
                System.out.println("Producto no encontrado en el inventario.");
                return;
            }

            System.out.println("Producto encontrado: " + prodInventario.getNombre());
            System.out.println("Precio: $" + prodInventario.getPrecio());
            System.out.println("Disponibles: " + prodInventario.getCantidad());

            int cantidad = leerEntero("Cantidad a agregar: ");

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a 0.");
                return;
            }

            if (cantidad > prodInventario.getCantidad()) {
                System.out.println("No hay suficiente stock. Solo hay " +
                        prodInventario.getCantidad() + " unidades disponibles.");
                return;
            }

            // Crear copia del producto para el carrito
            Productos prodCarrito = new Productos(
                    prodInventario.getNombre(),
                    prodInventario.getPrecio(),
                    prodInventario.getCategoria(),
                    prodInventario.getFechaVencimiento(),
                    cantidad
            );

            cliente.agregarProductoAlCarrito(prodCarrito);
            System.out.println("Producto agregado al carrito exitosamente.");
        }

        // --- 3.2: Buscar producto en carrito ---
        private void buscarProductoEnCarrito(Cliente cliente) {
            System.out.println("\n--- BUSCAR PRODUCTO EN CARRITO ---");

            if (cliente.getCarrito().estaVacia()) {
                System.out.println("El carrito está vacío.");
                return;
            }

            scanner.nextLine(); // limpiar buffer
            System.out.print("Nombre del producto a buscar: ");
            String nombre = scanner.nextLine().trim();

            Productos producto = cliente.getCarrito().buscarProducto(nombre);

            if (producto == null) {
                System.out.println("Producto no encontrado en el carrito.");
            } else {
                System.out.println("Producto encontrado en el carrito:");
                System.out.println(producto);
            }
        }

        // --- 3.3: Eliminar producto del carrito ---
        private void eliminarProductoDelCarrito(Cliente cliente) {
            System.out.println("\n--- ELIMINAR PRODUCTO DEL CARRITO ---");

            if (cliente.getCarrito().estaVacia()) {
                System.out.println("El carrito está vacío.");
                return;
            }

            // Mostrar carrito actual
            System.out.println("\nCarrito actual:");
            cliente.getCarrito().mostrarProductos();

            scanner.nextLine(); // limpiar buffer
            System.out.print("\nNombre del producto a eliminar: ");
            String nombre = scanner.nextLine().trim();

            boolean eliminado = cliente.getCarrito().eliminarProducto(nombre);

            if (eliminado) {
                System.out.println("Producto eliminado del carrito.");
            }
        }

        // --- 3.4: Ver carrito completo ---
        private void verCarritoCompleto(Cliente cliente) {
            System.out.println("\nCARRITO DE " + cliente.getNombre().toUpperCase());
            if (cliente.getCarrito().estaVacia()) {
                System.out.println("El carrito está vacío.");
                return;
            }
            cliente.getCarrito().mostrarProductos();
        }

        // --- 3.5: Ver total del carrito ---
        private void verTotalCarrito(Cliente cliente) {
            System.out.println("\nTOTAL DEL CARRITO");
            if (cliente.getCarrito().estaVacia()) {
                System.out.println("El carrito está vacío.");
                return;
            }
            cliente.getCarrito().mostrarReporteCostos();
        }

        // ================ 4. ATENDER SIGUIENTE CLIENTE ================

        private void atenderSiguienteCliente() {
            System.out.println("\n--- ATENDER SIGUIENTE CLIENTE ---");

            if (cola.estaVacia()) {
                System.out.println("No hay clientes en la cola.");
                return;
            }

            Cliente cliente = cola.desencolar();
            System.out.println("\nAtendiendo a: " + cliente.getNombre());

            if (cliente.getCarrito().estaVacia()) {
                System.out.println("El cliente no tiene productos en su carrito.");
                System.out.println("No se genera factura.");
                return;
            }

            // Generar factura
            generarFactura(cliente);

            // Actualizar inventario
            actualizarInventarioDespuesDeCompra(cliente);

            System.out.println("\nCliente atendido exitosamente.");
        }

        private void generarFactura(Cliente cliente) {
            System.out.println("\n========== FACTURA ==========");
            System.out.println("Cliente: " + cliente.getNombre());
            System.out.println("ID: " + cliente.getId());

            String tipoPrioridad = switch (cliente.getPrioridad()) {
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
            NodoListaP actual = cliente.getCarrito().getPrimero();

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

        private void actualizarInventarioDespuesDeCompra(Cliente cliente) {
            NodoListaP actual = cliente.getCarrito().getPrimero();

            while (actual != null) {
                Productos prodCarrito = actual.getProducto();
                Productos prodInventario = tienda.buscar(prodCarrito.getNombre());

                if (prodInventario != null) {
                    int nuevaCantidad = prodInventario.getCantidad() - prodCarrito.getCantidad();
                    tienda.modificarCantidad(prodInventario.getNombre(), nuevaCantidad);
                }

                actual = actual.getSiguiente();
            }

            System.out.println("Inventario actualizado.");
        }

        // ================ MÉTODOS UTILITARIOS ================

        private int leerEntero(String msg) {
            int valor;
            while (true) {
                System.out.print(msg);
                if (scanner.hasNextInt()) {
                    valor = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Debe digitar un número entero.");
                    scanner.next();
                }
            }
            return valor;
        }

        private double leerDouble(String msg) {
            double valor;
            while (true) {
                System.out.print(msg);
                if (scanner.hasNextDouble()) {
                    valor = scanner.nextDouble();
                    break;
                } else {
                    System.out.println("Debe digitar un número decimal.");
                    scanner.next();
                }
            }
            return valor;
        }

        private LocalDate leerFecha(String msg) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = null;

            while (fecha == null) {
                System.out.print(msg);
                String fechaStr = scanner.nextLine().trim();

                try {
                    fecha = LocalDate.parse(fechaStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Use dd/MM/yyyy (ejemplo: 25/12/2024)");
                }
            }
            return fecha;
        }
    }
}