/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.addMethod("nombres", function (value, element) {
    return this.optional(element) || /^[ÁÉÍÓÚáéíóúñÑa-zA-Z ]{3,15}/.test(value);
}, 'Entre 3 y 15 letras y no se permiten números');

$.validator.addMethod("descripcionArticulo", function (value, element) {    
    return this.optional(element) || /^[áéíóúÁÉÍÓÚñÑ°.,:'&quot;0-9a-zA-Z ]{3,30}/.test(value);
}, 'Entre 3 y 30 letras, se permiten numeros y algunos caracteres como , y .');

$.validator.addMethod("fechaMayor", function (value, element) {
    var fecha = new Date();
    //var fechaSeminario = new Date(value.substr(6,4),value.substr(3,2),value.substr(0,2),value.substr(11,2),value.substr(14,2));    
    var dia, mes, año;
    dia = value.substr(0, 2);
    mes = value.substr(3, 2);
    año = value.substr(6, 4);
    if (año >= fecha.getFullYear()) {
        if (mes >= fecha.getMonth() + 1) {
            if (mes == fecha.getMonth() + 1) {
                return dia > fecha.getDate();
            }
            else {
                return true;
            }
        }
    }

}, 'La fecha no puede ser menor o igual');

var fecha = new Date();
dformat = [fecha.getDate(), fecha.getMonth() + 1, fecha.getFullYear()].join('/') + ' ' + [fecha.getHours(), fecha.getMinutes()].join(':');
/*estructura json*/


var validaciones = {
    seminario: function () {
    }
}
validationCurso = $('#formCurso').validate({
    debug: true,
    onsubmit: false,
    rules: {
        txtNombre: {
            required: true,
            nombres: true
        },
        txtFechaSeminario: {
            required: true,
            fechaMayor: true
        },
        txtDescripcionCurso: {
            minlength: 3,
            maxlength: 100,
            required: true
        },
        txtPrecio: {
            min: 100000,
            max: 500000,
            required: true
        },
        txtCantidadClases: {
            min: 1,
            max: 30,
            required: true
        },
        txtCantidadHoras: {
            min: 1,
            max: 5,
            required: true
        },
        txtCupoSeminario: {
            min: 1,
            max: 50,
            required: true
        }
    }
});

validationArticulo = $('#formArticulo').validate({
    debug: true,
    onsubmit: false,
    rules: {
        txtDescripcion: {
            required: true,
            descripcionArticulo: true
        },
        txtPrecioCompra: {
            required: true,
            min: 50,
            max: 100000
        },
          txtPrecioVenta: {
            required: true,
            min: 50,
            max: 100000
        },
          txtCantidadArticulo: {
            required: true,
            min: 0,
            max: 1000
        }
        
    }
});

$('#miPopupCurso').on('shown.bs.modal', function () {
    validationCurso.resetForm();
});

//$.validator.addMethod("nombres", function(value) {
//    return ;
//}, '');

