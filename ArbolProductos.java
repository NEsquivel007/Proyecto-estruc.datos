public class ArbolProductos {
    
    private NodoArbol raiz;

    //constructor

    public ArbolProductos(){
        raiz=null;
    }

    //getters 

    public NodoArbol getRaiz(){
        return raiz;
    }

    //setters 

    public void setRaiz(NodoArbol nuvaRaiz){
        raiz=nuvaRaiz;
    }

    //metodo de busca 

    public boolean estaVacio(){
        return raiz == null;
    }

   public Productos buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Productos buscarRecursivo(NodoArbol nodo, String nombre) {
        // Caso base: no encontrado o árbol vacío
        if (nodo == null) {
            return null;
        }

        // Comparar con la llave (nombre)
        int comparacion = nombre.compareToIgnoreCase(nodo.getProducto().getNombre());

        if (comparacion == 0) {
            // ¡Encontrado!
            return nodo.getProducto();
        } else if (comparacion < 0) {
            // Buscar en el subárbol izquierdo
            return buscarRecursivo(nodo.getHijoIzquierdo(), nombre);
        } else {
            // Buscar en el subárbol derecho
            return buscarRecursivo(nodo.getHijoDerecho(), nombre);
        }
    }


    public NodoArbol insertar(NodoArbol nodo, Productos producto){
        if (estaVacio()) {
            return new NodoArbol(producto);
        }

        int comparacion= producto.getNombre().compareToIgnoreCase(nodo.getProducto().getNombre());

        if (comparacion<0) {
            //ir a la izquierda

            nodo.setHijoIzquierdo(insertar(nodo.getHijoIzquierdo(), producto));
        }else if (comparacion>0) {
            //ir a la derecha 

            nodo.setHijoDerecho(insertar(nodo.getHijoDerecho(), producto));
        }else{
            //Ya existe el producto

            System.out.println("Ya existe un producto con el nombre " +producto.getNombre()+" ");
            System.out.println("Se actualizara la cantidad sumando las unidades");

            //actualiza la cantidad 
             int cantidadActual = nodo.getProducto().getCantidad();
            nodo.getProducto().setCantidad(cantidadActual + producto.getCantidad());
        }
        return nodo;
    }

    //metodo de eliminacion

       public boolean eliminar(String nombre) {
        if (buscar(nombre) == null) {
            System.out.println("❌ El producto '" + nombre + "' no existe en el inventario.");
            return false;
        }
        raiz = eliminarRecursivo(raiz, nombre);
        System.out.println("✅ Producto '" + nombre + "' eliminado correctamente.");
        return true;
    }

    private NodoArbol eliminarRecursivo(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(nodo.getProducto().getNombre());

        if (comparacion < 0) {
            nodo.setHijoIzquierdo(eliminarRecursivo(nodo.getHijoIzquierdo(), nombre));
        } else if (comparacion > 0) {
            nodo.setHijoDerecho(eliminarRecursivo(nodo.getHijoDerecho(), nombre));
        } else {
            // Nodo encontrado, proceder a eliminarlo

            // Caso 1: Nodo sin hijos (hoja)
            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                return null;
            }

            // Caso 2: Nodo con un solo hijo
            if (nodo.getHijoIzquierdo() == null) {
                return nodo.getHijoDerecho();
            }
            if (nodo.getHijoDerecho() == null) {
                return nodo.getHijoIzquierdo();
            }

            // Caso 3: Nodo con dos hijos
            // Encontrar el sucesor (mínimo del subárbol derecho)
            Productos sucesor = encontrarMinimo(nodo.getHijoDerecho());
            nodo.setProducto(sucesor);
            nodo.setHijoDerecho(eliminarRecursivo(nodo.getHijoDerecho(), sucesor.getNombre()));
        }

        return nodo;
    }

    //encuentra el minimo para el caso 3 

      private Productos encontrarMinimo(NodoArbol nodo) {
        while (nodo.getHijoIzquierdo() != null) {
            nodo = nodo.getHijoIzquierdo();
        }
        return nodo.getProducto();
    }


    //modifica la cantidad 
    public boolean modificarCantidad(String nombre, int nuevaCantidad) {
        Productos producto =buscar(nombre);
        if (producto == null) {
            System.out.println("❌ Producto no encontrado.");
            return false;
        }
        producto.setCantidad(nuevaCantidad);
        System.out.println(" Cantidad actualizada: " + nombre + " ahora tiene " + nuevaCantidad + " unidades.");
        return true;
    }
}
