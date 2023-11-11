SELECT
	r.id AS res_id,
	s.name AS services_name,
	s.price AS services_price,
	c.make AS cars_make,
	c.model AS cars_model,
	c.production_year AS cars_year,
	c.colour AS cars_year,
	r.reservation_term AS res_reservation_term
FROM
	reservations AS r
	JOIN services AS s ON r.service_id = s.id
	JOIN cars AS c ON r.car_id = c.id
--WHERE
