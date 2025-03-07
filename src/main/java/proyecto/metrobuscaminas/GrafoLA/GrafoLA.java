/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GrafoLA;

import Lista.Lista;
import Lista.Nodo;

public class GrafoLA {
    private boolean dirigido;
    private int maxNodos;
    private int numVertices;
    private Lista<Nodo<Byte>> listaVertices;
    private Lista<Lista<Integer>> listaAdy;

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
        Nodo<Lista<Integer>> posAdy = listaAdy.fin();
        listaAdy.insertar(posAdy, new Lista<>());

        numVertices++;
    }

    public void imprimirListaVertices() {
    Nodo<Nodo<Byte>> aux = listaVertices.primero();

    while (aux != null) {
        Nodo<Byte> mina = aux.info;
        
        // Convertir el valor Byte a char
        char caracter = (char) mina.info.byteValue();
        
        System.out.println("Mina en posicion: (" + caracter + " -> " + mina.fila + "," + mina.col + ")");
        
        aux = aux.prox;
    }
    
    
/* public void imprimirGrafo() {
        System.out.println("Tamaño máximo del grafo: " + maxNodos);
        System.out.println("El grafo contiene " + numVertices + " vértices:");
        
        Nodo<Lista<Integer>> auxAdy = listaAdy.primero();
        Nodo<Integer> auxVert = listaVertices.primero();
 
        
        while (auxVert != null && auxAdy != null) {
            System.out.print("Vértice " + auxVert.info + ": ");
            escribir(auxAdy.info);
            auxVert = auxVert.prox;
            auxAdy = auxAdy.prox;
        }
    }
    private void escribir(Lista<Integer> lista) {
        if (lista == null) {
            System.out.println("FIN");
            return;
        }
        Nodo<Integer> aux = lista.primero();
        while (aux != null) {
            System.out.print(aux.info + " -> ");
            aux = aux.prox;
        }
        System.out.println("FIN");
    } 
    
    public void insertaArista(int i, int j) {
        if (i >= numVertices || j >= numVertices) {
            System.out.println("Error, no existe el vértice en el grafo");
            return;
        }

        System.out.println("Insertando arista de " + i + " a " + j);
        Lista<Integer> adyI = obtenerListaAdy(i);
        Nodo<Integer> pos = adyI.fin();
        adyI.insertar(pos, j);

        if (!dirigido) {
            Lista<Integer> adyJ = obtenerListaAdy(j);
            pos = adyJ.fin();
            adyJ.insertar(pos, i);
        }
    }
    private Lista<Integer> obtenerListaAdy(int vertice) {
        Nodo<Lista<Integer>> aux = listaAdy.primero();
        for (int i = 0; i < vertice && aux != null; i++) {
            aux = aux.prox;
        }
        return aux != null ? aux.info : null;
    }
    private Lista<Integer> obtenerListaAdy(int vertice) {
        Nodo<Lista<Integer>> aux = listaAdy.primero();
        for (int i = 0; i < vertice && aux != null; i++) {
            aux = aux.prox;
        }
        return aux != null ? aux.info : null;
    }
    */
    /*
     public void insertaArista(int i, int j) {
        if (i >= numVertices || j >= numVertices) {
            System.out.println("Error, no existe el vértice en el grafo");
            return;
        }

        System.out.println("Insertando arista de " + i + " a " + j);
        Lista<Integer> adyI = obtenerListaAdy(i);
        Nodo<Integer> pos = adyI.fin();
        adyI.insertar(pos, j);

        if (!dirigido) {
            Lista<Integer> adyJ = obtenerListaAdy(j);
            pos = adyJ.fin();
            adyJ.insertar(pos, i);
        }
    }
    */

    
}

}
