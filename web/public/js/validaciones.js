/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.addMethod("nombres", function(value, element) {
    return this.optional(element) || /^[ÁÉÍÓÚáéíóúñÑa-zA-Z ]{3,15}/.test(value);
}, 'Entre 3 y 15 letras y no se permiten numeros');

$.validator.addMethod("fechaMayor", function(value, element) {
    var fecha = new Date();
    //var fechaSeminario = new Date(value.substr(6,4),value.substr(3,2),value.substr(0,2),value.substr(11,2),value.substr(14,2));    
    dformat = [fecha.getDate(), fecha.getMonth() + 1, fecha.getFullYear()].join('/') + ' ' + [fecha.getHours(), fecha.getMinutes()].join(':');
    return this.optional(element) || value > dformat;
}, 'No se puede registrar un seminario el mismo dia de su realización');

var fecha = new Date();
dformat = [fecha.getDate(), fecha.getMonth() + 1, fecha.getFullYear()].join('/') + ' ' + [fecha.getHours(), fecha.getMinutes()].join(':');
/*estructura json*/


var validaciones = {
    seminario: function() {
    }
}
var validationCurso = $('#formCurso').validate({
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
$('#miPopupCurso').on('shown.bs.modal', function() {
    validationCurso.resetForm();
});

//$.validator.addMethod("nombres", function(value) {
//    return ;
//}, '');

