package cn.edu.lingnan.text;

import cn.edu.lingnan.dao.ScoreDao;

public class ScoreText {
	public static void main(String[] args) {
	ScoreDao st=new ScoreDao();
	System.out.println(st.findScoreBySidAndCid("s01", "c02"));
	}
}
