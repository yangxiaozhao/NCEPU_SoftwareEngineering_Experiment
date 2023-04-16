package dbwork;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookInformation extends JInternalFrame {
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInformation frame = new BookInformation();
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
	public BookInformation() {
		setBackground(new Color(255, 255, 255));
		setIconifiable(true);
		setFrameIcon(new ImageIcon(StudentInformation.class.getResource("/images/信息查询.png")));
		setToolTipText("");
		setTitle("图书信息");
		setClosable(true);
		setBounds(120, 16, 633, 530);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 81, 499, 397);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table=Service.show_table("booklist");
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("书名：");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel.setBounds(94, 29, 60, 35);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(164, 31, 187, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("查  询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname=textField.getText();
				try {
					JTable temptable=Service.selectbook(bname);
					scrollPane.setViewportView(temptable);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("幼圆", Font.BOLD, 17));
		btnNewButton.setBounds(394, 29, 106, 32);
		getContentPane().add(btnNewButton);
	}
}
