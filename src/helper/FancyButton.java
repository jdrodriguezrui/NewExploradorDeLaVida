/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author julia
 */
public class FancyButton extends JButton {

    public FancyButton(String text){
        super(text);

        Font font = new Font(Font.DIALOG, Font.BOLD, 15);
        this.setBorderPainted(false);
        this.setBackground(Color.GRAY);
        this.setForeground(Color.WHITE);
        this.setFont(font);
    }

    public FancyButton(String iconName, int bulto) {
        super();
        this.setPreferredSize(new Dimension(40, 40));
        java.net.URL imageURL = getClass().getResource("/resources/" + iconName + ".png");
        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            this.setIcon(icon);
        }
        this.setBorderPainted(false);
        this.setBackground(Color.GRAY);

    }

}
