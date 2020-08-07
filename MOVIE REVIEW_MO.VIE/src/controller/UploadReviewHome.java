package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.ReviewMM;


@WebServlet({"/upReview","/showreview","/updateReview","/deleteMyreview","/searchBLReview", "/showMyReview","/deletereview"})
public class UploadReviewHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UploadReviewHome() {
        super();
    }


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getServletPath();
		System.out.println("cmd:"+cmd);
		ForwardInfo fi = null;
		ReviewMM rm = new ReviewMM(request, response);
		String json = null;
		switch (cmd) {
		case "/upReview":
			fi = new ForwardInfo();
			fi = rm.upReview();
			break;
		case "/showreview":
			json = rm.showreview();
			break;
		case "/updateReview":
			fi = new ForwardInfo();
			fi = rm.updateReview();
			break;
		case "/deleteMyreview":
			fi = new ForwardInfo();
			fi = rm.deleteMyreview();
			break;
		case "/searchBLReview":
			json = rm.searchBLreview();
			break;
		case "/showMyReview":
			fi = new ForwardInfo();
			fi = rm.showMyReview();
			break;
		case "/deletereview":
			fi = new ForwardInfo();
			fi = rm.showReview();
		}
		if(fi!=null) {
			if(fi.isRedirect()) {
				response.sendRedirect(fi.getPath());
			}else {
				request.getRequestDispatcher(fi.getPath()).forward(request, response);
			}
		}
		if(json!=null) {
			response.setContentType("html/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
