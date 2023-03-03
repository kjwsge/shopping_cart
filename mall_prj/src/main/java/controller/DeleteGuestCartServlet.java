package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.CartVO;

@WebServlet("/delGC.do")
public class DeleteGuestCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int pno = Integer.parseInt(request.getParameter("pno"));
		ArrayList<CartVO> list = (ArrayList)session.getAttribute("list");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPno()==pno) {
				list.remove(i);
			}
		}
		session.removeAttribute("list");
		session.setAttribute("list", list);
		
	}

}
