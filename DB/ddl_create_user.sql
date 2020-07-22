create user mutation identified by mutation; -- we create a user "mutation" with password is same name in the database.

grant create session to mutation;
	
grant create table to mutation;

grant create view, create procedure, create sequence to mutation;

--Source: https://blogs.oracle.com/sql/how-to-create-users-grant-them-privileges-and-remove-them-in-oracle-database
