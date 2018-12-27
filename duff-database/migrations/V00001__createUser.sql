declare userexist integer;

begin
    select count(*) into userexist from dba_users where username='${settings.database.duff.user}';
    if (userexist = 0) then
        execute immediate 'CREATE USER ${settings.database.duff.user} IDENTIFIED BY ${settings.database.duff.passwd}';
        execute immediate 'GRANT CREATE SESSION TO ${settings.database.duff.user}';
    end if;
end;