create table ${settings.database.duff.schema}.beer_playlist
(
    beer_id     number(15,0) not null primary key,
    playlist_id varchar(2000),

    constraint fk_beer_playlist foreign key (beer_id) references ${settings.database.duff.schema}.beer(beer_id)
);

grant select, insert, update, delete on ${settings.database.duff.schema}.beer_playlist to ${settings.database.duff.user};