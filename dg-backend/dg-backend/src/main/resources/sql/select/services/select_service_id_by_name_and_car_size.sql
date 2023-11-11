SELECT
    s.id
FROM
    services AS s
WHERE
	s.name = ?
	AND s.car_size = ?::car_size;