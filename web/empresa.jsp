<%-- 
    Document   : empresa
    Created on : 31-oct-2014, 12:04:18
    Author     : David
--%>
<%@page import="Controller.ControllerEmpresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
    };%>
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
        
        
        <%
            Controller.ControllerEmpresa controllerEmpresa = new ControllerEmpresa();
        %>
        

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4 ">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Formulario de Empresa
                            </h3>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <input data-toggle="modal" class="btn btn-default btn-block" type="button" data-target="#miPopupEmpresa" data-dismiss="modal" name="regEmpresa" value="Registrar Empresa">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="panel-group" id="accordion">
                                            <div class="panel panel-default">
                                                <input class="btn btn-default btn-block" data-toggle="collapse" value="Consultar Empresa" data-parent="#accodion" href="#collapseConsultaEmpresa"/>                                                           
                                            </div>
                                        </div>
                                        <div class="panel-collapse collapse" id="collapseConsultaEmpresa">
                                            <div class="panel-body">
                                                <form action="ControllerEmpresa" method="POST">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="nombreEmpresa">
                                                                    Ingresa el nombre
                                                                </label>
                                                                <input name="nombreEmpresa" id="nombreEmpresa" type="text" class="form-control" placeholder="Ejm: EPM" required>
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
                                            <div class="panel-footer">

                                            </div>
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
                            <a href="#empresas" role="tab" data-toggle="tab">Listado de Empresas</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="empresas">
                            <table id="tblEmpresas" class="table table-hover" cellspacing="0" width="100%">
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
                                    <%
                                        out.print(controllerEmpresa.getTableEmpresa());                                        
                                    %>
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
                                <form method="POST" action="ControllerEmpresa">
                                    <div class="panel">

                                        <div class="panel-heading estilo2">
                                            <h3 class="panel-title">
                                                Gestión de Empresas
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
                                                        <input name="txtNitEmpresa" id="txtNombre" type="number" class="form-control" placeholder="Ej: 123.456.789-0" required>
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
                                                        <input name="txtEmailContacto" id="txtEmailContacto" type="text" class="form-control" placeholder="Ejm: direccion@correo.com" required>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input  class="btn btn-default btn-block" type="submit" name="action" value="Guardar">
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
    </body>
    <%@include file="WEB-INF/jspf/imports.jspf" %>
</html>
