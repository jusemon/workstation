<%-- 
    Document   : index
    Created on : 24-oct-2014, 12:08:03
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
        <div class="row">
            <div class="col-md-12">
                <div class="alert-success">
                    <div class="text-center text-success"><h3><label>Bienvenido al sistema WorkStation</label></h3></div>
                </div>
            </div>
        </div>
    </body>
</html>
