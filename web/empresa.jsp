<%-- 
    Document   : empresa
    Created on : 31-oct-2014, 12:04:18
    Author     : Sebastian
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
                <div class="col-md-4 ">
                    <form id="form_estudiante" class="" action="" method="">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Formulario de Empresa
                                </h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtNit">
                                                Nit
                                            </label>
                                            <input name="txtNit" id="txtNombre" type="number" class="form-control" placeholder="Ejm: 98524567" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtNombre">
                                                Razón Social
                                            </label>
                                            <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: UNE S.A" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtApellido">
                                                Nombre de Contacto
                                            </label>
                                            <input name="txtApellido" id="txtApellido" type="text" class="form-control" placeholder="Ejm: Juan Montoya Montoya" required>
                                        </div>
                                    </div>
                                </div>                                
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtSeguridadSocial">
                                                Telefono de Contacto
                                            </label>
                                            <input name="txtEPS" id="txtDSeguridadSocial" type="text" class="form-control" placeholder="Ejm: 2145632" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtCelular">
                                                Celular Contacto
                                            </label>
                                            <input name="txtCelular" id="txtCelular" type="text" class="form-control" placeholder="Ejm: 321 801 62 37">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtDireccion">
                                                Email Contacto
                                            </label>
                                            <input name="txtDireccion" id="txtDireccion" type="text" class="form-control" placeholder="Ejm: comunicaciones@une.com" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="txtTelefono">
                                                Direccion Empresa
                                            </label>
                                            <input name="txtTelefono" id="txtTelefono" type="text" class="form-control" placeholder="Ejm: Cll 24 # 65 a 28" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="row">

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input data-toggle="modal" id="boton" class="btn btn-default btn-block" type="submit" name="action" value="Añadir">
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
                <div class="col-md-8">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active">
                            <a href="#matriculas" role="tab" data-toggle="tab">Listado de Empresas</a>
                        </li>
                        <li class="pull-right">
                            <input type="submit" class="btn glyphicon-search" value="Buscar"/>
                        </li>
                        <li class="pull-right">
                            <input type="search" value="98524567" class="form-control" />
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="matriculas">
                            <table id="tbSeminarios" class="table table-hover" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th class="text-center">Nit</th>
                                        <th class="text-center">Razón Social</th>
                                        <th class="text-center">Contacto</th>
                                        <th class="text-center">Telefono Contacto</th>
                                        <th class="text-center">Celular</th>
                                        <th class="text-center">Email</th>
                                        <th class="text-center">Dirección</th>
                                        <th class="text-center">Editar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-center">98524567</td>
                                        <td class="text-center">Une</td>
                                        <td class="text-center">Margarita de la Rosa</td>
                                        <td class="text-center">5861528</td>
                                        <td class="text-center">3136943958</td>
                                        <td class="text-center">comunicaciones@une.com</td>                                        
                                        <td class="text-center">Calle 25 # 65 a 28</td>
                                        <td class="text-center">
                                            <a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add("Estado")">
                                               <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
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
