package cn.edu.lingnan.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DataAccess {

	private static Connection conn=null;
	private static String driver=null;
	private static String url=null;
	private static String user=null;
	private static String password=null;
	private static String xmlPath="bin//database.conf.xml";
	private static String xsdPath="bin//database.conf.xsd";

	static {
		if(XmlValidate.validate(xmlPath,xsdPath)){
			HashMap<String,String> hm=XmlParser.parse(xmlPath);
			driver=hm.get("driver");
			url=hm.get("url");
			user =hm.get("user");
			password=hm.get("password");
//			System.out.println(driver+" "+url+" "+user+" "+password);
		}
	}

	// 返回数据库的连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
			conn = DriverManager.getConnection(url, user, password);
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
