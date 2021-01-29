delete from room_type;
delete from authorities;
delete from users;

insert into users (username, password, enabled) values
    ('admin', '$2y$12$IM5uQDyYXPcxa6/LgdzmsOXymMluC2D9Eo0Lfd4gE9qcPm3t7.qCe', 1),
    ('user', '$2y$12$v39PA2DJQ9X0nHXctHhcWeSURSgJVbcPSoO2cxVQbAETsMytjteEq', 1);

insert into authorities (username, authority) values
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('user', 'ROLE_USER');

insert into room_type (id, name, number_of_beds, price, short_description)  values
    (1, 'Test 1', 1, 123.12, 'short 1'),
    (2, 'Test 2', 2, 246.35, 'short 2');


