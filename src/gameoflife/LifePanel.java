/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author jmwantisi
 */
public class LifePanel extends JPanel {
    
    private boolean [][] cells;
    private double width;
    private double height;
    

    public LifePanel(boolean [][] in) {
        
        this.cells = in;
       
        
    }
    
    public void setCells(boolean[][] in){
        this.cells = in;
    }
    
    @Override
    public void paintComponent(Graphics g){
    
        super.paintComponent(g);
        width  = (double) this.getWidth() / cells[0].length;
        height  = (double)this.getHeight() / cells.length;
        
        g.setColor(Color.BLACK);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
              
                //if true
                if(cells[i][j]){
                    g.fillRect((int)(j*width), (int)(i*height), (int)width+1, (int)height+1);
                }
            }
        }
        //xcordinates
        for (int i = 0; i < cells[0].length + 1; i++) {
            
            g.drawLine((int)Math.round(i*width), 0, (int)Math.round(i*width), this.getHeight());
        }
        
        //y cordincates
        for (int i = 0; i < cells.length + 1; i++) {
            
            g.drawLine(0, (int)Math.round(i*height), this.getWidth(), (int)Math.round(i*height));
        }
    }

  
    
    
    
}
