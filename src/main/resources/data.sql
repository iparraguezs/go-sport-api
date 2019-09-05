INSERT INTO user (username, password, email, created_at, updated_at, enabled_at, disabled_at) VALUES 
('admin', '$2a$10$v5IwV21.f/4HoO.sqH1zL.SbxdnGquMUkcKU79.3YLURG2veJIugi', 'admin@go-sport.cl', NOW(), NOW(), NOW(), null),
('cmartinezs', '$2a$10$MBNqejAi2ElcJU6p0k/Y5e1PAJLkkOC10Tp0JVx133R/v7rBP/NzO', 'carlos.f.martinez.s@gmail.com', NOW(), NOW(), NOW(), null),
('jrodriguez121989', '$2a$10$GDjoNsTDWyyagO/ulRylhuM05eoX1IwpTMJN6Rt/NMaXeEFZ335.a', 'jonathane.rodriguez@gmail.com', NOW(), NOW(), NOW(), null),
('jorgesilva16', '$2a$10$XH3O0InwRaXMycOzjuKFxevMrJj0Rc5kJz8eqVGMLIgFkXUjB9HBS', 'jorge.silva2@virginiogomez.cl', NOW(), NOW(), NOW(), null),
('darwinrosas2107', '$2a$10$XH3O0InwRaXMycOzjuKFxevMrJj0Rc5kJz8eqVGMLIgFkXUjB9HBS', 'darwinrosas23@gmail.com', NOW(), NOW(), NOW(), null),
('invitado', '$2a$10$A3F7nbdGgW9Vjohroipm4uzljihYSYM/XyKF5PJ/bobSD49.QrRvm', 'invitado@gmail.com', NOW(), NOW(), NOW(), null);

INSERT INTO role (code, name, description, created_at, updated_at, enabled_at, disabled_at) VALUES
('ADM', 'ROLE_ADMIN', 'Administrador', NOW(), NOW(), NOW(), null),
('USR', 'ROLE_USER', 'Usuario', NOW(), NOW(), NOW(), null);

INSERT INTO assignment (user_id, role_id, created_at, updated_at,  enabled_at, disabled_at) VALUES
(1, 1, NOW(), NOW(), NOW(), null),
(2, 1, NOW(), NOW(), NOW(), null),
(2, 2, NOW(), NOW(), NOW(), null),
(3, 2, NOW(), NOW(), NOW(), null),
(4, 2, NOW(), NOW(), NOW(), null),
(5, 2, NOW(), NOW(), NOW(), null),
(6, 2, NOW(), NOW(), NOW(), null);

INSERT INTO function (code, name, description, created_at, updated_at, enabled_at, disabled_at) VALUES
('USR', 'USER', 'Usuario', NOW(), NOW(), NOW(), null),
('RL', 'ROLE', 'Rol', NOW(), NOW(), NOW(), null),
('FNCTN', 'FUNCTION', 'Función', NOW(), NOW(), NOW(), null),
('ASSGNMT', 'ASSIGNMENT', 'Asignación',NOW(), NOW(), NOW(), null),
('TM', 'TEAM', 'Equipo', NOW(), NOW(), NOW(), null),
('MTCH', 'MATCH', 'Partido', NOW(), NOW(), NOW(), null),
('TRNMT', 'TOURNAMENT', 'Torneo',NOW(), NOW(), NOW(), null),
('MTCHRSLTS', 'MATCH_RESULTS', 'Resultados', NOW(), NOW(), NOW(), null),
('MMBR', 'MEMBER', 'Miembro', NOW(), NOW(), NOW(), null);

INSERT INTO functionality (role_id, function_id, created_at, updated_at,  enabled_at, disabled_at) VALUES
(1, 1, NOW(), NOW(), NOW(), null),
(1, 2, NOW(), NOW(), NOW(), null),
(1, 3, NOW(), NOW(), NOW(), null),
(1, 4, NOW(), NOW(), NOW(), null),
(2, 5, NOW(), NOW(), NOW(), null),
(2, 6, NOW(), NOW(), NOW(), null),
(2, 7, NOW(), NOW(), NOW(), null),
(2, 8, NOW(), NOW(), NOW(), null),
(2, 9, NOW(), NOW(), NOW(), null);