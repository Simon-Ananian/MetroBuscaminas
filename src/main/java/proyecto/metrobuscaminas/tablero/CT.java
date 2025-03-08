package proyecto.metrobuscaminas.tablero;

import GrafoLA.GrafoLA;
import Lista.Nodo;
import java.util.Random;

public class CT {
    private GrafoLA grafo;
    private int filas;
    private int columnas;

    public CT(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grafo = new GrafoLA(filas, columnas, true);
    }

    public void generarMinas() {
        int numMinas = Math.max(filas, columnas);
        Random rand = new Random();

        for (int i = 0; i < numMinas; i++) {
            byte fila = (byte) rand.nextInt(filas);
            byte col = (byte) rand.nextInt(columnas);
            Nodo<Byte> mina = new Nodo<>((byte) '*', fila, col);
            grafo.insertaVertice(mina);
        }
    }
    
    public String[][] generarMatriz(int filas, int columnas) {
        CT controlador = new CT(filas, columnas);
        controlador.generarMinas();
        controlador.grafo.calcularAdyacencias();
        return controlador.grafo.obtenerMatrizConMinasYNumeros();
    }
}
