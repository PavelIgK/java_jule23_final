create table schedule_schema.schedule (
    id uuid not null,
    end_date_time timestamp(6),
    start_date_time timestamp(6),
    user_id uuid,
    login varchar(255),
    primary key (id)
)
