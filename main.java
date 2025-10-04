public class main {
    public static void main(String[] args) throws Exception {
        listaProductos inventario = new listaProductos();
        Menu Menu = new Menu(inventario);
        Menu.run();
    }
}
