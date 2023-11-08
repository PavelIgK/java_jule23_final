create table schedule_schema.order (
    id uuid not null,
    client_id varchar(255),
    client_login varchar(255),
    end_date_time timestamp(6),
    performer_id varchar(255),
    performer_login varchar(255),
    start_date_time timestamp(6),
    service_id uuid,
    primary key (id)
);

alter table if exists schedule_schema.order
    add constraint fk_order_service
    foreign key (service_id)
    references schedule_schema.service;



