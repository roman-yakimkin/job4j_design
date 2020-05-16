--Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач

select cb.name 
from car_bodies cb
left outer join cars c on cb.id = c.body_id
where c.body_id is null

union

select ce.name 
from car_engines ce
left outer join cars c on ce.id = c.engine_id
where c.engine_id is null

union

select ct.name 
from car_transmissions ct
left outer join cars c on ct.id = c.transmission_id
where c.transmission_id is null
