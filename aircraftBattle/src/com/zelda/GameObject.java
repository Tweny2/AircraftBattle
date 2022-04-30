package com.zelda;

//游戏物体的根类


import java.awt.*;

public class GameObject {
    Image img;  //对应的图片
    int x,y;    //坐标
    int speed;  //物体移动速度
    int width,height;   //物体的宽度和高度

    public void drawMySelf(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }

    //返回该物体所在的矩形。便于后续的碰撞检测
    public Rectangle getRect(){

        return  new Rectangle(x,y,width,height);
    }

    public GameObject(){}


    public GameObject(Image img, int x, int y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image img, int x, int y, int speed) {
        this(img,x,y);
        this.speed = speed;
    }

    public GameObject(Image img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public GameObject(Image img) {
        this.img = img;
        if(this.img!=null){
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }
}