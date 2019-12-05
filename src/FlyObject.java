import java.awt.image.BufferedImage;

public abstract class FlyObject {
    protected int x;//x坐标
    protected  int y;//y坐标
    protected  int width;//宽
    protected  int height;//高
    protected BufferedImage image ;//image

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public abstract boolean outOfBouns();//是否出界
    public  abstract void step();//star 走一步
    public abstract boolean beCatch(int x,int y);//是否被抓住  双参分别是 小人的 x，y



}
