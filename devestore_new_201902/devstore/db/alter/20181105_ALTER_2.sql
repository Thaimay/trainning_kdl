alter table project alter column external_release_confirm type boolean
  using case
    when external_release_confirm = 'true' then TRUE
    when external_release_confirm = 'false' then FALSE
    else null
  end;


update project_classification set name = '再契'
where id =
(
select child_id
 from project_classificatoin_child_parent
 where
 parent_id =
 (
   select id from project_classification
where classification = 'LANDING' AND name = '再契約'
 )
);
