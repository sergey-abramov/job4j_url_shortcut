create table statistic (
    id serial primary key not null,
    count_call int default 0,
    url_id int references url(id)
);

alter table url add column statistic_id int references statistic(id);
