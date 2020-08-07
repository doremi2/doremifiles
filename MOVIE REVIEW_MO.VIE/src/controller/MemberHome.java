package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.MemberMM;

@WebServlet({"/movieMain","/loginaccess","/Moviejoin","/joinMember","/logout","/gologinForm","/updateMyInfo","/updatenickname","/showBlackList","/deleteBL"})
public class MemberHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberHome() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getServletPath();
		System.out.println(cmd);
		ForwardInfo fi = null;
		
		MemberMM mm= new MemberMM(request,response);
		switch (cmd) {
		case "/movieMain":
			fi = new ForwardInfo();
			fi.setPath("movieMain.jsp");
			fi.setRedirect(false);
			break;
		case "/loginaccess" :
			fi = new ForwardInfo();
			fi = mm.loginAccess();
			break;
		case "/Moviejoin" :
			fi = new ForwardInfo();
			fi.setPath("joinMember.jsp");
			fi.setRedirect(false);
			break;
		case "/joinMember" :
			fi = new ForwardInfo();
			fi = mm.joinMember();
			break;
		case "/logout" :
			fi = new ForwardInfo();
			fi = mm.logout();
			break;
		case "/gologinForm":
			fi = new ForwardInfo();
			fi.setPath("loginForm.jsp");
			fi.setRedirect(true);
			break;
		case "/updateMyInfo":
			fi = new ForwardInfo();
			fi = mm.updateMyInfo();
			break;
		case "/updatenickname":
			fi = new ForwardInfo();
			fi = mm.updatenickname();
			break;
		case "/showBlackList":
			fi = new ForwardInfo();
			fi = mm.showBlackList();
			break;
		case "/deleteBL":
			fi = new ForwardInfo();
			fi = mm.deleteBList();
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
