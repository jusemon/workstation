-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2015 a las 06:41:30
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `dbworkstationsoftware`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarArticulo`(
        in idArticu		int,
	in idCategoriaArticu	int,
	in descripcionArticu	varchar(50),
	in cantidadDisponib	int,
	in precioComp		int,
        in precioVen            int
)
BEGIN
	UPDATE tblarticulo SET 
            `idCategoriaArticulo` = `idCategoriaArticu`, 
            `descripcionArticulo`=`descripcionArticu`,
            `cantidadDisponible`=`cantidadDisponib`,
            `precioCompra`=`precioComp`,
            `precioVenta`=`precioVen`
        WHERE `idArticulo`=`idArticu`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarCategoriaArticulo`(
        in idCategoriaArticu        int,
	in nombreCategoriaArticu    varchar(50)
)
BEGIN
	UPDATE tblCategoriaArticulo SET 
            `nombreCategoriaArticulo`=`nombreCategoriaArticu`
        WHERE `idCategoriaArticulo`=`idCategoriaArticu`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarCategoriaCurso`(
        in idCategoria int,
        in nombreCategoria varchar(30)
)
BEGIN
	UPDATE tblcategoriacurso SET 
            `nombreCategoriaCurso`=`nombreCategoria`
	WHERE `idCategoriaCurso`=`idCategoria`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarCredito`(
    in idCredi		int,
    in saldoActu	int,
    in estadoCredi	int
)
BEGIN
	UPDATE tblcredito SET
             `saldoActual`=`saldoActu`,`estadoCredito`=`estadoCredi`
        WHERE `idCredito`=`idCredi`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarCurso`(
        in idCur            int,
	in nombreCur        varchar(50),
	in cantidadClas     int,
	in horasPorCla      int,
	in estadoCur        int,
        in descripcionCur   varchar(100),
        in precioCur        int,
        in idCategoriaCur   int
)
BEGIN
	UPDATE tblcurso SET 
            `nombreCurso`=`nombreCur`,
            `cantidadClases`=`cantidadClas`,
            `horasPorClase`=`horasPorCla`,
            `estadoCurso` = `estadoCur`,
            `descripcionCurso`=`descripcionCur`,
            `precioCurso`=`precioCur`,
            `idCategoriaCurso` = `idCategoriaCur`
        WHERE `idCurso`=`idCur`;       
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarEmpresa`(
        in nitEmpre         int,
	in nombreEmpre      varchar(50),
	in direccionEmpre   varchar(50),
	in nombreContac     varchar(50),
	in telefonoContac   varchar(50),
	in emailContac      varchar(50)
)
BEGIN
	UPDATE tblEmpresa SET
            `nombreEmpresa`=`nombreEmpre`,
            `direccionEmpresa`=`direccionEmpre`,
            `nombreContacto`=`nombreContac`,
            `telefonoContacto`=`telefonoContac`,
            `emailContacto`=`emailContac`
	WHERE `nitEmpresa`=`nitEmpre`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarEstadoCredito`(
    in idCredi          int,
    in idCategoriaCredi int,
    in estadoCredi      int
)
BEGIN
	UPDATE tblCredito SET 
            estadoCredito = estadoCredi 
        WHERE idCredito = idCredi and idCategoriaCredito = idCategoriaCredi;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarEstadoCurso`(
    in idCur        int,
    in estadoCur    int
)
BEGIN
	UPDATE tblcurso SET 
            `estadoCurso`=`estadoCur` 
        WHERE `idCurso`=`idCur`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarEstudiante`(
    in documentoUsuar   varchar(20),
    in fechaNacimien    DATE,
    in nombreUsuar      varchar(30),
    in apellidoUsuar    varchar(30),
    in emailUsuar       varchar(50),
    in passwo           varchar(45),
    in estadoUsuar      int,
    in documentoAcudien varchar(20),
    in direccionUsuar   varchar(50),
    in telefonoFi       varchar(11),
    in telefonoMov      varchar(15),
    in generoUsuar      bit
)
BEGIN
    UPDATE tblusuario SET
        `fechaNacimiento`=`fechaNacimien`,
        `nombreUsuario`=`nombreUsuar`,
        `apellidoUsuario`=`apellidoUsuar`,
        `emailUsuario`=`emailUsuar`,
        password=passwo,
        `estadoUsuario`=`estadoUsuar`,
        `documentoAcudiente`=`documentoAcudien`
    WHERE `documentoUsuario`=`documentoUsuar`;

    UPDATE tbldetalleusuario SET 
        `direccionUsuario` = `direccionUsuar`,
        `telefonoFijo`=`telefonoFi`,
        `telefonoMovil`=`telefonoMov`,
        `generoUsuario`=`generoUsuar`
    WHERE `idDetalleUsuario` = (SELECT `idDetalleUsuario` FROM tblusuario WHERE `documentoUsuario`=`documentoUsuar`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarAbonoByCredito`(IN `idCredi`     int)
select (a.idAbono, a.idCredito, a.valorAbono, a.fechaPago) from tblAbono a inner join tblCredito c on(a.idCredito=c.idCredito) 
where a.idCredito like concat('%',idCredi,'%') 
order by (a.idCredito,a.FechaPago)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarArticuloPorID`(id int)
BEGIN
    SELECT 
        `idArticulo`,
        `idCategoriaArticulo`, 
        `descripcionArticulo`, 
        `cantidadDisponible`, 
        `precioCompra`, 
        `precioVenta` 
    FROM `tblarticulo` 
    WHERE `idArticulo` = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCategoriaCursos`()
BEGIN
    SELECT 
        `idCategoriaCurso`,
        `nombreCategoriaCurso` 
    FROM `tblcategoriacurso` 
    WHERE `nombreCategoriaCurso`!='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarComprasRangoFecha`(
	in	fechaInici	datetime,
	in	fechaFin	datetime
)
BEGIN
	select * from tblCompra
	where fechaCompra BETWEEN fechaInici AND fechaFin;
	
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCreditoByTipoCredito1`(
    in idCredi int
)
BEGIN
    select c.idCredito, c.idCategoriaCredito, c.documentoUsuario, c.fechaInicio, c.saldoInicial, c.saldoActual, c.estadoCredito
    FROM tblCredito c INNER JOIN tblCategoriaCredito cc ON (c.idCategoriaCredito=cc.idCategoriaCredito) 
    WHERE idCredito=idCredi AND idCategoriaCredito=1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCreditoByTipoCredito2`(
    in idCredi int
)
BEGIN
    select c.idCredito, c.idCategoriaCredito, c.documentoUsuario, c.fechaInicio, c.saldoInicial, c.saldoActual, c.estadoCredito
    FROM tblCredito c INNER JOIN tblCategoriaCredito cc ON (c.idCategoriaCredito=cc.idCategoriaCredito) 
    WHERE idCredito=idCredi AND idCategoriaCredito=2;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCursoPorID`(id int)
BEGIN
    SELECT 
        `idCurso`,
        `nombreCurso`, 
        `cantidadClases`, 
        `horasPorClase`, 
        `estadoCurso`, 
        `descripcionCurso`, 
        `precioCurso`, 
        cc.`idCategoriaCurso` as `idCategoriaCurso`,
        cc.`nombreCategoriaCurso` as `nombreCategoriaCurso`
    FROM `tblcurso` c INNER JOIN tblcategoriacurso cc ON (c.`idCategoriaCurso`=cc.`idCategoriaCurso`) 
    WHERE `idCurso`=id AND `nombreCategoriaCurso`!='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCursos`()
BEGIN
    SELECT 
        `idCurso`,
        `nombreCurso`, 
        `cantidadClases`, 
        `horasPorClase`, 
        `estadoCurso`, 
        `descripcionCurso`, 
        `precioCurso`, 
        cc.`idCategoriaCurso` as `idCategoriaCurso`,
        cc.`nombreCategoriaCurso` as `nombreCategoriaCurso`
    FROM `tblcurso` c INNER JOIN tblcategoriacurso cc ON (c.`idCategoriaCurso`=cc.`idCategoriaCurso`) 
    WHERE `nombreCategoriaCurso`!='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCursosDisponibles`()
BEGIN
    SELECT 
        `idCurso`,
        `nombreCurso`, 
        `cantidadClases`, 
        `horasPorClase`, 
        `estadoCurso`, 
        `descripcionCurso`, 
        `precioCurso`, 
        cc.`idCategoriaCurso` as `idCategoriaCurso`,
        cc.`nombreCategoriaCurso` as `nombreCategoriaCurso`
    FROM `tblcurso` c INNER JOIN tblcategoriacurso cc ON (c.`idCategoriaCurso`=cc.`idCategoriaCurso`) 
    WHERE `nombreCategoriaCurso`!='Seminario' and `estadoCurso`=1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarEstudiantePorID`(
    in id varchar(30)
)
BEGIN
    SELECT 
        `documentoUsuario`, 
        DATE_FORMAT(`fechaNacimiento`,'%d/%m/%Y') as `fechaNacimiento`, 
        `nombreUsuario`, 
        `apellidoUsuario`, 
        `emailUsuario`, 
        `password`, 
        `estadoUsuario`, 
        du.`idDetalleUsuario` as `idDetalleUsuario`, 
        `direccionUsuario`, 
        `telefonoFijo`, 
        `telefonoMovil`, 
        `generoUsuario`, 
        `idrol`, 
        `documentoAcudiente`,
        `estadoBeneficiario`
    FROM `tblusuario` u inner join `tbldetalleusuario` du on (u.`idDetalleUsuario`= du.`idDetalleUsuario`)
    WHERE idrol=3 and u.`documentoUsuario`=id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarEstudiantes`()
BEGIN
    SELECT 
        `documentoUsuario`, 
        `fechaNacimiento`, 
        `nombreUsuario`, 
        `apellidoUsuario`, 
        `emailUsuario`, 
        `password`, 
        `estadoUsuario`, 
        du.`idDetalleUsuario` as `idDetalleUsuario`, 
        `direccionUsuario`, 
        `telefonoFijo`, 
        `telefonoMovil`, 
        `generoUsuario`, 
        `idrol`, 
        `documentoAcudiente` 
    FROM `tblusuario` u inner join `tbldetalleusuario` du on (u.`idDetalleUsuario`= du.`idDetalleUsuario`)
    WHERE idrol=3;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarIDCategoriaSeminario`()
BEGIN
    SELECT 
        `idCategoriaCurso`
    FROM `tblcategoriacurso` 
    WHERE `nombreCategoriaCurso`='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarSeminarioPorID`(id int)
BEGIN
    SELECT 
        `idCurso`,
        `nombreCurso`, 
        `cantidadClases`, 
        `horasPorClase`, 
        `estadoCurso`, 
        `descripcionCurso`, 
        `precioCurso`, 
        cc.`idCategoriaCurso` as `idCategoriaCurso`,
        cc.`nombreCategoriaCurso` as `nombreCategoriaCurso`
    FROM `tblcurso` c INNER JOIN tblcategoriacurso cc ON (c.`idCategoriaCurso`=cc.`idCategoriaCurso`) 
    WHERE `idCurso`=id AND `nombreCategoriaCurso`='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarSeminarios`()
BEGIN
    SELECT 
        `idCurso`,
        `nombreCurso`, 
        `cantidadClases`, 
        `horasPorClase`, 
        `estadoCurso`, 
        `descripcionCurso`, 
        `precioCurso`, 
        cc.`idCategoriaCurso` as `idCategoriaCurso`,
        cc.`nombreCategoriaCurso` as `nombreCategoriaCurso`
    FROM `tblcurso` c INNER JOIN tblcategoriacurso cc ON (c.`idCategoriaCurso`=cc.`idCategoriaCurso`) 
    WHERE `nombreCategoriaCurso`='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarSeminariosDisponibles`()
BEGIN
    SELECT 
        `idCurso`,
        `nombreCurso`, 
        `cantidadClases`, 
        `horasPorClase`, 
        `estadoCurso`, 
        `descripcionCurso`, 
        `precioCurso`, 
        cc.`idCategoriaCurso` as `idCategoriaCurso`,
        cc.`nombreCategoriaCurso` as `nombreCategoriaCurso`
    FROM `tblcurso` c INNER JOIN tblcategoriacurso cc ON (c.`idCategoriaCurso`=cc.`idCategoriaCurso`) 
    WHERE `nombreCategoriaCurso`='Seminario' and `estadoCurso`=1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarSubsidiosEmpresa`(

	in idEmpre	int

)
BEGIN
	select * from tblSubsidio
	where idEmpresa = idEmpre
	order by idSubsidio;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarUsuarioModulo`(
    IN `idUser` varchar (30)
)
BEGIN
    SELECT 
        tblusuario.`emailUsuario` "id",
        tblmodulo.enlace "enlace",
        tblmodulo.nombre "nombre" 
    FROM tblusuario inner join tblrol on (tblusuario.idrol = tblrol.idrol) inner join tblmodulorol on(tblrol.idrol=tblmodulorol.idrol) inner join tblmodulo on(tblmodulorol.idmodulo=tblmodulo.idmodulo) where tblusuario.`emailUsuario`=idUser;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarUsuarioPorID`(
    in `documentoUsuar` varchar(40)
 )
BEGIN
    SELECT 
        `documentoUsuario`,
        `fechaNacimiento`, 
        `nombreUsuario`, 
        `apellidoUsuario`, 
        `emailUsuario`, 
        `password`, 
        `estadoUsuario`,
        `idDetalleUsuario`, 
        `idrol`, 
        `documentoAcudiente` 
    FROM `tblusuario` 
    WHERE `documentoUsuario` = `documentoUsuar`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarUsuarioPorPassYCorreo`(
    in `correo` varchar(40),
    in `pass` varchar(40)
)
BEGIN
    SELECT 
        `documentoUsuario`, 
        `fechaNacimiento`, 
        `nombreUsuario`, 
        `apellidoUsuario`, 
        `emailUsuario`, 
        `password`, 
        `estadoUsuario`, 
        `idDetalleUsuario`, 
        `idrol`, 
        `documentoAcudiente` 
    FROM `tblusuario`  
    WHERE `emailUsuario` = `correo` and `password` = `pass`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarVentasDiarias`(

)
BEGIN
	select * from tblVenta
	where fechaVenta = CURDATE();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spContadorArticulos`()
BEGIN

SELECT MAX(idArticulo)+1 AS idArticulo FROM tblArticulo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarAbono`(
    in idAbo        int,
    in valorAbo     int,
    in fechaPa    datetime,
    in idCredi      int
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select idAbono from tblAbono where idAbono=idAbo)) then
		set msg="Este abono ya fue registrado.";
		select msg as Respuesta;
	else
		insert into tblAbono (idCredito,valorAbono,fechaPago) Values(idCredi,valorAbo,fechaPa);
		set msg="El abono se ha registrado correctamente.";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarArticulo`(IN `idCategoriaArticulo` INT, IN `descripcionArticulo` VARCHAR(50) CHARSET utf8, IN `cantidadDisponible` INT, IN `precioUnitario` INT)
    NO SQL
INSERT INTO `tblarticulo`(`idCategoriaArticulo`, `descripcionArticulo`, `cantidadDisponible`, `precioUnitario`) VALUES (idCategoriaArticulo,descripcionArticulo,cantidadDisponible,precioUnitario)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCategoriaArticulo`(IN `nombre` VARCHAR(30))
    NO SQL
insert into tblCategoriaArticulo (nombreCategoriaArticulo) values (nombre)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCategoriaCurso`(IN `nombre` VARCHAR(30))
BEGIN
insert into tblCategoriaCurso (nombreCategoriaCurso) values (nombre);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCompra`(
    IN `facturaProveed` VARCHAR(50), 
    IN `nombreProveed` VARCHAR(50), 
    IN `totalMovimien` INT,
    IN `documentoUsuar` VARCHAR(30)
)
BEGIN
    declare msg varchar(40);    
    if (exists(select facturaProveedor from tblMovimiento where facturaProveedor=`facturaProveed`)) then
            set msg="Esta compra ya fue registrada.";
            select msg as Respuesta;
    else
        INSERT INTO `tblmovimiento`
        (
            `fechaMovimiento`, 
            `totalMovimiento`, 
            `idtipoMovimiento`, 
            `documentoUsuario`, 
            `facturaProveedor`, 
            `nombreProveedor`
        ) VALUES (
            curdate(),
            `totalMovimien`,
            1,
            `documentoUsuar`, 
            `facturaProveed`, 
            `nombreProveed`
        );
    end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCredito`(
    in idCredi      int,
    in idClien      int,
    in fechaInic    datetime,
    in saldoInici   double,
    in saldoActu    double,
    in estadoCredi  tinyint
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select idCliente from tblCredito where idCliente=idClien and saldoActual>0)) then
		set msg="Este cliente ya tiene un credito activo.";
		select msg as Respuesta;
       	else
		insert into tblCredito (idCliente,fechaInicio,saldoInicial,saldoActual,estadoCredito) 
                Values(idClien,fechaInic,saldoInic,saldoActu,estadoCredi);
		set msg="El credito ha sido registrado correctamente.";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCurso`(
    in nombre varchar(30),
    in cantidad int,
    in horas int,
    in estado int,
    in descripcion varchar(100),
    in precio int,
    in idcategoria int
)
BEGIN
INSERT INTO `tblcurso`(
    `nombreCurso`, 
    `cantidadClases`, 
    `horasPorClase`, 
    `estadoCurso`, 
    `descripcionCurso`, 
    `precioCurso`, 
    `idCategoriaCurso`
) VALUES (
    nombre,
    cantidad,
    horas,
    estado,
    descripcion,
    precio,
    idcategoria
);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarDetalleCompra`(
    IN `idArticu` INT, 
    IN `cantid` INT, 
    IN `descuen` INT, 
    IN `totalDetalleMovimien` INT,
    IN `precioArticu` INT
)
BEGIN
    INSERT INTO `tbldetallemovimiento`
    (
        `idArticulo`, 
        `cantidad`, 
        `descuento`, 
        `totalDetalleMovimiento`, 
        `idMovimiento`, 
        `precioArticulo`
    ) VALUES 
    (
        `idArticu`, 
        `cantid`, 
        `descuen`, 
        `totalDetalleMovimien`,
        (SELECT MAX(`idMovimiento`) FROM tblMovimiento),
        `precioArticu`
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarDetalleVenta`(
    in idDetalleVen	    int,
    in idVen     	    int,
    in idarticu	        int,
    in cantidadVen	    int,
    in descuen		    int,
    in totalDetalleVen  int   
)
BEGIN
	declare msg varchar(40);    
	if (exists(select idDetalleVenta from tblDetalleVenta where idDetalleVenta=idDetalleven)) then
		set msg="Este detalle de venta ya existe";
		select msg as Respuesta;
	else
		insert into tblDetalleVenta (idVenta,idArticulo,cantidadVenta,descuento,totalDetalleVenta) Values(idVen,idarticu,cantidadVen,descuen,totalDetalleVen);
		set msg="Detalla de venta registrado exitosamente";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarEmpresa`(
    in nitEmpre         varchar(20),
    in nombreEmpre    	varchar(50),
    in direccionEmpre   varchar(50),
    in nombreContac     varchar(50),
    in telefonoContac	varchar(50),
    in emailContac	varchar(50)
    
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select nitEmpresa from tblEmpresa where nitEmpresa=nitEmpre)) then
		set msg="Esta empresa ya existe";
		select msg as Respuesta;
	else
		insert into tblEmpresa (nitEmpresa,nombreEmpresa,direccionEmpresa,nombreContacto,telefonoContacto,emailContacto) Values(nitEmpre,nombreEmpre,direccionEmpre,nombreContac,telefonoContac,emailContac);
		set msg="La empresa se ha registrado exitosamente";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarEstudiante`(
    in documentoUsuar   varchar(20),
    in fechaNacimien    DATE,
    in nombreUsuar      varchar(30),
    in apellidoUsuar    varchar(30),
    in emailUsuar       varchar(50),
    in passwo           varchar(45),
    in estadoUsuar      int,
    in documentoAcudien varchar(20),
    in direccionUsuar   varchar(50),
    in telefonoFi       varchar(11),
    in telefonoMov      varchar(15),
    in generoUsuar      bit
)
BEGIN
INSERT INTO `tbldetalleusuario`(
    `direccionUsuario`, 
    `telefonoFijo`, 
    `telefonoMovil`, 
    `generoUsuario`
) VALUES (
    direccionUsuar,
    telefonoFi,
    telefonoMov,
    generoUsuar
);
INSERT INTO `tblusuario`(
    `documentoUsuario`, 
    `fechaNacimiento`, 
    `nombreUsuario`, 
    `apellidoUsuario`, 
    `emailUsuario`, 
    `password`, 
    `estadoUsuario`, 
    `idrol`, 
    `documentoAcudiente`,
    `idDetalleUsuario`
) VALUES (
    documentoUsuar,
    fechaNacimien,
    nombreUsuar,
    apellidoUsuar,
    emailUsuar,
    passwo,
    estadoUsuar,
    3,
    documentoAcudien,
    (SELECT MAX(idDetalleUsuario) FROM `tbldetalleusuario`));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarFactura`(
    in idFactu       int,
    in fechafactu    datetime,
    in totalFactu    varchar(50)
    
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select idFactura from tblFactura where idFactura=idFactu)) then
		set msg="Esta factura ya existe";
		select msg as Respuesta;
	else
		insert into tblFactura (fechaFactura,totalFactura) Values(fechafactu,totalFactu);
		set msg="La factura se ha registrado exitosamente";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarInscripcion`(
    in idIncripci       int,
    in idSeminar        int,
    in precioSeminar	int,
    in fechaAsistenc    datetime,
    in idVen            int  
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select idIncripcion from tblInscripcion where idIncripcion=idIncripci)) then
		set msg="Esta inscripciÃ³n ya existe";
		select msg as Respuesta;
	else
		insert into tblInscripcion (idIncripcion,idSeminario, precioSeminario, fechaAsistencia,idVenta) Values(idIncripci,idSeminar,precioSeminar,fechaAsistenc,idVen);
		set msg="La inscripciï¿½ï¿½n se ha registrado exitosamente";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarPreinscripcion`(
    `documentoUsuar` varchar(45), 
    `idCur` int
)
BEGIN
    INSERT INTO `tblpreinscripcion`
    (
        `estado`, 
        `documentoUsuario`, 
        `idCurso`
    ) 
    VALUES  
    (
        1, 
        `documentoUsuar`, 
        `idCur`
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarSubsidio`(IN `idSubsid` INT, IN `nitEmpre` INT, IN `idClien` VARCHAR(30), IN `fechaAsignaci` DATETIME, IN `valorSubsid` int)
BEGIN
	declare msg varchar(40);    
	if (exists(select idSubsideo from tblSubsideo where idSubsidio=idSubsid)) then
		set msg="Este subsidio ya existe";
		select msg as Respuesta;
	else
		insert into tblSubsidio (nitEmpresa,idCliente,fechaAsignacion,valorSubsidio) Values(nitEmpre,idClien,fechaAsignaci,valorSubsid);
		set msg="Este subsidio se ha registrado exitosamente";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarUsuario`(
    in `documentoUsuar` varchar(40), 
    in `fechaNacimien` date, 
    in `nombreUsuar` varchar(40), 
    in `apellidoUsuar` varchar(40), 
    in `emailUsuar` varchar(40), 
    in `passwo` varchar(40), 
    in `estadoUsuar` int, 
    in `idr` int
 )
BEGIN
INSERT INTO `tblusuario`
(
    `documentoUsuario`, 
    `fechaNacimiento`, 
    `nombreUsuario`, 
    `apellidoUsuario`, 
    `emailUsuario`, 
    `password`, 
    `estadoUsuario`, 
    `idrol`
) 
VALUES 
(
    `documentoUsuar`, 
    `fechaNacimien`, 
    `nombreUsuar`, 
    `apellidoUsuar`, 
    `emailUsuar`, 
    `passwo`, 
    `estadoUsuar`, 
     4
);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarVenta`(
    in idVen                int,
    in fechaVen             datetime,
    in totalVen             int,
    in idFactu int,
    in idClien VARCHAR(30) 
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select idVenta from tblVenta where idVenta=idVen)) then
		set msg="Este subsideo ya existe";
		select msg as Respuesta;
	else
		insert into tblVenta (fechaVenta,totalVenta,idFactura,idCliente) Values(fechaVen,totalVen,idFactu,idClien);
		set msg="Este subsideo se ha registrado exitosamente";
		select msg as Respuesta; 
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarAbonos`()
select * from tblAbono a inner join tblCredito c on(a.idCredito=c.idCredito) 
order by (a.idCredito,a.fechaPago)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarArticulos`()
BEGIN

SELECT * FROM tblarticulo INNER JOIN tblcategoriaarticulo ON tblarticulo.idCategoriaArticulo = tblcategoriaarticulo.idCategoriaArticulo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarCompras`(IN `numeroFactura` VARCHAR(50), IN `nombreProveedor` VARCHAR(50), IN `fechaCompra` DATE, IN `totalCompra` INT)
    NO SQL
BEGIN
SELECT * FROM tblCompra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarCreditos`()
select c.idCredito, c.idCategoriaCredito, c.documentoUsuario, c.fechaInicio, c.saldoInicial, c.saldoActual, c.estadoCredito 
from tblCredito c inner join tblUsuario u on(c.documentoUsuario=u.documentoUsuario)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarEmpresas`()
BEGIN

SELECT * FROM tblEmpresa;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblabono`
--

CREATE TABLE IF NOT EXISTS `tblabono` (
`idAbono` int(11) NOT NULL,
  `valorAbono` int(11) NOT NULL DEFAULT '0',
  `fechaPago` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idCredito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblacudiente`
--

CREATE TABLE IF NOT EXISTS `tblacudiente` (
  `documentoAcudiente` varchar(20) NOT NULL,
  `nombreAcudiente` varchar(50) NOT NULL,
  `telefonoAcudiente` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblarticulo`
--

CREATE TABLE IF NOT EXISTS `tblarticulo` (
`idArticulo` int(11) NOT NULL,
  `idCategoriaArticulo` int(11) NOT NULL,
  `descripcionArticulo` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `cantidadDisponible` mediumint(9) NOT NULL,
  `precioCompra` int(11) NOT NULL,
  `precioVenta` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblarticulo`
--

INSERT INTO `tblarticulo` (`idArticulo`, `idCategoriaArticulo`, `descripcionArticulo`, `cantidadDisponible`, `precioCompra`, `precioVenta`) VALUES
(1, 4, 'Vinilo Aguamarina', 30, 1000, 1200),
(2, 1, 'Vinilo Dorado', 30, 1200, 1400),
(3, 1, 'Vinilo Plateado', 30, 1200, 1400);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcategoriaarticulo`
--

CREATE TABLE IF NOT EXISTS `tblcategoriaarticulo` (
`idCategoriaArticulo` int(11) NOT NULL,
  `nombreCategoriaArticulo` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblcategoriaarticulo`
--

INSERT INTO `tblcategoriaarticulo` (`idCategoriaArticulo`, `nombreCategoriaArticulo`) VALUES
(1, 'Categoria A'),
(2, 'Categoria B'),
(3, 'Categoria C'),
(4, 'Categoria D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcategoriacredito`
--

CREATE TABLE IF NOT EXISTS `tblcategoriacredito` (
  `idCategoriaCredito` int(11) NOT NULL,
  `nombreCategoria` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcategoriacurso`
--

CREATE TABLE IF NOT EXISTS `tblcategoriacurso` (
`idCategoriaCurso` int(11) NOT NULL,
  `nombreCategoriaCurso` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblcategoriacurso`
--

INSERT INTO `tblcategoriacurso` (`idCategoriaCurso`, `nombreCategoriaCurso`) VALUES
(1, 'Seminario'),
(2, 'Categoria A'),
(3, 'Categoria B'),
(4, 'Categoria C'),
(5, 'Categoria D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblclase`
--

CREATE TABLE IF NOT EXISTS `tblclase` (
`idClase` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estadoPago` bit(1) DEFAULT NULL,
  `estadoAsistencia` bit(1) DEFAULT NULL,
  `creditoCreado` bit(1) DEFAULT NULL,
  `precioClase` float DEFAULT NULL,
  `idCurso` int(11) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcredito`
--

CREATE TABLE IF NOT EXISTS `tblcredito` (
`idCredito` int(11) NOT NULL,
  `fechaInicio` datetime DEFAULT CURRENT_TIMESTAMP,
  `saldoInicial` int(11) NOT NULL DEFAULT '0',
  `saldoActual` int(11) NOT NULL DEFAULT '0',
  `estadoCredito` tinyint(4) NOT NULL,
  `idCategoriaCredito` int(11) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcurso`
--

CREATE TABLE IF NOT EXISTS `tblcurso` (
`idCurso` int(11) NOT NULL,
  `nombreCurso` varchar(50) NOT NULL,
  `cantidadClases` int(11) NOT NULL,
  `horasPorClase` int(11) NOT NULL,
  `estadoCurso` int(11) NOT NULL,
  `descripcionCurso` varchar(100) DEFAULT NULL,
  `precioCurso` int(11) DEFAULT NULL,
  `idCategoriaCurso` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblcurso`
--

INSERT INTO `tblcurso` (`idCurso`, `nombreCurso`, `cantidadClases`, `horasPorClase`, `estadoCurso`, `descripcionCurso`, `precioCurso`, `idCategoriaCurso`) VALUES
(2, 'SeminarioPrueba', 1, 5, 1, 'Un Seminario de Prueba', 50000, 1),
(3, 'Oleo', 6, 3, 1, 'El oleo es un curso muy interesante', 120000, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetallemovimiento`
--

CREATE TABLE IF NOT EXISTS `tbldetallemovimiento` (
  `idDetalleMovimiento` int(11) NOT NULL,
  `idArticulo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `descuento` int(11) DEFAULT NULL,
  `totalDetalleMovimiento` int(11) NOT NULL,
  `idMovimiento` int(11) NOT NULL,
  `precioArticulo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetalleusuario`
--

CREATE TABLE IF NOT EXISTS `tbldetalleusuario` (
`idDetalleUsuario` int(11) NOT NULL,
  `direccionUsuario` varchar(50) NOT NULL,
  `telefonoFijo` varchar(11) NOT NULL,
  `telefonoMovil` varchar(15) NOT NULL,
  `generoUsuario` bit(1) NOT NULL,
  `estadoBeneficiario` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbldetalleusuario`
--

INSERT INTO `tbldetalleusuario` (`idDetalleUsuario`, `direccionUsuario`, `telefonoFijo`, `telefonoMovil`, `generoUsuario`, `estadoBeneficiario`) VALUES
(1, 'Calle 24 # 65 e 25', '5860529', '3218016237', b'1', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblempresa`
--

CREATE TABLE IF NOT EXISTS `tblempresa` (
  `nitEmpresa` varchar(20) NOT NULL DEFAULT '',
  `nombreEmpresa` varchar(50) NOT NULL,
  `direccionEmpresa` varchar(50) NOT NULL,
  `nombreContacto` varchar(50) NOT NULL,
  `telefonoContacto` varchar(50) NOT NULL,
  `emailContacto` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblempresa`
--

INSERT INTO `tblempresa` (`nitEmpresa`, `nombreEmpresa`, `direccionEmpresa`, `nombreContacto`, `telefonoContacto`, `emailContacto`) VALUES
('1017225673', 'OtherGames S.A.S', 'Calle 24 # 65 e 25', 'Juan Sebastian Montoya', '3218016237', 'thejuansebas03@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblmodulo`
--

CREATE TABLE IF NOT EXISTS `tblmodulo` (
`idmodulo` int(11) NOT NULL,
  `enlace` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblmodulo`
--

INSERT INTO `tblmodulo` (`idmodulo`, `enlace`, `nombre`) VALUES
(1, 'matricula.jsp', 'Gestión de Matriculas'),
(2, 'empresa.jsp', 'Gestión de Empresas'),
(3, 'curso.jsp', 'Gestión de Cursos y Seminarios'),
(5, 'articulo.jsp', 'Gestión de Artículos'),
(6, 'caja.jsp', 'Caja Registradora'),
(7, 'nuestro.jsp', 'Nuestros Cursos'),
(8, 'acerca.jsp', 'Acerca de Nosotros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblmodulorol`
--

CREATE TABLE IF NOT EXISTS `tblmodulorol` (
  `idmodulo` int(11) NOT NULL,
  `idrol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblmodulorol`
--

INSERT INTO `tblmodulorol` (`idmodulo`, `idrol`) VALUES
(1, 1),
(2, 1),
(3, 1),
(5, 1),
(6, 1),
(7, 3),
(8, 3),
(7, 4),
(8, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblmovimiento`
--

CREATE TABLE IF NOT EXISTS `tblmovimiento` (
  `idMovimiento` int(11) NOT NULL,
  `fechaMovimiento` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `totalMovimiento` int(11) NOT NULL,
  `idtipoMovimiento` int(11) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL,
  `facturaProveedor` varchar(45) DEFAULT NULL,
  `nombreProveedor` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblpreinscripcion`
--

CREATE TABLE IF NOT EXISTS `tblpreinscripcion` (
`idPreinscripcion` int(11) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `documentoUsuario` varchar(20) NOT NULL,
  `idCurso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblrol`
--

CREATE TABLE IF NOT EXISTS `tblrol` (
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(40) NOT NULL,
`idrol` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblrol`
--

INSERT INTO `tblrol` (`nombre`, `descripcion`, `idrol`) VALUES
('administrador', 'Es quien administra el sistema', 1),
('operador', 'privilegios por debajo del administrador', 2),
('estudiante', 'estudiante registrado', 3),
('cliente', 'cliente registrado', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblsubsidio`
--

CREATE TABLE IF NOT EXISTS `tblsubsidio` (
`idSubsidio` int(11) NOT NULL,
  `fechaAsignacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valorSubsidio` int(11) NOT NULL,
  `nitEmpresa` varchar(20) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbltipomovimiento`
--

CREATE TABLE IF NOT EXISTS `tbltipomovimiento` (
  `idtipoMovimiento` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbltipomovimiento`
--

INSERT INTO `tbltipomovimiento` (`idtipoMovimiento`, `descripcion`) VALUES
(1, 'Compra a Proveedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblusuario`
--

CREATE TABLE IF NOT EXISTS `tblusuario` (
  `documentoUsuario` varchar(20) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `nombreUsuario` varchar(30) NOT NULL,
  `apellidoUsuario` varchar(30) NOT NULL,
  `emailUsuario` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `estadoUsuario` int(11) NOT NULL,
  `idDetalleUsuario` int(11) DEFAULT NULL,
  `idrol` int(11) NOT NULL,
  `documentoAcudiente` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblusuario`
--

INSERT INTO `tblusuario` (`documentoUsuario`, `fechaNacimiento`, `nombreUsuario`, `apellidoUsuario`, `emailUsuario`, `password`, `estadoUsuario`, `idDetalleUsuario`, `idrol`, `documentoAcudiente`) VALUES
('1017225673', '1994-11-03', 'Juan Sebastián', 'Montoya Montoya', 'jsmontoya37@misena.edu.co', '123', 1, NULL, 1, NULL),
('CC1017225673', '1994-11-03', 'Juan', 'Montoya', 'thejuansebas03@gmail.com', 'es120300', 1, 1, 3, NULL),
('CC32466217', '1999-02-03', 'Maria Dolly', 'Montoya Puerta', 'micorreo@correo.com', '123', 1, NULL, 4, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tblabono`
--
ALTER TABLE `tblabono`
 ADD PRIMARY KEY (`idAbono`), ADD KEY `fk_tblabono_tblcredito1_idx` (`idCredito`);

--
-- Indices de la tabla `tblacudiente`
--
ALTER TABLE `tblacudiente`
 ADD PRIMARY KEY (`documentoAcudiente`);

--
-- Indices de la tabla `tblarticulo`
--
ALTER TABLE `tblarticulo`
 ADD PRIMARY KEY (`idArticulo`), ADD KEY `FK_tblArticulo_idCategoriaArticulo` (`idCategoriaArticulo`);

--
-- Indices de la tabla `tblcategoriaarticulo`
--
ALTER TABLE `tblcategoriaarticulo`
 ADD PRIMARY KEY (`idCategoriaArticulo`);

--
-- Indices de la tabla `tblcategoriacredito`
--
ALTER TABLE `tblcategoriacredito`
 ADD PRIMARY KEY (`idCategoriaCredito`);

--
-- Indices de la tabla `tblcategoriacurso`
--
ALTER TABLE `tblcategoriacurso`
 ADD PRIMARY KEY (`idCategoriaCurso`);

--
-- Indices de la tabla `tblclase`
--
ALTER TABLE `tblclase`
 ADD PRIMARY KEY (`idClase`);

--
-- Indices de la tabla `tblcredito`
--
ALTER TABLE `tblcredito`
 ADD PRIMARY KEY (`idCredito`), ADD KEY `fk_tblcredito_tblCategoriaCredito1_idx` (`idCategoriaCredito`), ADD KEY `fk_tblcredito_tblusuario1_idx` (`documentoUsuario`);

--
-- Indices de la tabla `tblcurso`
--
ALTER TABLE `tblcurso`
 ADD PRIMARY KEY (`idCurso`), ADD KEY `fk_tblcurso_tblcategoriacurso1_idx` (`idCategoriaCurso`);

--
-- Indices de la tabla `tbldetallemovimiento`
--
ALTER TABLE `tbldetallemovimiento`
 ADD PRIMARY KEY (`idDetalleMovimiento`), ADD KEY `FK_tblDetalleVenta_idArticulo` (`idArticulo`), ADD KEY `fk_tbldetallemovimiento_tblMovimiento1_idx` (`idMovimiento`);

--
-- Indices de la tabla `tbldetalleusuario`
--
ALTER TABLE `tbldetalleusuario`
 ADD PRIMARY KEY (`idDetalleUsuario`);

--
-- Indices de la tabla `tblempresa`
--
ALTER TABLE `tblempresa`
 ADD PRIMARY KEY (`nitEmpresa`);

--
-- Indices de la tabla `tblmodulo`
--
ALTER TABLE `tblmodulo`
 ADD PRIMARY KEY (`idmodulo`);

--
-- Indices de la tabla `tblmodulorol`
--
ALTER TABLE `tblmodulorol`
 ADD PRIMARY KEY (`idmodulo`,`idrol`), ADD KEY `idrol` (`idrol`);

--
-- Indices de la tabla `tblmovimiento`
--
ALTER TABLE `tblmovimiento`
 ADD PRIMARY KEY (`idMovimiento`), ADD KEY `fk_tblMovimiento_tblTipoMovimiento1_idx` (`idtipoMovimiento`), ADD KEY `fk_tblMovimiento_tblusuario1_idx` (`documentoUsuario`);

--
-- Indices de la tabla `tblpreinscripcion`
--
ALTER TABLE `tblpreinscripcion`
 ADD PRIMARY KEY (`idPreinscripcion`);

--
-- Indices de la tabla `tblrol`
--
ALTER TABLE `tblrol`
 ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `tblsubsidio`
--
ALTER TABLE `tblsubsidio`
 ADD PRIMARY KEY (`idSubsidio`), ADD KEY `fk_tblsubsidio_tblempresa1_idx` (`nitEmpresa`), ADD KEY `fk_tblsubsidio_tblusuario1_idx` (`documentoUsuario`);

--
-- Indices de la tabla `tbltipomovimiento`
--
ALTER TABLE `tbltipomovimiento`
 ADD PRIMARY KEY (`idtipoMovimiento`);

--
-- Indices de la tabla `tblusuario`
--
ALTER TABLE `tblusuario`
 ADD PRIMARY KEY (`documentoUsuario`), ADD UNIQUE KEY `emailUsuario_UNIQUE` (`emailUsuario`), ADD KEY `fk_tblestudiante_tblDetalleCliente1_idx` (`idDetalleUsuario`), ADD KEY `fk_tblusuario_tblrol1_idx` (`idrol`), ADD KEY `fk_tblusuario_tblacudiente1_idx` (`documentoAcudiente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tblabono`
--
ALTER TABLE `tblabono`
MODIFY `idAbono` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tblarticulo`
--
ALTER TABLE `tblarticulo`
MODIFY `idArticulo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `tblcategoriaarticulo`
--
ALTER TABLE `tblcategoriaarticulo`
MODIFY `idCategoriaArticulo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tblcategoriacurso`
--
ALTER TABLE `tblcategoriacurso`
MODIFY `idCategoriaCurso` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `tblclase`
--
ALTER TABLE `tblclase`
MODIFY `idClase` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tblcredito`
--
ALTER TABLE `tblcredito`
MODIFY `idCredito` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tblcurso`
--
ALTER TABLE `tblcurso`
MODIFY `idCurso` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `tbldetalleusuario`
--
ALTER TABLE `tbldetalleusuario`
MODIFY `idDetalleUsuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tblmodulo`
--
ALTER TABLE `tblmodulo`
MODIFY `idmodulo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `tblpreinscripcion`
--
ALTER TABLE `tblpreinscripcion`
MODIFY `idPreinscripcion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tblrol`
--
ALTER TABLE `tblrol`
MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tblsubsidio`
--
ALTER TABLE `tblsubsidio`
MODIFY `idSubsidio` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tblabono`
--
ALTER TABLE `tblabono`
ADD CONSTRAINT `tblabono_ibfk_1` FOREIGN KEY (`idCredito`) REFERENCES `tblcredito` (`idCredito`);

--
-- Filtros para la tabla `tblarticulo`
--
ALTER TABLE `tblarticulo`
ADD CONSTRAINT `FK_tblArticulo_idCategoriaArticulo` FOREIGN KEY (`idCategoriaArticulo`) REFERENCES `tblcategoriaarticulo` (`idCategoriaArticulo`);

--
-- Filtros para la tabla `tblcredito`
--
ALTER TABLE `tblcredito`
ADD CONSTRAINT `fk_tblcredito_tblCategoriaCredito1` FOREIGN KEY (`idCategoriaCredito`) REFERENCES `tblcategoriacredito` (`idCategoriaCredito`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_tblcredito_tblusuario1` FOREIGN KEY (`documentoUsuario`) REFERENCES `tblusuario` (`documentoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tblcurso`
--
ALTER TABLE `tblcurso`
ADD CONSTRAINT `fk_tblcurso_tblcategoriacurso1` FOREIGN KEY (`idCategoriaCurso`) REFERENCES `tblcategoriacurso` (`idCategoriaCurso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbldetallemovimiento`
--
ALTER TABLE `tbldetallemovimiento`
ADD CONSTRAINT `FK_tblDetalleVenta_idArticulo` FOREIGN KEY (`idArticulo`) REFERENCES `tblarticulo` (`idArticulo`),
ADD CONSTRAINT `fk_tbldetallemovimiento_tblMovimiento1` FOREIGN KEY (`idMovimiento`) REFERENCES `tblmovimiento` (`idMovimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tblmodulorol`
--
ALTER TABLE `tblmodulorol`
ADD CONSTRAINT `tblmodulorol_ibfk_1` FOREIGN KEY (`idmodulo`) REFERENCES `tblmodulo` (`idmodulo`),
ADD CONSTRAINT `tblmodulorol_ibfk_2` FOREIGN KEY (`idrol`) REFERENCES `tblrol` (`idrol`);

--
-- Filtros para la tabla `tblmovimiento`
--
ALTER TABLE `tblmovimiento`
ADD CONSTRAINT `fk_tblMovimiento_tblTipoMovimiento1` FOREIGN KEY (`idtipoMovimiento`) REFERENCES `tbltipomovimiento` (`idtipoMovimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_tblMovimiento_tblusuario1` FOREIGN KEY (`documentoUsuario`) REFERENCES `tblusuario` (`documentoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tblsubsidio`
--
ALTER TABLE `tblsubsidio`
ADD CONSTRAINT `fk_tblsubsidio_tblempresa1` FOREIGN KEY (`nitEmpresa`) REFERENCES `tblempresa` (`nitEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_tblsubsidio_tblusuario1` FOREIGN KEY (`documentoUsuario`) REFERENCES `tblusuario` (`documentoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tblusuario`
--
ALTER TABLE `tblusuario`
ADD CONSTRAINT `fk_tblusuario_tblacudiente1` FOREIGN KEY (`documentoAcudiente`) REFERENCES `tblacudiente` (`documentoAcudiente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_tblusuario_tblrol1` FOREIGN KEY (`idrol`) REFERENCES `tblrol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `tblusuario_ibfk_1` FOREIGN KEY (`idDetalleUsuario`) REFERENCES `tbldetalleusuario` (`idDetalleUsuario`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
