create or replace function FLIP_YES_NO(col_val varchar(3))
returns varchar(3) as $$
begin
IF col_val = 'Yes' THEN
	return 'No';
ELSE
	return 'Yes';
END IF;
end;
$$ language plpgsql;