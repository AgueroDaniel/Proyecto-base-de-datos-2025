-- 6 a) Devolver por cada reclamo, el detalle de materiales utilizados para solucionarlo, si un reclamo no uso materiales, listarlo también
SELECT 
    r.nro AS nro_reclamo,
    r.fecha,
    r.hora,
    m.descrip_mat AS material,
    u.cantidad
FROM reclamo r
LEFT JOIN usa u ON r.nro = u.nro_reclamo
LEFT JOIN material m ON u.cod_material = m.codigo_mat
ORDER BY r.nro;

/*LEFT JOIN asegura que aunque el reclamo no tenga materiales, igual aparezca.
Si no se usó material, material y cantidad serán NULL.*/


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


-- 6 c) Reclamos asignados a más de un empleado de mantenimiento

SELECT 
    r.nro AS nro_reclamo,
    COUNT(s.nro_ide) AS cantidad_empleados
FROM reclamo r
JOIN soluciona s ON r.nro = s.nro_reclamo
GROUP BY r.nro
HAVING COUNT(s.nro_ide) > 1;
