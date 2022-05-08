-- ensure that tables are deleted before creating a new ones.
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS guild;

-- create ds_guild table
CREATE TABLE guild (
    id varchar(30) not null,
    timestamp bigint default 0,
    primary key (id),
    unique (id)
);

-- create ds_wins_count table
CREATE TABLE member (
    user_id varchar(30) not null,
    guild_id varchar(30) not null,
    wins_count int default 0,
    active boolean,
    foreign key (guild_id) references guild(id),
    primary key (user_id, guild_id),
    unique (user_id, guild_id)
);