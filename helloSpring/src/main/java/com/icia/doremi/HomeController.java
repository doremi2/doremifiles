package com.icia.doremi;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.icia.doremi.bean.Person;
import com.icia.doremi.bean.Student;
import com.icia.doremi.service.MemberMM;


@Controller
public class HomeController { //서블릿은 아니다. 하지만 서블릿의 역할을 한다.
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired //스프링언어 ( 자동으로 주입하도록 하는 것) // 타입을 기준으로 주입한다. 
	//@Qualifier("person")
	//@Resource(name="person") // 위 두 명령어를 합친 것과 같음
	private Student ps;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		//Model == Httpgetservlet
		MemberMM mm = new MemberMM();
		ModelAndView mav = mm.access();
		//res.setContentType("text/html;charset=utf-8"); << 서블릿방식
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String home(Locale locale, Model model) { // locale 지역 국가코드
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home"; //home.jsp로 줄임 
	}
	
	@RequestMapping(value = "/access", method = RequestMethod.GET)
	public String access( Model model) {
		logger.info("콘솔 기록 출력용 == sysout");
		System.out.println("출력용");
		Person mb = new Person();
		mb.setId("a1234").setAge(20);
		mb.setPw("1234");
		mb.setAge(24);
		mb.getId();
		mb.getPw();
		mb.getAge();
		//logger 기록하는것
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("msg", "helloSpringWorld");
		//==request.setAttribute 모델 객체가 주입해준다. 
		
		
		
		return "home";
	}

}
