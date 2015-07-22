<%-- 
    Document   : matricula
    Created on : 23-oct-2014, 12:13:22
    Author     : Administrador
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/header.jspf" %>
<div class="container-fluid" style="height: 100%;">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            Gestión de Estudiantes
                        </a> 
                    </h3>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input class="btn btn-default btn-block" type="button" onclick="estudiante.registrar()" value="Registrar Estudiante">
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
                    <a href="#estudiantes" role="tab" data-toggle="tab">Listado de Estudiantes</a>
                </li>
                <li>
                    <a href="#matriculas" role="tab" data-toggle="tab">Listado de Matrículas</a>
                </li>
                <li>
                    <a href="#preinscritos" role="tab" data-toggle="tab">Listado de Preinscritos</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="estudiantes">
                    <table id="tblEstudiantes" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>                                
                                <th class="text-center">Documento</th>
                                <th class="text-center">Nombres</th>
                                <th class="text-center">Fecha Nacimiento</th>
                                <th class="text-center">Teléfono</th>
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Editar</th>
                                <th class="text-center">Matricular</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="matriculas">
                    <table id="tblMatriculas" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Cliente</th>
                                <th class="text-center">Curso</th>
                                <th class="text-center">Clases</th>
                                <th class="text-center">Clases Asistidas</th>                                
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Marcar Asistencia</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="preinscritos">
                    <table id="tblPreinscritos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Documento</th>
                                <th class="text-center">Cliente</th>
                                <th class="text-center">Curso</th>
                                <th class="text-center">Fecha Preinscripción</th>
                                <th class="text-center">Correo Cliente</th>
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Inscribir</th>
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

<%@include file="WEB-INF/jspf/modalEstudiante.jspf" %>
<%@include file="WEB-INF/jspf/modalAcudiente.jspf" %>
<%@include file="WEB-INF/jspf/modalMatricula.jspf" %>
<%@include file="WEB-INF/jspf/modalBeneficiario.jspf" %>
<%@include file="WEB-INF/jspf/footer.jspf" %>
