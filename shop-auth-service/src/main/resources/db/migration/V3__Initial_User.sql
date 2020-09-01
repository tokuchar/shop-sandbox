insert
    into
        user
        (is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, username)
    values
        (1, 1, 1, 1, '$2a$04$Q69GYZC4LX5cSUq6iioJcen5qkLjNF6751P5D78RwnPeoHCVN9Bu6', 'admin');
insert
    into
        users_authorities
        (user_id, authority_name)
    values
        (1, 'ADMIN');
