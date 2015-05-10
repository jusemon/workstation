<%-- 
    Document   : caja
    Created on : 23-oct-2014, 12:19:52
    Author     : Sebastian, David, Lorenzo
--%>
<%@page import="Controller.ControllerLogin"%>  
<%@page import="Controller.ControllerAbono"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--
        Cabeza del documento
        Aqui se encuentran importadas las librerias en el siguiente orden:
        Primero importe bootstrap.min.css que es la que le da el responsive.
        Stylesheet.css son personalizaciones, en ella se pueden cambiar los colores de botones y demas.
        jquery contiene una libreria javascript que permite darle efectos a la pagina y algunas validaciones.
        bootstrap.min.js tiene metodos jquery para darle vida y movilidad a la pagina.
        javascript.js son librerias propias para darle algunos eventos a los botones.
    -->
    <head>
        <title>WorkStation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="public/css/stylesheet.css">
        <link rel="stylesheet" type="text/css" href="public/css/bootstrap.min.css">
        <link href="public/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css"/>
        <link href="public/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <!--
        Aquí se encuentra la barra superior, lo que se le llama navbar que es barra de navegación, 
        en ella esta el login y los botones para navegar a través del aplicativo.
        -->

        <!--
        Este contenedor tiene el contenido de la pagina, en este caso un accordion que contendrá 
        un par de paneles, uno para la gestión de Compras, otro para la gestion de Ventas.
        -->

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <div class="panel-group" id="accordion">
                        <!--Gestión de Compras-->
                        <div class="panel panel-default">
                            <!--
                            Aqui el botón que desplegara la gestion de Compras
                            -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        Gestión de Compras 
                                    </a>
                                </h4>
                            </div>
                            <!--
                            Aqui el contenido de la gestion de Compras, en este caso un boton para registrar  una compra
                            y otro para consultar el registro de comrpas
                            -->                            
                            <div id="collapseOne" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input class="btn btn-default btn-block" type="button" value="Registrar Compra" onclick="compra.registrar()">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!--Gestión de Ventas-->
                        <div class="panel panel-default">
                            <!--
                            Aqui el boton que desplegara la gestión Ventas
                            -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                        Gestión de Ventas
                                    </a>
                                </h4>
                            </div>
                            <!--
                            Aqui el contenido de la gestion de Ventas, en este caso un boton para registrar  una Venta
                            y otro para consultar el registro de Ventas
                            -->                              
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupRegistroVenta" data-dismiss="modal" name="regVenta" value="Registrar Venta">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-group" id="accordion2">
                                                <div class="panel panel-default">
                                                    <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Facturas" data-parent="#accordion2" href="#collapseConsultaFactura"/>                                                           
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Gestión de Abonos-->
                        <div class="panel panel-default">
                            <!--
                            Aquí el botón que desplegará la gestión de Abonos
                            -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                        Gestión de Abonos
                                    </a>
                                </h4>
                            </div>
                            <!--
                            Aquí el contenido de la gestión de abonos, en este caso habrá un botón para registrar un abono
                            y otro para consultar abonos por crédito
                            -->                              
                            <div id="collapseThree" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupAbono" data-dismiss="modal" name="regAbono" value="Registrar Abono">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-group" id="accordion2">
                                                <div class="panel panel-default">
                                                    <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Abono por crédito" data-parent="#accordion2" href="#collapseConsultaAbono"/>                                                           
                                                </div>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapseConsultaAbono">
                                                <div class="panel-body">
                                                    <form action="ControllerAbono" method="POST">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="form-group">
                                                                    <label for="txtIdCredito">
                                                                        Id. del Crédito
                                                                    </label>
                                                                    <input name="txtIdCredito" id="txtIdCredito" type="text" class="form-control" placeholder="Ej: 12345" required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="form-group">
                                                                    <button type="button" class=" btn btn-default btn-block" >
                                                                        <span class="glyphicon glyphicon-search "></span>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Gestion de Diario de Caja-->
                        <div class="panel panel-default">
                            <!--
                            Aquí el botón que desplegara la gestión Diario de Caja
                            -->
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                                        Gestión de Diario de Caja
                                    </a>
                                </h4>
                            </div>
                            <!--
                            Aquí el contenido de la gestión de Diario de Caja, en este caso un boton para registrar  Movimientos diarios
                            
                            -->                              
                            <div id="collapseFour" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupDiario" data-dismiss="modal" name="regDiario" value="Registrar Diario">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-9">
                    <ul class="nav nav-tabs" role="tablist">

                        <li class="active">
                            <a href="#compra" role="tab" data-toggle="tab">Listado de Compras</a>
                        </li>
                        <li>
                            <a href="#ventas" role="tab" data-toggle="tab">Listado de Ventas</a>
                        </li>
                        <li>
                            <a href="#abonos" role="tab" data-toggle="tab">Listado de Abonos</a>                            
                        </li>                            
                        <li>
                            <a href="#diario" role="tab" data-toggle="tab">Diario de caja</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="compra">
                            <table id="tblCompra" class="table table-responsive table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Factura Proveedor</th>
                                        <th class="text-center">Nombre Proveedor</th>
                                        <th class="text-center">Fecha de Compra</th>
                                        <th class="text-center">Total Compra</th>
                                        <th class="text-center">Editar</th>

                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="ventas">
                            <table id="tblVentas" class="table table-responsive table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Número Venta</th>
                                        <th class="text-center">Fecha</th>
                                        <th class="text-center">Cod Cliente</th>
                                        <th class="text-center">Nombre Cliente</th>
                                        <th class="text-center">Total </th>                                
                                        <th class="text-center">Consultar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-center">0001</td>
                                        <td class="text-center">21/11/2014</td>
                                        <td class="text-center">0001</td>
                                        <td class="text-center">Manolo</td>
                                        <td class="text-center">56000</td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " data-toggle="modal" data-target="#miPopupDetalleVenta" href="javascript:void(0)"> <span class="glyphicon glyphicon-search"></span></a>
                                        </td>  
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="abonos">
                            <table id="tblAbono" class="table table-responsive table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Id. Abono</th>
                                        <th class="text-center">Id. Crédito</th>
                                        <th class="text-center">Valor Abono ($)</th>                                
                                        <th class="text-center">Fecha Pago</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="diario">
                            <table id="tblDiario" class="table table-hover tabla" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Total Compras</th>
                                        <th class="text-center">Total Ventas</th>
                                        <th class="text-center">Fecha</th>
                                        <th class="text-center">Total </th>                                
                                        <th class="text-center">Consultar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-center">0001</td>
                                        <td class="text-center">10000</td>
                                        <td class="text-center">30000</td>
                                        <td class="text-center">21/11/2014</td>
                                        <td class="text-center">30000</td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " data-toggle="modal" data-target="#miPopupBusqueda" href="javascript:void(0)">                                                <span class="glyphicon glyphicon-search"></span></a>
                                        </td>  
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--

Los Popup son ventanas emergentes que estan formadas por la clase modal, a su vez esta clase
tiene categorias, en este caso este es un modal-dialog que permite ingresar informacion al usuario
el modal tiene un contenido que es el modal-content y este contenido esta separado por una cabeza (modal-header)
un cuerpo (modal-body) y un pie (modal-footer), en mi caso solo le puse el cuerpo y dentro de el puse el panel con la información
referente al detalle de la compra

El panel que se encuentra en el Popup Detalle compra, esta dividido en cabeza cuerpo y pie, la capeza a su vez tiene un titulo.
dentro del cuerpo del panel se encuentra el formulario para agregar elementos y una tabla que muestra todos los articulos a comprar,
finalmente en el pie se ponen los botones de aceptar y cancelar respectivamente.
        -->
        <%--popup de compra--%> 
        <div class="modal" id="miPopupCompra">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form id="formCompra" action="ControllerCompra" method="POST">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                <label id="titulo"></label>
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <input type="hidden" name="FacturaProveedor" id="idArticulo"/>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtFacturaProveedor">
                                                            Nº Factura Proveedor:
                                                        </label>
                                                        <input name="txtFacturaProveedor" id="txtFacturaProveedor" type="text" class="form-control" placeholder="P23455" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombreProveedor">
                                                            Nombre Proveedor:
                                                        </label>
                                                        <input name="txtNombreProveedor" id="txtNombreProveedor" type="text" class="form-control" placeholder="Ejm: Pinturas Arcoíris" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="dateFechaCompra">
                                                            Fecha Compra:
                                                        </label>
                                                        <input name="dateFechaCompra" id="dateFechaCompra" type="text" class="form-control fecha" placeholder="Ejm: 10/04/2015" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupRegistrar" data-dismiss="modal" name="regVenta" value="Registrar Artículo">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtTotalCompra">
                                                            Total Compra:
                                                        </label>
                                                        <input name="txtTotalCompra" id="txtTotalCompra" type="number" class="form-control" placeholder="Ejm: 30000" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
                                            <div class="col-md-offset-3 col-md-6">
                                                <div class="form-group">
                                                    <input class="btn btn-default btn-block" id="btnCompra" type="submit" name="action"  value="Registrar" onclick="compra.myAjax($('#btnCompra').val())">
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--popup de Registrar Articulo de Venta -->
        <div class="modal" id="miPopupRegistrar">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form>
                                    <div class="panel">
                                        <div class="panel-body">
                                            <div class="panel-heading estilo2">
                                                <h3 class="panel-title">
                                                    Registrar Articulo
                                                    <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                                </h3>
                                            </div>
                                            <input type="hidden" name="idArticulo" id="idArticulo"/>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="idCategoria">
                                                            Categoría:
                                                        </label>
                                                        <select name="idCategoria" id="idCategoriaArticulo" class="form-control" required>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtCodigoArticulo">
                                                            Código Artículo:
                                                        </label>
                                                        <input name="txtCodigo" id="txtCodigo" type="number" class="form-control" placeholder="Ejm: 20000" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtDescripcionArticulo">
                                                            Descripción Artículo:
                                                        </label>
                                                        <!-- <input name="txtDescripcionArticulo" id="txtDescripcionArticulo"type="text" class="form-control" placeholder="Ejm: Vinilo Rojo" required>-->
                                                        <input name="txtDescripcionArticulo" id="txtDescripcionArticulo" type="search" class="form-control input-sm" placeholder="" aria-controls="tblArticulo">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtPrecio">
                                                            Cantidad:
                                                        </label>
                                                        <input name="txtCantidad" id="txtCantidad" type="number" class="form-control" placeholder="Ejm: 10000" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtPrecio">
                                                            Precio Unidad:
                                                        </label>
                                                        <input name="txtPrecioArticulo" id="txtPrecioArticulo" type="number" class="form-control" placeholder="Ejm: 10000" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtCantidad">
                                                            Descuento:
                                                        </label>
                                                        <input name="txtDescuento" id="txtDescuento" type="number" class="form-control" placeholder="Ejm: 30" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtCantidad">
                                                            Total:
                                                        </label>
                                                        <input name="txtTotal" id="txtDescuento" type="number" class="form-control" placeholder="Ejm: 300000" required>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="panel-footer">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input  class="btn btn-default btn-block" id="boton1" onclick="imprimir()" type="button" name="action" value="Añadir">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input class="btn btn-primary btn-block" type="button" data-dismiss="modal" name="cerrar" value="Cancelar">
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--popup RegistroVenta -->
        <div class="modal" id="miPopupRegistroVenta">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form>
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Ventas
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label for="txtNumero">
                                                                Código Cliente:
                                                            </label>
                                                            <input name="txtNumero" id="txtNumero" type="text" class="form-control" placeholder="00001" required>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label for="txtCliente">
                                                                Cliente:
                                                            </label>
                                                            <input name="txtCliente" id="txtCliente" type="text" class="form-control" placeholder="calle" required>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label for="dateFechaCompra">
                                                                Fecha :
                                                            </label>
                                                            <input name="dateFecha" id="dateFecha" type="text" class="form-control fecha" placeholder="Ejm: 10/04/2015" required>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupRegistrar" data-dismiss="modal" name="regVenta" value="Registrar Artículo">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="Líneas de detalle">
                                                            Líneas de detalle
                                                        </label>
                                                    </div>
                                                </div>

                                            </div>
                                            <table id="tblArticulosVenta" class="table table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">Código</th>
                                                        <th class="text-center">Artículo</th>
                                                        <th class="text-center">Cantidad</th>
                                                        <th class="text_center">Precio Unidad</th>
                                                        <th class="text_center">Descuento</th>
                                                        <th class="text_center">% I.V.A </th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="text-center">00001</td>
                                                        <td class="text-center">Vinilo Azul Mediano</td>
                                                        <td class="text-center">15</td>
                                                        <td class="text-center">1000</td>
                                                        <td class="text-center">0</td>
                                                        <td class="text-center">16</td>

                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div class="col-md-12">
                                                <div class="col-sm-4 pull-right">
                                                    Total: 15000
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel-footer">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input  class="btn btn-default btn-block" id="boton1"type="button" name="action" value="Registrar">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input class="btn btn-primary btn-block" type="button" data-dismiss="modal" name="cerrar" value="Cerrar">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Popup Abono-->
        <div class="modal" id="miPopupAbono">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form method="POST" action="ControllerAbono">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Registrar abono
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtIdCredito">
                                                            Id. del crédito
                                                        </label>
                                                        <input name="txtIdCredito" id="txtIdCredito" type="text" class="form-control" placeholder="Ej: 0001" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtValorAbono">
                                                            Valor abono ($)
                                                        </label>
                                                        <input name="txtValorAbono" id="txtValorAbono" type="number" class="form-control" placeholder="Ej: 25000" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="dateFechaPago">
                                                            Fecha de pago
                                                        </label>
                                                        <input name="dateFechaPago" id="dateFechaPago" type="date" class="form-control" placeholder="" required>
                                                    </div>
                                                </div>
                                            </div>                                
                                        </div>
                                        <div class="panel-footer">
                                            <div class="col-md-offset-3 col-md-6">
                                                <div class="form-group">
                                                    <input  class="btn btn-default btn-block" id="btnAbono" type="submit" name="action" value="Registrar">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Popup Diario de Caja-->
        <div class="modal" id="miPopupDiario">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form method="POST" action="ControllerCaja">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Diario de Caja
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">

                                            <div class="row">
                                                <div class="col-md-12">                                           
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="dateFechaSaldo">
                                                                    Fecha:
                                                                </label>
                                                                <input name="dateFechaSaldo" id="dateFechaSaldo" type="text" class="form-control fecha" placeholder="Ejm: 10/04/2015" required>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="txtSaldo">
                                                            Saldo Inicial
                                                        </label>
                                                        <input name="txtSaldo" id="txtIdSaldo" type="number" class="form-control" placeholder="Ej: 50000" required>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="ddlMovimientos">
                                                                    Movimientos
                                                                </label>
                                                                <select name="ddlMovimiento" id="ddlMovimiento" class="form-control" required>
                                                                    <option value="">Seleccionar...</option>
                                                                    <option value="CO" > Compra </option>
                                                                    <option value="VE" > Venta </option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                               
                                        </div>
                                        <div class="panel-footer">
                                            <div class="col-md-offset-3 col-md-6">
                                                <div class="form-group">
                                                    <input  class="btn btn-default btn-block" id="btnAbono" type="submit" name="action" value="Registrar">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Popup Busqueda de Diario de Caja-->
        <div class="modal" id="miPopupBusqueda">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form method="POST" action="ControllerCaja">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Busqueda Diario de Caja
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12">                                           
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="dateFechaInicial">
                                                                    Fecha Inicial:
                                                                </label>
                                                                <input name="dateFechaInicial" id="dateFechaInicial" type="text" class="form-control fecha" placeholder="Ejm: 10/04/2015" required>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="dateFechaFinal">
                                                                    Fecha Final:
                                                                </label>
                                                                <input name="dateFechaFinal" id="dateFechaFinal" type="text" class="form-control fecha" placeholder="Ejm: 10/04/2015" required>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>                               
                                            </div>
                                            <div class="panel-footer">
                                                <div class="col-md-offset-3 col-md-6">
                                                    <div class="form-group">
                                                        <input  class="btn btn-default btn-block" id="btnAbono" type="submit" name="action" value="Buscar">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <%@include file="WEB-INF/jspf/footer.jspf" %>
</html>
