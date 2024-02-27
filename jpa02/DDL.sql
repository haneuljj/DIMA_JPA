-- jpa02와 연동하는 테이블 생성

drop table myuser;
create table myuser
(
    email varchar2(50) primary key
    , username varchar2(50) not null
    , join_date date default sysdate
);

select * from myuser;