/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author jmwantisi
 */
public class Life implements MouseListener, ActionListener, Runnable {

    private boolean[][] cells;
    private JFrame jframe;
    private LifePanel panel;
    private Container buttonContainer;
    private JButton move, start, stop;
    private boolean running = false;

    public Life() {

        this.cells = new boolean[30][30]; //Grid of 50 by 50
        this.panel = new LifePanel(cells);
        this.buttonContainer = new Container();

        // initButtons();
        initJFrame();

    }

    private void initButtons() {

    }

    private void initJFrame() {

        this.jframe = new JFrame("Game Of Life");
        this.jframe.setSize(1000, 1000);
        this.jframe.setLayout(new BorderLayout());
        this.jframe.add(panel, BorderLayout.CENTER);
        this.panel.addMouseListener(this);
        this.panel.setBackground(Color.white);
        //buttons
        this.start = new JButton("Start Game");
        this.move = new JButton("Move");

        this.stop = new JButton("End Game");

        this.buttonContainer.setLayout(new GridLayout(1, 3));
        //this.move.addActionListener(this);
        this.buttonContainer.add(start);
        //this.start.addActionListener(this);
        this.buttonContainer.add(stop);
        //this.stop.addActionListener(this);
        this.panel.add(buttonContainer);
        JOptionPane.showMessageDialog(jframe, "Game Of Life Instructions \n\n"
                + "\nRed squares are dead cells\nSelect Grids to make cells alive\nPress start to begin playing"); //Show instructions first
        this.jframe.setVisible(true);
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jframe.setResizable(false);
        buttons();
    }

    public void buttons() {

        start.addActionListener((ActionEvent e) -> {
            if (running == false) {
                running = true;
                //String action = e.getActionCommand();
                Thread thread = new Thread(Life.this);
                thread.start();
                
            }
        });
        
        /*
        
            start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (running == false) {
                    running = true;
                    //String action = e.getActionCommand();
                    Thread thread = new Thread(Life.this);
                    thread.start();

                }
            }
        });

        
        */

        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
            }
        });
    }

    private void move() {

        boolean[][] nextCells = new boolean[cells.length][cells[0].length];
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[0].length; column++) {
                int neighborCount = 0;

                if (row > 0 && column > 0 && cells[row - 1][column - 1]) {
                    neighborCount++;//ok
                }

                if (row > 0 && cells[row - 1][column]) {
                    neighborCount++;//ok
                }

                if (row > 0 && column < cells[0].length - 1 && cells[row - 1][column + 1]) {
                    neighborCount++;//ok
                }

                if (column > 0 && cells[row][column - 1]) {
                    neighborCount++;//ok
                }

                if (column < cells[0].length - 1 && cells[row][column + 1]) {
                    neighborCount++; //ok
                }

                if (row < cells.length - 1 && column > 0 && cells[row + 1][column - 1]) {
                    neighborCount++; //ok
                }

                if (row < cells.length - 1 && cells[row + 1][column]) {
                    neighborCount++;//ok
                }

                if (row < cells.length - 1 && column < cells[0].length - 1 && cells[row + 1][column + 1]) {
                    neighborCount++; //ok
                }

                //game rules apply here
                if (cells[row][column]) {
                    if (neighborCount == 2 || neighborCount == 3) {
                        nextCells[row][column] = true;
                    } else {
                        nextCells[row][column] = false;
                    }
                } else {
                    if (neighborCount == 3) {
                        nextCells[row][column] = true;
                    } else {
                        nextCells[row][column] = false;
                    }

                }
            }
        }

        cells = nextCells;
        panel.setCells(nextCells); //check this out
        jframe.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        double width = (double) panel.getWidth() / cells[0].length;
        double height = (double) panel.getHeight() / cells.length;
        int column = Math.min(cells[0].length - 1, (int) (e.getX() / width));
        int row = Math.min(cells.length - 1, (int) (e.getY() / height));
        cells[row][column] = !cells[row][column];
        jframe.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        while (running) {
            try {
                move();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Life.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
