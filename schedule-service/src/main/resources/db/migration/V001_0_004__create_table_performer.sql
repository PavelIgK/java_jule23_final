create table schedule_schema.performer (
    id uuid not null,
    first_name varchar(255),
    last_name varchar(255),
    phone_number varchar(255),
    user_id uuid,
	CONSTRAINT pk_performer PRIMARY KEY (id),
	CONSTRAINT fk_performer_auth_user FOREIGN KEY (user_id) REFERENCES schedule_schema.auth_user(id)
)