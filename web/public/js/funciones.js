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
}

function habilitar(miForm) {
    $(':input', miForm).each(function () {
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
    if ($(this).data('target') === '#tabListas') {
        $(this).data('target', '#tabCompras');
    } else {
        $(this).data('target', '#tabListas');
    }
});

$('#btnGestionVentas').on('click', function () {
    if ($(this).data('target') === '#tabListas') {
        $(this).data('target', '#tabVentas');
    } else {
        $(this).data('target', '#tabListas');
    }
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

var actual = $('#tabCompras').find('#txtTotalCompra').val();