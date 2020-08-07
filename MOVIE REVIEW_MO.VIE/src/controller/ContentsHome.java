package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.ContentsMM;

@WebServlet({"/goupLoadmovieList","/upLoadMovie","/showAllitem","/showDetailmovie","/showAllMovieList","/deleteMovie"})
public class ContentsHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ContentsHome() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getServletPath();
		System.out.println("cmd:"+cmd);
		ForwardInfo fi = null;
		ContentsMM cm = new ContentsMM(request,response);
		switch (cmd) {
		case "/goupLoadmovieList":
			fi = new ForwardInfo();
			fi.setPath("upLoadMovieList.jsp");
			fi.setRedirect(true);
			break;
		case "/upLoadMovie":
			fi = new ForwardInfo();
			fi = cm.upLoadMovie();
			break;
		case "/showAllitem":
			fi = new ForwardInfo();
			fi = cm.showMovieList();
			break;
		case "/showDetailmovie":
			fi = new ForwardInfo();
			fi = cm.showDetailmovie();
			break;
		case "/showAllMovieList":
		fi = new ForwardInfo();
		fi = cm.showAllMovieList();
			break;
		case "/deleteMovie":
			fi = new ForwardInfo();
			fi = cm.deletemovieList();
			break;
		}
		if(fi!=null) {
			if(fi.isRedirect()) {
				response.sendRedirect(fi.getPath());
			}else {
				request.getRequestDispatcher(fi.getPath()).forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
