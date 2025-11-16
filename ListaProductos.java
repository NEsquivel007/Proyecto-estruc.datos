
import java.time.LocalDate;
public class ListaProductos {


     private Productos Primero;

    public ListaProductos(){
        Primero=null;
    }

    //getters

    public Productos getprimero(){
        return Primero;
    }

    //setter

    public void setprimero(Productos nuevoPrimero){
        Primero=nuevoPrimero;
    }

    // metodo insertar 

    public void insertarProductosInicio(String nombreProducto, double precioProducto, 
    String categoriaProducto, LocalDate fechaVencimientoProducto, 
    int cantidadProducto){

        Productos nuevaListaProductos=new Productos(nombreProducto,  precioProducto, 
     categoriaProducto,  fechaVencimientoProducto, 
    cantidadProducto);
    nuevaListaProductos.setSiguiente(Primero);
    setprimero(nuevaListaProductos);

    }

    //metodo buscar

    public Productos buscaProductos(String nombreBuscar){
        Productos ProductoActual=Primero;
        while (ProductoActual!=null && !ProductoActual.getNombre().equals(nombreBuscar)) {
            ProductoActual=ProductoActual.getSiguiente();
        }
        if (ProductoActual!=null) {
           // System.out.println("El producto buscado  se encuentra en el inventario ");
        }else{
           // System.out.println("El producto buscado no se ha encontrado en el inventario");
        }
        return ProductoActual;
    }
    

    // insertar un producto al final de la lista 

    public void insertarProductosFinal(String nombreProducto, double precioProducto, 
    String categoriaProducto, LocalDate fechaVencimientoProducto, 
    int cantidadProducto){
        Productos nuevProducto=new Productos(nombreProducto, precioProducto, categoriaProducto, fechaVencimientoProducto, cantidadProducto);
      if ((Primero==null)) {
        setprimero(nuevProducto);
        return;
      }
      Productos ProductoTemp=Primero;
      while (ProductoTemp.getSiguiente()!=null) {
        ProductoTemp=ProductoTemp.getSiguiente();
      }
      ProductoTemp.setSiguiente(nuevProducto);
    }

    //metodo para eliminar un Producto

    public boolean eliminaProductos(String nombreEliminar) {

    if (Primero == null) {
        System.out.println("La lista está vacía.");
        return false;
    }

    // Caso 1: el primero es el que se elimina
    if (Primero.getNombre().equals(nombreEliminar)) {
        Primero = Primero.getSiguiente();
        System.out.println("Producto eliminado correctamente.");
        return true;
    }

    // Caso 2: búsqueda en el resto de la lista
    Productos actual = Primero;

    while (actual.getSiguiente() != null &&
           !actual.getSiguiente().getNombre().equals(nombreEliminar)) {
        actual = actual.getSiguiente();
    }

    // Si no lo encontró
    if (actual.getSiguiente() == null) {
        System.out.println("El producto no se ha encontrado.");
        return false;
    }

    // Si lo encontró, eliminarlo
    actual.setSiguiente(actual.getSiguiente().getSiguiente());
    System.out.println("Producto eliminado correctamente.");
    return true;
}


    // metodo para mostrar productos 

    public void MostrarProductos(){
        if (Primero==null) {
            System.out.println("la lista esta vacia");
            return;
        }
        Productos ProductoActual=Primero;
        while (ProductoActual!=null) {
            System.out.println(ProductoActual);
            ProductoActual=ProductoActual.getSiguiente();
        }
    }

    
}
