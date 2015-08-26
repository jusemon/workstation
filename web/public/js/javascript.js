$(document).ready(function () {
    var enlace = window.location.search;
    if (enlace.indexOf('mensaje=2') !== -1) {
        $.notify('Has ingresado un usuario o contraseña incorrectos', 'error');
    }
    else if (enlace.indexOf('mensaje=1') !== -1) {
        $.notify('Bienvenido al Sistema WorkStation', 'success');
    }
    else if (enlace.indexOf('mensaje=3') !== -1) {
        $.notify('Su cuenta se encuentra desactivada, póngase en contacto con el administrador', 'warning');
    }
    var URLactual = window.location.pathname;
    if (URLactual.indexOf('index') !== -1) {
        $('#btnindex').attr('class', 'active');
    } else if (URLactual.indexOf('nuestro') !== -1) {
        $('#btnnuestro').attr('class', 'active');
        curso.mostrarDisponibles();
        seminario.mostrarDisponibles();
        setInterval(curso.mostrarDisponibles, 60000);
        setInterval(seminario.mostrarDisponibles, 60000);
        $.ajaxSetup({cache: false});
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
    } else if (URLactual.indexOf('operario') !== -1) {
        $('#btnoperario').attr('class', 'active');
    }
    else {
        $('#btnindex').attr('class', 'active');
    }
    $('#fecha').on('load', function () {
        var f = new Date();
        var x = (f.getDate() + "-" + (f.getMonth() + 1) + "-" + f.getFullYear());
        $('#fecha').attr('value', x);
        alert($('#fecha').attr('value'));
    });
});





