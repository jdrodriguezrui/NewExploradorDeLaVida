package menu;

import exploradordelavida.logic.Board;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author EDER H
 */
public class Memoria implements java.io.Serializable{
    private Board memorisa;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setMemorisa(Board memorisa) {
        this.memorisa = memorisa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Memoria(){
        this.memorisa= new Board(Board.DEFAULT_SIZE);
    }

    public Board getMemorisa() {
        return memorisa;
    }
    //---------------Metodo para abrir partidas-------------------------------- 
    public void abrir() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fINombre = new FileInputStream(this.nombre);
        ObjectInputStream oILector = new ObjectInputStream(fINombre);
        this.memorisa =  (Board) oILector.readObject();
        
        oILector.close();
        fINombre.close();
    }
    //---------------Metodo para guardar partidas----------------------------
    public void guardar() throws IOException{
        FileOutputStream fileStream = new FileOutputStream(this.nombre + ".EJI");
        ObjectOutputStream OS = new ObjectOutputStream(fileStream);
        OS.writeObject(this.memorisa);
        OS.close();
        fileStream.close();
            
        }
    
    }

