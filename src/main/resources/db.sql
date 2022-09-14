DROP
DATABASE IF EXISTS car_rental_spring;
CREATE
DATABASE IF NOT EXISTS car_rental_spring;
USE car_rental_spring;

INSERT INTO users(id, username, password, active)
VALUES (1, 'admin', 1, 0x01);
INSERT INTO user_role(user_id, roles)
VALUES (1, 'ADMIN');

INSERT INTO cars(id, producer, model, release_date, price_per_day, employment_status, damage_status, img_link, is_deleted)
VALUES (1, 'Audi', 'A7', '2015', 100, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=640acbdf28144e25d6bcb0910a8d5f36-5330388-images-thumbs&n=13', 0x00),
       (2, 'BMW', 'm3', '2017', 170, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=45c37ba01c9998628492f293a45a110a-5887419-images-thumbs&n=13', 0x00),
       (3, 'Mercedes', '600', '2003', 110, 0x01, 'Without damage', 'https://avatars.mds.yandex.net/i?id=2a0000017a06d13e75e6bf48ed2fc6652583-4751583-images-thumbs&n=13', 0x00);

