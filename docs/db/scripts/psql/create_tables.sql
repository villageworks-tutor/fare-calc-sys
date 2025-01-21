DROP TABLE IF EXISTS ride;
DROP TABLE IF EXISTS station;
DROP TABLE IF EXISTS fare;
DROP TABLE IF EXISTS pricing_scheme;
DROP TABLE IF EXISTS line;

/**********************************/
/* テーブル名: 路線コード */
/**********************************/
CREATE TABLE line(
		code VARCHAR(2) NOT NULL,
		name VARCHAR(10) NOT NULL
);

/**********************************/
/* テーブル名: 運賃体系 */
/**********************************/
CREATE TABLE pricing_scheme(
	code VARCHAR(3)	 NOT NULL,
	name VARCHAR(10) NOT NULL
);

/**********************************/
/* テーブル名: 運賃 */
/**********************************/
CREATE TABLE fare(
	code     VARCHAR(3)    NOT NULL,	
	distance INTEGER NOT NULL,
	value INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 駅 */
/**********************************/
CREATE TABLE station(
		id SERIAL,
		code VARCHAR(2) NOT NULL,
		pricing_scheme VARCHAR(3),
		name VARCHAR(10) NOT NULL,
		distance INTEGER NOT NULL,
		total_distance INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 乗車情報 */
/**********************************/
CREATE TABLE ride(
		id SERIAL,
		boarding CHARACTER(12) NOT NULL,
		distination CHARACTER(12) NOT NULL,
		distance INTEGER NOT NULL,
		fare INTEGER NOT NULL
);


ALTER TABLE line ADD CONSTRAINT IDX_line_PK PRIMARY KEY (code);

ALTER TABLE station ADD CONSTRAINT IDX_station_PK PRIMARY KEY (id);
ALTER TABLE station ADD CONSTRAINT IDX_station_FK0 FOREIGN KEY (code) REFERENCES line (code);

ALTER TABLE ride ADD CONSTRAINT IDX_ride_PK PRIMARY KEY (id);
-- ALTER TABLE ride ADD CONSTRAINT IDX_ride_FK0 FOREIGN KEY (boarding) REFERENCES station (id);
-- ALTER TABLE ride ADD CONSTRAINT IDX_ride_FK1 FOREIGN KEY (distination) REFERENCES station (id);
-- CREATE INDEX IDX_ride_FK_boarding ON ride (boarding);
-- CREATE INDEX IDX_ride_FK_distination ON ride (distination);

-- 路線マスタのサンプルレコード
INSERT INTO line VALUES ('JY', 'JR山手線');
INSERT INTO line VALUES ('JU', 'JR宇都宮線');
INSERT INTO line VALUES ('OH', '小田急本線');
INSERT INTO line VALUES ('OM', '小田急多摩線');

-- 運賃体系マスタのサンプルレコード
INSERT INTO pricing_scheme VALUES ('OH', '小田急本線');
INSERT INTO pricing_scheme VALUES ('JRE', 'JR東京電車特別区');
INSERT INTO pricing_scheme VALUES ('JRL', 'JR地方交通線');


-- 駅マスタのサンプルレコード
--：山手線
INSERT INTO fare (code, distance, value) VALUES 
	('JY', 3, 150), 
	('JY', 6, 170), 
	('JY', 10, 180), 
	('JY', 15, 210), 
	('JY', 20, 280);
--：小田急本線
INSERT INTO fare (code, distance, value) VALUES 
	('OH', 3, 140), 
	('OH', 6, 170), 
	('OH', 9, 200), 
	('OH', 13, 220), 
	('OH', 17, 270), 
	('OH', 21, 300), 
	('OH', 25, 330), 
	('OH', 29, 360), 
	('OH', 33, 390), 
	('OH', 37, 430), 
	('OH', 41, 480), 
	('OH', 46, 520), 
	('OH', 51, 560), 
	('OH', 56, 610), 
	('OH', 61, 650), 
	('OH', 66, 700), 
	('OH', 71, 750), 
	('OH', 76, 800), 
	('OH', 81, 850), 
	('OH', 83, 910);

--：JR東京電車特定区間
INSERT INTO fare (code, distance, value) VALUES 
	('JRE', 3, 150), 
	('JRE', 6, 170), 
	('JRE', 10, 180), 
	('JRE', 15, 230), 
	('JRE', 20, 320), 
	('JRE', 25, 410), 
	('JRE', 30, 490), 
	('JRE', 35, 580), 
	('JRE', 40, 660), 
	('JRE', 45, 740), 
	('JRE', 50, 830), 
	('JRE', 60, 950), 
	('JRE', 70, 1110), 
	('JRE', 80, 1280), 
	('JRE', 90, 1460), 
	('JRE', 100, 1620), 
	('JRE', 120, 1880), 
	('JRE', 140, 2210), 
	('JRE', 160, 2540), 
	('JRE', 180, 2870), 
	('JRE', 200, 3200), 
	('JRE', 220, 3530), 
	('JRE', 240, 3860), 
	('JRE', 260, 4190), 
	('JRE', 280, 4520), 
	('JRE', 300, 4850), 
	('JRE', 320, 5180), 
	('JRE', 340, 5510), 
	('JRE', 360, 5730), 
	('JRE', 380, 5950), 
	('JRE', 400, 6280);

--：JR地方交通線
INSERT INTO fare (code, distance, value) VALUES 
	('JRL', 3, 150), 
	('JRL', 6, 190), 
	('JRL', 10, 210), 
	('JRL', 15, 240), 
	('JRL', 20, 330), 
	('JRL', 23, 420), 
	('JRL', 28, 510), 
	('JRL', 32, 590), 
	('JRL', 37, 680), 
	('JRL', 41, 770), 
	('JRL', 46, 860), 
	('JRL', 55, 990), 
	('JRL', 64, 1170), 
	('JRL', 73, 1340), 
	('JRL', 82, 1520), 
	('JRL', 91, 1690), 
	('JRL', 100, 1880), 
	('JRL', 110, 1980), 
	('JRL', 128, 2310), 
	('JRL', 146, 2640), 
	('JRL', 164, 3080), 
	('JRL', 182, 3410), 
	('JRL', 200, 3740), 
	('JRL', 219, 4070), 
	('JRL', 237, 4510), 
	('JRL', 255, 4840), 
	('JRL', 273, 5170), 
	('JRL', 291, 5500), 
	('JRL', 310, 5720);

-- 駅マスタのサンプルレコード
--：山手線
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('JY', 'JY', '品川', 0, 0), 
	('JY', 'JY', '大崎', 2, 2), 
	('JY', 'JY', '五反田', 0.9, 2.9), 
	('JY', 'JY', '目黒', 1.2, 4.1), 
	('JY', 'JY', '恵比寿', 1.5, 5.6), 
	('JY', 'JY', '渋谷', 1.6, 7.2), 
	('JY', 'JY', '原宿', 1.2, 8.4), 
	('JY', 'JY', '代々木', 1.5, 9.9), 
	('JY', 'JY', '新宿', 0.7, 10.6), 
	('JY', 'JY', '新大久保', 1.3, 11.9), 
	('JY', 'JY', '高田馬場', 1.4, 13.3), 
	('JY', 'JY', '目白', 0.9, 14.2), 
	('JY', 'JY', '池袋', 1.2, 15.4), 
	('JY', 'JY', '大塚', 1.8, 17.2), 
	('JY', 'JY', '巣鴨', 1.1, 18.3), 
	('JY', 'JY', '駒込', 0.7, 19), 
	('JY', 'JY', '田端', 1.6, 20.6), 
	('JY', 'JY', '西日暮里', 0.8, 21.4), 
	('JY', 'JY', '日暮里', 0.5, 21.9), 
	('JY', 'JY', '鶯谷', 1.1, 23), 
	('JY', 'JY', '上野', 1.1, 24.1), 
	('JY', 'JY', '御徒町', 0.6, 24.7), 
	('JY', 'JY', '秋葉原', 1, 25.7), 
	('JY', 'JY', '神田', 0.7, 26.4), 
	('JY', 'JY', '東京', 1.3, 27.7), 
	('JY', 'JY', '有楽町', 0.8, 28.5), 
	('JY', 'JY', '新橋', 1.1, 29.6), 
	('JY', 'JY', '浜松町', 1.2, 30.8), 
	('JY', 'JY', '田町', 1.5, 32.3), 
	('JY', 'JY', '高輪ゲートウェイ', 1.3, 33.6), 
	('JY', 'JY', '品川', 0.9, 34.5);

--：小田急本線
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('OH', 'OH', '新宿', 0, 0),
	('OH', 'OH', '南新宿', 0.8, 0.8),
	('OH', 'OH', '参宮橋', 0.7, 1.5),
	('OH', 'OH', '代々木八幡', 1.2, 2.7),
	('OH', 'OH', '代々木上原', 0.8, 3.5),
	('OH', 'OH', '東北沢', 0.7, 4.2),
	('OH', 'OH', '下北沢', 0.7, 4.9),
	('OH', 'OH', '世田谷代田', 0.7, 5.6),
	('OH', 'OH', '梅ヶ丘', 0.7, 6.3),
	('OH', 'OH', '豪徳寺', 0.7, 7),
	('OH', 'OH', '経堂', 1, 8),
	('OH', 'OH', '千歳船橋', 1.2, 9.2),
	('OH', 'OH', '祖師ヶ谷大蔵', 1.4, 10.6),
	('OH', 'OH', '成城学園前', 1, 11.6),
	('OH', 'OH', '喜多見', 1.1, 12.7),
	('OH', 'OH', '狛江', 1.1, 13.8),
	('OH', 'OH', '和泉多摩川', 0.6, 14.4),
	('OH', 'OH', '登戸', 0.8, 15.2),
	('OH', 'OH', '向ヶ丘遊園', 0.6, 15.8),
	('OH', 'OH', '生田', 2.1, 17.9),
	('OH', 'OH', '読売ランド前', 1.3, 19.2),
	('OH', 'OH', '百合ヶ丘', 1.3, 20.5),
	('OH', 'OH', '新百合ヶ丘', 1, 21.5),
	('OH', 'OH', '柿生', 1.9, 23.4),
	('OH', 'OH', '鶴川', 1.7, 25.1),
	('OH', 'OH', '玉川学園前', 2.8, 27.9),
	('OH', 'OH', '町田', 2.9, 30.8),
	('OH', 'OH', '相模大野', 1.5, 32.3),
	('OH', 'OH', '小田急相模原', 2.4, 34.7),
	('OH', 'OH', '相武台前', 2.2, 36.9),
	('OH', 'OH', '座間', 2.3, 39.2),
	('OH', 'OH', '海老名', 3.3, 42.5),
	('OH', 'OH', '厚木', 1.6, 44.1),
	('OH', 'OH', '本厚木', 1.3, 45.4),
	('OH', 'OH', '愛甲石田', 3.1, 48.5),
	('OH', 'OH', '伊勢原', 3.7, 52.2),
	('OH', 'OH', '鶴巻温泉', 3.7, 55.9),
	('OH', 'OH', '東海大学前', 1.1, 57),
	('OH', 'OH', '秦野', 4.7, 61.7),
	('OH', 'OH', '渋沢', 3.9, 65.6),
	('OH', 'OH', '新松田', 6.2, 71.8),
	('OH', 'OH', '開成', 2.5, 74.3),
	('OH', 'OH', '栢山', 1.9, 76.2),
	('OH', 'OH', '富水', 1.6, 77.8),
	('OH', 'OH', '螢田', 1.4, 79.2),
	('OH', 'OH', '足柄', 1.6, 80.8),
	('OH', 'OH', '小田原', 1.7, 82.5),
	('OH', 'OH', '箱根板橋', 1.7, 84.2),
	('OH', 'OH', '風祭', 1.5, 85.7),
	('OH', 'OH', '入生田', 1, 86.7),
	('OH', 'OH', '箱根湯本', 1.9, 88.6),
	('OH', 'OH', '塔ノ沢', 1, 89.6),
	('OH', 'OH', '大平台', 2.8, 92.4),
	('OH', 'OH', '宮ノ下', 2.2, 94.6),
	('OH', 'OH', '小涌谷', 1.3, 95.9),
	('OH', 'OH', '彫刻の森', 0.9, 96.8),
	('OH', 'OH', '強羅', 0.7, 97.5);

-- 小田急多摩線
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('OM', 'OH', '新百合ヶ丘', 0, 0), 
	('OM', 'OH', '五月台', 1.6, 1.6), 
	('OM', 'OH', '栗平', 1.9, 3.5), 
	('OM', 'OH', '黒川', 1.3, 4.8), 
	('OM', 'OH', 'はるひ野', 1, 5.8), 
	('OM', 'OH', '小田急永山', 1.6, 7.4), 
	('OM', 'OH', '小田急多摩センター', 1.5, 8.9), 
	('OM', 'OH', '唐木田', 2, 10.9);

-- 宇都宮線
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('JU', 'JRE', '上野', 0, 0), 
	('JU', 'JRE', '尾久', 4.8, 4.8), 
	('JU', 'JRE', '赤羽', 5, 9.8), 
	('JU', 'JRE', '浦和', 11, 20.8), 
	('JU', 'JRE', 'さいたま新都心', 4.5, 25.3), 
	('JU', 'JRE', '大宮', 1.6, 26.9), 
	('JU', 'JRL', '大宮', 1.6, 26.9), 
	('JU', 'JRL', '土呂', 3, 29.9), 
	('JU', 'JRL', '東大宮', 2.1, 32), 
	('JU', 'JRL', '蓮田', 3.8, 35.8), 
	('JU', 'JRL', '白岡', 4.3, 40.1), 
	('JU', 'JRL', '新白岡', 2.4, 42.5), 
	('JU', 'JRL', '久喜', 3, 45.5), 
	('JU', 'JRL', '東鷲宮', 2.7, 48.2), 
	('JU', 'JRL', '栗橋', 5.6, 53.8), 
	('JU', 'JRL', '古河', 7.5, 61.3), 
	('JU', 'JRL', '野木', 4.7, 66), 
	('JU', 'JRL', '間々田', 3.9, 69.9), 
	('JU', 'JRL', '小山', 7.3, 77.2), 
	('JU', 'JRL', '小金井', 7.5, 84.7), 
	('JU', 'JRL', '自治医大', 2.6, 87.3), 
	('JU', 'JRL', '石橋', 4.7, 92), 
	('JU', 'JRL', '雀宮', 6.4, 98.4), 
	('JU', 'JRL', '宇都宮', 7.7, 106.1), 
	('JU', 'JRL', '岡本', 6.2, 112.3), 
	('JU', 'JRL', '宝積寺', 5.5, 117.8), 
	('JU', 'JRL', '氏家', 5.9, 123.7), 
	('JU', 'JRL', '蒲須坂', 4.5, 128.2), 
	('JU', 'JRL', '片岡', 3.9, 132.1), 
	('JU', 'JRL', '矢板', 6.3, 138.4), 
	('JU', 'JRL', '野崎', 4.8, 143.2), 
	('JU', 'JRL', '西那須野', 5.2, 148.4), 
	('JU', 'JRL', '那須塩原', 6, 154.4), 
	('JU', 'JRL', '黒磯', 5.5, 159.9);
/*

*/

