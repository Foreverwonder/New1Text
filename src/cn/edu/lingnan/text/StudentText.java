package cn.edu.lingnan.text;

import java.sql.SQLException;
import java.util.Vector;

import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dto.StudentDto;

public class StudentText {
		public static void main(String[] args) throws SQLException {
			StudentDao sd=new StudentDao();
//			System.out.println(sd.findStudentByNameAndPassword("zhangsan2","123"));
//			Vector<StudentDto> v=new Vector<StudentDto>();
//			v=sd.findAllStudent();
//			for(StudentDto s:v)
//				System.out.println(s.getSname());
			StudentDto s=new StudentDto();
//			s.setSid("s01");
//			s.setSname("zhangsan5");
//			s.setPassword("123");
//			s.setSuperuser(1);
//			System.out.println(sd.insertInfoToStudent(s));
//			System.out.println(sd.updataStudentSname(s));
//			System.out.println(sd.updataStudentPassword(s));
//			System.out.println(sd.updataStudentSuperuser(s));
			System.out.println(sd.deleteStudent("s05"));
		}
}
