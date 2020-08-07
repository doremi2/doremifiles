package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.NotifyBean;

public class NotifyDao {
 Connection con = JdbcUtil.getconnection();
 PreparedStatement pstmt;
 ResultSet rs;
public boolean notifyreview(String rcode, String rid, String nid) {
	String sql = "INSERT INTO MOVIE_NOTIFY VALUES(?,?,?)";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setNString(1, rcode);
		pstmt.setNString(2, rid);
		pstmt.setNString(3, nid);
		int result = pstmt.executeUpdate();
		if(result!=0) {
			return true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
public List<NotifyBean> showNotifyList() {
	String sql = "SELECT * FROM MR,MN WHERE MR.R_MLCODE=MN.N_RMLCODE AND MR.R_MID=MN.N_RMID";
	List<NotifyBean> nList = new ArrayList<>();
	try {
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			NotifyBean nb = new NotifyBean();
			nb.setNrmlcode(rs.getNString("n_rmlcode"));
			nb.setNrmid(rs.getNString("n_rmid"));
			nb.setNmid(rs.getNString("n_mid"));
			nb.setRdetail(rs.getNString("r_detail"));
			nList.add(nb);
		}return nList;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return null;
}
public boolean deleteNotify(String ncode, String nmid, String nrmid) {
	String sql = "DELETE FROM MOVIE_NOTIFY WHERE N_RMLCODE = ? AND N_RMID = ? AND N_MID = ? ";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setNString(1, ncode);
		pstmt.setNString(2, nrmid);
		pstmt.setNString(3, nmid);
		int result = pstmt.executeUpdate();
		if(result!=0) {
			return true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
public boolean appendblackList(String nrmid,String ncode) {
	String sql = "INSERT INTO MOVIE_BLACKLIST VALUES(?,?)";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setNString(1, nrmid);
		pstmt.setNString(2, ncode);
		int result = pstmt.executeUpdate();
		if(result!=0) {
			return true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return false;
}
 
}
