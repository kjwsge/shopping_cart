package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.CartVO;

@WebServlet("/changeGuestQtt.do")
public class ChangeGuestQttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt(request.getParameter("pno"));
		int qtt = Integer.parseInt(request.getParameter("qtt"));
		HttpSession session = request.getSession();

		ArrayList<CartVO> list = (ArrayList)session.getAttribute("list");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPno()==pno) {
				list.get(i).setPquantity(qtt);
			}
		}
		session.removeAttribute("list");
		session.setAttribute("list", list);
		PrintWriter out = response.getWriter();
		
		out.print(qtt);
	}

}
