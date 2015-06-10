-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2015 a las 22:57:54
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

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
        in precioVen            int
)
BEGIN
	UPDATE tblarticulo SET 
            `idCategoriaArticulo` = `idCategoriaArticu`, 
            `descripcionArticulo`=`descripcionArticu`,
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
	UPDATE tblCredito SET
             `saldoActual`=`saldoActu`
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `spActualizarVenta`(
    IN `idArticu` INT, 
    IN `documentoClien ` varchar (20),
    IN `nombreClien` varchar (50),
    IN `fechaVen` varchar (20),
    IN `totalVen` INT
)
BEGIN
	UPDATE tblventa SET 
            `documentoCliente`=`documentoClien`,
            `nombreCliente`=`nombreClien`,
            `fechaVenta` = `fechaVen`,
            `totalVenta`=`totalVen`
        WHERE `idVenta`=`idVen`;       
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarClientesYEstudiantes`()
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
    WHERE idrol in (3,4);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCompraPorID`(IN idCompra int)
BEGIN
    SELECT  
        `idMovimiento`, 
        `fechaMovimiento` as fechaCompra, 
        `totalMovimiento` as totalCompra, 
        `idtipoMovimiento`, 
        `documentoUsuario`, 
        `numeroAuxiliar` as `facturaProveedor`, 
        `nombreAuxiliar` as `nombreProveedor` 
    FROM `tblmovimiento` 
    WHERE `idtipoMovimiento` = 1
    AND `idMovimiento` = idCompra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCompras`()
BEGIN
    SELECT  
        `idMovimiento`, 
        `fechaMovimiento` as fechaCompra, 
        `totalMovimiento` as totalCompra, 
        `idtipoMovimiento`, 
        `documentoUsuario`, 
        `numeroAuxiliar` as `facturaProveedor`, 
        `nombreAuxiliar` as `nombreProveedor` 
    FROM `tblmovimiento` 
    WHERE `idtipoMovimiento` = 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarComprasRangoFecha`(
	in	fechaInici	datetime,
	in	fechaFin	datetime
)
BEGIN
	select * from tblCompra
	where fechaCompra BETWEEN fechaInici AND fechaFin;
	
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCreditoByDocumento`(
    in documentoUsuar varchar(20)
)
BEGIN
    select c.idCredito, c.documentoUsuario, c.fechaInicio, c.saldoInicial, c.saldoActual, c.estadoCredito
    FROM tblCredito inner join usuario u on c.documentoUsuario = u.documentoUsuario
    WHERE c.documentoUsuar = documentoUsuar;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarCreditoByID`(
    in idCredi int
)
BEGIN
    select idCredito, documentoUsuario, fechaInicio, saldoInicial, saldoActual, estadoCredito
    FROM tblCredito 
    WHERE idCredito=idCredi;
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarDetalleCreditoByIdCredito`(
    in idCredi int
)
BEGIN
    select d.idDetalleCredito, d.idCredito, d.idMovimiento, d.fechaDetalle
    FROM tblDetalleCredito d inner join tblCredito c on d.idCredito = c.idCredito
    WHERE d.idCredito = idCredi;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarDetallesCompraPorID`(IN idCompra int)
BEGIN
    SELECT 
        `idDetalleMovimiento`, 
        art.`idArticulo` as `idArticulo`, 
        `descripcionArticulo`,
        `cantidad`, 
        `descuento`, 
        `totalDetalleMovimiento`, 
        `idMovimiento`, 
        `precioArticulo` 
    FROM `tbldetallemovimiento` detMov INNER JOIN tblarticulo art
    ON (detMov.`idArticulo`=art.`idArticulo`)
    WHERE `idMovimiento` = idCompra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarDetallesVentaPorID`(IN idVenta int)
BEGIN
    SELECT 
        `idDetalleMovimiento`, 
        art.`idArticulo` as `idArticulo`, 
        `descripcionArticulo`,
        `cantidad`, 
        `descuento`, 
        `totalDetalleMovimiento`, 
        `idMovimiento`, 
        `precioArticulo` 
    FROM `tbldetallemovimiento` detMov INNER JOIN tblarticulo art
    ON (detMov.`idArticulo`=art.`idArticulo`)
    WHERE `idMovimiento` = idVenta;
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
        `documentoAcudiente` 
    FROM `tblusuario` u inner join `tbldetalleusuario` du on (u.`idDetalleUsuario`= du.`idDetalleUsuario`)
    WHERE idrol=3;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarEstudiantesConClasesActivas`()
BEGIN
    SELECT 
        usu.`documentoUsuario` as `documentoUsuario`, 
        `nombreCurso`,  
        (select count(`idClase`) from tblclase where `documentoUsuario` = usu.`documentoUsuario` and `idCurso`=cu.`idCurso`) as "numeroClases",
        (select count(`idClase`) from tblclase where `documentoUsuario` = usu.`documentoUsuario` and `idCurso`=cu.`idCurso` and cl.`estadoAsistencia`=0) as "numeroClasesFaltantes",
         ROUND("numeroClases" - "numeroClasesFaltantes" ,0) as numeroClasesAsistidas,
        (select max(fecha) from tblclase where `documentoUsuario` = usu.`documentoUsuario` and `idCurso`=cu.`idCurso` and cl.`estadoAsistencia`=1) as fechaUltimaAsistencia,
        cu.`idCurso` as idCurso
    FROM `tblclase` cl 
        inner join `tblcurso` cu 
        on(cl.`idCurso` = cu.`idCurso`)
        inner join tblusuario usu 
        on (cl.`documentoUsuario`=usu.`documentoUsuario`)
    GROUP BY usu.`documentoUsuario`, cl.`idCurso`;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarEstudiantesConClasesSinPagar`()
BEGIN
    SELECT 
        DISTINCT  usu.`documentoUsuario` as `documentoUsuario`, 
        `nombreCurso`,  
        (select count(`idClase`) from tblclase where `documentoUsuario` = usu.`documentoUsuario` and `idCurso`=cu.`idCurso` and cl.`estadoAsistencia`=1 and cl.`estadoPago`=0) as numeroClases
    FROM `tblclase` cl 
        inner join `tblcurso` cu 
        on(cl.`idCurso` = cu.`idCurso`)
        inner join tblusuario usu 
        on (cl.`documentoUsuario`=usu.`documentoUsuario`)
    WHERE numeroClases>0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarIDCategoriaSeminario`()
BEGIN
    SELECT 
        `idCategoriaCurso`
    FROM `tblcategoriacurso` 
    WHERE `nombreCategoriaCurso`='Seminario';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarMatriculaPorDocumentoYIdCurso`(
    IN documentoUsuar VARCHAR(20),
    IN idCur int
)
BEGIN
    SELECT 
        usu.`documentoUsuario` as `documentoUsuario`,
        `nombreUsuario`,
        `apellidoUsuario`,
        cu.`idCurso` as `idCurso`,
        `nombreCurso`,
        `precioCurso`,
        `precioClase`,
        `horasPorClase`
    FROM `tblclase` cl 
        inner join `tblcurso` cu 
        on(cl.`idCurso` = cu.`idCurso`)
        inner join tblusuario usu 
        on (cl.`documentoUsuario`=usu.`documentoUsuario`)
    WHERE usu.`documentoUsuario`=`documentoUsuar` AND cu.`idCurso` = `idCur`
    GROUP BY usu.`documentoUsuario`, cl.`idCurso`;   
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarPreinscritoPorID`(IN id VARCHAR(30))
BEGIN
    SELECT        
        usu.`documentoUsuario` as `documentoUsuario`, 
        (select `nombreCurso` from tblcurso WHERE pre.`idCurso` = tblcurso.`idCurso`) as nombreCurso,
        fecha as fechaPreincripcion, 
        DATE_FORMAT(`fechaNacimiento`,'%d/%m/%Y') as `fechaNacimiento`, 
        `nombreUsuario`, 
        `apellidoUsuario`, 
        `emailUsuario`, 
        `password`, 
        `estadoUsuario`, 
        `idrol`
    FROM `tblusuario` usu
    inner join tblpreinscripcion pre on(usu.`documentoUsuario`=pre.`documentoUsuario`)
    WHERE idrol=4 and estado=1 and usu.`documentoUsuario`=id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarPreinscritos`()
BEGIN
    SELECT        
        usu.`documentoUsuario` as `documentoUsuario`, 
        (select `nombreCurso` from tblcurso WHERE pre.`idCurso` = tblcurso.`idCurso`) as nombreCurso,
        `idCurso`,
        fecha as fechaPreincripcion, 
        DATE_FORMAT(`fechaNacimiento`,'%d/%m/%Y') as `fechaNacimiento`, 
        `nombreUsuario`, 
        `apellidoUsuario`, 
        `emailUsuario`, 
        `password`, 
        `estadoUsuario`, 
        `idrol`
    FROM `tblusuario` usu
    inner join tblpreinscripcion pre on(usu.`documentoUsuario`=pre.`documentoUsuario`)
    WHERE idrol=4 and estado=1;
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarVentaPorID`(
    in idVenta int
)
BEGIN
    SELECT  
        `idMovimiento`, 
        `fechaMovimiento` as fechaVenta, 
        `totalMovimiento` as totalVenta, 
        `idtipoMovimiento`, 
        `documentoUsuario`, 
        `numeroAuxiliar` as `numeroVenta`, 
        `nombreAuxiliar` as `nombreCliente`, 
        `documentoAuxiliar` as `documentoCliente`
    FROM `tblmovimiento` 
    WHERE `idtipoMovimiento` = 3
    AND `idMovimiento` = idVenta;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarVentas`()
BEGIN
    SELECT  
        `idMovimiento`, 
        `fechaMovimiento` as fechaVenta, 
        `totalMovimiento` as totalVenta, 
        `idtipoMovimiento`, 
        `documentoUsuario`, 
        `numeroAuxiliar` as `numeroVenta`, 
        `nombreAuxiliar` as `nombreCliente`, 
        `documentoAuxiliar` as `documentoCliente`
    FROM `tblmovimiento` 
    WHERE `idtipoMovimiento` = 3;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spConsultarVentasDiarias`(

)
BEGIN
	select * from tblVenta
	where fechaVenta = CURDATE();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spContadorArticulo`()
BEGIN
    SELECT MAX(idArticulo)+1 AS idArticulo FROM tblArticulo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spContadorVenta`()
BEGIN
    declare respuesta int;
    set respuesta = (SELECT max(`numeroAuxiliar`)+1 FROM `tblmovimiento` WHERE `idtipoMovimiento` = 3);
    IF (respuesta is not null) THEN
        SELECT respuesta as idVenta;
    ELSE
        SELECT 1 as idVenta;
    END IF;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spContarClasesRestantes`(
    IN `idCur` INT,
    IN `documentoUsuar` VARCHAR(30),
    IN `cantidadClas` INT
)
BEGIN
    SELECT `cantidadClases`-(`cantidadClas`+ count(`idClase`)) as "Restantes" FROM tblcurso cur inner join tblclase cl on(cur.`idCurso` =cl.`idCurso`) where cur.`idCurso` = `idCur` and `documentoUsuario` = `documentoUsuar` group by `documentoUsuario`;
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarArticulo`(
    IN `idCategoriaArticu` int, 
    IN `descripcionArticu` varchar(50), 
    IN `precioComp` int, 
    IN `precioVen` int
)
BEGIN
INSERT INTO `tblarticulo`(
    `idCategoriaArticulo`, 
    `descripcionArticulo`, 
    `cantidadDisponible`,   
    `precioCompra`, 
    `precioVenta`
) 
VALUES (
    `idCategoriaArticu`, 
    `descripcionArticu`, 
     0,   
    `precioComp`, 
    `precioVen`
);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCategoriaArticulo`(IN `nombre` VARCHAR(30))
    NO SQL
insert into tblCategoriaArticulo (nombreCategoriaArticulo) values (nombre)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCategoriaCurso`(IN `nombre` VARCHAR(30))
BEGIN
insert into tblCategoriaCurso (nombreCategoriaCurso) values (nombre);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarClase`(
    IN `idCur` INT,
    IN `documentoUsuar` VARCHAR(30)
)
BEGIN

    declare msg varchar(100);    
    IF (exists(select `documentoUsuario` from tblclase WHERE `estadoPago` = 0  and `estadoAsistencia` = 1 and `documentoUsuario` = `documentoUsuar` and `idCurso` = `idCur`)) THEN
        set msg= CONVERT('Este estudiante cuenta con clases sin pagar de este curso' using utf8);
        select msg as mensaje, 'error' as tipo;
    ELSEIF(exists (select `documentoUsuario` from tblclase WHERE `estadoPago` = 0  and `documentoUsuario` = `documentoUsuar` and `idCurso` IN (SELECT `idCurso` from tblcurso where idCurso!=`idCur` and `idCategoriaCurso` = (SELECT `idCategoriaCurso` from tblcurso where `idCurso`= `idCur`)))) THEN
        set msg= CONVERT('Este estudiante cuenta con una matrícula activa de un curso de la misma categoria' using utf8);
        select msg as mensaje, 'error' as tipo;
    ELSEIF((select if(count(1)>1, 1, null) from (select distinct `idCurso` from tblclase WHERE `estadoAsistencia` = 0  and `documentoUsuario` = `documentoUsuar` and `idCurso`!=`idCur` GROUP by `idCurso`) as temp)is not null) THEN
        set msg= CONVERT('Este estudiante cuenta con dos matrículas activas' using utf8);        
        select msg as mensaje, 'error' as tipo;
    ELSE
        INSERT INTO tblclase
        (
            `idCurso`,
            `documentoUsuario`,
            `precioClase`
        ) VALUES 
        (
            idCur, 
            documentoUsuar,
            (SELECT `precioCurso`/`cantidadClases` FROM `tblcurso` WHERE `idCurso` = idCur)
        );
        set msg= CONVERT('Se ha regitrado la matricula' using utf8);
        select msg as mensaje, 'success' as tipo;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCompra`(
    IN `facturaProveed` VARCHAR(50), 
    IN `nombreProveed` VARCHAR(50), 
    IN `totalMovimien` INT,
    IN `documentoUsuar` VARCHAR(30)
)
BEGIN
    declare msg varchar(40);    
    if (exists(select numeroAuxiliar from tblMovimiento where numeroAuxiliar=`facturaProveed` and `idtipoMovimiento` = 1)) then
            set msg="Esta compra ya fue registrada.";
            select msg as Respuesta;
    else
        INSERT INTO `tblmovimiento`
        (
            `fechaMovimiento`, 
            `totalMovimiento`, 
            `idtipoMovimiento`, 
            `documentoUsuario`, 
            `numeroAuxiliar`, 
            `nombreAuxiliar`
        ) VALUES (
            NOW(),
            `totalMovimien`,
            1,
            `documentoUsuar`, 
            `facturaProveed`, 
            `nombreProveed`
        );
    end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarCredito`(
    in idCredi              int,
    in documentoUsuar       varchar(20),
    in fechaInic    datetime,
    in saldoInici   double,
    in saldoActu    double,
    in estadoCredi  tinyint
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select documentoUsuario from tblCredito where documentoUsuario=documentoUsuar)) then
		set msg="Este usuario ya tiene un credito activo.";
		select msg as Respuesta;
       	else
		insert into tblCredito (documentoUsuario,fechaInicio,saldoInicial,saldoActual,estadoCredito) 
                Values(documentoUsuar,fechaInic,saldoInic,saldoActu,estadoCredi);
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
    UPDATE `tblarticulo` SET `cantidadDisponible`=`cantidadDisponible` + `cantid`,`precioCompra`= `precioArticu` WHERE `idArticulo` = `idArticu`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarDetalleCredito`(
    in idDetalleCredi      int,
    in idCredi      int,
    in idMovimien    int,
    in fechaDetal   datetime
 )
BEGIN
	insert into tblDetalleCredito (idCredito,idMovimiento,fechaDetalle) 
        Values(idCredi,idMovimien,fechaDetal);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarDetalleEstudiante`(
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
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarDetalleVenta`(
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
    UPDATE `tblarticulo` SET `cantidadDisponible`=`cantidadDisponible` - `cantid`,`precioVenta`= `precioArticu` WHERE `idArticulo` = `idArticu`;
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
    in documentoAcudien varchar(20)
)
BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `spIngresarEstudianteApartirDePreinscrito`(
    in documentoUsuar   varchar(20),
    in fechaNacimien    DATE,
    in nombreUsuar      varchar(30),
    in apellidoUsuar    varchar(30),
    in emailUsuar       varchar(50),
    in passwo           varchar(45),
    in estadoUsuar      int,
    in documentoAcudien varchar(20)
)
BEGIN
declare msg varchar(50);
IF (exists(SELECT `documentoUsuario` FROM `tblusuario` WHERE `documentoUsuario` = `documentoUsuar` and `idrol` = 3)) then
		set msg= CONVERT(CONCAT('Este cliente ya esta registrado como estudiante') using utf8);
		select msg as mensaje, 'error' as tipo;    
ELSE
    UPDATE tblusuario 
        SET `fechaNacimiento` = `fechaNacimien`,
            `nombreUsuario` = `nombreUsuar`,
            `apellidoUsuario` = `apellidoUsuar`,
            `emailUsuario` = `emailUsuar`,
            password = passwo,
            `estadoUsuario` = `estadoUsuar`,
            idrol  = 3,
            `idDetalleUsuario` = (SELECT MAX(idDetalleUsuario) FROM `tbldetalleusuario`)
        WHERE `documentoUsuario` = `documentoUsuar`;
    UPDATE tblpreinscripcion
        SET estado = 0
        WHERE `documentoUsuario` = `documentoUsuar`;
    set msg= CONVERT(CONCAT('Este cliente se ha registrado como estudiante') using utf8);
    select msg as mensaje, 'success' as tipo;    
END IF;
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

declare msg varchar(60);    
    if (exists(SELECT `documentoUsuario` FROM `tblpreinscripcion` WHERE `documentoUsuario` = `documentoUsuar` and `idCurso` = `idCur`)) then
		set msg= CONVERT(CONCAT('Este cliente ya se ha preinscrito a ',(SELECT `nombreCurso` from tblcurso WHERE `idCurso` = idCur)) using utf8);
		select msg as mensaje, 'error' as tipo;
    elseif (exists(SELECT `documentoUsuario` FROM `tblpreinscripcion` WHERE `documentoUsuario` = `documentoUsuar` and estado = 1)) then
                set msg=CONVERT(CONCAT('Este cliente se encuentra preinscrito a ',(SELECT `nombreCurso` from tblcurso WHERE `idCurso` = (SELECT idCurso from `tblpreinscripcion` WHERE `documentoUsuario` = `documentoUsuar` and estado = 1))) using utf8);
		select msg as mensaje, 'error' as tipo;
    else
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
    set msg= CONVERT(CONCAT('Se ha registrado su preincripción a ',(SELECT `nombreCurso` from tblcurso WHERE `idCurso` = idCur)) using utf8);
    select msg as mensaje, 'success' as tipo;
end if;
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
    in totalVen             int,
    in documentoUsuar       VARCHAR(30) ,
    in nombreClien          VARCHAR(30),
    in documentoClien       VARCHAR(45)
 )
BEGIN
	declare msg varchar(40);    
	if (exists(select numeroAuxiliar from tblMovimiento where numeroAuxiliar=`idVen` and `idtipoMovimiento`=3)) then
		set msg="Esta venta ya existe";
		select msg as Respuesta;
	else
            insert into tblMovimiento 
            (
                `fechaMovimiento`, 
                `totalMovimiento`, 
                `idtipoMovimiento`, 
                `documentoUsuario`, 
                `nombreAuxiliar`,
                `numeroAuxiliar`,
                documentoAuxiliar
            ) VALUES (
                NOW(),
                `totalVen`,
                3,
                documentoUsuar,
                `nombreClien`,
                idVen,
                documentoClien
            );
	end if;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarAbonos`()
select a.idAbono, a.idCredito, a.valorAbono, a.fechaPago from tblAbono a inner join tblCredito c on(a.idCredito = c.idCredito) 
order by (a.idCredito, a.fechaPago)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarArticulos`()
BEGIN

SELECT * FROM tblarticulo INNER JOIN tblcategoriaarticulo ON tblarticulo.idCategoriaArticulo = tblcategoriaarticulo.idCategoriaArticulo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarArticulosConExistencias`()
BEGIN

SELECT * FROM tblarticulo INNER JOIN tblcategoriaarticulo ON tblarticulo.idCategoriaArticulo = tblcategoriaarticulo.idCategoriaArticulo where `cantidadDisponible`>0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarCreditos`()
select c.idCredito, c.documentoUsuario, c.fechaInicio, c.saldoInicial, c.saldoActual, c.estadoCredito 
from tblCredito c inner join tblUsuario u on(c.documentoUsuario=u.documentoUsuario)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarDetalleCreditos`()
select d.idDetalleCredito, d.idCredito, d.idMovimiento, d.fechaDetalle
from tblDetalleCredito d inner join tblCredito c on(d.idCredito = c.idCredito)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `spListarEmpresas`()
BEGIN

SELECT * FROM tblEmpresa;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblacudiente`
--

CREATE TABLE IF NOT EXISTS `tblacudiente` (
  `documentoAcudiente` varchar(20) NOT NULL,
  `nombreAcudiente` varchar(50) NOT NULL,
  `telefonoAcudiente` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  PRIMARY KEY (`documentoAcudiente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblarticulo`
--

CREATE TABLE IF NOT EXISTS `tblarticulo` (
  `idArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `idCategoriaArticulo` int(11) NOT NULL,
  `descripcionArticulo` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `cantidadDisponible` mediumint(9) NOT NULL,
  `precioCompra` int(11) NOT NULL,
  `precioVenta` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArticulo`),
  KEY `FK_tblArticulo_idCategoriaArticulo` (`idCategoriaArticulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `tblarticulo`
--

INSERT INTO `tblarticulo` (`idArticulo`, `idCategoriaArticulo`, `descripcionArticulo`, `cantidadDisponible`, `precioCompra`, `precioVenta`) VALUES
(1, 1, 'Vinilo Aguamarina', 104, 1200, 1300),
(2, 2, 'Vinilo Dorado', 60, 1400, 1500),
(3, 2, 'Vinilo Plateado', 73, 1400, 1500),
(4, 1, 'Pincel delgado', -172, 23423, 1300),
(5, 1, 'Vinilo plateado', 0, 3000, 3000),
(6, 1, 'Pincel Grueso', 0, 1200, 1500),
(7, 3, 'Panela', 10, 1000, 1200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcategoriaarticulo`
--

CREATE TABLE IF NOT EXISTS `tblcategoriaarticulo` (
  `idCategoriaArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCategoriaArticulo` varchar(50) NOT NULL,
  PRIMARY KEY (`idCategoriaArticulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `tblcategoriaarticulo`
--

INSERT INTO `tblcategoriaarticulo` (`idCategoriaArticulo`, `nombreCategoriaArticulo`) VALUES
(1, 'Pinceles'),
(2, 'Vinilos'),
(3, 'Categoria C'),
(4, 'Categoria D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcategoriacurso`
--

CREATE TABLE IF NOT EXISTS `tblcategoriacurso` (
  `idCategoriaCurso` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCategoriaCurso` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategoriaCurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `tblcategoriacurso`
--

INSERT INTO `tblcategoriacurso` (`idCategoriaCurso`, `nombreCategoriaCurso`) VALUES
(1, 'Seminario'),
(2, 'Primera Categoria'),
(3, 'Categoria B'),
(4, 'Categoria C'),
(5, 'Categoria D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblclase`
--

CREATE TABLE IF NOT EXISTS `tblclase` (
  `idClase` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `estadoPago` bit(1) NOT NULL DEFAULT b'0',
  `estadoAsistencia` bit(1) NOT NULL DEFAULT b'0',
  `creditoCreado` bit(1) NOT NULL DEFAULT b'0',
  `precioClase` float NOT NULL,
  `idCurso` int(11) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idClase`),
  KEY `idCurso_idx` (`idCurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

--
-- Volcado de datos para la tabla `tblclase`
--

INSERT INTO `tblclase` (`idClase`, `fecha`, `estadoPago`, `estadoAsistencia`, `creditoCreado`, `precioClase`, `idCurso`, `documentoUsuario`) VALUES
(1, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(2, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(3, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(4, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(5, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(6, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(7, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(8, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(9, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(10, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(11, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(12, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(13, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(14, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(15, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(16, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(17, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(18, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(19, NULL, b'0', b'0', b'0', 8000, 5, 'CC32466217'),
(26, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(27, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(28, NULL, b'0', b'0', b'0', 8000, 5, 'CC1017225673'),
(34, NULL, b'0', b'0', b'0', 24000, 8, 'CC1017225673'),
(35, NULL, b'0', b'0', b'0', 24000, 8, 'CC1017225673'),
(36, NULL, b'0', b'0', b'0', 24000, 8, 'CC1017225673'),
(37, NULL, b'0', b'0', b'0', 24000, 8, 'CC1017225673'),
(38, NULL, b'0', b'0', b'0', 24000, 8, 'CC1017225673'),
(39, NULL, b'0', b'0', b'0', 12000, 9, 'CE1017225673'),
(40, NULL, b'0', b'0', b'0', 12000, 9, 'CE1017225673'),
(41, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(42, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(43, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(44, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(45, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(46, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(47, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(48, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(49, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(50, NULL, b'0', b'0', b'0', 12000, 9, 'CC32466217'),
(51, NULL, b'0', b'0', b'0', 12000, 7, 'CC8101926'),
(52, NULL, b'0', b'0', b'0', 12000, 7, 'CC8101926'),
(53, NULL, b'0', b'0', b'0', 12000, 7, 'CC8101926'),
(54, NULL, b'0', b'0', b'0', 12000, 7, 'CC8101926'),
(55, NULL, b'0', b'0', b'0', 12000, 7, 'CC8101926');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcredito`
--

CREATE TABLE IF NOT EXISTS `tblcredito` (
  `idCredito` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` datetime DEFAULT CURRENT_TIMESTAMP,
  `saldoInicial` int(11) NOT NULL DEFAULT '0',
  `saldoActual` int(11) NOT NULL DEFAULT '0',
  `estadoCredito` tinyint(4) NOT NULL,
  `idCategoriaCredito` int(11) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idCredito`),
  KEY `fk_tblcredito_tblCategoriaCredito1_idx` (`idCategoriaCredito`),
  KEY `fk_tblcredito_tblusuario1_idx` (`documentoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblcurso`
--

CREATE TABLE IF NOT EXISTS `tblcurso` (
  `idCurso` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCurso` varchar(50) NOT NULL,
  `cantidadClases` int(11) NOT NULL,
  `horasPorClase` int(11) NOT NULL,
  `estadoCurso` int(11) NOT NULL,
  `descripcionCurso` varchar(100) DEFAULT NULL,
  `precioCurso` int(11) DEFAULT NULL,
  `idCategoriaCurso` int(11) NOT NULL,
  PRIMARY KEY (`idCurso`),
  KEY `fk_tblcurso_tblcategoriacurso1_idx` (`idCategoriaCurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `tblcurso`
--

INSERT INTO `tblcurso` (`idCurso`, `nombreCurso`, `cantidadClases`, `horasPorClase`, `estadoCurso`, `descripcionCurso`, `precioCurso`, `idCategoriaCurso`) VALUES
(5, 'Oleo', 15, 2, 1, 'Asd', 120000, 2),
(6, 'Loquesea', 1, 5, 1, 'Seminario de lo que sea', 120000, 1),
(7, 'Curso J', 10, 3, 1, 'La J es una letra, por la cual empieza mi nombre', 120000, 2),
(8, 'Curso 3', 5, 3, 1, 'asdasdasd', 120000, 3),
(9, 'Curso 4', 10, 10, 1, 'asdasdasd', 120000, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetallecredito`
--

CREATE TABLE IF NOT EXISTS `tbldetallecredito` (
  `idDetalleCredito` int(11) NOT NULL AUTO_INCREMENT,
  `idCredito` int(11) NOT NULL,
  `idMovimiento` int(11) NOT NULL,
  `fechaDetalle` date DEFAULT NULL,
  PRIMARY KEY (`idDetalleCredito`),
  KEY `idMovimiento` (`idMovimiento`),
  KEY `idCredito` (`idCredito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetallemovimiento`
--

CREATE TABLE IF NOT EXISTS `tbldetallemovimiento` (
  `idDetalleMovimiento` int(11) NOT NULL AUTO_INCREMENT,
  `idArticulo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `descuento` int(11) DEFAULT NULL,
  `totalDetalleMovimiento` int(11) NOT NULL,
  `idMovimiento` int(11) NOT NULL,
  `precioArticulo` int(11) NOT NULL,
  PRIMARY KEY (`idDetalleMovimiento`),
  KEY `FK_tblDetalleVenta_idArticulo` (`idArticulo`),
  KEY `fk_tbldetallemovimiento_tblMovimiento1_idx` (`idMovimiento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=43 ;

--
-- Volcado de datos para la tabla `tbldetallemovimiento`
--

INSERT INTO `tbldetallemovimiento` (`idDetalleMovimiento`, `idArticulo`, `cantidad`, `descuento`, `totalDetalleMovimiento`, `idMovimiento`, `precioArticulo`) VALUES
(19, 2, 10, 3, 20000, 14, 2000),
(20, 1, 10, 3, 25000, 14, 2500),
(21, 3, 30, 3, 30000, 14, 1000),
(22, 2, 9, 1, 18900, 15, 2100),
(23, 1, 30, 1, 36000, 16, 1200),
(24, 4, 30, 1, 36000, 17, 1200),
(26, 4, 12, 1, 18792, 20, 1566),
(27, 4, 234, 1, 5480982, 21, 23423),
(28, 1, 1, 1, 1200, 22, 1200),
(29, 2, 10, 1, 9000, 23, 900),
(30, 3, 1, 1, 3243, 24, 3243),
(31, 5, 30, 1, 30000, 25, 1000),
(32, 5, 30, 1, 90000, 26, 3000),
(33, 1, 10, 3, 12000, 27, 1200),
(34, 2, 10, 3, 14000, 27, 1400),
(35, 3, 10, 3, 14000, 27, 1400),
(36, 1, 30, 3, 39000, 28, 1300),
(37, 2, 30, 3, 45000, 28, 1500),
(38, 3, 30, 3, 45000, 28, 1500),
(39, 7, 10, 1, 10000, 29, 1000),
(40, 1, 10, 1, 13000, 30, 1300),
(41, 3, 10, 2, 15000, 31, 1500),
(42, 2, 10, 2, 15000, 31, 1500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbldetalleusuario`
--

CREATE TABLE IF NOT EXISTS `tbldetalleusuario` (
  `idDetalleUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `direccionUsuario` varchar(50) NOT NULL,
  `telefonoFijo` varchar(11) NOT NULL,
  `telefonoMovil` varchar(15) NOT NULL,
  `generoUsuario` bit(1) NOT NULL,
  `estadoBeneficiario` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`idDetalleUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `tbldetalleusuario`
--

INSERT INTO `tbldetalleusuario` (`idDetalleUsuario`, `direccionUsuario`, `telefonoFijo`, `telefonoMovil`, `generoUsuario`, `estadoBeneficiario`) VALUES
(1, 'Calle 24 # 65 e 25', '5860529', '3218016237', b'1', b'0'),
(2, 'Calle falsa 456', '56865245', '3218016237', b'1', b'0'),
(3, 'asdasd', '123123123', '123123123123', b'0', b'0'),
(4, 'ssdsdasdasd', '5861529', '3218016237', b'0', b'0'),
(5, 'dasdasdasdasd', '123123123', '123123213212', b'1', b'0'),
(6, 'Carrera 17 # 50A-45', '2218135', '3002339623', b'1', b'0');

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
  `emailContacto` varchar(50) NOT NULL,
  PRIMARY KEY (`nitEmpresa`)
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
  `idmodulo` int(11) NOT NULL AUTO_INCREMENT,
  `enlace` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`idmodulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

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
  `idrol` int(11) NOT NULL,
  PRIMARY KEY (`idmodulo`,`idrol`),
  KEY `idrol` (`idrol`)
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
  `idMovimiento` int(11) NOT NULL AUTO_INCREMENT,
  `fechaMovimiento` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `totalMovimiento` int(11) NOT NULL,
  `idtipoMovimiento` int(11) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL,
  `numeroAuxiliar` varchar(45) DEFAULT NULL,
  `nombreAuxiliar` varchar(45) DEFAULT NULL,
  `documentoAuxiliar` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMovimiento`),
  KEY `fk_tblMovimiento_tblTipoMovimiento1_idx` (`idtipoMovimiento`),
  KEY `fk_tblMovimiento_tblusuario1_idx` (`documentoUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=32 ;

--
-- Volcado de datos para la tabla `tblmovimiento`
--

INSERT INTO `tblmovimiento` (`idMovimiento`, `fechaMovimiento`, `totalMovimiento`, `idtipoMovimiento`, `documentoUsuario`, `numeroAuxiliar`, `nombreAuxiliar`, `documentoAuxiliar`) VALUES
(14, '2015-05-18 14:14:07', 75000, 1, '1017225673', '123', 'Sebas', NULL),
(15, '2015-05-18 14:26:23', 18900, 1, '1017225673', '256', 'Sebas', NULL),
(16, '2015-05-18 14:44:49', 36000, 1, '1017225673', 'asdasd', 'asd', NULL),
(17, '2015-05-27 12:52:18', 36000, 1, '1017225673', '234234', 'asdasd', NULL),
(20, '2015-06-01 17:02:26', 18792, 1, '1017225673', '1', 'tela', NULL),
(21, '2015-06-01 17:21:48', 5480982, 3, '1017225673', '1', 'Categoria asdasd', '567576576'),
(22, '2015-06-01 17:26:02', 1200, 3, '1017225673', '2', 'Sebas', '1017225673'),
(23, '2015-06-01 17:30:38', 9000, 3, '1017225673', '3', 'Lorenzo', '12312312'),
(24, '2015-06-01 17:32:47', 3243, 3, '1017225673', '4', 'vintage', '234234'),
(25, '2015-06-02 17:54:48', 30000, 1, '1017225673', '1231321', 'Locoloco', NULL),
(26, '2015-06-02 17:55:37', 90000, 3, '1017225673', '5', 'David', '12312321'),
(27, '2015-06-02 18:03:36', 55000, 3, '1017225673', '6', 'Juan', '1000'),
(28, '2015-06-02 18:08:52', 129000, 3, '1017225673', '7', '1234234', 'sdfsdfsdf'),
(29, '2015-06-09 13:40:44', 10000, 1, '1017225673', '123123', 'Asdasd', NULL),
(30, '2015-06-09 14:30:19', 13000, 3, '1017225673', '8', 'Juancito Montoya', 'CC1017225673'),
(31, '2015-06-09 14:34:22', 30000, 3, '1017225673', '9', 'Albert Arango', 'CC1017225674');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblpreinscripcion`
--

CREATE TABLE IF NOT EXISTS `tblpreinscripcion` (
  `idPreinscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `estado` bit(1) DEFAULT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `documentoUsuario` varchar(20) NOT NULL,
  `idCurso` int(11) NOT NULL,
  PRIMARY KEY (`idPreinscripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `tblpreinscripcion`
--

INSERT INTO `tblpreinscripcion` (`idPreinscripcion`, `estado`, `fecha`, `documentoUsuario`, `idCurso`) VALUES
(4, b'0', '2015-06-06 20:54:41', 'CC32466217', 6),
(5, b'0', '2015-06-07 18:15:39', 'CC94110325805', 7),
(6, b'0', '2015-06-09 13:35:01', 'CC8101926', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tblrol`
--

CREATE TABLE IF NOT EXISTS `tblrol` (
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(40) NOT NULL,
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

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
  `idSubsidio` int(11) NOT NULL AUTO_INCREMENT,
  `fechaAsignacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valorSubsidio` int(11) NOT NULL,
  `nitEmpresa` varchar(20) NOT NULL,
  `documentoUsuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idSubsidio`),
  KEY `fk_tblsubsidio_tblempresa1_idx` (`nitEmpresa`),
  KEY `fk_tblsubsidio_tblusuario1_idx` (`documentoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbltipomovimiento`
--

CREATE TABLE IF NOT EXISTS `tbltipomovimiento` (
  `idtipoMovimiento` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipoMovimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbltipomovimiento`
--

INSERT INTO `tbltipomovimiento` (`idtipoMovimiento`, `descripcion`) VALUES
(1, 'Compra a Proveedor'),
(2, 'Ingreso de producto propio'),
(3, 'Venta a cliente');

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
  `documentoAcudiente` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`documentoUsuario`),
  UNIQUE KEY `emailUsuario_UNIQUE` (`emailUsuario`),
  KEY `fk_tblestudiante_tblDetalleCliente1_idx` (`idDetalleUsuario`),
  KEY `fk_tblusuario_tblrol1_idx` (`idrol`),
  KEY `fk_tblusuario_tblacudiente1_idx` (`documentoAcudiente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tblusuario`
--

INSERT INTO `tblusuario` (`documentoUsuario`, `fechaNacimiento`, `nombreUsuario`, `apellidoUsuario`, `emailUsuario`, `password`, `estadoUsuario`, `idDetalleUsuario`, `idrol`, `documentoAcudiente`) VALUES
('1017225673', '1994-11-03', 'Juan Sebastián', 'Montoya Montoya', 'jsmontoya37@misena.edu.co', '123', 1, NULL, 1, NULL),
('CC1017225673', '1994-11-03', 'Juancito', 'Montoya', 'thejuansebas03@gmail.com', '123', 0, 1, 3, NULL),
('CC32466217', '1999-02-03', 'Maria Dolly', 'Montoya Puerta', 'micorreo@correo.com', '123', 0, 4, 3, NULL),
('CC8101926', '1984-01-06', 'David', 'Cano Arango', 'dcanoara@gmail.com', '123', 0, 6, 3, NULL),
('CC94110325805', '1990-03-02', 'Juan', 'Olla', 'nuevocorrep@correo.com', '123', 0, 5, 3, NULL),
('CE1017225673', '1990-03-09', 'Juanse', 'DeOlla', 'juansmm@outlook.com', '123', 0, 2, 3, NULL);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tblarticulo`
--
ALTER TABLE `tblarticulo`
  ADD CONSTRAINT `FK_tblArticulo_idCategoriaArticulo` FOREIGN KEY (`idCategoriaArticulo`) REFERENCES `tblcategoriaarticulo` (`idCategoriaArticulo`);

--
-- Filtros para la tabla `tblclase`
--
ALTER TABLE `tblclase`
  ADD CONSTRAINT `tblclase_ibfk_1` FOREIGN KEY (`idCurso`) REFERENCES `tblcurso` (`idCurso`);

--
-- Filtros para la tabla `tblcredito`
--
ALTER TABLE `tblcredito`
  ADD CONSTRAINT `fk_tblcredito_tblusuario1` FOREIGN KEY (`documentoUsuario`) REFERENCES `tblusuario` (`documentoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tblcurso`
--
ALTER TABLE `tblcurso`
  ADD CONSTRAINT `fk_tblcurso_tblcategoriacurso1` FOREIGN KEY (`idCategoriaCurso`) REFERENCES `tblcategoriacurso` (`idCategoriaCurso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tbldetallecredito`
--
ALTER TABLE `tbldetallecredito`
  ADD CONSTRAINT `tbldetallecredito_ibfk_1` FOREIGN KEY (`idMovimiento`) REFERENCES `tblmovimiento` (`idMovimiento`),
  ADD CONSTRAINT `tbldetallecredito_ibfk_2` FOREIGN KEY (`idCredito`) REFERENCES `tblcredito` (`idCredito`);

--
-- Filtros para la tabla `tbldetallemovimiento`
--
ALTER TABLE `tbldetallemovimiento`
  ADD CONSTRAINT `fk_tbldetallemovimiento_tblMovimiento1` FOREIGN KEY (`idMovimiento`) REFERENCES `tblmovimiento` (`idMovimiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_tblDetalleVenta_idArticulo` FOREIGN KEY (`idArticulo`) REFERENCES `tblarticulo` (`idArticulo`) ON DELETE CASCADE ON UPDATE CASCADE;

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
