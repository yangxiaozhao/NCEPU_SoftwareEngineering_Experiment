package dbwork;

import java.util.Scanner;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;

public class Service {
	    public static String connect(String User,String Password) {
	    	// 连接数据库
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库驱动成功");
			String connectDB = "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY";
			Connection con = DriverManager.getConnection(connectDB, User,Password);
			System.out.println("连接数据库成功");
			
			//Statement s=con.createStatement();
			//s.execute("insert into sc values('22002','002',90)");
		} catch (ClassNotFoundException e) {
			return "未找到驱动";
		}catch (SQLException e) {
			return"error! "+e.getMessage();
		}
		return null;
	  }
	   
	    // 根据给定视图名，返回数据向量
	    public static Vector<Vector<String>> show(String view) throws Exception {      
			Connection con =DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from "+view);
			ResultSetMetaData rsmd = rs.getMetaData() ;
			Vector<Vector<String>> data=new Vector<Vector<String>>();
			Vector colname = null;
			while(rs.next()) {
				colname=new Vector<String>();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					colname.add(rs.getString(i));
				}
				data.add(colname);
			}
			return data;
		}
	    
	    
	    public static Vector<String> Colname(String view) throws Exception {      
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from "+view);
			ResultSetMetaData rsmd = rs.getMetaData() ;
			Vector<String> colname=new Vector<String>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) 
				colname.add(rsmd.getColumnName(i+1));
			return colname;
	    }
	    
	    public static JTable show_table(String view) {          // 返回表格，展示给定视图数据
	    	Vector<Vector<String>> data = null;
	    	Vector colname=new Vector<String>();
			try {
				data = show(view);
				colname=Colname(view);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JTable table= new JTable(data,colname);
			setTableStyle(table);
			return table;
	    }
	    
	    // 增加学生记录
	    public static void insertstudent(String Sno,String Sname,String Ssex,String Sdept) throws Exception {
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			st.execute("insert into Student values(\'"+Sno+"\',\'"+Sname+"\',\'"+Ssex+"\',\'"+Sdept+"\')");
		}
	    
	    // 删除学生记录
	    public static void deletestudent(String Sno,String Sname,String Ssex,String Sdept) throws Exception {
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			String s=new String();
			if(!Sno.equals("")) {
				s=s.concat(" Sno="+"\'"+Sno+"\'");
			}
			if(!Sname.equals("")) {
				if(!s.equals(""))
					s=s.concat(" and Sname="+"\'"+Sname+"\'");
				else
					s=s.concat(" Sname="+"\'"+Sname+"\'");
			}
			if(!Ssex.equals("")) {
				if(!s.equals(""))
					s=s.concat(" and Ssex="+"\'"+Ssex+"\'");
				else
					s=s.concat(" Ssex="+"\'"+Ssex+"\'");
			}
			if(!Sdept.equals("")) {
				if(!s.equals(""))
					s=s.concat(" and Sdept="+"\'"+Sdept+"\'");
				else
					s=s.concat(" Sdept="+"\'"+Sdept+"\'");
			}
			if(s.equals("")) {
				s=" 1=0";
			}
			st.execute("delete from Student where"+s);
		}
	    
	    // 查找学生记录并返回表格
	    public static JTable selectstudent(String Sno,String Sname,String Ssex,String Sdept) throws Exception {
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			String s=new String();
			if(!Sno.equals("")) {
				s=s.concat(" Sno="+"\'"+Sno+"\'");
			}
			if(!Sname.equals("")) {
				if(!s.equals(""))
					s=s.concat(" and Sname="+"\'"+Sname+"\'");
				else
					s=s.concat(" Sname="+"\'"+Sname+"\'");
			}
			if(!Ssex.equals("")) {
				if(!s.equals(""))
					s=s.concat(" and Ssex="+"\'"+Ssex+"\'");
				else
					s=s.concat(" Ssex="+"\'"+Ssex+"\'");
			}
			if(!Sdept.equals("")) {
				if(!s.equals(""))
					s=s.concat(" and Sdept="+"\'"+Sdept+"\'");
				else
					s=s.concat(" Sdept="+"\'"+Sdept+"\'");
			}
			if(s.equals("")) {
				s=" 1=1";
			}
			ResultSet rs=st.executeQuery("select Sno,Sname,Sdept from Student where"+s);
			ResultSetMetaData rsmd = rs.getMetaData() ;
			Vector<Vector<String>> data=new Vector<Vector<String>>();
			Vector colname = null;
			while(rs.next()) {
				colname=new Vector<String>();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					colname.add(rs.getString(i));
				}
				data.add(colname);
			}
			colname=new Vector<String>();
			colname.add("学号");
			colname.add("姓名");
			colname.add("所在系");
			JTable table= new JTable(data,colname);
			setTableStyle(table);
			return table;
		}
	    
	    // 修改学生记录
	    public static void updatestudent(String Sno,String Sname,String Ssex,String Sdept) throws Exception {
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			String s=new String();
			if(Sno.equals("")) {
				return;
			}
			if(!Sname.equals("")) {
				s=s.concat(" Sname="+"\'"+Sname+"\'");
			}
			if(!Ssex.equals("")) {
				if(!s.equals(""))
					s=s.concat(",Ssex="+"\'"+Ssex+"\'");
				else
					s=s.concat(" Ssex="+"\'"+Ssex+"\'");
			}
			if(!Sdept.equals("")) {
				if(!s.equals(""))
					s=s.concat(",Sdept="+"\'"+Sdept+"\'");
				else
					s=s.concat(" Sdept="+"\'"+Sdept+"\'");
			}
			if(s.equals("")) {
				return;
			}
			st.execute("update Student set"+s+" where Sno=\'"+Sno+"\'");
		}
	    
	 // 查找图书记录并返回表格
	    public static JTable selectbook(String bname) throws Exception {
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			String sql=new String();
			if(!bname.equals(""))
				sql="select * from booklist where 书名=\'"+bname+"\'";
			else
				sql="select * from booklist";
			ResultSet rs=st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData() ;
			Vector<Vector<String>> data=new Vector<Vector<String>>();
			Vector colname = null;
			while(rs.next()) {
				colname=new Vector<String>();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					colname.add(rs.getString(i));
				}
				data.add(colname);
			}
			colname=new Vector<String>();
			colname.add("书名");
			colname.add("库存总数");
			colname.add("现存可借");
			JTable table= new JTable(data,colname);
			setTableStyle(table);
			return table;
		}
	    
	    // 根据学号返回学生姓名
	    public static String getsname(String sno) throws Exception {
	    	Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select sname from student where sno=\'"+sno+"\'");
			if(rs.next())
				return rs.getString(1);
			return null;
	    }

	    // 根据图书名返回图书序号列表-未借出
	    public static Vector<String> getbnolist(String bname) throws Exception{
	    	Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select bno from book where isrent=\'否\' and bname=\'"+bname+"\'");
			Vector<String> v=new Vector<String>();
			while(rs.next())
				v.add(rs.getString(1));
			return v;
	    }
	    
	    //返回目前Borrow_Return表中最大rid
	    public static int getrid() throws Exception {
	    	Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select rid from borrow_return where rid>= all(select rid from Borrow_Return)");
			if(rs.next())
				return rs.getInt(1);
			return -2;
	    }
	    
	    // 借书业务办理
	    public static String borrowbook(int rid,String sno,String bno,String bor,String time) {
	    	Connection con;
			try {
				con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
				Statement st=con.createStatement();
				st.execute("insert into borrow_return values("+rid+","+"\'"+sno+"\',"+"\'"+bno+"\',"+"\'"+bor+"\',"+"\'"+time+"\'"+")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
			return null;
	    }
	    
	   // 根据图书名返回图书序号列表-已借出
	    public static Vector getrentbno(String bname) throws Exception {
			Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select bno from book where isrent=\'是\' and bname=\'"+bname+"\'");
			Vector<String> v=new Vector<String>();
			while(rs.next())
				v.add(rs.getString(1));
			return v;
		}
	    
	 // 还书业务办理
	    public static String returnbook(int rid,String sno,String bno,String bor,String time) {
	    	Connection con;
	    	String s =new String();
	    	s="还书失败！";
			try {
				con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", Login.username,"123456");
				Statement st=con.createStatement();
				st.execute("insert into borrow_return values("+rid+","+"\'"+sno+"\',"+"\'"+bno+"\',"+"\'"+bor+"\',"+"\'"+time+"\'"+")");
				s="";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
			return s;
	    }
	    
	    // 新书入库
	    public static void newbook(String bname,int num) throws Exception {
	    	Connection con = DriverManager.getConnection( "jdbc:sqlserver://localhost:1433;DatabaseName=LIBRARY", "yangxz","123456");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select bno from book where bno>= all(select bno from book)");
			rs.next();
			String temp=rs.getString(1);
			int ti=Integer.parseInt(temp);
	    	for(int i=0;i<num;i++) {
	    		int j=ti+i+1;
	    		String bno=String.valueOf(j);
	    		String str="";
	    		for(int k=0;k<(5-bno.length());k++)
					str+="0";
				bno=str+bno;
				st.execute("insert into book(bno,bname) values(\'"+bno+"\',\'"+bname+"\'"+")");
	    	}
	    }
	    
	    //设置表格格式
	    public static void setTableStyle(JTable jtb) {            
	        //设置选中行的背景色
	        //jtb.setSelectionBackground(new Color(224, 242, 255));
	        //设置表格每行的高度
	        jtb.setRowHeight(24);
	        // 设置表格中的数据居中显示
	        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        jtb.setDefaultRenderer(Object.class,r);
	        // 设置表头格式
	        Dimension size = jtb.getTableHeader().getPreferredSize();
			size.height = 28;
	        jtb.getTableHeader().setPreferredSize(size);
			jtb.getTableHeader().setFont(new Font("黑体",Font.PLAIN,20));
			// 设置表头文字居中显示
	        DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer) jtb.getTableHeader().getDefaultRenderer();
	        renderer.setHorizontalAlignment(renderer.CENTER);
	         // 设置点击表头自动实现排序
	        jtb.setAutoCreateRowSorter(true);
	        jtb.setFocusable(false);
	        jtb.setFont(new Font("新宋体", Font.PLAIN, 18));
	    }

		

}

