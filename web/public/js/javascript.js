
$(document).ready(function () {

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



$(function () {
    /* BOOTSNIPP FULLSCREEN FIX */
    $('#boton').on('click', function (event) {
        event.preventDefault();
        $('#miPopup').modal({
            backdrop: 'static',
            keyboard: false
        });
        $('#miPopup').modal('show');
    })
});

$(function () {
    /* BOOTSNIPP FULLSCREEN FIX */
    if (window.location == window.parent.location) {
        $('#back-to-bootsnipp').removeClass('hide');
        $('.alert').addClass('hide');
    }

    $('#fullscreen').on('click', function (event) {
        event.preventDefault();
        window.parent.location = "http://bootsnipp.com/iframe/Q60Oj";
    });
    $('tbody > tr').on('click', function (event) {
        event.preventDefault();
        $('#myModal').modal('show');
    })

    $('.btn-mais-info').on('click', function (event) {
        $('.open_info').toggleClass("hide");
    })
});



    