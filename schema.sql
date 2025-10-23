DROP DATABASE IF EXISTS reclamoempresaelectrica;
CREATE DATABASE reclamoempresaelectrica; 

USE reclamoempresaelectrica;

-- DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(
	nro_ide INTEGER UNIQUE AUTO_INCREMENT,
	direccion VARCHAR (35),
	tel INTEGER NOT NULL,
	CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide)
);


-- DROP TABLE IF EXISTS empresa;
CREATE TABLE empresa(
	nro_ide INTEGER UNIQUE NOT NULL,
	cuit INTEGER UNIQUE,
	capacidadKW INTEGER NOT NULL,
	CONSTRAINT potencia_instalada CHECK (capacidadKW >= 0 AND capacidadKW < 50000),
	CONSTRAINT fk_nro_ide FOREIGN KEY (nro_ide) REFERENCES usuario (nro_ide) ON DELETE CASCADE,
	CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide)
); 

-- DROP TABLE IF EXISTS persona;
CREATE TABLE persona(
	nro_ide INTEGER UNIQUE NOT NULL,
	dni INTEGER NOT NULL,
	CONSTRAINT persona CHECK (dni > 0 AND dni < 1000000000),
	CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide),
	CONSTRAINT fk_nro_ide_persona FOREIGN KEY (nro_ide) REFERENCES usuario (nro_ide) ON DELETE CASCADE
); 

-- DROP TABLE IF EXISTS empleado;
CREATE TABLE empleado(
	nro_ide INTEGER NOT NULL,
    dni INTEGER NOT NULL,
	nombre VARCHAR (45),
	apellido VARCHAR (45),
	fecha_nac DATE,
	sueldo INTEGER NOT NULL,
	CONSTRAINT pk_nro_ide PRIMARY KEY (nro_ide),
	CONSTRAINT fk_nro_ide_empleado FOREIGN KEY (nro_ide) REFERENCES persona (nro_ide) ON DELETE CASCADE
); 

-- DROP TABLE IF EXISTS motivo;
CREATE TABLE motivo(
	codigo INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
	descripcion VARCHAR(45),
	CONSTRAINT pk_codigo PRIMARY KEY (codigo)
); 

-- DROP TABLE IF EXISTS reclamo;
CREATE TABLE reclamo(
	nro INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
	fecha DATE,
	hora TIME,
	fecha_resol DATE,
	nro_usuario INTEGER,
	cod_motivo INTEGER,
	CONSTRAINT pk_nro PRIMARY KEY (nro),
	CONSTRAINT fk_usuario FOREIGN KEY (nro_usuario) REFERENCES usuario (nro_ide) ON DELETE CASCADE,
    CONSTRAINT fecha_valida CHECK (fecha <= fecha_resol),
	CONSTRAINT fk_motivo FOREIGN KEY (cod_motivo) REFERENCES motivo (codigo) ON UPDATE CASCADE
); 

-- DROP TABLE IF EXISTS material;
CREATE TABLE material(
	codigo_mat INTEGER UNIQUE AUTO_INCREMENT NOT NULL,
	descrip_mat VARCHAR(35),
	CONSTRAINT pk_codigo PRIMARY KEY (codigo_mat)
); 

-- DROP TABLE IF EXISTS soluciona;
CREATE TABLE soluciona(
	nro_ide INTEGER,
	nro_reclamo INTEGER,
	CONSTRAINT pk_soluciona PRIMARY KEY (nro_ide, nro_reclamo),
	CONSTRAINT fk_nro_ide_soluciona FOREIGN KEY (nro_ide) REFERENCES empleado (nro_ide) ON DELETE CASCADE,
	CONSTRAINT fk_reclamo FOREIGN KEY (nro_reclamo) REFERENCES reclamo (nro) ON DELETE CASCADE
);

-- DROP TABLE IF EXISTS usa;
CREATE TABLE usa(
	cod_material INTEGER,
	nro_reclamo INTEGER,
	CONSTRAINT pk_usa PRIMARY KEY (cod_material, nro_reclamo),
	CONSTRAINT fk_cod_material FOREIGN KEY (cod_material) REFERENCES material (codigo_mat) ON DELETE CASCADE,
	CONSTRAINT fk_reclamo_usa FOREIGN KEY (nro_reclamo) REFERENCES reclamo (nro) ON DELETE CASCADE
);

-- DROP TABLE IF EXISTS rellamado;
CREATE TABLE rellamado(
	cod_reclamo INTEGER,
	nro_llamado INTEGER,
	fecha DATE,
	hora TIME,
	CONSTRAINT pk_usa PRIMARY KEY (cod_reclamo, nro_llamado),
	CONSTRAINT fk_nro_reclamo FOREIGN KEY (cod_reclamo) REFERENCES reclamo (nro) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS auditoria (
	id INTEGER AUTO_INCREMENT,
    
    nro INTEGER NOT NULL,
	fecha DATE,
	hora TIME,
	fecha_resol DATE,
	nro_usuario INTEGER,
	cod_motivo INTEGER,
    
    fecha_eliminacion DATE,
    db_usuario NVARCHAR(128) NOT NULL,
    
    CONSTRAINT pk_auditoria PRIMARY KEY (id)
);

delimiter $$
CREATE TRIGGER trigger_elim_reclamo
	AFTER DELETE ON reclamo
	FOR EACH ROW
	BEGIN
		INSERT INTO auditoria (nro, fecha, hora, fecha_resol, nro_usuario, cod_motivo, fecha_eliminacion, db_usuario) VALUES
        (OLD.nro, OLD.fecha, OLD.hora, OLD.fecha_resol, OLD.nro_usuario, OLD.cod_motivo, NOW(), CURRENT_USER());
    END;
$$
delimiter ;

delimiter $$
CREATE TRIGGER trigger_elim_usuario
	BEFORE DELETE ON usuario
    FOR EACH ROW
    BEGIN 
		DELETE FROM reclamo WHERE nro_usuario = OLD.nro_ide;
    END;
$$
delimiter ;
	hora TIME,
	CONSTRAINT pk_usa PRIMARY KEY (cod_reclamo ,nro_llamado),
	CONSTRAINT fk_nro_reclamo FOREIGN KEY (cod_reclamo) REFERENCES reclamo (nro) ON DELETE CASCADE
);

INSERT INTO usuario (nro_ide, direccion, tel) 
VALUES
	(1, 'Sabatini 1520', 4662919),
	(2, 'Fray cardarelli 280', 4668852),
	(3, 'General Paz 800', 4667910),
	(4, 'Constiticion 1566', 358973669),

	(5, 'Sabatini 1520', 4662919),
	(6, 'Fray cardarelli 280', 4668852),
	(7, 'General Paz 800', 4667910),
	(8, 'Constiticion 1566', 358973669);

INSERT INTO empresa (nro_ide, cuit, capacidadKW) 
VALUES
	(1, 55789666, 49000),
	(2, 55777666, 500),
	(3, 48788966, 400),
	(4, 66789669, 8000);

INSERT INTO persona (nro_ide, dni) 
VALUES
	(5, 44973669),
	(6, 44785555),
	(7, 44789252),
	(8, 44777222);

INSERT INTO empleado (nro_ide, dni, nombre, apellido, fecha_nac, sueldo)
VALUES
	(5, 44973661,'Pedro','Pereyra', '2024-2-21', 500000),
	(6, 44785552, 'Daniel', 'Aguero', '2025-07-24', 10000),
	(7, 44789253, 'Ricardo', 'Cardozo', '2000-09-21', 600000),
	(8, 44777224, 'Nicolas', 'Fernandez', '1969-05-12', 10);

INSERT INTO motivo(codigo, descripcion) VALUES
	(1, 'Tuvimos un apagon en la cuadra'),
	(2, 'Se me corto la luz en casa'),
	(3, 'Un generador no esta andando'),
	(4, 'No me anda el televisor y pague la luz');

INSERT INTO reclamo (nro, fecha, hora, fecha_resol, nro_usuario, cod_motivo) VALUES
	(1, '2025-5-12', '5:5:5', '2061-5-1', 5, 1),
	(2, '2025-5-12', '5:5:5', '2061-5-1', 6, 2),
	(3,'2025-5-12', '5:5:5', '2061-5-1', 7, 3),
    (4, '2025-4-4', '5:5:4', '2026-5-7', 6, 1),
	(5, '2025-5-12', '5:5:5', '2025-5-12', 8, 4);

INSERT INTO material (codigo_mat, descrip_mat) VALUES
	(1, 'Cables'),
	(2, 'Cutter'),
	(3, 'Presintos'),
	(4, 'Cobre');
    
INSERT INTO soluciona (nro_ide, nro_reclamo) VALUES
	(5, 1),
    (6, 2),
    (7, 3),
    (8, 4);
    
INSERT INTO usa (cod_material, nro_reclamo) VALUES
		(1, 2),
        (2, 1),
        (3, 4),
        (4, 3); 
   
 INSERT INTO rellamado (cod_reclamo, nro_llamado, fecha, hora) VALUES
	(1, 1, '2025-5-7', '5:4:7'),
    (2, 2, '2003-7-21', '7:8:9'),
    (3, 3, '2004-8-30', '9:6:2'),
    (4, 4, '2005-9-25', '7:8:2')
