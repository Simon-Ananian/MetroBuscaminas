package Lista;

public class Nodo<T> {
   public T info;
   public Nodo<T> prox;
   public byte fila;
   public byte col;
   public boolean visitado;  // Nuevo atributo para marcar visitas

   // Constructor para nodos normales
   public Nodo(T info) {
       this.info = info;
       this.prox = null;
       this.visitado = false;
   }

   // Constructor para nodos con coordenadas (minas)
   public Nodo(T info, byte fila, byte col) {
       this.info = info;
       this.fila = fila;
       this.col = col;
       this.prox = null;
       this.visitado = false;
   }
}
