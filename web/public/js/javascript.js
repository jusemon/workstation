$(document).ready(function () {
    var enlace = window.location.search;
    if (enlace.indexOf('mensaje=2') !== -1) {
        $.notify('Has ingresado un usuario o contraseña incorrectos', 'error');
    }
    else if (enlace.indexOf('mensaje=1') !== -1) {
        $.notify('Bienvenido al Sistema WorkStation', 'success');
    }
    var URLactual = window.location.pathname;
    if (URLactual.indexOf('index') !== -1) {
        $('#btnindex').attr('class', 'active');
    } else if (URLactual.indexOf('nuestro') !== -1) {
        $('#btnnuestro').attr('class', 'active');
    } else if (URLactual.indexOf('acerca') !== -1) {
        $('#btnacerca').attr('class', 'active');
    } else if (URLactual.indexOf('matricula') !== -1) {
        $('#btnmatricula').attr('class', 'active');
    } else if (URLactual.indexOf('empresa') !== -1) {
        $('#btnempresa').attr('class', 'active');
    } else if (URLactual.indexOf('curso') !== -1) {
        $('#btncurso').attr('class', 'active');
    } else if (URLactual.indexOf('ficha') !== -1) {
        $('#btnficha').attr('class', 'active');
    } else if (URLactual.indexOf('articulo') !== -1) {
        $('#btnarticulo').attr('class', 'active');
    } else if (URLactual.indexOf('caja') !== -1) {
        $('#btncaja').attr('class', 'active');
    } else {
        $('#btnindex').attr('class', 'active');
    }
    $('.imgIndex').on('load', function () {
        $('.imgIndex').height('80%');
    });
    $('#fecha').on('load', function () {
        var f = new Date();
        var x = (f.getDate() + "-" + (f.getMonth() + 1) + "-" + f.getFullYear());
        $('#fecha').attr('value', x);
        alert($('#fecha').attr('value'));
    });
});

$('#radioBtn2 a').on('click', function () {
    var sel = $(this).data('title');
    var tog = $(this).data('toggle');
    $('#' + tog).prop('value', sel);
    $('a[data-toggle="' + tog + '"]').not('[data-title="' + sel + '"]').removeClass('active').addClass('notActive');
    $('a[data-toggle="' + tog + '"][data-title="' + sel + '"]').removeClass('notActive').addClass('active');
});

$('#radioBtn a').on('click', function () {
    var sel = $(this).data('title');
    var tog = $(this).data('toggle');
    $('#' + tog).prop('value', sel);
    $('a[data-toggle="' + tog + '"]').not('[data-title="' + sel + '"]').removeClass('active').addClass('notActive');
    $('a[data-toggle="' + tog + '"][data-title="' + sel + '"]').removeClass('notActive').addClass('active');
});

//Empresa

var tableEmpresa = $('#tblEmpresa').DataTable({
    "scrollX": true,
    "language": {
        "url": "public/lang/Spanish.json"
    }
});

function editarEmpresa() {
    $('#tblEmpresa tbody').on('click', 'tr', function() {
        var tipo = $(this).data('tipo');
        if (tipo === 'Nombre Empresa') {
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
        }
    });
};
