-- Написать запрос, который выводит количество всех продуктов определенного типа.

select t.*, count(p.type_id) as cnt
from product_types t
inner join products p
on t.id = p.type_id
group by t.id
order by t.name

 