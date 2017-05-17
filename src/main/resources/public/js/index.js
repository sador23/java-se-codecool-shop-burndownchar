$(document).ready(function () {

    $('.btn-success').click(function (e) {
        console.log("hali");
        console.log($(this).attr("name"));

        $.ajax({
            url: $(this).attr("name"),
            type: "GET",
            success: function () {
                $('#cart-counter').load(document.URL +  ' #cart-counter');
            },
            timeout: 1500,
            error: function () {
            }

    });

        //e.preventDefault();

    });


});
