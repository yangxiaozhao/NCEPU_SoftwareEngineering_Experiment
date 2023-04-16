package dbwork;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RentRecord extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentRecord frame = new RentRecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public RentRecord() throws SQLException {
		getContentPane().setBackground(new Color(255, 255, 204));
		setFrameIcon(new ImageIcon(StudentInformation.class.getResource("/images/记录.png")));
		setToolTipText("");
		setTitle("当前借阅");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 620, 447);
		getContentPane().add(scrollPane);
		
		Vector<Vector<String>> data=new Vector<Vector<String>>();
		Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select 书序号,书名,借阅人 from rent");
		ResultSetMetaData rsmd = rs.getMetaData() ;
		Vector colname = null;
		while(rs.next()) {
			colname=new Vector<String>();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				colname.add(rs.getString(i));
			}
			data.add(colname);
		}
		
		colname=new Vector<String>();
		colname.add("书序号");
		colname.add("书名");
		colname.add("借阅人");
		JTable table = new JTable(data,colname);
		Service.setTableStyle(table);
		scrollPane.setViewportView(table);
		
		
		setClosable(true);
		setIconifiable(true);
		setBounds(130, 40, 652, 496);
	}

}
