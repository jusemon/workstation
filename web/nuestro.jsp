<%-- 
    Document   : nuestro
    Created on : 23-oct-2014, 12:13:09
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
                    <li class=""><a href="index.jsp">Inicio</a></li>
                    <li class="active"><a href="nuestro.jsp">Nuestros Cursos</a></li>
                    <li class=""><a href="acerca.jsp">Acerca de Nosotros</a>
                    </li>              
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-user"></span> 
                            <strong>Aun no has iniciado sesion</strong>
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
                                            <form action="#" method="POST">
                                                <p class="text-left">
                                                    <label for="nom">Ingresa tu Nombre</label>
                                                    <input name="nom" id="nom" class="form-group" type="text"/></p>
                                                <p class="text-left small">
                                                    <label for="pass">Ingresa tu Contraseña</label>
                                                    <input name="pass" id="pass" class="form-group" type="password"/></p>
                                                <p class="text-left">
                                                    <input type="submit" name="Action" value="Iniciar Sesion" class="btn-success btn-block btn"/>
                                                </p>
                                            </form>
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
                                            <form action="#" method="POST">
                                                <input type="submit" name="Action" class="btn btn-primary btn-block btn-sm" value="Recuperar Contraseña"/>
                                            </form>
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
            <div class="row col-md-10 col-md-offset-2">

            </div>            
        </div>
    </body>
</html>
