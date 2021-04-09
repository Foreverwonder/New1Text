package cn.edu.lingnan.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DataAccess {
	// 返回数据库的连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
		} catch (ClassNotFoundException e) {
			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("判断一下是否获取Mysql连接时参数设置是否正确...");
			e.printStackTrace();
		}
		return conn;
	}



	// 关闭数据库的连接
	public static void closeConnection(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭数据库连接
	public static void closeConnection(Connection conn, PreparedStatement prep, ResultSet rs) {
		try {
			if (rs!= null)
				rs.close();
			if (prep!= null)
				prep.close();
			if (conn!= null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭数据库连接
	public static void closeConnection(Connection conn, PreparedStatement prep) {
		try {
			if (prep!= null)
				prep.close();
			if (conn!= null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭数据库连接
	public static void closeConnection(Connection conn, Statement stat) {
		try {
			if (stat!= null)
				stat.close();
			if (conn!= null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
