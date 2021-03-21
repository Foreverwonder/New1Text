package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.lingnan.dto.StudentDto;

/**
 * 完成对学生表的数据操作类
 */
public class StudentDao {
	// 实现按用户名和密码进行查找
	public boolean findStudentByNameAndPassword(String _sname, String _password) {
		boolean flag = false;
		Connection conn = null;
//		Statement stat = null;
		PreparedStatement prep =null;
		
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
			
//			stat = conn.createStatement();
//			String sql = "select * from student where sname='" + _sname + "' and " + "password ='" + _password + "'";// 写'"
//			rs = stat.executeQuery(sql);
			
			String sql = "select * from student where sname=? and password =?";// 写'"
			prep =conn.prepareStatement(sql);
			prep.setString(1,_sname);
			prep.setString(2,_password);
			rs= prep.executeQuery();
			if (rs.next())
				flag = true;
		} catch (ClassNotFoundException e) {
			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
//				if (stat != null)
//					stat.close();
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	// 查找所有的学生信息
	public Vector<StudentDto> findAllStudent() {
		Vector<StudentDto> v = new Vector<StudentDto>();
		Connection conn = null;
//		Statement stat = null;
		PreparedStatement prep =null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
//			stat = conn.createStatement();
			String sql = "select * from student";
//			rs = stat.executeQuery(sql);
			prep = conn.prepareStatement(sql);
			rs= prep.executeQuery();
			while (rs.next()) {
				StudentDto s = new StudentDto();
				s.setSid(rs.getString("sid"));
				s.setSname(rs.getString("sname"));
				s.setPassword(rs.getString("password"));
				s.setSuperuser(rs.getInt("superuser"));
				v.add(s);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return v;
	}
	
	
	
	
	
	
	
	//插入一条学生记录
	public int  insertInfoToStudent(StudentDto _sd) {
		int flag=0;
		Connection conn = null;
		PreparedStatement prep =null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
			String sql = 
			"insert into student values(?,?,?,?)";//一条语句写错两处地方。。。
			prep =conn.prepareStatement(sql);
			prep.setString(1,_sd.getSid());
			prep.setString(2,_sd.getSname());
			prep.setString(3,_sd.getPassword());
			prep.setInt(4,_sd.getSuperuser());
			int i =prep.executeUpdate();
			System.out.println("i="+i);
			flag=1;//若上方prep.executeUpdate()失败将直接跳转到catch块，flag不会被置为1
		} catch (ClassNotFoundException e) {
			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
//				if (rs != null)
//					rs.close();
				if (prep != null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
