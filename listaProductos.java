import java.time.LocalDate;

public class listaProductos {

    private productos Primero;

    public listaProductos() {
        Primero = null;
    }

    public productos getprimero() {
        return Primero;
    }

    public void setprimero(productos nuevoPrimero) {
        Primero = nuevoPrimero;
    }

    // insertar al inicio
    public void insertarProductosInicio(String nombreProducto, double precioProducto,
            String categoriaProducto, LocalDate fechaVencimientoProducto,
            int cantidadProducto) {
        productos nuevaListaProductos = new productos(nombreProducto, precioProducto,
                categoriaProducto, fechaVencimientoProducto,
                cantidadProducto);
        nuevaListaProductos.setSiguiente(Primero);
        setprimero(nuevaListaProductos);
    }

    // insertar al final
    public void insertarProductosFinal(String nombreProducto, double precioProducto,
            String categoriaProducto, LocalDate fechaVencimientoProducto,
            int cantidadProducto) {
        productos nuevoProducto = new productos(nombreProducto, precioProducto,
                categoriaProducto, fechaVencimientoProducto,
                cantidadProducto);
        if (Primero == null) {
            setprimero(nuevoProducto);
            return;
        }
        productos temp = Primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nuevoProducto);
    }

    // buscar, eliminar y mostrar no cambian mucho la verdad :)
    public productos buscaProductos(String nombreBuscar) {
        productos actual = Primero;
        while (actual != null && !actual.getNombre().equalsIgnoreCase(nombreBuscar)) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public void eliminaProductos(String nombre) {
        if (Primero == null)
            return;
        // Si el producto a eliminar es el primero
        if (Primero.getNombre().equalsIgnoreCase(nombre)) {
            Primero = Primero.getSiguiente();
            return;
        }
        productos actual = Primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getNombre().equalsIgnoreCase(nombre)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    public void MostrarProductos() {
        if (Primero == null) {
            System.out.println("La lista está vacía");
            return;
        }
        productos actual = Primero;
        while (actual != null) {
            System.out.println(actual);
            actual = actual.getSiguiente();
        }
    }
}
