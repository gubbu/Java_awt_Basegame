import javax.swing.JFrame;

import basegame.JBoard;

import java.awt.Color;
import java.awt.Graphics;


public class BaseGame {
    JFrame gameframe;

    public static class HorizontalMovingSquare{
        private int x = 0;
        private int y = 0;
        private int size = 20;
        private boolean mode = false;
        private int screen_width;

        public HorizontalMovingSquare(int x, int y,  int size, int screen_width){
            this.x = x;
            this.y = y;
            this.size = size;
            this.screen_width = screen_width;
            if (x<0 || x+size>screen_width){
                throw new IllegalArgumentException("square must be inside the screen rectangle, that is given by: (0, 0, screen_width, height) but x and size dont allow for that.");
            }
        }

        public int update(){
            if (mode){
                x++;
                if (x+size > screen_width){
                    mode = false;
                }
            }else{
                x--;
                if (x < 0){
                    mode = true;
                }
            }
            return x;
        }

        public int getY(){
            return this.y;
        }
    }

    public static class MovingSquare extends JBoard
    {
        int x = 0;
        boolean mode = true;
        int center_x, center_y, circle_radius;

        public MovingSquare(int width, int height, int fps) {
            super(width, height, fps);
            center_x = width/2;
            center_y = height/2;
            circle_radius = Math.min(width, height);
        }

        @Override
        protected void redraw_and_update(Graphics g){
            g.setColor(Color.RED);
            g.drawRect(x, 0, 20, 20);
            if(mode){
                x++;
                if(x+20 > this.getWidth()){
                    mode = !mode;
                }
            }else{
                x--;
                if(x<0){
                    mode = !mode;
                }
            }
        }
    }

    public static void main(String[] args) {
        new MovingSquare(200, 200, 10).show_in_jframe();
    }
}
