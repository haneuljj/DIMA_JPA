-- JPQL TEST

drop table test_user;
drop sequence testuser_seq;

create table test_user
(
    userid number primary key
    , username varchar2(250) not null
    , pwd varchar2(200) not null
    , email varchar2(200) not null
);

create sequence testuser_seq;

select * from test_user;
