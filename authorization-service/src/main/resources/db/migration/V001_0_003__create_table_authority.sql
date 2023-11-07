create table auth_user_schema.authority (
    id uuid not null,
    authority varchar(255),
    user_id uuid,
    primary key (id)
)