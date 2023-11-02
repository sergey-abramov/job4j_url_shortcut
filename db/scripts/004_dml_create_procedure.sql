create function updateStatistic(INOUT urlId int)
    as
$$
update statistic
SET count_call = count_call + 1
where url_id = urlId returning url_id;
$$
language sql;