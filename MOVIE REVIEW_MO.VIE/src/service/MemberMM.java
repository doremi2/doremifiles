package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ForwardInfo;
import bean.MemberBean;
import dao.MemberDao;

public class MemberMM {
	HttpServletRequest request;
	HttpServletResponse response;
	ForwardInfo fi = null;
	MemberBean mb = null;
	MemberDao mDao = null;
	HttpSession session = null;
	
	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	public ForwardInfo loginAccess() {
		fi = new ForwardInfo();
		mDao = new MemberDao();
		session = request.getSession();
		mb = new MemberBean();
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		boolean result = mDao.loginAccess(mb);
		System.out.println(result);
		if(result==true) {
			session.setAttribute("id", mb.getId());
			session.setAttribute("nickname", mb.getNickname());
			session.setAttribute("workList", makeworkList(mb.getId()));
			fi.setPath("movieMain");
			fi.setRedirect(false);
		}else {
			request.setAttribute("loginFailMsg", "<b id='errorlogin'>ID/PW가 일치하지 않습니다.</b><br/><br/>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		}
		return fi;
	}
	private String makeworkList(String id) {
		StringBuilder sb = new StringBuilder();
		if(id.equals("z1234")) {
		sb.append("<a href = 'goupLoadmovieList'>영화등록</a>");
		sb.append("<a href = 'showBlackList'>블랙리스트</a>");
		sb.append("<a href = 'showAllMovieList'>전체영화리스트</a><br/>");
		sb.append("<a href = 'showNotify'>신고내역</a>");
		sb.append("<a href = 'logout'>로그아웃</a>");
		}else {
			sb.append("<a href = 'showMyReview'>내가 쓴 리뷰</a>");
			sb.append("<a href = 'updateMyInfo'>내정보</a>");
			sb.append("<a href = 'logout'>로그아웃</a>");
		}
		return sb.toString();
	}
	public ForwardInfo joinMember() {
		fi = new ForwardInfo();
		mDao = new MemberDao();
		MemberBean mb = new MemberBean();
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		mb.setName(request.getParameter("name"));
		mb.setNickname(request.getParameter("nickname"));
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setGender(request.getParameter("gender"));
		boolean result = mDao.joinMember(mb);
		if(result) {
			request.setAttribute("joinMsg", "<script>"+"alert('회원가입이 완료되었습니다.');"+"</script>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		}else {
			request.setAttribute("joinFailMsg", "<script>"+"alert('양식을 다시 작성해주세요.');"+"</script>");
			fi.setPath("Moviejoin");
			fi.setRedirect(false);
		}
		return fi;
	}
	public ForwardInfo logout() {
		session = request.getSession();
		session.invalidate();
		fi = new ForwardInfo();
		request.setAttribute("logout", "<script>"+"alert('정상적으로 로그아웃 되었습니다.')"+"</script>");
		fi.setPath("movieMain");
		fi.setRedirect(false);
		return fi;
	}
	public ForwardInfo updateMyInfo() {
		fi = new ForwardInfo();
		session = request.getSession();
		mDao = new MemberDao();
		MemberBean mb = new MemberBean();
		String id = session.getAttribute("id").toString();
		mb = mDao.upDateMyInfo(id);
		if(mb!=null) {
			request.setAttribute("upDate", upDateMyInfo(mb));
			fi.setPath("updateMyInfo.jsp");
			fi.setRedirect(false);
		}else {
			request.setAttribute("upDate", "내 정보를 불러올 수 없습니다.");
			fi.setPath("updateMyInfo.jsp");
			fi.setRedirect(false);
		}
		return fi;
	}
	private String upDateMyInfo(MemberBean mb) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>이름</td>");
		sb.append("<td>"+mb.getName()+"</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>닉네임</td>");
		sb.append("<td id='nickname'><p>"+mb.getNickname()+"</p></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>나이</td>");
		sb.append("<td>"+mb.getAge()+"</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>성별</td>");
		sb.append("<td>"+mb.getGender()+"</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type='button' id='changebtn' onclick='changetag()' value='닉네임 수정'/>");
		sb.append("<input type='button' id='goMainbtn' onclick='goMain()' value='메인으로가기'/><br/>");
		return sb.toString();
	}
	public ForwardInfo updatenickname() {
		fi = new ForwardInfo();
		mDao = new MemberDao();
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		String nickname = request.getParameter("nickname");
		boolean result = mDao.updatenickname(id,nickname);
		if(result) {
			request.setAttribute("updateMsg", "<script>"+"alert('닉네임이 수정되었습니다.')"+"</script>");
			session.setAttribute("nickname", nickname);
			fi.setPath("updateMyInfo");
			fi.setRedirect(false);
		}else {
			request.setAttribute("updateMsg", "<script>"+"alert('닉네임 수정 실패.')"+"</script>");
			fi.setPath("updateMyInfo");
			fi.setRedirect(false);
		}
		return fi;
	}
	public ForwardInfo showBlackList() {
		fi = new ForwardInfo();
		mDao = new MemberDao();
		List<MemberBean> bList = new ArrayList<>();
		bList = mDao.searchBlackList();
		if(bList!=null) {
			request.setAttribute("showBlackList", showBlackList(bList));
			fi.setPath("MemberList.jsp");
			fi.setRedirect(false);
		}else {
			request.setAttribute("showBlackList","<script>"+"alert('블랙리스트가 없습니다.')"+"</script>");
			fi.setPath("MemberList.jsp");
			fi.setRedirect(false);
		}
		return fi;
	}
	private String showBlackList(List<MemberBean> bList) {
		StringBuilder sb= new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th>회원 아이디</th>");
		sb.append("<th>경고 횟수</th>");
		sb.append("<th>블랙리스트 해제</th>");
		sb.append("</tr>");
		for(int i = 0; i<bList.size(); i++) {
			mb = bList.get(i);
			sb.append("<tr>");
			sb.append("<td><div class='detailbid' data-code='"+mb.getBid()+"'>"+mb.getBid()+"</div></td>");
			sb.append("<td>"+mb.getWarncount()+"</td>");
			sb.append("<td><form action ='deleteBL' method='post'>");
			sb.append("<input type='hidden' name='bid' value='"+mb.getBid()+"'/>");
			sb.append("<button id='notifybtn'>해제</button></form>");
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	public ForwardInfo deleteBList() {
		fi = new ForwardInfo();
		mDao = new MemberDao();
		String bid = request.getParameter("bid");
		boolean result = mDao.deleteBList(bid);
		if(result) {
			request.setAttribute("deleteBMsg", "<script>"+"alert('해당 아이디를 블랙리스트 해제하였습니다.')"+"</script>");
		}else {
			request.setAttribute("deleteBMsg", "<script>"+"alert('해제 실패.')"+"</script>");
		}
		fi.setPath("showBlackList");
		fi.setRedirect(false);
		return fi;
	}
	
//	

}
