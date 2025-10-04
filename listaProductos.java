
import java.time.LocalDate;

public class listaProductos {

    private productos Primero;

    public listaProductos() {
        Primero = null;
    }

    // getters

    public productos getprimero() {
        return Primero;
    }

    // setter

    public void setprimero(productos nuevoPrimero) {
        Primero = nuevoPrimero;
    }

    // metodo insertar

    public void insertarProductosInicio(String nombreProducto, double precioProducto,
            String categoriaProducto, LocalDate fechaVencimientoProducto,
            int cantidadProducto) {

        productos nuevaListaProductos = new productos(nombreProducto, precioProducto,
                categoriaProducto, fechaVencimientoProducto,
                cantidadProducto);
        nuevaListaProductos.setSiguiente(Primero);
        setprimero(nuevaListaProductos);

    }

    // metodo buscar

    public productos buscaProductos(String nombreBuscar) {
        productos ProductoActual = Primero;
        while (ProductoActual != null && !ProductoActual.getNombre().equals(nombreBuscar)) {
            ProductoActual = ProductoActual.getSiguiente();
        }
        if (ProductoActual != null) {
            // System.out.println("El producto buscado se encuentra en el inventario ");
        } else {
            // System.out.println("El producto buscado no se ha encontrado en el
            // inventario");
        }
        return ProductoActual;
    }

    // insertar un producto al final de la lista

    public void insertarProductosFinal(String nombreProducto, double precioProducto,
            String categoriaProducto, LocalDate fechaVencimientoProducto,
            int cantidadProducto) {
        productos nuevProducto = new productos(nombreProducto, precioProducto, categoriaProducto,
                fechaVencimientoProducto, cantidadProducto);
        if ((Primero == null)) {
            setprimero(nuevProducto);
            return;
        }
        productos ProductoTemp = Primero;
        while (ProductoTemp.getSiguiente() != null) {
            ProductoTemp = ProductoTemp.getSiguiente();
        }
        ProductoTemp.setSiguiente(nuevProducto);
    }

    // metodo para eliminar un Producto

    public productos eliminaProductos(String nombreElimanar) {
        if (Primero == null) {
            System.out.println("la lista se encuentra vacia");
            return null;
        }
        if (Primero.getNombre().equals(nombreElimanar)) {
            Primero = Primero.getSiguiente();
        }

        productos ProductoActu = Primero;
        while (ProductoActu.getSiguiente() != null && ProductoActu.getSiguiente().getNombre().equals(nombreElimanar)) {
            ProductoActu = ProductoActu.getSiguiente();
        }
        if (ProductoActu.getSiguiente() != null) {
            System.out.println("El producto budcado se encontro en la estructura");
            ProductoActu.setSiguiente(ProductoActu.getSiguiente().getSiguiente());
        } else {
            System.out.println("El producto no se ha encontrado");
        }

        return ProductoActu;
    }

    // metodo para mostrar productos

    public void MostrarProductos() {
        if (Primero == null) {
            System.out.println("la lista esta vacia");
            return;
        }
        productos ProductoActual = Primero;
        while (ProductoActual != null) {
            System.out.println(ProductoActual);
            ProductoActual = ProductoActual.getSiguiente();
        }
    }

}
