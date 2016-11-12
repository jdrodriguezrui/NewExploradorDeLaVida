package exploradordelavida;

import java.awt.Graphics;

//UBICAR LAS CELULAS EN UN TREEMAP EN EL TABLERO
public class Cell {

    //LAS DIFERENTES ESPECIES HEREDAN DE CELL (?)
    static int desfaultTurnsLeft = 0;
    int turnsLeft = 0;
    //Los valores de especie podría considerarse Estáticos
    private boolean state;
    //VALOR PARA USAR EN EL TREEMAP DE BOARD, UTIL EN LA COMPROBACION DE LAS CELULAS
    private Position position;
    private int specie;
    
    //Debería cell saber de sus vecinos, o es tarea de BOARD ?!?!? 
    //Posiblemente de board... porque las muertas no tienen especie!!
    //USAR CLASE POSITION (?)
    private int x;
    private int y;

    public void bringToLife(int specie) {
        this.state = true;
        this.specie = specie;
    }

    public void turnToDeath() {
        this.state = false;
        this.specie = 0;
    }

    void paint(Graphics graphics, int x, int y) {
        //paint rectangulo rellenito con borde de tamaño bla
        //DEPENDE TANTO DE LA ESPECIE COMO DEL ESTADO DE LA CELULA

        //DEJAR PARA LUEGO PLS
    }

    public Position getPosition() {
        return this.position;
    }
}
