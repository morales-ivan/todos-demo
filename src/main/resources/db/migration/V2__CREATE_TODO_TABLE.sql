create table todo
(
    id bigserial not null
        constraint todo_pkey
            primary key,
    date_created timestamp,
    description varchar(255),
    last_modified timestamp,
    title varchar(255),
    todo_status varchar(255)
);

alter table todo owner to sa;

