<%-- 
    Document   : empresa
    Created on : 31-oct-2014, 12:04:18
    Author     : David
--%>

<%@page import="Controller.ControllerEmpresa"%>
<%@page import="Controller.ControllerLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
    };%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/superior.jspf" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 ">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Gestión de Empresa
                    </h3>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
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
        <div class="col-md-8">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#empresa" role="tab" data-toggle="tab">Listado de Empresas</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="empresa">
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

<div class="modal" id="miPopupEmpresa">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">                               
                        <form id="formEmpresa" method="POST" action="ControllerEmpresa">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNitEmpresa">
                                                    NIT
                                                </label>
                                                <input name="txtNitEmpresa" id="txtNitEmpresa" type="text" class="form-control" placeholder="Ej: 123.456.789-0" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombreEmpresa">
                                                    Nombre
                                                </label>
                                                <input name="txtNombreEmpresa" id="txtNombreEmpresa" type="text" class="form-control" placeholder="Ej: UNE S.A" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtDireccionEmpresa">
                                                    Dirección
                                                </label>
                                                <input name="txtDireccionEmpresa" id="txtDireccionEmpresa" type="text" class="form-control" placeholder="Ej: Calle 1 # 2-34" required>
                                            </div>
                                        </div>
                                    </div>                                
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombreContacto">
                                                    Responsable
                                                </label>
                                                <input name="txtNombreContacto" id="txtNombreContacto" type="text" class="form-control" placeholder="Ej: David Cano Arango" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtTelefonoContacto">
                                                    Teléfono
                                                </label>
                                                <input name="txtTelefonoContacto" id="txtTelefonoContacto" type="text" class="form-control" placeholder="Ejm: 3218016237">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtEmailContacto">
                                                    Email Contacto
                                                </label>
                                                <input name="txtEmailContacto" id="txtEmailContacto" type="email" class="form-control" placeholder="Ejm: direccion@correo.com" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="col-md-offset-3 col-md-6">
                                        <div class="form-group">
                                            <input  class="btn btn-default btn-block" id="btnEmpresa" type="submit" onclick="empresa.myAjax($('#btnEmpresa').val())" name="action" value="Registrar">
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
