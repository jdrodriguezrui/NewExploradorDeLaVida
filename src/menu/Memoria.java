/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import exploradordelavida.logic.Cell;
import exploradordelavida.logic.Position;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TreeMap;

/**
 *
 * @author EDER H
 */
public class Memoria {
    private TreeMap<Position , Cell> memorisa;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setMemorisa(TreeMap<Position, Cell> memorisa) {
        this.memorisa = memorisa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Memoria(){
        this.memorisa= new TreeMap<>();
    }
    public void abrir() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fINombre = new FileInputStream(this.nombre);
        ObjectInputStream oILector = new ObjectInputStream(fINombre);
        TreeMap<Position , Cell> aux = (TreeMap) oILector.readObject();
        this.memorisa=aux;
        oILector.close();
    }
}
