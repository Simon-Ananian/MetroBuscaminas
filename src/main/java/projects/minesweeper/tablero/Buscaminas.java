/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projects.minesweeper.tablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

/**
 *
 * @author g
 */
public class Buscaminas extends JFrame {
    private JButton[][] botones;
    private JPanel panel;
    private boolean modoBandera = false;
    private int banderasColocadas = 0;
    private boolean[][] banderas;
    private int cantidadMinas;
    private boolean[][] visitado;
    private String[][] matriz;
    private boolean CargarJuego = false;
    ImageIcon IconoBandera = new ImageIcon(getClass().getResource("/FTP_Images/bandera.png"));
    ImageIcon IconoMina = new ImageIcon(getClass().getResource("/FTP_Images/mine.png"));
    
    public Buscaminas(){}
    
    public Buscaminas(int cantidadFilas, int cantidadColumnas, int cantidadMinas) {
        final JLabel labelMinas;
        this.cantidadMinas = cantidadMinas;
        this.banderas = new boolean[cantidadFilas][cantidadColumnas];
        this.visitado = new boolean[cantidadFilas][cantidadColumnas];
        setTitle("Buscaminas");
        setSize(60 * cantidadColumnas + 80, 60 * cantidadFilas + 120); // Ajustar tamaño dinámicamente
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout()); // Se usa un layout para manejar los componentes
        // Panel superior para mostrar la información
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen alrededor del panel
        labelMinas = new JLabel("Cantidad de Minas: " + cantidadMinas);
        infoPanel.add(labelMinas);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        // Crear el botón para guardar el juego
        JButton botonGuardar = new JButton("Guardar Juego");
        botonGuardar.setBackground(Color.GREEN);
        botonGuardar.setForeground(Color.BLACK);
        botonGuardar.setFocusPainted(false);
        botonGuardar.setBorderPainted(true);
        botonGuardar.setContentAreaFilled(true);
        botonGuardar.setPreferredSize(new Dimension(150, 30));
        
        // Acción del botón Guardar
        botonGuardar.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                guardarJuego(cantidadFilas, cantidadColumnas); // Llamada al método que guarda el juego
            }
        });

        // Agregar el botón Guardar al panel de información
        panelBotones.add(botonGuardar);

        // Crear Boton Bandera
        JButton botonBandera = new JButton("Bandera");
        botonBandera.setIcon(IconoBandera);
        botonBandera.setPreferredSize(null); // Permite que el botón tome solo el espacio necesario
        botonBandera.setMargin(new Insets(5, 5, 5, 5));
        botonBandera.setBackground(Color.YELLOW);
        botonBandera.setForeground(Color.BLACK);
        botonBandera.setFocusPainted(false);
        botonBandera.setBorderPainted(true);
        botonBandera.setContentAreaFilled(true);
        botonBandera.setPreferredSize(new Dimension(150, 30));
        
        // Acción del botón
        botonBandera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modoBandera = !modoBandera; // Alterna entre modo normal y modo bandera
                botonBandera.setBackground(modoBandera ? Color.RED : Color.YELLOW); // Indica visualmente el estado
            }
        });

        // Agregar el botón al panel de información
        panelBotones.add(botonBandera);
        
        // Agregar el panel de información a la ventana
        add(infoPanel, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.SOUTH);
        
        // Crear la matriz de botones
        dibujarMatrizBotones(cantidadFilas, cantidadColumnas, cantidadMinas);

        // Asegurar que el panel tiene los botones antes de agregarlo
        if (panel != null) {
            add(panel, BorderLayout.CENTER);
        }

        this.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

        
    private int ContarTodasLasMinas(String[][] matriz) { // Mostrar todas las minas después de perder juego
        int minas = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].equals("*")) {
                    minas++; // Muestra la mina
                }
            }
        }
        return minas;
    }
    
    
    private void revelarTodasLasMinas(String[][] matriz) { // Mostrar todas las minas después de perder juego
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                if (matriz[i][j].equals("*")) {
                    if(!banderas[i][j]){
                        botones[i][j].setIcon(IconoMina); // Muestra la mina
                        botones[i][j].setText("");
                    }
                }
            }
        }
        for (JButton[] botone1 : botones) { // se desabilitan los botones (perdiste)
            for (JButton botone : botone1) {
                botone.setEnabled(false);
            }
        }
    }
    
    private void revelarTodasLasCeldas(String[][] matriz) { // Mostrar todas las celdas, si son banderas las muestra
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                if(!banderas[i][j]){
                    botones[i][j].setText(matriz[i][j]); // Muestra la mina 
                }
            }
        }
        for (JButton[] botone1 : botones) { // se desabilitan los botones (perdiste)
            for (JButton botone : botone1) {
                botone.setEnabled(false);
            }
        }
    }
    
    
    private void revelarCeldasAdyacentes(String[][] matriz, int x, int y) {
        if (visitado[x][y] || banderas[x][y]) {
            return;  // Si ya fue visitada, salimos
        }
  
        visitado[x][y] = true;
        
        // Deshabilitar el botón para evitar más clics
        botones[x][y].setEnabled(false); // Deshabilitar botón
        
        // Si la casilla es "0", revelamos las celdas adyacentes recursivamente
        if (matriz[x][y].equals("0")) {
            botones[x][y].setText(""); // Si es 0, no mostrar nada
            // Explorar las celdas adyacentes (vecinos)
            for (int i = -1; i <= 1; i++) {
                if(x + i >= 0 && x + i < matriz.length){
                    for (int j = -1; j <= 1; j++) {
                        // Evitar salir de los límites de la matriz
                        if (y + j >= 0 && y + j < matriz[0].length) {
                            // Llamada recursiva para descubrir las celdas adyacentes
                            revelarCeldasAdyacentes(matriz, x + i, y + j);
                        }
                    }
                }
            }
        } else {
            botones[x][y].setText(matriz[x][y]); // Revelar el contenido
        }
    }
    
    public int contarCeldasVisitadas(boolean[][] visitado) {
        int contador = 0;
        for (int i = 0; i < visitado.length; i++) {
            for (int j = 0; j < visitado[i].length; j++) {
                if (visitado[i][j]) {
                    contador++;
                }
            }
        }
        return contador;
    }
    
    public boolean validarBanderasMinas (String[][] matriz){
        for (int i = 0; i < banderas.length; i++) {
            for (int j = 0; j < banderas[i].length; j++) {
                if ("*".equals(matriz[i][j])) {
                    if (!banderas[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void ganarJuego (String[][] matriz, int filas, int columnas, int minas){
        if ((contarCeldasVisitadas(visitado) == (filas*columnas - minas)) || validarBanderasMinas(matriz)){
            JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado el juego 🎉", "Victoria", JOptionPane.INFORMATION_MESSAGE);
            revelarTodasLasCeldas(matriz);
        }
    }
    
    private void manejarBandera(int x, int y) {
        if (!botones[x][y].isEnabled()) {
            return; // No poner banderas en casillas reveladas
        }
        if (banderas[x][y]) {
            botones[x][y].setText(String.valueOf((char) ('A' + y)) + (x + 1));
            botones[x][y].setIcon(null);
            banderas[x][y] = false;
            banderasColocadas--;
        } else if (banderasColocadas < cantidadMinas) {
            botones[x][y].setIcon(IconoBandera);
            botones[x][y].setText("");
            banderas[x][y] = true;
            banderasColocadas++;
        }
    }
    
    private void guardarJuego(int filas, int columnas) {
        try {
            String nombre = "";
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Guardar Juego");  // Título del cuadro de diálogo
            int userSelection = file.showSaveDialog(this); // Mostramos el diálogo

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File guarda = file.getSelectedFile();
                if (guarda != null) {
                // Si no tiene la extensión, la agregamos
                if (!guarda.getName().endsWith(".csv")) {
                    guarda = new File(guarda.getAbsolutePath() + ".csv");
                }

                // FileWriter para escribir en el archivo
                FileWriter save = new FileWriter(guarda);
                BufferedWriter writer = new BufferedWriter(save);

                // Escribir los encabezados
                writer.write("Filas: " + filas + ", Columnas: " + columnas);
                writer.newLine();
                writer.write("Matriz, Visitado, Banderas");
                writer.newLine();

                // Guardar los datos de la matriz y los arreglos
                for (int i = 0; i < filas; i++) {
                    StringBuilder matrizFila = new StringBuilder();
                    StringBuilder visitadoFila = new StringBuilder();
                    StringBuilder banderasFila = new StringBuilder();
                    for (int j = 0; j < columnas; j++) {
                        matrizFila.append(matriz[i][j]);
                        visitadoFila.append(visitado[i][j] ? "1" : "0"); // Representación booleana
                        banderasFila.append(banderas[i][j] ? "1" : "0"); // Representación booleana
                        if (j < columnas - 1) {
                            matrizFila.append(",");
                            visitadoFila.append(",");
                            banderasFila.append(",");
                        }
                    }
                    // Escribir cada fila de la matriz, visitado y banderas
                    writer.write(matrizFila.toString() + ", " + visitadoFila.toString() + ", " + banderasFila.toString());
                    writer.newLine();
                }
                // Cerrar el archivo
                writer.close();
                JOptionPane.showMessageDialog(null,
                    "El juego se ha guardado exitosamente.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                "Su archivo no se ha guardado.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void cargarJuego() {
        try {
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Cargar Juego");
            int userSelection = file.showOpenDialog(this); // Mostramos el diálogo

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File archivo = file.getSelectedFile();
                if (archivo != null) {
                    BufferedReader reader = new BufferedReader(new FileReader(archivo));

                    // Leer la primera línea que contiene las filas y columnas
                    String line = reader.readLine();
                    if (line != null && line.startsWith("Filas:")) {
                        String[] datos = line.split(", ");
                        int filas = Integer.parseInt(datos[0].split(": ")[1]);
                        int columnas = Integer.parseInt(datos[1].split(": ")[1]);

                        // Crear las matrices según las filas y columnas leídas
                        matriz = new String[filas][columnas];
                        visitado = new boolean[filas][columnas];
                        banderas = new boolean[filas][columnas];

                        // Leer la línea de encabezado "Matriz, Visitado, Banderas"
                        reader.readLine();

                        // Leer las líneas con los datos de la matriz, visitado y banderas
                        for (int i = 0; i < filas; i++) {
                            line = reader.readLine();
                            if (line != null) {
                                String[] partes = line.split(", ");
                                String[] matrizFila = partes[0].split(",");
                                String[] visitadoFila = partes[1].split(",");
                                String[] banderasFila = partes[2].split(",");
                                for (int j = 0; j < columnas; j++) {
                                    matriz[i][j] = matrizFila[j].trim();
                                    visitado[i][j] = visitadoFila[j].trim().equals("1");
                                    banderas[i][j] = banderasFila[j].trim().equals("1");
                                }
                            }
                        }
                        reader.close();
                        JOptionPane.showMessageDialog(null,
                        "El juego se ha cargado exitosamente.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                        CargarJuego = true;
                                                                
                        final JLabel labelMinas;
                        this.cantidadMinas = ContarTodasLasMinas(matriz);
                        setTitle("Buscaminas");
                        setSize(60 * filas + 80, 60 * columnas + 120); // Ajustar tamaño dinámicamente
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
                        setLocationRelativeTo(null); // Centrar la ventana
                        setLayout(new BorderLayout()); // Se usa un layout para manejar los componentes
                        // Panel superior para mostrar la información
                        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
                        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen alrededor del panel
                        labelMinas = new JLabel("Cantidad de Minas: " + cantidadMinas);
                        infoPanel.add(labelMinas);
                        JPanel panelBotones = new JPanel();
                        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
                        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        // Crear el botón para guardar el juego
                        JButton botonGuardar = new JButton("Guardar Juego");
                        botonGuardar.setBackground(Color.GREEN);
                        botonGuardar.setForeground(Color.BLACK);
                        botonGuardar.setFocusPainted(false);
                        botonGuardar.setBorderPainted(true);
                        botonGuardar.setContentAreaFilled(true);
                        botonGuardar.setPreferredSize(new Dimension(150, 30));
        
                        // Acción del botón Guardar
                        botonGuardar.addActionListener(new ActionListener() {
                        @Override
                            public void actionPerformed(ActionEvent e) {
                                guardarJuego(filas, columnas); // Llamada al método que guarda el juego
                            }
                        });
                        // Agregar el botón Guardar al panel de información
                        panelBotones.add(botonGuardar);
                        // Crear Boton Bandera
                        JButton botonBandera = new JButton("Bandera");
                        botonBandera.setIcon(IconoBandera);
                        botonBandera.setPreferredSize(null); // Permite que el botón tome solo el espacio necesario
                        botonBandera.setMargin(new Insets(5, 5, 5, 5));
                        botonBandera.setBackground(Color.YELLOW);
                        botonBandera.setForeground(Color.BLACK);
                        botonBandera.setFocusPainted(false);
                        botonBandera.setBorderPainted(true);
                        botonBandera.setContentAreaFilled(true);
                        botonBandera.setPreferredSize(new Dimension(150, 30));
        
                        // Acción del botón
                        botonBandera.addActionListener(new ActionListener() {
                        @Override
                            public void actionPerformed(ActionEvent e) {
                                modoBandera = !modoBandera; // Alterna entre modo normal y modo bandera
                                botonBandera.setBackground(modoBandera ? Color.RED : Color.YELLOW); // Indica visualmente el estado
                            }
                        });
                        // Agregar el botón al panel de información
                        panelBotones.add(botonBandera);
                        // Agregar el panel de información a la ventana
                        add(infoPanel, BorderLayout.NORTH);
                        add(panelBotones, BorderLayout.SOUTH);
                        // Crear la matriz de botones
                        dibujarMatrizBotones(filas, columnas, this.cantidadMinas);
                        // Asegurar que el panel tiene los botones antes de agregarlo
                        if (panel != null) {
                            add(panel, BorderLayout.CENTER);
                        }
                        this.setVisible(true);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                "El archivo no se ha podido cargar.",
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    private void dibujarMatrizBotones(int filas, int columnas, int minas) {
        panel = new JPanel(new GridLayout(filas, columnas, 2, 2)); // Usar la variable de clase `panel`
        botones = new JButton[filas][columnas];
        boolean perderJuego = false;
        boolean ganarJuego = false;
        
        if(!CargarJuego){
            CT controlador = new CT(filas, columnas);
            String[][] matriz = controlador.generarMatriz(filas, columnas, minas);
            this.matriz = matriz;
        }
         
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                final int x = i;
                final int y = j;  
                final String valorCelda = matriz[i][j];
                
                // Convertir índice de columna (0=A, 1=B, ...) y fila (0=1, 1=2, ...)
                char columnaLetra = (char) ('A' + j); // Al sumar j a 'A', se obtiene el siguiente carácter en la secuencia del abecedario (ASCII).
                String identificador = columnaLetra + String.valueOf(i + 1); //concatena la letra de la columna con el numero de fila
              
                botones[i][j] = new JButton(); // Agregar botón al panel
                botones[i][j].setText(identificador); 
                
                if(CargarJuego){
                    if (visitado[x][y]){
                        if (valorCelda.equals("*")) { // Si la casilla es una mina
                            botones[x][y].setText("");
                            botones[x][y].setEnabled(false);
                            perderJuego = true;
                        } else if (valorCelda.equals("0")) {
                            botones[x][y].setText("");
                            botones[x][y].setEnabled(false);
                        } else {
                            botones[x][y].setText(valorCelda);
                            botones[x][y].setEnabled(false);
                        }
                    } else if (banderas [x][y]) {
                        botones[x][y].setIcon(IconoBandera);
                        botones[x][y].setText("");
                        banderasColocadas++;
                    }
                }     
               
                panel.add(botones[i][j]);
                
                botones[i][j].addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) { //Accion de dar clic a la casilla
                        if(modoBandera){
                            manejarBandera(x, y);
                            ganarJuego(matriz, filas, columnas, minas);
                        } else if (!banderas[x][y]){
                            if (valorCelda.equals("*")) { // Si la casilla es una mina (perdiste)
                                visitado[x][y] = true;
                                revelarTodasLasMinas(matriz); // Revelar todas las minas
                                JOptionPane.showMessageDialog(
                                    null, 
                                    "¡Perdiste el juego!", 
                                    "Game Over", 
                                    JOptionPane.ERROR_MESSAGE
                                );
                            } else if (valorCelda.equals("0")) { // Si la casilla es un "0"
                                revelarCeldasAdyacentes(matriz, x, y); // Descubrir celdas adyacentes
                                ganarJuego(matriz, filas, columnas, minas);
                            } else {
                                visitado[x][y] = true;
                                botones[x][y].setText(valorCelda); // Revelar el contenido al hacer clic
                                botones[x][y].setEnabled(false); // Deshabilitar botón después de revelarlo
                                ganarJuego(matriz, filas, columnas, minas);
                            }
                        }
                    }
                });
            }
        }
        if(CargarJuego){
            if(perderJuego){
                revelarTodasLasMinas(matriz); // Revelar todas las minas
                JOptionPane.showMessageDialog(
                null, 
                "¡Perdiste el juego!", 
                "Game Over", 
                JOptionPane.ERROR_MESSAGE);
            }
            ganarJuego(matriz, filas, columnas, minas);
        }
        CargarJuego = false;
    }
}
