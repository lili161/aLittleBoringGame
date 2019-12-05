import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

class screenCut
{
    public static String time;



    public static void cut()
            throws AWTException, IOException
    {
        Date date = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        time = dt.format(date);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle size = new Rectangle(screensize);
        Robot robot = new Robot();
        BufferedImage img = robot.createScreenCapture(size);
        File fs = new File("C:/CutPictures");
        File f = new File("C:/CutPictures/" + time + ".png");
        if (!fs.exists()) {
            fs.mkdirs();
        }
        if (!f.exists()) {
            f.mkdir();
        }
        ImageIO.write(img, "png", f);

    }
    public  static void open() throws IOException {
        Desktop.getDesktop().open(new File("C:/CutPictures"));

    }
}
