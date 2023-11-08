create table auth_user_schema.auth_user (
    id uuid not null,
    enabled boolean not null,
    first_name varchar(255),
    last_name varchar(255),
    login varchar(255),
    password varchar(255),
    phone_number varchar(255),
    telegram_id varchar(255),
    primary key (id)
)
