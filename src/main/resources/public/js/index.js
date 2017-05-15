$(document).ready(function () {

    $('.btn-success').click(function (e) {
        console.log("hali");
        console.log($(this).attr("name"));

        $.ajax({
            url: $(this).attr("name"),
            type: "GET",
            success: function () {
                window.location.reload();
            },
            timeout: 1500,
            error: function () {
            }

    });

        //e.preventDefault();

    });


});
