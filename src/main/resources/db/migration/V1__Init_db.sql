create table cars(
    id                bigint           not null auto_increment,
    damage_status     varchar(255),
    is_deleted        bit,
    employment_status bit              not null,
    img_link          varchar(255),
    model             varchar(2048),
    price_per_day     double precision not null,
    producer          varchar(2048),
    release_date      varchar(2048),
    primary key (id)
);

create table hibernate_sequence(
    next_val bigint
);

insert into hibernate_sequence
values (1);

create table orders(
    id            bigint           not null,
    order_date    datetime,
    order_status  integer,
    price         double precision not null,
    rental_period integer          not null,
    car_id        bigint,
    refund_id     bigint,
    user_id       bigint,
    primary key (id)
);

create table passports
(
    id         bigint not null,
    address    varchar(255),
    birthday   varchar(255),
    name       varchar(255),
    patronymic varchar(255),
    surname    varchar(255),
    primary key (id)
);

create table refunds(
    id                 bigint           not null,
    damage_description varchar(255),
    damage_status      bit,
    price              double precision not null,
    refund_date        datetime,
    primary key (id)
);

create table user_role(
    user_id bigint not null,
    roles   varchar(255)
);

create table users(
    id              bigint not null auto_increment,
    activation_code varchar(255),
    active          bit    not null,
    email           varchar(255),
    password        varchar(255),
    username        varchar(255),
    passport_id     bigint,
    primary key (id)
);

alter table orders
    add constraint order_car_fk foreign key (car_id) references cars (id);
alter table orders
    add constraint order_refund_fk foreign key (refund_id) references refunds (id);
alter table orders
    add constraint order_user_fk foreign key (user_id) references users (id);
alter table user_role
    add constraint user_role_user_fk foreign key (user_id) references users (id);
alter table users
    add constraint user_passports_fk foreign key (passport_id) references passports (id);

INSERT INTO users(id, username, password, active, email)
VALUES (1, 'admin', 1, 0x01,'stasonpokemon@icloud.com');
INSERT INTO user_role(user_id, roles)
VALUES (1, 'ADMIN'), (1, 'USER');

INSERT INTO cars(id, producer, model, release_date, price_per_day, employment_status, damage_status, img_link, is_deleted)
VALUES (1, 'Audi', 'A7', '2015', 290, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=640acbdf28144e25d6bcb0910a8d5f36-5330388-images-thumbs&n=13', 0x00),
       (2, 'BMW', 'm3', '2017', 400, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=45c37ba01c9998628492f293a45a110a-5887419-images-thumbs&n=13', 0x00),
       (3, 'Mercedes', '600', '2003', 110, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=2a0000017a06d13e75e6bf48ed2fc6652583-4751583-images-thumbs&n=13', 0x00),
       (4, 'BMW', 'I8', '2021', 570, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=f5dc107fb114e6c831200ddd59d82a66-5503033-images-thumbs&n=13', 0x00),
       (5, 'Bentley', 'Continental gt', '2021', 700, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=0c7086819b8e7e59b4655fa56210d526-5140197-images-thumbs&n=13', 0x00),
       (6, 'Maclaren', '720s', '2021', 800, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=2935e7d83106b4c9d0e7e3ef32317107-5436885-images-thumbs&n=13', 0x00),
       (7, 'Ferrari', '488 Pista', '2018', 500, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=a1a43df466e944e61a1d5b750adf2af2-4407062-images-thumbs&n=13', 0x00),
       (8, 'Nissan', 'GTR', '2017', 700, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=0c11cd4e88b398ed905ce966735cfc58-4954930-images-thumbs&n=13', 0x00);

