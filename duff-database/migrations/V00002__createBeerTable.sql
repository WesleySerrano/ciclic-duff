CREATE TABLE ${settings.database.duff.schema}.beer
(
    beer_id     number(15,0) not null primary key,
    style  varchar2(100) not null,
    max_temp number(5,3) not null,
    min_temp number(5,3) not null,
    excluded number(1,0) default 0
);

CREATE SEQUENCE ${settings.database.duff.schema}.beer_seq
increment by 1
maxvalue 999999999999999
minvalue 1 nocache;

grant select, insert, update, delete on ${settings.database.duff.schema}.beer to ${settings.database.duff.user};
grant select on ${settings.database.duff.schema}.beer_seq to ${settings.database.duff.user};