package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.ForwardInfo;
import bean.MemberBean;
import bean.ReviewBean;
import dao.ReviewDao;

public class ReviewMM {
	HttpServletRequest request;
	HttpServletResponse response;
	ForwardInfo fi = null;
	ReviewBean rb = null;
	ReviewDao rDao = null;
	HttpSession session = null;
	String json = null;

	public ReviewMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo upReview() {
		fi = new ForwardInfo();
		rDao = new ReviewDao();
		rb = new ReviewBean();
		session = request.getSession();
		if (session.getAttribute("id") == null) {
			request.setAttribute("nullIDMsg", "<script>" + "alert('로그인 후 이용가능합니다.')" + "</script>");
			fi.setPath("movieMain.jsp");
			fi.setRedirect(false);
		} else {
			if (request.getParameter("reviewDetail") == null) {
				request.setAttribute("nullreview", "<script>" + "alert('내용을 입력해주세요')" + "</script>");
				fi.setPath("detailmovie.jsp");
				fi.setRedirect(false);
			} else {
				rb.setRcode(request.getParameter("mcode"));
				rb.setRid(session.getAttribute("id").toString());
				String id = session.getAttribute("id").toString();
				int cnt = rDao.searchBlackList(id);
				System.out.println("cnt:" + cnt);
				if (cnt >= 3) {
					request.setAttribute("UpReviewMsg",
							"<script>" + "alert('블랙리스트에 등록되어 리뷰를 등록할 수 없습니다. 관리자에게 문의하세요.')" + "</script>");
					request.setAttribute("mlcode", rb.getRcode());
					fi.setPath("showDetailmovie");
					fi.setRedirect(false);
				} else if (session.getAttribute("nickname") == null) {
					rb.setRnickname(session.getAttribute("id").toString());
				} else {
					rb.setRnickname(session.getAttribute("nickname").toString());
					rb.setRdetail(request.getParameter("reviewDetail"));
					rb.setRscore(Integer.parseInt(request.getParameter("score")));
					boolean result = rDao.upReview(rb);
					if (result) {
						String code = request.getParameter("mcode");
						boolean avgresult = rDao.updateavg(code);
						if (avgresult) {
							request.setAttribute("UpReviewMsg", "<script>" + "alert('리뷰가 등록되었습니다.')" + "</script>");
							request.setAttribute("mlcode", rb.getRcode());
							System.out.println(rb.getRcode());
							fi.setPath("showDetailmovie");
							fi.setRedirect(false);
						} else {
							request.setAttribute("UpReviewMsg", "<script>" + "alert('리뷰등록에 실패하였습니다.')" + "</script>");
							request.setAttribute("mlcode", rb.getRcode());
							fi.setPath("showDetailmovie");
							fi.setRedirect(false);
						}

					} else {
						request.setAttribute("UpReviewMsg",
								"<script>" + "alert('이미 이 게시물에 리뷰를 등록하셨습니다.')" + "</script>");
						request.setAttribute("mlcode", rb.getRcode());
						fi.setPath("showDetailmovie");
						fi.setRedirect(false);
					}
				}
			}
		}
		return fi;
	}

	public String showreview() {
		String data = request.getParameter("data");
		System.out.println("data" + data);
		rb = new ReviewBean();
		List<ReviewBean> rList = new ArrayList<>();
		ReviewDao rDao = new ReviewDao();
		rList = rDao.showreview(data);
		if (rb != null) {
			json = new Gson().toJson(rList);
		}
		return json;
	}

	public ForwardInfo updateReview() {
		fi = new ForwardInfo();
		rDao = new ReviewDao();
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		String code = request.getParameter("code");
		String rdetail = request.getParameter("reDetail");
		boolean result = rDao.updateReview(id, code, rdetail);
		if (result) {
			request.setAttribute("updateMsg", "<script>" + "alert('리뷰 수정이 완료되었습니다.')" + "</script>");
			request.setAttribute("updatereviewMsg", "(수정됨)");
			request.setAttribute("mlcode", code);
			fi.setPath("showDetailmovie");
			fi.setRedirect(false);
		} else {
			request.setAttribute("updateMsg", "<script>" + "alert('리뷰 수정 실패')" + "</script>");
			request.setAttribute("mlcode", code);
			fi.setPath("showDetailmovie");
			fi.setRedirect(false);
		}
		return fi;
	}

	public ForwardInfo deleteMyreview() {
		fi = new ForwardInfo();
		rDao = new ReviewDao();
		session = request.getSession();
		String cmd = request.getServletPath();
		String id = session.getAttribute("id").toString();
		String code = request.getParameter("code");
		boolean result = rDao.deleteMyreview(id, code);
		if (result) {
			boolean avgresult = rDao.updateavg(code);
			if (avgresult) {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제되었습니다.')" + "</script>");
				request.setAttribute("mlcode", code);
				fi.setPath("showDetailmovie");
				fi.setRedirect(false);
			} else {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제 실패')" + "</script>");
				request.setAttribute("mlcode", code);
				fi.setPath("showDetailmovie");
				fi.setRedirect(false);
			}
		} else {
			request.setAttribute("deleteMsg", "<script>" + "alert('삭제 실패')" + "</script>");
			request.setAttribute("mlcode", code);
			fi.setPath("showDetailmovie");
			fi.setRedirect(false);
		}
		return fi;
	}

	public String searchBLreview() {
		String json = null;
		String data = request.getParameter("data");
		rDao = new ReviewDao();
		List<ReviewBean> brList = new ArrayList<>();
		brList = rDao.searchBLreview(data);
		if (brList != null) {
			json = new Gson().toJson(brList);
		}
		return json;
	}

	public ForwardInfo showMyReview() {
		fi = new ForwardInfo();
		rDao = new ReviewDao();
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		List<ReviewBean> rList = new ArrayList<>();
		rList = rDao.showMyReview(id);
		if (rList != null) {
			request.setAttribute("myReview", showMyReview(rList));
		} else {
			request.setAttribute("myReview", "등록한 리뷰가 없습니다.");
		}
		fi.setPath("showMyList.jsp");
		fi.setRedirect(false);
		return fi;
	}

	private String showMyReview(List<ReviewBean> rList) {
		StringBuilder sb = new StringBuilder();
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th>등록일</th>");
		sb.append("<th>영화</th>");
		sb.append("<th class='Myreview'>리뷰</th>");
		sb.append("<th>평점</th>");
		sb.append("<th>이 리뷰 삭제</th>");
		sb.append("</tr>");
		for (int i = 0; i < rList.size(); i++) {
			rb = rList.get(i);
			sb.append("<tr>");
			sb.append("<td>" + rb.getRdate() + "</td>");
			sb.append("<td>" + rb.getMlname() + "</td>");
			sb.append("<td class = 'Myreview'>" + rb.getRdetail() + "</td>");
			sb.append("<td>" + rb.getRscore() + "</td>");
			sb.append("<td><form action='deletereview' method='post'>");
			sb.append("<input type='hidden' name='code' value='" + rb.getRcode() + "'>");
			sb.append("<button>리뷰삭제</button></form></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	public ForwardInfo showReview() {
		fi = new ForwardInfo();
		rDao = new ReviewDao();
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		String code = request.getParameter("code");
		boolean result = rDao.deleteMyreview(id, code);
		if (result) {
			boolean avgresult = rDao.updateavg(code);
			if (avgresult) {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제되었습니다.')" + "</script>");
				fi.setPath("showMyReview");
				fi.setRedirect(false);
			} else {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제 실패')" + "</script>");
				fi.setPath("showMyReview");
				fi.setRedirect(false);
			}
		} else {
			request.setAttribute("deleteMsg", "<script>" + "alert('삭제 실패')" + "</script>");
			fi.setPath("showDetailmovie");
			fi.setRedirect(false);
		}
		return fi;
	}

}
