package ohora.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ohora.domain.MyPageDTO;

public class MypageDAOImpl implements MypageDAO{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public MypageDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public ArrayList<MyPageDTO> myPageName(Integer userId) {
		
		System.out.println("dao userId : " + userId);

		String user_name;
		String mem_name;
		int user_point;
		String sql;

		sql = " select a.user_name, b.mem_name, a.user_point ";
		sql += " from o_user a ";
		sql += " inner join o_membership b on a.mem_id = b.mem_id ";
		sql += " where user_id = ? ";

		ArrayList<MyPageDTO> list = null;
		MyPageDTO myPageDTO = null;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<MyPageDTO>();
				do {
					user_name = rs.getString("user_name");
					mem_name = rs.getString("mem_name");
					user_point = rs.getInt("user_point");

					myPageDTO = new MyPageDTO().builder()
							.user_name(user_name)
							.mem_name(mem_name)
							.user_point(user_point)
							.build();

					list.add(myPageDTO);

				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	// 마이페이지 이름 처리 끝

	// 마이페이지 order list 시작
	@Override
	public ArrayList<MyPageDTO> orderList(Integer userId) {

		System.out.println("dao userId : " + userId);
		String sql;
		String opdt_state;

		sql = "SELECT b.opdt_state " +
				"FROM o_order a " +
				"INNER JOIN o_orddetail b ON a.ord_pk = b.ord_pk " +
				"WHERE a.user_id = ?";

		ArrayList<MyPageDTO> orderList = new ArrayList<>();
		MyPageDTO myPageDTO;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				opdt_state = rs.getString("opdt_state");

				myPageDTO = new MyPageDTO().builder()
						.opdt_state(opdt_state)
						.build();

				orderList.add(myPageDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return orderList;

	}
	// 마이페이지 order list 끝

	// 마이페이지 주문 부분 select 시작
	@Override
	public ArrayList<MyPageDTO> ordDetail(Integer userId) {

		System.out.println("dao userId : " + userId);

		Date ord_orderdate;
		String opdt_name;
		int opdt_amount;
		String sql;

		sql = " select a.ord_orderdate, b.opdt_name, opdt_amount ";
		sql += " from o_order a ";
		sql += " inner join o_orddetail b on a.ord_pk = b.ord_pk ";
		sql += " where user_id = ? ";
		sql += " order by a.ord_orderdate desc ";

		ArrayList<MyPageDTO> orderDetail = null;
		MyPageDTO myPageDTO = null;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				orderDetail = new ArrayList<MyPageDTO>();
				do {
					ord_orderdate = rs.getDate("ord_orderdate");
					opdt_name = rs.getString("opdt_name");
					opdt_amount = rs.getInt("opdt_amount");

					myPageDTO = new MyPageDTO().builder()
							.ord_orderdate(ord_orderdate)
							.opdt_name(opdt_name)
							.opdt_amount(opdt_amount)
							.build();

					orderDetail.add(myPageDTO);

				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return orderDetail;

	}
	// 마이페이지 주문 부분 select 끝
}
