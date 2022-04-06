package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	MemberDTO info = null;
	
	int cnt = 0;
	
	// 1. ojdbc6.jar import 하기
	
	// DB 연결 메소드
	public void conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url ="jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String dbid = "aws_test";
			String dbpw = "aws_test";
			
			conn = DriverManager.getConnection(url, dbid, dbpw);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB 문 닫는 메소드
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원가입 메소드
	public int join(MemberDTO dto) {
		try {
			// DB 연결 메소드 호출
			conn();
			
			String sql = "insert into web_member values(?,?,?,?)";
			
			psmt =  conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getEmail());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getTel());
			psmt.setString(4, dto.getAddr());
			
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB 문 닫는 메소드 호출
			close();
		} return cnt;
	}

	// 로그인 메소드
	public MemberDTO login(String getEmail, String getPw) {
		try {
			conn();
			String sql = "select * from web_member where email = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, getEmail);
			psmt.setString(2, getPw);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString(1);
				String pw = rs.getString(2);
				String tel = rs.getString(3);
				String addr = rs.getString(4);
				
				info = new MemberDTO(email, pw, tel, addr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} return info;
	}
	
	// 회원정보수정 메소드
	public int update(MemberDTO dto) {
		try {
			conn();
			String sql = "update web_member set pw=?, tel=?, address=? where email =?";
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getPw());
			psmt.setString(2, dto.getTel());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getEmail());
			psmt.setNString(1, dto.getEmail());
			
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} return cnt;
	}
	
	// 회원 정보 관리 메소드
	public ArrayList<MemberDTO> showMember() {
		// 회원 정보가 들어있는 DTO를 담기 위한 어레이리스트
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			conn();
			String sql = "select * from web_member";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String email = rs.getString("email");
				String pw = rs.getString("pw");
				String tel = rs.getString("tel");
				String addr = rs.getString("address");
				
				MemberDTO dto = new MemberDTO(email, pw, tel, addr);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} return list;
	}
	
	
	// 아이디 중복확인 메소드
	public boolean idCheck(String email) {
		boolean check = false;
		
		conn();
		
		try {
			String sql = "select email from web_member where email = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return check;
	}
	
	
	
	
	
	
	
	
	

}
