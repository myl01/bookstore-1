package com.orilore.daos;
import com.orilore.entitys.Detail;
import java.sql.*;
import java.util.*;
public class DetailDAO implements IDetailDAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	public boolean addDetail(Detail detail,Connection conn) throws Exception{
		String sql="insert into detail(id,code,pid,size,quantity,money) values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,detail.getId());
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public boolean delDetail(int id,Connection conn) throws Exception{
		String sql = "delete from detail where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);
		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public boolean updDetail(Detail detail,Connection conn) throws Exception{;
		String sql="update detail set id=?,code=?,pid=?,size=?,quantity=?,money=? where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,detail.getId());
			this.close();
			return true;
		}else{

			return false;
		}
	}
	public Detail findDetail(int id,Connection conn) throws Exception{
		String sql = "select * from detail where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);
		Detail detail = new Detail();
		if(rs.next()){
			detail.setId(rs.getInt("id"));
			detail.setMoney(rs.getFloat("money"));
			detail.setQuantity(rs.getInt("quantity"));
			detail.setPid(rs.getInt("pid"));
			detail.setCode(rs.getString("code"));
			detail.setSize(rs.getString("size"));
		}
		this.close();
		return detail;
	}
	public List<Detail> findDetails(Connection conn) throws Exception{
		String sql = "select * from detail";
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		List<Detail> details = new ArrayList<Detail>();
		while(rs.next()){
			Detail detail = new Detail();
			detail.setId(rs.getInt("id"));
			detail.setMoney(rs.getFloat("money"));
			detail.setQuantity(rs.getInt("quantity"));
			detail.setPid(rs.getInt("pid"));
			detail.setCode(rs.getString("code"));
			detail.setSize(rs.getString("size"));
			details.add(detail);
		}
		this.close();
		return details;
	}
	public void close() throws Exception{
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
	}
}