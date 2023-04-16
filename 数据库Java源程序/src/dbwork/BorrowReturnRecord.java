package dbwork;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BorrowReturnRecord extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowReturnRecord frame = new BorrowReturnRecord();
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
	public BorrowReturnRecord() {
		getContentPane().setBackground(new Color(255, 255, 204));
		setFrameIcon(new ImageIcon(StudentInformation.class.getResource("/images/记录.png")));
		setToolTipText("");
		setTitle("借阅记录");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 673, 473);
		getContentPane().add(scrollPane);
		
		Vector<Vector<String>> data=null;
		try {
			data = Service.show("BorrowReturnRecord");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<String> colname=new Vector<String>();
		colname.add("学生");
		colname.add("书序号");
		colname.add("书籍");
		colname.add("业务");
		colname.add("办理时间");
		table = new JTable(data,colname);
		table.getColumnModel().getColumn(4).setPreferredWidth(170);
		Service.setTableStyle(table);
		scrollPane.setViewportView(table);
		
		
		setClosable(true);
		setIconifiable(true);
		setBounds(130, 20, 705, 522);

	}

}
