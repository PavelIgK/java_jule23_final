create table schedule_schema.auth_user (
    id uuid not null,
    enabled boolean not null,
    login varchar(255),
    password varchar(255),
    telegram_id varchar(255),
    primary key (id)
)
