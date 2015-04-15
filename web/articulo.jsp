<%-- 
    Document   : articulo
    Created on : 23-oct-2014, 12:13:31
    Author     : Administrador
--%>
<%@page import="Controller.ControllerArticulo"%>
<%@page import="Controller.ControllerCategoriaArticulo"%>
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
        <link href="public/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/superior.jspf" %>

        <%            Controller.ControllerCategoriaArticulo controllerCategoriaArticulo = new ControllerCategoriaArticulo();
            Controller.ControllerArticulo controllerArticulo = new ControllerArticulo();
        %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        Gestion de Articulos
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <form id="form_articulo" action="ControllerArticulo" method="POST" >
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupArticulo" data-dismiss="modal" name="regArticulo" value="Registrar Articulo">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="panel-group" id="accordion">
                                                    <div class="panel panel-default">
                                                        <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Articulo" data-parent="#accodion" href="#collapseConsultaArticulo"/>                                                           
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapseConsultaArticulo">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="nombreBusqueda">
                                                                    Ingresa el nombre
                                                                </label>
                                                                <input name="nombreBusqueda" id="nombreArticulo" type="text" class="form-control" placeholder="Ejm: Pincel" required>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <button type="submit" name="action" value="Consultar" class=" btn btn-default btn-block" >
                                                                    <span class="glyphicon glyphicon-search "></span>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                        Gestion de Categorias
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <form id="form_categoria" class="" action="ControllerCategoriaArticulo" method="POST">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <input data-toggle="modal" class="btn btn-default btn-block" type="button" id="registrarCategoriaArticulo" name="regCategoria" value="Registrar">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active">
                            <a href="#articulos" role="tab" data-toggle="tab">Listado de Articulos</a>
                        </li>
                        <li>
                            <a href="#categorias" role="tab" data-toggle="tab">Listado de Categorias</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="articulos">
                            <table id="tblArticulos" class="table table-striped table-bordered tabla" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Codigo</th>
                                        <th class="text-center">Categoria</th>
                                        <th class="text-center">Descripcion</th>
                                        <th class="text-center">Cantidad</th>                                        
                                        <th class="text-center">Precio</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        out.print(controllerArticulo.getTableArticulo());
                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="categorias">
                            <table id="tblCategoriaArticulos" class="table table-striped table-bordered tabla" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody> 
                                    <%
                                        out.print(controllerCategoriaArticulo.getTableCategoriaArticulo());
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                
        <div class="modal" id="miPopupArticulo">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="ControllerArticulo" method="POST">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Ingresar Articulo
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <input type="hidden" name="idArticulo" id="idArticulo"/>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombreArticulo">
                                                            Descripcion Articulo:
                                                        </label>
                                                        <input name="txtDescripcion" id="txtNombreArticulo" type="text" class="form-control" placeholder="Ejm: Vinilo Rojo" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtPrecio">
                                                            Precio:
                                                        </label>
                                                        <input name="txtPrecio" id="txtPrecioArticulo" type="number" class="form-control" placeholder="Ejm: 10000" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtCantidad">
                                                            Cantidad disponible:
                                                        </label>
                                                        <input name="txtCantidad" id="txtCantidadArticulo" type="text" class="form-control" placeholder="Ejm: 30" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="idCategoria">
                                                            Categoria:
                                                        </label>
                                                        <select name="idCategoria" id="idCategoriaArticulo" class="form-control" required>
                                                            <option value="">Seleccionar...</option>
                                                            <%
                                                                out.print(controllerCategoriaArticulo.getOptionsCategorias());
                                                            %>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
                                            <div class="col-md-offset-3 col-md-6">
                                                <div class="form-group">
                                                    <input id="btnArticulo"  class="btn btn-default btn-block" type="submit" name="action" value="Registrar">
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
                                                        
        <div class="modal" id="miPopupCategoriaArticulo">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form method="POST" action="ControllerCategoriaArticulo">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Ingresar Categoria
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                 <input type="hidden" id="idCategoriaArticulo" name="idCategoriaArticulo"/>
                                                <div class="col-md-12">                                        
                                                    <div class="form-group">
                                                            Nombre
                                                        <input name="txtNombre" id="txtNombreCategoriaArticulo" type="text" class="form-control" placeholder="Ejm: Vinilos" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-6">
                                                    <div class="form-group">
                                                        <input  class="btn btn-default btn-block" id="btnCategoriaArticulo" type="submit" name="action" value="Registrar">
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
        <%@include file="WEB-INF/jspf/imports.jspf" %>
    </body>
</html>
