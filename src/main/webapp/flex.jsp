<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<title>Satsang America</title>
 <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
  
<script src ="js/jquery-2.1.4.js"></script>
<SCRIPT>
 $(document).ready(function() {                        
                $('#submit').click(function(event) {
                var orderSearchType ;
                var orderSearchParam ; 
                var select = $('#orderdataTable');
                 $.ajax({
			        type: "POST",
			        url: "getFlexSchedule.do",
			        async: true,
			        datatype:'json',
			        data: {
						invoiceAmount: $('#invoiceAmount').val(),
						userOptions: $('#userOptions').val(),
						serviceDurationMonths: $('#serviceDurationMonths').val()
			    	},
			        error: function(xmlhttprequest, msg){
			    		alert(msg);
			        },
			        success: function(data){
			           //alert('scuccess');
			           var returnObject = eval(data);
			           $("#orderdataTable").html("");
			           if (returnObject["returnCode"] == "SUCCESS" || returnObject["returnCode"] == "success") {
				            select.append($('<tr><td>Sr No</td><td>Start Date</td><td>End Date</td><td>Amount</td></tr>'));
				            for (var i = 0; i < returnObject.flexSchedule.length; i++) { 
 								   select.append($('<tr><td><input type = "text" value = '+returnObject.flexSchedule[i].srNO+'></td><td>'+returnObject.flexSchedule[i].startDate+'</td>'+
 								   '+<td>'+returnObject.flexSchedule[i].endDate+'</td><td>'+returnObject.flexSchedule[i].amount+'</td></tr>'));
							}
						} 
            		}
          		   });
    	        });
    	        
    	        $('#read').click(function(event) {
    	        
    	        	$('#orderdataTable tr').each(function() {
					    var stdate = $(this).find("tr").eq(1).html();
					    var enDate = $(this).find("tr").eq(2).html();  
					    alert(stdate +"-"+enDate);  
					});
    	        });
            });   
</SCRIPT>
<STYLE>
table, th , td {
  border: 0px solid grey;
  border-collapse: collapse;
  padding: 5px;
  font-size : 90%;
  font-family : "Myriad Web",Verdana,Helvetica,Arial,sans-serif;
}
table tr:nth-child(odd) {
  background-color: #f1f1f1;
}
table tr:nth-child(even) {
  background-color: #ffffff;
}
</STYLE>
</HEAD>

<BODY>
<s:form>
  <table width1 = "100%"  align = "center" >
  <tbody>
  <tr>
		<td style="text-align:center; font-family: arial; font-size: 18; color: #2E9AFE" align = "middle" colspan = "5">
			 <b>Flexible Invoice Schedule</b> 
		</td>
	</tr>
	
	<tr>
		<td align = "left">Invoice Bill Amount	</td>
		<td align = "left">
			 <s:textfield id ="invoiceAmount" name = "invoiceAmount" theme="simple" tabindex="1"/>
		</td>
		
		<td align = "left">EMI</td>
		<td align = "left">
			 <s:textfield id ="orderSearchParam" name = "orderSearchParam" theme="simple" />
		</td>
		
	</tr>	 
	
	<tr>
		<td align = "left">Total Service Duration</td>
		
		<td align = "middle">
			 <s:textfield id ="serviceDurationMonths" name = "serviceDurationMonths" theme="simple" tabindex="2"/>
		</td>
		
		<td align = "left">Amount per billing cycle	</td>
		<td align = "left">
			 <s:textfield id ="orderSearchParam" name = "orderSearchParam" theme="simple" />
		</td>
	</tr>	 
	
	<tr>
		<td align = "left">Installment Option 	</td>
		
		<td align = "middle">
			 <s:textfield id ="userOptions" name = "userOptions" theme="simple" tabindex="3" />
		</td>
		
		<td align = "left">Billing cycle(Monthly) 	</td>
		<td align = "left">
			 <s:textfield id ="orderSearchParam" name = "orderSearchParam" theme="simple" />
		</td>
	</tr>	 
			
		
	<tr>
		<td align = "middle" colspan = "5">
			<s:submit type ="button" id="clear" label = "Clear" theme="simple"/>
			<input type ="button" id="submit" value = "Submit">
			<input type ="button" id="read" value = "Read Table">
		</td>
	</tr>
	</tbody>
</table>


</s:form>

<div id="blank"><br></div>

<div id="orderDatadIV">
<table id="orderdataTable" border = "1" align = "center">
<tbody id= "party"></tbody>
</table></div>
</BODY>
</HTML>
