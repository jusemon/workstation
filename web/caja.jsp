<%-- 
    Document   : caja
    Created on : 23-oct-2014, 12:19:52
    Author     : Sebastian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario")==null) {
   response.sendRedirect("index.jsp");
}; %>
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
        <link rel="stylesheet" type="text/css" href="public/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="public/css/stylesheet.css">
    </head>
    <body>
       
        <!--
        Popup Detalle Compra
        
        Los Popup son ventanas emergentes que estan formadas por la clase modal, a su vez esta clase
        tiene categorias, en este caso este es un modal-dialog que permite ingresar informacion al usuario
        el modal tiene un contenido que es el modal-content y este contenido esta separado por una cabeza (modal-header)
        un cuerpo (modal-body) y un pie (modal-footer), en mi caso solo le puse el cuerpo y dentro de el puse el panel con la información
        referente al detalle de la compra
        
        El panel que se encuentra en el Popup Detalle compra, esta dividido en cabeza cuerpo y pie, la capeza a su vez tiene un titulo.
        dentro del cuerpo del panel se encuentra el formulario para agregar elementos y una tabla que muestra todos los articulos a comprar,
        finalmente en el pie se ponen los botones de aceptar y cancelar respectivamente.
        -->

        <div class="modal" id="miPopupCompra">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form>
                                    <div class="panel">

                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Detalle de Compra
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">

                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline pull-left">
                                                        <input class="form-control" type="search" placeholder="Ingrese la factura"/>
                                                        <div class="btn btn-primary form-control">
                                                            <span class="glyphicon glyphicon-folder-open" ></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group form-inline pull-right">
                                                        <input class="form-control" type="search"/>         
                                                        <div class="btn btn-primary form-control">
                                                            <span class="glyphicon glyphicon-search" ></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline pull-right">
                                                        <label for="txtNombre">
                                                            Agrege Compra
                                                        </label>
                                                        <select class="form-control" name="txtNombre">
                                                            <option value="000">...</option>
                                                            <option value="001">Pincel 3"      1200$</option>
                                                        </select>
                                                        <input type="button" class="form-control btn-default" value="Agregar"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <table id="tblArticulosCompra" class="table table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">Codigo</th>
                                                        <th class="text-center">Nombre</th>
                                                        <td class="text-center">Cantidad</td>
                                                        <th class="text-center">Precio</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="text-center">00001</td>
                                                        <td class="text-center">Vinilo Azul Mediano</td>
                                                        <td class="text-center">15</td>
                                                        <td class="text-center">1200</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                            </div>

                            <div class="panel-footer">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input  class="btn btn-default btn-block" type="submit" name="action" value="Añadir">
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


        <!--
        Popup Venta
        
        -->
        <div class="modal" id="miPopupVenta">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form>
                                    <div class="panel">


                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Detalle de Venta
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline pull-right">
                                                        <input class="form-control" type="search"/>         
                                                        <div class="btn btn-primary form-control">
                                                            <span class="glyphicon glyphicon-search" ></span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline pull-right">
                                                        <label for="txtNombre">
                                                            Agrege los articulos
                                                        </label>
                                                        <select class="form-control" name="txtNombre">
                                                            <option value="000">...</option>
                                                            <option value="001">Pincel 3"      1200$</option>
                                                        </select>
                                                        <input type="button" class="form-control btn-default" value="Agregar"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <table id="tblArticulosVenta" class="table table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">Codigo</th>
                                                        <th class="text-center">Nombre</th>
                                                        <td class="text-center">Cantidad</td>
                                                        <th class="text-center">Precio</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="text-center">00001</td>
                                                        <td class="text-center">Vinilo Azul Mediano</td>
                                                        <td class="text-center">15</td>
                                                        <td class="text-center">1200</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div class="col-md-12">
                                                <div class="col-sm-4 pull-right">
                                                    Total: 1200
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
        </div>
        <!--popup detalle de Venta -->
        <div class="modal" id="miPopupDetalleVenta">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form>
                                    <div class="panel">


                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Detalle de Venta
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline">
                                                        Nombre: Juan Montoya     
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-xs-6">
                                                    <div class="form-group form-inline">
                                                        Cedula: 123654    
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-xs-6">
                                                    <div class="form-group form-inline">
                                                        Teléfono: 3214587     
                                                    </div>
                                                </div>
                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline">
                                                        Fecha: 21/11/2014     
                                                    </div>
                                                </div>
                                            </div>



                                            <table id="tblArticulosVenta" class="table table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">Codigo</th>
                                                        <th class="text-center">Articulo</th>
                                                        <th class="text-center">Cantidad</th>
                                                        <th class="text_center">Precio Unidad</th>
                                                        <th class="text-center">Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="text-center">00001</td>
                                                        <td class="text-center">Vinilo Azul Mediano</td>
                                                        <td class="text-center">15</td>
                                                        <td class="text-center">1000</td>
                                                        <td class="text-center">15000</td>
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
                                                    <input  class="btn btn-default btn-block" id="boton1"type="button" name="action" value="Imprimir">
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
        <!--popup detalle de Compra-->
        <div class="modal" id="miPopupDetalleCompra">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form>
                                    <div class="panel">


                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Detalle de Compra
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-5 col-xs-5">
                                                    <div class="form-group form-inline">
                                                        Codigo de Compra: 0001    
                                                    </div>
                                                </div>

                                                <div class="col-md-7 col-xs-7">   
                                                    <div class="form-group form-inline">
                                                        Numero de Factura: 21112014     
                                                    </div> 

                                                </div>
                                                <div class="col-md-12 col-xs-12">
                                                    <div class="form-group form-inline">
                                                        Fecha: 21/11/2014     
                                                    </div>
                                                </div>
                                            </div>



                                            <table id="tblArticulosVenta" class="table table-hover" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th class="text-center">Codigo</th>
                                                        <th class="text-center">Articulo</th>
                                                        <th class="text-center">Cantidad</th>
                                                        <th class="text_center">Precio Unidad</th>
                                                        <th class="text-center">Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="text-center">00001</td>
                                                        <td class="text-center">Vinilo Azul Mediano</td>
                                                        <td class="text-center">15</td>
                                                        <td class="text-center">1000</td>
                                                        <td class="text-center">15000</td>
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
                                                    <input  class="btn btn-default btn-block" id="boton1"type="button" name="action" value="Imprimir">
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
    </div>
    <!--
    Aquí se encuantra la barra superior, lo que se le llama navbar que es barra de navegación, 
    en ella esta el login y los botones para navegar a traves del aplicativo.
    -->

    <%@include file="WEB-INF/jspf/superior.jspf" %>


    <!--
    Este contenedor tiene el contenido de la pagina, en este caso un accordion que contendra 
    un par de panel uno para la gestion de Compras, otro para la gestion de Ventas.
    -->

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <div class="panel-group" id="accordion">
                    <!--Gestión de Compras-->
                    <div class="panel panel-default">
                        <!--
                        Aqui el boton que desplegara la gestion de Compras
                        -->
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                    Gestionar Compras 
                                </a>
                            </h4>
                        </div>
                        <!--
                        Aqui el contenido de la gestion de Compras, en este caso un boton para registrar  una compra
                        y otro para consultar el registro de comrpas
                        -->                            
                        <div id="collapseOne" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupCompra" data-dismiss="modal" name="regCompra" value="Registrar Compra">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-group" id="accordion">
                                            <div class="panel panel-default">
                                                <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Compra" data-parent="#accodion" href="#collapseConsultaCompra"/>                                                           
                                            </div>
                                        </div>
                                        <div class="panel-collapse collapse" id="collapseConsultaCompra">
                                            <div class="panel-body">
                                                <form action="ControllerCompra" method="POST">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="dateFechaInicio">
                                                                    Fecha de Inicio 
                                                                </label>
                                                                <input name="dateFechaInicio" id="dateFechaInicio" type="date" class="form-control" placeholder="Ejm: 10/10/2014" required>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="dateFechaFinal">
                                                                    Fecha de Final 
                                                                </label>
                                                                <input name="dateFechaFinal" id="dateFechaFinal" type="date" class="form-control" placeholder="Ejm: 10/10/2014" required>
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
                    <!--Gestión de Ventas-->
                    <div class="panel panel-default">
                        <!--
                        Aqui el boton que desplegara la gestión Ventas
                        -->
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                    Gestion de Ventas
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
                                            <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupVenta" data-dismiss="modal" name="regVenta" value="Registrar Venta">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-group" id="accordion">
                                            <div class="panel panel-default">
                                                <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Venta" data-parent="#accodion" href="#collapseConsultaVenta"/>                                                           
                                            </div>
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
                        <a href="#compra" role="tab" data-toggle="tab">Listado de Compra</a>
                    </li>
                    <li>
                        <a href="#ventas" role="tab" data-toggle="tab">Listado de Ventas</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="compra">
                        <table id="example" class="table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="text-center">Codigo</th>
                                    <th class="text-center">Fecha</th>
                                    <th class="text-center">Total</th>
                                    <th class="text-center">Consultar</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr>
                                    <td class="text-center">001</td>
                                    <td class="text-center">21/11/2014</td>
                                    <td class="text-center">155000</td>
                                    <td class="text-center"><a class="btn-sm btn-primary btn-block " data-toggle="modal" data-target="#miPopupDetalleCompra" href="javascript:void(0)">
                                            <span class="glyphicon glyphicon-search"></span></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="ventas">
                        <table id="tbSeminarios" class="table table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="text-center">Código</th>
                                    <th class="text-center">Fecha</th>
                                    <th class="text-center">Total </th>                                
                                    <th class="text-center">Consultar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="text-center">0001</td>
                                    <td class="text-center">21/11/2014</td>
                                    <td class="text-center">56000</td>
                                    <td class="text-center"><a class="btn-sm btn-primary btn-block " data-toggle="modal" data-target="#miPopupDetalleVenta" href="javascript:void(0)">                                                <span class="glyphicon glyphicon-search"></span></a>
                                    </td>  
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function imprimir() {
            var d = document.getElementById("boton1");
            d.setAttribute("value", "Imprimir");
        }

    </script> 
</body>
<script type="text/javascript" src="public/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="public/js/bootstrap.min.js"></script>
<script type="text/javascript" src="public/js/javascript.js"></script>
</html>
