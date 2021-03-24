package cn.edu.lingnan.text;

import cn.edu.lingnan.dao.CourseDao;
import cn.edu.lingnan.dto.CourseDto;

import java.util.ArrayList;
import java.util.Arrays;

public class CourseText {
	public static void main(String[] args) {
		CourseDao cd =new CourseDao();
//		System.out.println(cd.findCnameByCid("c01"));
		CourseDto c =new CourseDto();
		c.setCid("c05");
		c.setCname("javaweb5");
		System.out.println(cd.insertInfoToCourse(c));
	}
}
