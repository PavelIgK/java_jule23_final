create table schedule_schema.service (
    id uuid not null,
    description varchar(255),
    duration integer not null,
    name varchar(255),
    price float(53) not null,
    primary key (id)
)

