package cn.edu.lingnan.dao;

import java.sql.*;
import java.util.Vector;

import cn.edu.lingnan.dto.StudentDto;
import com.sun.net.httpserver.Authenticator.Result;
import cn.edu.lingnan.dto.CourseDto;
import cn.edu.lingnan.dto.ScoreDto;
import cn.edu.lingnan.util.DataAccess;

/**
 * 对课表course的操作类
 */
public class CourseDao {
    //通过cid找name
    public String findCnameByCid(String _cid) {
        String _cname = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/school", "root", "123");
            String sql = "select * from course where cid=?";
            prep = conn.prepareStatement(sql);
            prep.setString(1, _cid);
            rs = prep.executeQuery();
            if (rs.next())
                _cname = rs.getString("cname");
        } catch (ClassNotFoundException e) {
            System.out.println("检查Mysql的jar导入是否正确");
            e.printStackTrace();
        } catch (SQLException e) {
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
        return _cname;
    }


    //课程表插入一条信息
    public int insertInfoToCourse(CourseDto _cd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        Result rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
            String sql =
                    "insert into course values(?,?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, _cd.getCid());
            prep.setString(2, _cd.getCname());
            int i = prep.executeUpdate();
            System.out.println("i=" + i);
            flag = 1;
        } catch (ClassNotFoundException e) {
            System.out.println("检查Mysql的jar导入是否正确");
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

    //更新课程表
    //cid是主键
    public int updataCourse(CourseDto _sd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement
                    ("update course set cname =? where cid=?");
            prep.setString(1, _sd.getCname());
            prep.setString(2, _sd.getCid());
            prep.executeUpdate();
            flag = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return flag;
    }


    //删除课程表(若该cid在score表中只有0条记录，则直接删除，否则不删除)
    public boolean deleteCourse(String _cid) throws SQLException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        Statement stat = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = DataAccess.getConnection();
            //----------通过学生编号找到待删除的课程，存入动态数组中------------------------
//            Vector<String> v = new Vector<String>();
            String sql0 =
                    "select count(*) as num from score where cid=?";
            prep1 = conn.prepareStatement(sql0);
            prep1.setString(1, _cid);
            rs1 = prep1.executeQuery();
            rs1.next();
            if (Integer.parseInt(rs1.getString("num")) == 0) {
//                System.out.println("要删除的课程号：" + _cid);
                String sql1 = "delete from course where cid=?";
                prep1 = conn.prepareStatement(sql1);
                prep1.setString(1, _cid);
                prep1.executeUpdate();
            flag=true;
            }
            prep1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return flag;
    }
    // 查找所有的课程信息（改）
    public Vector<CourseDto> findAllCourse() {
        Vector<CourseDto> v = new Vector<>();
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
            String sql = "select * from course";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                CourseDto c = new CourseDto();
                c.setCid(rs.getString("cid"));
                c.setCname(rs.getString("cname"));
                v.add(c);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }
        return v;
    }
}







