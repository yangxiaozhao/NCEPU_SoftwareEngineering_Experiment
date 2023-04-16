package dbwork;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField tf_password;
	protected static String username;
	protected static String password;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");          // Java皮肤包
//		 org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
 //		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
 //		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {                                                  // 登陆界面
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书信息管理系统");
		lblNewLabel.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/图书.png")));
		lblNewLabel.setBounds(113, 10, 352, 153);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("用户名：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(124, 155, 96, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("密 码：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(124, 217, 89, 26);
		contentPane.add(lblNewLabel_3);
		
		tf_username = new JTextField();
		tf_username.setBounds(205, 158, 232, 26);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		JButton btn_login = new JButton("登  录");
		btn_login.addActionListener(new ActionListener() {               // 点击登录按钮
			public void actionPerformed(ActionEvent e) {
				username=tf_username.getText();
				password=tf_password.getText(); 
				String erromessage=Service.connect(username, password);
				if(erromessage!=null) {
					JOptionPane.showMessageDialog(Login.this, erromessage);
				}else {
					if(username.equals("admin")) {
						MainView mainview =new MainView();
						mainview.setVisible(true);
						Login.this.setVisible(false);
					}else if(username.equals("student")) {
						MainViewStudent mainview =new MainViewStudent();
						mainview.setVisible(true);
						Login.this.setVisible(false);
					}
					
				}
			}
		});
		btn_login.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_login.setBounds(96, 296, 117, 38);
		contentPane.add(btn_login);
		
		JButton btn_exit = new JButton("退  出");						// 点击退出按钮
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btn_exit.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_exit.setBounds(348, 296, 117, 38);
		contentPane.add(btn_exit);
		
		tf_password = new JPasswordField();
		tf_password.setBounds(205, 217, 232, 26);
		contentPane.add(tf_password);
	}
}
