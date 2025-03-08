package GrafoLA;

import Lista.Lista;
import Lista.Nodo;

public class GrafoLA {
    private boolean dirigido;
    private int maxNodos;
    private int numVertices;
    private Lista<Nodo<Byte>> listaVertices;
    private Lista<Lista<Nodo<Integer>>> listaAdy;
    private int[][] matrizValores;  // Matriz para visualizar la salida

    public GrafoLA(int filas, int columnas, boolean d) {
        dirigido = d;
        maxNodos = filas * columnas;
        numVertices = 0;
        listaVertices = new Lista<>();
        listaAdy = new Lista<>();
        matrizValores = new int[filas][columnas];  // Inicializa la matriz con ceros
    }

   public void insertaVertice(Nodo<Byte> mina) {
    if (numVertices + 1 > maxNodos) {
        System.out.println("Error, se supera el número de nodos máximo del grafo");
        return;
    }

    // Insertamos la mina como vértice en la lista de vértices
    Nodo<Nodo<Byte>> posVert = listaVertices.fin();
    listaVertices.insertar(posVert, mina);

    // Aseguramos que cada vértice tenga una lista de adyacencias inicializada
    listaAdy.insertar(listaAdy.fin(), new Lista<>());

    numVertices++;
}


    public void calcularAdyacencias() {
        Nodo<Nodo<Byte>> aux = listaVertices.primero();
        while (aux != null) {
            if (!aux.info.visitado) {
                calcularAdyacenciasRecursivo(aux.info);
            }
            aux = aux.prox;
        }
    }

    private void calcularAdyacenciasRecursivo(Nodo<Byte> mina) {
    mina.visitado = true;
    int fila = mina.fila;
    int columna = mina.col;

    int index = obtenerIndiceMina(mina);
    if (index == -1) return; // No debería ocurrir, pero evita errores
    Lista<Nodo<Integer>> adyacencias = obtenerListaAdy(index);

    // Verificamos que la lista de adyacencias no sea null
    if (adyacencias == null) {
        adyacencias = new Lista<>();
    }

    for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
            if (i == 0 && j == 0) continue;

            int nuevaFila = fila + i;
            int nuevaColumna = columna + j;

            if (nuevaFila >= 0 && nuevaFila < matrizValores.length &&
                nuevaColumna >= 0 && nuevaColumna < matrizValores[0].length) {

                boolean esMina = false;
                Nodo<Nodo<Byte>> aux = listaVertices.primero();
                while (aux != null) {
                    if (aux.info.fila == nuevaFila && aux.info.col == nuevaColumna) {
                        esMina = true;
                        if (!aux.info.visitado) {
                            calcularAdyacenciasRecursivo(aux.info);
                        }
                        break;
                    }
                    aux = aux.prox;
                }

                if (!esMina) {
                    matrizValores[nuevaFila][nuevaColumna]++;
                    Nodo<Integer> nuevaPosicion = new Nodo<>(matrizValores[nuevaFila][nuevaColumna], (byte) nuevaFila, (byte) nuevaColumna);
                    Nodo<Nodo<Integer>> posAdy = adyacencias.fin();
                    adyacencias.insertar(posAdy, nuevaPosicion);
                }
            }
        }
    }
}
private int obtenerIndiceMina(Nodo<Byte> mina) {
    Nodo<Nodo<Byte>> aux = listaVertices.primero();
    int index = 0;
    while (aux != null) {
        if (aux.info.fila == mina.fila && aux.info.col == mina.col) {
            return index;
        }
        aux = aux.prox;
        index++;
    }
    return -1; // Si no encuentra la mina
}

    
 private Lista<Nodo<Integer>> obtenerListaAdy(int index) {
    Nodo<Lista<Nodo<Integer>>> aux = listaAdy.primero();
    for (int i = 0; i < index && aux != null; i++) {
        aux = aux.prox;
    }
    return aux != null ? aux.info : new Lista<>();
}
    
    public String[][] obtenerMatrizConMinasYNumeros() {
        String[][] matriz = new String[matrizValores.length][matrizValores[0].length];

        for (int i = 0; i < matrizValores.length; i++) {
            for (int j = 0; j < matrizValores[0].length; j++) {
                boolean esMina = false;
                Nodo<Nodo<Byte>> aux = listaVertices.primero();

            while (aux != null) {
                if (aux.info.fila == i && aux.info.col == j) {
                    esMina = true;
                    break;
                }
                aux = aux.prox;
            }

            if (esMina) {
                matriz[i][j] = "*"; // Mina
            } else {
                matriz[i][j] = String.valueOf(matrizValores[i][j]); // Número de minas adyacentes
            }
        }
    }
        return matriz;
    }
}

    
