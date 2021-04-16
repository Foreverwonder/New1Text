package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.lingnan.dto.ScoreDto;
import cn.edu.lingnan.dto.StudentDto;
import cn.edu.lingnan.util.DataAccess;

/**
 * 对score表的数据操作类
 */
public class ScoreDao {
    //查找所有成绩
    public Vector<ScoreDto> findAllScore() {
        Vector<ScoreDto> v = new Vector<ScoreDto>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            String sql = "select * from score";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                ScoreDto s = new ScoreDto();
                s.setSid(rs.getString("sid"));
                s.setCid(rs.getString("cid"));
                s.setScore(rs.getInt("score"));
                v.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }
        return v;
    }

    //删除成绩表
    public boolean deleteScore(String _sid, String _cid) throws SQLException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        try {
            conn = DataAccess.getConnection();
            conn.setAutoCommit(false);
            String sql1 =
                    "insert into xscore(xsid,xcid,xscore) select sid,cid,score from score where sid=? and cid=? ";
            prep2 = conn.prepareStatement(sql1);
            prep2.setString(1, _sid);
            prep2.setString(2, _cid);
            prep2.executeUpdate();

            String sql0 =
                    "delete from score where sid=? and cid=?";
            prep1 = conn.prepareStatement(sql0);
            prep1.setString(1, _sid);
            prep1.setString(2, _cid);
            prep1.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            flag = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        prep1.close();
        return flag;
    }

    // 实现按sid和cid查找成绩
    public int findScoreBySidAndCid(String _sid, String _cid) {
        int _score = 0;
        Connection conn = null;
//		Statement stat = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
//			stat = conn.createStatement();
//			String sql = "select * from score where sid='" + _sid + "' and cid='" + _cid + "'";
            String sql = "select * from score where sid=? and cid=?";
            prep = conn.prepareStatement(sql);
            prep.setString(1, _sid);
            prep.setString(2, _cid);
//			rs = stat.executeQuery(sql);
            rs = prep.executeQuery();
            if (rs.next())
                _score = rs.getInt("score");
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
                if (prep != null)
                    prep.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return _score;
    }

    // 插入一条分数信息
    // 返回值的可能性：0、失败（没学生没课程）：1、成功：2、有学生没课程：3、有课程没学生4、主键约束

    public int insertInfotoScore(ScoreDto _sd) {
        int flag = 0;
        int flagStudent = 0;
        int flagCourse = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        String _sid = _sd.getSid();
        String _cid = _sd.getCid();
        int _score = _sd.getScore();
        try {
            conn = DataAccess.getConnection();
            // 查分数表
            prep = conn.prepareStatement("select * from score where sid=? and cid=?");
            prep.setString(1, _sid);
            prep.setString(2, _cid);
            rs = prep.executeQuery();
            if (rs.next())
                return 4;
            rs.close();
            prep.close();
            // 查学生表
            prep = conn.prepareStatement("select * from student where sid=?");
            prep.setString(1, _sid);
            rs = prep.executeQuery();
            if (rs.next())
                flagStudent = 1;
            rs.close();
            prep.close();
            // 查课程表
            prep = conn.prepareStatement("select * from course where cid=?");
            prep.setString(1, _cid);
            rs = prep.executeQuery();
            if (rs.next())
                flagCourse = 1;
            rs.close();
            prep.close();
            if (flagStudent == 1 && flagCourse == 1) {// 意味着可以执行插入操作
                String sql = "insert into score values(?,?,?)";// 一条语句写错两处地方。。。
                prep = conn.prepareStatement(sql);
                prep.setString(1, _sid);
                prep.setString(2, _cid);
                prep.setInt(3, _score);
                prep.executeUpdate();
                flag = 1;// 若上方prep.executeUpdate()失败将直接跳转到catch块，flag不会被置为1
            } else if (flagStudent == 0 && flagCourse == 1)
                flag = 3;
            else if (flagStudent == 1 && flagCourse == 0)
                flag = 2;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }
        return flag;
    }

    //更新分数表
    public int updataScore(ScoreDto _sd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement
                    ("update score set score =? where sid=? and cid=?");
            prep.setInt(1, _sd.getScore());
            prep.setString(2, _sd.getSid());
            prep.setString(3, _sd.getCid());
            prep.executeUpdate();
            flag = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return flag;
    }
}
