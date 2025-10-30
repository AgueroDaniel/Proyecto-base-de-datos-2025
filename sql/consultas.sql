-- 6 c) Reclamos asignados a más de un empleado de mantenimiento

SELECT 
    s.nro_ide AS nro_empleado,
    e.nombre,
    e.apellido,
    COUNT(s.nro_reclamo) AS cantidad_reclamos
FROM soluciona s
JOIN empleado e ON s.nro_ide = e.nro_ide
GROUP BY s.nro_ide, e.nombre, e.apellido -- Agrupa todos los reclamos por empleado.
HAVING COUNT(s.nro_reclamo) > 1;


-- 6 b) Usuarios con más de un reclamo
SELECT 
    u.nro_ide AS nro_usuario,
    u.direccion,
    u.tel,
    COUNT(r.nro) AS cantidad_reclamos
FROM usuario u
JOIN reclamo r ON u.nro_ide = r.nro_usuario
GROUP BY u.nro_ide, u.direccion, u.tel
HAVING COUNT(r.nro) > 1;  

/*GROUP BY agrupa reclamos por usuario.
HAVING COUNT(r.nro) > 1 filtra solo los que tienen más de un reclamo.*/

-- 6 a) Devolver por cada reclamo, el detalle de materiales utilizados para solucionarlo, si un reclamo no uso materiales, listarlo también
SELECT 
    m.descrip_mat AS material,
    u.cantidad,
    r.nro AS nro_reclamo,
    r.fecha,
    r.hora
FROM reclamo r
LEFT JOIN usa u ON r.nro = u.nro_reclamo
LEFT JOIN material m ON u.cod_material = m.codigo_mat
ORDER BY r.nro; 

/*LEFT JOIN asegura que aunque el reclamo no tenga materiales, igual aparezca.
Si no se usó material, material y cantidad serán NULL.*/
