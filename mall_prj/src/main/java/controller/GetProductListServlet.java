package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import db.MallDAO;

@WebServlet("/getPList.do")
public class GetProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MallDAO dao = new MallDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		JSONArray list = dao.getPList();
		
		PrintWriter out = response.getWriter();
		out.print(list);
	}

}
