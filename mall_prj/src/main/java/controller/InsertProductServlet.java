package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db.MallDAO;
import vo.MallVO;

@WebServlet("/addProduct.do")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MallDAO dao = new MallDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		
		MultipartRequest multi = new MultipartRequest(
				request,
				application.getRealPath("/img"),
				100 * 1024 * 1024,
				"utf-8",
				new DefaultFileRenamePolicy()
		);
		
		MallVO product = new MallVO();
		File file = multi.getFile("image");
		String pname = multi.getParameter("pname");
		String pinfo = multi.getParameter("pinfo");
		int price = Integer.parseInt(multi.getParameter("price"));
		int stock = Integer.parseInt(multi.getParameter("stock"));
		
		if(file != null) {
			product.setPname(pname);
			product.setPinfo(pinfo);
			product.setPprice(price);
			product.setPstock(stock);
			
			dao.addFile(file, product);
		}

		response.sendRedirect("mallList.jsp");
	}

}
