public class main {
    public static void main(String[] args) throws Exception {
        listaProductos inventario = new listaProductos();
        Menu menu = new Menu(inventario);
        menu.run();
    }
}
