package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ForwardInfo;
import dao.NotifyDao;

public class NotifyMM {
	HttpServletRequest request;
	HttpServletResponse response;
	ForwardInfo fi = null;
	HttpSession session = null;
	NotifyDao nDao = null;
	NotifyBean nb = null;
	public NotifyMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	public ForwardInfo notifyreview() {
		fi = new ForwardInfo();
		session = request.getSession();
		nDao = new NotifyDao();
		if(session.getAttribute("id")==null) {
			request.setAttribute("nullpoint", "<script>"+"alert('로그인이 필요합니다.')"+"</script>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		}else {
		String rcode = request.getParameter("rcode");
		String rid = request.getParameter("rid");
		String nid = session.getAttribute("id").toString();
		System.out.println(nid+"/"+rid+"/"+rcode);
		boolean result = nDao.notifyreview(rcode,rid,nid);
		if(result) {
			request.setAttribute("mlcode", rcode);
			request.setAttribute("notifyreivewMsg", "<script>"+"alert('해당 리뷰를 신고하였습니다.')"+"</script>");
		}else {
			request.setAttribute("mlcode", rcode);
			request.setAttribute("notifyreivewMsg", "<script>"+"alert('신고실패.')"+"</script>");
		}
		fi.setPath("showDetailmovie");
		fi.setRedirect(false);
		}
		return fi;
	}
	public ForwardInfo showNotify() {
		fi = new ForwardInfo();
		session = request.getSession();
		if(session.getAttribute("id")==null) {
			request.setAttribute("showListerror", "<script>"+"alert('관리자만 이용가능합니다.')"+"</script>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		}else if(session.getAttribute("id").toString().equals("z1234")) {
			NotifyBean nb = new NotifyBean();
			List<NotifyBean> nList = new ArrayList<>();
			nDao = new NotifyDao();
			nList = nDao.showNotifyList();
			if(nList!=null) {
				request.setAttribute("nList", showNotifyList(nList));
				fi.setPath("notifyList.jsp");
				fi.setRedirect(false);
			}else {
				request.setAttribute("nList", "신고 내역이 없습니다.");
				fi.setPath("notifyList.jsp");
				fi.setRedirect(false);
			}
		}else {
			request.setAttribute("showListerror", "<script>"+"alert('관리자만 이용가능합니다.')"+"</script>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		}
		return fi;
	}
	private String showNotifyList(List<NotifyBean> nList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th>게시물번호</th>");
		sb.append("<th>신고자</th>");
		sb.append("<th>피신고자</th>");
		sb.append("<th>신고내용</th>");
		sb.append("<th>신고내역삭제</th>");
		sb.append("<th>블랙리스트에 피신고자 추가</th>");
		sb.append("</tr>");
		for(int i = 0; i<nList.size();i++) {
			nb = nList.get(i);
			sb.append("<tr>");
			sb.append("<td>"+nb.getNrmlcode()+"</td>");
			sb.append("<td>"+nb.getNmid()+"</td>");
			sb.append("<td>"+nb.getNrmid()+"</td>");
			sb.append("<td>"+nb.getRdetail()+"</td>");
			sb.append("<td><form action='deleteNotify' method='post'>");
			sb.append("<input type='hidden' name='ncode' value='"+nb.getNrmlcode()+"'>");
			sb.append("<input type='hidden' name='nmid' value='"+nb.getNmid()+"'>");
			sb.append("<input type='hidden' name='nrmid' value='"+nb.getNrmid()+"'>");
			sb.append("<button>삭제</button></form></td>");
			sb.append("<td><form action='appendblacklist' method='post'>");
			sb.append("<input type='hidden' name='ncode' value='"+nb.getNrmlcode()+"'>");
			sb.append("<input type='hidden' name='nmid' value='"+nb.getNmid()+"'>");
			sb.append("<input type='hidden' name='nrmid' value='"+nb.getNrmid()+"'>");
			sb.append("<button>블랙리스트 추가</button></form></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	public ForwardInfo deleteNotify() {
		fi = new ForwardInfo();
		nDao = new NotifyDao();
		String ncode = request.getParameter("ncode");
		String nmid = request.getParameter("nmid");
		String nrmid = request.getParameter("nrmid");
		boolean result = nDao.deleteNotify(ncode,nmid,nrmid);
		if(result) {
			request.setAttribute("deleteNotifyMsg", "<script>"+"alert('해당 신고가 삭제되었습니다.')"+"</script>");
		}else {
			request.setAttribute("deleteNotifyMsg", "<script>"+"alert('삭제실패')"+"</script>");
		}
		fi.setPath("showNotify");
		fi.setRedirect(false);
		return fi;
	}
	public ForwardInfo appendblackList() {
		fi = new ForwardInfo();
		nDao = new NotifyDao();
		String ncode = request.getParameter("ncode");
		String nmid = request.getParameter("nmid");
		String nrmid = request.getParameter("nrmid");
		boolean addresult = nDao.appendblackList(nrmid,ncode);
		if(addresult) {
			boolean result = nDao.deleteNotify(ncode, nmid, nrmid);
			if(result) {
				request.setAttribute("addblacklistMsg", "<script>"+"alert('블랙리스트에 추가되었습니다.')"+"</script>");
			}else {
				request.setAttribute("addblacklistMsg", "<script>"+"alert('블랙리스트 추가 실패')"+"</script>");
			}
		}else {
			request.setAttribute("addblacklistMsg", "<script>"+"alert('블랙리스트 추가 실패')"+"</script>");
		}
		fi.setPath("showNotify");
		fi.setRedirect(false);
		return fi;
	}

}
