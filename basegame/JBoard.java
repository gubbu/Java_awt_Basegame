package basegame;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Toolkit;
import javax.swing.JFrame;

public abstract class JBoard extends JPanel implements ActionListener{
    Timer t;
    int width;
    int height;
    boolean mode = false;

   public JBoard(int width, int height, int fps){
       this.width = width;
       this.height = height;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        int mspf = 1000/fps;
        System.out.println("milliseconds per frame:" + mspf);
        //delay: 25 ms
        t = new Timer(25, this);
        t.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t){
            //System.out.print("Timer called");
            repaint();
        }
        
    }

    protected abstract void redraw_and_update(Graphics g);

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.redraw_and_update(g);
        //removing this line results in bad timing on Linux:
        Toolkit.getDefaultToolkit().sync();
    }

    public void show_in_jframe(){
        JFrame mainframe = new JFrame();
        mainframe.setResizable(false);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.add(this);
        mainframe.pack();
        mainframe.setVisible(true);
    }

}