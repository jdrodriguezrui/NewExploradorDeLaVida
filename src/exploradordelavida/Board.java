package exploradordelavida;

import javax.swing.JPanel;
import java.util.TreeMap;

public class Board extends JPanel {
    //GUARDAR CELULAS EN UNA ESTRUCTURA X: UN TREEMAP

    int largoTablero;
    TreeMap<Position,Cell> cells = new TreeMap<>();
    
    void doTurn() {
        //COMPROBAR CADA CELULA Y HACERLES SUS COSAS

        //
    }

    //FUNCION PARA PINTAR EL COMPONENTE HACIENDO OVERRIDE A JPANEL
    void paint() {
        for (int i = 1; i < largoTablero; i++) {
            for (int j = 1; j < largoTablero; i++) {
                //PINTAR CELULA EN X Y
                // X = i*anchoCELULA
                // Y = j*anchoCELULA
                //RESUMEN: LLAMAR EL METODO PAINT A TODAS LAS CELULAS
            }
        }
    }

    void checkCell(Cell cell) {
        //Usar el treemap y las keys para comprobar si la celula nace, vive o muere... 
        //HACERLO MEDIANTE EL ATRIBUTO POSITION DE CADA CELULA
    }
}
