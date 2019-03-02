<!DOCTYPE html>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.olsa.pojo.IshtMDB" %>

<%@ page errorPage="error.jsp" %>  

<html>
<head>
<noscript>
    <div style="position: fixed; top: 0px; left: 0px; z-index: 3000; 
                height: 100%; width: 100%; background-color: #FFFFFF">
       <p style="margin-left: 10px; text-align: center;color: red; font-size: x-large;">
        JavaScript is required to browse in. Your browser either
        does not support JavaScript or it is being blocked.<br>
		Enable JavaScript in your browser or use one which supports it.</p>
    </div>
</noscript>
<script src="https://js.braintreegateway.com/web/3.42.0/js/client.min.js"></script>
<script src="https://js.braintreegateway.com/web/3.42.0/js/us-bank-account.min.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Satsang America - Istavrity</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="js/jquery.tabletojson.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />


    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>


<link
	href="http://vitalets.github.io/angular-xeditable/dist/css/xeditable.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="http://vitalets.github.io/angular-xeditable/dist/js/xeditable.js"></script>
<script src="https://code.angularjs.org/1.0.8/angular-mocks.js"></script>
<script src="js/lumino.glyphs.js"></script>
<link href="css/styles.css" rel="stylesheet">
<link href="css/apps.css" rel="stylesheet">

<script src="js/operation.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.2/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<%


response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
 
    if(session.getAttribute("userBean")==null){
   		response.sendRedirect(request.getContextPath() + "/");
	}
	

%>
<body ng-app="ishtApp" ng-controller="ishtCtrl">

	<%
		String applciationFlow = OnlineSAConstants.PORTAL_USER;
		String phoneNo="";
		RootMDB root = (RootMDB)session.getAttribute("userBean");
		ArrayList<RootMDB>   rootList  = new ArrayList<RootMDB>();
		IshtMDB ishtPay=new IshtMDB();
		ishtPay.setName( root.getFirstName() +"  " +root.getLastName());
		ishtPay.setFamilyID(root.getFamilyID());
		ishtPay.setPhoneNo(root.getPhoneNo());
	//	ishtPay.setReceiptNo(root.getr)
		session.setAttribute("ishtPay",ishtPay);
		
		
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
                            </svg><%= root.getUserName()%> <span class="caret"></span></a>
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
	<jsp:include page="slidemenu.jsp" />  
	<!-- <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
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

	</div> -->
	<!--/.sidebar-->

<!-- depositForm start -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main depositForm">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">ISTARGHYA DEPOSIT FORM</li>
			</ol>
		</div>
		<div class="row">
			<div id="dvErrAlert" class="alert alert-danger" style= "display:none">
				<a class="close" href="#">�</a>
				<p><strong>{{PostDataResponse}}</strong> 
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value = <%= root.getPhoneNo()%> name="phoneNo" placeholder="your phone no" ><br>
				<input id="txtApplicationFlow" name="txtApplicationFlow" type="hidden" value =<%= applciationFlow %>>
				<br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">ISTARGHYA DEPOSIT FORM</div>
					<div class="panel-body">
						<div>
							<table class="table table-striped table-bordered" bgcolor = "solid #d8d8d8">
								<tbody>
									<tr>
										<td colspan = "3"><strong>Member Name:</strong><br>
						 					 <%= root.getFirstName() +"  " +root.getLastName()%>
						 				 </td>
						 				 <td colspan = "1"><strong>Family Code:</strong><br>
						 					 <%= root.getFamilyID()%>
						 					 <input type="hidden" value="<%= root.getFamilyID()%>" id="familyCode">
						  				 </td>
									</tr>
									<tr>
										<td><strong>Address:</strong><br>
							  				<%= root.getAddress().getAddressLine1() +" "+root.getAddress().getAddressLine2()+"  "+root.getAddress().getAddressLine3()+" "+ root.getAddress().getCity()+", "+root.getAddress().getState()%>
							 			 </td>
							  			<td>
							  				<strong>Contact:</strong><br><%= root.getPhoneNo()%>
							  				<input type="hidden" value="<%= root.getPhoneNo()%>" id="contact">	
							  			</td>
							  				
							  			<td>
							  				<strong>EMail ID:</strong><br><%= root.getEmail()%><br>
							  			</td>
							  			<td>
							  				<strong>India Family Code:</strong><br><%= root.getIndfamilyCode()%><br>
							  			</td>
									</tr>	
														
								</tbody>
							</table>
						</div>
						<form name ="ishtPayForm" id="ishtPayForm" class="form-vertical">
							<fieldset>
								<div class="form-group">
									<div class="row">
										<div class=" col-md-7">
											<table class="table table-striped table-bordered">
												<tbody>
													<tr>
														<td>
															<strong>Payment Method:</strong>
														</td>
														<td>
															<select id="selPmtMethod" name="selPmtMethod"  style="text-transform:uppercase;"  placeholder="Payment Method" class="form-control" ng-model="selPmtMethod" ng-change="changeMe()" required>
																<option ng-selected="true" value="AUTO" >AUTO</option>
																<option value="MANUAL" >MANUAL</option> 
															</select>
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td>
															<strong>Bank Name</strong></td>
														<td>
															<input id="stBankName" name="stBankName" placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" ng-required="selPmtMethod == 'MANUAL'">
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td>
															<strong>Transaction Ref / Cheque No</strong>
														</td>
														<td>
															<input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" ng-required="selPmtMethod == 'MANUAL'">
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td><strong>E-Transaction Date / Cheque Date:</strong></td>
														<td><input id="dtChqDate" onchange="checkDate()" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" ng-required="selPmtMethod == 'MANUAL'"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</fieldset>
						</form>
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
									<tbody id="mytbody">
										<tr ng-repeat="ishtL in ishtLine" id={{ishtL.id}} ng-value="{{$index}}">
											<td> <input name = "id" value = {{ishtL.id}} disabled style="width:120px" id="id{{$index+11}}"/></td>
											<td> <input name = "name" value = {{ishtL.name}} disabled style="width:120px" id="name{{$index+12}}"/></td>
											
											<td>
												<select style="width:45px" class="mySelectOp" name="action" id="my_select,{{$index}}" onchange="selectedOperatoins(this.id)">	
													<option value="+" style="font-size:24px" class="fa">&#xf067;</option>
													<option value="-" style="font-size:24px" class="fa">&#xf068;</option>
													<option value="*" style="font-size:24px" class="fa">&#xf069;</option>
													<option value="/" style="font-size:24px" class="fa">&#xf033;</option>
												</select>
											</td>
											
											<!-- <td><input name="textValue" id="value{{$index}}" onblur="CurrencyFormatted(this.value,this.id)"  value="0.00" style="width:45px" onkeypress="getIdVal();" class="selecttextValue" /></td> -->
											
											<td><input name="textValue" id="value{{$index}}" onblur="CurrencyFormatted(this.value,this.id)"  value="0.00" style="width:45px"  class="selecttextValue" /></td>	
											<td><input name="swastyayani" id="S{{$index+1}}" onblur="CurrencyFormatted(this.value,this.id)"  value="{{ishtL.swastyayani | number:2}}"style="width:45px" class="sum1 triggerSource" /></td>
											<td><input name = "istavrity" id="I{{$index+2}}" onblur="CurrencyFormatted(this.value,this.id)"  value= "{{ishtL.istavrity | number:2}}" style="width:45px" class="sum2" /></td>
											<td><input name = "acharyavrity" id="A{{$index+3}}" onblur="CurrencyFormatted(this.value,this.id)" value= "{{ishtL.acharyavrity | number:2}}" style="width:45px" class="sum3" /></td>
											<td><input name = "dakshina" id="D{{$index+4}}" onblur="CurrencyFormatted(this.value,this.id)" value=" {{ishtL.dakshina | number:2}}" style="width:45px" class="sum4" /></td>
											<td><input name = "sangathani" id="Sa{{$index+5}}" onblur="CurrencyFormatted(this.value,this.id)" value=" {{ishtL.sangathani | number:2}}" style="width:45px" class="sum5" /></td>
											<td><input name = "pronami" id="P{{$index+6}}" onblur="CurrencyFormatted(this.value,this.id)"  value=" {{ishtL.pronami | number:2}}" style="width:45px" class="sum6" /></td>
											<td><input name = "surplus" id="Su{{$index+7}}" onblur="CurrencyFormatted(this.value,this.id)" value="{{ishtL.surplus | number:2}}" style="width:45px" class="sum7" /></td>
											<td><input name = "parivrity" id="Pa{{$index+8}}" onblur="CurrencyFormatted(this.value,this.id)" value="{{ishtL.parivrity | number:2}}" style="width:45px" class="sum8" /></td>
											<td><input name = "ritwiki" id="Ri{{$index+9}}" onblur="CurrencyFormatted(this.value,this.id)" value="{{ishtL.ritwiki| number:2 }}" style="width:45px" class="sum9" /></td>
											<td class="total" id="tot{{$index+10}}"> {{ishtL.total | number:2 }}</td>
											</tr>
									</tbody>
								</table>
								<div class="form-group">
									<div class="col-md-14">
										<table id="grand_sum_table"
											class="grand_sum_table table table-borderless">
											<thead>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Ishtabhrity Amount</td>
													<td  align = "right" style="border-top: none;">{{ishtAmount | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Processing Fee</td>
													<td  align = "right" style="border-top: none;">{{processIng | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
														<td  align = "right" style="">Grand Total : US $ </td>
														<td  align = "right" style=""> <label id="GTotal" value="grandTotal">{{grandTotal | number : 2}}</label></td>
															
													</tr>
													
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td  align = "right" style="border-top: none;">Grand Total : US $ </td>
														<td  align = "right" style="border-top: none;"> <label id="GTotal" value="grandTotal">{{grandTotal | number : 2}}</label></td>
															
													</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
							<div class="form-group col-md-12">
                                 <input class="btn btn-primary" id = "submit_1" rel="modal:open" type="submit" 
                                 value="Continue"   
                                 
                                 onclick="reviewForm()"
                                 />  
         						 
       							<!--  <p><a href="#ex1" rel="modal:open"  class="btn btn-primary" id = "submit_1" type="submit" ng-click="ishtPayForm.$valid && ishtPay($event)" 
                                ng-disabled="ishtPayForm.$invalid" onclick="reviewForm()">Continue</a></p>
                         	 -->
                         	
                         	</div>
							<div class="panel-footer">
								<small class="text-muted"  class="btn btn-primary">Satsang America,Inc  </small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- depositForm end -->
	
	<!-- payReviewForm Step 2start -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main payReviewForm">
	<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">ISTARGHYA PREVIEW FORM</li>
			</ol>
		</div>
		<div class="row">
			<div id="dvErrAlert" class="alert alert-danger" style= "display:none">
				<a class="close" href="#">�</a>
				<p><strong>{{PostDataResponse}}</strong> 
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value = <%= root.getPhoneNo()%> name="phoneNo" placeholder="your phone no" ><br>
				<input id="txtApplicationFlow" name="txtApplicationFlow" type="hidden" value =<%= applciationFlow %>>
				<br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">ISTARGHYA PREVIEW FORM</div>
						<p id="amountError" style="color: red; margin-top: 5px; font-size: medium;" align="center"></p>
					<div class="panel-body">
						<div>
							<table class="table table-striped table-bordered" bgcolor = "solid #d8d8d8">
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
							  			<td>
							  				<strong>Contact:</strong><br><%= root.getPhoneNo()%></td>
							  			<td>
							  				<strong>EMail ID:</strong><br><%= root.getEmail()%><br>
							  			</td>
							  			<td>
							  				<strong>India Family Code:</strong><br><%= root.getIndfamilyCode()%><br>
							  			</td>
									</tr>	
									<tr>
										<td colspan = "4"><strong>Payment Method:</strong><br>
						 					 				{{selPmtMethod}}
						 				 </td>
						 				
									</tr>	
									<tr ng-show="selPmtMethod == 'MANUAL'">
														<td >
															<strong>Bank Name</strong><br>
														
															<!-- <input id="stBankName" name="stBankName" placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" disabled required> -->
															 <span  style="text-transform:uppercase;"> {{stBankName}} </span>
														</td>
												
														<td>
															<strong>Transaction Ref / Cheque No</strong><br>
														
															<!-- <input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" required disabled>
															 -->
															 <span  style="text-transform:uppercase;">
															 	{{stTrnNo}}
															 </span>
														</td>
													
														<td colspan="2"><strong>E-Transaction Date / Cheque Date:</strong><br><!-- <input id="dtChqDate" onchange="checkDate()" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" disabled required>
															 --> {{dtChqDate| date:'d-M-yyyy'}}
														</td>
													
														
													</tr>				
								</tbody>
							</table>
						</div>
						<!-- <form name ="ishtPayForm1" class="form-vertical">
							<fieldset>
								<div class="form-group ">
									<div class="row">
										<div class="col-md-5">
											<table class="table table-striped table-bordered">
												<tbody>
													<tr>
														<td>
															<strong>Payment Method:</strong>
														</td>
														<td>
															 <select id="selPmtMethod" name="selPmtMethod"  style="text-transform:uppercase;"  placeholder="Payment Method" class="form-control" ng-model="selPmtMethod" disabled required>
																<option ng-selected="selPmtMethod == 'AUTO'">E-TRANSFER</option>
																<option ng-selected="selPmtMethod == 'MANUAL'">CASH</option> 
															</select>
															
															{{selPmtMethod}}
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td>
															<strong>Bank Name</strong></td>
														<td>
															<input id="stBankName" name="stBankName" placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" disabled required>
															{{stBankName}}
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td>
															<strong>Transaction Ref / Cheque No</strong>
														</td>
														<td>
															<input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" required disabled>
															{{stTrnNo}}
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td><strong>E-Transaction Date / Cheque Date:</strong></td>
														<td><input id="dtChqDate" onchange="checkDate()" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" disabled required>
															 {{dtChqDate| date:'medium'}}
														</td>
													
														
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</fieldset>
						</form> -->
						<div class="form-group">
							<div class="col-md-12">
								<table class="table table-striped table-bordered" 
									id="sum_tablePre" style="margin-left: -15px">
									<thead>
										<tr >
											<th scope="col">Member Id</th>
											<th scope="col">Name </th>
											<th scope="col">Option</th>
											<th scope="col">Value</th>
											<th scope="col">Swastyayani</th>
											<th scope="col">Istavrity</th>
											<th scope="col">Acharyavrity</th>
											<th scope="col">Dakshina</th>
											<th scope="col">Sangathani</th>
											<th scope="col">Pronami</th>
											<th scope="col">S Surplus</th>
											<th scope="col">Parivrity</th>
											<th scope="col">Ritwiki</th>
											<th scope="col">Total</th>
										</tr> 
									</thead>
									<tbody id="tbodyPre">
									</tbody>
								</table>
								<div class="form-group">
										<table style="border: none;"
											class="grand_sum_table table table-borderless">
											<thead >
													<thead>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Ishtabhrity Amount: US $</td>
													<td  align = "right" style="border-top: none;">{{ishtAmount | number : 2 }}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Processing Fee: US $ </td>
													<td  align = "right" style="border-top: none;">{{processIng | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
													
													<td  align = "right" style="">Grand Total : US $ </td>
													<td  align = "right" style="">{{grandTotal | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
													
													<td  align = "right" style="border-top: none;">Grand Total : US $ </td>
													<td  align = "right" style="border-top: none;">{{grandTotal | number : 2}}</td>
													</tr>									
											
											</thead>
										</table>
								</div>
							</div>
							<div class="panel-footer">
									<a href="#" onclick="editDepositForm()"><span class="glyphicon glyphicon-pencil btn btn-info btn-md btn-edit"></span></a>
									<button class="btn btn-info btn-parmentForm"  ng-click="suibMitPayment()"> 
									<span ng-if="selPmtMethod == 'AUTO'">Continue</span> 
        							<span ng-if="selPmtMethod == 'MANUAL'">Submit</span>  
        							</button>
									<br><small class="text-muted"  class="btn btn-primary">Satsang America,Inc  </small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Auto payReviewForm START -->
	
	
	<!-- MANUAL CONFIRMAATION START -->
	
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main payMANReviewForm">
	<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">ISTARGHYA PREVIEW</li>
			</ol>
		</div>
		<div class="row">
			<div id="dvErrAlert" class="alert alert-danger" style= "display:none">
				<a class="close" href="#">�</a>
				<p><strong>{{PostDataResponse}}</strong> 
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value = <%= root.getPhoneNo()%> name="phoneNo" placeholder="your phone no" ><br>
				<input id="txtApplicationFlow" name="txtApplicationFlow" type="hidden" value =<%= applciationFlow %>>
				<br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">ISTARGHYA PREVIEW FORM</div>
						<p id="amountError" style="color: red; margin-top: 5px; font-size: medium;" align="center"></p>
					<div class="panel-body">
						<div>
							<table class="table table-striped table-bordered" bgcolor = "solid #d8d8d8">
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
							  			<td>
							  				<strong>Contact:</strong><br><%= root.getPhoneNo()%></td>
							  			<td>
							  				<strong>EMail ID:</strong><br><%= root.getEmail()%><br>
							  			</td>
							  			<td>
							  				<strong>India Family Code:</strong><br><%= root.getIndfamilyCode()%><br>
							  			</td>
									</tr>						
								</tbody>
							</table>
						</div>
						<form name ="ishtPayForm1" class="form-vertical">
							<fieldset>
								<div class="form-group ">
									<div class="row">
										<div class="col-md-5">
											<table class="table table-striped table-bordered">
												<tbody>
													<tr>
														<td>
															<strong>Payment Method:</strong>
														</td>
														<td>
															<!--  <select id="selPmtMethod" name="selPmtMethod"  style="text-transform:uppercase;"  placeholder="Payment Method" class="form-control" ng-model="selPmtMethod" disabled required>
																<option ng-selected="selPmtMethod == 'AUTO'">E-TRANSFER</option>
																<option ng-selected="selPmtMethod == 'MANUAL'">CASH</option> 
															</select> -->
															
															{{selPmtMethod}}
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td>
															<strong>Bank Name</strong></td>
														<td>
															<!-- <input id="stBankName" name="stBankName" placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" disabled required> -->
															{{stBankName}}
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td>
															<strong>Transaction Ref / Cheque No</strong>
														</td>
														<td>
															<!-- <input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" required disabled>
															 -->{{stTrnNo}}
														</td>
													</tr>
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td><strong>E-Transaction Date / Cheque Date:</strong></td>
														<td><!-- <input id="dtChqDate" onchange="checkDate()" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" disabled required>
															 --> {{dtChqDate| date:'medium'}}
														</td>
													
														
													</tr>
													<tr>
													<td ><strong>Grand Total : US $  </strong></td>
													<td><span style="width:50px">
													  {{grandTotal | number : 2}}</span></td>
													</tr>

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</fieldset>
						</form>
					
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- MANUAL CONFIRMATION END -->

	<!-- paymentForm Card  Start -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main paymentForm">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">ISTARGHYA PAYMENT FORM</li>
			</ol>
		</div>
		<div class="row">
			<div id="dvErrAlert" class="alert alert-danger" style="display: none">
				<a class="close" href="#">�</a>
				<p>
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value=<%= root.getPhoneNo()%>
					name="phoneNo" placeholder="your phone no"><br> <input
					id="txtApplicationFlow" name="txtApplicationFlow" type="hidden"
					value=<%= applciationFlow %>> <br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">
					<i class="fa fa-arrow-circle-left cursror-pointer back-wrap" onclick="goBackToCardDetails()"   title="Back"></i> 
					ISTARGHYA PAYMENT METHOD
					<!-- 	<button class="backButtonPayment" class="btn" onclick="goBackToCardDetails()" title="Back"><i class="fa fa-arrow-circle-left"></i> </button> -->
					
						<a  class="pull-right" href="#" title="Refresh">
             				<i style="font-size:20x;" class="fa fa-refresh" onClick="window.location.reload()"></i>
       					</a>
					</div>
					<div class="panel-body">
						<div class="paymentFormBody" id="paymentFormBodyId" class="col-xs-12 col-sm-12 col-md-12">
							<div class="col-xs-12 col-sm-2 col-md-2" >
							</div>
							<div class="col-xs-10 col-sm-4 col-md-4" >
								<div class="row" style="margin: 10px;">
									<form id="paymentForm" class="scale-down">
										<div class="cardinfo-card-number">
											<div class="form-group  has-feedback">
											    <label class="cardinfo-label" for="card-number">Card
													Number(no space or dashes)</label>
											    <input type="text" id="cardNumber" ng-model="cardNumberText" onkeypress='return cardNumberFun(event)' class="form-control input-lg" placeholder="Card number">
											      <span style=" font-size: 25px;" class="	fa fa-credit-card form-control-feedback"></span>
										   </div>
										</div>
										<div  style="margin-top: 5px;">
											<div class="row">
												<div class="col-xs-12 col-sm-6 col-md-6">
													<div class="cardinfo-exp-date">
															<label class="cardinfo-label" for="expiration-date">Expiration</label>
															<div id="expiration-date">
																<input type="text" ng-model="expirationDateText" id="expirationDateId" class="form-control input" onkeyup="return expirationDateKeyUp(event)" onkeypress='return expirationDateFun(event)' placeholder="10 / 2018">
															</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="cvv">CVV</label>
														<div id="cvv">
														<input type="text" id="cvvNum" ng-model="cvvText" class="form-control input" onkeypress='return cvvFun(event)'
																placeholder="123" >
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="div-btn-paynow" style="margin-top: 18px;">
												<input id="payNowButton"  ng-click="paymentFun()"  onclick="return paymentIstarghya()" type="submit" value="Pay Now" class="btn btn-success  btn-block payNowButtonCls">
										</div>
										<div style="margin-top: 10px">
												<button class="btn btn-info btn-md" ng-click="addCard()"><i class="fa fa-plus"></i>&nbsp; Add Card</button>
												<!-- <button class="btn btn-info btn-md" ng-click="viewCard()"><i class="fa fa-eye"></i>&nbsp;View Card</button>		 -->								
										</div>
									</form>
									<div id="spinerId" class="spinerClass" ng-style="myObj"  ng-show="spinerFlag" >
										<i class="fa fa-spinner fa-spin" style="font-size:98px;" ></i>
									</div>
								</div>
							</div>
							
							<div class="col-xs-12 col-sm-3 col-md-3">
							
								<div class="col-md-14">
										<table id="grand_sum_table"
											class="grand_sum_table table table-borderless">
											<thead>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Ishtabhrity Amount</td>
													<td  align = "right" style="border-top: none;">{{ishtAmount | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Processing Fee</td>
													<td  align = "right" style="border-top: none;">{{processIng | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
														<td  align = "right" style="">Grand Total : US $ </td>
														<td  align = "right" style=""> <label id="GTotal" value="grandTotal">{{grandTotal | number : 2}}</label></td>
															
													</tr>
													
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td  align = "right" style="border-top: none;">Grand Total : US $ </td>
														<td  align = "right" style="border-top: none;"> <label id="GTotal" value="grandTotal">{{grandTotal | number : 2 }}</label></td>
															
													</tr>
											</thead>
										</table>
									</div>
							</div>
							
							<div class="col-xs-12 col-sm-2 col-md-2" >
								<div class="col-xs-12 col-sm-12 col-md-12" id="paymentResponse" style="font-size: 16px;">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- paymentForm Card  END -->


<!-- ACH details  Start -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main achForm">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">ISTARGHYA PAYMENT FORM</li>
			</ol>
		</div>
		<div class="row">
			<div id="dvErrAlert" class="alert alert-danger" style="display: none">
				<a class="close" href="#">�</a>
				<p>
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value=<%= root.getPhoneNo()%>
					name="phoneNo" placeholder="your phone no"><br> <input
					id="txtApplicationFlow" name="txtApplicationFlow" type="hidden"
					value=<%= applciationFlow %>> <br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">
						<i class="fa fa-arrow-circle-left cursror-pointer back-wrap" onclick="goBackFrmACH()"   title="Back"></i> 
						Add a checking account
						<!-- <button class="backButtonPayment" class="btn" onclick="goBackFrmACH()" title="Back"><i class="fa fa-arrow-circle-left"></i> </button> -->
						<a  class="pull-right" href="#" title="Refresh">
             				<i style="font-size:20px;" class="fa fa-refresh" onClick="window.location.reload()"></i>
       					</a>
					</div>
					<div class="panel-body">
					<form id="paymentACHForm" class="scale-down">
						<div class="paymentFormBody row" id="paymentFormBodyId" class="col-xs-12 col-sm-12 col-md-12">
							<div class="col-xs-12 col-sm-2 col-md-2" >
							</div>
							<div class="col-xs-12 col-sm-4 col-md-4" >
								<div class="row" style="margin: 10px;">
									
										<div class="cardinfo-card-number">
											
										 <div class="form-group  has-feedback">
											    <label class="cardinfo-label" for="ach-name">Name On Account
												</label>
											    <input type="text" id="achName" ng-model="achName" class="form-control" placeholder="Acc Name">
											    
										   </div>
										 
										</div>
										<div class="row" >
										  <div class="form-group  fName-ach col-sm-6 col-md-6 margin-top-5">
											    <label class="cardinfo-label" for="first-name">First Name
												</label>
											    <input type="text" id="firstName" ng-model="firstName" class="form-control" placeholder="Fisrt Name">
											    
										   </div>
										   <div class="form-group  lName-ach col-sm-6 col-md-6 margin-top-5">
											    <label class="cardinfo-label" for="last-name">Last Name
												</label>
											    <input type="text" id="lastName" ng-model="lastName" class="form-control" placeholder="Last Name">
											    
										   </div>
										</div>
										<div  class="">
											<div class="row">
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-exp-date">
															<label class="cardinfo-label" for="expiration-date">Bank Routing Number</label>
															<div id="bank-routing-no">
																<input type="text" ng-model="bankRoutingNo" id="bankRoutingNo" class="form-control input" onblur="return bankRoutingKeyup(event)"  placeholder="9 digit Number">
															</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="cvv">Checking Account Number</label>
														<div id="bank-chAccNo">
														<input type="text" id="bankChACCNo" ng-model="bankChACCNo" class="form-control input" onblur='return achNoFun(event)'
																placeholder="Upto 17 digit" >
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="cvv">Re-enter Checking Account Number</label>
														<div id="bank-re-chAccNo">
														<input type="text" id="reBankChACCNo" ng-model="reBankChACCNo" class="form-control input" onblur='return reAchNoFun(event)'
																placeholder="Upto 17 digit" >
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="stAddress">Street Address</label>
														<div id="bank-stAddress">
														<input type="text" id="stAddress" ng-model="stAddress" class="form-control input" 
																placeholder="Street Address" >
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="extAddress">Extended Address</label>
														<div id="bank-extAddress">
														<input type="text" id="extAddress" ng-model="extAddress" class="form-control input" 
																placeholder="Extended Address" >
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="locality">City</label>
														<div id="bank-locality">
														<input type="text" id="locality" ng-model="locality" class="form-control input" 
																placeholder="City" >
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
												<div class="row" >
													  <div class="form-group  fName-ach col-sm-6 col-md-6 margin-top-5">
														    <label class="cardinfo-label" for="state">State</label>
															<select name='state' required id="state" style="text-transform:uppercase;" class="form-control input-sm"  ng-model="state"  placeholder="Select State" ng-options="state as state.StateName for state in allStates">
									                			<option value="" selected >Select State</option>
									                		</select>
													   </div>
													   <div class="form-group  lName-ach col-sm-6 col-md-6 margin-top-5">
														    <div class="cardinfo-cvv" >
																<label class="cardinfo-label" for="postalCode">Postal code</label>
																<div id="bank-posCode">
																<input type="text" id="postalCode" ng-model="postalCode" class="form-control input" 
																		placeholder="Postal Code" >
																</div>
															</div>
													   </div>
												</div>
												</div>
											
												
												 
                                        
                                      
												<div class="col-xs-12 col-sm-12 col-md-12 margin-top-5">
													<div class="cardinfo-cvv" >
														<label class="cardinfo-label" for="cvv">Driver License Number</label>
														<div id="dl-no">
														<input type="text" id="dlNo" ng-model="dlNo" class="form-control input" 
																placeholder="" >
														</div>
													</div>
												</div>
												
												
		
											</div>
										</div>
										
								
									<div id="spinerId" class="spinerClass" ng-style="myObj"  ng-show="spinerFlag" >
										<i class="fa fa-spinner fa-spin" style="font-size:98px;" ></i>
									</div>
								</div>
							</div>
									<div class="col-xs-12 col-sm-3 col-md-3">
							
								<div class="col-md-14">
										<table id="grand_sum_table"
											class="grand_sum_table table table-borderless">
											<thead>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Ishtabhrity Amount</td>
													<td  align = "right" style="border-top: none;">{{ishtAmount | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
													<td  align = "right" style="border-top: none;">Processing Fee</td>
													<td  align = "right" style="border-top: none;">{{processIng | number : 2}}</td>
													</tr>
													<tr ng-show="selPmtMethod == 'AUTO'">
														<td  align = "right" style="">Grand Total : US $ </td>
														<td  align = "right" style=""> <label id="GTotal" value="grandTotal">{{grandTotal | number : 2}}</label></td>
															
													</tr>
													
													<tr ng-show="selPmtMethod == 'MANUAL'">
														<td  align = "right" style="border-top: none;">Grand Total : US $ </td>
														<td  align = "right" style="border-top: none;"> <label id="GTotal" value="grandTotal">{{grandTotal | number : 2 }}</label></td>
															
													</tr>
											</thead>
										</table>
									</div>
							</div>
							<div class="col-xs-12 col-sm-2 col-md-2" >
								<div class="col-xs-12 col-sm-12 col-md-12" id="achADDResponse" style="font-size: 16px;">
									
								</div>
							</div>
						</div>
						<div class="row">
						<div class="col-xs-12 col-sm-2 col-md-2 margin-top-5">
						</div>
							<div class="col-xs-12 col-sm-8 col-md-8 margin-top-5">
													<div class="panel panel-default term-mandate-panel">
      													
      													<div class="panel-body term-mandate">
      													 By clicking <input type="checkbox" ng-model="termcb">, I authorize Braintree, a service of PayPal, on behalf of {{firstName}} {{lastName}} (i) to verify my bank account information using bank information and consumer reports and (ii) to debit my bank account.</div>
   													 </div>
												</div>
												
						</div>
						
						<div class="row">
						<div class="col-xs-12 col-sm-2 col-md-2 margin-top-5">
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4 margin-top-5">
										<div class="div-btn-paynow" style="margin-top: 18px;">
												<input id="payNowButton"  ng-disabled="!termcb" ng-click="paymentAchFun()"  onclick="return paymentACHValidation()" type="submit" value="Pay Now" class="btn btn-success  btn-block payNowButtonCls">
										</div>
										<div style="margin-top: 10px">
												<button class="btn btn-info btn-md" ng-click="addACH()"><i class="fa fa-plus"></i>&nbsp; Add </button>
												<!-- <button class="btn btn-info btn-md" ng-click="viewCard()"><i class="fa fa-eye"></i>&nbsp;View Card</button>		 -->								
										</div>
								</div>
									</div>
								</form>	
										
					</div>
				</div>
			</div>
		</div>
	</div>
<!--ACH details  Start  END -->


<!-- paymentCheckoutForm  Start -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main cardDdetailsPage">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="index.jsp"> <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
				</a></li>
				<li class="active">ISTARGHYA Payment Selection</li>
			</ol>
		</div>
		<div class="row">
			<div id="dvErrAlert" class="alert alert-danger" style="display: none">
				<a class="close" href="#">�</a>
				<p>
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value=<%= root.getPhoneNo()%>
					name="phoneNo" placeholder="your phone no"><br> <input
					id="txtApplicationFlow" name="txtApplicationFlow" type="hidden"
					value=<%= applciationFlow %>> <br>
				<div class="panel panel-default">
				
					<div class="panel-heading" id="accordion">
					<i class="fa fa-arrow-circle-left cursror-pointer back-wrap" onclick="goBack()"   title="Back"></i> 
					Select Payment method
					
						<!-- <button class="backButtonPayment" class="btn" onclick="goBack()" title="Back"><i class="fa fa-arrow-circle-left"></i> </button> -->
						
						<a  class="pull-right" href="#" title="Refresh">
             				<i style="font-size:20px;" class="fa fa-refresh" onClick="window.location.reload()"></i>
       					</a>
					</div>
					<div class="panel-body">
						<div class="paymentFormBody" id="paymentFormBodyId" class="col-xs-12 col-sm-12 col-md-12">
							<div class="col-xs-12 col-sm-2 col-md-2" >
								
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6" >
								<div class="row" style="margin: 10px;">
									<form id="cardListForm" class="scale-down">
										
										<table id="cardDetailsTBody" class="table table-responsive">
										<tr>
											
											
											<th>
												Your Credit and debit cards
											
											</th>
											
											<th >
												Expires On
											
											</th>
											<th >
												
										
											</th>
											
											<th >
											
											
											</th>
										</tr>
										</table>
										
											<div style="margin-top: 10px">
												<a  onClick="addCardForm()" class="add-card"><i class="fa fa-plus"></i>&nbsp; Add credit or debit Card</a>
												<!-- <button class="btn btn-info btn-md" ng-click="viewCard()"><i class="fa fa-eye"></i>&nbsp;View Card</button>		 -->								
											</div>
									</form>
									
									<div id="spinerId" class="spinerClass" ng-style="myObj"  ng-show="spinerFlag" >
										<i class="fa fa-spinner fa-spin" style="font-size:98px;" ></i>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-4 col-md-4" >
								<div class="col-xs-12 col-sm-12 col-md-12" id="paymentResponse" style="font-size: 16px;">
									{{removeCardRes}}
								</div>
							</div>
							
						
							<div class="col-xs-12 col-sm-12 col-md-12 margin-top-50" >
								
								<div class="row" style="margin: 10px;">
									<div class="col-xs-12 col-sm-2 col-md-2" >
									</div>
							<div class="col-xs-12 col-sm-6 col-md-6" >
								
									<form id="achListForm" class="scale-down">
										
										<table id="achDetailsTBody" class="table table-responsive">
										<tr>
											
											
											<th>
												Your Checking accounts
											
											</th>
											
											<th >
												Name on account
											
											</th>
											<th >
												
										
											</th>
											
											<th >
											
											
											</th>
										</tr>
										</table>
										
											<div style="margin-top: 10px">
												<a  onClick="addACHForm()" class="add-card"><i class="fa fa-plus"></i>&nbsp; Add Checking account</a>
												<!-- <button class="btn btn-info btn-md" ng-click="viewCard()"><i class="fa fa-eye"></i>&nbsp;View Card</button>		 -->								
											</div>
									</form>
									
									<div id="spinerId" class="spinerClass" ng-style="myObj"  ng-show="spinerFlag" >
										<i class="fa fa-spinner fa-spin" style="font-size:98px;" ></i>
									</div>
								</div>
							</div>
								
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	
<!-- paymentForm Card  END -->

	<% 	  System.out.println("1  phoneNo: "+root.getPhoneNo()); %>
	






</body>

</html>
