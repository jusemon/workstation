<%-- 
    Document   : servicios
    Created on : 6/05/2015, 12:30:57 PM
    Author     : Juan Montoya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%     if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
    };
%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/superior.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center">
                        Nuestros Cursos
                    </h4>
                </div>
                <div class="panel-body" id="cursosDisponibles">
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title text-center"> 
                        Nuestros Seminarios
                    </h4>
                </div>
                <div class="panel-body" id="seminariosDisponibles">

                </div>
            </div>
        </div>
    </div>
</div>
<script>
</script>
<%@include file="WEB-INF/jspf/imports.jspf" %>

