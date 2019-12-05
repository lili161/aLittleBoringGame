import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;

public class quitM2 {

	private JFrame frame;
	private JTextField psw;
	private int state =0;
	private JLabel lb2;
	private JLabel lb3;
	private JLabel lblsmile;

	/**
	 * Launch the application.
	 */

			public void run() {
				try {
					quitM2 window = new quitM2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the application.
	 */
	public quitM2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(Game.X/2-250, Game.Y/2-110, 500, 220);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setAlwaysOnTop(true);//始终位于最前
		JLabel lb1 = new JLabel("\u8BF4\u201C\u6211\u559C\u6B22\u4F60\u201D");
		lb1.setBounds(63, 28, 144, 21);
			lb1.setVisible(true);
		frame.getContentPane().add(lb1);
		
		psw = new JTextField();
		psw.setBounds(172, 64, 123, 27);
		frame.getContentPane().add(psw);
		psw.setColumns(10);
		screenCut screenCuter = new screenCut();
		JButton next = new JButton("\u6211\u5199\u597D\u4E86");
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			String psw1 =psw.getText();
			if(state==0&&psw1.equals("我喜欢你")) {
				try {
					screenCut.cut();
				} catch (AWTException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				state=1;
				lb1.setVisible(false);
				lb2.setVisible(true);
				lblsmile.setVisible(false);
			}else if(state==1&&(psw1.equals("I喜欢U")||psw1.equals("U喜欢I"))) {
				try {
					screenCut.cut();
				} catch (AWTException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				state=2;
				lb2.setVisible(false);
				lb3.setVisible(true);
				lblsmile.setVisible(false);
			}else if(state==2&&psw1.equals("U")) {
				try {
					screenCut.cut();
					screenCut.open();
				} catch (AWTException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				state=3;
				lb3.setVisible(false);
				lblsmile.setVisible(false);
				psw.setVisible(false);
				next.setText("现在可以点击退出");
				next.setSize(162, 29);
			}else if(state==3) {
				System.exit(0);
			}
			else {
				lblsmile.setVisible(true);
			}
			}
		});
		next.setBounds(306, 105, 132, 29);
		frame.getContentPane().add(next);
		
		lb2 = new JLabel("谁喜欢谁？ （格式：（名字）喜欢（名字））");
		lb2.setBounds(108, 28, 282, 21);
		lb2.setVisible(false);
	
		frame.getContentPane().add(lb2);
		
		lb3 = new JLabel("谁喜欢 “name”");
		lb3.setBounds(306, 28, 112, 21);
		lb3.setVisible(false);
		
		frame.getContentPane().add(lb3);
		
		lblsmile = new JLabel("");
		lblsmile.setIcon(new ImageIcon("src/smile.png"));////////////////
		lblsmile.setVisible(false);
		lblsmile.setBounds(73, 73, 58, 44);
		frame.getContentPane().add(lblsmile);
	}

}
