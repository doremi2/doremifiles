package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MemberBean;

public class MemberDao {
	Connection con = JdbcUtil.getconnection();
	PreparedStatement pstmt;
	ResultSet rs;
	
	public boolean loginAccess(MemberBean mb) {
		String sql = "SELECT * FROM MM WHERE M_ID = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(mb.getPw().equals(rs.getNString("M_PW"))) {
					mb.setNickname(rs.getNString("M_NICKNAME"));
					System.out.println(rs.getNString("M_NICKNAME"));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean joinMember(MemberBean mb) {
		String sql = "INSERT INTO MM VALUES(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			pstmt.setNString(2, mb.getPw());
			pstmt.setNString(3, mb.getName());
			pstmt.setNString(4, mb.getNickname());
			pstmt.setInt(5, mb.getAge());
			pstmt.setNString(6, mb.getGender());
			int result = pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public MemberBean upDateMyInfo(String id) {
		String sql = "SELECT * FROM MOVIE_MEMBER WHERE M_ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setName(rs.getNString("m_name"));
				mb.setNickname(rs.getNString("m_nickname"));
				mb.setAge(rs.getInt("m_age"));
				mb.setGender(rs.getNString("m_gender"));
				return mb;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean updatenickname(String id, String nickname) {
		String sql = "UPDATE MM SET M_NICKNAME = ? WHERE M_ID = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, nickname);
			pstmt.setNString(2, id);
			int result = pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public List<MemberBean> searchBlackList() {
		String sql = "SELECT b_rid,COUNT(*) as warncount FROM MB group by b_rid";
		List<MemberBean> bList = new ArrayList<MemberBean>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setBid(rs.getNString("b_rid"));
				mb.setWarncount(rs.getInt("warncount"));
				bList.add(mb);
			}return bList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteBList(String bid) {
		String sql = "DELETE FROM MOVIE_BLACKLIST WHERE B_RID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bid);
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
