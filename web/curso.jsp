<%-- 
    Document   : curso
    Created on : 23-oct-2014, 12:12:59
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/superior.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseZero">
                                Gestión de Cursos
                            </a>
                        </h4>
                    </div>
                    <div id="collapseZero" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="curso.registrar()" value="Registrar Curso">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="categoriaCurso.registrar()" value="Registrar Categoría">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a  data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                Gestión de Fichas
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input  class="btn btn-default btn-block" type="button" onclick="ficha.registrar()" value="Registrar Ficha">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                Gestión de Seminarios
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="seminario.registrar()" value="Registrar Seminario">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#cursos" role="tab" data-toggle="tab">Listado de Cursos</a>
                </li>
                <li class="">
                    <a href="#categoriaCursos" role="tab" data-toggle="tab">Categorías de los Cursos</a>
                </li>
                <li>
                    <a href="#fichas" role="tab" data-toggle="tab">Listado de Fichas</a>
                </li>
                <li>
                    <a href="#seminarios" role="tab" data-toggle="tab">Listado de Seminarios</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="cursos">
                    <table id="tblCursos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="categoriaCursos">
                    <table id="tblCategoriaCursos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="fichas">
                    <table id="tblFichas" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Cupos Disponibles</th>
                                <th class="text-center">Precio</th>
                                <th class="text-center">Fecha</th>
                                <th class="text-center">Estado</th>                                        
                                <th class="text-center">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="seminarios">
                    <table id="tblSeminarios" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Nombre</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Consultar</th>
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

<!--
    Emergente para registrar los cursos y seminarios
-->
<div class="modal" id="miPopupCurso">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">                               
                        <form id="formCurso" action="ControllerCurso" method="POST">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <input type="hidden" name="idCurso" id="idCurso"/>
                                    <input type="hidden" name="tipo" id="tipo"/>
                                    <div class="row" id="ContenedorCategoria">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="ddlCategoria"> 
                                                    Categoría
                                                </label>
                                                <select name="ddlCategoria" id="ddlCategoria" class="form-control" required>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombre">
                                                    Nombre Curso
                                                </label>
                                                <input name="txtNombre" id="txtNombreCurso" type="text" class="form-control" pattern="[ÁÉÍÓÚáéíóúañÑ-zA-Z ]{3,15}" title="Entre 3 y 15 letras y no se permiten numeros" placeholder="Ejm: Oleo" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtDescripcion">
                                                    Descripción
                                                </label>
                                                <textarea rows="2" name="txtDescripcion" id="txtDescripcionCurso"  class="form-control" pattern="{3,100}" title="Entre 3 y 100 letras" placeholder="Ejm: El oleo es un curso dedicado al..." required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtPrecio">
                                                    Precio
                                                </label>
                                                <input name="txtPrecio" id="txtPrecio" type="number" min="5000" max="100000" class="form-control" placeholder="Ejm: 100000" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtCantidadClases">
                                                    Cantidad de Clases
                                                </label>
                                                <input name="txtCantidadClases" id="txtCantidadClases" type="number" min="5" max="90" class="form-control" placeholder="Ejm: 10" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtCantidadHoras">
                                                    Cantidad de Horas (Por clase)
                                                </label>
                                                <input name="txtCantidadHoras" id="txtCantidadHoras" type="number" min="5" max="90" class="form-control" placeholder="Ejm: 10" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="ddlEstado">
                                                    Estado
                                                </label>
                                                <select name="ddlEstado" id="ddlEstado" class="form-control" required>
                                                    <option value="1">Activo</option>
                                                    <option value="0">Inactivo</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-6">
                                            <div class="form-group">
                                                <input class="btn btn-default btn-block" id="btnCurso" type="submit" name="action" onclick="curso.myAjax($('#btnCurso').val())">
                                            </div>
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
<!-- 
    Emergente para registrar Categorias de Cursos
-->
<div class="modal" id="miPopupCategoriaCurso">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">                               
                        <form id="form_categoriaCurso" action="ControllerCategoriaCurso" method="POST">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only" style=" color: #ffffff">Cerrar</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <input type="hidden" id="idCategoriaCurso" name="idCategoriaCurso"/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombre">
                                                    Nombre Categoría Curso
                                                </label>
                                                <input name="txtNombre" id="txtNombreCategoriaCurso" type="text" class="form-control"  pattern="[ÁÉÍÓÚáéíóúñÑa-zA-Z ]{3,15}" title="Entre 3 y 15 letras y no se permiten numeros" placeholder="Ejm: Oleo" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-6">
                                            <div class="form-group">
                                                <input id="btnCategoriaCurso" class="btn btn-default btn-block" type="submit" name="action" onclick="categoriaCurso.myAjax($('#btnCategoriaCurso').val())">
                                            </div>
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
<!-- 
    Emergente para registrar Fichas
-->                                           
<div class="modal" id="miPopupFicha">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="formFicha" action="ControllerFicha" method="POST">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <input type="hidden" id="idFicha" name="idFicha"/>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="idCursoFicha">
                                                    Curso
                                                </label>
                                                <select name="idCurso" id="idCursoFicha" class="form-control" required>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtCupos">
                                                    Cupos disponibles
                                                </label>
                                                <input type="number" name="txtCupos" id="txtCupos" class="form-control" placeholder="Ejm: 15" min="0" max="30" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtPrecioFicha">
                                                    Precio
                                                </label>
                                                <input name="txtPrecio" id="txtPrecioFicha" type="number" min="5000" max="100000" class="form-control" placeholder="Ejm: 100000" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="dateFechaFicha">
                                                    Fecha de Inicio
                                                </label>
                                                <div class="date">
                                                    <input name="dateFecha" id="dateFechaFicha" placeholder="Ejm: 19/05/2015" pattern="(0[1-9]|1[0-9]|3[01]).(0[1-9]|1[0-2]).([0-9]{4})" title="El formato de la fecha debe ser dd/mm/yyyy" type="text" class="form-control fecha" required>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" id="idFicha" name="idFicha"/>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="idCursoFicha">
                                                Estado
                                            </label>
                                            <select name="estadoFicha" id="estadoFicha" class="form-control" required>
                                                <option value="1">Activo</option>
                                                <option value="0">Inactivo</option>                                                   
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" value="" id="fecha" name="fecha">
                            </div>
                            <div class="panel-footer">
                                <div class="col-md-offset-3 col-md-6">
                                    <div class="form-group">
                                        <input id="btnFicha"  class="btn btn-default btn-block" type="submit" name="action" onclick="ficha.myAjax($('#btnFicha').val())">
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
