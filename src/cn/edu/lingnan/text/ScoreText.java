package cn.edu.lingnan.text;

import cn.edu.lingnan.dao.ScoreDao;
import cn.edu.lingnan.dto.ScoreDto;

//对score类的测试
public class ScoreText {
	public static void main(String[] args) {
//	ScoreDao st=new ScoreDao();
		ScoreDao sd=new ScoreDao();
		ScoreDto s=new ScoreDto();
//	System.out.println(st.findScoreBySidAndCid("s01", "c02"));
//		System.out.println(sd.findScoreBySidAndCid("s01", "c02"));
//		s.setSid("s05");
//		s.setCid("c05");
//		s.setScore(99);
//		System.out.println(sd.insertInfotoScore(s));
		s.setSid("s05");
		s.setCid("c05");
		s.setScore(66);
		System.out.println(sd.updataScore(s));
	}
}
