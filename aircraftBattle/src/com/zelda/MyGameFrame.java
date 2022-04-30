package com.zelda;
/**
 * 飞机大战 v1.09
 *   新增功能：
 *        1.增加计时器功能
 */



import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import static com.zelda.GameUtil.*;



public class MyGameFrame extends Frame {
    Image bgImg = GameUtil.getImage("images/bg.png");
    Image planeImg = GameUtil.getImage("images/64plane.png");

    Plane plane = new Plane(planeImg,400,300,24);
    Shell[] shells = new Shell[50];
    Explode explode; //声明炮弹

    //设置计时器变量
    Date startTime = new Date();
    Date endTime;
    int period; //玩了多少秒


    //画图片的方法
    @Override
    public void paint(Graphics g) {
        //自动被调用，g相当于一只画笔
        g.drawImage(bgImg,0,0,FRAME_WIDTH,FRAME_HEIGHT,null);
        plane.drawMySelf(g); //画飞机

        //画出所有的炮弹
        for(int i=0;i<shells.length;i++){

            if(shells[i]!=null) {
                shells[i].drawMySelf(g);

                //飞机和炮弹的碰撞检测！！！
                boolean peng = shells[i].getRect().intersects(plane.getRect());
                if (peng) {
                    plane.live = false;
                    endTime = new Date();
                    System.out.println("お前はもう死んでいる！！！");

                    period = (int)((endTime.getTime()-startTime.getTime())/1000);

                    if(explode==null){
                        explode =new Explode(plane.x,plane.y);
                    }
                    explode.draw(g);


                }

            }

        }

        //画出计时器
        if(!plane.live){
            printInfo(g,"游戏时间："+period+"秒",
                    50,200,200,Color.YELLOW);
        }


    }
    //计时器方法
    public void printInfo(Graphics g,String str,int size,int x, int y,Color color){
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();

        Font f = new Font("宋体",Font.BOLD,size);
        g.setFont(f);
        g.setColor(color);
        g.drawString(str,x,y);

        g.setFont(oldFont);
        g.setColor(oldColor);

    }
    //初始化窗口方法
    public  void launchFrame(){
        this.setTitle("aircraftBattle");
        this.setVisible(true);//窗口默认不可见，使其可见
        this.setSize(800,600);
        this.setLocation(300,300);
        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //启动窗口绘制线程
        new PaintThread().start();
        //启动键盘监听
        this.addKeyListener(new KeyMonitor());
        //初始化50发炮弹
        for(int i=0;i<shells.length;i++){
            shells[i] = new Shell();
        }
    }
    //键盘监听内部类方法
    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }
    //重画线程方法
    class PaintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(40);  //1s=1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        MyGameFrame frame = new MyGameFrame();
        frame.launchFrame();
    }
    private  Image offScreenImage = null;
    public void update(Graphics g){
        if(offScreenImage == null)
            offScreenImage = this.createImage(FRAME_WIDTH,FRAME_HEIGHT); //这是游戏窗口

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

}
