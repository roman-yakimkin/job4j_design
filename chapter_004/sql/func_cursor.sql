create or replace function func_cursor() 
returns varchar as $$
declare 
	cur1 cursor for select p.name from products p;
	rec record;
	product_names varchar default '';
begin
	OPEN cur1;
	loop
		fetch cur1 into rec; 
		exit when not found;
		product_names := product_names || ', ' || rec.name;
	end loop;
	close cur1;
	return product_names;
end; $$

LANGUAGE plpgsql;
	