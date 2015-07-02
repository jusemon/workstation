<%-- 
    Document   : index
    Created on : 23-oct-2014, 11:17:43
    Author     : Sebastian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/header.jspf" %>
<!--
Este contenedor tiene el contenido de la pagina, en este caso un carrusel que pasa productos de Stellarte Decoracion
-->
<div class="container-fluid" style="height: 100%;">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div id="ejemplo_carrusel" class="carousel slide" style="">
                <!-- Indicadores circulares -->
                <ol class="carousel-indicators">
                    <li data-target="#ejemplo_carrusel" data-slide-to="0" class="active"></li>
                    <li data-target="#ejemplo_carrusel" data-slide-to="1"></li>
                    <li data-target="#ejemplo_carrusel" data-slide-to="2"></li>
                </ol>

                <!-- Bloque para las imágenes -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img class="imgIndex" src="public/images/ceramica.JPG" alt="300px">
                        <div class="carousel-caption">
                            <h3>Ceramica</h3>
                            <p></p>
                        </div>
                    </div>
                    <div class="item text-center">
                        <img class="imgIndex" src="public/images/marmolina.JPG" alt="300px">
                        <div class="carousel-caption">
                            <h3>Marmilona</h3>
                            <p></p>
                        </div>
                    </div>
                    <div class="item text-center">
                        <img class="imgIndex" src="public/images/vintage1.JPG" alt="300px">
                        <div class="carousel-caption">
                            <h3>Vintage</h3>
                            <p></p>
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
</div>
<%@include file="WEB-INF/jspf/footer.jspf" %>
