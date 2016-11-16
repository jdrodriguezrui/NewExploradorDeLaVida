/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author julia
 */
public class ComboBoxRenderer extends BasicComboBoxRenderer {

    public ComboBoxRenderer() {
        super();
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);       
        switch (((Integer) value)) { 
            case 1:
                this.setBackground(Color.BLACK);
                break;
            case 2:
                this.setBackground(Color.GREEN);
                break;
            case 3:
                this.setBackground(Color.RED);
                break;
        }
        return this;
    }

}
