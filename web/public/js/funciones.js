/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
function cambiarPantalla() {
    if ($('#tabListas').hasClass('active')) {
        $('#tabListas').removeClass('active');
        $('#tabCompras').addClass('active');
    } else {
        $('#tabListas').addClass('active');
        $('#tabCompras').removeClass('active');
    }
}


var $eventSelect = $("#ddlArticulos");

$eventSelect.on("select2:select", function (e) {
    var id = e.params.data.id;
    if (id != '-1') {
        articulo.seleccionar(id);
    }
});
$(document).ready(function() {
var fixHelperModified = function (e, tr) {
    var $originals = tr.children();
    var $helper = tr.clone();

    $helper.children().each(function (index) {
        $(this).width($originals.eq(index).width())
    });

    return $helper;
};

$(".table-sortable tbody").sortable({
    helper: fixHelperModified
}).disableSelection();

$(".table-sortable thead").disableSelection();
});

