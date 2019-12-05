import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class quitM1 {

	private JFrame frame;
	private JTextField psw;

	/**
	 * Launch the application.
	 */
	
			public void run() {
				try {
					quitM1 window = new quitM1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	

	/**
	 * Create the application.
	 */
	public quitM1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(Game.X/2-250, Game.Y/2-110, 500, 220);
		frame.setAlwaysOnTop(true);//始终位于最前
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u60F3\u9000\u51FA\u60A8\u5F97\u8F93\u5165\u5BC6\u7801\u554A\uFF1A");
		label.setBounds(43, 15, 217, 21);
		frame.getContentPane().add(label);
		
		psw = new JTextField();
		psw.setBounds(248, 13, 177, 27);
		frame.getContentPane().add(psw);
		psw.setColumns(10);
		
		JLabel wrong = new JLabel("\u8FD9\u4E2A\u5BC6\u7801\u5B83\u53EF\u80FD\u4E0D\u548B\u4E48\u5BF9\u5427");
		wrong.setIcon(new ImageIcon("src/pic0.png"));
		wrong.setBounds(16, 49, 290, 72);
		wrong.setVisible(false);
		frame.getContentPane().add(wrong);
		
		JButton btok = new JButton("\u6211\u8F93\u5165\u5BC6\u7801\u4E86");
		btok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			String pswtxt = psw.getText();
			System.out.println(pswtxt);
			if(!pswtxt.equals("我喜欢你wmx")) {
				wrong.setVisible(true);
			}else {
				System.exit(0);
			}
			}
		});
		btok.setBounds(325, 123, 141, 29);
		frame.getContentPane().add(btok);
		
		JButton btidontcare = new JButton("\u4E0D\u7BA1\u4E86\u6211\u5C31\u8981\u9000\u51FA");
		btidontcare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quitM2 q2 =new quitM2();
				q2.run();
				frame.dispose();
			}
		});
		btidontcare.setBounds(27, 123, 193, 29);
		frame.getContentPane().add(btidontcare);
		JLabel lblscore = new JLabel("获得密码很简单，就把游戏Score打到240以上自动出现密码");
		lblscore.setFont(new Font("黑体", Font.PLAIN, 18));
		JLabel label_1 = new JLabel("\u67E5\u770B\u83B7\u53D6\u5BC6\u7801\u65B9\u6CD5");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblscore.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblscore.setVisible(false);
			}
		});
		label_1.setBounds(309, 73, 157, 31);
		frame.getContentPane().add(label_1);
		
	
		lblscore.setBackground(Color.DARK_GRAY);
		lblscore.setForeground(Color.PINK);
		lblscore.setBounds(15, 43, 498, 31);
		lblscore.setVisible(false);
		frame.getContentPane().add(lblscore);
	}
}
