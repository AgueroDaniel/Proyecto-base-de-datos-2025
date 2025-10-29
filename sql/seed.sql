
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
    
INSERT INTO usa (cod_material, nro_reclamo, cantidad) VALUES
		(1, 2, 5),
        (2, 1, 100),
        (3, 4, 800),
        (4, 3, 969); 
   
 INSERT INTO rellamado (cod_reclamo, nro_llamado, fecha, hora) VALUES
	(1, 1, '2025-5-7', '5:4:7'),
    (2, 2, '2003-7-21', '7:8:9'),
    (3, 3, '2004-8-30', '9:6:2'),
    (4, 4, '2005-9-25', '7:8:2');
