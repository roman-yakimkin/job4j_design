--Написать запрос, который выводит самый дорогой продукт.

-- 1 способ

select * 
from products p
order by price desc
limit 1

-- 2 способ

select *
from products p 
where p.price = (select max(price) from products)
