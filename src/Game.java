import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {
    private int ifOn = 0;  //表示的是鼠标在开始菜单时候的显示效果，共三种形态。
    private int rainbowindex=0;  //月亮旁的话 读秒
    private int N=400;    //控制星星产生的速度，几百就是几秒
    private int P=0;
    private int starindex=0;   // 和N配合实现计时，图象刷新率为100p/s
    private static JFrame frame;
    public static final int WIDTH=1024; //图象的宽高
    public static final int HEIGHT=635;
    private  int state;//状态
    public static int X;
    public static int Y;
    private static final int  START=0;
    private static final int  RUNNING=1;
    private static final int  PAUSE=2;
    private static final int  OVER=3;
    public static BufferedImage background;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    public static BufferedImage p1;
    public static BufferedImage p2;
    public static BufferedImage Quit1;
    public static BufferedImage Quit2;
    public static BufferedImage Start1;
    public static BufferedImage Start2;
    public static BufferedImage Star1;
    public static BufferedImage Star2;
    public static BufferedImage Star3;
    public static BufferedImage goods0;
    public static BufferedImage goods1;
    public static BufferedImage goods2;
    public static BufferedImage goods3;
    public static BufferedImage goods4;
    public static BufferedImage code;
    private int Score=0;
    private Timer timer;
    private int timelong=10;//时间间隔
    private Ric ric=new Ric();
    FlyObject[] flys = {};
    static {
        try {
            background = ImageIO.read(new File("src/background.png"));
            p1=ImageIO.read(new File("src/p1.png"));
            p2=ImageIO.read(new File("src/p2.png"));
            Quit1=ImageIO.read(new File("src/Quit1.png"));
            Quit2=ImageIO.read(new File("src/Quit2.png"));
            Star1=ImageIO.read(new File("src/Star1.png"));
            Star2=ImageIO.read(new File("src/Star2.png"));
            Star3=ImageIO.read(new File("src/Star3.png"));
            Start1=ImageIO.read(new File("src/Start1.png"));
            Start2=ImageIO.read(new File("src/Start2.png"));
            goods0=ImageIO.read(new File("src/一大波彩虹屁.png"));
            goods1=ImageIO.read(new File("src/你就是春风，不停吹动着我的心。.png"));
            goods2=ImageIO.read(new File("src/你是世间最可爱的小星星 我爱了整个宇宙只为了跟你碰头.png"));
            goods3=ImageIO.read(new File("src/我的天  这是什么级别的仙女。.png"));
            goods4=ImageIO.read(new File("src/how far.png"));
            pause=ImageIO.read(new File("src/pause.png"));
            gameover=ImageIO.read(new File("src/gameover.png"));
            code=ImageIO.read(new File("src/code.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MalformedURLException, JavaLayerException, FileNotFoundException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        X= (int) screenSize.getWidth();
         Y= (int) screenSize.getHeight();
         frame = new JFrame( "Get the star                                                  --by LR");
         frame.setBounds(X/2-WIDTH/2,Y/2-HEIGHT/2, WIDTH,  HEIGHT);
         frame.setIconImage(Star1);
//        frame.setSize(WIDTH, HEIGHT); // 设置大小
        Game game=new Game();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 默认关闭操作
        frame.setAlwaysOnTop(true);//始终位于最前
        frame.setResizable(false);//可重置大小
        frame.setVisible(true); //是否可视
        game.action();//运行action 线程
       Player a= new Player(Game.class.getResourceAsStream("Bruno Mars - Just the Way You Are.mp3"));
       a.play();

    }

    @Override
    public void paint(Graphics g) {    // 图象的绘制
        g.drawImage(background,0,0,null); //背景绘制
        addStar(g);//星星绘制
        addRic(g);//小人绘制
        addRainbow(g);//彩虹屁得到绘制
        addScore(g);//分数的绘制
        addState(g);//初始菜单的绘制
        if(Score>=240){////////
            addCode(g);
        }
    }

    private void addCode(Graphics g) {
        g.drawImage(code,260,80,100,100,null);
    }

    private void addState(Graphics g) {
        if(state==START){//选择栏的视觉显示
            if(ifOn==0) {
                g.drawImage(Start1, WIDTH / 2 - Start1.getWidth() / 2, HEIGHT / 2 - 20, null);
                g.drawImage(Quit1, WIDTH / 2 - Start1.getWidth() / 2, HEIGHT / 2 + 60, null);
            }else if(ifOn==1){
                g.drawImage(Start2, WIDTH / 2 - Start1.getWidth() / 2, HEIGHT / 2 - 20, null);
                g.drawImage(Quit1, WIDTH / 2 - Start1.getWidth() / 2, HEIGHT / 2 + 60, null);
            }else if(ifOn==2){
                g.drawImage(Start1, WIDTH / 2 - Start1.getWidth() / 2, HEIGHT / 2 - 20, null);
                g.drawImage(Quit2, WIDTH / 2 - Start1.getWidth() / 2, HEIGHT / 2 + 60, null);
            }
        }else if(state==PAUSE){
            g.drawImage(pause,0,0,null);
        }else if(state==OVER){
            g.drawImage(gameover,0,0,null);
        }
    }
    private void addScore(Graphics g) {
        Font font =new Font(Font.SANS_SERIF,Font.BOLD,22);
        g.setFont(font);
        g.setColor(new Color(0xF9F3FF));
        g.drawString("your score:"+Score,100,100);
        g.drawString("your Life:"+ric.getLife(),100,150);
    }

    private void addRainbow(Graphics g) {
            if(rainbowindex<=600) {
                g.drawImage(goods0, 790, 60, null);
                rainbowindex++;
            }else if(rainbowindex/1000%4==0){
                g.drawImage(goods1, 790, 60, null);
                rainbowindex++;
            }else if(rainbowindex/1000%4==1){
                g.drawImage(goods2,790,60,null);
                rainbowindex++;
            }else if(rainbowindex/1000%4==2){
                g.drawImage(goods3,790,60,null);
                rainbowindex++;
            }else if(rainbowindex/1000%4==3){
                g.drawImage(goods4,790,60,null);
                rainbowindex++;
            }
    }

    private void addRic(Graphics g) {
        g.drawImage(ric.getImage(),ric.getX(),ric.getY(),null);
    }

    private void addStar(Graphics g) {
        if(starindex>N) {                            //控制生成星星的速度 N是几百就是几秒
            if (flys.length < 20) {
                Star star = new Star();
                flys = Arrays.copyOf(flys, flys.length + 1);
                flys[flys.length - 1] = star;
            }
            for (int i = 0; i < flys.length; i++) {
                g.drawImage(flys[i].getImage(), flys[i].getX(), flys[i].getY(), null);
            }
            starindex=0;
        }else{
            for (int i = 0; i < flys.length; i++) {
                g.drawImage(flys[i].getImage(), flys[i].getX(), flys[i].getY(), null);
            }
            if(state==RUNNING)
                starindex++;
        }
    }
    public void action() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitM1 m1 = new quitM1();
                m1.run();
            }
        });
        MouseAdapter m = new MouseAdapter() {//鼠标事件
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == START) {
                    if (e.getX() > WIDTH / 2 - Start1.getWidth() / 2 && e.getX() < WIDTH / 2 - Start1.getWidth() / 2 + Start1.getWidth() && e.getY() > HEIGHT / 2 - 20 && e.getY() < HEIGHT / 2 - 20 + Start1.getHeight()) {
                        if (state == START) {
                            N=400;
                            P=0;
                            state = RUNNING;
                            ric.reLife();
                            System.out.println("run");

                        }
                    } else if (e.getX() > WIDTH / 2 - Quit1.getWidth() / 2 && e.getX() < WIDTH / 2 - Quit1.getWidth() / 2 + Quit1.getWidth() && e.getY() > HEIGHT / 2 + 60 && e.getY() < HEIGHT / 2 + 60 + Quit1.getHeight()) {
                        System.out.println("想走？？？可能吗?");
                        quit q = new quit();
                        q.run();
                    }
                } else if (state == OVER) {
                    state = START;
                    Score=0;
                    N=400;
                    P=0;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (state == RUNNING) {
                    ric.moveTo(e.getX());
                }
                if (e.getX() > WIDTH / 2 - Start1.getWidth() / 2 && e.getX() < WIDTH / 2 - Start1.getWidth() / 2 + Start1.getWidth() && e.getY() > HEIGHT / 2 - 20 && e.getY() < HEIGHT / 2 - 20 + Start1.getHeight()) {
                    ifOn = 1;
                } else if (e.getX() > WIDTH / 2 - Quit1.getWidth() / 2 && e.getX() < WIDTH / 2 - Quit1.getWidth() / 2 + Quit1.getWidth() && e.getY() > HEIGHT / 2 + 60 && e.getY() < HEIGHT / 2 + 60 + Quit1.getHeight()) {
                    ifOn = 2;
                } else {
                    ifOn = 0;
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (state == RUNNING)
                    state = PAUSE;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE)
                    state = RUNNING;
            }
        };
        this.addMouseListener(m);
        this.addMouseMotionListener(m);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) {
                    step();//进一步
                    out();//检测是否出界
                    ifbeCatch();
                    ifOver();
                    if(N>40)
                        Nless();
                    if(Score>=100&&Score<200) {
                        N = 30;
                    }else if(Score>=200){
                        N=20;
                    }
//                    System.out.println(flys.length);//
//                    System.out.println(Score+" "+ric.getLife());
                    System.out.println(N);
                }
                repaint();//重绘
            }
        }, timelong, timelong); //timelong 是时间间隔 此处为10ms
    }

    private void Nless() {
        if(state==RUNNING) {
            if (P / 20 <= 0) {
                P++;
            } else {
                N = N - 2;
                P = 0;
            }
        }
    }

    private void ifOver() {
        if(ric.getLife()<=0){
            flys=Arrays.copyOf(flys,0);
            rainbowindex=0;
            N=400;
            P=0;
            state=OVER;
        }
    }

    private void ifbeCatch() {
        for(int i=0;i<flys.length;i++){
            if(flys[i].beCatch(ric.getX(),ric.getY())){
                flys[i]=flys[flys.length-1];
                flys=Arrays.copyOf(flys,flys.length-1);
             Score++;
            }
        }
    }

    public void step(){
    ric.step();
    for(int i=0;i<flys.length;i++){
        flys[i].step();
    }
    }
    public void out(){
        for(int i=0;i<flys.length;i++){//星星出界后即时被清除
            if(flys[i].outOfBouns()){
                flys[i]=flys[flys.length-1];
                flys=Arrays.copyOf(flys,flys.length-1);
                ric.loseLife();
            }

        }
    }
}
