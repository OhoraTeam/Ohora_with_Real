package ohora.persistence;

import java.util.ArrayList;

import ohora.domain.MyPageDTO;

public interface MypageDAO {
	// 마이페이지 이름
	ArrayList<MyPageDTO> myPageName(Integer userId);

	// 마이페이지 order list
	ArrayList<MyPageDTO> orderList(Integer userId);

	// 마이페이지 주문 부분
	ArrayList<MyPageDTO> ordDetail(Integer userId);
}
