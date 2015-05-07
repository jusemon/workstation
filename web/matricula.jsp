<%-- 
    Document   : matricula
    Created on : 23-oct-2014, 12:13:22
    Author     : Administrador
--%>
<%@page import="Controller.ControllerLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/superior.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 ">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            Gestión de Estudiantes
                        </a> 
                    </h3>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <input class="btn btn-default btn-block" type="button" onclick="estudiante.registrar()" value="Registrar Estudiante">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active">
                    <a href="#estudiantes" role="tab" data-toggle="tab">Listado de Estudiantes</a>
                </li>
                <li>
                    <a href="#matriculas" role="tab" data-toggle="tab">Listado de Matriculas</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="estudiantes">
                    <table id="tblEstudiantes" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Tipo Documento</th>
                                <th class="text-center">Documento</th>
                                <th class="text-center">Nombres</th>
                                <th class="text-center">Género</th>
                                <th class="text-center">Beneficiario</th>
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Editar</th>
                                <th class="text-center">Matricular</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="matriculas">
                    <table id="tblMatriculas" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Código</th>
                                <th class="text-center">Cliente</th>
                                <th class="text-center">Curso</th>
                                <th class="text-center">Fecha Inicio</th>
                                <th class="text-center">Fecha Fin</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Editar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--tr>
                                <td class="text-center">0001</td>
                                <td class="text-center">1017225673</td>
                                <td class="text-center">Oleo</td>
                                <td class="text-center">11/01/2015</td>
                                <td class="text-center">11/04/2015</td>
                                <td class="text-center"><a class="btn-sm btn-success btn-block " href="javascript:void(0)"  onclick="add("Estado")>
                                                           <span class="glyphicon glyphicon-ok"></span></a>
                                </td>
                                <td class="text-center"><a class="btn-sm btn-primary btn-block " href="javascript:void(0)"  onclick="add("Estado")>
                                                           <span class="glyphicon glyphicon-pencil"></span></a>
                                </td>
                            </tr-->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Emergente de Acudiente-->
<div class="modal" id="miPopupAcudiente">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="formAcudiente" action="ControllerAcudiente" method="POST">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombre">
                                                    Nombres
                                                </label>
                                                <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: Juan Sebastian" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtApellido">
                                                    Apellidos
                                                </label>
                                                <input name="txtApellido" id="txtApellido" type="text" class="form-control" placeholder="Ejm: Montoya Montoya" required>
                                            </div>
                                        </div>
                                    </div>                                            
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtDireccion">
                                                    Teléfono
                                                </label>
                                                <input name="txtDireccion" id="txtDireccion" type="text" class="form-control" placeholder="Ejm: Calle 24 # 65 e 25" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtDireccion">
                                                    Correo Electrónico
                                                </label>
                                                <input name="txtDireccion" id="txtDireccion" type="text" class="form-control" placeholder="Ejm: Calle 24 # 65 e 25" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtDireccion">
                                                    Dirección
                                                </label>
                                                <input name="txtDireccion" id="txtDireccion" type="text" class="form-control" placeholder="Ejm: Calle 24 # 65 e 25" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="dateFechaNaciemiento">
                                                    Fecha de Nacimiento
                                                </label>
                                                <input name="dateFechaNaciemiento" id="dateFechaNaciemiento" type="text" class="form-control fecha" placeholder="Ejm: 10/10/2014" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="ddlIdentificacion">
                                                    Tipo de Identificación
                                                </label>
                                                <select name="ddlIdentificacion" id="ddlIdentificacion" class="form-control" required>
                                                    <option value="">Seleccionar...</option>
                                                    <option value="0" > Cédula </option>
                                                    <option value="1" > Cédula Extranjeria </option>
                                                    <option value="3" > Tarjeta de Identidad </option>
                                                    <option value="4" > Registro Civil </option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtIdentificacion">
                                                    Número de Identificación
                                                </label>
                                                <input name="txtIdentificacion" id="txtIdentificacion" type="text" class="form-control" placeholder="Ejm: 1017225673" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="col-md-offset-3 col-md-6">
                                        <div class="form-group">
                                            <input  class="btn btn-default btn-block" type="submit" name="action" value="Añadir">
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

<!--Emergente de Matricular-->
<div class="modal" id="miPopupMatricula">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="formMatricula" method="POST" action="ControllerMatricula">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtIdentificacion">
                                                    Estudiante:
                                                </label>
                                                <label id="txtIdentificacion"></label>
                                                <div id="nombres">
                                                    <label for="nombres">
                                                        Nombres:
                                                    </label>
                                                    <label id="txtNombre"></label>
                                                    <label id="txtApellido"></label>
                                                </div>
                                                <input type="hidden" name="txtIdentificacion" id="idEstudiante"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="idCursoFicha">
                                                    Ficha:
                                                </label>
                                                <select name="idCursoFicha" id="idCursoFicha" class="form-control" onchange="estudiante.myAjax('Seleccion', $('#idCursoFicha').val())" required>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="dateInicioFicha">
                                                    Fecha de Inicio:
                                                </label>
                                                <label id="dateInicioFicha"></label>
                                                <input type="hidden" name="dateInicio" id="dateInicio"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="dateFinFicha">
                                                    Fecha Fin: 
                                                </label>
                                                <label id="dateFinFicha"></label>
                                                <input type="hidden" name="dateFinal" id="dateFinal"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="col-md-offset-3 col-md-6">
                                        <div class="form-group">
                                            <input  class="btn btn-default btn-block" type="submit" name="action" value="Matricular">
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

<!--Emergete de Estudiante -->

<div class="modal" id="miPopupEstudiante">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="form_estudiante" class="" action="ControllerEstudiante" method="POST">
                            <div class="panel">
                                <div class="panel-heading estilo2">
                                    <h3 class="panel-title">
                                        <label id="titulo"></label>
                                        <button type="button" id="cerrar1" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="ddlIdentificacion">
                                                    Tipo de Identificación
                                                </label>
                                                <select name="ddlIdentificacion" id="ddlIdentificacion" class="form-control" required>
                                                    <option value="">Seleccionar...</option>
                                                    <option value="CC" > Cédula </option>
                                                    <option value="CE" > Cédula Extranjería </option>
                                                    <option value="TI" > Tarjeta de Identidad </option>
                                                    <option value="RC" > Registro Civil </option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtIdentificacion">
                                                    Número de Identificación
                                                </label>
                                                <input name="txtIdentificacion" id="txtIdentificacion" type="text" class="form-control" placeholder="Ejm: 1017225673" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtNombre">
                                                    Nombres
                                                </label>
                                                <input name="txtNombre" id="txtNombre" type="text" class="form-control" placeholder="Ejm: Juan Sebastian" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtApellido">
                                                    Apellidos
                                                </label>
                                                <input name="txtApellido" id="txtApellido" type="text" class="form-control" placeholder="Ejm: Montoya Montoya" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="radioGenero">
                                                    Género: 
                                                </label>
                                                <label class="radio-inline"><input type="radio" name="radioGenero" id="radioGeneroFemenino" class="radio-inline" value="0">Femenino</label>
                                                <label class="radio-inline"><input type="radio" name="radioGenero" id="radioGeneroMasculino" class="radio-inline" value="1">Masculino</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="dateFechaNacimiento">
                                                    Fecha de Nacimiento
                                                </label>
                                                <input name="dateFechaNacimiento" id="dateFechaNacimiento" type="text" class="form-control fecha" placeholder="Ejm: 10/10/2014" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtDireccion">
                                                    Dirección
                                                </label>
                                                <input name="txtDireccion" id="txtDireccion" type="text" class="form-control" placeholder="Ejm: Calle 24 # 65 e 25" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtTelefono">
                                                    Teléfono
                                                </label>
                                                <input name="txtTelefono" id="txtTelefono" type="text" class="form-control" placeholder="Ejm: 5 65 85 45" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtCelular">
                                                    Celular
                                                </label>
                                                <input name="txtCelular" id="txtCelular" type="number" class="form-control" placeholder="Ejm: 321 801 62 37">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="txtCorreo">
                                                    Correo Electrónico
                                                </label>
                                                <input name="txtCorreo" id="txtCorreo" type="email" class="form-control" placeholder="Ejm: juansmm@outlook.com" required>
                                            </div>
                                        </div>
                                    </div>                             
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="radioBeneficiario">
                                                    Beneficiario: 
                                                </label>
                                                <label class="radio-inline"><input type="radio" name="radioBeneficiario" id="radioSiBeneficiario" class="radio-inline" value="1">Si</label>
                                                <label class="radio-inline"><input type="radio" name="radioBeneficiario" id="radioNoBeneficiario" class="radio-inline" value="0">No</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-offset-3 col-md-6">
                                    <div class="form-group">
                                        <input class="btn btn-default btn-block" type="submit" id="btnEstudiante" name="action" onclick="estudiante.myAjax($('#btnEstudiante').val())">
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