<!DOCTYPE html>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="java.util.ArrayList"%>

<%@ page errorPage="error.jsp" %>  

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Satsang America - Istavrity</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link
	href="http://vitalets.github.io/angular-xeditable/dist/css/xeditable.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script
	src="http://vitalets.github.io/angular-xeditable/dist/js/xeditable.js"></script>
<script src="https://code.angularjs.org/1.0.8/angular-mocks.js"></script>
<script src="js/lumino.glyphs.js"></script>
<link href="css/styles.css" rel="stylesheet">
<script src="js/jquery-2.1.4.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script src="js/jquery.tabletojson.js"></script>
<script src="js/bootstrap-table.js"></script>
</head>

<body>

	<%
		String applciationFlow = OnlineSAConstants.PORTAL_USER;
		String phoneNo="";
		RootMDB root = (RootMDB)session.getAttribute("userBean");
		ArrayList<RootMDB>   rootList  = new ArrayList<RootMDB>();
	
	    if (request.getParameter("applicationFlow") != null && request.getParameter("applicationFlow").equalsIgnoreCase("adminFlow") ) {
	    	if(request.getParameter("phoneNo") != null) { 
	    		phoneNo=request.getParameter("phoneNo");
	    		applciationFlow= request.getParameter("applicationFlow");
	    	System.out.println("in payisht applciationFlow :"+applciationFlow);
	    	 System.out.println("in payisht phoneNo: "+phoneNo);
	     	rootList = (ArrayList<RootMDB>)session.getAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER);
	    	  for (RootMDB rootMdb : rootList) {
	    	       if(rootMdb.getPhoneNo().equalsIgnoreCase(phoneNo))
	    	          	root=rootMdb; 
	    	           session.setAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER, root);
		     	 }
	    	}
	    }
	    else {
	    	//root = (RootMDB)session.getAttribute(OnlineSAConstants.PORTAL_USER);
	    	root = (RootMDB)session.getAttribute("userBean");
	    	System.out.println("else in payisht applciationFlow :"+applciationFlow);
	     	if(request.getParameter("phoneNo") != null)
	     		System.out.println(" request else in payisht phoneNo: "+phoneNo);
	     	else 
	     	   phoneNo=(String) root.getPhoneNo();
	     	  System.out.println(" root else in payisht phoneNo: "+phoneNo);
	    }
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Satsang America</span> Ishtavrity</a>
				<ul class="user-menu">
					<li class="dropdown pull-right"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <svg
								class="glyph stroked male-user">
                                <use xlink:href="#stroked-male-user"></use>
                            </svg> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"> <svg class="glyph stroked male-user">
                                        <use
											xlink:href="#stroked-male-user"></use>
                                    </svg> Profile
							</a></li>
							<li><a href="#"> <svg class="glyph stroked gear">
                                        <use xlink:href="#stroked-gear"></use>
                                    </svg> Settings
							</a></li>
							<li><a href="#"> <svg class="glyph stroked cancel">
                                        <use
											xlink:href="#stroked-cancel"></use>
                                    </svg> Logout
							</a></li>
						</ul></li>
				</ul>
			</div>

		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--sidebar-->
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<ul class="nav menu">
			<li><a href="index.jsp"> <svg
						class="glyph stroked dashboard-dial">
                        <use xlink:href="#stroked-dashboard-dial"></use>
                    </svg> Dashboard
			</a></li>
			<li class="active"><a href="payisht.jsp"> <svg
						class="glyph stroked app-window-with-content">
                        <use
							xlink:href="#stroked-app-window-with-content"></use>
                    </svg> Pay Ishtavrity
			</a></li>
			<li>
				<a href="searchIsht.jsp">
					<svg class="glyph stroked clipboard with paper"><use xlink:href="#stroked-clipboard-with-paper"/></svg>
					Ishtavrity Transcations</a>
			</li>
				
			<li role="presentation" class="divider"></li>
			<li><a href="login.jsp"> <svg
						class="glyph stroked male-user">
                        <use xlink:href="#stroked-male-user"></use>
                    </svg> Logout
			</a></li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">PAYISHT REVEIW FORM</li>
			</ol>
		</div>
		<!--/.row sub navigation-->

		<!--<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Ishtavrity Deposit Form</h1>
            </div>
        </div>-->
		<!--/.row sub header-->
		<% 	  System.out.println("1  phoneNo: "+root.getPhoneNo()); %>

		<div class="row">
					<div id="dvErrAlert" class="alert alert-danger" style= "display:none">
					  <a class="close" href="#">×</a>
					  <p><strong>{{PostDataResponse}}</strong> 
					</div>
			<div class="col-md-12" ng-app="ishtApp" ng-controller="ishtCtrl">
			<input type="hidden" id="phoneNo" value = <%= root.getPhoneNo()%> name="phoneNo" placeholder="your phone no" ><br>
			<input id="txtApplicationFlow" name="txtApplicationFlow" type="hidden" value =<%= applciationFlow %>>
			<br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">PAYISHT REVEIW FORM
						</div>
			   
					<div class="panel-body">
					<div><table class="table table-striped table-bordered" bgcolor = "solid #d8d8d8">
					<tbody>
						<tr>
						  <td colspan = "3"><strong>Member Name:</strong><br>
						  <%= root.getFirstName() +"  " +root.getLastName()%>
						  </td>
						  <td colspan = "1"><strong>Family Code:</strong><br>
						  <%= root.getFamilyID()%>
						  </td>
						</tr>
						<tr>
						  <td><strong>Address:</strong><br>
						  <%= root.getAddress().getAddressLine1() +" "+root.getAddress().getAddressLine2()+"  "+root.getAddress().getAddressLine3()+" "+ root.getAddress().getCity()+", "+root.getAddress().getState()%>
						  </td>
						  </td><td><strong>Contact:</strong><br><%= root.getPhoneNo()%></td>
						  <td><strong>EMail ID:</strong><br><%= root.getEmail()%><br></td>
						  <td><strong>India Family Code:</strong><br><%= root.getIndfamilyCode()%><br></td>
						</tr>						
					</tbody>
					</table>
							
					</div>
						<form name ="ishtPayForm" class="form-vertical">
							<fieldset>
								<div class="form-group ">
									<div class="row">
										<div class=" col-md-7">
											<table class="table table-striped table-bordered">
												<tbody>
													
											  	<tr>
														<td><strong>Payment Method:</strong></td>
														<td><select id="selPmtMethod" name="selPmtMethod"  style="text-transform:uppercase;"  placeholder="Payment Method" class="form-control" ng-model="selPmtMethod" required>
														<option ng-selected="selPmtMethod">E-TRANSFER</option>
														<option>CASH</option> 
														</select>
														</td>
													</tr>
												   <!-- 	
													<tr>
														<td><strong>Collection Date:</strong></td>
														<td><input id="dtIshtDate" name="dtIshtDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtIshtDate" required></td>
													</tr>
													 -->
													<tr>
														<td><strong>Bank Name</strong></td>
														<td><input id="stBankName" name="stBankName"
															placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" required></td>
													</tr>
													<tr>
														<td><strong>Transaction Ref / Cheque No</strong></td>
														<td><input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" required></td>
													</tr>
													
													<tr>
														<td><strong>E-Transaction Date / Cheque Date:</strong></td>
														<td><input id="dtChqDate" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" required></td>
													</tr>
													
												<!-- <td><strong>Cheque No</strong></td>
													<td><input id="stChqNo" name="stChqNo"
														placeholder="Cheque No" type="text" style="text-transform:uppercase;" class="form-control" ng-model = "stChqNo" required></td>
													</tr> -->	
													
												</tbody>
											</table>
										</div>
									</div>
							</fieldset>
						</form>
				<% 	  System.out.println("2  phoneNo: "+root.getPhoneNo()); %>	
				
					<!-- Sample Table -->
						<div class="form-group">
							<div class="col-md-12">
								<table id="sum_table"
									class="table table-striped table-bordered" style="margin-left: -15px">
									<thead>
										<tr>
											<th>Member Id</th>
											<th>Name </th>
											<th>Option</th>
											<th>Value</th>
											<th>Swastyayani</th>
											<th>Istavrity</th>
											<th>Acharyavrity</th>
											<th>Dakshina</th>
											<th>Sangathani</th>
											<th>Pronami</th>
											<th>S Surplus</th>
											<th>Parivrity</th>
											<th>Ritwiki</th>
											<th>Total</th>
											
										</tr> 
									</thead>
									<tbody>
										<tr ng-repeat="ishtL in ishtLine" id={{ishtL.id}} ng-value="{{$index}}">
											<td> <input name = "id" value = {{ishtL.id}} disabled style="width: 120px"/></td>
											<td> <input name = "name" value = {{ishtL.name}} disabled style="width: 120px"/></td>
											
											<td>
												<select  id="op{{$index+10}}"  style="width:45px" >
												 	<option value="select">select</option>
												    <option value="+">+</option>
												    <option value="-">-</option>
												    <option value="*">*</option>
												    <option value="/">/</option>
												</select>
												
											</td>
											
											<td><input name="textValue" id="sl{{$index+10}}" onblur="CurrencyFormatted(this.value,this.id)"  value="12" style="width:45px" class="sum1" /></td>
											
											
											<td><input name="swastyayani" id="S{{$index+1}}" onblur="CurrencyFormatted(this.value,this.id)"  value="{{ishtL.swastyayani | number:2}}"style="width:45px" class="sum1" /></td>
											<td><input name = "istavrity" id="I{{$index+2}}" onblur="CurrencyFormatted(this.value,this.id)"  value= "{{ishtL.istavrity | number:2}}" style="width:45px" class="sum2" /></td>
											<td><input name = "acharyavrity" id="A{{$index+3}}" onblur="CurrencyFormatted(this.value,this.id)" value= "{{ishtL.acharyavrity | number:2}}" style="width:45px" class="sum3" /></td>
											<td><input name = "dakshina" id="D{{$index+4}}" onblur="CurrencyFormatted(this.value,this.id)" value=" {{ishtL.dakshina | number:2}}" style="width:45px" class="sum4" /></td>
											<td><input name = "sangathani" id="Sa{{$index+5}}" onblur="CurrencyFormatted(this.value,this.id)" value=" {{ishtL.sangathani | number:2}}" style="width:45px" class="sum5" /></td>
											<td><input name = "pronami" id="P{{$index+6}}" onblur="CurrencyFormatted(this.value,this.id)"  value=" {{ishtL.pronami | number:2}}" style="width:45px" class="sum6" /></td>
											<td><input name = "surplus" id="Su{{$index+7}}" onblur="CurrencyFormatted(this.value,this.id)" value="{{ishtL.surplus | number:2}}" style="width:45px" class="sum7" /></td>
											<td><input name = "parivrity" id="Pa{{$index+8}}" onblur="CurrencyFormatted(this.value,this.id)" value="{{ishtL.parivrity | number:2}}" style="width:45px" class="sum8" /></td>
											<td><input name = "ritwiki" id="Ri{{$index+9}}" onblur="CurrencyFormatted(this.value,this.id)" value="{{ishtL.ritwiki| number:2 }}" style="width:45px" class="sum9" /></td>
											<td class="total"> {{ishtL.total | number:2 }}</td>
											</tr>
									</tbody>
								</table>
							
						<div class="form-group">
							<div class="col-md-14">
								<table id="sum_table"
									class="table table-borderless">
									<thead>
										<tr>
										<td colspan = 13 align = "right">Grand Total : US $  <input type="text" id="GTotal" style="width:50px" disabled/></td>
										</tr>
									</thead>
								</table>
							</div>
						</div>				
						 </div>
						 <div class="form-group col-md-12">
                               <!-- <input class="btn btn-primary" id = "submit_1" type="submit" ng-click="ishtPayForm.$valid && ishtPay($event)" value="Continue" ng-disabled="ishtPayForm.$invalid" />  -->
                               
                               <a href="payisht.jsp" class="btn btn-info btn-md" >
        						  <span class="glyphicon glyphicon-pencil"></span>  
     						   </a>
                               &nbsp;&nbsp;
                               
                               
                               <a href="#" class="btn btn-info btn-md" >
        						  <span >Continue</span>  
     						   </a>
                         </div>
                             
						<div class="panel-footer">
							<small class="text-muted"  class="btn btn-primary">Satsang America,Inc</small>
						</div>
					</div>  

				</div>
				<!--/.col - Left-->
			</div>
			<!--/.row content msg-->
		</div>
		<!--/.main--></div>
	</div> <!--  ISTARGHYA DEPOSIT FORM end  -->
	
		<% 	  System.out.println("3  phoneNo: "+root.getPhoneNo()); %>
<script>

	
		
		function myFunction(val) {
		    var $row = $("abc").closest("tr");    // Find the row
		    var $text = $row.find(".nr").text(); // Find the text
		    // Let's test it out
		    //alert($text);
		}

		(function() {
    	  var courseApp = angular.module("ishtApp", ["xeditable"]);
    	  courseApp.controller('ishtCtrl', ['$scope', '$http', '$filter', function($scope, $http, $filter) {
    		 // $scope.method = [{model:"E-TRANSFER",id:"1"}, {model :"CHEQUE",id:"2"}];
    		 // $scope.selPmtMethod = "1";
    		  //,
    	   
    		  
    		  
    		  
    		  $scope.ishtPay = function() {
    	     var data=[];
		        $("#sum_table").find("tr").each(function(){
		            //var id=$(this).attr("id");
		            var id="id";
		            var row={};
		            $(this).find("input,select,textarea").each(function(){
		                row[$(this).attr("name")]=$(this).val();
		            });
		            data.push(row);
		        });
	        	 var headerDetails ={
	        			dtIshtDate:$scope.dtChqDate,
	        			stBankName:$scope.stBankName,
	   	 			  	stTrnNo:$scope.stTrnNo,
	   	 			  	stChqNo:$scope.stChqNo,
	   	 			  	phoneNo:$('#phoneNo').val(),
	   	 			    pmtMethod:$scope.selPmtMethod,
	   	 		        chqDate:$scope.dtChqDate,
	  				};
	   	 		
	   	 		var applicationFlow = $('#txtApplicationFlow').val();
	   	 		 // alert('Hi shyam 1'+headerDetails);
	   	 		//alert(applicationFlow);
	   	 	 	var contextPath = "saveIshtJSONData.do"+"?ishtLineData="+ JSON.stringify(data)+"&ishtHeaderData="+JSON.stringify(headerDetails)+"&applicationFlow="+applicationFlow;
	   	 		$http({
					 method : "POST",
					 url : contextPath,
					 dataType: 'json',
					 headers : {
		                    'Content-Type' : 'application/json'
		                }
				 }).then(function mySucces(data) {
					var returnObject = eval(data); // Parse Return Data
					//alert(returnObject.data.returnCode);
					if(returnObject.data.returnCode=='error') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
						 }else{
						 
						 if(returnObject.data.applicationFlow=='adminFlow') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
							 $("#dvErrAlert").show();
							 $("#submit_1").prop("disabled",true);
						 }else						 
							 window.location = 'ishtpayconfirm.jsp';
							 //window.location = 'payisht_reveiw_confirm.jsp';
							}
				 },function myError(d) {
					 alert("save failed");
				 });
	    	  };
    	  	
	    	  
	    	
	  
	    	  
	    	/*   $(document).ready(function(){
					$("select").change(function(){
						alert("===============================");
						
					});	    		
	    	  }); */
	    	  
	    	 
	    	  
	    	  $scope.opKeyUp=function(){
	    		  alert("ssssssssssssssssssssssssssssssss");
	    	  }
	    	  
	    	  $(document).ready(function() {
	    		  $("op10").change("change",function(){
	    			  alert("=========================");
	    			   $(this).closest('td').next('td').find('input:text').show();
	    		  });
	    		 
	   		   });
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
	    	  
    	    $(document).ready(function() {
    	    	  //alert('Hi Shyam 2');
	  			 	var istPhone =  $('#phoneNo').val();
	  			    var applicationFlow=$('#txtApplicationFlow').val();
	  	 		    var contextPath = "getIshtJSONObject.do"+"?phoneNo="+ istPhone+"&applicationFlow="+applicationFlow;
		  		   //var contextPath = "getIshtJSONObject.do"+"?phoneNo="+ istPhone;
		  			$http({
					 method : "POST",
					 url : contextPath
				 }).then(function mySucces(data) {
					var returnObject = eval(data); // Parse Return Data
					if(returnObject.data.returnCode=='error') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
						 }else{
							 $scope.ishtLine = returnObject.data.userJSONObject.line;
							 
							 //$table.bootstrapTable('load', returnObject.data.userJSONObject.line);
							 $table.bootstrapTable('hideLoading');
							 $table.tableEditor();
						 }
				 },function myError(d) {
					 alert("failed to get Inside");
				 });
    	    });
	  			
    	  $(document).on('keyup change', '#sum_table tr:not(.totalCol) input:text', function() {
  		     var $table = $(this).closest('table');
  		     var total = 0;
  		     var thisNumber = $(this).attr('class').match(/(\d+)/)[1];
  		     //alert('RowVal :'+$(this).attr('class').match(/(\d+)/)[1]);
  		     $table.find('tr:not(.totalCol) .sum' + thisNumber).each(function() {
  		    		total += parseFloat(this.value).toFixed(2);
  		     });
  		   
  		      total=formatCurrency(total); //added b shyam
  		     $table.find('.totalCol td:nth-child(' + thisNumber + ')').html(total);
  		     
  		   });
    	  
    	  
    	  

 		 $(document).on('keyup', 'input', newSum);
  		   function newSum() {
  		   //alert('newSum')
  		     var sum = 0;
  		     var thisRow = $(this).closest('tr');
  		     var total = 0;
  		     var GrTotal = 0;
			 
  		     $(thisRow).find("td:not(.total) input").each(function() {
  		     	if(!isNaN(this.value)){
  		     		sum += parseFloat(parseFloat(Math.round(this.value * 100) / 100).toFixed(2));
  		     	}
  		     });
  		   
  		      sum=formatCurrency(sum); //added b shyam
  		      
  		     $(thisRow).find(".total").html(sum);
  		     
  		     $('.total').each(function() {
  		       GrTotal += parseFloat($(this).html());
  		        //alert('GrTotal :'+GrTotal);
  		       //document.getElementById("GTotal").innerHTML = GrTotal;
  		       $("#GTotal").val(GrTotal.toFixed(2));
  		       
  		     });
  		   }

  $scope.loadUser = function() {
 		 var istPhone =  $('#phoneNo').val();
 		 var applicationFlow=$('#txtApplicationFlow').val();
 		 var contextPath = "getIshtJSONObject.do"+"?phoneNo="+ istPhone+"&applicationFlow="+applicationFlow;
 		 	$http({
				 method : "POST",
					 url : contextPath
					 }).then(function mySucces(data) {
						var returnObject = eval(data); // Parse Return Data
						alert(returnObject.data.returnCode);
						if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 $scope.ishtLine = returnObject.data.userJSONObject.line;
							 }
					 },function myError(d) {
						 alert("failed to load");
					 });
	  			};
    	  }
    	  ]);
    	  
    	})();
    	
function CurrencyFormatted(amount,val) {
	//alert('VAL:'+val);
	var i = parseFloat(amount);
	if(isNaN(i)) { i = 0.00; }
	var minus = '';
	if(i < 0) { minus = '-'; }
	i = Math.abs(i);
	i = parseInt((i + .005) * 100);
	i = i / 100;
	s = new String(i);
	if(s.indexOf('.') < 0) { s += '.00'; }
	if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
	s = minus + s;
	 //document.getElementById("One").value=s;
	document.getElementById(val).value=s;
     $scope.value=s;
	return s;
}

function formatCurrency(amount) {
	var i = parseFloat(amount);
	if(isNaN(i)) { i = 0.00; }
	var minus = '';
	if(i < 0) { minus = '-'; }
	i = Math.abs(i);
	i = parseInt((i + .005) * 100);
	i = i / 100;
	s = new String(i);
	if(s.indexOf('.') < 0) { s += '.00'; }
	if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
	s = minus + s;
	return s;
}


$(document).ready(function(){
	//$("dtIshtDate").focus(function(){
	$("dtChqDate").focus(function(){
		//alert('Hi');
	var dtToday = new Date();
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var maxDate = year + '-' + month + '-' + day;
    document.getElementById("dtChqDate").setAttribute("max", maxDate);
    //$('#txtDate').attr('max', maxDate);
})

});
</script>

<%  System.out.println("4  phoneNo: "+root.getPhoneNo()); %>

</body>

</html>
