
$(document).ready(function() {
	$( "#autocompleteText" ).autocomplete({
		source : function( request, response ) {
	        $.ajax({
	            url: "autoSearch",
	            dataType: "json",
	            data: {
	              searchValue: request.term
	            },
	            success: function( result ) {
	                response( 
	                	$.map( result, function( item ) {

	                			return {
	                  				label: item.data,
	                  				value: item.data
	                			}
	              		})
	              	);
	            }
	          });
	    },
		select: function( event, ui ) {},
        minLength: 1
	});
});
