insert into car (barcode,license_plate_number, passenger_capacity, brand, model, mileage, transmission_type, daily_price, type, status) values
                      (1,'ABC123', 4, 'Toyota', 'Corolla', 10000, 'AUTOMATIC', 50.00, 'STANDARD', 'AVAILABLE')


insert into member values
                         (1,"istanbul",123,"member1@gmail.com","member1","5121211"),
                         (2,"ankara",124,"member2@gmail.com","member2","5121212"),
                         (3,"izmir",125,"member3@gmail.com","member3","5121213");
insert into location values
                           (1,"istanbul",1,"İstanbul Airport"),
                           (2,"istanbul",2,"İstanbul Sabiha Gökçen Airport"),
                           (3,"istanbul",3,"İstanbul Kadıköy"),
                           (4,"izmir",4,"İzmir City Center");
insert into equipment values
                            (1,"Snow Tyres",10),
                            (2,"Child Seat",10),
                            (3,"Baby Seat",10),
                            (4,"Roof Box",10),
                            (5,"WIFI",10),
                            (6,"GPS", 10);
insert into service values
                          (1, "Additional Driver", 20),
                          (2, "Towing Service", 20),
                          (3, "Roadside assistance", 20);
insert into car (brand, car_type, daily_price, plate, mileage, model, number, capacity, status, transmission) values
                                                                                                                    ("audi","SUV",75,"licensed",0,"a4",1,5,"Available","Automatic"),
                                                                                                                    ("mercedes","SUV",70,"licensed",0,"a200",2,5,"Available","Automatic"),
                                                                                                                    ("bmw","SUV",75,"licensed",0,"320i",3,5,"Available","Automatic");