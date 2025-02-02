package test.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import app.bean.Station;
import app.dao.StationDAO;

class StationDaoTest {

	/** テスト対象クラス：system under test */
	StationDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		sut = new StationDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("StationDAO#findByLine(String)のテストクラス")
	class FindByLineTest {
		@ParameterizedTest
		@MethodSource("dataProvider")
		@DisplayName("Test11: 指定された路線コードのすべての駅を取得する")
		void test11(String code, List<Station> expected) {
			// execute
			List<Station> actual = sut.findByCode(code);
			// verify
			for (int i = 0; i < expected.size(); i++) {
				assertThat(actual.get(i), is(expected.get(i)));
			}
		}
		
		static Stream<Arguments> dataProvider() {
			// setup
			List<String> codes = new ArrayList<String>();
			List<Station> expectedList = new ArrayList<Station>();
			List<List<Station>> expecteds = new ArrayList<List<Station>>();
			
			// Test11: JR山手線の駅リストを取得する
			codes.add("JY");
			
			
			// テストパラメータを返却
			return Stream.of(Arguments.of(null));
		}
	}

}
