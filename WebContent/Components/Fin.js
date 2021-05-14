$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

 
$(document).on("click", "#btnSave", function(event) 
{  
	  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	  
	var status = validateFinForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	  
	var t = ($("#hidFinIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "FinAPI",
		type : t,
		data : $("#formFin").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onFundSaveComplete(response.responseText, status);
			console.log(response);
		}
	});
}); 

function onFundSaveComplete(response, status){
	if(status == "success")
	{
		console.log(response +  " "+status);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
			console.log("dataaaaaa");
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	
	
	$("#hidFinIDSave").val("");
	$("#formFin")[0].reset();
}

 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidFinIDSave").val($(this).closest("tr").find('#hidFinIDUpdate').val());     
	$("#product_category").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#category_price").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#tax_rate").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#gross_price").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});



$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "FinAPI",
		type : "DELETE",
		data : "FinID=" + $(this).data("finid"),
		dataType : "text",
		complete : function(response, status)
		{
			onFundDeletedComplete(response.responseText, status);
		}
	});
});

function onFinDeletedComplete(response, status)
{
	if(status == "success")
	{
		console.log(response);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("------Successfully Deleted-------");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}


function validateFinForm() {  
	 
	if ($("#product_category").val().trim() == "")  {   
		return "Insert product category.";  
		
	} 
	
	 
	if ($("#category_price").val().trim() == "")  {   
		return "Insert category price.";  
	} 
	
	
	
	if ($("#tax_rate").val().trim() == "")  {   
		return "Insert tax rate."; 
		 
	}
	 
	   
	if  ($("#gross_price").val().trim() ==""); {   
		return "Insert gross price.";  
		
	}
	 
	
		

	 
	 return true; 
	 
}
