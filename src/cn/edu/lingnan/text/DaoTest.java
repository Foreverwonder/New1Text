package cn.edu.lingnan.text;
/**
 * 完整功能：查找所有学生，查找所有课程、按sid和cid查成绩
 * 添加学生，添加课程、按sid查学生
 * 删除学生，删除课程、按cid查课程
 * 几个方法的DataAccess的关闭方法有待优化
 *
 * <p>
 * 没写: ScoreDao（updataScore）
 * StudentDao(updataStudentSname、updataStudentPassword、updataStudentSuperuser）
 * CourseDao(updataCourse)
 *
 * 几个方法的DataAccess的连接数据库方法，关闭方法有待优化
 */

import cn.edu.lingnan.dao.CourseDao;
import cn.edu.lingnan.dao.ScoreDao;
import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dto.CourseDto;
import cn.edu.lingnan.dto.ScoreDto;
import cn.edu.lingnan.dto.StudentDto;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class DaoTest {
    public static Scanner scanf = new Scanner(System.in);
    public static StudentDao sd = new StudentDao();
    public static ScoreDao scd = new ScoreDao();
    public static CourseDao cd = new CourseDao();

    public static void main(String[] args) throws SQLException {
        System.out.println("-------------------Welcome to the student achievement management system!------------- ");
        System.out.println("-------------------Please select the corresponding number to test（Main Menu）------------- ");
        System.out.println("--1.See the information--2.Add the information--3.Modify the information--4.Delete the information--5.exit------ ");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                find();
            } else if (str.equals("2")) {
                insert();
            } else if (str.equals("3")) {
                update();
            } else if (str.equals("4")) {
                delete();
            } else if (str.equals("5")) {
                System.out.println("GoodBye~ I'm looking forward to see you next time.");
                System.exit(0);
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            System.out.println("-------------------Welcome to the student achievement management system!------------- ");
            System.out.println("-------------------Please select the corresponding number to test（Main Menu）------------- ");
            System.out.println("--1.See the information--2.Add the information--3.Modify the information--4.Delete the information--5.exit------ ");
        }
    }

    //----------------------查找二级菜单------------------------
    public static void find() {
        System.out.println("------------Please select the corresponding number to test（Find Menu of 2th）------------- ");
//        System.out.println("----1查看所有用户--2查看所有课程--3查看所有成绩--4回到上一级--");
        System.out.println("----1查看用户--2查看课程--3查看成绩--4回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
//                findAllStudent();增加一级菜单
                findStudent();
            } else if (str.equals("2")) {
//                findAllCourse();增加一级菜单
                findCourse();
            } else if (str.equals("3")) {
//                findAllScore();增加一级菜单
                findScore();
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            //下面这两行要不
            System.out.println("-------------------Please select the corresponding number to test（Find Menu）------------- ");
            System.out.println("----1查看用户--2查看课程--3查看成绩--4回到上一级--");
        }
    }

    //--------------增加二级菜单------------------------
    public static void insert() {
        System.out.println("------------Please select the corresponding number to test（Add Menu of 2th）------------- ");
        System.out.println("----1增加用户--2增加课程--3增加成绩--4回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                insertStudent();
            } else if (str.equals("2")) {
                insertCourse();
            } else if (str.equals("3")) {
                insertScore();
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            //下面这两行要不
            System.out.println("------------Please select the corresponding number to test（Add Menu of 2th）------------- ");
            System.out.println("----1增加用户--2增加课程--3增加成绩--4回到上一级--");

        }
    }

    //-----------------更新二级菜单----------------------------------------------
    public static void update() {
        System.out.println("------------Please select the corresponding number to test（Update Menu of 2th）------------- ");
        System.out.println("----1更新用户--2更新课程--3更新成绩--4回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                updateStudent();
            } else if (str.equals("2")) {
                updateCourse();
            } else if (str.equals("3")) {
                updateScore();
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            //下面这两行要不
            System.out.println("------------Please select the corresponding number to test（Update Menu of 2th）------------- ");
            System.out.println("----1更新用户--2更新课程--3更新成绩--4回到上一级--");
        }
    }

    //---------------------------删除二级菜单------------------------------------------
    public static void delete() throws SQLException {
        System.out.println("------------Please select the corresponding number to test（Delete Menu of 2th）------------- ");
        System.out.println("----1删除用户--2删除课程--3删除成绩--4回到上一级--");//其实只能1删除学生，2、3办不到
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                deleteStudent();
            } else if (str.equals("2")) {
                deleteCourse();
            } else if (str.equals("3")) {
//                deleteScore();
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            //下面这两行要不
            System.out.println("------------Please select the corresponding number to test（Delete Menu of 2th）------------- ");
            System.out.println("----1删除用户--2删除课程--3删除成绩--4回到上一级--");
        }
    }


    //----------------------三级菜单-----------------------------------

    //查找（三级菜单）
    public static void findStudent() {
        System.out.println("------------Please select the corresponding number to test（Find Menu of 3th）------------- ");
        System.out.println("----1通过姓名和密码查学生--2通过ID查找学生--3查看所有学生--4回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                String _sname = null;
                String _password = null;
                System.out.println("请输入需要查找的学生姓名：");
                _sname = scanf.nextLine();
                System.out.println("请输入需要查找的学生密码：");
                _password = scanf.nextLine();
                if (sd.findStudentByNameAndPassword(_sname, _password) == true) {
                    System.out.println("该学生在本校存在");
                }
            } else if (str.equals("2")) {
                String _sid = null;
                System.out.println("请输入需要查找的学生ID：");
                _sid = scanf.nextLine();
                System.out.println(sd.findStudentBySid(_sid));
            } else if (str.equals("3")) {
                findAllStudent();
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            System.out.println("------------Please select the corresponding number to test（Find Menu of 3th）------------- ");
            System.out.println("----1通过姓名和密码查学生--2通过ID查找学生--3查看所有学生--4回到上一级--");

        }
    }

    public static void findCourse() {
        System.out.println("------------Please select the corresponding number to test（Find Menu of 3th）------------- ");
        System.out.println("----1通过课程号查课程--2查看所有课程--3回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                String _cid = null;
                System.out.println("请输入需要查找的课程ID：");
                _cid = scanf.nextLine();
                System.out.println(cd.findCnameByCid(_cid));
            } else if (str.equals("2")) {
                findAllCourse();
            } else if (str.equals("3")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            System.out.println("------------Please select the corresponding number to test（Find Menu of 3th）------------- ");
            System.out.println("----1通过课程号查课程--2查看所有课程--3回到上一级--");
        }
    }

    public static void findScore() {//未完成
        System.out.println("------------Please select the corresponding number to test（Find Menu of 3th）------------- ");
        System.out.println("----1通过学生号课程号查成绩--2查看所有成绩--3回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                String _sid = null;
                String _cid = null;
                System.out.println("请输入需要查找成绩的学生ID：");
                _sid = scanf.nextLine();
                System.out.println("请输入需要查找成绩的课程ID：");
                _cid = scanf.nextLine();
                System.out.println(scd.findScoreBySidAndCid(_sid, _cid));
            } else if (str.equals("2")) {
                findAllScore();
            } else if (str.equals("3")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            System.out.println("------------Please select the corresponding number to test（Find Menu of 3th）------------- ");
            System.out.println("----1通过学生号课程号查成绩--2查看所有成绩--3回到上一级--");
        }
    }

    //查找所有学生
    public static void findAllStudent() {
        Vector<StudentDto> v = new Vector<>();
        v = sd.findAllStudent();
        System.out.println("-----------所有学生信息如下----------------");
        System.out.println("学生编号 学生姓名 \t学生密码 学生权限");
        for (StudentDto s : v) {
            System.out.println(s.getSid() + " \t" + s.getSname() + " \t" + s.getPassword() + " \t" + s.getSuperuser());
        }
        System.out.println("--------------------------------------------");
    }

    //查找所有分数
    public static void findAllScore() {
        Vector<ScoreDto> v = new Vector<ScoreDto>();
        v = scd.findAllScore();
        System.out.println("-----------所有成绩信息如下----------------");
        System.out.println("学生编号 课程编号 \t成绩");
        for (ScoreDto s : v) {
            System.out.println(s.getSid() + " \t" + s.getCid() + " \t" + s.getScore());
        }
        System.out.println("--------------------------------------------");
    }

    //查找所有课程（改）
    public static void findAllCourse() {
        Vector<CourseDto> v = new Vector<>();
        v = cd.findAllCourse();
        System.out.println("-----------所有学生信息如下----------------");
        System.out.println("课程编号 \t课程名");
        for (CourseDto s : v) {
            System.out.println(" \t" + s.getCid() + " \t" + s.getCname());
        }
        System.out.println("--------------------------------------------");
    }

    //--------增加学生（三级菜单）---------------------------
    public static void insertStudent() {
        StudentDto s = new StudentDto();
        System.out.println("请输入需要新增的学生ID：");
        s.setSid(scanf.nextLine());
        System.out.println("请输入需要新增的学生姓名：");
        s.setSname(scanf.nextLine());
        System.out.println("请输入需要新增的学生密码：");
        s.setPassword(scanf.nextLine());
        System.out.println("请输入需要新增的学生权限：");
        //下一行转换数字就很妙：
//        s.setSuperuser(scanf.nextInt());这样会不方便，正确处理方法见下一行
        s.setSuperuser(Integer.parseInt(scanf.nextLine()));
        if (sd.insertInfoToStudent(s) == 1) {
            System.out.println("新增学生信息成功！");
        } else
            System.out.println("新增学生信息失败");
    }

    //---------------增加课程（三级）---------------------------------
    public static void insertCourse() {
        CourseDto c = new CourseDto();
        System.out.println("请输入需要新增的课程ID：");
        c.setCid(scanf.nextLine());
        System.out.println("请输入需要新增的课程名称");
        c.setCname(scanf.nextLine());
        if (cd.insertInfoToCourse(c) == 1) {
            System.out.println("新增课程信息成功！");
        } else
            System.out.println("新增课程信息失败");
    }

    //---------------增加分数（三级）---------------------------------
    public static void insertScore() {
        ScoreDto s = new ScoreDto();
        System.out.println("请输入需要新增分数的sid：");
        s.setSid(scanf.nextLine());
        System.out.println("请输入需要新增分数的cid");
        s.setCid(scanf.nextLine());
        System.out.println("请输入需要新增的分数score");
        s.setScore(Integer.parseInt(scanf.nextLine()));
        if (scd.insertInfotoScore(s) == 1) {
            System.out.println("新增课程信息成功！");
        } else if (scd.insertInfotoScore(s) == 0) {
            System.out.println("新增课程信息失败(没学生没课程)");
        } else if (scd.insertInfotoScore(s) == 2) {
            System.out.println("新增课程信息失败(有学生没课程)");
        } else if (scd.insertInfotoScore(s) == 3) {
            System.out.println("新增课程信息失败(有课程没学生)");
        } else if (scd.insertInfotoScore(s) == 4) {
            System.out.println("新增课程信息失败(主键约束)");
        } else
            System.out.println("新增课程信息失败(未知错误)");
    }
    //---------------增加学生（三级三功能）---------------------------------
    public static void updateStudent() {
        System.out.println("------------Please select the corresponding number to test（Update Menu of 3th）------------- ");
        System.out.println("----1更新学生名字--2更新学生密码--3更新学生权限--4回到上一级--");
        StudentDto s = new StudentDto();
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                System.out.println("请输入要更改的学生ID");
                s.setSid(scanf.nextLine());
                System.out.println("请输入要更改的学生名字");
                s.setSname(scanf.nextLine());
                if (sd.updataStudentSname(s) == 1) {
                    System.out.println("更改名字成功");
                } else System.out.println("更改名字失败");
            } else if (str.equals("2")) {
                System.out.println("请输入要更改的学生ID");
                s.setSid(scanf.nextLine());
                System.out.println("请输入要更改的学生密码");
                s.setPassword(scanf.nextLine());
                if (sd.updataStudentPassword(s) == 1) {
                    System.out.println("更改密码成功");
                } else System.out.println("更改密码失败");
            } else if (str.equals("3")) {
                System.out.println("请输入要更改的学生ID");
                s.setSid(scanf.nextLine());
                System.out.println("请输入要更改的学生权限");
                s.setSuperuser(Integer.parseInt(scanf.nextLine()));
                if (sd.updataStudentSuperuser(s) == 1) {
                    System.out.println("更改权限成功");
                } else System.out.println("更改权限失败");
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            //下面这两行要不
            System.out.println("------------Please select the corresponding number to test（Update Menu of 3th）------------- ");
            System.out.println("----1更新学生名字--2更新学生密码--3更新学生权限--4回到上一级--");
        }
    }
    //-----------------------更新课程（三级菜单）------------------------------------------
    public static void updateCourse() {
        CourseDto c = new CourseDto();
        System.out.println("请输入更新课程名的cid：");
        c.setCid(scanf.nextLine());
        System.out.println("请输入需要更新的cname：");
        c.setCname(scanf.nextLine());
        if (cd.updataCourse(c) == 1) {
            System.out.println("更新课程成功");
        } else
            System.out.println("更新失败");
    }

    //-----------------------更新分数表---------------------------------------------------------------------
    public static void updateScore() {
        ScoreDto c = new ScoreDto();
        System.out.println("请输入需要更新分数的sid：");
        c.setSid(scanf.nextLine());
        System.out.println("请输入需要更新分数的cid：");
        c.setCid(scanf.nextLine());
        System.out.println("请输入需要更新的分数");
        c.setScore(Integer.parseInt(scanf.nextLine()));
        if (scd.updataScore(c) == 1) {
            System.out.println("更新分数成功");
        } else
            System.out.println("更新失败");
    }


    //-----------------------删除学生（三级菜单）------------------------------------------
    public static void deleteStudent() throws SQLException {
        System.out.println("请输入要删除的学生ID：");
        String _sid = scanf.nextLine();
        if (sd.deleteStudent(_sid) == true) {
            System.out.println("删除学生信息成功");
        } else
            System.out.println("删除学生信息失败");
    }

    //-----------------------删除课程（三级菜单）------------------------------------------
    public static void deleteCourse() throws SQLException {
        System.out.println("请输入要删除的课程ID：");
        String _cid = scanf.nextLine();
        if (cd.deleteCourse(_cid) == true) {
            System.out.println("删除课程信息成功");
        } else
            System.out.println("删除课程信息失败");
    }
}
