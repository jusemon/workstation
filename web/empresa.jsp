<%-- 
    Document   : empresa
    Created on : 31-oct-2014, 12:04:18
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/header.jspf" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Gestión de Empresa
                    </h3>
                </div>
                <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input class="btn btn-default btn-block" type="button" value="Registrar empresa" onclick="empresa.registrar()">
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
                    <a href="#empresa" role="tab" data-toggle="tab">Listado de Empresas</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="empresa">
                    <table id="tblEmpresas" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">NIT</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Dirección</th>
                                <th class="text-center">Nombre contacto</th>
                                <th class="text-center">Teléfono contacto</th>
                                <th class="text-center">Correo electrónico</th>
                                <th class="text-center">Editar</th>
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

<%@include file="WEB-INF/jspf/modalEmpresa.jspf" %>
<%@include file="WEB-INF/jspf/footer.jspf" %>
