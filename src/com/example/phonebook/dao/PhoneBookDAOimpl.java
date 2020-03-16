package com.example.phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.phonebook.vo.PhoneBookVO;

public class PhoneBookDAOimpl implements PhoneBookDAO {

	private static String dbur1 = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String dbuser = "bituser";
	private static String dbpass = "bituser";

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbur1, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버를 불러오지 못했어요!");
		}
		return conn;
	}

	@Override
	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<PhoneBookVO> list = new ArrayList<>();

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT id, name, hp, tel FROM phone_book";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long phoneId = rs.getLong(1);
				String phoneName = rs.getString(2);
				String phoneHp = rs.getString(3);
				String phoneTel = rs.getString(4);

				PhoneBookVO vo = new PhoneBookVO(phoneId, phoneName, phoneHp, phoneTel);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.err.println("SQL 에러");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (Exception e) {

			}
		}
		return list;
	}

	@Override
	public PhoneBookVO get(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PhoneBookVO phone = null;

		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phone_book WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				phone = new PhoneBookVO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			System.err.println("SQL 에러");
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (Exception e) {

			}
		}
		return phone;

	}

	@Override
	public boolean insert(PhoneBookVO phonebookVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phonebookVO.getPhoneName());
			pstmt.setString(2, phonebookVO.getPhoneHp());
			pstmt.setString(3, phonebookVO.getPhoneTel());
			
			insertedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL 에러");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}

		return 1 == insertedCount;
	}

	@Override
	public boolean update(PhoneBookVO phonebookVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "UPDATE phone_book SET id=?, name=?, hp=?, tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, phonebookVO.getPhoneId());
			pstmt.setString(2, phonebookVO.getPhoneName());
			pstmt.setString(3, phonebookVO.getPhoneHp());
			pstmt.setString(4, phonebookVO.getPhoneTel());
			
			updatedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				
			}
		}

		return 1== updatedCount;
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			deletedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return deletedCount != 0;
	}

	@Override
	public List<PhoneBookVO> search(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phone_book WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  '%'+ name + '%');
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			Long phoneId = rs.getLong(1);
			String phoneName = rs.getString(2);
			String phoneHp = rs.getString(3);
			String phoneTel = rs.getString(4);
			PhoneBookVO vo=new PhoneBookVO(phoneId, phoneName, phoneHp, phoneTel);
			list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 에러");
		}finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			}catch(Exception e) {
				
			}
		}
		return list;
	}

}// class
