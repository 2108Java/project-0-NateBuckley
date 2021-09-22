
drop table joint;
drop table pending_transfers;
drop table customers;

create table joint(
joint_id serial primary key,
joint_balance numeric not null,
fk_customer1 varchar(20) references customers(customer_username) not null,
fk_customer2 varchar(20) references customers(customer_username) not null
);

create table employees(
employee_username varchar(20) primary key,
employee_password varchar(40) not null,
isAdmin boolean not null
);

create table customers(
customer_username varchar(20) primary key,
customer_password varchar(40) not null,
checking_balance numeric(19,2),
savings_balance numeric(19,2),
isApproved boolean not null
);

create table pending_transfers(
transfer_id serial primary key,
fk_customer_giver varchar(20) references customers(customer_username) not null,
fk_customer_receiver varchar(20) references customers(customer_username) not null,
transfer_balance numeric(19,2) not null
);

insert into employees values ('god', 'godwashere', true);
insert into customers (customer_username, customer_password, isApproved) values ('god', 'godwashere', true);
insert into customers (customer_username, customer_password, isApproved) values ('testing4', 'testing12', false);
insert into employees values ('bankbot', 'bankbot', false);

insert into pending_transfers (transfer_id, fk_customer_giver, fk_customer_receiver, transfer_balance) values (default, 'testing', 'god', 50);

delete from pending_transfers where fk_customer_receiver = 'god';

delete from customers where customer_username = 'god';

delete from joint where fk_customer1 = 'god';

insert into joint (joint_id, joint_balance, fk_customer1, fk_customer2) values (default, 50, 'god', 'testing');

--testing
select customers.customer_username , customers.customer_password , customers.checking_balance, customers.savings_balance, customers.isapproved, joint.joint_balance from customers left join joint on joint.fk_customer1 = 'testing5' where customers.customer_username = 'testing5' union	select customers.customer_username , customers.customer_password , customers.checking_balance, customers.savings_balance, customers.isapproved, joint.joint_balance from customers left join joint on joint.fk_customer2 = 'testing5' where customers.customer_username = 'testing5';

select customers.customer_username , customers.customer_password , customers.checking_balance, customers.savings_balance, customers.isapproved, joint.joint_balance from customers left join joint on joint.fk_customer1 = 'testing5' or joint.fk_customer2 = 'testing5' where customers.customer_username = 'testing5';

UPDATE joint SET joint_balance = joint_balance + 10 WHERE joint_id = (select joint_id from joint where fk_customer1 = 'testing5' OR fk_customer2 = 'testing5');

select * from customers;

select * from joint;

select * from pending_transfers;

select fk_customer_receiver from pending_transfers where fk_customer_receiver = 'god' order by transfer_id;

delete from pending_transfers where transfer_id = (select transfer_id from pending_transfers where fk_customer_receiver = 'god' order by transfer_id desc limit 1);

update customers set savings_balance = 50 where customer_username = 'testing5';

update customers set checking_balance = checking_balance + 10 where customer_username = 'testing5';
