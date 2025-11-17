import java.time.LocalDate;

public class Tienda {

    // Árbol que almacena todos los productos de la tienda
    private final ArbolProductos inventario;

    public Tienda() {
        inventario = new ArbolProductos();
        // Ya no se cargan ejemplos automáticamente
    }

    // ---------- Métodos de acceso al inventario ----------

    // Buscar un producto por nombre en el árbol
    public Productos buscar(String nombre) {
        return inventario.buscar(nombre);
    }

    // Obtener la raíz del árbol (por si querés recorrerlo desde afuera)
    public NodoArbol getRaiz() {
        return inventario.getRaiz();
    }

    // Saber si el inventario está vacío
    public boolean estaVacio() {
        return inventario.estaVacio();
    }

    // ---------- Operaciones sobre el inventario ----------

    // Insertar un producto al árbol de inventario (NUEVO - compatible con Main)
    public void insertar(Productos p) {
        NodoArbol raizActual = inventario.getRaiz();
        raizActual = inventario.insertar(raizActual, p);
        inventario.setRaiz(raizActual);
    }

    // Agregar un producto al árbol de inventario (mantener compatibilidad)
    public void agregarProducto(Productos p) {
        insertar(p); // Llama al método insertar
    }

    // Eliminar un producto por nombre usando el árbol (ACTUALIZADO)
    public void eliminar(String nombre) {
        inventario.eliminar(nombre);
    }

    // Eliminar un producto por nombre (mantener compatibilidad)
    public boolean eliminarProducto(String nombre) {
        inventario.eliminar(nombre);
        return true; // Retorna true si se ejecutó
    }

    // Modificar la cantidad de un producto en el inventario (NUEVO)
    public void modificarCantidad(String nombre, int nuevaCantidad) {
        Productos prod = inventario.buscar(nombre);
        if (prod != null) {
            prod.setCantidad(nuevaCantidad);
            System.out.println(" Cantidad de '" + nombre + "' actualizada a " + nuevaCantidad);
        } else {
            System.out.println(" Producto no encontrado en el inventario.");
        }
    }

    // Listar todos los productos en orden alfabético (NUEVO)
    public void listarInorden() {
        if (estaVacio()) {
            System.out.println(" El inventario está vacío.");
            return;
        }
        System.out.println("\n========== INVENTARIO (Orden Alfabético) ==========");
        listarInordenRecursivo(inventario.getRaiz());
        System.out.println("===================================================\n");
    }

    // Método auxiliar recursivo para recorrido inorden
    private void listarInordenRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }
        listarInordenRecursivo(nodo.getHijoIzquierdo());
        System.out.println(nodo.getProducto());
        System.out.println("---------------------------------------------------");
        listarInordenRecursivo(nodo.getHijoDerecho());
    }

    // ---------- Carga de productos de ejemplo (OPCIONAL) ----------
    
    public void cargarEjemplos() {
        Productos p1 = new Productos("Leche", 3500, "Lácteos", LocalDate.of(2026, 7, 15), 50);
        Productos p2 = new Productos("Pan", 3000, "Panadería", LocalDate.of(2026, 6, 30), 100);
        Productos p3 = new Productos("Manzanas", 100, "Frutas", LocalDate.of(2026, 7, 10), 200);
        Productos p4 = new Productos("Arroz", 4000, "Cereales", LocalDate.of(2026, 1, 1), 150);
        Productos p5 = new Productos("Huevos", 1800, "Lácteos", LocalDate.of(2026, 8, 5), 80);


        insertar(p1);
        insertar(p2);
        insertar(p3);
        insertar(p4);
        insertar(p5);
        
        
        
    }
}