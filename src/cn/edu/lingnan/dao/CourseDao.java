package cn.edu.lingnan.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.net.httpserver.Authenticator.Result;
import cn.edu.lingnan.dto.CourseDto;
/**
 * 对课表course的操作类
 */
public class CourseDao {
	//根据cid找cname
	public String findCnameByCid(String _cid) {
		String _cname=null;
		Connection conn = null;
//		Statement stat = null;
		PreparedStatement prep=null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/school", "root", "123");
//			stat = conn.createStatement();
//			String sql = "select * from course where cid='" + _cid + "'";
			String sql = "select * from course where cid=?";
			prep=conn.prepareStatement(sql);
			prep.setString(1,_cid);
//			rs = stat.executeQuery(sql);
			rs = prep.executeQuery();
			if (rs.next())
				_cname=rs.getString("cname");
//				System.out.println(rs.getString("cname"));
		} catch (ClassNotFoundException e) {
			System.out.println("?ж????????????MySql????JAR??????????.....");
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
		return _cname;
	}
	

	//对课程表插入学生信息
	public int insertInfoToCourse(CourseDto _cd) {
		int flag=0;
		Connection conn =null;
		PreparedStatement prep=null;
		Result rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
			String sql=
			"insert into course values(?,?)";
			prep=conn.prepareStatement(sql);
			prep.setString(1, _cd.getCid());
			prep.setString(2, _cd.getCname());
			int i =prep.executeUpdate();
			System.out.println("i="+i);
			flag=1;
		}catch (ClassNotFoundException e) {
			System.out.println("?ж????????????MySql????JAR??????????.....");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
//				if (rs!= null)
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







