--Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
-- company name for each person

select p.name, c.name
from person p
inner join company c
on p.company_id = c.id
where c.id <> 5
