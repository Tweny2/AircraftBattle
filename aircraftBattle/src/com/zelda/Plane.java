package com.zelda;


import java.awt.*;
import java.awt.event.KeyEvent;

//飞机类
public class Plane extends GameObject {

    boolean left,up,right,down;
    boolean live = true;
    @Override
    public void drawMySelf(Graphics g) {
        if(live){
            g.drawImage(img, (int) x, (int) y, null);//特别注意，飞机类自己画自己

            if (left) {
                x -= speed;
            }
            if (right) {
                x += speed;
            }
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }

        }else{}





    }

    public void addDirection(KeyEvent e) {


        switch ((e.getKeyCode())) {
            case KeyEvent.VK_LEFT:
                left = true;
                System.out.println("向左飞行");
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                System.out.println("向右飞行");
                break;
            case KeyEvent.VK_UP:
                up = true;
                System.out.println("向上飞行");
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                System.out.println("向下飞行");
                break;
        }
    }
    public void minusDirection(KeyEvent e) {
        switch ((e.getKeyCode())) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    public Plane(Image img, int x, int y, int speed) {

        super(img, x, y, speed);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
}
