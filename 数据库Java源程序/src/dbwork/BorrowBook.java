package dbwork;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class BorrowBook extends JInternalFrame {
	private JTextField snotext;
	private JTextField snametext;
	private JTextField bnametext;
	private JComboBox bnotext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowBook frame = new BorrowBook();
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
	public BorrowBook() {
		setTitle("借书业务");
		setFrameIcon(new ImageIcon(BorrowBook.class.getResource("/images/办理业务.png")));
		setClosable(true);
		setBounds(100, 100, 584, 348);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学号：");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel.setBounds(55, 40, 60, 34);
		getContentPane().add(lblNewLabel);
		
		snotext = new JTextField();
		snotext.setFont(new Font("宋体", Font.PLAIN, 15));
		snotext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sname=Service.getsname(snotext.getText());
					snametext.setText(sname);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		snotext.setBounds(109, 44, 131, 23);
		getContentPane().add(snotext);
		snotext.setColumns(10);
		
		
		bnotext =new JComboBox();
		bnotext.setFont(new Font("宋体", Font.PLAIN, 15));
		bnotext.setBounds(369, 124, 131, 23);
		getContentPane().add(bnotext);
		
		JButton btnNewButton = new JButton("借  阅");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rid = 0;
				String ee=null;
				String sno=snotext.getText();
				String bno=bnotext.getSelectedItem().toString();
				String bor="借书";
				Date d=new Date();
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				String time=sdf2.format(d);
				try {
					rid=Service.getrid()+1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ee=Service.borrowbook(rid, sno, bno, bor, time);
					JOptionPane.showMessageDialog(null, "借阅成功！","提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,ee,"失败",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setBounds(208, 213, 136, 46);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1.setBounds(304, 40, 60, 34);
		getContentPane().add(lblNewLabel_1);
		
		snametext = new JTextField();
		snametext.setFont(new Font("宋体", Font.PLAIN, 15));
		snametext.setEditable(false);
		snametext.setColumns(10);
		snametext.setBounds(369, 44, 131, 23);
		getContentPane().add(snametext);
		
		bnametext = new JTextField();
		bnametext.setFont(new Font("宋体", Font.PLAIN, 15));
		bnametext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector v=new Vector<>();
				try {
					v = Service.getbnolist(bnametext.getText());
					bnotext.removeAllItems();
					Enumeration vEnum = v.elements();        // v.elements() : 返回此向量组件的枚举
				    while(vEnum.hasMoreElements())
				    	bnotext.addItem(vEnum.nextElement());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bnametext.setColumns(10);
		bnametext.setBounds(109, 124, 131, 23);
		getContentPane().add(bnametext);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("书序号：");
		lblNewLabel_1_2_1.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_1_2_1.setBounds(304, 117, 90, 34);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("书名：");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel_2.setBounds(55, 120, 60, 34);
		getContentPane().add(lblNewLabel_2);

	}
}
