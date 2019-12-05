

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class quit {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
			public void run() {
				try {
					quit window = new quit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public quit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
	
	

		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);
		frame.setBounds(Game.X/2-250, Game.Y/2-110,500, 220);/////////λ��Ҫ���ڸ�����
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u60F3\u8DD1\uFF1F\uFF1F\u4F60\u89C9\u5F97\u53EF\u80FD\u5417\uFF1F");
		label.setFont(new Font("΢���ź�", Font.BOLD, 30));
		label.setBounds(69, 39, 387, 36);
		frame.getContentPane().add(label);
		
		JButton bt1 = new JButton("\u53EF\u80FD");
		JButton bt2 = new JButton("\u4E0D\u53EF\u80FD");
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		JLabel imageLab = new JLabel("\u70B9\u4E0D\u5230");
		imageLab.setFont(new Font("΢���ź�", Font.BOLD, 23));
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt1.setBounds(46, 110, 123, 43);
	    		bt2.setBounds(333, 110, 123, 43);
				imageLab.setVisible(false);
			}
		});
		bt1.setForeground(new Color(0, 0, 0));
		bt1.setBackground(SystemColor.activeCaption);
		bt1.setBounds(46, 110, 123, 43);
		frame.getContentPane().add(bt1);
		
		bt2.setBackground(SystemColor.activeCaption);
		bt2.setForeground(Color.BLACK);
		bt2.setBounds(333, 110, 123, 43);
		frame.getContentPane().add(bt2);
		
		
		imageLab.setHorizontalAlignment(SwingConstants.CENTER);
		imageLab.setForeground(SystemColor.infoText);
		imageLab.setIcon(new ImageIcon("src/doge.png")); ////doge
		imageLab.setBounds(172, 67, 158, 62);
		imageLab.setVisible(false);
		frame.getContentPane().add(imageLab);
	    bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e0) {
				if(e0.getX()>0&&e0.getX()<123&&e0.getY()>0&&e0.getY()<43) {
				bt1.setBounds(333, 110, 123, 43);
				bt2.setBounds(46, 110, 123, 43);
				imageLab.setVisible(true);
				}
			}
	    
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		bt1.setBounds(333, 110, 123, 43);
				bt2.setBounds(46, 110, 123, 43);
				imageLab.setVisible(true);
	    	}
		});
	
	}
}
