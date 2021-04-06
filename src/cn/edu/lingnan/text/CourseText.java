package cn.edu.lingnan.text;

import cn.edu.lingnan.dao.CourseDao;
import cn.edu.lingnan.dto.CourseDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class CourseText {
	public static void main(String[] args) throws SQLException {
		CourseDao cd =new CourseDao();
//		System.out.println(cd.findCnameByCid("c01"));
		CourseDto c =new CourseDto();
//		c.setCid("c02");
//		c.setCname("javaweb5");
//		System.out.println(cd.deleteCourse("c02"));
//		System.out.println(cd.insertInfoToCourse(c));
//		System.out.println(cd.updataCourse(c));
	}
}
