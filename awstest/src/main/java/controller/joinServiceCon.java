package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/joinServiceCon")
public class joinServiceCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post방식 인코딩
		request.setCharacterEncoding("EUC-KR");
		
		// email, pw, name, tel, addr
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		
		System.out.println("email : "+email);
		System.out.println("pw : "+pw);
		System.out.println("tel : "+tel);
		System.out.println("addr : "+addr);
		
		MemberDTO dto = new MemberDTO(email, pw, tel, addr);
		MemberDAO dao = new MemberDAO();
		int cnt = dao.join(dto);
		
		String moveURL = "";
		if(cnt >0) {
			System.out.println("회원가입 성공");
			moveURL = "join_success.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("name", email);
		}else {
			System.out.println("회원가입 실패");
			moveURL = "index.html";
		}
		
		response.sendRedirect(moveURL);
	
	}

}
