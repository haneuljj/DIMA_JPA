-- entity 정의

drop table stocks;
drop sequence stocks_seq;

create table stocks
(
    product_id number primary key
    , product_name varchar2(50)
    , price number
    , inventory_amount number
    , category varchar2(50)
);

create sequence stocks_seq;