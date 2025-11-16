import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.ejecutar();
    }

    public static class Menu {

        private Tienda tienda;        // AHORA usamos Tienda
        private ColaClientes cola;
        private Scanner scanner;

        public Menu() {
            tienda = new Tienda();
            tienda.cargarEjemplos();  // <<< AQUÍ SE LLENAN LOS 20 PRODUCTOS
            cola = new ColaClientes();
            scanner = new Scanner(System.in);
        }

        public void ejecutar() {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEntero("Digite una opción: ");

                switch (opcion) {
                    case 1 -> insertarClienteEnCola();
                    case 2 -> agregarProductosAlCarritoDelClienteEnFrente();
                    case 3 -> atenderSiguienteCliente();
                    case 4 -> mostrarInventarioYPermitirSeleccion();
                    case 5 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida, intente de nuevo.");
                }

                System.out.println();

            } while (opcion != 5);
        }

        // ---------------- MENÚ PRINCIPAL ----------------

        private void mostrarMenu() {
            System.out.println("======== MENÚ TIENDA ========");
            System.out.println("1. Insertar cliente en la cola");
            System.out.println("2. Agregar productos al carrito (cliente en frente, por nombre)");
            System.out.println("3. Atender al siguiente cliente (factura)");
            System.out.println("4. Mostrar inventario y seleccionar de la lista");
            System.out.println("5. Salir");
            System.out.println("================================");
        }

        // --------- OPCIÓN 1: insertar cliente en cola ---------

        private void insertarClienteEnCola() {
            System.out.println("--- Insertar cliente en la cola ---");
            scanner.nextLine(); // limpiar buffer

            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine();

            int prioridad;
            do {
                prioridad = leerEntero("Prioridad (1, 2 o 3; 3 es la más alta): ");
                if (prioridad < 1 || prioridad > 3) {
                    System.out.println("Prioridad inválida.");
                }
            } while (prioridad < 1 || prioridad > 3);

            Cliente cliente = new Cliente(nombre, prioridad);
            cola.encolar(cliente);

            System.out.println("Cliente agregado a la cola.");
        }

        // --------- OPCIÓN 2: agregar productos al carrito por NOMBRE ---------

        private void agregarProductosAlCarritoDelClienteEnFrente() {
            System.out.println("--- Agregar productos al carrito (cliente en frente) ---");

            if (cola.estaVacia()) {
                System.out.println("No hay clientes en la cola.");
                return;
            }

            Cliente cliente = cola.frente();
            System.out.println("Cliente actual: " + cliente.getNombre() +
                    " (Prioridad " + cliente.getPrioridad() + ")");

            String seguir;
            do {
                mostrarInventarioSimple(); // solo lo imprime

                scanner.nextLine(); // limpiar buffer
                System.out.print("Nombre del producto a agregar al carrito: ");
                String nombreProd = scanner.nextLine();

                productos prodInv = tienda.buscar(nombreProd); // BUSCA EN LA TIENDA

                if (prodInv == null) {
                    System.out.println("El producto no existe en el inventario.");
                } else {
                    int cantidad = leerEntero("Cantidad que desea agregar: ");
                    cliente.agregarProductoAlCarrito(prodInv, cantidad);
                }

                System.out.print("¿Agregar otro producto? (s/n): ");
                seguir = scanner.nextLine().trim().toLowerCase();

            } while (seguir.equals("s") || seguir.equals("si") || seguir.equals("sí"));
        }

        // --------- OPCIÓN 3: atender siguiente cliente ---------

        private void atenderSiguienteCliente() {
            System.out.println("--- Atender siguiente cliente ---");

            if (cola.estaVacia()) {
                System.out.println("No hay clientes en la cola.");
                return;
            }

            Cliente cliente = cola.desencolar();
            System.out.println("Atendiendo a: " + cliente.getNombre());
            cliente.imprimirFactura();
        }

        // --------- OPCIÓN 4: mostrar inventario y dejar escoger ---------

        private void mostrarInventarioYPermitirSeleccion() {
            System.out.println("--- Inventario de la tienda ---");
            if (tienda.estaVacio()) {
                System.out.println("El inventario está vacío.");
                return;
            }

            // Pasar el árbol a una lista en recorrido inorden
            ArrayList<productos> lista = new ArrayList<>();
            llenarListaInorden(tienda.getRaiz(), lista);

            // Mostrar productos numerados
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ". " + lista.get(i)); // usa toString() de productos
            }

            if (cola.estaVacia()) {
                System.out.println("No hay clientes en la cola. Solo se muestra el inventario.");
                return;
            }

            scanner.nextLine(); // limpiar buffer
            System.out.print("¿El cliente en frente desea elegir un producto de la lista? (s/n): ");
            String resp = scanner.nextLine().trim().toLowerCase();

            if (!resp.equals("s") && !resp.equals("si") && !resp.equals("sí")) {
                return;
            }

            Cliente cliente = cola.frente();
            System.out.println("Cliente actual: " + cliente.getNombre() +
                    " (Prioridad " + cliente.getPrioridad() + ")");

            int indice;
            do {
                indice = leerEntero("Digite el número del producto que desea: ");
                if (indice < 1 || indice > lista.size()) {
                    System.out.println("Número inválido, intente de nuevo.");
                }
            } while (indice < 1 || indice > lista.size());

            productos seleccionado = lista.get(indice - 1);
            int cantidad = leerEntero("Cantidad que desea agregar: ");
            cliente.agregarProductoAlCarrito(seleccionado, cantidad);

            System.out.println("Producto agregado al carrito del cliente.");
        }

        // --------- Apoyos para inventario ---------

        // Solo mostrar inventario (sin seleccionar)
        private void mostrarInventarioSimple() {
            System.out.println("--- Inventario (recorrido inorden) ---");
            if (tienda.estaVacio()) {
                System.out.println("El inventario está vacío.");
                return;
            }
            mostrarInorden(tienda.getRaiz());
        }

        private void llenarListaInorden(NodoArbol nodo, ArrayList<productos> lista) {
            if (nodo == null) {
                return;
            }
            llenarListaInorden(nodo.getHijoIzquierdo(), lista);
            lista.add(nodo.getProducto());
            llenarListaInorden(nodo.getHijoDerecho(), lista);
        }

        private void mostrarInorden(NodoArbol nodo) {
            if (nodo == null) {
                return;
            }
            mostrarInorden(nodo.getHijoIzquierdo());
            System.out.println(nodo.getProducto());
            mostrarInorden(nodo.getHijoDerecho());
        }

        // --------- Métodos utilitarios ---------

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
    }
}
