$(function() {
	var pop = $('.popbtn');
	var row = $('.row:not(:first):not(:last)');


	pop.popover({
		trigger: 'manual',
		html: true,
		container: 'body',
		placement: 'bottom',
		animation: false,
		content: function() {
			return $('#popover').html();
		}
	});


	pop.on('click', function(e) {
		pop.popover('toggle');
		document.getElementById("delete-item").href="/cart/delete/"  + $(this).attr('name');
        document.getElementById("edit-item").name="/cart/edit/"  + $(this).attr('name');
		pop.not(this).popover('hide');
	});

	document.getElementById("edit-item").onclick(function () {
        $('#myModal').modal('show');

    })

	$(window).on('resize', function() {
		pop.popover('hide');
	});

	row.on('touchend', function(e) {
		$(this).find('.popbtn').popover('toggle');
		row.not(this).find('.popbtn').popover('hide');
		return false;
	});

});