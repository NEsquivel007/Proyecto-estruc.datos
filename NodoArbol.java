

public class NodoArbol {
    

     
    private Productos ValorProducto;
    private NodoArbol hijoIzquierdo;
    private NodoArbol hijoDerecho;


    //constructor 

    public NodoArbol(Productos NombreProducto){
          this.ValorProducto=NombreProducto;

         hijoIzquierdo=hijoDerecho=null;
    }

    //getters 

    
    
    public Productos getProducto(){
        return ValorProducto;
    }

    public NodoArbol getHijoIzquierdo(){
        return hijoIzquierdo;
    }

       public NodoArbol getHijoDerecho(){
        return hijoDerecho;
    }


    //setters


    public void setProducto(Productos nuevoProducto){
        ValorProducto=nuevoProducto;
    }

    public void setHijoIzquierdo(NodoArbol nuevoHijoIzquierdo){
        hijoIzquierdo=nuevoHijoIzquierdo;
    }

      public void setHijoDerecho(NodoArbol nuevoHijoDerecho){
        hijoDerecho=nuevoHijoDerecho;
    }


    //to string 
    public void imprimir(){
        System.out.println(ValorProducto);
    }
}


