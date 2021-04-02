package cn.edu.lingnan.text;

import cn.edu.lingnan.util.DataAccess;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class trashBin_BlobClobTest_1 {

	public static void main(String[] args) throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = DataAccess.getConnection();
			String sql =
					"insert into blobtable values(?,?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, 1);
			File f = new File("d:\\z.jpg");
			FileInputStream fis = new FileInputStream(f);
			prep.setBinaryStream(2, fis, (int) f.length());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			DataAccess.closeConnection(conn, prep, rs);
		}

//------------------------------------------------取/---------------------------------------------------------------------------

		try{
			conn = DataAccess.getConnection();
			String sql =
					"select * from blobtable";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			rs.next();

			System.out.println(rs.getInt("id"));
			InputStream is = rs.getBinaryStream("binaryfile");
			File f = new File("d:\\zcopy.jpg");
			FileOutputStream fos = new FileOutputStream(f);
			int i = 0;
			while ((i = is.read()) != -1)
				fos.write(i);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}finally
		{
			DataAccess.closeConnection(conn,prep,rs);
		}
	}
}
