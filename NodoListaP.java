public class NodoListaP {
    
    private Productos producto;
    private NodoListaP siguiente;

    // Constructor
    public NodoListaP(Productos producto) {
        this.producto = producto;
        this.siguiente = null;
    }

    // Getters
    public Productos getProducto() {
        return producto;
    }

    public NodoListaP getSiguiente() {
        return siguiente;
    }

    // Setters
    public void setProducto(Productos nuevoProducto) {
        producto = nuevoProducto;
    }

    public void setSiguiente(NodoListaP nuevoSiguiente) {
        siguiente = nuevoSiguiente;
    }
}
