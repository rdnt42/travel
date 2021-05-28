create table if not exists tutu_routes
(
    id                   bigserial
        constraint tutu_routes_pk
            primary key,
    departure_station_id int not null references tutu_stations,
    arrival_station_id   int not null references tutu_stations
);