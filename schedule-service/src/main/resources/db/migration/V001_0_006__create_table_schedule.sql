create table schedule_schema.schedule (
    id uuid not null default gen_random_uuid(),
    end_date_time timestamp(6),
    start_date_time timestamp(6),
    performer_id uuid,
	CONSTRAINT pk_schedule PRIMARY KEY (id),
	CONSTRAINT fk_schedule_performer FOREIGN KEY (performer_id) REFERENCES schedule_schema.performer(id)
);
