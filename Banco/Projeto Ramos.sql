/**
*Sitema Os - Professor Ramos
* @Authors Allan Gomes, Bruno Henrique, Diego Maia, Igor Oliveira
*/

create database ramos;
use ramos;

create table clientes(
id int primary key auto_increment,
nome varchar(50) not null,
cpf varchar(16),
endereco varchar (30) ,
numero varchar (6) ,
complemento varchar(250),
bairro varchar(50) ,
cidade varchar(50),
cep varchar(10),
uf char(2) ,
telefone varchar(30) not null,
email varchar (250),
obs varchar (250) 
); 

create table os(
codigo int primary key auto_increment,
processador varchar (250),
memoria varchar (250),
hd varchar (250), 
placa varchar (250),
refri varchar(250),
video varchar (250),
fan varchar(250),
gabinete varchar (250),
fonte varchar(250),
situacao varchar(250),
obs nvarchar (1000),
nomee varchar(250),
telefone varchar (250),
datacad timestamp default current_timestamp,
foto1 longblob, 
foto2 longblob,
foto3 longblob,
foto4 longblob,
id int not null,
foreign key(id) references clientes(id)
); 

select * from os;
