alter table if exists auth_user_schema.authority
    add constraint fk_authority_to_auth_user
    foreign key (user_id)
    references auth_user_schema.auth_user
