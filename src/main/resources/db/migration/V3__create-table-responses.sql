create table responses(

id bigint not null auto_increment,
solution varchar(1000)not null,
author int not null ,
topic int not null ,
date datetime not null,



primary key(id)
);