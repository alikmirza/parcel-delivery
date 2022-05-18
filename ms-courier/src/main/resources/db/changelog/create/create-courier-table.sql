--liquibase formatted sql

--changeset Alik:1
create table couriers
(
    id         bigint                              not null generated by default as identity
        constraint pk_couriers
            primary key,
    username   varchar(200)                        not null,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp,
    vehicle    varchar(50)                         not null
);

--changeset Alik:2-create-user_id-index
create index courier_courier_id_idx on couriers (id);