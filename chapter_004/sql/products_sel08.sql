--Вывести все продукты и их тип.

select p.name, t.name, p.price, p.expired_date
from products p
inner join product_types t
on p.type_id = t.id
