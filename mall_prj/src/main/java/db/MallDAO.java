package db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.CartVO;
import vo.MallVO;

public class MallDAO {
	
	Connection conn = DBcon.getConnection();
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void addFile(File file, MallVO product) {

		String query = String.format(
				"INSERT INTO mall_product(pname, pinfo, pimg, pprice, pstock)" +
				"VALUES ('%s', '%s', '%s', '%d', '%d')" ,
				product.getPname(), product.getPinfo(), file.getName(), product.getPprice(), product.getPstock() 
				);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public JSONArray getPList() {
		
		JSONArray list = new JSONArray();
		String query = "SELECT * FROM mall_product ORDER BY pno DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				
				obj.put("pno", rs.getInt(1));
				obj.put("pname", rs.getString(2));
				obj.put("pimg", rs.getString(4));
				obj.put("pprice", rs.getInt(5));
				
				list.put(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void addCart(CartVO cart) {
		
		String query = "INSERT INTO cart(pno, pquantity, id) VALUES(?, ?, ?);";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cart.getPno());
			pstmt.setInt(2, cart.getPquantity());
			pstmt.setString(3, cart.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JSONArray getCartList(String id) {
		
		JSONArray list = new JSONArray();
		String query = "SELECT cart.pno, cart.pquantity, mp.pname, mp.pimg, mp.pprice, mp.pstock, cart.id FROM cart JOIN mall_product AS mp ON cart.pno = mp.pno AND cart.id = ?;";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				
				obj.put("pno", rs.getInt(1));
				obj.put("pqtt", rs.getInt(2));
				obj.put("pname", rs.getString(3));
				obj.put("pimg", rs.getString(4));
				obj.put("pprice", rs.getInt(5));
				obj.put("pstock", rs.getInt(6));
				
				list.put(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public MallVO getCartList2(int inr) {
		
		String query = "SELECT pname, pimg, pprice, pstock FROM mall_product WHERE pno = ?";
		MallVO product = new MallVO();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inr);
			rs = pstmt.executeQuery();
			rs.next();
			product.setPname(rs.getString(1));
			product.setPimg(rs.getString(2));
			product.setPprice(rs.getInt(3));
			product.setPstock(rs.getInt(4));

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return product;
	}
	
	public void updateQtt(int pno, int qtt, String id) {
		
		String query = "UPDATE cart SET pquantity = ? WHERE pno = ? AND id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qtt);
			pstmt.setInt(2, pno);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int getQtt(int pno, String id) {
		
		String query = "SELECT pquantity FROM cart WHERE pno = ? AND id = ?";
		int result = 0;	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
			
	}
	
	public void deleteCart(int pno, String id) {
		
		String query = "DELETE FROM cart WHERE pno = ? AND id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
