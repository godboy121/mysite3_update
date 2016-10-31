package com.bit2016.mysite.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.mysite.board.vo.BoardVo;

public class BoardDao {
	
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
	
	
	public BoardVo get(int no1){
		BoardVo vo=null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=getConnection();
			String sql=" select no, title, context, group_no, order_no, depth, users_no" +
					"   from board" +
					"  where no = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setLong( 1, no1 );
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				int no=rs.getInt(1);
				String title=rs.getString(2);
				String content = rs.getString( 3 );
				int groupNo = rs.getInt( 4 );
				int orderNo = rs.getInt( 5 );
				int depth = rs.getInt( 6 );
				int userNo = rs.getInt( 7 );
				
				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContext(content);
				vo.setGroup_no(groupNo);
				vo.setOrder_no(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
			}
		}catch(SQLException e){
			System.out.println("error"+e);
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
			}catch(SQLException e){
				System.out.println("error"+e);
			}
		}
		return vo;
	}
	public List<BoardVo> getList(int page, int size){//리스트 출력 
		List<BoardVo> list=new ArrayList<BoardVo>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=getConnection();
			String sql=" select * " +
					"   from ( select no, title, hit, reg_date, depth, name, users_no, rownum as rn" +
					"            from(  select a.no, a.title, a.hit, to_char(a.reg_date, 'yyyy-mm-dd hh24:mi:ss') as reg_date, a.depth, b.name, a.users_no" +
					"                     from board a, users b" +
					"                    where a.users_no = b.no" +
					 "                 order by group_no desc, order_no asc ))" +
		                "  where (?-1)*?+1 <= rn and rn <= ?*?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt( 1, page );
			pstmt.setInt( 2, size );
			pstmt.setInt( 3, page );
			pstmt.setInt( 4, size );
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int no=rs.getInt(1);//셀렉트 문에 있는 첫번째 VALUE NO의 값을 받아온다 
				String title=rs.getString(2);//이하동문
				int hit=rs.getInt(3);
				String regDate=rs.getString(4);
				int depth=rs.getInt(5);
				String userName=rs.getString(6);
				int userNo=rs.getInt(7);
				
				BoardVo vo=new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				vo.setUserName(userName);
				vo.setUserNo(userNo);
				
				list.add(vo);
			}
		}catch(SQLException e){
				System.out.println("error"+e);
			}finally{
				try{
					if(conn!=null)
						conn.close();
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();	
				}catch(SQLException e)
				{
					System.out.println("error"+e);
				}
			}
			return list;
	}
	
	public int getTotalCount() {
		int totalCount = 0;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select count(*) from board";
			rs = stmt.executeQuery(sql);
			if( rs.next() ) {
				totalCount = rs.getInt( 1 );
			}
		} catch (SQLException e) {
			System.out.println( "error:" + e );
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch ( SQLException e ) {
				System.out.println( "error:" + e );
			}  
		}
		
		return totalCount;
	}
	
	public void insert(BoardVo vo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=getConnection();
			String sql="insert into board values(board_seq.nextval,?,?,sysdate,0,nvl((select max(group_no) from board),0)+ 1,1,0,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContext());
			pstmt.setInt(3, vo.getUserNo());
			
			pstmt.executeUpdate();
		}catch(SQLException e)
		{
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
		
	
			
			
			
		

