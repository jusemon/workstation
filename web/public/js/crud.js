/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tablas = $('.tabla').DataTable({
    "language": {
        "url": "public/lang/Spanish.json"
    }
});

$(function () {
    myAjax(null, 'ControllerCurso', 'POST', 'Enlistar', null);
});
function editar() {
    var tipo;
    $('.table tbody').on('click', 'tr', function () {
        tipo = $(this).data('tipo');
        if (tipo === 'categoria curso') {
            var rowData = tablas.table('#tblCategoriaCursos').row(this).data();
            $('#miPopupCategoriaCurso').find('#idCategoriaCurso').attr('value', rowData[0]);
            $('#miPopupCategoriaCurso').find('#txtNombreCategoriaCurso').attr('value', rowData[1]);
            $('#miPopupCategoriaCurso').find('#btnCategoriaCurso').attr('value', 'Editar');
            $('#miPopupCategoriaCurso').modal('show');
            $('#tblCategoriaCursos tbody').off();
        }
        else if (tipo === 'categoria articulo') {
            var rowData = tablas.table('#tblCategoriaArticulos').row(this).data();
            $('#miPopupCategoriaArticulo').find('#idCategoriaArticulo').attr('value', rowData[0]);
            $('#miPopupCategoriaArticulo').find('#txtNombreCategoriaArticulo').attr('value', rowData[1]);
            $('#miPopupCategoriaArticulo').find('#btnCategoriaArticulo').attr('value', 'Editar');
            $('#miPopupCategoriaArticulo').modal('show');
            $('#tblCategoriaArticulos tbody').off();
        }
        else if (tipo === 'seminario') {
            var rowData = tablas.table('#tblSeminarios').row(this).data();
            $('#miPopupSeminario').find('#idSeminario').attr('value', rowData[0]);
            $('#miPopupSeminario').find('#txtNombreSeminario').attr('value', rowData[1]);
            $('#miPopupSeminario').find('#txtDuracion').attr('value', rowData[2]);
            $('#miPopupSeminario').find('#ddlEstadosemiario').attr('value', rowData[3]);
            $('#miPopupSeminario').find('#btnSeminario').attr('value', 'Editar');
            $('#miPopupSeminario').modal('show');
            $('#tblSeminarios tbody').off();
        }
        else if (tipo === 'articulo') {
            var rowData = tablas.table('#tblArticulos').row(this).data();
            $('#miPopupArticulo').find('#idArticulo').attr('value', rowData[0]);
            $('#miPopupArticulo').find('#txtNombreArticulo').attr('value', rowData[1]);
            $('#miPopupArticulo').find('#txtPrecioArticulo').attr('value', rowData[2]);
            $('#miPopupArticulo').find('#txtCantidadArticulo').attr('value', rowData[3]);
            $('#miPopupArticulo').find('#idCategoriaArticulo').attr('value', rowData[4]);
            $('#miPopupArticulo').find('#btnArticulo').attr('value', 'Editar');
            $('#miPopupArticulo').modal('show');
            $('#tblArticulos tbody').off();
        }
        else if (tipo === 'ficha') {
            var rowData = tablas.table('#tblFichas').row(this).data();
            $('#miPopupFicha').find('#idFicha').attr('value', rowData[0]);
            $('#miPopupFicha').find('#idCursoFicha').attr('value', rowData[1]);
            $('#miPopupFicha').find('#txtCupos').attr('value', rowData[2]);
            $('#miPopupFicha').find('#txtPrecioFicha').attr('value', rowData[3]);
            $('#miPopupFicha').find('#dateFechaFicha').attr('value', rowData[4]);
            $('#miPopupFicha').find('#btnFicha').attr('value', 'Editar');
            $('#miPopupFicha').modal('show');
            $('#tblFichas tbody').off();
        }
        if (tipo === 'empresa') {
            var rowData = tablas.table('#tblEmpresas').row(this).data();
            $('#miPopupEmpresa').find('#txtNitEmpresa').attr('value', rowData[0]).attr('readonly', true);
            $('#miPopupEmpresa').find('#txtNombreEmpresa').attr('value', rowData[1]);
            $('#miPopupEmpresa').find('#txtDireccionEmpresa').attr('value', rowData[2]);
            $('#miPopupEmpresa').find('#txtNombreContacto').attr('value', rowData[3]);
            $('#miPopupEmpresa').find('#txtTelefonoContacto').attr('value', rowData[4]);
            $('#miPopupEmpresa').find('#txtEmailContacto').attr('value', rowData[5]);
            $('#miPopupEmpresa').find('#btnEmpresa').attr('value', 'Editar');
            $('#miPopupEmpresa').modal('show');
            $('#tblEmpresa tbody').off();
        }
    });
}
;


$('#registrarCategoriaArticulo').on('click', function () {
    $('#btnCategoriaArticulo').attr('value', 'Registrar');
    $('#txtNombreCategoriaArticulo').attr('value', ' ');
    $('#miPopupCategoriaArticulo').modal('show');
});
$('#registrarCategoriaCurso').on('click', function () {
    $('#btnCategoriaCurso').attr('value', 'Registrar');
    $('#txtNombreCategoriaCurso').attr('value', ' ');
    $('#miPopupCategoriaCurso').modal('show');
});
$('#registrarSeminario').on('click', function () {
    $('#btnSeminario').attr('value', 'Registrar');
    $('#txtNombreSeminario').attr('value', ' ');
    $('#txtDuracion').attr('value', ' ');
    $('#ddlEstadosemiario').attr('value', ' ');
    $('#miPopupSeminario').modal('show');
});
$('#registrarAbono').on('click', function () {
    $('#btnAbono').attr('value', 'Registrar');
    $('#txtIdCredito').attr('value', ' ');
    $('#txtValorAbono').attr('value', ' ');
    $('#dateFechaPago').attr('value', ' ');
    $('#miPopupAbono').modal('show');
});

function myAjax(id, controller, method, action, aux) {
    var form = $('<form method="' + method + '" action="' + controller + '">' +
            '<input type="hidden" name="id" value="' + id + '">\n\
            <input type="hidden" id="action" name="action" value="' + action + '"></form>');
    $(form).submit(function () {
        $.ajax({
            type: $(form).attr('method'),
            url: $(form).attr('action'),
            data: $(form).serialize(),
            success: function (data) {
                if (controller === 'ControllerCurso') {
                    if (action === 'Consultar') {
                        if (aux === 'Editar') {
                            curso.editar(data);
                        }
                        else {
                            curso.consultar(data);
                        }
                    } else if (action === 'Estado') {
                        curso.cambiarEstado(data);
                        myAjax(null, 'ControllerCurso', 'POST', 'Enlistar', null);
                    } else if (action === 'Editar') {
                        myAjax(null, 'ControllerCurso', 'POST', 'Enlistar', null);
                    } else if (action === 'Enlistar') {
                        curso.actualizarTabla(data);
                    }
                }
            }
        });
        return false;
    });
    $(form).submit();
}
;

var curso = {
    consultar: function (data) {
        $('#miPopupCurso').find('#idCurso').attr('value', data['idCurso']);
        $('#miPopupCurso').find('#ddlCategoria').attr('value', data['nombreCategoriaCurso']);
        $('#miPopupCurso').find('#txtNombre').attr('value', data['nombreCurso']);
        $('#miPopupCurso').find('#dateDuracion').attr('value', data['duracionCurso']);
        $('#miPopupCurso').find('#txtDescripcionCurso').val(data['descripcionCurso']);
        $('#miPopupCurso').find('#ddlEstado option').prop('selected', false).filter('[value="' + data['estadoCurso'] + '"]').prop('selected', true);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'hidden');
        $('#miPopupCurso').modal('show');
    },
    registrar: function () {
        $('#miPopupCurso').find('#idCurso').attr('value', null);
        $('#miPopupCurso').find('#ddlCategoria').attr('value', null);
        $('#miPopupCurso').find('#txtNombre').attr('value', null);
        $('#miPopupCurso').find('#dateDuracion').attr('value', null);
        $('#miPopupCurso').find('#txtDescripcionCurso').val(null);
        $('#miPopupCurso').find('#ddlEstado option').prop('selected', false);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Registrar');
        $('#miPopupCurso').modal('show');
    },
    editar: function (data) {
        curso.consultar(data);
        $('#miPopupCurso').find('#btnCurso').attr('type', 'submit').attr('value', 'Editar');
    },
    cambiarEstado: function (data) {
        $.notify(data['mensaje'], data['tipo']);
    },
    actualizarTabla: function (data) {
        tablas.tables().destroy();
        $('#tblCursos').find("tbody").empty();
        $('#tblCursos').find("tbody").append(data);
        tablas = $('.tabla').DataTable({
            "language": {
                "url": "public/lang/Spanish.json"
            }
        });
    }
};