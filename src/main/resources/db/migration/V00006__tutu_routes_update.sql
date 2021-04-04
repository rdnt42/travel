update tutu_routes
set popularity = 10
where departure_station_id = 2000000
  and arrival_station_id in (2064150, 2064188, 2014370, 2004540, 2060340, 2100100, 2064040, 2060615, 2060500,
                             2000150, 2014130, 2100000, 2064000, 2000125, 2600000, 2064140);

update tutu_routes
set popularity = 10
where departure_station_id = 2000006
  and arrival_station_id in (8010100, 2100035, 5100136, 8100100, 8300157, 5457076, 5100132, 8060317);

