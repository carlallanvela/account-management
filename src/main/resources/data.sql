insert into account values(10001, 'Account 1', 'SAVINGS', sysdate(), 'SGD', '1000.5');
insert into account values(10002, 'Account 2', 'CURRENT', sysdate(), 'AUD', '100.5');
insert into account values(10003, 'Account 3', 'SAVINGS', sysdate(), 'SGD', '2000.5');


-- Transactions for 10001
insert into transaction values(11001, 10.5, 10, 'DEBIT', 'Transaction 1 for Account 1', sysdate(), 10001);
insert into transaction values(11002, 9.5, 10, 'CREDIT', 'Transaction 2 for Account 2', sysdate(), 10001);
insert into transaction values(11003, 4, 1, 'DEBIT', 'Transaction 3 for Account 3', sysdate(), 10001);

-- Transactions for 10002
insert into transaction values(11004, 11, 10, 'DEBIT', 'Transaction 1 for Account 2', sysdate(), 10002);
insert into transaction values(11005, 9.2, 10, 'CREDIT', 'Transaction 2 for Account 2', sysdate(), 10002);
insert into transaction values(11006, 4.5, 111, 'DEBIT', 'Transaction 3 for Account 3', sysdate(), 10002);