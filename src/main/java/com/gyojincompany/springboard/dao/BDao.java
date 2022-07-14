package com.gyojincompany.springboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gyojincompany.springboard.dto.BDto;

public class BDao {
	
	DataSource dataSource;

	public BDao() {
		super();
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void write(String bname, String btitle, String bcontent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_board(bid,bname,btitle,bcontent,bhit,bgroup,bstep,bindent) "
					+ "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	public ArrayList<BDto> list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_board ORDER BY bid DESC";
			pstmt = conn.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//글의 개수만큼 반복
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				
				BDto dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				dtos.add(dto);				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		return dtos;
	}
	
	
	
}
