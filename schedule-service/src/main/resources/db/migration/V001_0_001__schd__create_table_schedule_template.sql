create table schedule_schema.schedule_template (
    id uuid not null, end_time time(6),
    schedule_name varchar(255),
    start_time time(6),
    user_id uuid,
    login varchar(255),
    primary key (id)
)
