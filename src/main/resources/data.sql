insert into users (username, password, enabled) values
    ('admin', '$2y$12$IM5uQDyYXPcxa6/LgdzmsOXymMluC2D9Eo0Lfd4gE9qcPm3t7.qCe', 1),
    ('user', '$2y$12$v39PA2DJQ9X0nHXctHhcWeSURSgJVbcPSoO2cxVQbAETsMytjteEq', 1);

insert into authorities (username, authority) values
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('user', 'ROLE_USER');