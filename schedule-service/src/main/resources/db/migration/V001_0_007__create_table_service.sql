create table schedule_schema.service (
    id uuid not null default gen_random_uuid(),
    description varchar(255),
    duration integer not null,
    name varchar(255),
    price float(53) not null,
	CONSTRAINT pk_service PRIMARY KEY (id)
)

