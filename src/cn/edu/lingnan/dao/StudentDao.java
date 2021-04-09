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
 * 完成对学生表的数据操作类
 */
public class StudentDao {
    //通过sid找学生
    public String findStudentBySid(String _sid) {
        String _sname = null;
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection
//                    ("jdbc:mysql://localhost:3306/school", "root", "123");
            conn = DataAccess.getConnection();
            String sql = "select * from student where sid=?";
            prep = conn.prepareStatement(sql);
            prep.setString(1, _sid);
            rs = prep.executeQuery();
            if (rs.next())
                _sname = rs.getString("sname");
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
        return _sname;
    }
    // 实现按用户名和密码进行查找，存在即返回1。
    public boolean findStudentByNameAndPassword(String _sname, String _password) {
        boolean flag = false;
        Connection conn = null;
//		Statement stat = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
//			
////			stat = conn.createStatement();
////			String sql = "select * from student where sname='" + _sname + "' and " + "password ='" + _password + "'";// 写'"
////			rs = stat.executeQuery(sql);
//			
//			String sql = "select * from student where sname=? and password =?";// 写'"
//			prep =conn.prepareStatement(sql);
//			prep.setString(1,_sname);
//			prep.setString(2,_password);
//			rs= prep.executeQuery();
//			if (rs.next())
//				flag = true;
//		} catch (ClassNotFoundException e) {
//			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
////				if (stat != null)
////					stat.close();
//				if (prep != null)
//					prep.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return flag;
//	}
        //---------------------------------省去了数据库连接的try写法:
        try {
            conn = DataAccess.getConnection();
            String sql = "select * from student where sname=? and password =?";// 写'"
            prep = conn.prepareStatement(sql);
            prep.setString(1, _sname);
            prep.setString(2, _password);
            rs = prep.executeQuery();
            if (rs.next())
                flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep, rs);
        }
        return flag;
    }
    //---------------------------------省去了数据库连接的try写法:


    // 查找所有的学生信息
    public Vector<StudentDto> findAllStudent() {
        Vector<StudentDto> v = new Vector<StudentDto>();
        Connection conn = null;
//		Statement stat = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = DataAccess.getConnection();
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
////			stat = conn.createStatement();
            String sql = "select * from student";
//			rs = stat.executeQuery(sql);
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            while (rs.next()) {
                StudentDto s = new StudentDto();
                s.setSid(rs.getString("sid"));
                s.setSname(rs.getString("sname"));
                s.setPassword(rs.getString("password"));
                s.setSuperuser(rs.getInt("superuser"));
                v.add(s);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (prep != null)
//					prep.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
            DataAccess.closeConnection(conn, prep, rs);
        }
        return v;
    }


    //插入一条学生记录
    public int insertInfoToStudent(StudentDto _sd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "123");
            conn = DataAccess.getConnection();
            String sql =
                    "insert into student values(?,?,?,?)";//一条语句写错两处地方。。。
            prep = conn.prepareStatement(sql);
            prep.setString(1, _sd.getSid());
            prep.setString(2, _sd.getSname());
            prep.setString(3, _sd.getPassword());
            prep.setInt(4, _sd.getSuperuser());
            int i = prep.executeUpdate();
            System.out.println("i=" + i);
            flag = 1;//若上方prep.executeUpdate()失败将直接跳转到catch块，flag不会被置为1
        }
//		catch (ClassNotFoundException e) {
//			System.out.println("判断一下是不是你的MySql连接JAR包出了问题.....");
//			e.printStackTrace();
//		} 
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
//			try {
////				if (rs != null)
////					rs.close();
//				if (prep != null)
//					prep.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
            DataAccess.closeConnection(conn, prep, rs);
        }
        return flag;
    }

    //更新学生表_学生名字
    public int updataStudentSname(StudentDto _sd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement
                    ("update student set sname =? where sid=?");
            prep.setString(1, _sd.getSname());
            prep.setString(2, _sd.getSid());
            prep.executeUpdate();
            flag = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return flag;
    }


    //更新学生表_学生密码
    public int updataStudentPassword(StudentDto _sd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement
                    ("update student set password =? where sid=?");
            prep.setString(1, _sd.getPassword());
            prep.setString(2, _sd.getSid());
            prep.executeUpdate();
            flag = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return flag;
    }

    //更新学生表_学生权限
    public int updataStudentSuperuser(StudentDto _sd) {
        int flag = 0;
        Connection conn = null;
        PreparedStatement prep = null;
        try {
            conn = DataAccess.getConnection();
            prep = conn.prepareStatement
                    ("update student set superuser =? where sid=?");
            prep.setInt(1, _sd.getSuperuser());
            prep.setString(2, _sd.getSid());
            prep.executeUpdate();
            flag = 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataAccess.closeConnection(conn, prep);
        }
        return flag;
    }


    //删除一条学生记录
    public boolean deleteStudent(String _sid) throws SQLException {
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
            Vector<String> v = new Vector<String>();
            String sql0 =
                    "select * from score where sid=?";
            prep1 = conn.prepareStatement(sql0);
            prep1.setString(1, _sid);
            rs1 = prep1.executeQuery();
            System.out.println("567567");

            while (rs1.next()) {
                //这里已经找到s01同学所选的课程编号，C01，C02
                String cid = rs1.getString("cid");
                System.out.println(cid);
                //找一下这个课程编号对应多少条记录，如果只有一条，就删除对应的课程编号
                String sql01 =
                        "select count(*) as num from score where cid=? ";
                prep2 = conn.prepareStatement(sql01);
                prep2.setString(1, cid);
                rs2 = prep2.executeQuery();
                rs2.next();
                if (Integer.parseInt(rs2.getString("num")) == 1) {
                    System.out.println("要删除的编号：" + cid);
                    //找到了，用不了，先存起来
                    v.add(cid);
                }
            }
            if (prep2 != null) {
                prep2.close();
            }
            prep1.close();
            rs1.close();
            if (rs2 != null) {
                rs2.close();
            }
            //-----------------------------------------------------------------------------
            conn.setAutoCommit(false);
            //先删分数表
            String sql1 =
                    "delete from score where sid=?";
            prep1 = conn.prepareStatement(sql1);
            prep1.setString(1, _sid);
            prep1.executeUpdate();
            prep1.close();
            //再删学生表
            String sql2 =
                    "delete from student where sid=?";//------------------------
            prep1 = conn.prepareStatement(sql2);
            prep1.setString(1, _sid);
            prep1.executeUpdate();
            //最后删课程表
            for (String s : v) {
                stat = conn.createStatement();
                stat.executeUpdate
                        ("delete from course where cid= '" + s + "'");
            }
            conn.commit();
            conn.setAutoCommit(true);
            flag = true;


        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DataAccess.closeConnection(conn, prep1, rs1);
            }

        }
        return flag;
    }
}
