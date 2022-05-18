--liquibase formatted sql

--changeset Alik:1
create table orders
(
    id                bigint                              not null generated by default as identity
        constraint pk_orders
            primary key,
    created_at        timestamp default current_timestamp not null,
    updated_at        timestamp,
    name              varchar(200)                        not null,
    description       text,
    destination       text                                not null,
    status            varchar(50)                         not null,
    weight            numeric,
    amount            numeric                             not null,
    delivery_amount   numeric                             not null,
    total_amount      numeric                             not null,
    customer_username varchar(200)                        not null,
    courier_name      varchar(200)
);

--changeset Alik:2-create-order_id-index
create index order_order_id_idx on orders (id);

--changeset Alik:3-create-customer_username-index
create index order_customer_username_idx on orders (customer_username);