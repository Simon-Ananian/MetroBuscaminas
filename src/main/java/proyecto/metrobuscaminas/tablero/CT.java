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
        String[] posicionesMinas = new String[numMinas];
        
        for (int i = 0; i < numMinas; i++) {
            byte fila = (byte) rand.nextInt(filas);
            byte col = (byte) rand.nextInt(columnas);
            String clave = fila + "," + col;
            
            boolean repetida = false;
            for (int j = 0; j < i; j++) { // Se recorre hasta el índice actual
                if (posicionesMinas[j].equals(clave)) {
                    repetida = true;
                    break;
                }
            }
            
            if(repetida){
                i--; // Si la posición ya existe, restamos 1 a `i` para repetir la iteración
            } else {
                posicionesMinas[i] = clave; // Guardamos la nueva posición en el arreglo
                Nodo<Byte> mina = new Nodo<>((byte) '*', fila, col);
                grafo.insertaVertice(mina); // Insertamos la mina en el grafo
            }
  
        }
    }
    
    public String[][] generarMatriz(int filas, int columnas) {
        CT controlador = new CT(filas, columnas);
        controlador.generarMinas();
        controlador.grafo.calcularAdyacencias();
        return controlador.grafo.obtenerMatrizConMinasYNumeros();
    }
}
