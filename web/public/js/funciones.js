
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

$('#tabListas').tab('show');

$('#btnGestionCompras').on('click', function () {
    $('#tabMovimientos').find('#btnMovimiento').show();
    habilitar('#formMovimiento');
    limpiar('#formMovimiento');
    var actual = $('#contenidoDinamico').data('actual');
    if (actual == 'listas' || actual == 'venta' || actual == 'credito') {
        compra.show('Registrar');
        articulo.listarArticulos('Compras');
        $('#btnGestionVentas').data('target', '#tabMovimientos');
        $('#btnGestionCredito').data('target', '#tabMovimientos');
        $(this).data('target', '#tabMovimientos');
    } else {
        $('#contenidoDinamico').data('actual', 'listas');
        $('#btnGestionVentas').data('target', '#tabListas');
        $('#btnGestionCredito').data('target', '#tabListas');
        $(this).data('target', '#tabListas');
    }

});

$('#btnGestionVentas').on('click', function () {
    $('#tabMovimientos').find('#btnMovimiento').show();
    habilitar('#formMovimiento');
    limpiar('#formMovimiento');
    var actual = $('#contenidoDinamico').data('actual');
    if (actual == 'listas' || actual == 'compra' || actual == 'credito') {
        venta.show('Registrar')
        articulo.listarArticulos('Venta');
        $('#btnGestionCompras').data('target', '#tabMovimientos');
        $('#btnGestionCredito').data('target', '#tabMovimientos');
        $(this).data('target', '#tabMovimientos');
    } else {
        $('#contenidoDinamico').data('actual', 'listas');
        $('#btnGestionCredito').data('target', '#tabListas');
        $('#btnGestionCompras').data('target', '#tabListas');
        $(this).data('target', '#tabListas');
    }
});

$('#btnGestionCredito').on('click', function () {
    $('#tabMovimientos').find('#btnMovimiento').show();
    habilitar('#formMovimiento');
    limpiar('#formMovimiento');
    var actual = $('#contenidoDinamico').data('actual');
    if (actual == 'listas' || actual == 'venta' || actual == 'compra') {
        credito.show('Registrar');
        articulo.listarArticulos('Venta');
        $('#btnGestionVentas').data('target', '#tabMovimientos');
        $('#btnGestionCompras').data('target', '#tabMovimientos');
        $(this).data('target', '#tabMovimientos');
    } else {
        $('#contenidoDinamico').data('actual', 'listas');
        $('#btnGestionVentas').data('target', '#tabListas');
        $('#btnGestionCompras').data('target', '#tabMovimientos');
        $(this).data('target', '#tabListas');
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