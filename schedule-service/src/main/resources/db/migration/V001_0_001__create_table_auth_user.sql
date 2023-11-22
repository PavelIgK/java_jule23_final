create table schedule_schema.auth_user (
    id uuid not null default gen_random_uuid(),
    enabled boolean not null,
    login varchar(255),
    password varchar(255),
    telegram_id varchar(255),
    primary key (id)
)
