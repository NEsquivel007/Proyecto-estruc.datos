public class ColaClientes {

    // Tres colas simples, una para cada nivel de prioridad
    private ColaSimple colaPremium;    // Prioridad 3
    private ColaSimple colaAfiliado;   // Prioridad 2
    private ColaSimple colaBasico;     // Prioridad 1

    public ColaClientes() {
        colaPremium = new ColaSimple();
        colaAfiliado = new ColaSimple();
        colaBasico = new ColaSimple();
    }

    // Encolar cliente según su prioridad
    public void encolar(Cliente cliente) {
        switch (cliente.getPrioridad()) {
            case 3:
                colaPremium.encolar(cliente);
                System.out.println("Cliente Premium agregado a la cola: " + cliente.getNombre());
                break;
            case 2:
                colaAfiliado.encolar(cliente);
                System.out.println("Cliente Afiliado agregado a la cola: " + cliente.getNombre());
                break;
            case 1:
                colaBasico.encolar(cliente);
                System.out.println("Cliente Básico agregado a la cola: " + cliente.getNombre());
                break;
            default:
                System.out.println("Prioridad inválida");
        }
    }

    // Desencolar al cliente con mayor prioridad
    public Cliente desencolar() {
        // Primero revisar Premium (prioridad 3)
        if (!colaPremium.estaVacia()) {
            return colaPremium.desencolar();
        }
        // Luego Afiliado (prioridad 2)
        if (!colaAfiliado.estaVacia()) {
            return colaAfiliado.desencolar();
        }
        // Finalmente Básico (prioridad 1)
        if (!colaBasico.estaVacia()) {
            return colaBasico.desencolar();
        }
        // Cola completamente vacía
        System.out.println("No hay clientes en la cola");
        return null;
    }

    // Ver el siguiente cliente sin eliminarlo
    public Cliente verSiguiente() {
        if (!colaPremium.estaVacia()) {
            return colaPremium.verPrimero();
        }
        if (!colaAfiliado.estaVacia()) {
            return colaAfiliado.verPrimero();
        }
        if (!colaBasico.estaVacia()) {
            return colaBasico.verPrimero();
        }
        return null;
    }

    // Alias para compatibilidad
    public Cliente frente() {
        return verSiguiente();
    }

    // Verificar si está vacía
    public boolean estaVacia() {
        return colaPremium.estaVacia() && 
               colaAfiliado.estaVacia() && 
               colaBasico.estaVacia();
    }

    // Obtener tamaño total
    public int tamaño() {
        return colaPremium.tamaño() + 
               colaAfiliado.tamaño() + 
               colaBasico.tamaño();
    }

    // Mostrar toda la cola
    public void mostrarCola() {
        System.out.println("\n========== COLA DE CLIENTES ==========");

        if (estaVacia()) {
            System.out.println("No hay clientes en la cola");
            System.out.println("======================================\n");
            return;
        }

        System.out.println("Total de clientes en espera: " + tamaño());
        System.out.println();

        // Mostrar cola Premium
        if (!colaPremium.estaVacia()) {
            System.out.println("CLIENTES PREMIUM (Prioridad 3):");
            colaPremium.mostrar();
            System.out.println();
        }

        // Mostrar cola Afiliado
        if (!colaAfiliado.estaVacia()) {
            System.out.println("CLIENTES AFILIADOS (Prioridad 2):");
            colaAfiliado.mostrar();
            System.out.println();
        }

        // Mostrar cola Básico
        if (!colaBasico.estaVacia()) {
            System.out.println("CLIENTES BÁSICOS (Prioridad 1):");
            colaBasico.mostrar();
            System.out.println();
        }

        System.out.println("======================================\n");
    }
}