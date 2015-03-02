<%-- 
    Document   : curso
    Created on : 23-oct-2014, 12:12:59
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario")==null) {
   response.sendRedirect("index.jsp");
}; %>
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

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="panel-group" id="accordion">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        Gestion de Cursos
                                    </a>
                                </h3>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <form id="form_Maestros" action="ControllerCurso" method="POST">
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
                                </form>

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
                                <form>
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
                                </form>
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
