DELETE FROM TOFFER;
DELETE FROM TMERCHNT;
DELETE FROM TOFFTYPE;
DELETE FROM TOFFCAT;
-- MERCHANT DATA

INSERT INTO TMERCHNT(ID, CODE, NAME, CURRENCY_CODE) VALUES (1, 'ANZ001', 'BINGO REPUBLIC' ,'GBP');
INSERT INTO TMERCHNT(ID, CODE, NAME, CURRENCY_CODE) VALUES (2, 'DMD001', 'DRAMA STUDIOS' ,'GBP');
INSERT INTO TMERCHNT(ID, CODE, NAME, CURRENCY_CODE) VALUES (3, 'MRG001', 'TOURS AND OPERATOR' ,'GBP');
INSERT INTO TMERCHNT(ID, CODE, NAME, CURRENCY_CODE) VALUES (4, 'PHT001', 'THE COFFEE SHOP' ,'GBP');
INSERT INTO TMERCHNT(ID, CODE, NAME, CURRENCY_CODE) VALUES (5, 'RCT001', 'Rococo Chocolates' ,'GBP');
INSERT INTO TMERCHNT(ID, CODE, NAME, CURRENCY_CODE) VALUES (6, 'HDT001', 'Halcyon Days' ,'GBP');
--OFFER TYPE DATA
INSERT INTO TOFFTYPE(ID, OFFER_TYPE) VALUES (100057, '% off');
INSERT INTO TOFFTYPE(ID, OFFER_TYPE) VALUES (100058, '2 for 1');
INSERT INTO TOFFTYPE(ID, OFFER_TYPE) VALUES (100059, 'FREE');
INSERT INTO TOFFTYPE(ID, OFFER_TYPE) VALUES (100060, 'BUY AND SAVE');
--CATEGORY DATA
INSERT INTO TOFFCAT(ID, CATEGORY_NAME) VALUES (200057, 'Travel');
INSERT INTO TOFFCAT(ID, CATEGORY_NAME) VALUES (200058, 'Food + Wine');
INSERT INTO TOFFCAT(ID, CATEGORY_NAME) VALUES (200059, 'Shopping + Retail');
INSERT INTO TOFFCAT(ID, CATEGORY_NAME) VALUES (200060, 'Sports + Entertainment');
--OFFER TYPE DATA
INSERT INTO TOFFER(ID, TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES (1, 'Save 10% on Purchase', 200057, 3, 100057, '2017-01-11', '2017-02-11', 'N', 
			'For your next ride to or from My Car get you there. Just book online and enter discount code JIMM to receive 10% off your total fare.');
			
INSERT INTO TOFFER(ID, TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES (2, '£20 off purchases over £200', 200059, 3, 100060, '2017-01-11', '2020-02-11', 'N', 
			'Since 1875 Liberty has proudly stood at the heart of London renowned for its eclectic choice of unique and innovative products coupled with an unforgettable service. From the perfect gift to the one off product you can’t find anywhere else in the world, over six floors of our mock-tudor building, Liberty offers everything for the savvy and sophisticated shopper looking for that special something.');
			
			
INSERT INTO TOFFER(TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES ('10% off all purchases', 200059, 3, 100057, '2017-01-11', '2020-02-11', 'N', 
			'Established in 1730, Juan Floris married his English rose Elizabeth and began creating perfume. Today Floris hold Royal Warrants from The Queen Elizabeth II and HRH The Prince of Wales. Still run by the same family from the original premises, Floris remain purveyors of the finest English perfumes and toiletries.');
				
INSERT INTO TOFFER(TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES ('Complimentary Bee Bar with purchases over £15', 200059, 5, 100059, '2017-01-11', '2020-02-11', 'N', 
			'Rococo Chocolates is a luxury chocolate shop you’ll enjoy from the moment you enter. With over 50 different flavours of chocolate, from organic and sugar free to chocolates shaped as cats, fish and hearts, you’ll be entranced by the variety and quality of chocolate on offer. Original gift-wrapping also available.');
				
INSERT INTO TOFFER(TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES ('Complimentary Artisan Bar with purchases over £30', 200059, 5, 100059, '2017-01-11', '2020-02-11', 'N', 
			'Rococo Chocolates is a luxury chocolate shop you’ll enjoy from the moment you enter. With over 50 different flavours of chocolate, from organic and sugar free to chocolates shaped as cats, fish and hearts, you’ll be entranced by the variety and quality of chocolate on offer. Original gift-wrapping also available.');
					
INSERT INTO TOFFER(TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES ('Complimentary mug cup with purchases over £50', 200059, 5, 100059, '2017-01-11', '2020-02-11', 'N', 
			'Rococo Chocolates is a luxury chocolate shop you’ll enjoy from the moment you enter. With over 50 different flavours of chocolate, from organic and sugar free to chocolates shaped as cats, fish and hearts, you’ll be entranced by the variety and quality of chocolate on offer. Original gift-wrapping also available.');
						
INSERT INTO TOFFER(TITLE, CATEGORY_ID, MERCHANT_ID, TYPE_ID, VALID_FROM, VALID_TO, DELETED, DESCRIPTION) 
		VALUES ('Complimentary mug cup with purchases over £50', 200059, 6, 100057, '2017-01-11', '2020-02-11', 'N', 
			'Halcyon Days is the guardian of enamelling. A business of tradition with a team of highly skilled craftsmen and master artists employing refined techniques and processes. Handmade at the heart of where the industry originated, each piece is a work of art. An emporium of luxury gifts and fashion accessories and proud holders of all three Royal Warrants as Suppliers of Objets dArt, Halcyon Days continue to celebrate the best of British craftsmanship.');
			