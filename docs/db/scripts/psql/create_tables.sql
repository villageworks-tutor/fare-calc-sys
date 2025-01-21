DROP TABLE IF EXISTS ride;
DROP TABLE IF EXISTS station;
DROP TABLE IF EXISTS fare;
DROP TABLE IF EXISTS pricing_scheme;
DROP TABLE IF EXISTS line;

/**********************************/
/* �e�[�u����: �H���R�[�h */
/**********************************/
CREATE TABLE line(
		code VARCHAR(2) NOT NULL,
		name VARCHAR(10) NOT NULL
);

/**********************************/
/* �e�[�u����: �^���̌n */
/**********************************/
CREATE TABLE pricing_scheme(
	code VARCHAR(3)	 NOT NULL,
	name VARCHAR(10) NOT NULL
);

/**********************************/
/* �e�[�u����: �^�� */
/**********************************/
CREATE TABLE fare(
	code     VARCHAR(3)    NOT NULL,	
	distance INTEGER NOT NULL,
	value INTEGER NOT NULL
);

/**********************************/
/* �e�[�u����: �w */
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
/* �e�[�u����: ��ԏ�� */
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

-- �H���}�X�^�̃T���v�����R�[�h
INSERT INTO line VALUES ('JY', 'JR�R���');
INSERT INTO line VALUES ('JU', 'JR�F�s�{��');
INSERT INTO line VALUES ('OH', '���c�}�{��');
INSERT INTO line VALUES ('OM', '���c�}������');

-- �^���̌n�}�X�^�̃T���v�����R�[�h
INSERT INTO pricing_scheme VALUES ('OH', '���c�}�{��');
INSERT INTO pricing_scheme VALUES ('JRE', 'JR�����d�ԓ��ʋ�');
INSERT INTO pricing_scheme VALUES ('JRL', 'JR�n����ʐ�');


-- �w�}�X�^�̃T���v�����R�[�h
--�F�R���
INSERT INTO fare (code, distance, value) VALUES 
	('JY', 3, 150), 
	('JY', 6, 170), 
	('JY', 10, 180), 
	('JY', 15, 210), 
	('JY', 20, 280);
--�F���c�}�{��
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

--�FJR�����d�ԓ�����
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

--�FJR�n����ʐ�
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

-- �w�}�X�^�̃T���v�����R�[�h
--�F�R���
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('JY', 'JY', '�i��', 0, 0), 
	('JY', 'JY', '���', 2, 2), 
	('JY', 'JY', '�ܔ��c', 0.9, 2.9), 
	('JY', 'JY', '�ڍ�', 1.2, 4.1), 
	('JY', 'JY', '�b���', 1.5, 5.6), 
	('JY', 'JY', '�a�J', 1.6, 7.2), 
	('JY', 'JY', '���h', 1.2, 8.4), 
	('JY', 'JY', '��X��', 1.5, 9.9), 
	('JY', 'JY', '�V�h', 0.7, 10.6), 
	('JY', 'JY', '�V��v��', 1.3, 11.9), 
	('JY', 'JY', '���c�n��', 1.4, 13.3), 
	('JY', 'JY', '�ڔ�', 0.9, 14.2), 
	('JY', 'JY', '�r��', 1.2, 15.4), 
	('JY', 'JY', '���', 1.8, 17.2), 
	('JY', 'JY', '����', 1.1, 18.3), 
	('JY', 'JY', '�', 0.7, 19), 
	('JY', 'JY', '�c�[', 1.6, 20.6), 
	('JY', 'JY', '�����闢', 0.8, 21.4), 
	('JY', 'JY', '���闢', 0.5, 21.9), 
	('JY', 'JY', '��J', 1.1, 23), 
	('JY', 'JY', '���', 1.1, 24.1), 
	('JY', 'JY', '��k��', 0.6, 24.7), 
	('JY', 'JY', '�H�t��', 1, 25.7), 
	('JY', 'JY', '�_�c', 0.7, 26.4), 
	('JY', 'JY', '����', 1.3, 27.7), 
	('JY', 'JY', '�L�y��', 0.8, 28.5), 
	('JY', 'JY', '�V��', 1.1, 29.6), 
	('JY', 'JY', '�l����', 1.2, 30.8), 
	('JY', 'JY', '�c��', 1.5, 32.3), 
	('JY', 'JY', '���փQ�[�g�E�F�C', 1.3, 33.6), 
	('JY', 'JY', '�i��', 0.9, 34.5);

--�F���c�}�{��
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('OH', 'OH', '�V�h', 0, 0),
	('OH', 'OH', '��V�h', 0.8, 0.8),
	('OH', 'OH', '�Q�{��', 0.7, 1.5),
	('OH', 'OH', '��X�ؔ���', 1.2, 2.7),
	('OH', 'OH', '��X�؏㌴', 0.8, 3.5),
	('OH', 'OH', '���k��', 0.7, 4.2),
	('OH', 'OH', '���k��', 0.7, 4.9),
	('OH', 'OH', '���c�J��c', 0.7, 5.6),
	('OH', 'OH', '�~���u', 0.7, 6.3),
	('OH', 'OH', '������', 0.7, 7),
	('OH', 'OH', '�o��', 1, 8),
	('OH', 'OH', '��ΑD��', 1.2, 9.2),
	('OH', 'OH', '�c�t���J�呠', 1.4, 10.6),
	('OH', 'OH', '����w���O', 1, 11.6),
	('OH', 'OH', '�쑽��', 1.1, 12.7),
	('OH', 'OH', '���]', 1.1, 13.8),
	('OH', 'OH', '�a�򑽖���', 0.6, 14.4),
	('OH', 'OH', '�o��', 0.8, 15.2),
	('OH', 'OH', '�����u�V��', 0.6, 15.8),
	('OH', 'OH', '���c', 2.1, 17.9),
	('OH', 'OH', '�ǔ������h�O', 1.3, 19.2),
	('OH', 'OH', '�S�����u', 1.3, 20.5),
	('OH', 'OH', '�V�S�����u', 1, 21.5),
	('OH', 'OH', '�`��', 1.9, 23.4),
	('OH', 'OH', '�ߐ�', 1.7, 25.1),
	('OH', 'OH', '�ʐ�w���O', 2.8, 27.9),
	('OH', 'OH', '���c', 2.9, 30.8),
	('OH', 'OH', '���͑��', 1.5, 32.3),
	('OH', 'OH', '���c�}���͌�', 2.4, 34.7),
	('OH', 'OH', '������O', 2.2, 36.9),
	('OH', 'OH', '����', 2.3, 39.2),
	('OH', 'OH', '�C�V��', 3.3, 42.5),
	('OH', 'OH', '����', 1.6, 44.1),
	('OH', 'OH', '�{����', 1.3, 45.4),
	('OH', 'OH', '���b�Γc', 3.1, 48.5),
	('OH', 'OH', '�ɐ���', 3.7, 52.2),
	('OH', 'OH', '�ߊ�����', 3.7, 55.9),
	('OH', 'OH', '���C��w�O', 1.1, 57),
	('OH', 'OH', '�`��', 4.7, 61.7),
	('OH', 'OH', '�a��', 3.9, 65.6),
	('OH', 'OH', '�V���c', 6.2, 71.8),
	('OH', 'OH', '�J��', 2.5, 74.3),
	('OH', 'OH', '���R', 1.9, 76.2),
	('OH', 'OH', '�x��', 1.6, 77.8),
	('OH', 'OH', '壓c', 1.4, 79.2),
	('OH', 'OH', '����', 1.6, 80.8),
	('OH', 'OH', '���c��', 1.7, 82.5),
	('OH', 'OH', '������', 1.7, 84.2),
	('OH', 'OH', '����', 1.5, 85.7),
	('OH', 'OH', '�����c', 1, 86.7),
	('OH', 'OH', '�������{', 1.9, 88.6),
	('OH', 'OH', '���m��', 1, 89.6),
	('OH', 'OH', '�啽��', 2.8, 92.4),
	('OH', 'OH', '�{�m��', 2.2, 94.6),
	('OH', 'OH', '���O�J', 1.3, 95.9),
	('OH', 'OH', '�����̐X', 0.9, 96.8),
	('OH', 'OH', '����', 0.7, 97.5);

-- ���c�}������
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('OM', 'OH', '�V�S�����u', 0, 0), 
	('OM', 'OH', '�܌���', 1.6, 1.6), 
	('OM', 'OH', '�I��', 1.9, 3.5), 
	('OM', 'OH', '����', 1.3, 4.8), 
	('OM', 'OH', '�͂�Ж�', 1, 5.8), 
	('OM', 'OH', '���c�}�i�R', 1.6, 7.4), 
	('OM', 'OH', '���c�}�����Z���^�[', 1.5, 8.9), 
	('OM', 'OH', '���ؓc', 2, 10.9);

-- �F�s�{��
INSERT INTO station (code, pricing_scheme, name, distance, total_distance) VALUES
	('JU', 'JRE', '���', 0, 0), 
	('JU', 'JRE', '���v', 4.8, 4.8), 
	('JU', 'JRE', '�ԉH', 5, 9.8), 
	('JU', 'JRE', '�Y�a', 11, 20.8), 
	('JU', 'JRE', '�������ܐV�s�S', 4.5, 25.3), 
	('JU', 'JRE', '��{', 1.6, 26.9), 
	('JU', 'JRL', '��{', 1.6, 26.9), 
	('JU', 'JRL', '�y�C', 3, 29.9), 
	('JU', 'JRL', '����{', 2.1, 32), 
	('JU', 'JRL', '�@�c', 3.8, 35.8), 
	('JU', 'JRL', '����', 4.3, 40.1), 
	('JU', 'JRL', '�V����', 2.4, 42.5), 
	('JU', 'JRL', '�v��', 3, 45.5), 
	('JU', 'JRL', '���h�{', 2.7, 48.2), 
	('JU', 'JRL', '�I��', 5.6, 53.8), 
	('JU', 'JRL', '�É�', 7.5, 61.3), 
	('JU', 'JRL', '���', 4.7, 66), 
	('JU', 'JRL', '�ԁX�c', 3.9, 69.9), 
	('JU', 'JRL', '���R', 7.3, 77.2), 
	('JU', 'JRL', '������', 7.5, 84.7), 
	('JU', 'JRL', '�������', 2.6, 87.3), 
	('JU', 'JRL', '�΋�', 4.7, 92), 
	('JU', 'JRL', '���{', 6.4, 98.4), 
	('JU', 'JRL', '�F�s�{', 7.7, 106.1), 
	('JU', 'JRL', '���{', 6.2, 112.3), 
	('JU', 'JRL', '��ώ�', 5.5, 117.8), 
	('JU', 'JRL', '����', 5.9, 123.7), 
	('JU', 'JRL', '���{��', 4.5, 128.2), 
	('JU', 'JRL', '�Љ�', 3.9, 132.1), 
	('JU', 'JRL', '���', 6.3, 138.4), 
	('JU', 'JRL', '���', 4.8, 143.2), 
	('JU', 'JRL', '���ߐ{��', 5.2, 148.4), 
	('JU', 'JRL', '�ߐ{����', 6, 154.4), 
	('JU', 'JRL', '����', 5.5, 159.9);
/*

*/

