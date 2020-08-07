package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.ForwardInfo;
import bean.ReviewBean;
import bean.UploadMovie;
import dao.ReviewDao;
import dao.UploadDao;

public class ContentsMM {
	HttpServletRequest request;
	HttpServletResponse response;
	ForwardInfo fi = null;
	UploadMovie um = null;
	UploadDao uDao = null;

	public ContentsMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo upLoadMovie() throws IOException {
		fi = new ForwardInfo();
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		int size = 10 * 1024 * 1024;
		File dir = new File(uploadPath);

		if (!dir.exists()) {
			dir.mkdir();
		}
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8",
				new DefaultFileRenamePolicy());
		um = new UploadMovie();
		um.setMlname(multi.getParameter("mlname"));
		um.setMlgenre(multi.getParameter("mlgenre"));
		um.setMlplayday(multi.getParameter("mlplayday"));
		System.out.println(um.getMlplayday());
		um.setMlproducer(multi.getParameter("mlproducer"));
		um.setMldetail(multi.getParameter("mldetail"));
		um.setMlimg(multi.getFilesystemName("mlimg"));
		um.setMlvideo(multi.getParameter("mlvideo"));
		uDao = new UploadDao();
		boolean result = uDao.upLoadMovie(um);
		if (result) {
			request.setAttribute("successUploadMsg", "<script>" + "alert('게시물이 등록되었습니다.')" + "</script>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		} else {
			request.setAttribute("upLoadFailMsg", "<script>" + "alert('양식에 맞게 작성해주십시오')" + "</script");
			fi.setPath("goupLoadmovieList");
			fi.setRedirect(false);
		}

		return fi;
	}

	public ForwardInfo showMovieList() {
		fi = new ForwardInfo();
		uDao = new UploadDao();
		List<UploadMovie> uList = new ArrayList<>();
		uList = uDao.showMovieList();
		if (uList != null) {
			request.setAttribute("pList", showMoiveList(uList));
			fi.setPath("movieList.jsp");
			fi.setRedirect(false);
		} else {
			request.setAttribute("pList", "등록한 영화가 없습니다.");
			fi.setPath("movieList.jsp");
			fi.setRedirect(false);
		}
		return fi;
	}

	private String showMoiveList(List<UploadMovie> uList) {
		StringBuilder sb = new StringBuilder();
		HttpSession session = request.getSession();
		for (int i = 0; i < uList.size(); i++) {
			um = uList.get(i);
			sb.append("<div class = 'detailcode' data-code=" + um.getMlcode() + ">");
			sb.append("<form action = 'showDetailmovie' method = 'post'>");
			sb.append("<input type = 'hidden' name='mlcode' value='" + um.getMlcode() + "'/>");
			sb.append("<img src ='upload/" + um.getMlimg() + "'class='listimg'><br/>");
			sb.append(um.getMlname() + "<br/>");
			sb.append(um.getMlgenre() + " | ");
			sb.append(um.getMlplayday().substring(0, 10) + "<br/>");
			sb.append("회원 평점["+um.getMl_avg()+"]<br/>");
			sb.append("<button>리뷰보기</button>");
			sb.append("</form>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	public ForwardInfo showDetailmovie() {
		fi = new ForwardInfo();
		uDao = new UploadDao();
		um = new UploadMovie();
		if (request.getParameter("mlcode") == null) {
			String mlcode = request.getAttribute("mlcode").toString();
			System.out.println("getAttribute:" + mlcode);
			um = uDao.showDetailmovie(mlcode);
		} else {
			String mlcode = request.getParameter("mlcode");
			System.out.println("getParameter:" + mlcode);
			um = uDao.showDetailmovie(mlcode);
		}
		System.out.println("um : " + um);
		if (um != null) {
			request.setAttribute("mldetail", detailmovie(um));
			fi.setPath("detailmovie.jsp");
			fi.setRedirect(false);
		} else {
			request.setAttribute("mldetail", "<script>" + "alert('영화정보를 불러오지 못했습니다.')" + "</script>");
			fi.setPath("movieMain.jsp");
			fi.setRedirect(false);
		}
		return fi;
	}

	private String detailmovie(UploadMovie um) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div id ='detail' data-code=" + um.getMlcode() + ">");
		sb.append("<div class='code'>");
		sb.append("게시물 등록번호[" + um.getMlcode() + "-" + um.getMldate().substring(0, 10) + "]");
		sb.append("</div>");
		sb.append("<div class ='movie_info'>");
		sb.append("<h1>" + um.getMlname() + "</h1><br/>");
		sb.append("<h2>평점[" + um.getMl_avg() + "]</h2><br/>");
		sb.append("<h4>감독</h4>" + um.getMlproducer() + " | ");
		sb.append(um.getMlgenre() + " | ");
		sb.append(um.getMlplayday().substring(0, 10) + " 개봉");
		sb.append("</div>");
		sb.append("<img src = 'upload/" + um.getMlimg() + "' class = 'movie_img'>");
		sb.append("<div id='video'><iframe width='400' height='300' src='https://www.youtube.com/embed/"
				+ um.getMlvideo()
				+ "' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>");
		sb.append("</div><br/>");
		sb.append("<div class='movie_detail'>");
		sb.append("<h3>줄거리</h3></br>");
		sb.append(um.getMldetail() + "<br/>");
		sb.append("</div>");
		sb.append("</div>");

		return sb.toString();
	}

	public ForwardInfo deletemovieList() {
		fi = new ForwardInfo();
		uDao = new UploadDao();
		String mlcode = request.getParameter("mlcode");
		boolean subresult = uDao.deletereviewList(mlcode);
		if (subresult) {
			boolean result = uDao.deletemovieList(mlcode);
			if (result) {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제되었습니다.')" + "</script>");
			} else {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제실패.')" + "</script>");
			}
		} else {
			boolean result = uDao.deletemovieList(mlcode);
			if (result) {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제되었습니다.')" + "</script>");
			} else {
				request.setAttribute("deleteMsg", "<script>" + "alert('삭제실패.')" + "</script>");
			}
		}
		fi.setPath("showAllMovieList");
		fi.setRedirect(false);
		return fi;
	}

	public ForwardInfo showAllMovieList() {
		fi = new ForwardInfo();
		uDao = new UploadDao();
		List<UploadMovie> uList = new ArrayList<>();
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			request.setAttribute("showListerror", "<script>" + "alert('관리자만 이용가능합니다.')" + "</script>");
			fi.setPath("movieMain");
			fi.setRedirect(false);
		} else {
			String id = session.getAttribute("id").toString();
			if (id.equals("z1234")) {
				uList = uDao.showReviewList();
				if (uList != null) {
					request.setAttribute("uList", showAllList(uList));
					fi.setPath("showAllList.jsp");
					fi.setRedirect(false);
				} else {
					request.setAttribute("showListerror", "<script>" + "alert('리스트가 존재하지 않습니다.')" + "</script>");
					fi.setPath("movieMain");
					fi.setRedirect(false);
				}
			} else {
				request.setAttribute("showListerror", "<script>" + "alert('관리자만 이용가능합니다.')" + "</script>");
				fi.setPath("movieMain");
				fi.setRedirect(false);
			}
		}
		return fi;
	}

	private String showAllList(List<UploadMovie> uList) {
		StringBuilder sb = new StringBuilder();
		um = new UploadMovie();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th>영화 사진</th>");
		sb.append("<th>업로드 번호</th>");
		sb.append("<th>등록일자</th>");
		sb.append("<th>게시물이름</th>");
		sb.append("<th>삭제</th>");
		sb.append("</tr>");
		for (int i = 0; i < uList.size(); i++) {
			um = uList.get(i);
			sb.append("<tr>");
			sb.append("<td><img src = 'upload/" + um.getMlimg() + "'></td>");
			sb.append("<td>" + um.getMlcode() + "</td>");
			sb.append("<td>" + um.getMldate() + "</td>");
			sb.append("<td>" + um.getMlname() + "</td>");
			sb.append("<td><form action ='deleteMovie' method ='post'>");
			sb.append("<input type='hidden' name='mlcode' value='" + um.getMlcode() + "'/>");
			sb.append("<button>삭제</button></form></td>");
		}
		sb.append("</table>");
		return sb.toString();
	}

}
