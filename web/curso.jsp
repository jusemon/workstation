<%-- 
    Document   : curso
    Created on : 23-oct-2014, 12:12:59
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/header.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseZero">
                                Gestión de Cursos
                            </a>
                        </h4>
                    </div>
                    <div id="collapseZero" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="curso.registrar()" value="Registrar Curso">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="categoriaCurso.registrar()" value="Registrar Categoría">
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
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                Gestión de Seminarios
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="seminario.registrar()" value="Registrar Seminario">
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
        <div class="col-md-9">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#cursos" role="tab" data-toggle="tab">Listado de Cursos</a>
                </li>
                <li class="">
                    <a href="#categoriaCursos" role="tab" data-toggle="tab">Categorías de los Cursos</a>
                </li>
                <li>
                    <a href="#seminarios" role="tab" data-toggle="tab">Listado de Seminarios</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="cursos">
                    <table id="tblCursos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="categoriaCursos">
                    <table id="tblCategoriaCursos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="seminarios">
                    <table id="tblSeminarios" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Estado</th>
                                 <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="WEB-INF/jspf/modalCurso.jspf" %>
<%@include file="WEB-INF/jspf/modalCategoriaCurso.jspf" %>
<%@include file="WEB-INF/jspf/modalDetalleSeminario.jspf" %>
<%@include file="WEB-INF/jspf/modalAsistenteSeminario.jspf" %>
<%@include file="WEB-INF/jspf/footer.jspf" %>
