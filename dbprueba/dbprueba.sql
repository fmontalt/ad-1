create table categoria (
  id serial primary key,
  nombre varchar(50) not null unique
);
create table articulo (
  id serial primary key,
  nombre varchar(50) not null unique,
  precio decimal(10,2),
  categoria bigint 
);

insert into categoria (nombre) values ('categoría 1');
insert into categoria (nombre) values ('categoría 2');
insert into categoria (nombre) values ('categoría 3');

insert into articulo (nombre, precio, categoria) values ('artículo 1', 1, 1);
insert into articulo (nombre, precio, categoria) values ('artículo 2', 2, 2);
insert into articulo (nombre, precio, categoria) values ('artículo 3', 3, 1);
insert into articulo (nombre, precio, categoria) values ('artículo 4', 4, 4);
insert into articulo (nombre) values ('artículo 5');




