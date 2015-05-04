/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tablaCurso, tablaCategoriaCurso, tablaFicha, tablaSeminario, tablaEstudiante, tablaMatricula, tablaArticulo, tablaCategoriaArticulo, tablaEmpresa, tablaCompra;

var curso = {
    myAjax: function (accion, id, aux) {
        var form = $('#formCurso');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id,
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
        if (accion === 'Estado' || accion === 'Consultar' || accion === 'getOptionsCursos') {
            $(form).submit();
        }
    },
    consultar: function (data) {
        limpiar("#formCurso");
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Consultar Curso');
        $('#miPopupCurso').find('#idCurso').val(data['idCurso']);
        $('#miPopupCurso').find('#ddlCategoria option').prop('selected', false).filter('[value="' + data['idtblCategoriaCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#txtNombreCurso').val(data['nombreCurso']);
        $('#miPopupCurso').find('#dateDuracion').val(data['duracionCurso']);
        $('#miPopupCurso').find('#txtDescripcionCurso').val(data['descripcionCurso']);
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
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupCurso').modal('show');
    },
    editar: function (data) {
        curso.consultar(data);
        habilitar('#formCurso');
        $('#miPopupCurso').find('#titulo').empty();
        $('#miPopupCurso').find('#titulo').append('Editar Curso');
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    cargar: function () {
        curso.myAjax('getOptionsCursos');
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
        curso.myAjax('getOptionsCursos');
        tablaCurso.ajax.reload();
    },
    cargarOpciones: function (data) {
        $('#miPopupFicha').find('#idCursoFicha').empty();
        $('#miPopupFicha').find('#idCursoFicha').append(data);
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
            }
            );
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

var ficha = {
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
                        ficha.actualizarTabla();
                        mensaje(data);
                    } else if (accion == 'getOptionsFichas') {
                        ficha.cargarOpciones(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar' || accion === 'getOptionsFichas') {
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
        var data = tablaFicha.row(tr).data();
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
        ficha.myAjax('getOptionsFichas');
        tablaFicha = $('#tblFichas').DataTable({
            "ajax": {
                "url": "ControllerFicha",
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
        tablaFicha.ajax.reload();
    },
    cargarOpciones: function (data) {
        $('#formMatricula').find('#idCursoFicha').empty();
        $('#formMatricula').find('#idCursoFicha').append(data);
    }
};

var seminario = {
    myAjax: function (accion, id) {
        var form = $('#formSeminario');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id,
                success: function (data) {
                    seminario.actualizarTabla();
                    $('#miPopupSeminario').modal('hide');
                    if (accion == 'Estado' || accion == 'Editar' || accion == 'Registrar') {
                        mensaje(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#formSeminario');
        $('#miPopupSeminario').find('#titulo').empty();
        $('#miPopupSeminario').find('#titulo').append('Registrar Seminario');
        $('#miPopupSeminario').find('#btnSeminario').attr('value', 'Registrar');
        $('#miPopupSeminario').modal('show');
    },
    editar: function (tr, estado) {
        var data = tablaSeminario.row(tr).data();
        $('#miPopupSeminario').find('#titulo').empty();
        $('#miPopupSeminario').find('#titulo').append('Editar Seminario');
        $('#miPopupSeminario').find('#idSeminario').val(data[0]);
        $('#miPopupSeminario').find('#txtNombreSeminario').val(data[1]);
        $('#miPopupSeminario').find('#txtDuracion').val(data[2]);
        $('#miPopupSeminario').find('#ddlEstado option').prop('selected', false).filter('[value="' + estado + '"]').prop('selected', true);
        $('#miPopupSeminario').find('#btnSeminario').val('Editar');
        $('#miPopupSeminario').modal('show');
    },
    cargar: function () {
        tablaSeminario = $('#tblSeminarios').DataTable({
            "ajax": {
                "url": "ControllerSeminario",
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
                    if (accion == 'Seleccion') {
                        estudiante.cargarSeleccion(data);
                    }
                    if (accion == 'Consultar') {
                        if (aux == 'Editar') {
                            estudiante.editar(data);

                        } else if (aux == 'Matricular') {
                            estudiante.matricular(data);
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
                        ficha.cargarOpciones(data);
                    }
                }
            });
            $(form).off();
            return false;
        });
        if (accion === 'Estado' || accion === 'Consultar' || accion === 'getOptionsFichas' || accion === 'Seleccion') {
            $(form).submit();
        }
    },
    matricular: function (data) {
        limpiar("#formMatricula");
        $('#miPopupMatricula').find('#titulo').empty();
        $('#miPopupMatricula').find('#titulo').append('Matricular Estudiante');
        $('#miPopupMatricula').find('#txtNombre').empty();
        $('#miPopupMatricula').find('#txtApellido').empty();
        $('#miPopupMatricula').find('#txtNombre').append(data["nombreCliente"]);
        $('#miPopupMatricula').find('#txtApellido').append(data["apellidoCliente"]);
        $('#miPopupMatricula').find('#idEstudiante').val(data['numeroDocumento']);
        $('#miPopupMatricula').find('#txtIdentificacion').empty();
        $('#miPopupMatricula').find('#txtIdentificacion').append(data['numeroDocumento']);
        $('#miPopupMatricula').find('#idCursoFicha option').prop('selected', false);
        $('#miPopupMatricula').find('#dateInicio').val('');
        $('#miPopupMatricula').find('#dateFinal').val('');
        $('#miPopupMatricula').find('#dateInicioFicha').empty();
        $('#miPopupMatricula').find('#dateFinFicha').empty();
        $('#miPopupMatricula').modal('show');
    },
    consultar: function (data) {
        limpiar("#form_estudiante");
        $('#miPopupEstudiante').find('#titulo').empty();
        $('#miPopupEstudiante').find('#titulo').append('Consultar Estudiante');
        $('#miPopupEstudiante').find('#txtIdentificacion').val(data['numeroDocumento']);
        $('#miPopupEstudiante').find('#ddlIdentificacion option').prop('selected', false).filter('[value="' + data['tipoDocumento'] + '"]').prop('selected', true);
        $('#miPopupEstudiante').find('#txtNombre').val(data['nombreCliente']);
        $('#miPopupEstudiante').find('#txtApellido').val(data['apellidoCliente']);
        $('#miPopupEstudiante').find('#dateFechaNacimiento').val(data['fechaNacimiento']);
        $('#miPopupEstudiante').find('#txtDireccion').val(data['direccionCliente']);
        $('#miPopupEstudiante').find('#txtTelefono').val(data['telefonoFijo']);
        $('#miPopupEstudiante').find('#txtCelular').val(data['telefonoMovil']);
        $('#miPopupEstudiante').find('#txtCorreo').val(data['emailCliente']);
        if (data['generoCliente'] == 0)
            $('#miPopupEstudiante').find('#radioGeneroFemenino').prop('checked', true);
        else
            $('#miPopupEstudiante').find('#radioGeneroMasculino').prop('checked', true)

        if (data['estadoEstudiante'] == 0)
            $('#miPopupEstudiante').find('#radioNoBeneficiario').prop('checked', true);
        else
            $('#miPopupEstudiante').find('#radioSiBeneficiario').prop('checked', true)

        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#form_estudiante');
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
        limpiar("#formCurso");
        estudiante.consultar(data);
        $('#miPopupEstudiante').find('#titulo').empty();
        $('#miPopupEstudiante').find('#titulo').append('Editar Estudiante');
        habilitar('#form_estudiante');
        $('#miPopupEstudiante').find('#btnEstudiante').attr('type', 'submit').attr('value', 'Editar').attr('disabled', false);
    },
    cargarSeleccion: function (data) {
        $('#miPopupMatricula').find('#dateInicio').val(data['fechaInicio']);
        $('#miPopupMatricula').find('#dateFinal').val(data['fechaFinal']);
        $('#miPopupMatricula').find('#dateInicioFicha').empty();
        $('#miPopupMatricula').find('#dateInicioFicha').append(data['fechaInicio']);
        $('#miPopupMatricula').find('#dateFinFicha').empty();
        $('#miPopupMatricula').find('#dateFinFicha').append(data['fechaFinal']);

    },
    cargar: function () {
        estudiante.myAjax('getOptionsFichas');
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

var matricula = {
    myAjax: function (accion, id, tipo) {
        var form = $('#formMatricula');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                success: function (data) {
                    if (accion === 'Seleccion') {
                        matricula.cargarSeleccion(data);
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
                    if (accion === 'getOptionsCategorias') {
                        categoriaArticulo.cargarOpciones(data);
                    } else if (accion !== 'getOptionsCategorias') {
                        categoriaArticulo.actualizarTabla();
                        if (accion === 'Editar' || accion === 'Registrar') {
                            $('#miPopupCategoriaArticulo').modal('hide');
                            mensaje(data);
                        }
                    }
                }
            }
            );
            $(form).off();
            return false;
        });
        if (accion === 'getOptionsCategorias') {
            $(form).submit();
        }
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
        categoriaArticulo.myAjax('getOptionsCategorias');
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
    cargarOpciones: function (data) {
        $('#miPopupArticulo').find('#idCategoriaArticulo').empty();
        $('#miPopupArticulo').find('#idCategoriaArticulo').append(data);
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
        if (accion === 'getOptionsCategorias') {
            $(form).submit();
        }
    },
    registrar: function () {
        limpiar('#formArticulo');
        $('#miPopupArticulo').find('#titulo').empty();
        $('#miPopupArticulo').find('#titulo').append('Registrar Artículo');
        $('#miPopupArticulo').find('#btnArticulo').attr('value', 'Registrar');
        $('#miPopupArticulo').modal('show');
    },
    editar: function (tr) {
        var data = tablaArticulo.row(tr).data();
        $('#miPopupArticulo').find('#titulo').empty();
        $('#miPopupArticulo').find('#titulo').append('Editar Artículo');
        $('#miPopupArticulo').find('#idArticulo').val(data[0]);
        $('#miPopupArticulo').find('#txtNombreArticulo').val(data[2]);
        $('#miPopupArticulo').find('#txtPrecioArticulo').val(data[4]);
        $('#miPopupArticulo').find('#txtCantidadArticulo').val(data[3]);
        $('#miPopupArticulo').find('#idCategoriaArticulo option').prop('selected', false).filter(function () {
            return ($(this).text() == data[1]);
        }).prop('selected', true);
        $('#miPopupArticulo').find('#btnArticulo').val('Editar');
        $('#miPopupArticulo').modal('show');
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
    actualizarTabla: function () {
        tablaArticulo.ajax.reload();
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
    myAjax: function (accion, id, tipo) {
        var form = $('#formCompra');
        $(form).off();
        $(form).on('submit', function () {
            $.ajax({
                type: $(form).attr('method'),
                url: $(form).attr('action'),
                data: $(form).serialize() + '&action=' + accion + '&id=' + id + '&tipo=' + tipo,
                success: function (data) {
                    if (accion == 'Consultar') {
                        if (aux == 'Editar') {
                            compra.editar(data);
                        } else
                            compra.consultar(data);
                    }
                    else if (accion == 'Registrar' || accion == 'Editar' || accion == 'Estado') {
                        if (accion != 'Estado') {
                            $('#miPopupCompra').modal('hide');
                        }
                        mensaje(data);
                        compra.actualizarTabla();
                    }
                    else if (accion === 'getOptionsCompra') {
                        compra.cargarOpciones(data);
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
    consultar: function (data) {
        limpiar("#form_compra");
        $('#miPopupCompra').find('#txtFacturaProveedor').val(data['facturaProveedor']);
        $('#miPopupCompra').find('#txtNombreProveedor').val(data['nombreProveedor']);
        $('#miPopupCompra').find('#dateFechaCompra').val(data['fechaCompra']);
        $('#miPopupCompra').find('#totalFactura').val(data['totalFactura']);
        $('#miPopupCompra').find('#btnCompra').attr('type', 'hidden').attr('disabled', true);
        desabilitar('#form_compra');
        $('#miPopupCompra').modal('show');
    },
    registrar: function () {
        habilitar('#form_compra');
        limpiar("#form_compra");
        $('#miPopupCompra').find('#btnCompra').attr('type', 'submit').attr('value', 'Registrar').attr('disabled', false);
        $('#miPopupCompra').modal('show');
    },
    editar: function (tr) {
        $('#miPopupDetalleCompra').modal('show');
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
                "url": "public/lang/Spanish.json"
            }
        });
    },
    actualizarTabla: function () {
        tablaCompra.ajax.reload();
    }
}

compra.cargar();
ficha.cargar();
categoriaCurso.cargar();
curso.cargar();
seminario.cargar();
abono.cargar();
estudiante.cargar();
categoriaArticulo.cargar();
articulo.cargar();
//matricula.cargar();
empresa.cargar();
//credito.cargar()
