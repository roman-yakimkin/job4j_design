-- Select the name of the company with the maximum number of persons + number of persons in this company

-- способ 1
select c.name, count(p.id) as staff
from company c 
inner join person p 
on c.id = p.company_id
group by c.id 
order by staff desc
limit 1

-- способ 2
select c.name, count(p.id) as staff
from company c inner join person p on c.id = p.company_id
group by c.id
having count(p.id) = (select max(v.cnt1) from (
	select count(p1.id) as cnt1 
	from company c1 
	inner join person p1 
	on c1.id = p1.company_id 
	group by c1.id 
	) v)

-- способ 3
select v1.name, v1.cnt1 as staff
from (
	select c1.name, count(p1.id) as cnt1 
	from company c1 
	inner join person p1 
	on c1.id = p1.company_id 
	group by c1.id 		
) v1
where v1.cnt1 = (select max(v2.cnt1)
		 from (
			select c1.name, count(p1.id) as cnt1 
			from company c1 
			inner join person p1 
			on c1.id = p1.company_id 
			group by c1.id 
		      ) v2)
