DROP TABLE IF EXISTS ride;
DROP TABLE IF EXISTS station;
DROP TABLE IF EXISTS fare;
DROP TABLE IF EXISTS line;

/**********************************/
/* テーブル名: 路線コード */
/**********************************/
CREATE TABLE line(
		code VARCHAR(2) NOT NULL,
		name VARCHAR(10) NOT NULL
);

/**********************************/
/* テーブル名: 運賃 */
/**********************************/
CREATE TABLE fare(
		fare INTEGER NOT NULL,
		distance INTEGER NOT NULL
);

/**********************************/
/* テーブル名: 駅 */
/**********************************/
CREATE TABLE station(
		id SERIAL,
		code VARCHAR(2) NOT NULL,
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
ALTER TABLE ride ADD CONSTRAINT IDX_ride_FK0 FOREIGN KEY (boarding) REFERENCES station (id);
ALTER TABLE ride ADD CONSTRAINT IDX_ride_FK1 FOREIGN KEY (distination) REFERENCES station (id);
CREATE INDEX IDX_ride_FK_boarding ON ride (boarding);
CREATE INDEX IDX_ride_distination ON ride (distination);

