package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import db.MallDAO;

@WebServlet("/getCart.do")
public class GetCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MallDAO dao = new MallDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		PrintWriter out = response.getWriter();
		
		if(id != null) {
			JSONArray list = dao.getCartList(id);
			out.print(list);
		}
		
	}

}
