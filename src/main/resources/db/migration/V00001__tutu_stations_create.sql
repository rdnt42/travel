create table if not exists tutu_stations
(
    station_id   int
        constraint tutu_stations_pk
            primary key,
    station_name text not null
);