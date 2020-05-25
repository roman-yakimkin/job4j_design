--Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников

select m.name, count(mu.user_id)
from m_meetings m
left join (
	select * 
	from m_meet_users 
	where state_id = 1
	) mu
on m.id = mu.meeting_id
group by m.id
