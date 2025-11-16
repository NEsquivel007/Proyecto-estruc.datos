

public class NodoProducto {
    

     
    private Productos ValorProducto;
    private NodoProducto hijoIzquierdo;
    private NodoProducto hijoDerecho;


    //constructor 

    public NodoProducto(Productos NombreProducto){
          this.ValorProducto=NombreProducto;

         hijoIzquierdo=hijoDerecho=null;
    }

    //getters 

    
    
    public Productos getProducto(){
        return ValorProducto;
    }

    public NodoProducto getHijoIzquierdo(){
        return hijoIzquierdo;
    }

       public NodoProducto getHijoDerecho(){
        return hijoDerecho;
    }


    //setters


    public void setProducto(Productos nuevoProducto){
        ValorProducto=nuevoProducto;
    }

    public void setHijoIzquierdo(NodoProducto nuevoHijoIzquierdo){
        hijoIzquierdo=nuevoHijoIzquierdo;
    }

      public void setHijoDerecho(NodoProducto nuevoHijoDerecho){
        hijoDerecho=nuevoHijoDerecho;
    }


    //to string 
    public void imprimir(){
        System.out.println(ValorProducto);
    }
}


