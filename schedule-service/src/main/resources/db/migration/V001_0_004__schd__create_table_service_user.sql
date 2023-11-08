create table schedule_schema.service_user (
    id uuid not null,
    login varchar(255),
    user_id uuid,
    service_id uuid,
    primary key (id)
);

alter table if exists schedule_schema.service_user
    add constraint fk_service_user_service
    foreign key (service_id)
    references schedule_schema.service;


