/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global documentoUsuario */

$('.fecha').datepicker({
    format: "dd/mm/yyyy",
    language: "es",
    autoclose: true,
    orientation: "top left"
});

var tablaCurso, tablaCategoriaCurso, tablaClases, tablaSeminario, tablaEstudiante, tablaMatricula, tablaArticulo, tablaCategoriaArticulo, tablaEmpresa, tablaCompra, tablaVenta, tablaUsuario, idCurso, tablaPreinscritos;

var curso = {
    myAjax: function (accion, id, aux, typo) {
        var form = $('#formCurso');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&type=' + typo,
                async: false,
                success: function (data) {
                    if (accion == 'Consultar') {
                        if (aux == 'Editar') {
                            curso.editar(data);
                        } else
                            curso.consultar(data);
                    }
                    else if (accion == 'Registrar' || accion == 'Editar' || accion == 'Estado') {
                        if (accion != 'Estado') {
                            $('#miPopupCurso').modal('hide');
                        }
                        if (accion == 'Registrar') {
                            seminario.actualizarTabla();
                        }
                        mensaje(data);
                        curso.actualizarTabla();
                    }
                    else if (accion === 'getOptionsCursos') {
                        curso.cargarOpciones(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar') {
            $(form).submit();
        }
    },
    seleccionar: function (id) {
        if (id > 0) {
            $.ajax({
                url: "ControllerCurso",
                type: 'POST',
                data: {
                    action: 'Consultar',
                    id: id,
                    type: 'Curso'
                },
                success: function (data, textStatus, jqXHR) {
                    $('#miPopupMatricula').find('#txtPrecioCurso').text(data['precioCurso']).parents('.form-group:first').show();
                    $('#miPopupMatricula').find('#txtClases').val(data['cantidadClases']).parents('.row:first').show();
                    $('#miPopupMatricula').find('#txtPrecioClases').text(data['precioCurso'] / data['cantidadClases']);
                    $('#miPopupMatricula').find('#txtHoraClase').text(data['horasPorClase']);
                }
            });
        } else {
            $('#miPopupMatricula').find('#txtPrecioCurso').empty().parents('.form-group:first').hide();
            $('#miPopupMatricula').find('#txtClases').val(null).parents('.row:first').hide();
            $('#miPopupMatricula').find('#txtPrecioClases').empty();
            $('#miPopupMatricula').find('#txtHoraClase').empty();
        }

    },
    cargarOpciones: function () {
        $.ajax({
            url: "ControllerCurso",
            type: 'POST',
            data: {
                action: 'getOptionsCursos'
            },
            success: function (data, textStatus, jqXHR) {
                $('#idCursoMatricula').empty();
                $('#idCursoMatricula').append('<option value="0"></option>' + data);
            }
        });
    },
    consultar: function (data) {
        limpiar("#formCurso");
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Consultar Curso');
        $('#miPopupCurso').find('#tipo').val('Curso');
        $('#miPopupCurso').find('#idCurso').val(data['idCurso']);
        $('#miPopupCurso').find('#ddlCategoria option').prop('selected', false).filter('[value="' + data['idCategoriaCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#txtNombreCurso').val(data['nombreCurso']);
        $('#miPopupCurso').find('#ContenedorCategoria').show();
        $('#miPopupCurso').find('#txtCantidadClases').val(data['cantidadClases']);
        $('#miPopupCurso').find('#txtCantidadHoras').val(data['horasPorClase']);
        $('#miPopupCurso').find('#txtDescripcionCurso').val(data['descripcionCurso']);
        $('#miPopupCurso').find('#txtPrecio').val(data['precioCurso']);
        $('#miPopupCurso').find('#ddlEstado option').prop('selected', false).filter('[value="' + data['estadoCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#formCurso');
        $('#miPopupCurso').modal('show');
    },
    registrar: function () {
        limpiar("#formCurso");
        habilitar('#formCurso');
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Registrar Curso');
        $('#miPopupCurso').find('#tipo').val('Curso');
        $('#miPopupCurso').find('#ContenedorCategoria').show();
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupCurso').modal('show');
    },
    editar: function (data) {
        curso.consultar(data);
        habilitar('#formCurso');
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Editar Curso');
        $('#miPopupCurso').find('#tipo').val('Curso');
        $('#miPopupCurso').find('#ContenedorCategoria').show();
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    preinscripcion: function (idCurso, btn) {
        $('.notifyjs-foo-base ').trigger('notify-hide');
        $(document).off('click', '.notifyjs-foo-base .no');
        $(document).off('click', '.notifyjs-foo-base .yes');
        if (typeof (documentoUsuario) !== "undefined") {
            $(btn).notify({
                title: '¿Estas seguro?',
                button: 'Confirmar',
            }, {
                style: 'foo',
                autoHide: false,
                clickToHide: false,
                position: 'botton center'
            });
            //listen for click events from this style
            $(document).on('click', '.notifyjs-foo-base .no', function () {
                $(this).trigger('notify-hide');
            });
            $(document).on('click', '.notifyjs-foo-base .yes', function () {
                $.ajax({
                    type: 'POST',
                    url: "ControllerCurso",
                    dataType: 'JSON',
                    data: {
                        action: 'Preinscribir',
                        idCurso: idCurso,
                        documentoUsuario: documentoUsuario,
                        tipo: 'Curso'
                    },
                    success: function (data) {
                        mensaje(data);
                    }
                });
                $(this).trigger('notify-hide');
                $(document).off('click', '.notifyjs-foo-base .yes');
            });
        }
        else {
            $.notify('Lo siento, primero debes registrarte', 'error');
        }
    },
    mostrarDisponibles: function () {
        $('#cursosDisponibles').empty();
        $.ajax({
            type: 'POST',
            url: 'ControllerCurso',
            data: {
                action: 'cursosDisponibles'
            },
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var html = '<div class="col-md-6">'
                            + '<div class="panel panel-default">'
                            + '<div class="panelCursos-Heading">'
                            + '<div class="panel-title text-center">'
                            + data[i]['nombreCurso']
                            + '</div>'
                            + '</div>'
                            + '<div class="panel-body">'
                            + '<div class="row">'
                            + '<div class="col-md-6">'
                            + 'Precio:'
                            + '<label id="precio">' + data[i]['precioCurso'] + '</label>'
                            + '</div>'
                            + '<div class="col-md-6">'
                            + 'Clases:'
                            + '<label id="clases">' + data[i]['cantidadClases'] + '</label>'
                            + '</div>'
                            + '</div>'
                            + '<div class="row">'
                            + '<div class="col-md-6">'
                            + 'Horas (Por Clase):'
                            + '<label id="horas">' + data[i]['horasPorClase'] + '</label>'
                            + '</div>'
                            + '<div class="col-md-5">'
                            + '<a class="btn btn-sm btn-default" href="javascript:void(0)" onclick="curso.preinscripcion(' + data[i]['idCurso'] + ',this)">Preinscribirse</a>'
                            + '</div>'
                            + '</div>'
                            + '</div>'
                            + '</div>'
                            + '</div';
                    $("#cursosDisponibles").append(html);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $.notify(errorThrown, textStatus);
            }
        });
    },
    cargar: function () {
        tablaCurso = $('#tblCursos').DataTable({
            "ajax": {
                "url": "ControllerCurso",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaCurso.ajax.reload();
    }
};

var categoriaCurso = {
    myAjax: function (accion) {
        var form = $('#form_categoriaCurso');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion,
                success: function (data) {
                    if (accion === 'getOptionsCategorias') {
                        categoriaCurso.cargarOpciones(data);
                    } else if (accion !== 'getOptionsCategorias') {
                        categoriaCurso.actualizarTabla();
                        if (accion === 'Editar' || accion === 'Registrar') {
                            $('#miPopupCategoriaCurso').modal('hide');
                            mensaje(data);
                        }
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'getOptionsCategorias') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#form_categoriaCurso');
        $('#miPopupCategoriaCurso').find('#titulo').empty();
        $('#miPopupCategoriaCurso').find('#titulo').append('Registrar Categoría Curso');
        $('#miPopupCategoriaCurso').find('#btnCategoriaCurso').attr('value', 'Registrar');
        $('#miPopupCategoriaCurso').modal('show');
    },
    editar: function (tr) {
        var data = tablaCategoriaCurso.row(tr).data();
        $('#miPopupCategoriaCurso').find('#titulo').empty();
        $('#miPopupCategoriaCurso').find('#titulo').append('Editar Categoría Curso');
        $('#miPopupCategoriaCurso').find('#idCategoriaCurso').val(data[0]);
        $('#miPopupCategoriaCurso').find('#txtNombreCategoriaCurso').val(data[1]);
        $('#miPopupCategoriaCurso').find('#btnCategoriaCurso').val('Editar');
        $('#miPopupCategoriaCurso').modal('show');
    },
    cargar: function () {
        categoriaCurso.myAjax('getOptionsCategorias');
        tablaCategoriaCurso = $('#tblCategoriaCursos').DataTable({
            "ajax": {
                "url": "ControllerCategoriaCurso",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        categoriaCurso.myAjax('getOptionsCategorias');
        tablaCategoriaCurso.ajax.reload();
    },
    cargarOpciones: function (data) {
        $('#miPopupCurso').find('#ddlCategoria').empty();
        $('#miPopupCurso').find('#ddlCategoria').append(data);
    }
};

var clase = {
    myAjax: function (accion, id) {
        var form = $('#formFicha');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id,
                success: function (data) {
                    if (accion == 'Estado' || accion == 'Editar' || accion == 'Registrar') {
                        if (accion != 'Estado') {
                            $('#miPopupFicha').modal('hide');
                        }
                        clase.actualizarTabla();
                        mensaje(data);
                    } else if (accion == 'getOptionsClases') {
                        clase.cargarOpciones(data);
                    } else if (accion == 'cursosDisponibles') {
                        clase.mostrarDisponibles(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#formFicha');
        $('#miPopupFicha').find('#titulo').empty();
        $('#miPopupFicha').find('#titulo').append('Registrar Ficha');
        $('#miPopupFicha').find('#btnFicha').attr('value', 'Registrar');
        $('#miPopupFicha').modal('show');
    },
    editar: function (tr, estado, id) {
        var data = tablaClases.row(tr).data();
        $('#miPopupFicha').find('#titulo').empty();
        $('#miPopupFicha').find('#titulo').append('Editar Ficha');
        $('#miPopupFicha').find('#idFicha').val(data[0]);
        $('#miPopupFicha').find('#idCursoFicha option').prop('selected', false).filter('[value="' + id + '"]').prop('selected', true);
        $('#miPopupFicha').find('#txtCupos').val(data[2]);
        $('#miPopupFicha').find('#txtPrecioFicha').val(data[3]);
        $('#miPopupFicha').find('#dateFechaFicha').val(data[4]);
        $('#miPopupFicha').find('#estadoFicha option').prop('selected', false).filter('[value="' + estado + '"]').prop('selected', true);
        $('#miPopupFicha').find('#btnFicha').val('Editar');
        $('#miPopupFicha').modal('show');
    },
    cargar: function () {
        clase.myAjax('getOptionsClases');
        tablaClases = $('#tblFichas').DataTable({
            "ajax": {
                "url": "ControllerClase",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaClases.ajax.reload();
    },
    cargarOpciones: function (data) {
        $('#formMatricula').find('#idCursoFicha').empty();
        $('#formMatricula').find('#idCursoFicha').append(data);
    }
};

var seminario = {
    myAjax: function (accion, id, aux, typo) {
        var form = $('#formCurso');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&type=' + typo,
                success: function (data) {
                    if (accion == 'Consultar') {
                        if (aux == 'Editar') {
                            seminario.editar(data);
                        }
                        else
                            seminario.consultar(data);
                    }
                    else if (accion == 'Editar' || accion == 'Estado') {
                        if (accion != 'Estado') {
                            $('#miPopupCurso').modal('hide');
                        }
                        mensaje(data);
                        seminario.actualizarTabla();
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar') {
            $(form).submit();
        }
    },
    consultar: function (data) {
        limpiar("#formCurso");
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Consultar Seminario');
        $('#miPopupCurso').find('#tipo').val('Seminario');
        $('#miPopupCurso').find('#idCurso').val(data['idCurso']);
        $('#miPopupCurso').find('#txtNombreCurso').val(data['nombreCurso']);
        $('#miPopupCurso').find('#txtCantidadClases').val(data['cantidadClases']);
        $('#miPopupCurso').find('#ContenedorCategoria').hide().find('#ddlCategoria').attr('disabled', true);
        $('#miPopupCurso').find('#txtCantidadHoras').val(data['horasPorClase']);
        $('#miPopupCurso').find('#txtDescripcionCurso').val(data['descripcionCurso']);
        $('#miPopupCurso').find('#txtPrecio').val(data['precioCurso']);
        $('#miPopupCurso').find('#ddlEstado option').prop('selected', false).filter('[value="' + data['estadoCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#formCurso');
        $('#miPopupCurso').modal('show');
    },
    registrar: function () {
        limpiar('#formCurso');
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Registrar Seminario');
        $('#miPopupCurso').find('#tipo').val('Seminario');
        $('#miPopupCurso').find('#txtCantidadClases').val(1).attr('readOnly', true);
        $('#miPopupCurso').find('#ContenedorCategoria').hide().find('#ddlCategoria').attr('disabled', true);
        $('#miPopupCurso').find('#btnCurso').attr('value', 'Registrar');
        $('#miPopupCurso').modal('show');
    },
    editar: function (data) {
        seminario.consultar(data);
        habilitar('#formCurso');
        $('#miPopupCurso').find('#txtCantidadClases').attr('readOnly', true);
        $('#miPopupCurso').find('#ContenedorCategoria').hide().find('#ddlCategoria').attr('disabled', true);
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Editar Seminario');
        $('#miPopupCurso').find('#tipo').val('Seminario');
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    preinscripcion: function (idCurso, btn) {
        $('.notifyjs-foo-base ').trigger('notify-hide');
        $(document).off('click', '.notifyjs-foo-base .no');
        $(document).off('click', '.notifyjs-foo-base .yes');
        if (typeof (documentoUsuario) !== "undefined") {
            $(btn).notify({
                title: '¿Estas seguro?',
                button: 'Confirmar',
            }, {
                style: 'foo',
                autoHide: false,
                clickToHide: false,
                position: 'botton center'
            });
            //listen for click events from this style
            $(document).on('click', '.notifyjs-foo-base .no', function () {
                $(this).trigger('notify-hide');
            });
            $(document).on('click', '.notifyjs-foo-base .yes', function () {
                $.ajax({
                    type: 'POST',
                    url: "ControllerCurso",
                    dataType: 'JSON',
                    data: {
                        action: 'Preinscribir',
                        idCurso: idCurso,
                        documentoUsuario: documentoUsuario,
                        tipo: 'Seminario'
                    },
                    success: function (data) {
                        mensaje(data);
                    }
                });
                $(this).trigger('notify-hide');
                $(document).off('click', '.notifyjs-foo-base .yes');
            });
        }
        else {
            $.notify('Lo siento, primero debes registrarte', 'error');
        }
    },
    mostrarDisponibles: function () {
        $('#seminariosDisponibles').empty();
        $.ajax({
            type: 'POST',
            url: 'ControllerCurso',
            data: {
                action: 'seminariosDisponibles'
            },
            success: function (data) {
                var html = data;
                $("#seminariosDisponibles").append(html);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $.notify(errorThrown, textStatus);
            }
        });
    },
    cargar: function () {
        tablaSeminario = $('#tblSeminarios').DataTable({
            "ajax": {
                "url": "ControllerCurso",
                "type": "POST",
                "data": {
                    action: 'EnlistarSeminarios'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaSeminario.ajax.reload();
    }
};

var abono = {
    myAjax: function (accion, id, aux) {
        var form = $('#formAbono');
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id,
                success: function (data) {
                    if (accion == 'Consultar') {
                        if (aux == 'Editar') {
                            abono.editar(data);
                        } else
                            abono.consultar(data);
                    }
                    else if (accion == 'Registrar' || accion == 'Editar') {
                        $('#miPopupAbono').modal('hide');
                        abono.mensaje(data);
                        abono.actualizarTabla();
                    }
                    else if (accion == 'Estado') {
                        abono.mensaje(data);
                        abono.actualizarTabla();
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar') {
            $(form).submit();
        }
    },
    consultar: function (data) {
        limpiar("#formAbono");
        $('#miPopupAbono').find('#titulo').empty();
        $('#miPopupAbono').find('#titulo').append('Consultar Abono');
        $('#miPopupAbono').find('#idAbono').val(data['idAbono']);
        $('#miPopupAbono').find('#txtIdCredito').val(data['idCredito']);
        $('#miPopupAbono').find('#txtValorAbono').val(data['valorAbono']);
        $('#miPopupAbono').find('#dateFechaPago').val(data['fechaPago']);
        $('#miPopupAbono').find('#btnAbono').attr('type', 'hidden').attr('disabled', true);
        $('#miPopupAbono').modal('show');
    },
    registrar: function () {
        limpiar("#formAbono");
        $('#miPopupAbono').find('#titulo').empty();
        $('#miPopupAbono').find('#titulo').append('Registrar Abono');
        $('#miPopupAbono').find('#btnAbono').attr('type', 'submit').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupAbono').modal('show');
    },
    editar: function (data) {
        abono.consultar(data);
        $('#miPopupAbono').find('#titulo').empty();
        $('#miPopupAbono').find('#titulo').append('Editar Abono');
        $('#miPopupAbono').find('#btnAbono').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    mensaje: function (data) {
        $.notify(data['mensaje'], data['tipo']);
    },
    cargar: function () {
        tablaAbono = $('#tblAbono').DataTable({
            "ajax": {
                "url": "ControllerAbono",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaAbono.ajax.reload();
    }
};

var estudiante = {
    myAjax: function (accion, id, tipo, aux) {
        var form = $('#form_estudiante');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                success: function (data) {
                    if (accion == 'Consultar') {
                        if (tipo == 'Editar') {
                            estudiante.editar(data);

                        } else if (tipo == 'Matricular') {
                            estudiante.matricular(data);
                        } else if (tipo == 'Preinscrito') {
                            if (aux === 'Inscribir') {
                                estudiante.preinscribir(data);
                            } else
                                estudiante.consultarPreinscrito(data);

                        } else
                            estudiante.consultar(data);
                    }
                    else if (accion == 'Registrar' || accion == 'Editar' || accion == 'Estado') {
                        if (accion != 'Estado') {
                            $('#miPopupEstudiante').modal('hide');
                        }
                        mensaje(data);
                        estudiante.actualizarTabla();
                    }
                    else if (accion === 'getOptionsFichas') {
                        clase.cargarOpciones(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar' || accion === 'Seleccion') {
            $(form).submit();
        }
    },
    matricular: function (data) {
        limpiar("#formMatricula");
        curso.cargarOpciones();
        $('#miPopupMatricula').find('#titulo').text('Matricular Estudiante');
        $('#miPopupMatricula').find('#txtNombre').text(data["nombreUsuario"] + " " + data["apellidoUsuario"]);
        $('#miPopupMatricula').find('#txtIdentificacion').text(data['numeroDocumento']);
        var tipo = data['tipoDocumento'];
        tipo = (tipo === 'CC') ? 'Cedula' : tipo;
        tipo = (tipo === 'TI') ? 'Tarjeta de Identidad' : tipo;
        tipo = (tipo === 'RC') ? 'Registro Civil' : tipo;
        tipo = (tipo === 'CE') ? 'Cedula de Extranjeria' : tipo;
        $('#miPopupMatricula').find('#txtTipo').text(tipo);
        $('#miPopupMatricula').find('#txtDocumento').val(data['tipoDocumento'] + data['numeroDocumento']);
        $('#miPopupMatricula').find('#idCursoMatricula option').prop('selected', false);
        $('#miPopupMatricula').find('#txtPrecioCurso').empty().parents('.form-group:first').hide();
        $('#miPopupMatricula').find('#txtClases').val(null).parents('.row:first').hide();
        $('#miPopupMatricula').modal('show');
    },
    consultar: function (data) {
        limpiar("#form_estudiante");
        $('#miPopupEstudiante').find('#titulo').empty();
        $('#miPopupEstudiante').find('#titulo').append('Consultar Estudiante');
        $('#miPopupEstudiante').find('#txtIdentificacion').val(data['numeroDocumento']);
        $('#miPopupEstudiante').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
        $('#miPopupEstudiante').find('#txtNombre').val(data['nombreUsuario']);
        $('#miPopupEstudiante').find('#txtApellido').val(data['apellidoUsuario']);
        $('#miPopupEstudiante').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
        $('#miPopupEstudiante').find('#txtDireccion').val(data['direccionUsuario']).parents('.row:first').show();
        $('#miPopupEstudiante').find('#txtTelefono').val(data['telefonoFijo']);
        $('#miPopupEstudiante').find('#txtCelular').val(data['telefonoMovil']);
        $('#miPopupEstudiante').find('#txtCorreo').val(data['emailUsuario']);
        $('#miPopupEstudiante').find('#radioGeneroFemenino').parents('.row:first').show();
        if (data['generoUsuario'] == 0)
            $('#miPopupEstudiante').find('#radioGeneroFemenino').prop('checked', true).parents('.row:first').show();
        else
            $('#miPopupEstudiante').find('#radioGeneroMasculino').prop('checked', true);

        if (data['estadoBeneficiario'] == 0)
            $('#miPopupEstudiante').find('#radioNoBeneficiario').prop('checked', true);
        else
            $('#miPopupEstudiante').find('#radioSiBeneficiario').prop('checked', true)

        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#form_estudiante');
        $('#miPopupEstudiante').modal('show');
    },
    consultarPreinscrito: function (data) {
        limpiar("#form_estudiante");
        estudiante.consultar(data);
        $('#miPopupEstudiante').find('#txtDireccion').parents('.row:first').hide();
        $('#miPopupEstudiante').find('#radioGeneroFemenino').parents('.row:first').hide();
    },
    preinscribir: function (data) {
        limpiar("#form_estudiante");
        estudiante.consultar(data);
        $('#miPopupEstudiante').find('#titulo').empty();
        $('#miPopupEstudiante').find('#titulo').append('Formalizar Inscripcion');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'submit').attr('value', 'Formalizar Inscripcion').attr('disabled', false);
        $('#miPopupEstudiante').modal('show');
    },
    registrar: function () {
        habilitar('#form_estudiante');
        limpiar("#form_estudiante");
        $('#miPopupEstudiante').find('#titulo').empty();
        $('#miPopupEstudiante').find('#titulo').append('Registrar Estudiante');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'submit').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupEstudiante').modal('show');
    },
    editar: function (data) {
        limpiar("#form_estudiante");
        estudiante.consultar(data);
        $('#miPopupEstudiante').find('#titulo').empty();
        $('#miPopupEstudiante').find('#titulo').append('Editar Estudiante');
        habilitar('#form_estudiante');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    cargar: function () {
        tablaEstudiante = $('#tblEstudiantes').DataTable({
            "ajax": {
                "url": "ControllerEstudiante",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaEstudiante.ajax.reload();
    }
};

var preinscrito = {
    cargar: function () {
        tablaPreinscritos = $('#tblPreinscritos').DataTable({
            "ajax": {
                "url": "ControllerEstudiante",
                "type": "POST",
                "data": {
                    action: 'EnlistarPreinscritos'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaPreinscritos.ajax.reload();
    }
};

var usuario = {
    myAjax: function (accion, id, tipo, aux) {
        var form = $('#formUsuario');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                success: function (data) {
                    if (accion == 'Consultar') {
                        if (aux == 'Editar') {
                            usuario.editar(data);
                        } else if (aux == 'Preinscribir') {
                            usuario.preinscribir(data);
                        } else
                            usuario.consultar(data);
                    }
                    else if (accion == 'Registrar' || accion == 'Editar' || accion == 'Estado') {
                        if (accion != 'Estado') {
                            $('#miPopupUsuario').modal('hide');
                        }
                        mensaje(data);
                        usuario.actualizarTabla();
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar') {
            $(form).submit();
        }
    },
    preinscribir: function (data) {
        limpiar("#formPreinscripcion");
        $('#miPopupPreinscripcion').find('#titulo').empty();
        $('#miPopupPreinscripcion').find('#titulo').append('Preinscribir al ' + data['tipo']);
        $('#miPopupPreinscripcion').find('#dateFinFicha').empty();
        $('#miPopupPreinscripcion').modal('show');
    },
    consultar: function (data) {
        usuario.habilitar();
        limpiar("#formUsuario");
        $('#miPopupUsuario').find('#titulo').empty();
        $('#miPopupUsuario').find('#titulo').append('Consultar Usuario');
        $('#miPopupUsuario').find('#txtIdentificacion').val(data['numeroDocumento']);
        $('#miPopupUsuario').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
        $('#miPopupUsuario').find('#txtNombre').val(data['nombreUsuario']);
        $('#miPopupUsuario').find('#txtApellido').val(data['apellidoUsuario']);
        $('#miPopupUsuario').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
        $('#miPopupUsuario').find('#txtCorreo').val(data['emailUsuario']);
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#formUsuario');
        $('#miPopupUsuario').modal('show');
    },
    registrar: function () {
        usuario.habilitar();
        habilitar('#formUsuario');
        limpiar("#formUsuario");
        $('#miPopupUsuario').find('#titulo').empty();
        $('#miPopupUsuario').find('#titulo').append('Registro');
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'submit').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupUsuario').modal('show');
    },
    editar: function (data) {
        limpiar("#formUsuario");
        usuario.consultar(data);
        $('#miPopupUsuario').find('#titulo').empty();
        $('#miPopupUsuario').find('#titulo').append('Actualizar Datos');
        habilitar('#formUsuario');
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    cargar: function () {
        tablaUsuario = $('#tblUsuarios').DataTable({
            "ajax": {
                "url": "ControllerUsuario",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaUsuario.ajax.reload();
    },
    recuperarPass: function () {
        limpiar('#formUsuario');
        $('#miPopupUsuario').find('#titulo').empty();
        $('#miPopupUsuario').find('#titulo').append('Recuperar Contraseña');
        $('#miPopupUsuario').find('#txtNombre').attr('type', 'hidden').attr('disabled', true).parents('.row:first').hide();
        $('#miPopupUsuario').find('#txtApellido').attr('type', 'hidden').attr('disabled', true).parents('.row:first').hide();
        $('#miPopupUsuario').find('#dateFechaNacimiento').attr('type', 'hidden').attr('disabled', true).parents('.row:first').hide();
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'submit').attr('value', 'Recuperar').attr('disabled', false);
        $('#miPopupUsuario').modal('show');
    },
    habilitar: function () {
        $('#miPopupUsuario').find('#txtNombre').attr('type', 'text').attr('disabled', false).parents('.row:first').show();
        $('#miPopupUsuario').find('#txtApellido').attr('type', 'text').attr('disabled', false).parents('.row:first').show();
        $('#miPopupUsuario').find('#dateFechaNacimiento').attr('type', 'text').attr('disabled', false).parents('.row:first').show();
    }
};

var matricula = {
    registrar: function () {
        $.ajax({
            url: "ControllerMatricula",
            type: 'POST',
            data: $('#formMatricula').serialize() + '&action=Registrar',
            success: function (data, textStatus, jqXHR) {
                $('#miPopupMatricula').modal('hide');
                mensaje(data);
            }
        });
    },
    cargar: function () {
        tablaMatricula = $('#tblMatriculas').DataTable({
            "ajax": {
                "url": "ControllerMatricula",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaMatricula.ajax.reload();
    }
};

var categoriaArticulo = {
    myAjax: function (accion) {
        var form = $('#formCategoriaArticulo');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion,
                success: function (data) {
                    categoriaArticulo.actualizarTabla();
                    if (accion === 'Editar' || accion === 'Registrar') {
                        $('#miPopupCategoriaArticulo').modal('hide');
                        mensaje(data);
                    }
                }
            }
            );
            $(form).off();
            return false;
        });
    },
    registrar: function () {
        limpiar('#formCategoriaArticulo');
        $('#miPopupCategoriaArticulo').find('#titulo').empty();
        $('#miPopupCategoriaArticulo').find('#titulo').append('Registrar Categoría Artículo');
        $('#miPopupCategoriaArticulo').find('#btnCategoriaArticulo').attr('value', 'Registrar');
        $('#miPopupCategoriaArticulo').modal('show');
    },
    editar: function (tr) {
        var data = tablaCategoriaArticulo.row(tr).data();
        $('#miPopupCategoriaArticulo').find('#titulo').empty();
        $('#miPopupCategoriaArticulo').find('#titulo').append('Editar Categoría Artículo');
        $('#miPopupCategoriaArticulo').find('#idCategoriaArticulo').val(data[0]);
        $('#miPopupCategoriaArticulo').find('#txtNombreCategoriaArticulo').val(data[1]);
        $('#miPopupCategoriaArticulo').find('#btnCategoriaArticulo').val('Editar');
        $('#miPopupCategoriaArticulo').modal('show');
    },
    cargar: function () {
        tablaCategoriaArticulo = $('#tblCategoriaArticulos').DataTable({
            "ajax": {
                "url": "ControllerCategoriaArticulo",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        categoriaArticulo.myAjax('getOptionsCategorias');
        tablaCategoriaArticulo.ajax.reload();
    },
    cargarOpciones: function () {
        $.ajax({
            url: "ControllerCategoriaArticulo",
            type: 'POST',
            data: {
                action: 'getOptionsCategorias'
            },
            success: function (data, textStatus, jqXHR) {
                $('#miPopupArticulo').find('#idCategoriaArticulo').empty();
                $('#miPopupArticulo').find('#idCategoriaArticulo').append(data);
            }
        });
    }
};

var articulo = {
    myAjax: function (accion) {
        var form = $('#formArticulo');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion,
                success: function (data) {
                    if (accion == 'Registrar' || accion == 'Editar') {
                        $('#miPopupArticulo').modal('hide');
                        mensaje(data);
                        articulo.actualizarTabla();
                        $("#ddlArticulos").select2('destroy');
                        articulo.listarArticulos();
                    } else if (accion == 'ConsultarCodigo') {
                        articulo.registrar(data);
                    }
                    else if (accion === 'getOptionsArticulos') {
                        articulo.cargarOpciones(data);
                    }
                }
            }
            );
            $(form).off();
            return false;
        });
        if (accion === 'getOptionsCategorias' || accion == 'ConsultarCodigo') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#formArticulo');
        $('#miPopupArticulo').find('#titulo').empty();
        $('#miPopupArticulo').find('#titulo').append('Registrar Artículo');
        $('#miPopupArticulo').find('#btnArticulo').attr('value', 'Registrar');
        $('#miPopupArticulo').find('#txtPrecioCompra').attr('readOnly', false);
        $('#miPopupArticulo').find('#txtPrecioVenta');
        $('#miPopupArticulo').find('#txtCantidadArticulo').attr('readOnly', false);
        articulo.contador();
        $('#miPopupArticulo').modal('show');
    },
    editar: function (tr) {
        var data = tablaArticulo.row(tr).data();
        $('#miPopupArticulo').find('#titulo').empty();
        $('#miPopupArticulo').find('#titulo').append('Editar Artículo');
        $('#miPopupArticulo').find('#idArticulo').val(data[0]);
        $('#miPopupArticulo').find('#txtDescripcion').val(data[2]);
        $('#miPopupArticulo').find('#txtCantidadArticulo').val(data[3]).attr('readOnly', true);
        $('#miPopupArticulo').find('#txtPrecioCompra').val(data[4]).attr('readOnly', true);
        $('#miPopupArticulo').find('#txtPrecioVenta').val(data[5]);
        $('#miPopupArticulo').find('#idCategoriaArticulo option').prop('selected', false).filter(function () {
            return ($(this).text() == data[1]);
        }).prop('selected', true);
        $('#miPopupArticulo').find('#btnArticulo').val('Editar');
        $('#miPopupArticulo').modal('show');
    },
    seleccionar: function (id, tipo) {
        var x = articulo.noExiste(id);
        if (x) {
            $.ajax({
                type: 'POST',
                url: "ControllerArticulo",
                dataType: 'JSON',
                data: {
                    action: 'Consultar',
                    id: id
                },
                success: function (data) {
                    /**
                     * `idArticulo`, `idCategoriaArticulo`, `descripcionArticulo`,
                     * `cantidadDisponible`, `precioCompra`, `precioVenta`
                     */
                    var fila = '<tr  data-id="' + data['idArticulo'] + '">';
                    fila += '<td>' + data['idArticulo'] + '</td>';
                    fila += '<td>' + data['descripcionArticulo'] + '</td>';
                    if (tipo === 'Compra') {
                        fila += '<td>' + '<input type="number" id="cantidad" onblur="compra.actualizarTotal(\'cantidad\')" name="cantidad" min="1" required>' + '</td>';
                        fila += '<td>' + '<input type="number" id="valor" onblur="compra.actualizarTotal(\'valor\')" name="valor" min="50" required>' + '</td>';
                    } else {
                        fila += '<td>' + '<input type="number" id="cantidad" onblur="compra.actualizarTotal(\'cantidad\')" name="cantidad" min="1" max="' + data['cantidadDisponible'] + '" required>' + '</td>';
                        fila += '<td>' + '<input type="number" id="valor" onblur="compra.actualizarTotal(\'valor\')" name="valor" min="50" value="' + data['precioVenta'] + '" required>' + '</td>';
                    }
                    fila += '<td>' + '<button class="btn btn-danger glyphicon glyphicon-remove row-remove" onclick="articulo.remover(' + data['idArticulo'] + ')"></button>' + '</td>';
                    fila += '</tr>';
                    $('#tablaDetalleMovimiento tbody').append(fila);
                }
            });
        }
    },
    noExiste: function (id) {
        var flag = true;
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            if ($(this).data('id') == id) {
                flag = false;
            }
        });
        return flag;
    },
    remover: function (id) {
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            if ($(this).data('id') == id) {
                $(this).remove();
            }
        });
    },
    cargar: function () {
        tablaArticulo = $('#tblArticulos').DataTable({
            "ajax": {
                "url": "ControllerArticulo",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    listarArticulos: function (tipo) {
        var accion = null;
        var f = new Date();
        var fechaActual = (f.getDate() + "/" + (f.getMonth() + 1) + "/" + f.getFullYear());
        $('#tabCompras').find('#txtFechaCompra').append('Fecha: ' + fechaActual);
        if (tipo === 'Venta') {
            accion = 'Venta';
        }
        $.ajax({
            type: 'POST',
            url: 'ControllerArticulo',
            dataType: 'JSON',
            data: {
                action: 'getOptionsArticulos',
                tipo: accion
            },
            success: function (data) {
                $("#ddlArticulos").select2({
                    data: data,
                    language: "es",
                    placeholder: "Selecciona los articulos",
                    allowClear: true
                });
            }
        });
    },
    actualizarTabla: function () {
        tablaArticulo.ajax.reload();
    },
    contador: function () {
        $.ajax({
            url: "ControllerArticulo",
            type: 'POST',
            data: {
                action: 'Contador'
            },
            success: function (data, textStatus, jqXHR) {
                $('#miPopupArticulo').find('#txtIdArticulo').text('Codigo: ' + data['idArticulo']);
            }
        });
    }
};

var empresa = {
    myAjax: function (accion, id) {
        var form = $('#formEmpresa');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id,
                success: function (data) {
                    if (accion == 'Editar' || accion == 'Registrar') {
                        $('#miPopupEmpresa').modal('hide');
                        empresa.actualizarTabla();
                        mensaje(data);
                    } else if (accion == 'getOptionsEmpresas') {
                        empresa.cargarOpciones(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar' || accion === 'getOptionsEmpresas') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#formEmpresa');
        $('#miPopupEmpresa').find('#titulo').empty();
        $('#miPopupEmpresa').find('#titulo').append('Registrar Empresa');
        $('#miPopupEmpresa').find('#btnEmpresa').attr('value', 'Registrar');
        $('#miPopupEmpresa').modal('show');
    },
    editar: function (tr) {
        var data = tablaEmpresa.row(tr).data();
        $('#miPopupEmpresa').find('#titulo').empty();
        $('#miPopupEmpresa').find('#titulo').append('Editar Empresa');
        $('#miPopupEmpresa').find('#txtNitEmpresa').val(data[0]);
        $('#miPopupEmpresa').find('#txtNombreEmpresa').val(data[1]);
        $('#miPopupEmpresa').find('#txtDireccionEmpresa').val(data[2]);
        $('#miPopupEmpresa').find('#txtNombreContacto').val(data[3]);
        $('#miPopupEmpresa').find('#txtTelefonoContacto').val(data[4]);
        $('#miPopupEmpresa').find('#txtEmailContacto').val(data[5]);
        $('#miPopupEmpresa').find('#btnEmpresa').val('Editar');
        $('#miPopupEmpresa').modal('show');
    },
    cargar: function () {
        tablaEmpresa = $('#tblEmpresas').DataTable({
            "ajax": {
                "url": "ControllerEmpresa",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaEmpresa.ajax.reload();
    },
    cargarOpciones: function (data) {
        $('#form_estudiante').find('#idEmpresa').empty();
        $('#form_estudiante').find('#idEmpresa').append(data);
    }
};

var compra = {
    myAjax: function (accion, id) {
        var form = $('#formMovimiento');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id,
                success: function (data) {
                    if (accion == 'Consultar') {
                        $('#btnGestionCompras').tab('show')
                        compra.consultar(data);
                    }
                    else if (accion === 'getOptionsCompra') {
                        compra.cargarOpciones(data);
                        $("#ddlArticulos").destroy();
                        articulo.listarArticulos();
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion == 'Consultar' || accion === 'getOptionsCompra') {
            $(form).submit();
        }
    },
    actualizarTotal: function () {
        var salida = 0;
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            var elementos = {cantidad: 0, precioArticulo: 0};
            elementos.cantidad = $(this).find('#cantidad').val();
            elementos.precioArticulo = $(this).find('#valor').val();
            salida += elementos.cantidad * elementos.precioArticulo;
        });
        $('#tabMovimientos').find('#txtTotalMovimiento').val(salida);
    },
    cargar: function () {
        tablaCompra = $('#tblCompra').DataTable({
            "ajax": {
                "url": "ControllerCompra",
                "type": "POST",
                "data": {
                    action: 'Enlistar'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    consultar: function (id) {
        $.ajax({
            type: 'POST',
            url: "ControllerCompra",
            data: {
                id: id,
                action: 'Consultar'
            },
            success: function (data, textStatus, jqXHR) {
                compra.show('Consultar', data);
            }
        });
    },
    efectuarCompra: function () {
        $('#tabMovimientos').find('#btnMovimiento').attr("type", "submit");
        var form = $('#formMovimiento');
        $(form).off();
        $(form).on('submit', function () {
            var lista = Array();
            $('#tablaDetalleMovimiento tbody tr').each(function () {
                var elementos = {idArticulo: '', cantidad: '', precioArticulo: ''};
                elementos.idArticulo = $(this).data('id');
                elementos.cantidad = $(this).find('#cantidad').val();
                elementos.precioArticulo = $(this).find('#valor').val();
                lista.push(elementos);
            });
            if (lista.length > 0) {
                var nombre = $('#tabMovimientos').find('#txtNombre').val();
                var numeroFactura = $('#tabMovimientos').find('#txtNumero').val();
                var total = $('#tabMovimientos').find('#txtTotalMovimiento').val();
                $.ajax({
                    type: 'POST',
                    url: "ControllerCompra",
                    data: {
                        action: 'Registrar',
                        lista: lista,
                        size: lista.length,
                        txtNombre: nombre,
                        txtNumeroFactura: numeroFactura,
                        txtTotalCompra: total,
                        documentoUsuario: documentoUsuario
                    },
                    success: function (data, textStatus, jqXHR) {
                        compra.limpiarDetalle();
                        $("#ddlArticulos").val(null);
                        limpiar('#formMovimiento');
                        mensaje(data);
                        compra.actualizarTabla();
                    }
                });
            } else {
                $.notify('Una compra debe contener almenos un artículo', 'error');
                $(form).off();
                return false;
            }
            $(form).off();
            return false;
        });

    },
    actualizarTabla: function () {
        tablaCompra.ajax.reload();
    },
    limpiarDetalle: function () {
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            $(this).remove();
        });
    },
    imprimir: function (idMovimiento) {
        var link = document.createElement('a');
        link.href = window.URL = "ControllerCompra?action=Imprimir&id=" + idMovimiento;
        //link.href = window.URL = "ControllerCompra?action=Imprimir2&id=" + idMovimiento;
        link.download = "Compra_" + idMovimiento + ".pdf";
        link.click();
    },
    show: function (tipo, datos) {
        $("#ddlArticulos").off();
        $("#ddlArticulos").on("select2:select", function (e) {
            var id = e.params.data.id;
            if (id != '-1') {
                articulo.seleccionar(id, 'Compra');
            }
        });
        compra.limpiarDetalle();
        $('#contenidoDinamico').data('actual', 'compra');
        $('#tabMovimientos').find('#nombre').text('Nombre del Proveedor');
        $('#tabMovimientos').find('#numero').text('Numero de Factura ');
        $('#tabMovimientos').find('#total').text('Total compra');
        $('#tabMovimientos').find('#txtDocumentoCliente').attr('disabled', true).parents('.row:first').hide();
        if (tipo === 'Registrar') {
            $('#tabMovimientos').find('#titulo').text('Registrar Compra');
            $('#tabMovimientos').find('#txtNumero').attr('readOnly', false);
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
            $('#tabMovimientos').find('#btnArticulo').show();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'compra.efectuarCompra()').val('Efectuar Compra');
            $('#tabMovimientos').find('#ddlArticulos').attr('disabled', false).parents('.row:first').show();
            $('#tabMovimientos').find('#btnArticulo').attr('disabled', false).parents('.row:first').show();
        } else if (tipo === 'Consultar') {
            $('#tabMovimientos').find('#titulo').text('Consultar Compra');
            $('#btnGestionCompras').data('target', '#tabMovimientos').tab('show');
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + datos.Compra.fechaCompra);
            $('#tabMovimientos').find('#txtNombre').val(datos.Compra.nombreProveedor).attr('readOnly', true);
            $('#tabMovimientos').find('#txtNumero').val(datos.Compra.facturaProveedor).attr('readOnly', true);
            $('#tabMovimientos').find('#txtTotalMovimiento').val(datos.Compra.totalCompra);
            $('#tabMovimientos').find('#ddlArticulos').attr('disabled', true).parents('.row:first').hide();
            $('#tabMovimientos').find('#btnArticulo').attr('disabled', true).parents('.row:first').hide();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'compra.imprimir(' + datos.Compra.idMovimiento + ')').attr('type', 'button').val('Imprimir Compra');
            $.each(datos["Detalle"], function (index, element) {
                var fila = '<tr  data-id="' + element.idArticulo + '">';
                fila += '<td>' + element.idArticulo + '</td>';
                fila += '<td>' + element.descripcionArticulo + '</td>';
                fila += '<td>' + element.cantidad + '</td>';
                fila += '<td>' + element.precioArticulo + '</td>';
                fila += '<td>' + '</td>';
                fila += '</tr>';
                $('#tablaDetalleMovimiento tbody').append(fila);
            });
        }
    }
};

var venta = {
    actualizarTabla: function () {
        tablaVenta.ajax.reload();
    },
    actualizarTotal: function () {
        var salida = 0;
        $('#tablaDetalleVenta tbody tr').each(function () {
            var elementos = {cantidad: 0, precioArticulo: 0};
            elementos.cantidad = $(this).find('#cantidad').val();
            elementos.precioArticulo = $(this).find('#valor').val();
            salida += elementos.cantidad * elementos.precioArticulo;
        });
        $('#tabCompras').find('#txtTotalCompra').val(salida);
    },
    cargar: function () {
        tablaVenta = $('#tblVentas').DataTable({
            "ajax": {
                url: 'ControllerVenta',
                type: 'POST',
                data: {
                    action: 'Enlistar'
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    consultar: function (id) {
        $.ajax({
            type: 'POST',
            url: "ControllerVenta",
            data: {
                id: id,
                action: 'Consultar'
            },
            success: function (data, textStatus, jqXHR) {
                venta.show('Consultar', data);
            }
        });
    },
    contador: function () {
        $.ajax({
            url: "ControllerVenta",
            type: 'POST',
            data: {
                action: 'Contador'
            },
            success: function (data, textStatus, jqXHR) {
                $('#tabMovimientos').find('#txtNumero').val(data['numero']);
            }
        });
    },
    efectuarVenta: function () {
        $('#tabMovimientos').find('#btnMovimiento').attr("type", "submit");
        var form = $('#formMovimiento');
        $(form).off();
        $(form).on('submit', function () {
            var lista = Array();
            $('#tablaDetalleMovimiento tbody tr').each(function () {
                var elementos = {idArticulo: '', cantidad: '', precioArticulo: ''};
                elementos.idArticulo = $(this).data('id');
                elementos.cantidad = $(this).find('#cantidad').val();
                elementos.precioArticulo = $(this).find('#valor').val();
                lista.push(elementos);
            });
            if (lista.length > 0) {
                var nombre = $('#tabMovimientos').find('#txtNombre').val();
                var numeroVenta = $('#tabMovimientos').find('#txtNumero').val();
                var documentoCliente = $('#tabMovimientos').find('#txtDocumentoCliente').val();
                var total = $('#tabMovimientos').find('#txtTotalMovimiento').val();
                alert(documentoUsuario);
                $.ajax({
                    type: 'POST',
                    url: "ControllerVenta",
                    data: {
                        action: 'Registrar',
                        lista: lista,
                        size: lista.length,
                        txtNombreCliente: nombre,
                        txtNumeroVenta: numeroVenta,
                        txtTotalVenta: total,
                        documentoUsuario: documentoUsuario,
                        documentoCliente: documentoCliente
                    },
                    success: function (data, textStatus, jqXHR) {
                        venta.limpiarDetalle();
                        $("#ddlArticulos").val(null);
                        limpiar('#formMovimiento');
                        mensaje(data);
                        venta.actualizarTabla();
                    }
                });
            } else {
                $.notify('Una venta debe contener almenos un artículo', 'error');
                $(form).off();
                return false;
            }
            $(form).off();
            return false;
        });

    },
    imprimir: function (idMovimiento) {
        var link = document.createElement('a');
        link.href = window.URL = "ControllerVenta?action=Imprimir&id=" + idMovimiento;
        link.download = "Venta_" + idMovimiento + ".pdf";
        link.click();
    },
    show: function (tipo, datos) {
        $("#ddlArticulos").off();
        $("#ddlArticulos").on("select2:select", function (e) {
            var id = e.params.data.id;
            if (id != '-1') {
                articulo.seleccionar(id, 'Venta');
            }
        });
        venta.limpiarDetalle();
        $('#contenidoDinamico').data('actual', 'venta');
        $('#tabMovimientos').find('#nombre').text('Nombre del Cliente');
        $('#tabMovimientos').find('#numero').text('Numero de Venta');
        $('#tabMovimientos').find('#total').text('Total venta');
        $('#tabMovimientos').find('#txtDocumentoCliente').attr('disabled', false).parents('.row:first').show();
        if (tipo === 'Registrar') {
            $('#tabMovimientos').find('#titulo').text('Registrar Venta');
            $('#tabMovimientos').find('#txtNumero').attr('readOnly', true);
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
            $('#tabMovimientos').find('#btnArticulo').hide();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'venta.efectuarVenta()').val('Efectuar Venta');
            $('#tabMovimientos').find('#ddlArticulos').attr('disabled', false).parents('.row:first').show();
            $('#tabMovimientos').find('#btnArticulo').attr('disabled', false).parents('.row:first').show();
            venta.contador();
        } else if (tipo === 'Consultar') {
            $('#tabMovimientos').find('#titulo').text('Consultar Venta');
            $('#btnGestionVentas').data('target', '#tabMovimientos').tab('show');
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + datos.Venta.fechaVenta);
            $('#tabMovimientos').find('#txtNombre').val(datos.Venta.nombreCliente).attr('readOnly', true);
            $('#tabMovimientos').find('#txtNumero').val(datos.Venta.numeroVenta).attr('readOnly', true);
            $('#tabMovimientos').find('#txtDocumentoCliente').val(datos.Venta.documentoCliente).attr('readOnly', true);
            $('#tabMovimientos').find('#txtTotalMovimiento').val(datos.Venta.totalVenta);
            $('#tabMovimientos').find('#ddlArticulos').attr('disabled', true).parents('.row:first').hide();
            $('#tabMovimientos').find('#btnArticulo').attr('disabled', true).parents('.row:first').hide();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'venta.imprimir(' + datos.Venta.idMovimiento + ')').attr('type', 'button').val('Imprimir Venta');
            $.each(datos["Detalle"], function (index, element) {
                var fila = '<tr  data-id="' + element.idArticulo + '">';
                fila += '<td>' + element.idArticulo + '</td>';
                fila += '<td>' + element.descripcionArticulo + '</td>';
                fila += '<td>' + element.cantidad + '</td>';
                fila += '<td>' + element.precioArticulo + '</td>';
                fila += '<td>' + '</td>';
                fila += '</tr>';
                $('#tablaDetalleMovimiento tbody').append(fila);
            });
        }
    },
    limpiarDetalle: function () {
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            $(this).remove();
        });
    }
};

var credito = {
    show: function (tipo, datos) {
        $("#ddlArticulos").off();
        $("#ddlArticulos").on("select2:select", function (e) {
            var id = e.params.data.id;
            if (id != '-1') {
                articulo.seleccionar(id, 'Venta');
            }
        });
        credito.limpiarDetalle();
        $('#contenidoDinamico').data('actual', 'credito');
        $('#tabMovimientos').find('#nombre').text('Nombre del Cliente');
        $('#tabMovimientos').find('#numero').text('Numero del Credito');
        $('#tabMovimientos').find('#total').text('Total Credito');
        if (tipo === 'Registrar') {
            $('#tabMovimientos').find('#titulo').text('Registrar Credito');
            $('#tabMovimientos').find('#txtNumero').attr('readOnly', true);
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
            $('#tabMovimientos').find('#btnArticulo').hide();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'credito.registrarCredito()').val('Registrar Credito');
            $('#tabMovimientos').find('#ddlArticulos').attr('disabled', false).parents('.row:first').show();
            $('#tabMovimientos').find('#btnArticulo').attr('disabled', true).parents('.row:first').hide;
            credito.contador();
        }

    },
    limpiarDetalle: function () {
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            $(this).remove();
        });
    },
    contador: function () {
        $.ajax({
            url: "ControllerCredito",
            type: 'POST',
            data: {
                action: 'Contador'
            },
            success: function (data, textStatus, jqXHR) {
                $('#tabMovimientos').find('#txtNumero').val(data['numero']);
            }
        });
    }
};

compra.cargar();
venta.cargar();
categoriaCurso.cargar();
curso.cargar();
seminario.cargar();
//abono.cargar();
estudiante.cargar();
categoriaArticulo.cargar();
articulo.cargar();
//matricula.cargar();
empresa.cargar();
usuario.cargar();
//credito.cargar()
categoriaArticulo.cargarOpciones();
preinscrito.cargar();
