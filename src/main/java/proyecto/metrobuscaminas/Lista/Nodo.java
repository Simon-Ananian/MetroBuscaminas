package Lista;

public class Nodo<T> {
   public T info;
   public Nodo<T> prox;
   public byte fila;
   public byte col;

    // Constructor estándar
    public Nodo(T info) {
        this.info = info;
        this.prox = null;
    }

    // Constructor para minas
    public Nodo(T info, byte fila, byte col) {
        this.info = info;
        this.fila = fila;
        this.col = col;
        this.prox = null;
    }
}
