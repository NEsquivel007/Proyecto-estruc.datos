import java.util.PriorityQueue;
import java.util.Comparator;

public class ColaClientes {

    private PriorityQueue<Cliente> cola;
    private int contadorLlegada = 0;

    public ColaClientes() {
        Comparator<Cliente> comparador = new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                // Primero por prioridad (descendente)
                if (c1.getPrioridad() != c2.getPrioridad()) {
                    return Integer.compare(c2.getPrioridad(), c1.getPrioridad());
                }
                // Luego por orden de llegada (ascendente)
                return Integer.compare(c1.getOrdenLlegada(), c2.getOrdenLlegada());
            }
        };

        cola = new PriorityQueue<>(comparador);
    }

    public void encolar(Cliente cliente) {
        cliente.setOrdenLlegada(contadorLlegada++);
        cola.add(cliente);
    }

    public Cliente desencolar() {
        return cola.poll();
    }

    public Cliente frente() {
        return cola.peek();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }
}

