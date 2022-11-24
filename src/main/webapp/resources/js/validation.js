$('#assetStatus').on('click', function() {
	if ($(this).is(':checked')) {
		$('#statusValue').val(1);
	} else {
		$('#statusValue').val(0);
	}
});
