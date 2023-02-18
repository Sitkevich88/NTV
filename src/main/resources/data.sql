INSERT INTO "role" (role_name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_CLIENT');

INSERT INTO "theme" (theme_name)
VALUES ('Экономика'), ('Образование'), ('Спорт'), ('ЧС');

INSERT into "my_user" (login, password, role_id)
VALUES ('главный_журналюга', '$2a$10$SuNKjnILwduyP4zKrJca2.HvEHcIZwOOy0C/9i4F/bdra21LSsMNa', 1);