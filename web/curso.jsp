<%-- 
    Document   : curso
    Created on : 23-oct-2014, 12:12:59
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
    };%>
<!DOCTYPE html>
<html>
    <head>
        <title>WorkStation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="public/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="public/css/stylesheet.css">
        <script type="text/javascript" src="public/js/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="public/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="public/js/javascript.js"></script>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/superior.jspf" %>

        <div class="modal" id="miPopupCurso">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form id="form_Maestros" action="ControllerCurso" method="POST">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Ingresar Curso
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombre">
                                                            Nombre Curso
                                                        </label>
                                                        <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: Oleo" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="dateDuracion">
                                                            Duración (En dias)
                                                        </label>
                                                        <input name="dateDuracion" id="dateDuracion" type="number" class="form-control" placeholder="Ejm: 10" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="ddlEstado">
                                                            Estado
                                                        </label>
                                                        <select name="ddlEstado" id="ddlEstado" class="form-control" required>
                                                            <option value="1">Activo</option>
                                                            <option value="0">Inactivo</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel-footer">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <input class="btn btn-default btn-block" type="submit" name="action" value="Añadir">
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <input class="btn btn-primary btn-block" type="submit" name="action" value="Editar">
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

        <div class="modal" id="miPopupSeminario">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form id="form_Maestros" action="ControllerSeminario" method="POST">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Gestion de Seminarios
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombre">
                                                            Nombre
                                                        </label>
                                                        <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: Vinilos" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombre">
                                                            Duracion (En horas)
                                                        </label>
                                                        <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: Vinilos" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombre">
                                                            Precio
                                                        </label>
                                                        <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: Vinilos" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="ddlEstado">
                                                            Estado
                                                        </label>
                                                        <select name="ddlEstado" id="ddlEstado" class="form-control" required>
                                                            <option value="1">Activo</option>
                                                            <option value="0">Inactivo</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
                                            <div class="row">
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
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="miPopupFicha">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form action="ControllerFicha" method="POST">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Crear Ficha
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="idCurso">
                                                            Curso
                                                        </label>
                                                        <select name="idCurso" id="ddlEstado" class="form-control" required>
                                                            <option value="0">Seleccionar... </option>
                                                            <option value="1">Oleo</option>
                                                            <option value="2">Patchwork</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtCupos">
                                                            Cupos disponibles
                                                        </label>
                                                        <input name="txtCupos" id="txtCupos" class="form-control" placeholder="Ejm: 15" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtPrecio">
                                                            Precio
                                                        </label>
                                                        <input name="txtPrecio" id="txtPrecio" type="number" class="form-control" placeholder="Ejm: 500000" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" value="" id="fecha" name="fecha">
                                        </div>
                                        <div class="panel-footer">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input  class="btn btn-default btn-block" type="submit" name="action" value="Aceptar">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input class="btn btn-primary btn-block" type="submit" name="action" value="Editar">
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

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        Gestionar Cursos y Fichas
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupCurso" data-dismiss="modal" name="regCurso" value="Registrar Curso">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-group" id="accordion">
                                                <div class="panel panel-default">
                                                    <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Curso" data-parent="#accodion" href="#collapseConsultaCurso"/>                                                           
                                                </div>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapseConsultaCurso">
                                                <div class="panel-body">
                                                    <form action="ControllerCurso" method="POST">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="form-group">
                                                                    <label for="nombreCurso">
                                                                        Ingresa el nombre
                                                                    </label>
                                                                    <input name="nombreCurso" id="nombreCurso" type="text" class="form-control" placeholder="Ejm: Oleo" required>
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
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupFicha" data-dismiss="modal" name="regCurso" value="Registrar Ficha">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-group" id="accordion">
                                                <div class="panel panel-default">
                                                    <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Ficha" data-parent="#accodion" href="#collapseConsultaFicha"/>                                                           
                                                </div>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapseConsultaFicha">
                                                <div class="panel-body">
                                                    <form action="ControllerFicha" method="POST">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="form-group">
                                                                    <label for="numeroFicha">
                                                                        Ingresa el numero de la ficha
                                                                    </label>
                                                                    <input name="numeroFicha" id="numeroFicha" type="number" class="form-control" placeholder="Ejm: 629256" required>
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
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                        Gestion de Seminarios
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupSeminario" data-dismiss="modal" name="regSeminario" value="Registrar Seminario">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel-group" id="accordion">
                                                <div class="panel panel-default">
                                                    <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Seminario" data-parent="#accodion" href="#collapseConsultaSeminario"/>                                                           
                                                </div>
                                            </div>
                                            <div class="panel-collapse collapse" id="collapseConsultaSeminario">
                                                <div class="panel-body">
                                                    <form action="ControllerSeminario" method="POST">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="form-group">
                                                                    <label for="nombreSeminario">
                                                                        Ingresa el nombre
                                                                    </label>
                                                                    <input name="nombreSeminario" id="nombreSeminario" type="text" class="form-control" placeholder="Ejm: Seminario de Patchwork" required>
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
                                <div class="panel-footer">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active">
                            <a href="#cursos" role="tab" data-toggle="tab">Listado de Cursos</a>
                        </li>
                        <li>
                            <a href="#fichas" role="tab" data-toggle="tab">Listado de Fichas</a>
                        </li>
                        <li>
                            <a href="#seminarios" role="tab" data-toggle="tab">Listado de Seminarios</a>
                        </li>
                        <li class="pull-right">
                            <input type="submit" class="btn glyphicon-search" value="Buscar"/>
                        </li>
                        <li class="pull-right">
                            <input type="search" value="Lorem Ipsum" class="form-control" />
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="cursos">
                            <table id="example" class="table table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Estado</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-center">00001</td>
                                        <td class="text-center">PatchWork</td>
                                        <td class="text-center"><a class="btn-sm btn-success btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                                                   <span class="glyphicon glyphicon-ok"></span></a>
                                        </td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                                                   <span class="glyphicon glyphicon-pencil"></span></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="text-center">00002</td>
                                        <td class="text-center">Oleo</td>
                                        <td class="text-center"><a class="btn-sm btn-danger btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                                                   <span class="glyphicon glyphicon-remove"></span></a>
                                        </td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                                                   <span class="glyphicon glyphicon-pencil"></span></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="fichas">
                            <table id="example" class="table table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Curso</th>
                                        <th class="text-center">Cupos Disponibles</th>
                                        <th class="text-center">Precio</th>
                                        <th class="text-center">Fecha</th>
                                        <th class="text-center">Estado</th>                                        
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-center">00001</td>
                                        <td class="text-center">PatchWork</td>
                                        <td class="text-center">21</td>
                                        <td class="text-center">300.000</td>                                        
                                        <td class="text-center">15-sep-1984</td>
                                        <td class="text-center"><a class="btn-sm btn-success btn-block " href="javascript:void(0)"  onclick="add('Estado')">
                                                <span class="glyphicon glyphicon-ok"></span></a>
                                        </td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add('Estado')">
                                                <span class="glyphicon glyphicon-pencil"></span></a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="text-center">00002</td>
                                        <td class="text-center">Oleo</td>
                                        <td class="text-center">15</td>
                                        <td class="text-center">250.000</td>
                                        <td class="text-center">17-oct-2014</td>                                        
                                        <td class="text-center"><a class="btn-sm btn-danger btn-block " href="javascript:void(0)"  onclick="add('Estado')">
                                                <span class="glyphicon glyphicon-remove"></span></a>
                                        </td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add('Estado')">
                                                <span class="glyphicon glyphicon-pencil"></span></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="seminarios">
                            <table id="tbSeminarios" class="table table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Duración</th>
                                        <th class="text-center">Precio</th>
                                        <th class="text-center">Estado</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-center">01100</td>
                                        <td class="text-center">Nueva tecnica de Pincel</td>
                                        <td class="text-center">4 horas</td>
                                        <td class="text-center">50.000</td>                                        
                                        <td class="text-center"><a class="btn-sm btn-success btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                                                   <span class="glyphicon glyphicon-ok"></span></a>
                                        </td>
                                        <td class="text-center"><a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                                                   <span class="glyphicon glyphicon-pencil"></span></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
