<%-- 
    Document   : operario
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
                            Gestión de Operarios
                        </a> 
                    </h3>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input class="btn btn-default btn-block" type="button" onclick="operario.registrar()" value="Registrar Operario">
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
                    <a href="#operarios" role="tab" data-toggle="tab">Listado de Operarios</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="operarios">
                    <table id="tblOperarios" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>                                
                                <th class="text-center">Documento</th>
                                <th class="text-center">Nombres</th>
                                <th class="text-center">Correo</th>
                                <th class="text-center">Teléfono</th>
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
<%@include file="WEB-INF/jspf/modalOperario.jspf" %>
<%@include file="WEB-INF/jspf/footer.jspf" %>