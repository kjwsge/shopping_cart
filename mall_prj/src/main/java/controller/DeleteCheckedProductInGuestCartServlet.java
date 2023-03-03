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

@WebServlet("/delCPIGC.do")
public class DeleteCheckedProductInGuestCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] pnos = request.getParameterValues("pnos");
		
		ArrayList<CartVO> list = (ArrayList)session.getAttribute("list");
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < pnos.length; j++) {
				if(list.get(i).getPno() == Integer.parseInt(pnos[j])) {
					list.remove(i);
				}
			}
		}
		session.removeAttribute("list");
		session.setAttribute("list", list);
	}

}
