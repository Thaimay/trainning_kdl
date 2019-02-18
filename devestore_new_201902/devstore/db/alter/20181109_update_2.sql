update m_period set 
first_half_end_date = (concat(substring(start_date::text from 1 for 4),'-09-30'))::date
,second_half_start_date = (concat(substring(start_date::text from 1 for 4),'-10-01'))::date
where substring(start_date::text from 1 for 4) != substring(second_half_start_date::text from 1 for 4)
or substring(start_date::text from 1 for 4) != substring(first_half_end_date::text from 1 for 4)