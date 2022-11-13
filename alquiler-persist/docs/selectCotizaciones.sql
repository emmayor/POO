USE cotizador;
SELECT CT.idCotizacion,
V.tipoVehiculo,
CT.cantidadDias,
CT.precioCotizacion,
CT.Fecha_Creacion
FROM cotizacion AS CT
INNER JOIN vehiculo AS V ON V.idVehiculo = CT.idTipoVehiculo