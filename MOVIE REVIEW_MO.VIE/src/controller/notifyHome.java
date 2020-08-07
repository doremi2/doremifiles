package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.NotifyMM;

@WebServlet({ "/notifyreview","/showNotify","/deleteNotify","/appendblacklist"})
public class notifyHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public notifyHome() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String cmd = request.getServletPath();
		System.out.println("cmd:"+cmd);
		ForwardInfo fi = null;
		NotifyMM nm = new NotifyMM(request,response);
		
		switch (cmd) {
		case "/notifyreview":
			fi = new ForwardInfo();
			fi = nm.notifyreview();
			break;
		case "/showNotify":
			fi = new ForwardInfo();
			fi = nm.showNotify();
			break;
		case "/deleteNotify":
			fi = new ForwardInfo();
			fi = nm.deleteNotify();
			break;
		case "/appendblacklist":
			fi = new ForwardInfo();
			fi = nm.appendblackList();
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
