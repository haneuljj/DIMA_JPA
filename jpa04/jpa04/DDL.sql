-- jpa04에서 사용할 객체 생성
-- 취급하는 상품에 관련된 정보를 처리할 데이터베이스 테이블

drop table product;
drop sequence product_seq;

create table  product
(
    prod_id number primary key
    , prod_name varchar2(50)
    , season varchar2(100)
    , unit_price number
);

create sequence product_seq;