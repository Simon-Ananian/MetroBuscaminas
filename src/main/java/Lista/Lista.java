package Lista;

public class Lista<T> {
    Nodo<T> cabeza;

    public Lista() {
        this.cabeza = new Nodo<>(null); // Nodo ficticio para representar la cabeza de la lista
    }

    public boolean esVacia() {
        return cabeza.prox == null;
    }

    public Nodo<T> fin() {
        return null;
    }

    public Nodo<T> primero() {
        return cabeza.prox;
    }

    public Nodo<T> sucesor(Nodo<T> p) {
        return p.prox;
    }

    public Nodo<T> antecesor(Nodo<T> p) {
        Nodo<T> ap = cabeza;
        while (ap.prox != null && ap.prox != p) {
            ap = ap.prox;
        }
        return ap;
    }

    public Nodo<T> localizar(T x) {
        Nodo<T> p = cabeza.prox;
        while (p != null) {
            if (p.info.equals(x)) {
                return p;
            }
            p = p.prox;
        }
        return null;
    }

    public void insertar(Nodo<T> p, T x) {
        Nodo<T> nuevo = new Nodo<>(x);
        Nodo<T> ap = cabeza;
        while (ap.prox != p) {
            ap = ap.prox;
        }
        nuevo.prox = p;
        ap.prox = nuevo;
    }

    public void eliminar(Nodo<T> p) {
        Nodo<T> ap = cabeza;
        while (ap.prox != null && ap.prox != p) {
            ap = ap.prox;
        }
        if (ap.prox == p) {
            ap.prox = p.prox;
        }
    }

    public T acceder(Nodo<T> p) {
        return p.info;
    }

   
}
