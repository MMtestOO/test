var real_data = /*[[${names}]]*/'noValue';
    $(document).ready(function() {
        $("#movieTitle").autocomplete({
			minLength : 1,
            source : real_data,
            select : function(e, ui){
	
				return ui.item.value;
			}	
        })
    });
    
    			