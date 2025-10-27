import java.time.LocalDate;
import java.util.ArrayList;

public class producto {

    // atributos
    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaVencimiento;
    private int cantidad;
    ArrayList<String> listaImagenes;
    private producto siguiente;

    public producto(String nombreObj, double precioObj,
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

    // getters
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

    public producto getSiguiente() {
        return siguiente;
    }

    // setters

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

    public void setSiguiente(producto nuevoProductos) {
        siguiente = nuevoProductos;
    }
    // metedo to string

    public String toString() {
        return "Nombre: " + nombre +
                "\n Precio: " + precio +
                "\n Categoria: " + categoria +
                "\n Fecha de vencimiento: " + fechaVencimiento +
                "\n Cantidad: " + cantidad;
    }

}
