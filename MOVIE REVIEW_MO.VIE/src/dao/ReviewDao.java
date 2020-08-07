package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ReviewBean;

public class ReviewDao {
	Connection con = JdbcUtil.getconnection();
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean upReview(ReviewBean rb) {
		String sql = "INSERT INTO MR VALUES(?,?,?,DEFAULT,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, rb.getRcode());
			pstmt.setNString(2, rb.getRid());
			pstmt.setNString(3, rb.getRnickname());
			pstmt.setNString(4, rb.getRdetail());
			pstmt.setInt(5, rb.getRscore());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<ReviewBean> showreview(String data) {
		String sql = "SELECT * FROM MR WHERE R_MLCODE = ?";
		ReviewBean rb = null;
		List<ReviewBean> rList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, data);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rb = new ReviewBean();
				rb.setRid(rs.getNString("r_mid"));
				rb.setRnickname(rs.getNString("r_mnickname"));
				rb.setRdate(rs.getNString("r_date"));
				rb.setRdetail(rs.getNString("r_detail"));
				rb.setRscore(rs.getInt("r_score"));
				rList.add(rb);
			}
			return rList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean updateReview(String id, String code, String rdetail) {
		String sql = "UPDATE MOVIE_REVIEW SET R_DETAIL=?,R_DATE=SYSDATE WHERE R_MID=? AND R_MLCODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, rdetail);
			pstmt.setNString(2, id);
			pstmt.setNString(3, code);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteMyreview(String id, String code) {
		String sql = "DELETE FROM MOVIE_REVIEW WHERE R_MID=? AND R_MLCODE = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, code);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean updateavg(String code) {
		String sql = "UPDATE ML SET ML_AVG =(SELECT TRUNC(AVG(R_SCORE),1) AS AVG FROM MR WHERE R_MLCODE=?)WHERE ML_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, code);
			pstmt.setNString(2, code);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int searchBlackList(String id) {
		String sql = "SELECT COUNT(*) as warncount FROM MB WHERE B_RID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
					int cnt = rs.getInt("warncount");
					return cnt;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<ReviewBean> searchBLreview(String data) {
		String sql = "SELECT DISTINCT(MR.R_MLCODE),TO_CHAR(MR.R_DETAIL) AS DETAIL FROM MR,MB WHERE MR.R_MID = ? AND MR.R_MLCODE IN (SELECT B_MLCODE FROM MB WHERE MB.B_RID = ?)";
		List<ReviewBean> rbList = new ArrayList<ReviewBean>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, data);
			pstmt.setNString(2, data);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewBean rb = new ReviewBean();
				rb.setRcode(rs.getNString("r_mlcode"));
				rb.setRdetail(rs.getNString("detail"));
				rbList.add(rb);
			}return rbList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<ReviewBean> showMyReview(String id) {
		String sql = "SELECT R_MLCODE,R_DATE,ML_NAME,R_DETAIL,R_SCORE FROM MR,ML WHERE R_MLCODE=ML_CODE AND MR.R_MID = ?";
		List<ReviewBean> rList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewBean rb = new ReviewBean();
				rb.setRcode(rs.getNString("r_mlcode"));
				rb.setRdate(rs.getNString("r_date"));
				rb.setMlname(rs.getNString("ml_name"));
				rb.setRdetail(rs.getNString("r_detail"));
				rb.setRscore(rs.getInt("r_score"));
				rList.add(rb);
			}return rList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
