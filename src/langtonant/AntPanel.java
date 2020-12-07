/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package langtonant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author danie
 */
public class AntPanel extends javax.swing.JPanel {

    /**
     * Creates new form AntPanel
     */
    public AntPanel() {
        initComponents();
        setUp();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    //ant's current x value
    int x;
    //ant's current y value
    int y;
    //divide panel pixels using 2d array
    int[][] panel;
    //size of grid
    static int WIDTH = 500;
    static int LENGTH = 500;
    //directions as integers
    static final int ANT_UP = 0;
    static final int ANT_RIGHT = 1;
    static final int ANT_DOWN = 2;
    static final int ANT_LEFT = 3;
    //start facing up
    int dir = ANT_UP;
    //instead of white square i decided to make it grey
    Color myGrey = new Color (210,210,210);
    Timer t1 = new Timer(1, new TimerListener());
    
    public void setUp(){
        //start at middle
        x=250;
        y=250;
        //set every value in 2d array to 1 (white)
        panel = new int[WIDTH][LENGTH];
        for(int i=0; i<WIDTH; i++){
            for(int j=0; j<LENGTH; j++){
                panel[i][j] = 1;
            }
        }
    }
    
    public void turnRight(){
        //++ as the directions are now integers
        dir++;
        //if after adding 1 to the direction it is over 3, the int value becomes 0
        if(dir>ANT_LEFT){
            dir = ANT_UP;
        }
    }
    
    public void turnLeft(){
        //-- as the directions are now integers
        dir--;
        //if after subbing 1 to the direction it is under 0, the int value becomes 3
        if(dir<ANT_UP){
            dir = ANT_LEFT;
        }
    }
    
    public void moveForward(){
        //change in x and y coordinate based on direction
        if(dir==ANT_UP){
            y--;
        }
        else if(dir==ANT_RIGHT){
            x++;
        }
        else if(dir==ANT_DOWN){
            y++;
        }
        else if(dir==ANT_LEFT){
            x--;
        }
        
        //ant goes to other side if past boundary
        if(x>WIDTH-1){
            x = 0;
        }
        else if(x<0){
            x = WIDTH-1;
        }
        
        if(y>LENGTH-1){
            y = 0;
        }
        else if(y<0){
            y = LENGTH-1;
        }
    }
    
    public void antMove(){
        //check current color pixel ant is on
        int current = panel[x][y];
        //if white, turn right, change color, move forward
        if(current==1){
            turnRight();
            panel[x][y] = 0;
            moveForward();
        }
        //if black, turn left, change color, move forward
        else if(current==0){
            turnLeft();
            panel[x][y] = 1;
            moveForward();
        }
    }
    
    private class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                repaint();
        }
    }
    
    public void animate(){
        t1.start();
    }
    
    public void stopAnimate(){
        t1.stop();
    }
    
    public void paintComponent(Graphics g){
        //color the pixel with correct color during ant movement
        if(panel[x][y]==1){
            g.setColor(myGrey);
        }
        else if(panel[x][y]==0){
            g.setColor(Color.black);
        }
        g.fillRect(x, y, 1, 1);
        antMove();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
