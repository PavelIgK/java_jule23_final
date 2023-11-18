INSERT INTO schedule_schema.schedule
(id, start_date_time, end_date_time, performer_id)
VALUES(cast('00000000-0000-0000-0000-500000000001' as uuid), '2023-11-16 09:00:00.000', '2023-11-16 13:00:00.000', cast('00000000-0000-0000-0000-200000000001' as uuid));

INSERT INTO schedule_schema.schedule
(id, start_date_time, end_date_time, performer_id)
VALUES(cast('00000000-0000-0000-0000-500000000002' as uuid), '2023-11-16 14:00:00.000', '2023-11-16 18:00:00.000', cast('00000000-0000-0000-0000-200000000001' as uuid));