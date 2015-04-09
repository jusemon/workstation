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
    </head>
    <body>

        <!--
        Aqui se encuentra la barra superior, lo que se le llama navbar que es barra de navegacion, 
        en ella esta el login y los botones para navegar atravez del aplicativo.
        -->
        <%@include file="WEB-INF/jspf/superior.jspf" %>

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
                            <img class="imgIndex" src="public/images/vintage1.jpg">
                            <div class="carousel-caption">
                                <h3>Vintage</h3>
                                <p>Revistero</p>
                            </div>
                        </div>
                        <div class="item text-center">
                            <img class="imgIndex" src="public/images/tela1.JPG">
                            <div class="carousel-caption">
                                <h3>Tela</h3>
                                <p>Colcha de retazos</p>
                            </div>
                        </div>
                        <div class="item text-center">
                            <img class="imgIndex" src="public/images/country2.jpg">
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
        © 2015. Todos los derechos reservados.
    </body>
    <%@include file="WEB-INF/jspf/imports.jspf" %>

</html>
