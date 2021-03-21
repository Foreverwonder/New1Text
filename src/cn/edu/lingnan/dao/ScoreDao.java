package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *完成对score表的数据操作类
 */
public class ScoreDao {
	//实现按sid和cid查找成绩
	public int findScoreBySidAndCid(String _sid,String _cid) {
		int _score=0;
		Connection conn = null;
//		Statement stat = null;
		PreparedStatement prep=null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/school", "root", "123");
//			stat = conn.createStatement();
//			String sql = "select * from score where sid='" + _sid + "' and cid='" + _cid + "'";
			String sql = "select * from score where sid=? and cid=?";
			prep=conn.prepareStatement(sql);
			prep.setString(1, _sid);
			prep.setString(2, _cid);
//			rs = stat.executeQuery(sql);
			rs = prep.executeQuery();
			if (rs.next())
				_score=rs.getInt("score");
		} catch (ClassNotFoundException e) {
			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
//				if (stat != null)
//					stat.close();
				if (prep!= null)
					prep.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return _score;
	}
}
