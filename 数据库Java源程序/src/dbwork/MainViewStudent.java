package dbwork;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;


// 系统主界面
public class MainViewStudent extends JFrame {

	private JPanel contentPane;
	JDesktopPane desktopPane;
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainViewStudent frame = new MainViewStudent();
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
	public MainViewStudent() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 628);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(102, 204, 204));
		desktopPane.setBounds(0, 0, 929, 598);
		contentPane.add(desktopPane);
	
		JButton btnNewButton = new JButton("馆藏检索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookInformation book=new BookInformation();
				desktopPane.add(book);
				book.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(204, 255, 255));
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 24));
		btnNewButton.setBounds(240, 315, 163, 61);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("借阅书籍");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowBook bb=new BorrowBook();
				desktopPane.add(bb);
				bb.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(204, 255, 255));
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 24));
		btnNewButton_1.setBounds(536, 315, 163, 61);
		desktopPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("图 书 借 阅 系 统");
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		lblNewLabel_1.setForeground(new Color(102, 205, 170));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("华文琥珀", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel_1.setBounds(122, 70, 697, 194);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainViewStudent.class.getResource("/images/背景1.png")));
		lblNewLabel.setBounds(34, 27, 860, 524);
		desktopPane.add(lblNewLabel);
		
	}
}
