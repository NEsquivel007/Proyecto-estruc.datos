import java.time.LocalDate;

public class Tienda {

    // Árbol que almacena todos los productos de la tienda
    private final ArbolProductos inventario;

    public Tienda() {
        inventario = new ArbolProductos();
        // Si querés empezar con productos de ejemplo, descomenta:
        // cargarEjemplos();
    }

    // ---------- Métodos de acceso al inventario ----------

    // Buscar un producto por nombre en el árbol
    public productos buscar(String nombre) {
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

    // Agregar un producto al árbol de inventario
    public void agregarProducto(productos p) {
        NodoArbol raizActual = inventario.getRaiz();
        raizActual = inventario.insertar(raizActual, p);
        inventario.setRaiz(raizActual);
    }

    // Eliminar un producto por nombre usando el árbol
    public boolean eliminarProducto(String nombre) {
        return inventario.eliminar(nombre);
    }

    // ---------- Carga de productos de ejemplo ----------

    public void cargarEjemplos() {
        productos p1 = new productos("Leche", 3500, "Lácteos", LocalDate.of(2026, 7, 15), 50);
        productos p2 = new productos("Pan", 3000, "Panadería", LocalDate.of(2026, 6, 30), 100);
        productos p3 = new productos("Manzanas", 100, "Frutas", LocalDate.of(2026, 7, 10), 200);
        productos p4 = new productos("Arroz", 4000, "Cereales", LocalDate.of(2026, 1, 1), 150);
        productos p5 = new productos("Huevos", 1800, "Lácteos", LocalDate.of(2026, 8, 5), 80);
        productos p6 = new productos("Pollo", 5000, "Carnes", LocalDate.of(2026, 6, 25), 60);
        productos p7 = new productos("Zanahorias", 800, "Verduras", LocalDate.of(2026, 7, 20), 120);
        productos p8 = new productos("Cereal", 4000, "Cereales", LocalDate.of(2026, 2, 15), 90);
        productos p9 = new productos("Yogur", 1500, "Lácteos", LocalDate.of(2026, 7, 5), 70);
        productos p10 = new productos("Tomates", 1300, "Verduras", LocalDate.of(2026, 7, 18), 110);
        productos p11 = new productos("Carne de res", 7000, "Carnes", LocalDate.of(2026, 6, 28), 40);
        productos p12 = new productos("Lechuga", 900, "Verduras", LocalDate.of(2026, 7, 12), 130);
        productos p13 = new productos("Queso", 3000, "Lácteos", LocalDate.of(2026, 8, 1), 30);
        productos p14 = new productos("Camarones", 10000, "Mariscos", LocalDate.of(2026, 6, 22), 25);
        productos p15 = new productos("Café", 6000, "Bebidas", LocalDate.of(2026, 3, 10), 75);
        productos p16 = new productos("Té", 4500, "Bebidas", LocalDate.of(2026, 4, 5), 85);
        productos p17 = new productos("Galletas", 2000, "Snacks", LocalDate.of(2026, 12, 31), 95);
        productos p18 = new productos("Jugo de naranja", 3500, "Bebidas", LocalDate.of(2026, 9, 15), 65);
        productos p19 = new productos("Pasta", 1800, "Cereales", LocalDate.of(2025, 5, 20), 140);
        productos p20 = new productos("Aceite de oliva", 8000, "Condimentos", LocalDate.of(2026, 1, 1), 55);

        agregarProducto(p1);
        agregarProducto(p2);
        agregarProducto(p3);
        agregarProducto(p4);
        agregarProducto(p5);
        agregarProducto(p6);
        agregarProducto(p7);
        agregarProducto(p8);
        agregarProducto(p9);
        agregarProducto(p10);
        agregarProducto(p11);
        agregarProducto(p12);
        agregarProducto(p13);
        agregarProducto(p14);
        agregarProducto(p15);
        agregarProducto(p16);
        agregarProducto(p17);
        agregarProducto(p18);
        agregarProducto(p19);
        agregarProducto(p20);
    }
}
