--Вывести список всех машин и все привязанные к ним детали.

select c.name, cb.name as car_body, ct.name as transmission, ce.name as engine  
from cars c
inner join car_bodies cb on c.body_id = cb.id
inner join car_engines ce on c.engine_id = ce.id
inner join car_transmissions ct on c.transmission_id = ct.id
