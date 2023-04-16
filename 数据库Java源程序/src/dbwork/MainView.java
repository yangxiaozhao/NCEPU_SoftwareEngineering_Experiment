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
public class MainView extends JFrame {

	private JPanel contentPane;
	JDesktopPane desktopPane;
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
// 		 org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//		 javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 673);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("信息查询");
		mnNewMenu.setBackground(new Color(153, 204, 153));
		mnNewMenu.setIcon(new ImageIcon(MainView.class.getResource("/images/信息查询.png")));
		mnNewMenu.setFont(new Font("楷体", Font.PLAIN, 22));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("图书信息 ");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookInformation book=new BookInformation();
				desktopPane.add(book);
				book.setVisible(true);
			}
		});
		mntmNewMenuItem.setSelected(true);
		mntmNewMenuItem.setBackground(new Color(255, 255, 240));
		mntmNewMenuItem.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("学生信息 ");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentInformation student=new StudentInformation();
				desktopPane.add(student);
				student.setVisible(true);
			}
		});
		mntmNewMenuItem_3.setBackground(new Color(255, 255, 240));
		mntmNewMenuItem_3.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("业务办理");
		mnNewMenu_1.setIcon(new ImageIcon(MainView.class.getResource("/images/办理业务.png")));
		mnNewMenu_1.setFont(new Font("楷体", Font.PLAIN, 22));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("借书  ");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowBook bb=new BorrowBook();
				desktopPane.add(bb);
				bb.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("还书 ");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnBook rb=new ReturnBook();
				desktopPane.add(rb);
				rb.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("新书入库");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewBook nb=new NewBook();
				desktopPane.add(nb);
				nb.setVisible(true);
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_2_1);
		
		JMenu mnNewMenu_2 = new JMenu("业务记录");
		mnNewMenu_2.setIcon(new ImageIcon(MainView.class.getResource("/images/记录.png")));
		mnNewMenu_2.setFont(new Font("楷体", Font.PLAIN, 22));
		menuBar.add(mnNewMenu_2);
		
		/***
		 * JFrame j=new JFrame();
		 j.setBounts(100,100,200,300);
		 * **/
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("历史记录");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrowReturnRecord bd=new BorrowReturnRecord();
				desktopPane.add(bd);
				bd.setVisible(true);
			}
		});
		mntmNewMenuItem_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_2.add(mntmNewMenuItem_1_1);
		
		JMenuItem mntmNewMenuItem_1_1_1 = new JMenuItem("当前借阅");
		mntmNewMenuItem_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RentRecord bd;
				try {
					bd = new RentRecord();
					desktopPane.add(bd);
					bd.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mntmNewMenuItem_1_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_2.add(mntmNewMenuItem_1_1_1);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(102, 204, 204));
		desktopPane.setBounds(0, 0, 929, 598);
		contentPane.add(desktopPane);
	
		
		JLabel lblNewLabel_1 = new JLabel("图 书 信 息 管 理 系 统");
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		lblNewLabel_1.setForeground(new Color(102, 205, 170));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("华文琥珀", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel_1.setBounds(122, 191, 697, 194);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/images/背景1.png")));
		lblNewLabel.setBounds(34, 27, 860, 524);
		desktopPane.add(lblNewLabel);
		
	}
}
