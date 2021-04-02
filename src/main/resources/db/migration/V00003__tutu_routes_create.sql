create table if not exists tutu_routes(
    id                     integer auto_increment primary key,
    departure_station_id   integer not null,
    arrival_station_id     integer not null,
    foreign key (departure_station_id) references tutu_stations (station_id),
    foreign key (arrival_station_id) references tutu_stations (station_id)
);