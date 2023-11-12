CREATE TABLE schedule_schema.service_performer (
	service_id uuid NOT NULL,
	performer_id uuid NOT NULL,
	CONSTRAINT service_performer_pkey PRIMARY KEY (service_id, performer_id),
	CONSTRAINT fk_service_performer_service FOREIGN KEY (service_id) REFERENCES schedule_schema.service(id),
	CONSTRAINT fk_service_performer_performer FOREIGN KEY (performer_id) REFERENCES schedule_schema.performer(id)
);