--liquibase formatted sql

--changeset Alik:2
create table user_roles
(
    user_id bigint not null
        constraint fk_user_id
            references users (id),
    role_id bigint not null
        constraint fk_role_id
            references roles (id)
);
