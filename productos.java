import java.time.LocalDate;
import java.util.ArrayList;

public class productos {

    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaVencimiento; 
    private int cantidad;
    ArrayList<String> listaImagenes;
    private productos siguiente;

    public productos(String nombreObj, double precioObj,
                     String categoriaObj, LocalDate fechaVencimientoObj,
                     int cantidadObj) {

        nombre = nombreObj;
        precio = precioObj;
        categoria = categoriaObj;
        fechaVencimiento = fechaVencimientoObj;
        cantidad = cantidadObj;
        listaImagenes = new ArrayList<>();
        siguiente = null;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public ArrayList<String> getListaImagenes() { return listaImagenes; }
    public productos getSiguiente() { return siguiente; }

    // Setters
    public void setNombre(String nuevoNombre) { nombre = nuevoNombre; }
    public void setPrecio(double nuevoPrecio) { precio = nuevoPrecio; }
    public void setCategoria(String nuevaCategoria) { categoria = nuevaCategoria; }
    public void setFechaVencimiento(LocalDate nuevaFechaVencimiento) { fechaVencimiento = nuevaFechaVencimiento; }
    public void setCantidad(int nuevaCantidad) { cantidad = nuevaCantidad; }
    public void setSiguiente(productos nuevoProductos) { siguiente = nuevoProductos; }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               "\nPrecio: " + precio +
               "\nCategoría: " + categoria +
               "\nFecha de vencimiento: " + (fechaVencimiento != null ? fechaVencimiento : "N/A") +
               "\nCantidad: " + cantidad;
    }
}




    

