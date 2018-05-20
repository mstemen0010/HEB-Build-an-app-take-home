$(document).ready(function() {
	
	ajaxGet();
	
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : window.location + "api/items/all",
			success: function(result){
				$.each(result, function(i, item){
					
					var itemRow = '<tr>' +
										'<td>' + item.sku + '</td>' +
										'<td>' + item.description + '</td>' +
										'<td>' + item.lastSold + '</td>' +
										'<td>' + item.shelfLife + 'd' + '</td>' +
										'<td>' + item.price + '</td>' +
										'<td>' + item.unit + '</td>' +
										'<td>' + item.xFor + '</td>' +									
										'<td>' + item.cost + '</td>' +
									  '</tr>';
					
					$('#itemTable tbody').append(itemRow);
					
		        });
				
				$( "#itemTable tbody tr:odd" ).addClass("info");
				$( "#itemTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});	
	}
})