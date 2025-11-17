import java.util.ArrayList;
import java.time.LocalDate;

public class Productos {

    // Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;
    //  ELIMINADO: private productos siguiente; (esto va en el nodo, no aquí)

    // Constructor
    public Productos(String nombreObj, double precioObj,
                     String categoriaObj, LocalDate fechaVencimientoObj,
                     int cantidadObj) {
        nombre = nombreObj;
        precio = precioObj;
        categoria = categoriaObj;
        fechaVencimiento = fechaVencimientoObj;
        cantidad = cantidadObj;
        listaImagenes = new ArrayList<>();
        // ❌ ELIMINADO: siguiente = null;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    //  ELIMINADO: getSiguiente()

    // Setters
    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    public void setPrecio(double nuevoPrecio) {
        precio = nuevoPrecio;
    }

    public void setCategoria(String nuevaCategoria) {
        categoria = nuevaCategoria;
    }

    public void setFechaVencimiento(LocalDate nuevaFechaVencimiento) {
        fechaVencimiento = nuevaFechaVencimiento;
    }

    public void setCantidad(int nuevaCantidad) {
        cantidad = nuevaCantidad;
    }

    //  ELIMINADO: setSiguiente()

    // Método para agregar imagen
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    // toString
    public String toString() {
        String fechaStr = (fechaVencimiento != null) ? fechaVencimiento.toString() : "N/A";
        return "Nombre: " + nombre +
               "\nPrecio: $" + precio +
               "\nCategoría: " + categoria +
               "\nFecha de vencimiento: " + fechaStr +
               "\nCantidad: " + cantidad;
    }
}