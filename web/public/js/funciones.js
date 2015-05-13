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
var $otroContenedor = $('#compras').html();
var $contenedor = $('#contenedor').html();
function cambiarPantalla() {

    if ($('#contenedor').data('tipo') == 'tablas') {
        $('#contenedor').empty();
        $('#contenedor').append($otroContenedor);
        $('#contenedor').data('tipo', 'compra');
    }
    else if ($('#contenedor').data('tipo') == 'compra') {
        $('#contenedor').empty();
        $('#contenedor').append($contenedor);
        $('#contenedor').data('tipo', 'tablas');
        compra.cargar();
    }
}
