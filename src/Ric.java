import java.awt.image.BufferedImage;

public class Ric extends roadObject {
    private int index=0;
    private int x1=512;
    private int Life=10; //小人有10条命

    public Ric(){
        this.width=Game.p1.getWidth();
        this.height=Game.p1.getHeight();
        this.x=Game.WIDTH/2;
        this.y=Game.HEIGHT-145;
        this.image=Game.p1;
    }
    public void moveTo(int x){
        this.x=x-width/2;
    }

    @Override
    public boolean outOfBouns() {
        return false;
    }

    @Override
    public void step() {
        if(Math.abs(x-x1)>4){
            if((index++%2)==0){
                this.image=Game.p2;
            }else{
                this.image=Game.p1;
            }
        }
        x1=x;
    }

    public int getLife() { //获取生命条数
        return Life;
    }
    public void reLife(){//重新游戏 10 条命
        Life=10;
    }
    public void loseLife() {  //损失一条命
        Life--;
    }
}
