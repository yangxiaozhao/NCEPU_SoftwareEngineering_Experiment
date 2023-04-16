package dbwork;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class StudentInformation extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField sno_text;
	private JTextField sname_text;
	private JTextField ssex_text;
	private JTextField sdept_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInformation frame = new StudentInformation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public StudentInformation()  {
		setBackground(new Color(255, 255, 255));
		setIconifiable(true);
		setFrameIcon(new ImageIcon(StudentInformation.class.getResource("/images/信息查询.png")));
		setToolTipText("");
		setTitle("学生信息");
		setClosable(true);
		setBounds(120, 16, 706, 565);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 171, 587, 340);
		getContentPane().add(scrollPane);
		
		table = Service.show_table("studentlist");
		Service.setTableStyle(table);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(40, 21, 597, 41);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 8, 36, 23);
		panel.add(lblNewLabel);
		
		sno_text = new JTextField();
		sno_text.setBounds(46, 9, 89, 21);
		panel.add(sno_text);
		sno_text.setColumns(10);
		
		sname_text = new JTextField();
		sname_text.setColumns(10);
		sname_text.setBounds(192, 9, 89, 21);
		panel.add(sname_text);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(156, 8, 36, 23);
		panel.add(lblNewLabel_1);
		
		ssex_text = new JTextField();
		ssex_text.setColumns(10);
		ssex_text.setBounds(337, 9, 89, 21);
		panel.add(ssex_text);
		
		JLabel lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(301, 8, 36, 23);
		panel.add(lblNewLabel_2);
		
		sdept_text = new JTextField();
		sdept_text.setColumns(10);
		sdept_text.setBounds(493, 9, 89, 21);
		panel.add(sdept_text);
		
		JLabel lblNewLabel_3 = new JLabel("所在系");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(439, 8, 59, 23);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton("添加记录");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Sno=sno_text.getText();
				String Sname=sname_text.getText();
				String Ssex=ssex_text.getText();
				String Sdept=sdept_text.getText();
				try {
					Service.insertstudent(Sno, Sname, Ssex,Sdept);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(StudentInformation.this, e.getMessage());
				}
				JTable temptable = Service.show_table("studentlist");
				scrollPane.setViewportView(temptable);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_2.setBounds(125, 72, 117, 34);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("查询记录");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Sno=sno_text.getText();
				String Sname=sname_text.getText();
				String Ssex=ssex_text.getText();
				String Sdept=sdept_text.getText();
				try {
					JTable temptable=Service.selectstudent(Sno, Sname, Ssex, Sdept);
					scrollPane.setViewportView(temptable);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(StudentInformation.this, e.getMessage());
				}
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_3.setBounds(389, 72, 117, 34);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("修改信息");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Sno=sno_text.getText();
				String Sname=sname_text.getText();
				String Ssex=ssex_text.getText();
				String Sdept=sdept_text.getText();
				try {
					Service.updatestudent(Sno, Sname, Ssex,Sdept);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(StudentInformation.this, e.getMessage());
				}
				JTable temptable = Service.show_table("studentlist");
				scrollPane.setViewportView(temptable);
			}
		});
		btnNewButton.setBounds(125, 127, 117, 34);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除记录");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Sno=sno_text.getText();
				String Sname=sname_text.getText();
				String Ssex=ssex_text.getText();
				String Sdept=sdept_text.getText();
				try {
					Service.deletestudent(Sno, Sname, Ssex,Sdept);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(StudentInformation.this, e.getMessage());
				}
				JTable temptable = Service.show_table("studentlist");
				scrollPane.setViewportView(temptable);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_1.setBounds(389, 127, 117, 34);
		getContentPane().add(btnNewButton_1);
		
	}
}
