package test.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import app.bean.Station;
import app.model.Ticket;
import app.service.FareService;

class FareServiceTest {
	
	/** テスト対象クラス */
	FareService sut;
	
	@BeforeEach
	void setUp() throws Exception {
		sut = new FareService();
	}
	
	@Nested
	@DisplayName("FareService#calcFare(Ticket)メソッドのテストクラス")
	class CalcFareTest {
		@ParameterizedTest
		@MethodSource("dataProvider")
		@DisplayName("Test11: 線形路線の場合のテスト")
		void test11(Ticket ticket, int expected) {
			// execute
			int actual = sut.calcFare(ticket);
			// verify
			assertThat(actual, is(expected));
		}
		
		static Stream<Arguments> dataProvider() {
			// setup
			List<Ticket> tickets = new ArrayList<Ticket>();
			List<Integer> expecteds = new ArrayList<Integer>();
			
			// Test11: （始発駅から途中駅までの場合）上野から赤羽までの運賃は180円である
			tickets.add(new Ticket(new Station("JU", "JRE", "上野"), new Station("JU", "JRE", "赤羽")));
			expecteds.add(180);
			// Test12: （始発駅から終点駅までの場合）上野から大宮までの運賃は490円である
			tickets.add(new Ticket(new Station("JU", "JRE", "上野"), new Station("JU", "JRE", "大宮")));
			expecteds.add(490);
			// Test13: （途中駅から終点駅までの場合）尾久から浦和までの運賃は320円である
			tickets.add(new Ticket(new Station("JU", "JRE", "尾久"), new Station("JU", "JRE", "大宮")));
			expecteds.add(410);
			// Test14: （途中駅から途中駅までの場合）尾久から浦和までの運賃は320円である
			tickets.add(new Ticket(new Station("JU", "JRE", "浦和"), new Station("JU", "JRE", "尾久")));
			expecteds.add(320);
			// Test15: （途中駅から途中駅までの場合）浦和から尾久からの運賃は320円である
			tickets.add(new Ticket(new Station("JU", "JRE", "尾久"), new Station("JU", "JRE", "浦和")));
			expecteds.add(320);
			
			// テストパラメータの返却
			return Stream.of(
					Arguments.of(tickets.get(0), expecteds.get(0)), 
					Arguments.of(tickets.get(1), expecteds.get(1)), 
					Arguments.of(tickets.get(2), expecteds.get(2)), 
					Arguments.of(tickets.get(3), expecteds.get(3)), 
					Arguments.of(tickets.get(4), expecteds.get(4)), 
					Arguments.of(tickets.get(tickets.size() - 1), expecteds.get(expecteds.size() - 1))
				   );
		}
	}

	@Test
	@Disabled
	void test() {
		// setup
		Ticket target = new Ticket();
		target.setBoarding(new Station("JU", "JRE", "上野"));
		target.setDestination(new Station("JU", "JRE", "赤羽"));
		int expected = 180;
		
		// execute
		int actual = sut.calcFare(target);
		
		// verify
		assertThat(actual, is(expected));
		
	}

}
