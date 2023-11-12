create table schedule_schema.schedule_template (
    id uuid not null, end_time time(6),
    schedule_name varchar(255),
    start_time time(6),
    performer_id uuid,
	CONSTRAINT pk_schedule_template PRIMARY KEY (id),
	CONSTRAINT fk_schedule_template_performer FOREIGN KEY (performer_id) REFERENCES schedule_schema.performer(id)

);

