
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global articulo */

function limpiar(miForm) {
    $(':input', miForm).each(function () {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'text' || type == 'password' || tag == 'textarea' || type == 'number' || type == 'hidden' || type == 'date' || type == 'email')
            this.value = "";
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
        else if (false)
            this.value = 0;
    });
    if (miForm === '#formMovimiento') {
        $('#tablaDetalleMovimiento tbody tr').each(function () {
            $(this).remove();
        });
    }
}

function habilitar(miForm) {
    $(':input', miForm).each(function () {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'checkbox' || type == 'radio' || tag == 'select')
            this.disabled = false;
        else
            this.disabled = false;
        if (this.id !== 'txtTotalMovimiento') {
            this.readOnly = false;
        }
    });
}

function desabilitar(miForm) {
    $(':input', miForm).each(function () {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'checkbox' || type == 'radio' || tag == 'select')
            this.disabled = true;
        else
            this.readOnly = true;
    });
}

function mensaje(data) {
    $.notify(data['mensaje'], data['tipo']);
}

articulo.listarArticulos();

$('#tabListas').tab('show');

$('#btnGestionCompras').on('click', function () {
    $('#tabMovimientos').find('#btnMovimiento').show();
    habilitar('#formMovimiento');
    limpiar('#formMovimiento');
    var actual = $('#contenidoDinamico').data('actual');
    if (actual == 'listas' || actual == 'venta') {
        $(this).data('target', '#tabMovimientos');
        $('#contenidoDinamico').data('actual', 'compra');
        $('#btnGestionVentas').data('target', '#tabMovimientos');
        $('#tabMovimientos').find('#titulo').text('Registrar Compra');
        $('#tabMovimientos').find('#nombre').text('Nombre del Proveedor');
        $('#tabMovimientos').find('#numero').text('Numero de Factura');
        $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
        $('#tabMovimientos').find('#total').text('Total compra');
        $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'compra.efectuarCompra()').val('Efectuar Compra');
    } else {
        $('#contenidoDinamico').data('actual', 'listas');
        $('#btnGestionVentas').data('target', '#tabListas');
        $(this).data('target', '#tabListas');
    }
    $('#tabMovimientos').find('#ddlArticulos').attr('disabled', false).parents('.row:first').show();
    $('#tabMovimientos').find('#btnArticulo').attr('disabled', false).parents('.row:first').show();
});

$('#btnGestionVentas').on('click', function () {
    $('#tabMovimientos').find('#btnMovimiento').show();
    habilitar('#formMovimiento');
    limpiar('#formMovimiento');
    var actual = $('#contenidoDinamico').data('actual');
    if (actual == 'listas' || actual == 'compra') {
        $(this).data('target', '#tabMovimientos');
        $('#contenidoDinamico').data('actual', 'venta');
        $('#btnGestionCompras').data('target', '#tabMovimientos');
        $('#tabMovimientos').find('#titulo').text('Registrar Venta');
        $('#tabMovimientos').find('#nombre').text('Nombre del Cliente');
        $('#tabMovimientos').find('#numero').text('Numero de Venta');
        $('#tabMovimientos').find('#txtFechaMovimiento').text('Fecha: ' + fecha());
        $('#tabMovimientos').find('#total').text('Total venta');
        $('#tabMovimientos').find('#btnMovimiento').attr('onclick', 'venta.efectuarVenta()').val('Efectuar Venta');
    } else {
        $('#contenidoDinamico').data('actual', 'listas');
        $('#btnGestionCompras').data('target', '#tabListas');
        $(this).data('target', '#tabListas');
    }
    $('#tabMovimientos').find('#ddlArticulos').attr('disabled', false).parents('.row:first').show();
    $('#tabMovimientos').find('#btnArticulo').attr('disabled', false).parents('.row:first').show();
});

var $eventSelect = $("#ddlArticulos");

$eventSelect.on("select2:select", function (e) {
    var id = e.params.data.id;
    if (id != '-1') {
        articulo.seleccionar(id);
    }
});

$(document).ready(function () {
    var fixHelperModified = function (e, tr) {
        var $originals = tr.children();
        var $helper = tr.clone();

        $helper.children().each(function (index) {
            $(this).width($originals.eq(index).width());
        });

        return $helper;
    };

    $(".table-sortable tbody").sortable({
        helper: fixHelperModified
    }).disableSelection();

    $(".table-sortable thead").disableSelection();
});

$.notify.addStyle('foo', {
    html:
            "<div>" +
            "<div class='clearfix'>" +
            "<div class='title' data-notify-html='title'/>" +
            "<div class='buttons'>" +
            "<button class='no'>Cancelar</button>" +
            "<button class='yes' data-notify-text='button'></button>" +
            "</div>" +
            "</div>" +
            "</div>"
});

function fecha() {
    var fullDate = new Date();
    var twoDigitMonth = ((fullDate.getMonth().length + 1) === 1) ? (fullDate.getMonth() + 1) : '0' + (fullDate.getMonth() + 1);
    var currentDate = fullDate.getDate() + "/" + twoDigitMonth + "/" + fullDate.getFullYear();
    return currentDate;
}

var actual = $('#tabCompras').find('#txtTotalCompra').val();
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global articulo */

function limpiar(miForm) {
    $(':input', miForm).each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'text' || type == 'password' || tag == 'textarea' || type == 'number' || type == 'hidden' || type == 'date' || type == 'email')
            this.value = "";
        else if (type == 'checkbox' || type == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
        else if (false)
            this.value = 0;
    });
}

function habilitar(miForm) {
    $(':input', miForm).each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'checkbox' || type == 'radio' || tag == 'select')
            this.disabled = false;
        else
            this.disabled = false;
        this.readOnly = false;
    });
}

function desabilitar(miForm) {
    $(':input', miForm).each(function() {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'checkbox' || type == 'radio' || tag == 'select')
            this.disabled = true;
        else
            this.readOnly = true;
    });
}

function mensaje(data) {
    $.notify(data['mensaje'], data['tipo']);
}

articulo.listarArticulos();

$('#tabListas').tab('show');

$('#btnGestionCompras').on('click', function() {
      limpiar("#tabMovimientos");
    if ($(this).data('target') === '#tabListas') {
        $(this).data('target', '#tabMovimientos');
        $('#tabMovimientos').find('#titulo').text('Registrar Compra');
        $('#tabMovimientos').find('#nombre').text('Nombre del Proveedor');
        $('#tabMovimientos').find('#numero').text('Numero de Factura ');
        $('#tabMovimientos').find('#btnChange').attr('onclick', 'compra.efectuarCompra()').val('Efectuar Compra');
    } else {
        $(this).data('target', '#tabListas');
    }
});

$('#btnGestionVentas').on('click', function() {
     limpiar("#tabMovimientos");
    if ($(this).data('target') === '#tabListas') {
        $(this).data('target', '#tabMovimientos');
        $('#tabMovimientos').find('#titulo').text('Registrar Venta');
        $('#tabMovimientos').find('#nombre').text('Nombre del Cliente');
        $('#tabMovimientos').find('#numero').text('Numero de Venta');
        $('#tabMovimientos').find('#btnChange').attr('onclick', 'venta.efectuarCompra()').val('Efectuar Venta');
     
    } else {
        $(this).data('target', '#tabListas');
    }
});



var $eventSelect = $("#ddlArticulos");

$eventSelect.on("select2:select", function(e) {
    var id = e.params.data.id;
    
    if (id != '-1') {
        articulo.seleccionar(id);
    }
});

$(document).ready(function() {
    var fixHelperModified = function(e, tr) {
        var $originals = tr.children();
        var $helper = tr.clone();

        $helper.children().each(function(index) {
            $(this).width($originals.eq(index).width());
        });

        return $helper;
    };

    $(".table-sortable tbody").sortable({
        helper: fixHelperModified
    }).disableSelection();

    $(".table-sortable thead").disableSelection();
});

$.notify.addStyle('foo', {
    html:
            "<div>" +
            "<div class='clearfix'>" +
            "<div class='title' data-notify-html='title'/>" +
            "<div class='buttons'>" +
            "<button class='no'>Cancelar</button>" +
            "<button class='yes' data-notify-text='button'></button>" +
            "</div>" +
            "</div>" +
            "</div>"
});

var actual = $('#tabCompras').find('#txtTotalCompra').val();

