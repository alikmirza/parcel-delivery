--liquibase formatted sql

--changeset Alik:2
create table roles
(
    id         bigint                              not null generated by default as identity
        constraint pk_roles
            primary key,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp,
    name       varchar(200)                        not null
);

--changeset Alik:2-create-role_id-index
create
    index role_role_id_idx on roles (id);