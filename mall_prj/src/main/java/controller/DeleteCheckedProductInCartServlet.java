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

@WebServlet("/delCPIC.do")
public class DeleteCheckedProductInCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MallDAO dao = new MallDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] pnos = request.getParameterValues("pnos");
		String id = (String)session.getAttribute("id");
		for(int i = 0; i < pnos.length; i++) {
			dao.deleteCart(Integer.parseInt(pnos[i]), id);
		}
		PrintWriter out = response.getWriter();
		out.print(0);
	}


}
