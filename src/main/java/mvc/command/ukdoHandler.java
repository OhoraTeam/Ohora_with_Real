package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ukdoHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String login = request.getParameter("login");
		String signUp = request.getParameter("join");
		System.out.println("파라미터 ==" + login);
		System.out.println("파라미터2 ==" + signUp);
		
		

		if ("signup".equals(signUp)  ) {
			return "/ohora/oho_signUpForm.jsp";
		}
		else if ("gologin".equals(login) ) {
			System.out.println("로그인do 진입완료");
			return "/ohora/login.jsp";
		}
		
		
		
	return null;
	}

}
