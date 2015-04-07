<%-- 
    Document   : curso
    Created on : 23-oct-2014, 12:12:59
    Author     : Administrador
--%>

<%@page import="Controller.ControllerSeminario"%>
<%@page import="Controller.ControllerCategoriaCurso"%>
<%@page import="Controller.ControllerFicha"%>
<%@page import="Controller.ControllerCurso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%     if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
    };
    ControllerCurso controllerCurso = new ControllerCurso();
    ControllerFicha controllerFicha = new ControllerFicha();
    ControllerCategoriaCurso controllerCategoriaCurso = new ControllerCategoriaCurso();
    ControllerSeminario controllerSeminario = new ControllerSeminario();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>WorkStation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="public/css/stylesheet.css">
        <link rel="stylesheet" type="text/css" href="public/css/bootstrap.min.css">
        <link href="public/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/superior.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseZero">
                                        Gestion de Cursos
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseZero" class="panel-collapse collapse">
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
                                            <div class="form-group">
                                                <input class="btn btn-default btn-block" type="button" id="registrarCategoriaCurso" name="regCatCurso" value="Registrar Categoria">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        Gestion de Fichas
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupFicha" data-dismiss="modal" name="regCurso" value="Registrar Ficha">
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
                        <li class="">
                            <a href="#categoriaCursos" role="tab" data-toggle="tab">Categorias de los Cursos</a>
                        </li>
                        <li>
                            <a href="#fichas" role="tab" data-toggle="tab">Listado de Fichas</a>
                        </li>
                        <li>
                            <a href="#seminarios" role="tab" data-toggle="tab">Listado de Seminarios</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="cursos">
                            <table id="tblCursos" class="table table-hover tabla" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Estado</th>
                                        <th class="text-center">Consultar</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        out.print(controllerCurso.getTableCursos());
                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="categoriaCursos">
                            <table id="tblCategoriaCursos" class="table table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        out.print(controllerCategoriaCurso.getTableCategoriaCurso());
                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="fichas">
                            <table id="tblFichas" class="table table-hover tabla" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Cupos Disponibles</th>
                                        <th class="text-center">Precio</th>
                                        <th class="text-center">Fecha</th>
                                        <th class="text-center">Estado</th>                                        
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        out.print(controllerFicha.getTableFichas());
                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="seminarios">
                            <table id="tblSeminarios" class="table table-hover " cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Código</th>
                                        <th class="text-center">Nombre</th>
                                        <th class="text-center">Duración</th>
                                        <th class="text-center">Estado</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        out.print(controllerSeminario.getTableSeminario());
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--
            Emergente para registrar los cursos
        -->
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
                                                        <label for="ddlCategoria">
                                                            Categoria
                                                        </label>
                                                        <select name="ddlCategoria" id="ddlCategoria" class="form-control" required>
                                                            <%
                                                                out.print(controllerCategoriaCurso.getOptionsCategorias());
                                                            %>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
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
                                                        <label for="txtDescripcion">
                                                            Descripcion
                                                        </label>
                                                        <textarea rows="2" name="txtDescripcion" id="txtDescripcion" type="text"  class="form-control" placeholder="Ejm: El oleo es un curso dedicado al..." required></textarea>
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
                                                        <input class="btn btn-default btn-block" type="submit" name="action" value="Registrar">
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

        <!-- 
            Emergente para registrar Categorias de Cursos
        -->
        <div class="modal" id="miPopupCategoriaCurso">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">                               
                                <form id="form_categoriaCurso" action="ControllerCategoriaCurso" method="POST">
                                    <div class="panel">
                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Ingresar Categoria
                                                <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                            </h3>
                                        </div>
                                        <div class="panel-body">
                                            <input type="hidden" id="idCategoriaCurso" name="idCategoriaCurso"/>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtNombre">
                                                            Nombre Categoria Curso
                                                        </label>
                                                        <input name="txtNombre" id="txtNombreCategoriaCurso" type="text" class="form-control" placeholder="Ejm: Oleo" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-6">
                                                    <div class="form-group">
                                                        <input id="btnCategoriaCurso" class="btn btn-default btn-block" type="submit" name="action" value="Registrar">
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

        <!-- 
            Emergente para registrar Seminarios
        -->
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
                                                      <input type="hidden" id="idSeminario" name="idSeminario"/>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                            Nombre
                                                        </label>
                                                        <input name="txtNombre" id="txtNombreSeminario" type="text" class="form-control" placeholder="" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtDuracion">
                                                            Duracion (En horas)
                                                        </label>
                                                        <input name="txtDuracion" id="txtDuracion" type="text" class="form-control" placeholder="" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <%--<div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="txtPrecio">
                                                            Precio
                                                        </label>
                                                        <input name="txtPrecio" id="txtPrecio" type="text" class="form-control" placeholder="Ejm: Cinco"  required>
                                                    </div>
                                                </div>
                                            </div> --%>
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
                                                        <input  class="btn btn-default btn-block" id="btnRegistrarS" type="submit" name="action" value="Añadir">
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <input class="btn btn-primary btn-block" id="btnEditarS" type="submit" name="action" value="Editar">
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

        <!-- 
            Emergente para registrar Fichas
        -->                                           
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
                                                            <%
                                                                out.print(controllerFicha.getOptionsCursos());
                                                            %>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="dateFecha">
                                                            Fecha de Inicio
                                                        </label>
                                                        <input name="dateFecha" id="dateFecha" type="date" class="form-control" placeholder="Ejm: 500000" required>
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
                                                    <input  class="btn btn-default btn-block" type="submit" name="action" value="Registrar">
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

        <%@include file="WEB-INF/jspf/imports.jspf" %>
    </body>
</html>
