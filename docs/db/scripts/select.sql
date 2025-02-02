-- SELECT * FROM fare WHERE pricing_scheme = 'JRE' AND distance = ceil(9.8);
-- SELECT * FROM station WHERE pricing_scheme = 'JRE' AND name = '‘å‹{';
-- SELECT * FROM station WHERE pricing_scheme = ('JRE') AND name = ('‘å‹{');
-- SELECT * FROM fare WHERE pricing_scheme = ('JRE') AND ceil(distance) = ceil(('26.9'::double precision));
-- SELECT * from fare WHERE pricing_scheme = ('JRE') AND (ceil(('26.9'::double precision)) < (distance::double precision)) ORDER BY distance LIMIT 1;
-- SELECT * FROM fare WHERE pricing_scheme = ('JRE') AND (distance::double precision) > ceil((('9.8'::double precision)::double precision)) ORDER BY distance LIMIT 1
SELECT * FROM fare WHERE pricing_scheme = ('JRE') AND (distance::double precision) >= ceil((('22.1'::double precision)::double precision)) ORDER BY distance LIMIT 1;


