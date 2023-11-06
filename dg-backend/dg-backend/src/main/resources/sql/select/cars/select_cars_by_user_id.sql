SELECT
    c.id,
    c.make,
    c.model,
    c.production_year
FROM
    cars AS c
WHERE
    c.user_id = ?;

