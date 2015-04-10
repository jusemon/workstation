/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function editar() {
    var tipo;
    $('#tblCategoriaCursos tbody').on('click', 'tr', function () {
        tipo = $(this).data('tipo');
        if (tipo === 'categoria curso') {
            var rowData = tablas.table('#tblCategoriaCursos').row(this).data();
            $('#idCategoriaCurso').attr('value', rowData[0]);
            $('#txtNombreCategoriaCurso').attr('value', rowData[1]);
            $('#btnCategoriaCurso').attr('value', 'Editar');
            $('#miPopupCategoriaCurso').modal('show');
            $('#tblCategoriaCursos tbody').off();
        }
    });
    ;
    $('#tblSeminarios tbody').on('click', 'tr', function () {
        tipo = $(this).data('tipo');
        if (tipo === 'seminario') {
            var rowData = tablas.table('#tblSeminarios').row(this).data();
            $('#idSeminario').attr('value', rowData[0]);
            $('#txtNombreSeminario').attr('value', rowData[1]);
            $('#txtDuracion').attr('value', rowData[2]);
            $('#ddlEstadosemiario').attr('value', rowData[3]);
            $('#btnSeminario').attr('value', 'Editar');
            $('#miPopupSeminario').modal('show');
            $('#tblSeminarios tbody').off();
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

