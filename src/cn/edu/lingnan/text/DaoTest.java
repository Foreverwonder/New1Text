package cn.edu.lingnan.text;
/**
 * 完整功能：查找所有学生，查找所有课程
 *          添加学生，添加课程
 *          删除学生，删除课程
 */

import cn.edu.lingnan.dao.CourseDao;
import cn.edu.lingnan.dao.ScoreDao;
import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dto.CourseDto;
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
        //注意，自己并没有写2与3，而是按条件查找2，按条件查找3。
        System.out.println("----1查看所有用户--2查看所有课程--3查看所有成绩--4回到上一级--");
        while (scanf.hasNextLine()) {
            String str = scanf.nextLine();
            if (str.equals("1")) {
                findAllStudent();
            } else if (str.equals("2")) {
                findAllCourse();
            } else if (str.equals("3")) {
                findAllScore();
            } else if (str.equals("4")) {
                break;
            } else {
                System.out.println("您输入的信息有误！请重新输入！");
            }
            //下面这两行要不
            System.out.println("-------------------Please select the corresponding number to test（Find Menu）------------- ");
            System.out.println("----1查看所有用户--2查看所有课程--3查看所有成绩--4回到上一级--");

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
//                insertScore();
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
//                updateStudent();
            } else if (str.equals("2")) {
//                updateCourse();
            } else if (str.equals("3")) {
//                updateScore();
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

    //查找所有课程（改）
    public static void findAllCourse() {
        Vector<CourseDto> v = new Vector<>();
        v = cd.findAllCourse();
        System.out.println("-----------所有学生信息如下----------------");
        System.out.println("课程编号 \t课程名");
        for (CourseDto s : v) {
            System.out.println(" \t" +s.getCid() + " \t" + s.getCname());
        }
        System.out.println("--------------------------------------------");
    }
    //----------------------------------------------------
    public static void findAllScore() {
//        Vector<CourseDto> v=new Vector<>();
//        v=scd.findScoreBySidAndCid();
    }
//-------------------------------------


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

    //------------------------------------------------
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

    //-----------------------删除学生（三级菜单）------------------------------------------
    public static void deleteStudent() throws SQLException {
        System.out.println("请输入要删除的学生ID：");
        String _sid=scanf.nextLine();
        if(sd.deleteStudent(_sid)==true){
            System.out.println("删除学生信息成功");
        }
        else
            System.out.println("删除学生信息失败");
    }

    //-----------------------删除课程（三级菜单）------------------------------------------
    public static void deleteCourse() throws SQLException {
        System.out.println("请输入要删除的课程ID：");
        String _cid=scanf.nextLine();
        if(cd.deleteCourse(_cid)==true){
            System.out.println("删除课程信息成功");
        }
        else
            System.out.println("删除课程信息失败");
    }
}