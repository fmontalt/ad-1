create table categoria (
  id bigint auto_increment primary key,
  nombre varchar(50) not null unique
);

insert into categoria (nombre) values ('categoría 1');
insert into categoria (nombre) values ('categoría 2');
insert into categoria (nombre) values ('categoría 3');

create table articulo (
  id bigint auto_increment primary key,
  nombre varchar(50) not null unique,
  precio decimal(10,2) not null,
  categoria bigint
);

alter table articulo add foreign key (categoria) 
  references categoria (id);

insert into articulo (nombre, precio, categoria) values ('artículo 1', 1, 1);
insert into articulo (nombre, precio, categoria) values ('artículo 2', 2, 2);
insert into articulo (nombre, precio) values ('artículo 3', 3);

create table cliente (
  id bigint auto_increment primary key,
  nombre varchar(50) not null unique
);

insert into cliente (nombre) values ('cliente 1');
insert into cliente (nombre) values ('cliente 2');
insert into cliente (nombre) values ('cliente 3');

create table pedido (
  id bigint auto_increment primary key,
  cliente bigint not null,
  fecha datetime not null,
  importe decimal(10,2)
);

create table pedidolinea (
  id bigint auto_increment primary key,
  pedido bigint not null,
  articulo bigint not null,
  precio decimal(10,2),
  unidades decimal(10,2),
  importe decimal(10,2)
);

alter table pedido add foreign key (cliente) references cliente (id);
alter table pedidolinea add foreign key (pedido) references pedido (id) on delete cascade;
alter table pedidolinea add foreign key (articulo) references articulo (id);


