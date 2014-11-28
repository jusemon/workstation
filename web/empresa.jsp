<%-- 
    Document   : empresa
    Created on : 31-oct-2014, 12:04:18
    Author     : Sebastian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span> 
                </button>
                <a href="#" class="navbar-brand">WorkStation</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index2.jsp">Inicio</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrar Matriculas y Empresas
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="matricula.jsp">Gestion de Matriculas</a></li>
                            <li><a href="empresa.jsp">Gestion de Empresas</a></li>                     
                        </ul>
                    </li> 
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrar Fichas, Cursos y Seminarios
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="curso.jsp">Gestion de Cursos y Seminarios</a></li>
                            <li><a href="ficha.jsp">Gestion de Fichas</a></li>
                        </ul>
                    </li> 
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Compras y Ventas
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu active">
                            <li class="active"><a href="articulo.jsp">Gestion de Articulos</a></li>
                            <li><a href="caja.jsp">Caja registradora</a></li>
                        </ul>
                    </li> 
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Opciones
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Link 2</a></li>
                        </ul>
                    </li>              
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-user"></span> 
                            <strong>Stella</strong>
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-login">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <p class="text-center">
                                                <span class="glyphicon glyphicon-user icon-size"></span>
                                            </p>
                                        </div>
                                        <div class="col-lg-8">
                                            <p class="text-left"><strong>Nombre Apellido</strong></p>
                                            <p class="text-left small">Stellarte@email.com</p>
                                            <p class="text-left">
                                                <a href="#" class="btn btn-primary btn-block btn-sm">Actualizar Datos</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="navbar-login navbar-login-session">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <p>
                                                <a href="#" class="btn btn-danger btn-block">Cerrar Sesion</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
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
