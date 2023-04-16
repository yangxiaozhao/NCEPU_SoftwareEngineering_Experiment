package dbwork;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewBook extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBook frame = new NewBook();
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
	public NewBook() {
		setFrameIcon(new ImageIcon(NewBook.class.getResource("/images/办理业务.png")));
		setTitle("新书入库");
		setClosable(true);
		setIconifiable(true);
		setBounds(230, 120, 476, 272);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("书名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(35, 42, 58, 20);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(72, 41, 130, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("数量");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(256, 42, 58, 20);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(293, 41, 130, 25);
		getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("入   库");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bname=textField.getText();
				int num=Integer.parseInt(textField_1.getText());
				if(passwordField.getText().equals(Login.password)) {
					try {
						Service.newbook(bname, num);
						JOptionPane.showMessageDialog(NewBook.this, "入库成功！");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(NewBook.this, "身份验证失败!");
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setBounds(163, 183, 122, 37);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("管理员");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(16, 110, 58, 20);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(72, 109, 130, 25);
		textField_2.setText(Login.username);
		getContentPane().add(textField_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("密码");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(256, 110, 58, 20);
		getContentPane().add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(293, 109, 130, 25);
		getContentPane().add(passwordField);

	}
}
