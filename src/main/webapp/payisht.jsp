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

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Satsang America - Istavrity</title>

<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.6/dist/loadingoverlay.min.js"></script>

    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
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
<script src="js/jquery-2.1.4.js"></script>
<script src="js/jquery.tabletojson.js"></script>
<script src="js/operation.js"></script>
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
				<a class="close" href="#">×</a>
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
						<form name ="ishtPayForm" class="form-vertical">
							<fieldset>
								<div class="form-group ">
									<div class="row">
										<div class=" col-md-7">
											<table class="table table-striped table-bordered">
												<tbody>
													<tr>
														<td>
															<strong>Payment Method:</strong>
														</td>
														<td>
															<select id="selPmtMethod" name="selPmtMethod"  style="text-transform:uppercase;"  placeholder="Payment Method" class="form-control" ng-model="selPmtMethod" required>
																<option ng-selected="selPmtMethod">AUTO</option>
																<option>MANUAL</option> 
															</select>
														</td>
													</tr>
													<tr>
														<td>
															<strong>Bank Name</strong></td>
														<td>
															<input id="stBankName" name="stBankName" placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" required>
														</td>
													</tr>
													<tr>
														<td>
															<strong>Transaction Ref / Cheque No</strong>
														</td>
														<td>
															<input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" required>
														</td>
													</tr>
													<tr>
														<td><strong>E-Transaction Date / Cheque Date:</strong></td>
														<td><input id="dtChqDate" onchange="checkDate()" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" required></td>
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
												<select style="width:45px" class="mySelectOp "  id="my_select,{{$index}}" onchange="selectedOperatoins(this.id)">	
													<option value="+" style="font-size:24px" class="fa">&#xf067;</option>
													<option value="-" style="font-size:24px" class="fa">&#xf068;</option>
													<option value="*" style="font-size:24px" class="fa">&#xf069;</option>
													<option value="/" style="font-size:24px" class="fa">&#xf033;</option>
												</select>
											</td>
											
											<td><input name="textValue" id="value{{$index}}" onblur="CurrencyFormatted(this.value,this.id)"  value="0.00" style="width:45px" onkeypress="getIdVal();" class="selecttextValue" /></td>
											
													
											<td><input name="swastyayani" id="S{{$index+1}}" onblur="CurrencyFormatted(this.value,this.id)"  value="{{ishtL.swastyayani | number:2}}"style="width:45px" class="sum1" /></td>
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
                                 <input class="btn btn-primary" id = "submit_1" rel="modal:open" type="submit" ng-click="ishtPayForm.$valid && ishtPay($event)" 
                                 value="Continue" ng-disabled="ishtPayForm.$invalid"  
                                 
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
				<a class="close" href="#">×</a>
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
						<form name ="ishtPayForm" class="form-vertical">
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
																<option ng-selected="selPmtMethod">E-TRANSFER</option>
																<option>CASH</option> 
															</select>
														</td>
													</tr>
													<tr>
														<td>
															<strong>Bank Name</strong></td>
														<td>
															<input id="stBankName" name="stBankName" placeholder="Bank Name" type="text" style="text-transform:uppercase;"  class="form-control" ng-model = "stBankName" disabled required>
														</td>
													</tr>
													<tr>
														<td>
															<strong>Transaction Ref / Cheque No</strong>
														</td>
														<td>
															<input id="stTrnNo" name="stTrnNo" placeholder="Transaction Ref /Cheque No" type="text" style="text-transform:uppercase;"
															class="form-control" ng-model = "stTrnNo" required disabled>
														</td>
													</tr>
													<tr>
														<td><strong>E-Transaction Date / Cheque Date:</strong></td>
														<td><input id="dtChqDate" onchange="checkDate()" name="dtChqDate" type="date" style="text-transform:uppercase;" class="form-control" ng-model = "dtChqDate" disabled required></td>
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
											class="table table-borderless">
											<thead >
												<tr>
												<td colspan = 13 align = "right">Grand Total : US $  <input type="text" id="GTotalPre" 
															ng-model="grandTotalText"	style="width:50px" value='{{grandTotal}}'  disabled/></td>
												</tr>
											</thead>
										</table>
								</div>
							</div>
							<div class="panel-footer">
									<a href="#" onclick="editDepositForm()"><span class="glyphicon glyphicon-pencil btn btn-info btn-md btn-edit"></span></a>
									<button class="btn btn-success btn-parmentForm" onclick="return parmentForm()">Payment <i class="fa fa-dollar"></i></button>
									<br><small class="text-muted"  class="btn btn-primary">Satsang America,Inc  </small>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

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
				<a class="close" href="#">×</a>
				<p>
			</div>
			<div class="col-md-12">
				<input type="hidden" id="phoneNo" value=<%= root.getPhoneNo()%>
					name="phoneNo" placeholder="your phone no"><br> <input
					id="txtApplicationFlow" name="txtApplicationFlow" type="hidden"
					value=<%= applciationFlow %>> <br>
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">ISTARGHYA PAYMENT METHOD
						<button class="backButtonPayment" class="btn" onclick="goBack()" title="Back"><i class="fa fa-arrow-circle-left"></i> </button>
						<a  class="pull-right" href="#" title="Refresh">
             				<i style="font-size:30px;" class="fa fa-refresh" onClick="window.location.reload()"></i>
       					</a>
					</div>
					<div class="panel-body">
						<div class="paymentFormBody" id="paymentFormBodyId" class="col-xs-12 col-sm-12 col-md-12">
							<div class="col-xs-12 col-sm-2 col-md-2" >
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6" >
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
												<button class="btn btn-success btn-md" ng-click="addCard()"><i class="fa fa-plus"></i>&nbsp; Add Card</button>
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
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<% 	  System.out.println("1  phoneNo: "+root.getPhoneNo()); %>
	
<script>


	(function() {
		var courseApp = angular.module("ishtApp", [ "xeditable" ]);
		courseApp
				.controller(
						'ishtCtrl',
						[
								'$scope',
								'$http',
								'$filter',
								function($scope, $http, $filter,$compile) {
									$scope.spinerFlag=false;
									
									/* 
									$scope.afterTransactionSuccess=function(id){
										var contextPath = "transactionsuccess.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 data:{
												 	"transactionId":id
												 },
											 headers: {'Content-Type': 'application/json'}
										}).then(function mySucces(data) {
											 window.location = 'ishtpayconfirm.jsp';
										},function myError(d) {
											 console.log("Error:    "+d);
											 alert("fail");
											 $scope.spinerFlag=false;
										 });	 
									} */
									
									$scope.paymentFun=function(){
										removePNode();
										var res=paymentIstarghya();
										if(res.toString()=='false'){
											alert("Fill this required field.");
										}else{
											$scope.spinerFlag=true;
											var contextPath = "transactions.do";
											$http({
												 method : "POST",
												 url : contextPath,
												 data:{
													 "amount":document.getElementById("GTotalPre").value,
													 "familyCode":document.getElementById("familyCode").value,
													 "contact":document.getElementById("contact").value,
													 "cardNumber":$scope.cardNumberText,
													 "expirationDate":$scope.expirationDateText,
													 "cvv":$scope.cvvText
												 },
												 headers: {'Content-Type': 'application/json'}
												 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
											 }).then(function mySucces(data) {
												 $scope.spinerFlag=false;
												  $scope.json = angular.toJson(data.data);
												  var obj = JSON.parse($scope.json);
												  
												  	if(obj.status.toString()=="false"){
												  		var node = document.createElement("P");
												  		var failedTxt = document.createTextNode("Failed transaction");
											  			node.appendChild(failedTxt); 
												  		for(var i=0;i<obj.errorValidations.length;i++){
												  		    var textnode = document.createTextNode(", "+obj.errorValidations[i].error);
												  		    node.appendChild(textnode);
												  			node.style.color = "red";
												  			node.style.margin="22px";
												  		    document.getElementById("paymentResponse").appendChild(node);
												  		  $scope.spinerFlag=false;
												  		  
												  		}
												  	}else{
												  		/* var node = document.createElement("P");
												  		var seccussTxt = document.createTextNode("Transaction ");
											  		    var textnode = document.createTextNode("succussfully, Id: "+obj.trasactionId);
											  			
											  		    node.appendChild(seccussTxt); 
											  		 	node.appendChild(textnode);
											  		  
											  			node.style.color = "green";
											  			node.style.margin="22px";
											  		    document.getElementById("paymentResponse").appendChild(node); */
												  		
											  		  $scope.spinerFlag=false;
											  		//$scope.afterTransactionSuccess(obj.trasactionId);
											  		 
											  		sessionStorage.setItem("transactionId", obj.trasactionId);
											  		sessionStorage.setItem("GradTotalAmount", document.getElementById("GTotalPre").value);
											  		//alert(sessionStorage.getItem("transactionId"));
											  		window.location = 'ishtpayconfirm.jsp';
												  	}
												  $scope.spinerFlag=false;
											 },function myError(d) {
												 console.log("Error:    "+d);
												 alert("fail");
												 $scope.spinerFlag=false;
											 });
										}
									};
									
									
									function removePNode(){
										var length = document.getElementById("paymentResponse").childElementCount;
										var childNodeEle = document.getElementById("paymentResponse");   
										for(var i=0; i<length;i++){
											childNodeEle.removeChild(childNodeEle.childNodes[i]);
										}
									}
									$scope.addCard=function(){
										removePNode();
										var res=paymentIstarghya();
										if(res.toString()=='false'){
											alert("Fill this required field.");
										}else{
											var contextPath = "addcards.do";
											$http({
												 method : "POST",
												 url : contextPath,
												 data:{
													 "familyCode":document.getElementById("familyCode").value,
													 "contact":document.getElementById("contact").value,
													 "cardNumber":$scope.cardNumberText,
													 "expirationDate":$scope.expirationDateText,
													 "cvv":$scope.cvvText
												 },
												 headers: {'Content-Type': 'application/json'}
											 }).then(function mySucces(data) {
												  $scope.json = angular.toJson(data.data);
												  var obj = JSON.parse($scope.json);
												  var node = document.createElement("P");
										  		   var textnode = document.createTextNode(obj.responseMsg);
										  		    node.appendChild(textnode);
										  			node.style.color = "green";
										  			node.style.margin="20px";
										  		    document.getElementById("paymentResponse").appendChild(node);
												  
											 },function myError(d) {
												 console.log("Error:    "+d);
											 });
										}//else
									}//addCard
									
									$scope.viewCard=function(){
										var removeTBody = document.getElementById("cardDetailsTBody");
										removeTBody.innerHTML = "";
											var contextPath = "viewCards.do";
											$http({
												 method : "POST",
												 url : contextPath,
												 data:{
													 "contact":document.getElementById("contact").value,
												 },
												 headers: {'Content-Type': 'application/json'}
												 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
											 }).then(function mySucces(data) {
												  $scope.json = angular.toJson(data.data);
												  var obj = JSON.parse($scope.json);
												  var call = null;
												  for(var i=0;i<obj.length;i++){
													  call=
														"<td scope='row'>"+obj[i].cardNumber +"</td>"+
														"<td scope='row'>"+obj[i].expirationDate +"</td>"+
														"<td scope='row'>"+obj[i].cvv +"</td>"+
														"<td scope='row'><a  href="+obj[i].cardNumber+" class='btn btn-success' onclick='pay()'>Pay</a></td>"+
														"<td scope='row'><a  href="+obj[i].cardNumber+" class='btn btn-danger'  data-ng-click='removeCard()'><i class='fa fa-trash-o'></i>&nbsp;Remove</a></td>";
														
													  $('#cardDetailsTBody').append('<tr align="center">' + call + '</tr>');
													  call = null;
												  }
												  
												$('.paymentForm').hide();
												$('.cardDdetailsPage').show();
											 },function myError(d) {
												 console.log("Error:    "+d);
											 });
									}//viewCard
									
									
								
									$(document)
											.ready(
													function() {
														//alert('Hi Shyam 2');
														var istPhone = $(
																'#phoneNo')
																.val();
														var applicationFlow = $(
																'#txtApplicationFlow')
																.val();
														var contextPath = "getIshtJSONObject.do"
																+ "?phoneNo="
																+ istPhone
																+ "&applicationFlow="
																+ applicationFlow;
														//var contextPath = "getIshtJSONObject.do"+"?phoneNo="+ istPhone;
														$http({
															method : "POST",
															url : contextPath
														})
																.then(
																		function mySucces(
																				data) {
																			var returnObject = eval(data); // Parse Return Data
																			if (returnObject.data.returnCode == 'error') {
																				$scope.PostDataResponse = returnObject.data.returnMessage;
																			} else {
																				$scope.ishtLine = returnObject.data.userJSONObject.line;

																				//$table.bootstrapTable('load', returnObject.data.userJSONObject.line);
																				$table
																						.bootstrapTable('hideLoading');
																				$table
																						.tableEditor();
																			}
																		},
																		function myError(
																				d) {
																			alert("failed to get Inside");
																		});
													});

									$(document)
											.on(
													'change',
													'#sum_table tr:not(.totalCol) input:text',
													function() {
														var $table = $(this)
																.closest(
																		'table');
														var total = 0;
														var thisNumber = $(this)
																.attr('class')
																.match(/(\d+)/)[1];
														var count = 1;
														var mySelectOp = 0.00;
														var mytextValue = 0.00;
														var outVal = 0.00;
														var queryArr = [];

														$(this)
																.closest('tr')
																.find(
																		".mySelectOp")
																.each(
																		function() {
																			mySelectOp = this.value;
																		});
														$(this)
																.closest('tr')
																.find(
																		".mytextValue")
																.each(
																		function() {
																			mytextValue = this.value;
																		});

														$(this)
																.closest('tr')
																.find("input")
																.each(
																		function() {
																			if (count > 3
																					&& count < 13) {
																				//queryArr.push(this.value);
																				if (mySelectOp == "+") {
																					outVal = parseFloat(this.value)
																							+ parseFloat(mytextValue);
																					$(
																							this)
																							.val(
																									formatCurrency(outVal));
																					outVal = 0.00;
																				} else if (mySelectOp == "-") {
																					outVal = parseFloat(this.value)
																							- parseFloat(mytextValue);
																					if (outVal < 0) {
																						//alert(outVal);
																						$(
																								this)
																								.val(
																										formatCurrency(0.00));
																					} else {
																						$(
																								this)
																								.val(
																										formatCurrency(outVal));
																					}
																					outVal = 0.00;
																				} else if (mySelectOp == "*") {
																					outVal = parseFloat(mytextValue)
																							* parseFloat(this.value);
																					$(
																							this)
																							.val(
																									formatCurrency(outVal));
																					outVal = 0.00;
																				} else if (mySelectOp == "/") {
																					outVal = parseFloat(this.value)
																							/ parseFloat(mytextValue);
																					$(
																							this)
																							.val(
																									formatCurrency(outVal));
																					outVal = 0.00;
																				}
																				count++;
																			} else {
																				count++;
																			}
																		});

														/* var v= myOnFunction(queryArr);
														alert(v); */

														//alert('RowVal :'+$(this).attr('class').match(/(\d+)/)[1]);
														$table
																.find(
																		'tr:not(.totalCol) .sum'
																				+ thisNumber)
																.each(
																		function() {
																			total += parseFloat(
																					this.value)
																					.toFixed(
																							2);
																		});
														total = formatCurrency(total); //added b shyam

														$table
																.find(
																		'.totalCol td:nth-child('
																				+ thisNumber
																				+ ')')
																.html(total);

													});

									$(document).on('change', 'input', newSum);
									function newSum() {
										//alert('newSum')
										var sum = 0;
										var thisRow = $(this).closest('tr');
										var total = 0;
										var GrTotal = 0;
										var count = 0;
										var tempValue = 0;
										$(thisRow)
												.find("td:not(.total) input")
												.each(
														function() {
															if (!isNaN(this.value)) {
																$(this)
																		.closest(
																				'tr')
																		.find(
																				".mytextValue")
																		.each(
																				function() {
																					tempValue = this.value;
																				});
																sum += parseFloat(parseFloat(
																		Math
																				.round(this.value * 100) / 100)
																		.toFixed(
																				2));
															}
														});

										sum = formatCurrency(sum - tempValue); //added b shyam
										$(thisRow).find(".total").html(sum);
										$('.total')
												.each(
														function() {
															GrTotal += parseFloat($(
																	this)
																	.html());
															//alert('GrTotal :'+GrTotal);
															//document.getElementById("GTotal").innerHTML = GrTotal;
															$("#GTotal")
																	.val(
																			GrTotal
																					.toFixed(2));

														});
									}

									$scope.loadUser = function() {
										var istPhone = $('#phoneNo').val();
										var applicationFlow = $(
												'#txtApplicationFlow').val();
										var contextPath = "getIshtJSONObject.do"
												+ "?phoneNo="
												+ istPhone
												+ "&applicationFlow="
												+ applicationFlow;
										$http({
											method : "POST",
											url : contextPath
										})
												.then(
														function mySucces(data) {
															var returnObject = eval(data); // Parse Return Data
															alert(returnObject.data.returnCode);
															if (returnObject.data.returnCode == 'error') {
																$scope.PostDataResponse = returnObject.data.returnMessage;
															} else {
																$scope.ishtLine = returnObject.data.userJSONObject.line;
															}
														},
														function myError(d) {
															alert("failed to load");
														});
									};
								} ]);

	})();

	function CurrencyFormatted(amount, val) {
		//alert('VAL:'+val);
		var i = parseFloat(amount);
		if (isNaN(i)) {
			i = 0.00;
		}
		var minus = '';
		if (i < 0) {
			minus = '-';
		}
		i = Math.abs(i);
		i = parseInt((i + .005) * 100);
		i = i / 100;
		s = new String(i);
		if (s.indexOf('.') < 0) {
			s += '.00';
		}
		if (s.indexOf('.') == (s.length - 2)) {
			s += '0';
		}
		s = minus + s;
		//document.getElementById("One").value=s;
		document.getElementById(val).value = s;
		$scope.value = s;
		return s;
	}
	function formatCurrency(amount) {
		var i = parseFloat(amount);
		if (isNaN(i)) {
			i = 0.00;
		}
		var minus = '';
		if (i < 0) {
			minus = '-';
		}
		i = Math.abs(i);
		i = parseInt((i + .005) * 100);
		i = i / 100;
		s = new String(i);
		if (s.indexOf('.') < 0) {
			s += '.00';
		}
		if (s.indexOf('.') == (s.length - 2)) {
			s += '0';
		}
		s = minus + s;
		return s;
	}
	$(document).ready(function() {
		//$("dtIshtDate").focus(function(){
		$("dtChqDate").focus(function() {
			//alert('Hi');
			var dtToday = new Date();
			var month = dtToday.getMonth() + 1;
			var day = dtToday.getDate();
			var year = dtToday.getFullYear();

			if (month < 10)
				month = '0' + month.toString();
			if (day < 10)
				day = '0' + day.toString();

			var maxDate = year + '-' + month + '-' + day;
			document.getElementById("dtChqDate").setAttribute("max", maxDate);
			//$('#txtDate').attr('max', maxDate);
		});
	
		
		
	
			
	});

	
	
		

	function checkDate() {
		var selectedText = document.getElementById('dtChqDate').value;
		var selectedDate = new Date(selectedText);

       // alert("selectedDate :"+selectedDate);
        
		var selYear = selectedDate.getFullYear().toString();
		var salMonth = (selectedDate.getMonth() + 101).toString().substring(1);
		var salDay = (selectedDate.getDate() + 100).toString().substring(1);

		//alert("selYear :"+selYear);
		//alert("salMonth :"+salMonth);
		//alert("salDay :"+salDay);
		
		var now = new Date();

		var nowYear = now.getFullYear().toString();
		var nowMonth = (now.getMonth() + 101).toString().substring(1);
		var nowDay = (now.getDate() + 100).toString().substring(1);

		//alert("nowYear :"+nowYear);
		//alert("nowMonth :"+nowMonth);
		//alert("nowDay :"+nowDay);
		
		if( (selYear>=nowYear)&&(salMonth>=nowMonth)&&(salDay>=nowDay) ){
			alert("Transaction date should not be later than today's date.");
			document.getElementById("dtChqDate").value = "DD-MM-YYYY";
		}else{
		
		}
		
		/* var salactDate = Date.parse("" + salDay + "-" + salMonth + "-"
				+ selYear + "");
		var now = Date.parse("" + nowDay + "-" + nowMonth + "-" + nowYear + "");
		//alert(salactDate+"                    "+now);
		if (salactDate > now) {
			alert("select valied date");
			document.getElementById('dtChqDate').value = null;
		} */
	} 
</script>









</body>

</html>
