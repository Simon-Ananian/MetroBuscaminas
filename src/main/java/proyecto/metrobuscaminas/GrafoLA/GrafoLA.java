package GrafoLA;

import Lista.Lista;
import Lista.Nodo;

public class GrafoLA {
    private boolean dirigido;
    private int maxNodos;
    private int numVertices;
    private Lista<Nodo<Byte>> listaVertices;
    private Lista<Lista<Nodo<Integer>>> listaAdy; // Lista de listas de adyacencia

    public GrafoLA(int filas, int columnas, boolean d) {
        dirigido = d;
        maxNodos = filas * columnas;
        numVertices = 0;
        listaVertices = new Lista<>();
        listaAdy = new Lista<>();
    }

    public void insertaVertice(Nodo<Byte> mina) {
        if (numVertices + 1 > maxNodos) {
            System.out.println("Error, se supera el número de nodos máximo del grafo");
            return;
        }

        // Insertamos la mina como vértice en la lista de vértices
        Nodo<Nodo<Byte>> posVert = listaVertices.fin();
        listaVertices.insertar(posVert, mina);

        // Insertamos la lista de adyacencia vacía para este vértice
        Nodo<Lista<Nodo<Integer>>> posAdy = listaAdy.fin();
        listaAdy.insertar(posAdy, new Lista<>());

        numVertices++;
    }

    public void calcularAdyacencias(int filas, int columnas) {
        Nodo<Nodo<Byte>> aux = listaVertices.primero();
        int index = 0;

        while (aux != null) {
            Nodo<Byte> mina = aux.info;
            Lista<Nodo<Integer>> adyacencias = obtenerListaAdy(index);

            // Generar posiciones adyacentes dentro de los límites
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue; // Omitimos la posición de la mina

                    int nuevaFila = mina.fila + i;
                    int nuevaColumna = mina.col + j;

                    // Verificamos que las coordenadas estén dentro de los límites
                    if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                        Nodo<Integer> nuevaPosicion = new Nodo<>(1, (byte) nuevaFila, (byte) nuevaColumna);
                        Nodo<Nodo<Integer>> posAdy = adyacencias.fin();
                        adyacencias.insertar(posAdy, nuevaPosicion);
                    }
                }
            }

            aux = aux.prox;
            index++;
        }
    }

    private Lista<Nodo<Integer>> obtenerListaAdy(int vertice) {
        Nodo<Lista<Nodo<Integer>>> aux = listaAdy.primero();
        for (int i = 0; i < vertice && aux != null; i++) {
            aux = aux.prox;
        }
        return aux != null ? aux.info : null;
    }

    public void imprimirListaVertices() {
        Nodo<Nodo<Byte>> aux = listaVertices.primero();

        while (aux != null) {
            Nodo<Byte> mina = aux.info;
            System.out.println("Mina en Posición: (* -> (" + mina.fila + "," + mina.col + "))");
            aux = aux.prox;
        }
    }

    public void imprimirListaAdyacencia() {
    Nodo<Nodo<Byte>> auxVert = listaVertices.primero();
    Nodo<Lista<Nodo<Integer>>> auxAdy = listaAdy.primero();

    while (auxVert != null && auxAdy != null) {
        Nodo<Byte> mina = auxVert.info;
        System.out.print("Mina en Posicion: (* -> (" + mina.fila + "," + mina.col + ")); ");

        Nodo<Nodo<Integer>> aux = auxAdy.info.primero();
        while (aux != null) {
            System.out.print("1 en -> (" + aux.info.fila + "," + aux.info.col + "), ");
            aux = aux.prox;
        }
        System.out.println(); // Salto de línea solo al final de cada mina

        auxVert = auxVert.prox;
        auxAdy = auxAdy.prox;
    }
}

}
