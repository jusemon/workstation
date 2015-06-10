<%-- 
    Document   : matricula
    Created on : 23-oct-2014, 12:13:22
    Author     : Administrador
--%>
<%@page import="Controller.ControllerLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="WEB-INF/jspf/header.jspf" %>
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
                <li>
                    <a href="#preinscritos" role="tab" data-toggle="tab">Listado de Preinscritos</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="estudiantes">
                    <table id="tblEstudiantes" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>                                
                                <th class="text-center">Documento</th>
                                <th class="text-center">Nombres</th>
                                <th class="text-center">Fecha Nacimiento</th>
                                <th class="text-center">Telefono</th>
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Editar</th>
                                <th class="text-center">Matricular</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="matriculas">
                    <table id="tblMatriculas" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Cliente</th>
                                <th class="text-center">Curso</th>
                                <th class="text-center">Clases</th>
                                <th class="text-center">Clases Asistidas</th>                                
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Marcar Asistencia</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="preinscritos">
                    <table id="tblPreinscritos" class="table table-responsive table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Documento</th>
                                <th class="text-center">Cliente</th>
                                <th class="text-center">Curso</th>
                                <th class="text-center">Fecha Preinscripción</th>
                                <th class="text-center">Email Cliente</th>
                                <th class="text-center">Consultar</th>
                                <th class="text-center">Inscribir</th>
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
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label for="txtTipo">
                                                    Tipo de Documento:
                                                </label>
                                                <text id="txtTipo"></text>
                                                <br>
                                                <label for="txtIdentificacion">
                                                    Número:
                                                </label>
                                                <text id="txtIdentificacion"></text>
                                                <input type="hidden" name="txtDocumento" id="txtDocumento"/>
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label for="txtNombre">
                                                    Nombre:
                                                </label>
                                                <text id="txtNombre"></text>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label for="idCursoMatricula">
                                                    Curso:
                                                </label>
                                                <select name="idCursoMatricula" id="idCursoMatricula" class="form-control" onchange="curso.seleccionar($('#idCursoMatricula').val())" required>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <div class="form-group">
                                                <label for="txtPrecioCurso">
                                                    Precio del Curso:
                                                </label>
                                                <text id="txtPrecioCurso">
                                                </text>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="txtClases">
                                                    Clases:
                                                </label>
                                                <input type="text" name="txtClases" id="txtClases" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="txtPrecioClases">
                                                    Precio por Clase: 
                                                </label>
                                                <text id="txtPrecioClases">
                                                </text>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="txtHoraClase">
                                                    Horas por Clase: 
                                                </label>
                                                <text id="txtHoraClase">
                                                </text>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label for="txtHoraClase">
                                                        Pagar clase: 
                                                    </label>
                                                    <input type="checkbox" id="estadoPago" name="estadoPago"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                    
                                </div>
                                <div class="panel-footer">
                                    <div class="col-md-offset-3 col-md-6">
                                        <div class="form-group">
                                            <input id="btnMatricula"  class="btn btn-default btn-block" type="button" name="action">
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
                                        <div class="form-group">
                                            <input name="idCurso" id="idCurso" type="hidden">
                                            <label class="col-md-12" for="identificacion">
                                                Identificación
                                            </label>
                                            <div class="col-md-3" id="identificacion">
                                                <div class="form-group">
                                                    <label for="ddlIdentificacion">
                                                        Tipo
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
                                            <div class="col-md-9">
                                                <div class="form-group">
                                                    <label for="txtIdentificacion">
                                                        Número
                                                    </label>
                                                    <input name="txtIdentificacion" id="txtIdentificacion" type="text" pattern="[0-9]{5,15}"  title="Solo se permiten numeros y no deben ser menos de 5 o mas de 15" class="form-control" placeholder="Ejm: 1017225673" required>                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="txtNombre">
                                                    Nombres
                                                </label>
                                                <input name="txtNombre" id="txtNombre" type="text" pattern="([ÁÉÍÓÚáéíóúñÑa-zA-Z]{3,15})+([ ]{1})?([ÁÉÍÓÚáéíóúñÑa-zA-Z]{3,15})?" title="No se permiten numeros, ni tampoco mas de dos nombres, Ejem: Maria Camila" class="form-control" placeholder="Ejm: Juan Sebastián" required>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="txtApellido">
                                                    Apellidos
                                                </label>
                                                <input name="txtApellido" id="txtApellido" type="text" pattern="([ÁÉÍÓÚáéíóúñÑa-zA-Z]{3,15})+([ ]{1})?([ÁÉÍÓÚáéíóúñÑa-zA-Z]{3,15})?" title="No se permiten numeros, ni tampoco mas de dos apellidos, Ejem: Montoya Soto" class="form-control" placeholder="Ejm: Montoya Montoya" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="dateFechaNacimiento">
                                                    Fecha de Nacimiento
                                                </label>
                                                <input name="dateFechaNacimiento" id="dateFechaNacimiento" type="text" pattern="(0[1-9]|1[0-9]|3[01]).(0[1-9]|1[0-2]).([0-9]{4})" title="El formato de la fecha debe ser dd/mm/yyyy" class="form-control fecha" placeholder="Ejm: 10/10/2014" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="txtDireccion">
                                                    Dirección
                                                </label>
                                                <input name="txtDireccion" id="txtDireccion" type="text" class="form-control" placeholder="Ejm: Calle 24 # 65 e 25" required>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="txtTelefono">
                                                    Teléfono
                                                </label>
                                                <input name="txtTelefono" id="txtTelefono" type="text" pattern="([0-9 ]{7,14})" title="Numeros, se permiten espacios, ejem: 5 86 15 29" class="form-control" placeholder="Ejm: 5 65 85 45" required>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="txtCelular">
                                                    Celular
                                                </label>
                                                <input name="txtCelular" id="txtCelular" type="text" pattern="([0-9 ]{10,22})" title="Numeros, se permiten espacios, ejem: 321 801 62 37" class="form-control" placeholder="Ejm: 321 801 62 37">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="txtCorreo">
                                                    Correo Electrónico
                                                </label>
                                                <input name="txtCorreo" id="txtCorreo" type="email" class="form-control" placeholder="Ejm: juansmm@outlook.com" required>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="txtPass">
                                                    Contraseña                                                
                                                </label>
                                                <input name="txtPass" id="txtPass" type="password" class="form-control" required="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="radioGenero">
                                                    Género: 
                                                </label>
                                                <label class="radio-inline"><input type="radio" name="radioGenero" id="radioGeneroFemenino" class="radio-inline" value="0" required="true">Femenino</label>
                                                <label class="radio-inline"><input type="radio" name="radioGenero" id="radioGeneroMasculino" class="radio-inline" value="1">Masculino</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="radioBeneficiario">
                                                    Beneficiario: 
                                                </label>
                                                <label class="radio-inline"><input type="radio" name="radioBeneficiario" id="radioSiBeneficiario" class="radio-inline" value="1" required="true">Si</label>
                                                <label class="radio-inline"><input type="radio" name="radioBeneficiario" id="radioNoBeneficiario" class="radio-inline" value="0">No</label>
                                            </div>
                                        </div>                                       
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="row">
                                        <div class="col-md-offset-3 col-md-6">
                                            <div class="form-group">
                                                <input class="btn btn-default btn-block" type="submit" id="btnEstudiante" name="action" onclick="estudiante.myAjax($('#btnEstudiante').val())">
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
<%@include file="WEB-INF/jspf/footer.jspf" %>