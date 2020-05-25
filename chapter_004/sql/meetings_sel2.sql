-- Нужно получить все совещания, где не было ни одной заявки на посещения

select * 
from m_meetings
where id not in (
	select meeting_id 
	from m_meet_users
	)
