package com.bit2016.mysite3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bit2016.mysite.vo.UserVo;

public class UserDao {
	private Connection getConnection() throws SQLException{
		Connection conn=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 " + e);
	}
		return conn;
	}
	//modify
	public UserVo get(int no){
		UserVo vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql="SELECT name,email,passwd,gender from users where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,no);
				
			rs=pstmt.executeQuery();
			if(rs.next()){
				String name=rs.getString(1);
				String email=rs.getString(2);
				String passwd=rs.getString(3);
				String gender=rs.getString(4);
				
				vo=new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPasswd(passwd);
				vo.setGender(gender);
			}
		} catch (SQLException e) {
			System.out.println("error"+e);
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			}catch(SQLException e)
			{
				System.out.println("error"+e);
			}
		}
		
		return vo;
	}
	

	//인증(로그인)
	public UserVo get(String email, String password){
		UserVo vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql="select no, name from users where email=? and passwd=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				int no=rs.getInt(1);
				String name=rs.getString(2);
				
				vo=new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("error"+e);
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			}catch(SQLException e)
			{
				System.out.println("error"+e);
			}
		}
		
		return vo;
	}
	
	public UserVo get(String email){
		UserVo vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			
			String sql="select no, email,name from users where email=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo=new UserVo();
				vo.setNo(rs.getInt(1));
				vo.setEmail(rs.getString(2));
				vo.setName(rs.getString(3));
				
			
			}
		} catch (SQLException e) {
			System.out.println("error"+e);
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			}catch(SQLException e)
			{
				System.out.println("error"+e);
			}
		}
		
		return vo;
	}
	public UserVo update(UserVo vo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=getConnection();
			String sql="update users set name=?,passwd=?,gender=? where no=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getGender());
			pstmt.setInt(4, vo.getNo());
			pstmt.executeUpdate();
			System.out.println("Update 완료");
		}catch(SQLException e){
			System.out.println("error"+e);
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			
			}catch(SQLException e){
				System.out.println("error"+e);
			}
		}
		return vo;
		
	}
	

	public void insert(UserVo vo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			String sql="insert into users values(user_seq.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error"+e);
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
			
			}catch(SQLException e)
			{
				System.out.println("error"+e);
			}
		}
	}

}
