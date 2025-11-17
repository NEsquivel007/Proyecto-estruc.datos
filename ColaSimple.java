public class ColaSimple {

    private NodoCliente primero;
    private NodoCliente ultimo;
    private int tamaño;

    public ColaSimple() {
        primero = null;
        ultimo = null;
        tamaño = 0;
    }

    // Encolar al final (agregar cliente)
    public void encolar(Cliente cliente) {
        NodoCliente nuevoNodo = new NodoCliente(cliente);

        if (estaVacia()) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
            ultimo = nuevoNodo;
        }
        tamaño++;
    }

    // Desencolar (eliminar del frente)
    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }

        Cliente clienteAtendido = primero.getCliente();
        primero = primero.getSiguiente();

        // Si la cola quedó vacía, actualizar ultimo
        if (primero == null) {
            ultimo = null;
        }

        tamaño--;
        return clienteAtendido;
    }

    // Ver el primero sin eliminarlo
    public Cliente verPrimero() {
        if (estaVacia()) {
            return null;
        }
        return primero.getCliente();
    }

    // Verificar si está vacía
    public boolean estaVacia() {
        return primero == null;
    }

    // Obtener tamaño
    public int tamaño() {
        return tamaño;
    }

    // Mostrar todos los clientes de esta cola
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  (vacía)");
            return;
        }

        NodoCliente actual = primero;
        int posicion = 1;

        while (actual != null) {
            Cliente c = actual.getCliente();
            System.out.printf("  %d. %s (ID: %s)%n", posicion, c.getNombre(), c.getId());
            actual = actual.getSiguiente();
            posicion++;
        }
    }
}