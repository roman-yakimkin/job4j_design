--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  

select t.*
from product_types t
inner join products p
on t.id = p.type_id
group by t.id
having count(p.type_id) < 10