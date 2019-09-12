insert into account values(10001, 'Account 1', sysdate());
insert into account values(10002, 'Account 2', sysdate());
insert into account values(10003, 'Account 3', sysdate());
insert into transaction values(11001, 'First Transaction', 10001);
insert into transaction values(11002, 'Second Transaction', 10001);
insert into transaction values(11003, 'First Transaction', 10002);
insert into transaction values(11004, 'First Transaction', 10003);