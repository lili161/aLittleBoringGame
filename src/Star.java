import java.awt.image.BufferedImage;
import java.util.Random;

public class Star extends FlyObject{
    private int xspeed=1;//横向移动速度
    private  int yspeed=2;//纵向移动速度


    public Star(){
        Random r = new Random();
        int r1=r.nextInt(3);
        if(r1==0) {
            this.image = Game.Star1;
            this.width=Game.Star1.getWidth();
            this.height=Game.Star1.getHeight();
        }else if(r1==1){
            this.image=Game.Star2;
            this.width=Game.Star2.getWidth();
            this.height=Game.Star2.getHeight();
        }else if(r1==2){
            this.image=Game.Star3;
            this.width=Game.Star3.getWidth();
            this.height=Game.Star3.getHeight();
        }
        y=-height;
        x=r.nextInt(Game.WIDTH-width);
        Random a = new Random();
        if(a.nextInt(2)==0)
            xspeed=xspeed;
        else
            xspeed=-xspeed;
    }
    @Override
    public boolean outOfBouns() {//星星是否出界
        return y>Game.HEIGHT-height;
    }

    @Override
    public void step() { //星星移动
        y+=yspeed;
        x+=xspeed;
        if(x<=0){
            xspeed=-xspeed;
        }else if(x>=Game.WIDTH-width){
            xspeed=-xspeed;
        }
    }

    @Override
    public boolean beCatch(int x,int y) {//判断是否被抓住
        if(this.x>x-this.width&&this.x<x+Game.p1.getWidth()&&this.y>y-this.height&&this.y<y+Game.p1.getHeight()-this.height){
            return  true;
        }else {
            return false;
        }
    }


}
