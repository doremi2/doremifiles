package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ReviewBean;
import bean.UploadMovie;

public class UploadDao {
	Connection con = JdbcUtil.getconnection();
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean upLoadMovie(UploadMovie um) {
		String sql = "INSERT INTO ML VALUES(LPAD(ML_SEQ.NEXTVAL,10,0),DEFAULT,?,?,TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,DEFAULT)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, um.getMlname());
			pstmt.setNString(2, um.getMlgenre());
			pstmt.setNString(3, um.getMlplayday());
			pstmt.setNString(4, um.getMlproducer());
			pstmt.setNString(5, um.getMldetail());
			pstmt.setNString(6, um.getMlimg());
			pstmt.setNString(7, um.getMlvideo());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	public List<UploadMovie> showMovieList() {
		String sql = "SELECT * FROM ML";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<UploadMovie> uList = new ArrayList<>();
			while (rs.next()) {
				UploadMovie um = new UploadMovie();
				um.setMlcode(rs.getNString("ml_code"));
				um.setMlimg(rs.getNString("ml_img"));
				um.setMlname(rs.getNString("ml_name"));
				um.setMlplayday(rs.getNString("ml_playlist"));
				um.setMlgenre(rs.getNString("ml_genre"));
				um.setMl_avg(rs.getNString("ml_avg"));
				uList.add(um);
			}
			return uList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public UploadMovie showDetailmovie(String mlcode) {
		String sql = "SELECT * FROM ML WHERE ML_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mlcode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UploadMovie um = new UploadMovie();
				um.setMlcode(rs.getNString("ML_CODE"));
				um.setMldate(rs.getNString("ML_DATE"));
				um.setMlname(rs.getNString("ML_NAME"));
				um.setMldetail(rs.getNString("ML_DETAIL"));
				um.setMlgenre(rs.getNString("ML_GENRE"));
				um.setMlproducer(rs.getNString("ML_PRODUCER"));
				um.setMlplayday(rs.getNString("ML_PLAYLIST"));
				um.setMlimg(rs.getNString("ML_IMG"));
				um.setMlvideo(rs.getNString("ML_VIDEO"));
				um.setMl_avg(rs.getNString("ML_AVG"));
				return um;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean deletemovieList(String mlcode) {
		String sql = "DELETE FROM MOVIE_LIST WHERE ML_CODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mlcode);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<UploadMovie> showReviewList() {
		String sql = "SELECT * FROM ML";
		List<UploadMovie> uList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UploadMovie um = new UploadMovie();
				um.setMlcode(rs.getNString("ml_code"));
				um.setMldate(rs.getNString("ml_date"));
				um.setMlname(rs.getNString("ml_name"));
				um.setMlimg(rs.getNString("ml_img"));
				uList.add(um);
			}
			return uList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deletereviewList(String mlcode) {
		String sql = "DELETE FROM MOVIE_REVIEW WHERE R_MLCODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mlcode);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
