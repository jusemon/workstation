/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global documentoUsuario */

$('.fecha').datepicker({
    format: "dd/mm/yyyy",
    language: "es",
    orientation: "top left"
});
$('.fecha2').datetimepicker({
    locale: "es"
});
var validationCurso, validationArticulo, validationEstudiante, validationUsuario, validationEmpresa, validationUsuarioPerfil, validationBeneficiario, validationOperarios, validationAsistenteSeminario, validationAcudiente;
var tablaCurso, tablaCategoriaCurso, tablaClases, tablaCredito, tablaSeminario, tablaEstudiante, tablaMatricula, tablaArticulo, tablaCategoriaArticulo, tablaEmpresa, tablaCompra, tablaVenta, tablaUsuario, idCurso, tablaPreinscritos, tablaPreincripciones, tablaOperarios, tablaDetalleSeminario;
var curso = {
    myAjax: function (accion, id, aux, typo) {
        if (accion === 'Estado') {
            $.ajax({
                url: "ControllerCurso",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    type: typo
                },
                success: function (data) {
                    mensaje(data);
                    curso.actualizarTabla();
                }
            });
        }
        else if (accion === 'Consultar') {
            $.ajax({
                url: "ControllerCurso",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    type: typo
                },
                success: function (data) {
                    if (aux === 'Editar') {
                        curso.editar(data);
                    }
                    else {
                        curso.consultar(data);
                    }
                }

            });
        }
        else if (accion === 'Registrar' || accion === 'Editar') {
            if (validationCurso.valid()) {
                var formulario = $('#formCurso');
                $.ajax({
                    url: $(formulario).attr('action'),
                    type: $(formulario).attr('method'),
                    data: $(formulario).serialize() + '&action=' + accion + '&id=' + id + '&type=' + typo,
                    success: function (data) {
                        mensaje(data);
                        curso.actualizarTabla();
                        seminario.actualizarTabla();
                        $('#miPopupCurso').modal('hide');
                    }

                });
            } else {
                $.notify('Uno o más campos contienen datos erroneos', 'error');
            }
        }
        else if (accion === 'getOptionsCursos') {
            $.ajax({
                url: "ControllerCurso",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    type: typo
                },
                success: function (data) {
                    curso.cargarOpciones(data);
                }
            });
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
            $('#miPopupMatricula').find('#txtClases').val(null).parents('.row:first').attr('readOnly', false).hide();
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
        categoriaCurso.myAjax('getOptionsCategorias');
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
        $('#miPopupCurso').find('#txtFechaSeminario').parents('.row:first').hide();
        $('#miPopupCurso').find('#txtCupoSeminario').parents('.row:first').hide();
        $('#miPopupCurso').find('#ddlEstado option').prop('selected', false).filter('[value="' + data['estadoCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#formCurso');
        $('#miPopupCurso').modal('show');
    },
    registrar: function () {
        limpiar("#formCurso");
        habilitar('#formCurso');
        categoriaCurso.myAjax('getOptionsCategorias');
        $('#miPopupCurso').find('#titulo').text('Registrar Curso');
        $('#miPopupCurso').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupCurso').find('#tipo').val('Curso');
        $('#miPopupCurso').find('#txtFechaSeminario').parents('.row:first').hide();
        $('#miPopupCurso').find('#txtCupoSeminario').parents('.row:first').hide();
        $('#miPopupCurso').find('#ContenedorCategoria').show();
        $('#miPopupCurso').find('#btnCurso').attr('type', 'button').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupCurso').modal('show');
    },
    editar: function (data) {
        curso.consultar(data);
        habilitar('#formCurso');
        $('#miPopupCurso').find('#titulo').text('Editar Curso');
        $('#miPopupCurso').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupCurso').find('#tipo').val('Curso');
        $('#miPopupCurso').find('#ContenedorCategoria').show();
        $('#miPopupCurso').find('#btnCurso').attr('type', 'button').attr('value', 'Editar').attr('disabled', false);
    },
    preinscripcion: function (idCurso, btn) {
        $('.notifyjs-foo-base ').trigger('notify-hide');
        $(document).off('click', '.notifyjs-foo-base .no');
        $(document).off('click', '.notifyjs-foo-base .yes');
        if (typeof (documentoUsuario) !== 'undefined') {
            $(btn).notify({
                title: '¿Estás seguro?',
                button: 'Confirmar'
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
                    url: "ControllerMatricula",
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
            $.notify('Debe registrarse previamente.', 'error');
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
            async: false,
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
                    if (accion === 'Estado' || accion === 'Editar' || accion === 'Registrar') {
                        if (accion !== 'Estado') {
                            $('#miPopupFicha').modal('hide');
                        }
                        clase.actualizarTabla();
                        mensaje(data);
                    } else if (accion === 'getOptionsClases') {
                        clase.cargarOpciones(data);
                    } else if (accion === 'cursosDisponibles') {
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
var cliente = {
    seleccionar: function (id) {
        $.ajax({
            url: "ControllerUsuario",
            type: 'POST',
            data: {
                action: 'Consultar',
                id: id
            },
            success: function (data, textStatus, jqXHR) {
                $('#tabMovimientos').find('#txtIdentificacion').val(id.substring(2));
                $('#tabMovimientos').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + id.substring(0, 2) + '"]').prop('selected', true);
                $('#tabMovimientos').find('#txtNombre').val(data.nombreUsuario + ' ' + data.apellidoUsuario).attr('readOnly', true);
            }
        });
    }
};
var seminario = {
    myAjax: function (accion, id, aux, typo) {
        var form = $('#formCurso');
        $.ajax({
            type: $(form).attr('method'),
            url: $(form).attr('action'),
            data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&type=' + typo,
            success: function (data) {
                if (accion === 'Consultar') {
                    if (aux === 'Editar') {
                        seminario.editar(data);
                    }
                    else
                        seminario.consultar(data);
                }
                else if (accion === 'Editar' || accion === 'Estado') {
                    if (accion !== 'Estado') {
                        $('#miPopupCurso').modal('hide');
                    }
                    mensaje(data);
                    seminario.actualizarTabla();
                }
            }
        });
    },
    consultar: function (data) {
        limpiar("#formCurso");
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Consultar Seminario');
        $('#miPopupCurso').find('#tipo').val('Seminario');
        $('#miPopupCurso').find('#idCurso').val(data['idCurso']);
        $('#miPopupCurso').find('#txtNombreCurso').val(data['nombreCurso']);
        $('#miPopupCurso').find('#txtCantidadClases').val(data['cantidadClases']);
        $('#miPopupCurso').find('#ddlCategoria').empty();
        $('#miPopupCurso').find('#ddlCategoria').append('<option>Seminario</option>').attr('disabled', true);
        $('#miPopupCurso').find('#txtCantidadHoras').val(data['horasPorClase']);
        $('#miPopupCurso').find('#txtDescripcionCurso').val(data['descripcionCurso']);
        $('#miPopupCurso').find('#txtPrecio').val(data['precioCurso']);
        $('#miPopupCurso').find('#txtFechaSeminario').val(data['fechaSeminario']).parents('.row:first').show();
        $('#miPopupCurso').find('#txtCupoSeminario').val(data['cupoSeminario']).parents('.row:first').show();
        $('#miPopupCurso').find('#ddlEstado option').prop('selected', false).filter('[value="' + data['estadoCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#formCurso');
        $('#miPopupCurso').modal('show');
    },
    registrar: function () {
        limpiar('#formCurso');
        habilitar('#formCurso');
        $('#miPopupCurso').find('#titulo').text('Registrar Seminario');
        $('#miPopupCurso').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupCurso').find('#tipo').val('Seminario');
        $('#miPopupCurso').find('#txtFechaSeminario').parents('.row:first').show();
        $('#miPopupCurso').find('#txtCupoSeminario').parents('.row:first').show();
        $('#miPopupCurso').find('#txtCantidadClases').val(1).attr('readOnly', true);
        $('#miPopupCurso').find('#ddlCategoria').empty();
        $('#miPopupCurso').find('#ddlCategoria').append('<option>Seminario</option>').attr('disabled', true);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'button').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupCurso').modal('show');
    },
    editar: function (data) {
        seminario.consultar(data);
        habilitar('#formCurso');
        $('#miPopupCurso').find('#txtCantidadClases').attr('readOnly', true);
        $('#miPopupCurso').find('#ContenedorCategoria').hide().find('#ddlCategoria').attr('disabled', true);
        $('#miPopupCurso').find('#titulo').text('Editar Seminario');
        $('#miPopupCurso').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupCurso').find('#tipo').val('Seminario');
        $('#miPopupCurso').find('#btnCurso').attr('type', 'button').attr('value', 'Editar').attr('disabled', false);
    },
    preinscripcion: function (idCurso, btn) {
        $('.notifyjs-foo-base ').trigger('notify-hide');
        $(document).off('click', '.notifyjs-foo-base .no');
        $(document).off('click', '.notifyjs-foo-base .yes');
        if (typeof (documentoUsuario) !== "undefined") {
            $(btn).notify({
                title: '¿Estás seguro?',
                button: 'Confirmar'
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
                    url: "ControllerMatricula",
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
            $.notify('Debe registrarse previamente.', 'error');
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
            async: false,
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
            },
//            "bAutoWidth": false,
//            "aoColumns": [
//                {sWidth: '20%'},
//                {sWidth: '20%'},
//                {sWidth: '15%'},
//                {sWidth: '15%'},
//                {sWidth: '15%'},
//                {sWidth: '15%'}
//            ]
        });
    },
    cargarDetalle: function (idSeminario) {
        tablaDetalleSeminario = $('#tblDetalleSeminario').DataTable({
            "ajax": {
                "url": "ControllerCurso",
                "type": "POST",
                "data": {
                    action: 'DetalleAsistentesSeminario',
                    idSeminario: idSeminario
                }
            },
            "language": {
                "url": "public/js/locales/Spanish.json"
            },
//            "bAutoWidth": false,
//            "aoColumns": [
//                {sWidth: '20%'},
//                {sWidth: '20%'},
//                {sWidth: '15%'},
//                {sWidth: '15%'},
//                {sWidth: '15%'},
//                {sWidth: '15%'}
//            ]
        });
    },
    recargarDetalle: function () {
        tablaDetalleSeminario.ajax.reload();
    },
    actualizarTabla: function () {
        tablaSeminario.ajax.reload();
    },
    consultarDetalle: function (idSeminario) {
        $.ajax({
            url: "ControllerCurso",
            type: 'POST',
            data: {idSeminario: idSeminario, action: 'DetalleSeminario'},
            success: function (data, textStatus, jqXHR) {
                $('#miPopupDetalleSeminario').find('#titulo').text('Seminario ' + data.seminario.nombreCurso);
                $('#miPopupDetalleSeminario').find('#fecha').text(data.seminario.fechaSeminario);
                var cupos = data.seminario.cupoSeminario;
                $('#miPopupDetalleSeminario').find('#idSeminario').val(idSeminario);
                $('#miPopupDetalleSeminario').modal('show');
                seminario.cargarDetalle(idSeminario);
                $('#miPopupDetalleSeminario').find('#restantes').val(cupos);
            }
        });
    },
    mostrarRegistroAsistente: function () {
        var fechaSeminario = $('#miPopupDetalleSeminario').find('#fecha').text();
        if (validacionFechaRegistroASeminario(fechaSeminario) === true) {
            var idSeminario = $('#formDetalleSeminario').find('#idSeminario').val();
            $('#formAsistenteSeminario').find('#idSeminario').val(idSeminario);
            $('#miPopupAsistenteSeminario').modal('show');
        } else {
            $.notify('El seminario ya paso', 'error');
        }
    },
    registrarAsistente: function () {
        if (validationAsistenteSeminario.valid()) {
            $.ajax({
                url: $('#formAsistenteSeminario').attr('action'),
                type: $('#formAsistenteSeminario').attr('method'),
                data: $('#formAsistenteSeminario').serialize() + '&action=RegistrarAsistente',
                success: function (data) {
                    mensaje(data);
                    if (data['tipo'] === 'success') {
                        var cupos = $('#miPopupDetalleSeminario').find('#restantes').val();
                        $('#miPopupDetalleSeminario').find('#restantes').val(cupos - 1);
                        seminario.recargarDetalle();
                    }
                    $('#miPopupAsistenteSeminario').modal('hide');
                }
            });
        } else {
            $.notify("Uno o más campos contienen errores", "error")
        }

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
                    if (accion === 'Consultar') {
                        if (aux === 'Editar') {
                            abono.editar(data);
                        } else
                            abono.consultar(data);
                    }
                    else if (accion === 'Registrar' || accion === 'Editar') {
                        $('#miPopupAbono').modal('hide');
                        abono.mensaje(data);
                        abono.actualizarTabla();
                    }
                    else if (accion === 'Estado') {
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
    myAjax: function (accion, id, tipo, aux, aux2) {
        var form = $('#form_estudiante');
        $('#miPopupEstudiante').find('#ddlIdentificacion').attr('disabled', false);
        if (accion === 'Consultar') {
            $.ajax({
                url: "ControllerEstudiante",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    tipo: tipo
                },
                success: function (data, textStatus, jqXHR) {
                    if (tipo === 'Editar') {
                        estudiante.editar(data);
                    } else if (tipo === 'Matricular') {
                        estudiante.matricular(data);
                    } else if (tipo === 'Preinscrito') {
                        if (aux === 'Inscribir') {
                            estudiante.preinscribir(data, aux2);
                        } else
                            estudiante.consultarPreinscrito(data);
                    } else
                        estudiante.consultar(data);
                }
            });
        }
        else if (accion === 'Registrar' || accion === 'Editar') {
            if (validationEstudiante.valid()) {
                $.ajax({
                    type: $(form).attr('method'),
                    url: $(form).attr('action'),
                    data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                    success: function (data) {
                        if (accion === 'Editar') {

                        }
                        else {
                            if (data['tipo'] !== 'error') {
                                var identificacion = $('#miPopupEstudiante').find('#ddlIdentificacion').val() + $('#miPopupEstudiante').find('#txtIdentificacion').val();
                                matricula.registrarBeneficiario(identificacion);
                            }
                        }
                        $('#miPopupEstudiante').modal('hide');
                        mensaje(data);
                        estudiante.actualizarTabla();
                    }
                });
            } else {
                $.notify('Uno o más campos contienen datos erroneos', 'error');
            }
        }
        else if (accion === 'getOptionsFichas') {
            $.ajax({
                url: "ControllerEstudiante",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    tipo: tipo
                },
                success: function (data) {
                    clase.cargarOpciones(data);
                }
            });
        }
        else if (accion === 'Formalizar Inscripción') {
            $.ajax({
                url: "ControllerEstudiante",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    tipo: tipo
                },
                success: function (data) {
                    estudiante.formalizar(data);
                }
            });
        }
        else if (accion === 'Estado') {
            $.ajax({
                url: "ControllerEstudiante",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    tipo: tipo
                },
                success: function (data) {
                    mensaje(data);
                    estudiante.actualizarTabla();
                }
            });
        }
    },
    matricular: function (data) {
        limpiar("#formMatricula");
        curso.cargarOpciones();
        $('#miPopupMatricula').find('#titulo').text('Matricular Estudiante');
        $('#miPopupMatricula').find('#txtNombre').text(data["nombreUsuario"] + " " + data["apellidoUsuario"]);
        $('#miPopupMatricula').find('#txtIdentificacion').text(data['numeroDocumento']);
        var tipo = data['tipoDocumento'];
        tipo = (tipo === 'CC') ? 'Cédula' : tipo;
        tipo = (tipo === 'TI') ? 'Tarjeta de Identidad' : tipo;
        tipo = (tipo === 'RC') ? 'Registro Civil' : tipo;
        tipo = (tipo === 'CE') ? 'Cédula de Extranjeria' : tipo;
        $('#miPopupMatricula').find('#txtTipo').text(tipo);
        $('#miPopupMatricula').find('#txtDocumento').val(data['tipoDocumento'] + data['numeroDocumento']);
        $('#miPopupMatricula').find('#idCursoMatricula').prop('selected', false).attr('disabled', false);
        $('#miPopupMatricula').find('#txtPrecioCurso').empty().parents('.form-group:first').hide();
        $('#miPopupMatricula').find('#txtClases').attr('readOnly', false).val(null).parents('.row:first').hide();
        $('#miPopupMatricula').find('#estadoPago').attr('disabled', true).parents('.row:first').hide();
        $('#miPopupMatricula').find('#btnMatricula').attr('value', 'Matricular').attr('onclick', 'matricula.registrar()').show();
        $('#miPopupMatricula').modal('show');
    },
    consultar: function (data) {
        limpiar("#form_estudiante");
        $('#miPopupEstudiante').find('#titulo').text('Consultar Estudiante');
        $('#miPopupEstudiante').find('#txtIdentificacion').val(data['numeroDocumento']);
        $('#miPopupEstudiante').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
        $('#miPopupEstudiante').find('#txtNombre').val(data['nombreUsuario']);
        $('#miPopupEstudiante').find('#txtApellido').val(data['apellidoUsuario']);
        $('#miPopupEstudiante').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
        $('#miPopupEstudiante').find('#txtDireccion').val(data['direccionUsuario']).parents('.row:first').show();
        $('#miPopupEstudiante').find('#txtTelefono').val(data['telefonoFijo']);
        $('#miPopupEstudiante').find('#txtCelular').val(data['telefonoMovil']);
        $('#miPopupEstudiante').find('#txtCorreo').val(data['emailUsuario']);
        $('#miPopupEstudiante').find('#txtPass').val(data['password']);
        $('#miPopupEstudiante').find('#txtPass2').val(data['password']);
        $('#miPopupEstudiante').find('#radioGeneroFemenino').parents('.row:first').show();
        if (parseInt(data['generoUsuario']) === 0)
            $('#miPopupEstudiante').find('#radioGeneroFemenino').prop('checked', true).parents('.row:first').show();
        else
            $('#miPopupEstudiante').find('#radioGeneroMasculino').prop('checked', true);
        if (parseInt(data['estadoBeneficiario']) === 0)
            $('#miPopupEstudiante').find('#radioNoBeneficiario').prop('checked', true);
        else
            $('#miPopupEstudiante').find('#radioSiBeneficiario').prop('checked', true);
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
    preinscribir: function (data, idCurso) {
        limpiar("#form_estudiante");
        estudiante.consultar(data);
        $('#miPopupEstudiante').find('#idCurso').val(idCurso);
        $('#miPopupEstudiante').find('#titulo').text('Formalizar Inscripcion');
        $('#miPopupEstudiante').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'submit').attr('value', 'Formalizar Inscripción').attr('disabled', false);
        $('#miPopupEstudiante').find('#radioGeneroFemenino').prop('checked', false);
        $('#miPopupEstudiante').find('#radioGeneroMasculino').prop('checked', false);
        $('#miPopupEstudiante').find('#radioNoBeneficiario').prop('checked', false);
        $('#miPopupEstudiante').find('#radioSiBeneficiario').prop('checked', false);
        habilitar("#form_estudiante");
        $('#miPopupEstudiante').find('#txtIdentificacion').attr('readOnly', true);
        $('#miPopupEstudiante').find('#ddlIdentificacion').attr('disabled', true);
        $('#miPopupEstudiante').modal('show');
    },
    registrar: function () {
        habilitar('#form_estudiante');
        limpiar("#form_estudiante");
        $('#miPopupEstudiante').find('#titulo').text('Registrar Estudiante');
        $('#miPopupEstudiante').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'button').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupEstudiante').modal('show');
    },
    editar: function (data) {
        limpiar("#form_estudiante");
        estudiante.consultar(data);
        $('#miPopupEstudiante').find('#titulo').text('Editar Estudiante');
        $('#miPopupEstudiante').find('#obligatoriedad').text('Todos los campos son obligatorios');
        habilitar('#form_estudiante');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'button').attr('value', 'Editar').attr('disabled', false);
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
    },
    formalizar: function (data) {
        mensaje(data);
        if (data['tipo'] !== 'error') {
            $('#miPopupEstudiante').modal('hide');
            estudiante.matricular(data['estudiante']);
            $('#miPopupMatricula').find('#idCursoMatricula option').prop('selected', false).filter('[value="' + data['idCurso'] + '"]').prop('selected', true);
            curso.seleccionar(data['idCurso']);
            preinscrito.actualizarTabla();
        }
    },
    registrarAcudiente: function () {
        if (validationAcudiente.valid()) {
            $.ajax({
                url: "ControllerAcudiente",
                type: 'POST',
                data: $('#formAcudiente').serialize() + '&action=Registrar',
                success: function (data) {
                    if (data['tipo'] !== 'error') {
                        var identificacion = $('#miPopupAcudiente').find('#txtIdentificacionEstudiante').val();
                        var beneficiario = $('#miPopupAcudiente').find('#beneficiario').val();
                        $('#miPopupAcudiente').modal('hide');
                        if (beneficiario == 1) {
                            matricula.optionsBeneficio(identificacion);
                            $('#miPopupBeneficiario').modal('show');
                        } else {
                            $('#miPopupAcudiente').modal('hide');
                        }
                        mensaje(data);
                        matricula.registrarBeneficiario(identificacion);
                    }
                }
            });
        }
        else {

        }
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
var preinscripcion = {
    cargar: function () {
        tablaPreincripciones = $('#tblPreinscripciones').DataTable({
            "ajax": {
                "url": "ControllerMatricula",
                "type": "POST",
                "data": {
                    action: 'ConsultarPreinscripcionesCliente',
                    documentoUsuario: documentoUsuario
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaPreincripciones.ajax.reload();
    }
};
var usuario = {
    myAjax: function (accion, id, tipo, aux) {
        if (accion === 'Consultar') {
            $.ajax({
                url: "ControllerUsuario",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    tipo: tipo
                },
                success: function (data, textStatus, jqXHR) {
                    if (aux === 'Editar') {
                        usuario.editar(data);
                    } else if (aux === 'Preinscribir') {
                        usuario.preinscribir(data);
                    } else
                        usuario.consultar(data);
                }
            });
        }
        else if (accion === 'Registrar' || accion === 'Editar') {
            if (validationUsuario.valid()) {
                var form = $('#formUsuario');
                $.ajax({
                    type: $(form).attr('method'),
                    url: $(form).attr('action'),
                    data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                    success: function (data) {
                        $('#miPopupUsuario').modal('hide');
                        mensaje(data);
                        usuario.actualizarTabla();
                    }
                });
            } else {
                $.notify('Uno o más campos contienen datos erroneos', 'error');
            }
        }
        else if (accion === 'Actualizar') {
            if (validationUsuarioPerfil.valid()) {
                var form = $('#formActualizarDatos');
                $.ajax({
                    type: $(form).attr('method'),
                    url: $(form).attr('action'),
                    data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                    success: function (data) {
                        $('#miPopupConfiguracion').modal('hide');
                        mensaje(data);
                    }
                });
            } else {
                $.notify('Uno o más campos contienen datos erroneos', 'error');
            }
        }
        else if (accion === 'Estado') {
            $.ajax({
                url: "ControllerUsuario",
                type: 'POST',
                data: {
                    action: accion,
                    id: id,
                    tipo: tipo
                },
                success: function (data, textStatus, jqXHR) {
                    if (tipo === 'cuenta') {
                        mensaje(data);
                        setTimeout(function () {
                            location.href = 'index.jsp';
                        }, 2000);
                    } else {
                        mensaje(data);
                        usuario.actualizarTabla();
                    }
                }
            });
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
        $('#miPopupUsuario').find('#titulo').text('Consultar Usuario');
        $('#miPopupUsuario').find('#txtIdentificacion').val(data['numeroDocumento']);
        $('#miPopupUsuario').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
        $('#miPopupUsuario').find('#txtNombre').val(data['nombreUsuario']);
        $('#miPopupUsuario').find('#txtApellido').val(data['apellidoUsuario']);
        $('#miPopupUsuario').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
        var Edad = data['fechaNacimiento'];
        if (mayorDeEdad(Edad) === false) {
            $('#miPopupUsuario').find('#txtIdentificacionAcudiente').val(data['documentoAcudiente']).$('.row:first').show();
            $('#miPopupUsuario').find('#txtNombreAcudiente').val(data['nombreAcudiente']);
        } else {
            $('#miPopupUsuario').find('#txtIdentificacionAcudiente').$('.row:first').hide();
        }
        /*
         * 
         */
        $('#miPopupUsuario').find('#txtCorreo').val(data['emailUsuario']);
        $('#miPopupUsuario').find('#txtPass').val(data['password']);
        $('#miPopupUsuario').find('#txtPass2').val(data['password']);
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#formUsuario');
        $('#miPopupUsuario').modal('show');
    },
    registrar: function () {
        usuario.habilitar();
        habilitar('#formUsuario');
        limpiar("#formUsuario");
        $('#miPopupUsuario').find('#titulo').text('Registro');
        $('#miPopupUsuario').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'button').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupUsuario').modal('show');
    },
    editar: function (data) {
        limpiar("#formUsuario");
        usuario.consultar(data);
        $('#miPopupUsuario').find('#titulo').text('Actualizar Datos');
        $('#miPopupUsuario').find('#obligatoriedad').text('Todos los campos son obligatorios');
        habilitar('#formUsuario');
        $('#miPopupUsuario').find('#btnUsuario').attr('type', 'button').attr('value', 'Editar').attr('disabled', false);
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
        $('#miPopupUsuario').find('#titulo').text('Recuperar Contraseña');
        $('#miPopupUsuario').find('#obligatoriedad').text('Todos los campos son obligatorios');
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
    },
    miPerfil: function (documento) {
        $.ajax({
            url: "ControllerUsuario",
            type: 'POST',
            data: {
                action: 'MiPerfil',
                documentoUsuario: documento
            },
            success: function (data, textStatus, jqXHR) {
                $('#miPopupConfiguracion').find('#txtNombre').text(data.nombreUsuario + " " + data.apellidoUsuario);
                if (parseInt(data.idrol) === 1) {
                    $('#opcionActualizarDatos').removeClass('active');
                    $('#actualizarDatos').removeClass('active');
                    $('#opcionPreincripciones').hide();
                    $('#opcionInhabilitar').hide();
                }
                else if (parseInt(data.idrol) === 2) {
                    $('#opcionActualizarDatos').removeClass('active');
                    $('#actualizarDatos').removeClass('active');
                    $('#opcionPreincripciones').hide();
                    $('#opcionInhabilitar').show().removeClass('active');
                    $('#inhabilitar').removeClass('active');
                }
                else if (parseInt(data.idrol) === 3) {
                    $('#opcionActualizarDatos').removeClass('active');
                    $('#actualizarDatos').removeClass('active');
                    $('#opcionPreincripciones').show().removeClass('active');
                    $('#preincripciones').removeClass('active');
                    $('#opcionInhabilitar').show().removeClass('active');
                    $('#inhabilitar').removeClass('active');
                }
                $('#miPopupConfiguracion').modal('show');
            }
        });
    },
    actualizarDatos: function (documento) {
        $.ajax({
            url: "ControllerUsuario",
            type: 'POST',
            data: {
                action: 'Consultar',
                id: documento
            },
            success: function (data, textStatus, jqXHR) {
                limpiar("#formActualizarDatos");
                $('#formActualizarDatos').find('#txtIdentificacion').val(data['numeroDocumento']);
                $('#formActualizarDatos').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
                $('#formActualizarDatos').find('#txtNombre').val(data['nombreUsuario']);
                $('#formActualizarDatos').find('#txtApellido').val(data['apellidoUsuario']);
                $('#formActualizarDatos').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
                $('#formActualizarDatos').find('#txtTelefono').val(data['telefonoFijo']);
                $('#formActualizarDatos').find('#txtCorreo').val(data['emailUsuario']);
                $('#formActualizarDatos').find('#txtPass').val(data['password']);
                $('#formActualizarDatos').find('#txtPass2').val(data['password']);
                $('#formActualizarDatos').find('#btnUsuario').attr('type', 'button').attr('value', 'Actualizar').attr('disabled', false);
            }
        });
    }
};
var matricula = {
    actualizarTabla: function () {
        tablaMatricula.ajax.reload();
    },
    asistencia: function (documento, idCurso) {
        matricula.consultar(documento, idCurso);
        $('#miPopupMatricula').find('#estadoPago').attr('disabled', false).parents('.row:first').show();
        $('#miPopupMatricula').find('#btnMatricula').attr('value', 'Ingresar').attr('onclick', 'matricula.registrarAsistencia()');
        $('#miPopupMatricula').find('#btnMatricula').show();
    },
    consultar: function (documento, idCurso) {
        $.ajax({
            url: "ControllerMatricula",
            type: 'POST',
            data: {
                action: 'Consultar',
                documentoUsuario: documento,
                idCurso: idCurso
            },
            success: function (data, textStatus, jqXHR) {
                /**
                 *
                 resultado.put("documentoUsuario", rs.getString("documentoUsuario"));
                 resultado.put("nombreUsuario", rs.getString("nombreUsuario"));
                 resultado.put("apellidoUsuario", rs.getString("apellidoUsuario"));
                 resultado.put("idCurso", rs.getString("idCurso"));
                 resultado.put("precioCurso", rs.getString("precioCurso"));
                 resultado.put("precioClase", rs.getString("precioClase"));
                 resultado.put("horasPorClase", rs.getString("horasPorClase"));
                 */
                $('#miPopupMatricula').find('#titulo').text('Asistencia Estudiante');
                $('#miPopupMatricula').find('#txtNombre').text(data["nombreUsuario"] + " " + data["apellidoUsuario"]);
                $('#miPopupMatricula').find('#txtIdentificacion').text(data['documentoUsuario'].substring(2));
                var tipo = data['documentoUsuario'].substring(0, 2);
                tipo = (tipo === 'CC') ? 'Cédula' : tipo;
                tipo = (tipo === 'TI') ? 'Tarjeta de Identidad' : tipo;
                tipo = (tipo === 'RC') ? 'Registro Civil' : tipo;
                tipo = (tipo === 'CE') ? 'Cédula de Extranjería' : tipo;
                $('#miPopupMatricula').find('#txtTipo').text(tipo);
                $('#miPopupMatricula').find('#txtDocumento').val(data.documentoUsuario);
                $('#miPopupMatricula').find('#idCursoMatricula').empty().append('<option value="' + idCurso + '">' + data['nombreCurso'] + '</option>');
                $('#miPopupMatricula').find('#idCursoMatricula option').prop('selected', false).filter('[value="' + idCurso + '"]').prop('selected', true);
                $('#miPopupMatricula').find('#idCursoMatricula').attr('disabled', true);
                $('#miPopupMatricula').find('#txtPrecioCurso').text(data['precioCurso']);
                $('#miPopupMatricula').find('#txtPrecioClases').text(data['precioClase']);
                $('#miPopupMatricula').find('#txtHoraClase').text(data['horasPorClase']);
                $('#miPopupMatricula').find('#txtClases').val(1).attr('readOnly', true).parents('.row:first').show();
                $('#miPopupMatricula').modal('show');
            }
        });
        $('#miPopupMatricula').find('#btnMatricula').hide();
        $('#miPopupMatricula').find('#estadoPago').attr('disabled', true).parents('.row:first').hide();
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
    registrar: function () {
        $.ajax({
            url: "ControllerMatricula",
            type: 'POST',
            data: $('#formMatricula').serialize() + '&action=Registrar',
            success: function (data, textStatus, jqXHR) {
                $('#miPopupMatricula').modal('hide');
                matricula.actualizarTabla();
                mensaje(data);
            }
        });
    },
    registrarAsistencia: function () {
        var precioClase = $("#txtPrecioClases").text();
        $('#miPopupMatricula').find('#idCursoMatricula').attr('disabled', false);
        $.ajax({
            url: "ControllerMatricula",
            type: 'POST',
            data: $('#formMatricula').serialize() + '&action=RegistrarAsistencia' + '&documentoUsuario=' + documentoUsuario + '&precio=' + precioClase,
            success: function (data) {
                $('#miPopupMatricula').find('#idCursoMatricula').attr('disabled', true);
                $('#miPopupMatricula').modal('hide');
                matricula.actualizarTabla();
                mensaje(data);
            }
        });
    },
    optionsBeneficio: function (estudiante) {
        $.ajax({
            url: "ControllerMatricula",
            type: 'POST',
            data: {
                action: 'getOptionsBeneficio'
            },
            success: function (data, textStatus, jqXHR) {
                $('#miPopupBeneficiario').find('#ddlEstudiante').select2({
                    data: data.estudiantes,
                    language: "es",
                    placeholder: "Selecciona el estudiante",
                    allowClear: true
                }).select2("val", estudiante);
                $('#miPopupBeneficiario').find('#ddlEmpresa').select2({
                    data: data.empresas,
                    language: "es",
                    placeholder: "Selecciona la empresa",
                    allowClear: true
                });
            }
        });
        //$('#miPopupBeneficiario').find('#ddlEstudiante option').prop('selected', false).filter('[value="' + estudiante + '"]').prop('selected', true);
        $('#miPopupBeneficiario').modal('show');
    },
    registrarBeneficiario: function (identificacion) {
        var beneficiario, menor;
        beneficiario = $('inputradio[name=radioBeneficiario]:checked').val();
        menor = mayorDeEdad($('#miPopupEstudiante').find('#dateFechaNacimiento').val());
        if (menor !== true) {
            $('#miPopupAcudiente').modal('show');
            $('#miPopupAcudiente').find('#txtIdentificacionEstudiante').val(identificacion);
            $('#miPopupAcudiente').find('#beneficiario').val(beneficiario);
        }
        else {
            if (beneficiario === '1') {
                matricula.optionsBeneficio(identificacion);
                $('#miPopupBeneficiario').modal('show');
            }
        }
    },
    asignarEmpresa: function () {
        var idEstudiante = $('#miPopupBeneficiario').find('#ddlEstudiante').val();
        var idEmpresa = $('#miPopupBeneficiario').find('#ddlEmpresa').val();
        var valorBeneficio = $('#miPopupBeneficiario').find('#txtValorBeneficio').val();
        if (validationBeneficiario.valid()) {
            $.ajax({
                url: "ControllerMatricula",
                type: 'POST',
                data: {
                    action: 'asignarEmpresa',
                    nitEmpresa: idEmpresa,
                    documentoEstudiante: idEstudiante,
                    valorBeneficio: valorBeneficio
                }
            });
        }
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
                        categoriaArticulo.cargarOpciones();
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
                    if (accion === 'Registrar' || accion === 'Editar') {
                        $('#miPopupArticulo').modal('hide');
                        mensaje(data);
                        articulo.actualizarTabla();
                        if (document.getElementById('ddlArticulos') !== null) {
                            $("#ddlArticulos").select2('destroy');
                            articulo.listarArticulos();
                        }

                    } else if (accion === 'ConsultarCodigo') {
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
        if (accion === 'getOptionsCategorias' || accion === 'ConsultarCodigo') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#formArticulo');
        $('#miPopupArticulo').find('#titulo').text('Registrar Artículo');
        $('#miPopupArticulo').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupArticulo').find('#btnArticulo').attr('value', 'Registrar');
        $('#miPopupArticulo').find('#txtPrecioCompra').attr('readOnly', false);
        $('#miPopupArticulo').find('#txtPrecioVenta');
        $('#miPopupArticulo').find('#txtCantidadArticulo').attr('readOnly', false);
        $('#miPopupArticulo').find('#txtCantidadArticulo').attr('disabled', true).parents('.row:first').hide();
        articulo.contador();
        $('#miPopupArticulo').modal('show');
    },
    editar: function (tr) {
        var data = tablaArticulo.row(tr).data();
        $('#miPopupArticulo').find('#titulo').text('Editar Artículo');
        $('#miPopupArticulo').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupArticulo').find('#idArticulo').val(data[0]);
        $('#miPopupArticulo').find('#txtDescripcion').val(data[2]);
        $('#miPopupArticulo').find('#txtCantidadArticulo').val(data[3]).attr('readOnly', true);
        $('#miPopupArticulo').find('#txtCantidadArticulo').attr('disabled', false).parents('.row:first').show();
        $('#miPopupArticulo').find('#txtPrecioCompra').val(data[4]).attr('readOnly', true);
        $('#miPopupArticulo').find('#txtPrecioVenta').val(data[5]);
        $('#miPopupArticulo').find('#idCategoriaArticulo option').prop('selected', false).filter(function () {
            return ($(this).text() === data[1]);
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
                        fila += '<td>' + '<input type="number" id="valor" onblur="compra.actualizarTotal(\'valor\')" name="valor" min="' + (parseInt(data['precioCompra']) + 1) + '" value="' + data['precioVenta'] + '" required>' + '</td>';
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
            if (parseInt($(this).data('id')) === parseInt(id)) {
                flag = false;
            }
        });
        return flag;
    },
    remover: function (id) {
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            if (parseInt($(this).data('id')) === parseInt(id)) {
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
                    placeholder: "Selecciona los artículos",
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
                    if (accion === 'Editar' || accion === 'Registrar') {
                        $('#miPopupEmpresa').modal('hide');
                        empresa.actualizarTabla();
                        mensaje(data);
                    } else if (accion === 'getOptionsEmpresas') {
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
        $('#miPopupEmpresa').find('#titulo').text('Registrar Empresa');
        $('#miPopupEmpresa').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupEmpresa').find('#btnEmpresa').attr('value', 'Registrar');
        $('#miPopupEmpresa').modal('show');
    },
    editar: function (tr) {
        var data = tablaEmpresa.row(tr).data();
        $('#miPopupEmpresa').find('#titulo').text('Editar Empresa');
        $('#miPopupEmpresa').find('#obligatoriedad').text('Todos los campos son obligatorios');
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
                    if (accion === 'Consultar') {
                        $('#btnGestionCompras').tab('show');
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
        if (accion === 'Estado' || accion === 'Consultar' || accion === 'getOptionsCompra') {
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
        var subtotal = salida / 1.16;
        $('#tabMovimientos').find('#txtSubTotalMovimiento').val(subtotal.toFixed(2));
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
                $.notify('Una compra debe contener al menos un artículo', 'error');
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
        $('#clienteRegistrado').parents('.row:first').hide();
        $("#ddlArticulos").off();
        $("#ddlArticulos").on("select2:select", function (e) {
            var id = e.params.data.id;
            if (parseInt(id) !== -1) {
                articulo.seleccionar(id, 'Compra');
            }
        });
        compra.limpiarDetalle();
        $('#contenidoDinamico').data('actual', 'compra');
        $('#tabMovimientos').find('#nombre').text('Nombre del Proveedor');
        $('#tabMovimientos').find('#numero').text('Número de Factura ');
        $('#tabMovimientos').find('#subtotal').hide();
        $('#tabMovimientos').find('#total').text('Total compra: $');
        document.getElementById('tipoMovimiento').checked = false;
        $('#tabMovimientos').find('#tipoMovimiento').parents('.row:first').hide();
        $('#tabMovimientos').find('#txtIdentificacion').attr('disabled', true).parents('.row:first').hide();
        $('#tabMovimientos').find('#ddlIdentificacion').attr('disabled', true);
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
    cambioDeTipo: function (checkBox) {
        if (checkBox) {
            $.ajax({
                type: 'POST',
                url: 'ControllerUsuario',
                dataType: 'JSON',
                data: {
                    action: 'getOptionsClientes'
                },
                success: function (data) {
                    try {
                        $('#clienteRegistrado').select2();
                        $('#clienteRegistrado').select2('destroy');
                    } catch (err) {
                    }
                    $("#clienteRegistrado").select2({
                        data: data,
                        language: "es",
                        placeholder: "Selecciona el cliente",
                        allowClear: true
                    });
                    $('#tabMovimientos').find('#txtIdentificacion').attr('readOnly', true);
                    $('#tabMovimientos').find('#ddlIdentificacion').attr('readOnly', true);
                    $('#tabMovimientos').find('#txtNombre').attr('readOnly', true);
                    $('#clienteRegistrado').parents('.row:first').show();
                    $("#clienteRegistrado").off();
                    $("#clienteRegistrado").on("select2:select", function (e) {
                        var id = e.params.data.id;
                        if (parseInt(id) !== -1) {
                            cliente.seleccionar(id);
                        }
                    });
                }
            });
        }
        $('#clienteRegistrado').parents('.row:first').hide();
        $('#tabMovimientos').find('#txtIdentificacion').attr('readOnly', false);
        $('#tabMovimientos').find('#ddlIdentificacion').attr('readOnly', false);
        $('#tabMovimientos').find('#txtNombre').val(null).attr('readOnly', false);
        $('#tabMovimientos').find('#txtIdentificacion').val(null);
        $('#tabMovimientos').find('#ddlIdentificacion option').prop('selected', false);
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
                var documentoCliente = $('#tabMovimientos').find('#ddlIdentificacion').val() + $('#tabMovimientos').find('#txtIdentificacion').val();
                var total = $('#tabMovimientos').find('#txtTotalMovimiento').val();
                var chkCredito = document.getElementById('chkAddCredito').checked;
                $.ajax({
                    type: 'POST',
                    url: "ControllerVenta",
                    data: {
                        action: 'Registrar',
                        lista: lista,
                        size: lista.length,
                        txtNombreCliente: nombre, txtNumeroVenta: numeroVenta,
                        txtTotalVenta: total,
                        documentoUsuario: documentoUsuario,
                        documentoCliente: documentoCliente,
                        credito: chkCredito

                    },
                    success: function (data, textStatus, jqXHR) {
                        venta.limpiarDetalle();
                        $("#ddlArticulos").val(null);
                        limpiar('#formMovimiento');
                        mensaje(data);
                        venta.contador();
                        venta.actualizarTabla();
                        if (chkCredito) {
                            credito.actualizarTabla();
                        }
                    }
                });
            } else {
                $.notify('Una venta debe contener al menos un artículo', 'error');
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
        $('#clienteRegistrado').parents('.row:first').hide();
        document.getElementById('tipoMovimiento').checked = false;
        $("#ddlArticulos").off();
        $("#ddlArticulos").on("select2:select", function (e) {
            var id = e.params.data.id;
            if (parseInt(id) !== -1) {
                articulo.seleccionar(id, 'Venta');
            }
        });
        venta.limpiarDetalle();
        $('#contenidoDinamico').data('actual', 'venta');
        $('#tabMovimientos').find('#nombre').text('Nombre del Cliente');
        $('#tabMovimientos').find('#numero').text('Id. Venta');
        $('#tabMovimientos').find('#subtotal').show();
        $('#tabMovimientos').find('#lblSubtotal').text('Subtotal venta: $');
        $('#tabMovimientos').find('#total').text('Total venta: $');
        if (tipo === 'Registrar') {
            $('#tabMovimientos').find('#tipoMovimiento').parents('.row:first').show();
            $('#tabMovimientos').find('#ddlIdentificacion option').prop('selected', false);
            $('#tabMovimientos').find('#ddlIdentificacion').attr('disabled', false);
            $('#tabMovimientos').find('#txtIdentificacion').val(null).attr('readOnly', false).parents('.row:first').show();
            $('#tabMovimientos').find('#titulo').text('Registrar Venta');
            $('#tabMovimientos').find('#txtNumero').attr('readOnly', true);
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
            $('#tabMovimientos').find('#btnArticulo').hide();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'venta.efectuarVenta()').val('Efectuar Venta');
            $('#tabMovimientos').find('#ddlArticulos').attr('disabled', false).parents('.row:first').show();
            $('#tabMovimientos').find('#btnArticulo').attr('disabled', false).parents('.row:first').show();
            venta.contador();
        } else if (tipo === 'Consultar') {
            $('#tabMovimientos').find('#tipoMovimiento').parents('.row:first').hide();
            $('#tabMovimientos').find('#titulo').text('Consultar Venta');
            $('#btnGestionVentas').data('target', '#tabMovimientos').tab('show');
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + datos.Venta.fechaVenta);
            $('#tabMovimientos').find('#txtNombre').val(datos.Venta.nombreCliente).attr('readOnly', true);
            $('#tabMovimientos').find('#txtNumero').val(datos.Venta.numeroVenta).attr('readOnly', true);
            $('#tabMovimientos').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + datos.Venta.documentoCliente.substring(0, 2) + '"]').prop('selected', true);
            $('#tabMovimientos').find('#ddlIdentificacion').attr('disabled', true);
            $('#tabMovimientos').find('#txtIdentificacion').val(datos.Venta.documentoCliente.substring(2)).attr('readOnly', true).parents('.row:first').show();
            var subtotal = parseInt(datos.Venta.totalVenta) / 1.16;
            var iva = parseInt(datos.Venta.totalVenta) - subtotal;
            $('#tabMovimientos').find('#txtSubTotalMovimiento').val(subtotal.toFixed(2)).show();
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
    consultarDetalle: function (idCredito, documentoCliente) {
        $.ajax({
            url: "ControllerCredito",
            type: 'POST',
            data: {
                action: 'ConsultarDetalle',
                documentoCliente: documentoCliente,
                idCredito: idCredito
            }, 
            success: function (data, textStatus, jqXHR) {
                $("#miPopupEstadoDeCuenta").modal('show');
                console.log(data);
            }
        });
    },
    actualizarTabla: function () {
        tablaCredito.ajax.reload();
    },
    consultar: function (idCredito) {
        $.ajax({
        });
    },
    cargar: function () {
        tablaCredito = $('#tblCreditos').DataTable({
            "ajax": {
                url: 'ControllerCredito',
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
    show: function (tipo) {
        $("#ddlArticulos").off();
        $("#ddlArticulos").on("select2:select", function (e) {
            var id = e.params.data.id;
            if (parseInt(id) !== -1) {
                articulo.seleccionar(id, 'Venta');
            }
        });
        credito.limpiarDetalle();
        $('#contenidoDinamico').data('actual', 'credito');
        $('#tabMovimientos').find('#nombre').text('Nombre del Cliente');
        $('#tabMovimientos').find('#numero').text('Id. Crédito');
        $('#tabMovimientos').find('#total').text('Total Crédito');
        if (tipo === 'Registrar') {
            $('#tabMovimientos').find('#titulo').text('Registrar Crédito');
            $('#tabMovimientos').find('#txtNumero').attr('readOnly', true);
            $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
            $('#tabMovimientos').find('#btnArticulo').hide();
            $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'credito.registrarCredito()').val('Registrar Crédito');
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
    },
    estado: function (id) {
        $.ajax({
            type: 'POST',
            url: "ControllerCredito",
            data: {
                idCredito: id,
                action: 'Estado'
            },
            success: function (data, textStatus, jqXHR) {
                mensaje(data);
                credito.actualizarTabla();
            }
        });
    }
};
var operario = {
    myAjax: function (action, id, aux) {
        if (action === 'Estado') {
            $.ajax({
                url: "ControllerUsuario",
                type: 'POST',
                data: {
                    action: 'Estado',
                    id: id
                },
                success: function (data, textStatus, jqXHR) {
                    mensaje(data);
                    operario.actualizarTabla();
                }
            });
        }
        else if (action === 'Consultar') {
            $.ajax({
                url: "ControllerUsuario",
                type: 'POST',
                data: {
                    action: 'Consultar',
                    id: id
                },
                success: function (data, textStatus, jqXHR) {
                    if (aux === 'Editar') {
                        operario.editar(data);
                    }
                    else {
                        operario.consultar(data);
                    }
                }
            });
        }
        else if (action === 'Registrar' | action === 'Editar') {
            if (validationOperarios.valid()) {
                $.ajax({
                    url: $('#formOperario').attr('action'),
                    type: $('#formOperario').attr('method'),
                    data: $('#formOperario').serialize() + '&action=' + action + '&tipo=Operario',
                    success: function (data) {
                        mensaje(data);
                        operario.actualizarTabla();
                        $('#miPopupOperario').modal('hide');
                    }
                });
            } else {
                $.notify('Uno o más campos contienen datos erroneos', 'error');
            }

        }
    },
    registrar: function () {
        limpiar('#formOperario');
        habilitar('#formOperario');
        $('#miPopupOperario').find('#titulo').text('Registrar Operario');
        $('#miPopupOperario').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupOperario').find('#btnOperario').val('Registrar').attr('type', 'button');
        $('#miPopupOperario').modal('show');
    },
    consultar: function (data) {
        limpiar('#formOperario');
        desabilitar('#formOperario');
        $('#miPopupOperario').find('#titulo').text('Consultar Operario');
        $('#miPopupOperario').find('#txtIdentificacion').val(data['numeroDocumento']);
        $('#miPopupOperario').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
        $('#miPopupOperario').find('#txtNombre').val(data['nombreUsuario']);
        $('#miPopupOperario').find('#txtApellido').val(data['apellidoUsuario']);
        $('#miPopupOperario').find('#txtTelefono').val(data['telefonoFijo']);
        $('#miPopupOperario').find('#txtCorreo').val(data['emailUsuario']);
        $('#miPopupOperario').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
        $('#miPopupOperario').find('#txtPass').val(data['password']);
        $('#miPopupOperario').find('#txtPass2').val(data['password']);
        $('#miPopupOperario').find('#btnOperario').attr('type', 'hidden');
        $('#miPopupOperario').modal('show');
    },
    editar: function (data) {
        operario.consultar(data);
        habilitar('#formOperario');
        $('#miPopupOperario').find('#titulo').text('Editar Operario');
        $('#miPopupOperario').find('#obligatoriedad').text('Todos los campos son obligatorios');
        $('#miPopupOperario').find('#btnOperario').val('Editar').attr('type', 'button');
    },
    cargar: function () {
        tablaOperarios = $('#tblOperarios').DataTable({
            "ajax": {
                "url": "ControllerUsuario",
                "type": "POST",
                "data": {
                    action: 'EnlistarOperarios'
                }
            }, "language": {
                "url": "public/js/locales/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaOperarios.ajax.reload();
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
matricula.cargar();
empresa.cargar();
usuario.cargar();
credito.cargar();
categoriaArticulo.cargarOpciones();
preinscrito.cargar();
operario.cargar();
if (typeof documentoUsuario !== 'undefined') {
    preinscripcion.cargar();
}
$('#miPopupDetalleSeminario').on('hidden.bs.modal', function () {
    tablaDetalleSeminario.destroy();
});

$('#miPopupCurso').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupUsuario').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupAcudiente').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupArticulo').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupEstudiante').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupBeneficiario').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupOperario').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});
$('#miPopupAsistenteSeminario').on('hidden.bs.modal', function () {
    $(this).find('#obligatoriedad').text('');
});