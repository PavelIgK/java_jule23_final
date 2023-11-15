insert into schedule_schema.auth_user(id, enabled, login, password, telegram_id)
    values(cast('00000000-0000-0000-0000-000000000001' as uuid), true, 'ivanov', '123', 'ivanovTG');

insert into schedule_schema.auth_user(id, enabled, login, password, telegram_id)
    values(cast('00000000-0000-0000-0000-000000000002' as uuid), true, 'petrov', '123', 'petrovTG');

insert into schedule_schema.auth_user(id, enabled, login, password, telegram_id)
    values(cast('00000000-0000-0000-0000-000000000003' as uuid), true, 'sidorov', '123', 'sidorovTG');

insert into schedule_schema.auth_user(id, enabled, login, password, telegram_id)
    values(cast('00000000-0000-0000-0000-000000000004' as uuid), true, 'galkin', '123', 'galkinTG');

insert into schedule_schema.auth_user(id, enabled, login, password, telegram_id)
    values(cast('00000000-0000-0000-0000-000000000005' as uuid), true, 'malkin', '123', 'malkinTG');

insert into schedule_schema.auth_user(id, enabled, login, password, telegram_id)
    values(cast('00000000-0000-0000-0000-000000000005' as uuid), true, 'zalkind', '123', 'zalkindTG');

commit;

