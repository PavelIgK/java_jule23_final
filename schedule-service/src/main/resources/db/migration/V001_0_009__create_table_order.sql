create table schedule_schema.order (
    id uuid not null,
    client_id uuid,
    end_date_time timestamp(6),
    performer_id uuid,
    start_date_time timestamp(6),
    service_id uuid,
	CONSTRAINT pk_order PRIMARY KEY (id),
	CONSTRAINT fk_order_service FOREIGN KEY (service_id) REFERENCES schedule_schema.service(id),
	CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES schedule_schema.client(id),
	CONSTRAINT fk_order_performer FOREIGN KEY (performer_id) REFERENCES schedule_schema.performer(id)
);




