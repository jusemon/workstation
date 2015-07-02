/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.validator.addMethod("nombres", function (value, element) {
    return this.optional(element) || /^[ÁÉÍÓÚáéíóúñÑa-zA-Z ]{3,15}/.test(value);
}, 'Entre 3 y 15 letras y no se permiten números.');

$.validator.addMethod("precioMin", function (value, element) {
    return this.optional(element) || precioMin(value);
}, 'El precio de venta no debe ser menor al de compra');

$.validator.addMethod("descripcionArticulo", function (value, element) {
    return this.optional(element) || /^[áéíóúÁÉÍÓÚñÑ°.,:'&quot;0-9a-zA-Z ]{3,30}/.test(value);
}, 'Entre 3 y 30 letras, se permiten números y algunos caracteres como , y .');
var salida;
$.validator.addMethod("pass", function (value, element) {
    return this.optional(element) || /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/.test(value);
}, 'La contraseña debe contener al menos una letra minúscula, otra mayúscula y un número, la contraseña no debe ser menor a seis dígitos.');

$.validator.addMethod("identificacion", function (value, element) {
    return this.optional(element) || /^[0-9]{5,15}/.test(value);
}, 'Entre 5 y  15 dígitos.');

$.validator.addMethod("fecha", function (value, element) {
    return this.optional(element) || /^(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[0-2]).([0-9]{4})/.test(value);
}, 'El formato de la fecha debe ser dd/mm/yyyy');

$.validator.addMethod("fechaMayor", function (value, element) {
    var fecha = new Date();
    //var fechaSeminario = new Date(value.substr(6,4),value.substr(3,2),value.substr(0,2),value.substr(11,2),value.substr(14,2));    
    var dia, mes, año;
    dia = parseInt(value.substr(0, 2));
    mes = parseInt(value.substr(3, 2));
    año = parseInt(value.substr(6, 4));
    if (año >= fecha.getFullYear()) {
        if (año > fecha.getFullYear()) {
            return true;
        }
        else {
            if (mes >= fecha.getMonth() + 1) {
                if (mes === fecha.getMonth() + 1) {
                    return dia > fecha.getDate();
                }
                else {
                    return true;
                }
            }
        }
    }
}, 'La fecha ingresada no puede ser menor o igual a la actual.');

//Validación para calcular que el cliente cuenta con por lo menos 5 años de edad
$.validator.addMethod("edad", function (value, element) {
    var fecha = new Date();
    //var fechaSeminario = new Date(value.substr(6,4),value.substr(3,2),value.substr(0,2),value.substr(11,2),value.substr(14,2));    
    var dia, mes, año;
    dia = parseInt(value.substr(0, 2));
    mes = parseInt(value.substr(3, 2));
    año = parseInt(value.substr(6, 4));
    if (año <= fecha.getFullYear() - 5) {
        if (año < fecha.getFullYear() - 5) {
            return true;
        }
        else {
            if (mes <= fecha.getMonth() + 1) {
                if (mes === fecha.getMonth() + 1) {
                    return dia < fecha.getDate();
                }
                else {
                    return true;
                }
            }
        }
    }
}, 'Para registrarse debe contar con por lo menos 5 años de edad.');

//Validación para calcular que el cliente cuenta con por lo menos 18 años de edad
$.validator.addMethod("mayorDeEdad", function (value, element) {
    return this.optional(element) | mayorDeEdad(value);
}, 'Para registrarse debe ser mayor de edad');

jQuery.validator.setDefaults({
    debug: true,
    success: "valid"
});

if (document.getElementById('formCurso') !== null) {
    validationCurso = $('#formCurso');
    validationCurso.validate({
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
                min: 24000,
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
}

if (document.getElementById('formArticulo') !== null) {
    validationArticulo = $('#formArticulo');
    validationArticulo.validate({
        onsubmit: false,
        rules: {
            txtDescripcion: {
                required: true,
                descripcionArticulo: true
            },
            txtPrecioCompra: {
                required: true,
                min: 50,
                max: 10000000,
                digits: true
            },
            txtPrecioVenta: {
                required: true,
                digits: true,
                precioMin: true
            }
        }
    });
}

if (document.getElementById('formUsuario') !== null) {
    validationUsuario = $('#formUsuario');
    validationUsuario.validate({
        rules: {
            ddlIdentificacion: {
                required: true
            },
            txtIdentificacion: {
                required: true,
                identificacion: true,
                digits: true
            },
            txtNombre: {
                required: true,
                nombres: true
            },
            txtApellido: {
                required: true,
                nombres: true
            },
            dateFechaNacimiento: {
                required: true,
                fecha: true,
                edad: true
            },
            txtPass4: {
                required: true,
                pass: true
            },
            txtPass2: {
                required: true,
                equalTo: "#txtPass4"
            },
            txtTelefono: {
                required: true,
                minlength: 7,
                maxlength: 15,
                digits: true
            },
            txtCorreo: {
                required: true,
                email: true
            }
        }
    });
}

if (document.getElementById('formAcudiente') !== null) {
    validationAcudiente = $('#formAcudiente');
    validationAcudiente.validate({
        rules: {
            ddlIdentificacion: {
                required: true
            },
            txtIdentificacion: {
                required: true,
                identificacion: true,
                digits: true
            },
            txtNombre: {
                required: true,
                nombres: true
            },
            txtApellido: {
                required: true,
                nombres: true
            },
            dateFechaNacimiento: {
                required: true,
                fecha: true,
                mayorDeEdad: true
            },
            txtTelefono: {
                required: true,
                minlength: 7,
                maxlength: 15,
                digits: true
            }
        }
    });
}

if (document.getElementById('form_estudiante') !== null) {
    validationEstudiante = $('#form_estudiante');
    validationEstudiante.validate({
        onsubmit: false,
        rules: {
            ddlIdentificacion: {
                required: true
            },
            txtIdentificacion: {
                required: true,
                identificacion: true,
                digits: true
            },
            txtNombre: {
                required: true,
                nombres: true
            },
            txtApellido: {
                required: true,
                nombres: true
            },
            dateFechaNacimiento: {
                required: true,
                fecha: true,
                edad: true
            },
            txtPass: {
                required: true,
                pass: true
            },
            txtPass2: {
                required: true,
                equalTo: "#txtPass"
            },
            txtDireccion: {
                required: true,
                maxlength: 50
            },
            txtTelefono: {
                required: true,
                minlength: 7,
                maxlength: 15,
                digits: true
            },
            txtCelular: {
                required: true,
                minlength: 10,
                maxlength: 22,
                digits: true
            }
        }
    });
}

if (document.getElementById('formActualizarDatos') !== null) {
    validationUsuarioPerfil = $('#formActualizarDatos');
    validationUsuarioPerfil.validate({
        rules: {
            ddlIdentificacion: {
                required: true
            },
            txtIdentificacion: {
                required: true,
                identificacion: true,
                digits: true
            },
            txtNombre: {
                required: true,
                nombres: true
            },
            txtApellido: {
                required: true,
                nombres: true
            },
            dateFechaNacimiento: {
                required: true,
                fecha: true,
                edad: true
            },
            txtPass2: {
                required: true,
                equalTo: txtPass
            },
            txtPass: {
                required: true,
                pass: true
            }
        }
    });

}

if (document.getElementById('formBeneficiario') !== null) {
    validationBeneficiario = $('formBeneficiario');
    validationBeneficiario.validate({
        rules: {
            txtValorBeneficio: {
                min: 360000,
                max: 510000,
                required: true
            }
        }
    });
}

if (document.getElementById('formAsistenteSeminario') !== null) {
    validationAsistenteSeminario = $('#formAsistenteSeminario');
    validationAsistenteSeminario.validate({
        rules: {
            txtNombre: {
                required: true,
                minlength: 10,
                maxlength: 50
            },
            txtTelefono: {
                required: true,
                minlength: 7,
                maxlength: 15,
                digits: true
            },
            txtCorreo: {
                required: true,
                email: true
            }
        }
    });
}

if (document.getElementById('formOperario') !== null) {
    validationOperarios = $('#formOperario');
    validationOperarios.validate({
        rules: {
            ddlIdentificacion: {
                required: true
            },
            txtIdentificacion: {
                required: true,
                identificacion: true,
                digits: true
            },
            txtNombre: {
                required: true,
                nombres: true
            },
            txtApellido: {
                required: true,
                nombres: true
            },
            dateFechaNacimiento: {
                required: true,
                fecha: true,
                mayorDeEdad: true
            },
            txtPass: {
                required: true,
                pass: true
            },
            txtPass2: {
                required: true,
                equalTo: txtPass
            },
            txtTelefono: {
                required: true,
                minlength: 7,
                maxlength: 15,
                digits: true
            },
            txtCorreo: {
                required: true,
                email: true
            }
        }
    });
}

$('#miPopupCurso').on('shown.bs.modal', function () {
    validationCurso.validate().resetForm();
});
$('#miPopupUsuario').on('shown.bs.modal', function () {
    validationUsuario.validate().resetForm();
});
$('#miPopupAcudiente').on('shown.bs.modal', function () {
    validationAcudiente.validate().resetForm();
});
$('#miPopupArticulo').on('shown.bs.modal', function () {
    validationArticulo.validate().resetForm();
});
$('#miPopupEstudiante').on('shown.bs.modal', function () {
    validationEstudiante.validate().resetForm();
});
$('#miPopupBeneficiario').on('shown.bs.modal', function () {
    validationBeneficiario.validate().resetForm();
});
$('#miPopupOperario').on('shown.bs.modal', function () {
    validationOperarios.validate().resetForm();
});
$('#miPopupAsistenteSeminario').on('shown.bs.modal', function () {
    validationAsistenteSeminario.validate().resetForm();
});
//$.validator.addMethod("nombres", function(value) {
//    return ;
//}, '');

function validacionFechaRegistroASeminario(fechaSeminario) {
    var fecha = new Date();
    //var fechaSeminario = new Date(value.substr(6,4),value.substr(3,2),value.substr(0,2),value.substr(11,2),value.substr(14,2));    
    var dia, mes, año, hora, minuto;
    dia = parseInt(fechaSeminario.substr(0, 2));
    mes = parseInt(fechaSeminario.substr(3, 2));
    año = parseInt(fechaSeminario.substr(6, 4));
    hora = parseInt(fechaSeminario.substr(11, 2));
    minuto = parseInt(fechaSeminario.substr(14, 2));
    if (año >= fecha.getFullYear()) {
        if (año > fecha.getFullYear()) {
            return true;
        }
        else {
            if (mes >= (fecha.getMonth() + 1)) {
                if (mes === (fecha.getMonth() + 1)) {
                    if (dia > fecha.getDate()) {
                        return true;
                    } else if (dia === fecha.getDate()) {
                        if (hora >= fecha.getHours()) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {

                        return false;
                    }
                } else {
                    return true;
                }
            }
            else {                
                return false;
            }
        }
    }
}

function precioMin(precioVenta) {
    var precioCompra = parseInt($('#formArticulo').find('#txtPrecioCompra').val());
    return precioCompra < precioVenta;
}
