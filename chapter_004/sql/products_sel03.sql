--Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце

select * 
from products p
where extract(month from expired_date) = extract(month from now() + interval '1 month')
