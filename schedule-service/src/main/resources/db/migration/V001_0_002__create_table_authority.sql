create table schedule_schema.authority (
    id uuid not null,
    authority varchar(255),
    user_id uuid,
    primary key (id)
);

alter table if exists schedule_schema.authority
    add constraint fk_authority_to_auth_user
    foreign key (user_id)
    references schedule_schema.auth_user;