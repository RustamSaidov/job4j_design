INSERT INTO public.role(
            role_name)
    VALUES ('admin');

INSERT INTO public.role(
            role_name)
    VALUES ('user');

INSERT INTO public.rules(
            rule_name)
    VALUES ('full');

INSERT INTO public.rules(
            rule_name)
    VALUES ('read');

INSERT INTO public.category(
            category_name)
    VALUES ('urgent');

INSERT INTO public.category(
            category_name)
    VALUES ('ordinary');

INSERT INTO public.state(
            state_name)
    VALUES ('completed');

INSERT INTO public.state(
            state_name)
    VALUES ('in progress');
    
INSERT INTO public.state(
            state_name)
    VALUES ('not started');

INSERT INTO public.users(
            nickname, role_id)
    VALUES ('adminBoy', 1);

INSERT INTO public.users(
            nickname, role_id)
    VALUES ('userGirl', 2);

INSERT INTO public.role_rules(
             role_id, rules_id)
    VALUES ( 1, 1);

INSERT INTO public.role_rules(
             role_id, rules_id)
    VALUES ( 2, 2);

INSERT INTO public.item(
            user_id, category_id, state_id)
    VALUES (1, 1, 2);

INSERT INTO public.item(
            user_id, category_id, state_id)
    VALUES (2, 2, 1);

INSERT INTO public.item(
            user_id, category_id, state_id)
    VALUES (2, 2, 3);

INSERT INTO public.comments(
            comment, item_id)
    VALUES ('������ �����! ��� �����!', 1);


INSERT INTO public.comments(
            comment, item_id)
    VALUES ('����� ��, �� � �������������� ����������', 2);

INSERT INTO public.comments(
            comment, item_id)
    VALUES ('����� ��, �� ��� �������', 3);

INSERT INTO public.attachs(
            attach_file, item_id)
    VALUES ('file.txt', 1);

INSERT INTO public.attachs(
            attach_file, item_id)
    VALUES ('userFile.txt', 2);

INSERT INTO public.attachs(
            attach_file, item_id)
    VALUES ('anotherUserFile1.txt', 3);

INSERT INTO public.attachs(
            attach_file, item_id)
    VALUES ('anotherUserFile2.txt', 3);

