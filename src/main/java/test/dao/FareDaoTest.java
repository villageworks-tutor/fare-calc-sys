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

import app.bean.Fare;
import app.dao.FareDAO;

class FareDaoTest {

	/** テスト対象クラス：system under test */
	FareDAO sut;
	
	@BeforeEach
	void setUp() throws Exception {
		sut = new FareDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("FareDAO#findByScheme(String)のテストクラス")
	class FindBySchemeTest {
		@ParameterizedTest
		@MethodSource("dataProvider")
		@DisplayName("Test11: JR山手線の運賃体系を取得する")
		void test11(String scheme, List<Fare> expected) {
			// execute
			List<Fare> actual = sut.findByScheme(scheme);
			
			// verify
			for (int i = 0; i < expected.size(); i++) {
				assertThat(actual.get(i).getScheme(), is(expected.get(i).getScheme()));
				assertThat(actual.get(i).getValue(), is(expected.get(i).getValue()));
				assertThat(actual.get(i).getDistance(), is(expected.get(i).getDistance()));
			}
		}
		
		/**
		 * テストパラメータ生成メソッド
		 * @return テストパラメータ
		 */
		static Stream<Arguments> dataProvider() {
			// setup
			List<String> schemes = new ArrayList<String>();
			List<Fare> expectedList = new ArrayList<Fare>();
			List<List<Fare>> expecteds = new ArrayList<List<Fare>>();
			
			// Test11: JR山手線の運賃体系
			schemes.add("JY");
			expectedList.add(new Fare("JY", 3, 150));
			expectedList.add(new Fare("JY", 6, 170));
			expectedList.add(new Fare("JY", 10, 180));
			expectedList.add(new Fare("JY", 15, 210));
			expectedList.add(new Fare("JY", 20, 280));
			expecteds.add(expectedList);
			
			// Test12: JR東京電車特定区間の運賃体系
			expectedList = new ArrayList<Fare>();
			schemes.add("JRE");
			expectedList.add(new Fare("JRE", 3, 150));
			expectedList.add(new Fare("JRE", 6, 170));
			expectedList.add(new Fare("JRE", 10, 180));
			expectedList.add(new Fare("JRE", 15, 230));
			expectedList.add(new Fare("JRE", 20, 320));
			expectedList.add(new Fare("JRE", 25, 410));
			expectedList.add(new Fare("JRE", 30, 490));
			expectedList.add(new Fare("JRE", 35, 580));
			expectedList.add(new Fare("JRE", 40, 660));
			expectedList.add(new Fare("JRE", 45, 740));
			expectedList.add(new Fare("JRE", 50, 830));
			expectedList.add(new Fare("JRE", 60, 950));
			expectedList.add(new Fare("JRE", 70, 1110));
			expectedList.add(new Fare("JRE", 80, 1280));
			expectedList.add(new Fare("JRE", 90, 1460));
			expectedList.add(new Fare("JRE", 100, 1620));
			expectedList.add(new Fare("JRE", 120, 1880));
			expectedList.add(new Fare("JRE", 140, 2210));
			expectedList.add(new Fare("JRE", 160, 2540));
			expectedList.add(new Fare("JRE", 180, 2870));
			expectedList.add(new Fare("JRE", 200, 3200));
			expectedList.add(new Fare("JRE", 220, 3530));
			expectedList.add(new Fare("JRE", 240, 3860));
			expectedList.add(new Fare("JRE", 260, 4190));
			expectedList.add(new Fare("JRE", 280, 4520));
			expectedList.add(new Fare("JRE", 300, 4850));
			expectedList.add(new Fare("JRE", 320, 5180));
			expectedList.add(new Fare("JRE", 340, 5510));
			expectedList.add(new Fare("JRE", 360, 5730));
			expectedList.add(new Fare("JRE", 380, 5950));
			expectedList.add(new Fare("JRE", 400, 6280));
			expecteds.add(expectedList);
			
			// Test12: JR地方交通線の運賃体系
			expectedList = new ArrayList<Fare>();
			schemes.add("JRL");
			expectedList.add(new Fare("JRL", 3, 150));
			expectedList.add(new Fare("JRL", 6, 190));
			expectedList.add(new Fare("JRL", 10, 210));
			expectedList.add(new Fare("JRL", 15, 240));
			expectedList.add(new Fare("JRL", 20, 330));
			expectedList.add(new Fare("JRL", 23, 420));
			expectedList.add(new Fare("JRL", 28, 510));
			expectedList.add(new Fare("JRL", 32, 590));
			expectedList.add(new Fare("JRL", 37, 680));
			expectedList.add(new Fare("JRL", 41, 770));
			expectedList.add(new Fare("JRL", 46, 860));
			expectedList.add(new Fare("JRL", 55, 990));
			expectedList.add(new Fare("JRL", 64, 1170));
			expectedList.add(new Fare("JRL", 73, 1340));
			expectedList.add(new Fare("JRL", 82, 1520));
			expectedList.add(new Fare("JRL", 91, 1690));
			expectedList.add(new Fare("JRL", 100, 1880));
			expectedList.add(new Fare("JRL", 110, 1980));
			expectedList.add(new Fare("JRL", 128, 2310));
			expectedList.add(new Fare("JRL", 146, 2640));
			expectedList.add(new Fare("JRL", 164, 3080));
			expectedList.add(new Fare("JRL", 182, 3410));
			expectedList.add(new Fare("JRL", 200, 3740));
			expectedList.add(new Fare("JRL", 219, 4070));
			expectedList.add(new Fare("JRL", 237, 4510));
			expectedList.add(new Fare("JRL", 255, 4840));
			expectedList.add(new Fare("JRL", 273, 5170));
			expectedList.add(new Fare("JRL", 291, 5500));
			expectedList.add(new Fare("JRL", 310, 5720));
			expecteds.add(expectedList);
			
			// Test13: 小田急線の運賃体系
			expectedList = new ArrayList<Fare>();
			schemes.add("OH");
			expectedList.add(new Fare("OH", 3, 140));
			expectedList.add(new Fare("OH", 6, 170));
			expectedList.add(new Fare("OH", 9, 200));
			expectedList.add(new Fare("OH", 13, 220));
			expectedList.add(new Fare("OH", 17, 270));
			expectedList.add(new Fare("OH", 21, 300));
			expectedList.add(new Fare("OH", 25, 330));
			expectedList.add(new Fare("OH", 29, 360));
			expectedList.add(new Fare("OH", 33, 390));
			expectedList.add(new Fare("OH", 37, 430));
			expectedList.add(new Fare("OH", 41, 480));
			expectedList.add(new Fare("OH", 46, 520));
			expectedList.add(new Fare("OH", 51, 560));
			expectedList.add(new Fare("OH", 56, 610));
			expectedList.add(new Fare("OH", 61, 650));
			expectedList.add(new Fare("OH", 66, 700));
			expectedList.add(new Fare("OH", 71, 750));
			expectedList.add(new Fare("OH", 76, 800));
			expectedList.add(new Fare("OH", 81, 850));
			expectedList.add(new Fare("OH", 83, 910));
			expecteds.add(expectedList);
			
			// テストパラメータの返却
			return Stream.of(
					Arguments.of(schemes.get(0), expecteds.get(0)),
					Arguments.of(schemes.get(1), expecteds.get(1)),
					Arguments.of(schemes.get(2), expecteds.get(2)),
					Arguments.of(schemes.get(3), expecteds.get(3))
				   );
		}
	
	}

}
