$(document).ready(function () {


    $(function () {
        var pop = $('.popbtn');
        var row = $('.row:not(:first):not(:last)');


        pop.popover({
            trigger: 'manual',
            html: true,
            container: 'body',
            placement: 'bottom',
            animation: false,
            content: function () {
                return $('#popover').html();
            }
        });

        $('[id^="inpn"]').submit(function (e) {
            e.preventDefault();

            var link = "/cart/edit/" + $(this).attr("name");
            var name = $(this).attr("name");
            var id = $("#" + name);

            $.ajax({
                url: link,
                type: "POST",
                data: {"id": name, "quantity": id.val()},
                dataType: "html",
                success: function () {
                },
                timeout: 1500,
                error: function () {
                }
            });
        })

        setTimeout(function () {

            // Something you want delayed.


            pop.on('click', function (e) {
                pop.popover('toggle');
                document.getElementById("delete-item").href = "/cart/delete/" + $(this).attr('name');
                pop.not(this).popover('hide');
            });
        }, 1000);


        $(window).on('resize', function () {
            pop.popover('hide');
        });

        row.on('touchend', function (e) {
            $(this).find('.popbtn').popover('toggle');
            row.not(this).find('.popbtn').popover('hide');
            return false;
        });

    });


    $('#same_as_shipping').click(function () {
        if ($('#same_as_shipping').is(':checked')) {
            $('#billing-country').val($('#shipping-country').val());
            $('#billing-city').val($('#shipping-city').val());
            $('#billing-zip_code').val($('#shipping-zip_code').val());
            $('#billing-address').val($('#shipping-address').val());

        } else {
            $('#billing-country').val("");
            $('#billing-city').val("");
            $('#billing-zip_code').val("");
            $('#billing-address').val("");
        }
        ;

    });


    $('#credit_card_form').submit(function (event) {
        alert("Credit Card payment successful");
    });

    $('#paypal_form').submit(function (event) {
        alert("PayPal payment successful");
    });

});



