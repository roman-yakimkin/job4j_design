﻿-- Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

select p.*
from products p
inner join product_types t
on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО')