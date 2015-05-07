<%-- 
    Document   : articulo
    Created on : 23-oct-2014, 12:13:31
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
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                Gestión de Artículos
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="articulo.registrar()" value="Registrar Artículo">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="button" onclick="categoriaArticulo.registrar()" value="Registrar Categoría">
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
                    <a href="#articulos" role="tab" data-toggle="tab">Listado de Artículos</a>
                </li>
                <li>
                    <a href="#categorias" role="tab" data-toggle="tab">Listado de Categorías</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="articulos">
                    <table id="tblArticulos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Categoría</th>
                                <th class="text-center">Descripción</th>
                                <th class="text-center">Cantidad</th>                                        
                                <th class="text-center">Precio</th>
                                <th class="text-center">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="categorias">
                    <table id="tblCategoriaArticulos" class="table table-responsive table-hover" cellspacing="0" width="100%">
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
            </div>
        </div>
    </div>
</div>

<div class="modal" id="miPopupArticulo">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="formArticulo" action="ControllerArticulo" method="POST">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <input type="hidden" name="idArticulo" id="idArticulo"/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombreArticulo">
                                                    Descripción Artículo:
                                                </label>
                                                <input name="txtDescripcion" id="txtNombreArticulo" type="text" class="form-control" title="Entre 3 y 30 letras, se permiten numeros y algunos caracteres como , y ."  placeholder="Ejm: Vinilo Rojo" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtPrecio">
                                                    Precio:
                                                </label>
                                                <input name="txtPrecio" id="txtPrecioArticulo" type="number" min="1" max="10000000" class="form-control" placeholder="Ejm: 10000" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtCantidad">
                                                    Cantidad disponible:
                                                </label>
                                                <input name="txtCantidad" id="txtCantidadArticulo" type="number" min="0" max="1000" class="form-control" placeholder="Ejm: 30" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="idCategoria">
                                                    Categoría:
                                                </label>
                                                <select name="idCategoria" id="idCategoriaArticulo" class="form-control" required>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="col-md-offset-3 col-md-6">
                                        <div class="form-group">
                                            <input id="btnArticulo"  class="btn btn-default btn-block" onclick="articulo.myAjax($('#btnArticulo').val())" type="submit" name="action">
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

<div class="modal" id="miPopupCategoriaArticulo">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="formCategoriaArticulo" method="POST" action="ControllerCategoriaArticulo">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <input type="hidden" id="idCategoriaArticulo" name="idCategoriaArticulo"/>
                                        <div class="col-md-12">                                        
                                            <div class="form-group">
                                                Nombre
                                                <input name="txtNombre" id="txtNombreCategoriaArticulo" type="text" title="Entre 3 y 30 letras y no se permiten numeros" class="form-control" placeholder="Ejm: Vinilos" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-6">
                                            <div class="form-group">
                                                <input  class="btn btn-default btn-block" id="btnCategoriaArticulo" onclick="categoriaArticulo.myAjax($('#btnCategoriaArticulo').val())" type="submit" name="action">
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
<%@include file="WEB-INF/jspf/imports.jspf" %>