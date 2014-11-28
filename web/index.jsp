<%-- 
    Document   : index
    Created on : 23-oct-2014, 11:17:43
    Author     : Sebastian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--
        Cabeza del documento
        Aqui se encuentran importadas las librerias en el siguiente orden:
        Primero importe bootstrap.min.css que es la que le da el responsive.
        Stylesheet.css son personalizaciones, en ella se pueden cambiar los colores de botones y demas.
        jquery contiene una libreria javascript que permite darle efectos a la pagina y algunas validaciones.
        bootstrap.min.js tiene metodos jquery para darle vida y movilidad a la pagina.
        javascript.js son librerias propias para darle algunos eventos a los botones.
    -->
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

        <!--
        Aqui se encuentra la barra superior, lo que se le llama navbar que es barra de navegacion, 
        en ella esta el login y los botones para navegar atravez del aplicativo.
        -->
        <div class="navbar navbar-inverse" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span> 
                </button>
                <!--
                Aqui el titulo de la barra de navegacion.
                -->
                <a href="#" class="navbar-brand">WorkStation</a>
            </div>
            <div class="collapse navbar-collapse">
                <!--
                Aqui los botones de navegacion.
                -->
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Inicio</a></li>
                    <li class=""><a href="nuestro.jsp">Nuestros Cursos</a></li>
                    <li class=""><a href="acerca.jsp">Acerca de Nosotros</a>
                    </li>              
                </ul>
                <!--
                Aqui se encuentra el login, tambien tiene para recuperar la contraseña.
                -->
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
                                        <div class="col-lg-12">
                                            <form action="index2.jsp" method="POST">
                                                <p class="text-center">
                                                    <label for="nom">Ingresa tu Nombre</label>
                                                    <input name="nom" id="nom" class="form-group" type="text"/></p>
                                                <p class="text-center">
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
                                                <a href="#" class="btn btn-primary btn-block btn-sm">Recuperar Contraseña</a>
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
        
        <!--
        Este contenedor tiene el contenido de la pagina, en este caso un carrusel que pasa productos de Stellarte Decoracion
        -->

        <div class="container-fluid">
            <div class="row col-md-10 col-md-offset-2">
                <div id="ejemplo_carrusel" class="carousel slide thumbnail col-md-10 " style="margin: 0 auto">
                    <!-- Indicadores circulares -->
                    <ol class="carousel-indicators">
                        <li data-target="#ejemplo_carrusel" data-slide-to="0" class="active"></li>
                        <li data-target="#ejemplo_carrusel" data-slide-to="1"></li>
                        <li data-target="#ejemplo_carrusel" data-slide-to="2"></li>
                    </ol>

                    <!-- Bloque para las imágenes -->
                    <div class="carousel-inner center">

                        <div class="item active">
                            <img class="imgIndex" src="public/vintage1.jpg">
                            <div class="carousel-caption">
                                <h3>Vintage</h3>
                                <p>Revistero</p>
                            </div>
                        </div>
                        <div class="item text-center">
                            <img class="imgIndex" src="public/tela1.JPG">
                            <div class="carousel-caption">
                                <h3>Tela</h3>
                                <p>Colcha de retazos</p>
                            </div>
                        </div>
                        <div class="item text-center">
                            <img class="imgIndex" src="public/country2.jpg">
                            <div class="carousel-caption">
                                <h3>Country</h3>
                                <p>Joyero</p>
                            </div>
                        </div>    
                    </div>

                    <!-- Controles -->
                    <a class="left carousel-control" href="#ejemplo_carrusel" data-slide="prev">
                        <span class="icon-prev"></span>
                    </a>
                    <a class="right carousel-control" href="#ejemplo_carrusel" data-slide="next">
                        <span class="icon-next"></span>
                    </a>
                </div>    
            </div>
        </div>
    </body>
</html>
