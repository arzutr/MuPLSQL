Before running application database objects should be deployed to Oracle database.

Data definition scripts are kept in this folder.


The source code may support every custome database schema name. 

For deployment Schema name can be added to database objects.

Such as: 

BEFORE

create table tmutation ....

AFTER

create table myschema.tmutation .... 


Table information is added as pdf document. Would please read this document before run scripts. 


In the ddl_create_user.sql file sample schema and required grants are declared. 


Hint for clean cache: alter system flush shared_pool  
