declare userExists integer;

begin
    select count(*) into userExists from dba_users where username = '${settings.database.duff.user}';
    if(userExists = 0) then
        execute immediate 'CREATE USER ${seetings.database.duff.user} identified by ${settings.database.duff.passwd';
        execute immediate 'GRANTE SESSION CREATE TO ${settings.database.duff.user}';
    end if;
end;