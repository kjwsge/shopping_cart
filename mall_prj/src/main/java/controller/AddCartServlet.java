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

@WebServlet("/addCart.do")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MallDAO dao = new MallDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int pno = Integer.parseInt(request.getParameter("pno"));
		int qtt = Integer.parseInt(request.getParameter("qtt"));
		String id = (String)session.getAttribute("id");
		ArrayList<CartVO> list = (ArrayList)session.getAttribute("list");
		int cnt = 0;
		CartVO cart = new CartVO();
		cart.setPno(pno);
		cart.setPquantity(qtt);
		
		if(id != null) {
			if(dao.getQtt(pno, id) != 0) {
				int bqtt = dao.getQtt(pno, id);
				dao.updateQtt(pno, qtt+bqtt, id);
			}else {
				cart.setId(id);
				dao.addCart(cart);				
			}

		}else if(list != null){
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getPno()==pno) {
					list.get(i).setPquantity(qtt+list.get(i).getPquantity());
					cnt = 1;
					session.removeAttribute("list");
					session.setAttribute("list", list);
				}
			}
			if(cnt==0) {
				list.add(cart);
				session.removeAttribute("list");
				session.setAttribute("list", list);				
			}
		}else if(list == null) {
			ArrayList<CartVO> newList = new ArrayList<>();
			newList.add(cart);
			session.removeAttribute("list");
			session.setAttribute("list", newList);
		}
		
		response.sendRedirect("mallList.jsp");
		
	}

}
