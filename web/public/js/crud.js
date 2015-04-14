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
var tableEmpresa = $('#tblEmpresas').DataTable({
    "scrollX": true,
    "language": {
        "url": "public/lang/Spanish.json"
    }
});

function editar() {
    var tipo;
    $('.table tbody').on('click', 'tr', function () {
        tipo = $(this).data('tipo');
        if (tipo === 'categoria curso') {
            var rowData = tablas.table('#tblCategoriaCursos').row(this).data();
            $('#idCategoriaCurso').attr('value', rowData[0]);
            $('#txtNombreCategoriaCurso').attr('value', rowData[1]);
            $('#btnCategoriaCurso').attr('value', 'Editar');
            $('#miPopupCategoriaCurso').modal('show');
            $('#tblCategoriaCursos tbody').off();
        }
        else if (tipo === 'categoria articulo') {
            var rowData = tablas.table('#tblCategoriaArticulos').row(this).data();
            $('#idCategoriaArticulo').attr('value', rowData[0]);
            $('#txtNombreCategoriaArticulo').attr('value', rowData[1]);
            $('#btnCategoriaArticulo').attr('value', 'Editar');
            $('#miPopupCategoriaArticulo').modal('show');
            $('#tblCategoriaArticulos tbody').off();
        }
        else if (tipo === 'seminario') {
            var rowData = tablas.table('#tblSeminarios').row(this).data();
            $('#idSeminario').attr('value', rowData[0]);
            $('#txtNombreSeminario').attr('value', rowData[1]);
            $('#txtDuracion').attr('value', rowData[2]);
            $('#ddlEstadosemiario').attr('value', rowData[3]);
            $('#btnSeminario').attr('value', 'Editar');
            $('#miPopupSeminario').modal('show');
            $('#tblSeminarios tbody').off();
        }
        else if (tipo === 'articulo') {
            var rowData = tablas.table('#tblArticulos').row(this).data();
            $('#idArticulo').attr('value', rowData[0]);
            $('#txtNombreArticulo').attr('value', rowData[1]);
            $('#txtPrecioArticulo').attr('value', rowData[2]);
            $('#txtCantidadArticulo').attr('value', rowData[3]);
            $('#idCategoriaArticulo').attr('value', rowData[4]);
            $('#btnArticulo').attr('value', 'Editar');
            $('#miPopupArticulo').modal('show');
            $('#tblArticulos tbody').off();
        }
        else if (tipo === 'ficha') {
            alert(tipo);
            var rowData = tablas.table('#tblFichas').row(this).data();
            $('#idFicha').attr('value', rowData[0]);
            $('#idCursoFicha').attr('value', rowData[1]);
            $('#txtCupos').attr('value', rowData[2]);
            $('#txtPrecioFicha').attr('value', rowData[3]);
            $('#dateFechaFicha').attr('value', rowData[4]);
            $('#btnFicha').attr('value', 'Editar');
            $('#miPopupFicha').modal('show');
            $('#tblFichas tbody').off();
        }
        if (tipo === 'Nombre Empresa') {
            $('#tblEmpresa tbody').on('click', 'tr', function () {
                var rowData = tableEmpresa.row(this).data();
                $('#txtNitEmpresa').attr('value', rowData[0]);
                $('#txtNombreEmpresa').attr('value', rowData[1]);
                $('#txtDireccionEmpresa').attr('value', rowData[2]);
                $('#txtNombreContacto').attr('value', rowData[3]);
                $('#txtTelefonoContacto').attr('value', rowData[4]);
                $('#txtEmailContacto').attr('value', rowData[5]);

                $('#btnEmpresa').attr('value', 'Editar');
                $('#miPopupEmpresa').modal('show');
                $('#tblEmpresa tbody').off();
            });
        }
    });
}
;

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

$('#registrarCurso').on('click', function () {
    $('#btnCurso').attr('value', 'Registrar');
    $('#miPopupCurso').modal('show');
});

function editarEmpresa() {

}
;


function consultar(id) {
    var form = $('<form method="post" action="ControllerCurso">' +
            '<input type="hidden" name="idCurso" value="' + id + '">\n\
             <input type="hidden" name="action" value="Consultar"></form>');
    $(form).submit();
}
;
