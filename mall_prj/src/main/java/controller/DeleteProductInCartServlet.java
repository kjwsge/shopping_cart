package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.MallDAO;

@WebServlet("/delPIC.do")
public class DeleteProductInCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MallDAO dao = new MallDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		if(id != null) {
			dao.deleteCart(pno, id);			
		}
	}


}
