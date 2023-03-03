package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.MallDAO;

@WebServlet("/changeQtt.do")
public class ChangeQttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MallDAO dao = new MallDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt(request.getParameter("pno"));
		int qtt = Integer.parseInt(request.getParameter("qtt"));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		dao.updateQtt(pno, qtt, id);
		int result = dao.getQtt(pno, id);
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
	}

}
