INSERT INTO schedule_schema."order"
(id, client_id, end_date_time, performer_id, start_date_time, service_id)
VALUES(cast('00000000-0000-0000-0000-600000000001' as uuid), cast('00000000-0000-0000-0000-300000000003' as uuid), '2023-11-16 11:00:00.000', '00000000-0000-0000-0000-200000000001'::uuid, '2023-11-16 10:00:00.000', '00000000-0000-0000-0000-400000000002'::uuid);

INSERT INTO schedule_schema."order"
(id, client_id, end_date_time, performer_id, start_date_time, service_id)
VALUES(cast('00000000-0000-0000-0000-600000000002' as uuid), cast('00000000-0000-0000-0000-300000000003' as uuid), '2023-11-16 15:30:00.000', '00000000-0000-0000-0000-200000000001'::uuid, '2023-11-16 15:00:00.000', '00000000-0000-0000-0000-400000000002'::uuid);
