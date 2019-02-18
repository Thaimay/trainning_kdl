--過去データは訪問者をCCにする。通知先をTOにする。

update negotiation_notice_account set send_type = 'CC'
where id in
(
    select nna.id from negotiation_interview_account nia
    inner join negotiation_notice_account nna on nia.negotiation_id = nna.negotiation_id and nia.account_id = nna.account_id
);

update negotiation_notice_account set send_type = 'TO' where send_type is null;

--過去分の未読・既読データはクリアして、新規でデータを蓄積する。
truncate negotiation_open_account restart identity;
