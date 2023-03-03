package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.MallDAO;
import vo.CartVO;

@WebServlet("/sessionToDB.do")
public class SessionToDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MallDAO dao = new MallDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<CartVO> list = (ArrayList)session.getAttribute("list");
		String id = (String)session.getAttribute("id");
		
		for(int i = 0; i < list.size(); i++) {
			CartVO cart = new CartVO();
			int pno = list.get(i).getPno();
			int pqq = list.get(i).getPquantity();
			cart.setPno(pno);
			cart.setPquantity(pqq);
			cart.setId(id);
			dao.addCart(cart);
		}
		
		response.sendRedirect("mallList.jsp");
	}


}
