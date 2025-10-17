CREATE DATABASE reclamoEmpresaElectrica

USE reclamoempresaelectrica;

 DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(
nro_ide INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
direccion VARCHAR (35),
tel INTEGER NOT NULL,
CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide)
); 


 DROP TABLE IF EXISTS empresa;
CREATE TABLE empresa(
nro_ide INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
cuit INTEGER NOT NULL,
/*preguntar si es unique*/
capacidadKW INTEGER NOT NULL,
CONSTRAINT potencia_instalada CHECK (capacidadKW >= 0 AND capacidadKW < 50000),
CONSTRAINT fk_nro_ide FOREIGN KEY (nro_ide) REFERENCES usuario (nro_ide),
CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide)
); 

 DROP TABLE IF EXISTS persona;
CREATE TABLE persona(
nro_ide INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
dni INTEGER NOT NULL,
CONSTRAINT persona CHECK (dni > 0 AND dni < 1000000000),
CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide),
CONSTRAINT fk_nro_ide_persona FOREIGN KEY (nro_ide) REFERENCES usuario (nro_ide)
); 


DROP TABLE IF EXISTS empleado;
CREATE TABLE empleado(
nro_ide INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
/*preguntarle al profe el primary key*/
nombre VARCHAR (45),
apellido VARCHAR (45),
fecha_nac DATE,
sueldo INTEGER NOT NULL,
edad INTEGER NOT NULL, 
CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide),
CONSTRAINT fk_nro_ide_empleado FOREIGN KEY (nro_ide) REFERENCES persona (nro_ide)
); 



 DROP TABLE IF EXISTS motivo;
CREATE TABLE motivo(
	codigo INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
	descripcion INTEGER,
	CONSTRAINT pk_codigo PRIMARY KEY (codigo)
); 

 DROP TABLE IF EXISTS reclamo;
CREATE TABLE reclamo(
	nro INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
	fecha DATE,
	hora TIME,
	fecha_resol DATE,
	nro_usuario INTEGER,
	cod_motivo INTEGER,
CONSTRAINT pk_nro PRIMARY KEY (nro),
CONSTRAINT fk_usuario FOREIGN KEY (nro_usuario) REFERENCES usuario ON DELETE CASCADE,
CONSTRAINT fk_motivo FOREIGN KEY (cod_motivo) REFERENCES motivo ON UPDATE CASCADE
); 

 DROP TABLE IF EXISTS material;
CREATE TABLE material(
	codigo_mat INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
	descrip_mat VARCHAR(35),
	CONSTRAINT pk_codigo PRIMARY KEY (codigo_mat)
); 

DROP TABLE IF EXISTS soluciona;
CREATE TABLE soluciona(
	nro_ide INTEGER,
	nro_reclamo INTEGER,
	CONSTRAINT pk_soluciona PRIMARY KEY (nro_ide ,nro_reclamo),
	CONSTRAINT fk_nro_ide_soluciona FOREIGN KEY (nro_ide) REFERENCES empleado ON DELETE CASCADE,
	CONSTRAINT fk_reclamo FOREIGN KEY (nro_reclamo) REFERENCES reclamo ON DELETE CASCADE
);

DROP TABLE IF EXISTS usa;
CREATE TABLE usa(
	cod_material INTEGER,
	nro_reclamo INTEGER,
	CONSTRAINT pk_usa PRIMARY KEY (cod_material ,nro_reclamo),
	CONSTRAINT fk_cod_material FOREIGN KEY (cod_material) REFERENCES material (codigo_mat) ON DELETE CASCADE,
	CONSTRAINT fk_reclamo_usa FOREIGN KEY (nro_reclamo) REFERENCES reclamo ON DELETE CASCADE
);

DROP TABLE IF EXISTS rellamado;
CREATE TABLE rellamado(
	cod_reclamo INTEGER,
	nro_llamado INTEGER,
	fecha DATE,
	hora TIME,
	CONSTRAINT pk_usa PRIMARY KEY (cod_reclamo ,nro_llamado),
	CONSTRAINT fk_nro_reclamo FOREIGN KEY (cod_reclamo) REFERENCES reclamo ON DELETE CASCADE
);





