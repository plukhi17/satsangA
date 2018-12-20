<%@page import="sun.reflect.ReflectionFactory.GetReflectionFactoryAction"%>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@page import="com.olsa.pojo.FamilyMDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@ page session="true" %>
<%@ page errorPage="error.jsp" %>  


<!DOCTYPE html>
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

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Satsang America - Dashboard</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="css/styles.css" rel="stylesheet">
    <script src="js/jquery-2.1.4.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="js/lumino.glyphs.js"></script>

    <script src="js/bootstrap-table.js"></script>
	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular-sanitize.min.js"></script>
	<!-- <script src="js/ng-csv.js"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/ng-csv/0.3.6/ng-csv.min.js"></script>
	
	 	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.1/jszip.js"></script>
	 	 <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.1/xlsx.js"></script>
	    <script src="https://fastcdn.org/FileSaver.js/1.1.20151003/FileSaver.js"></script>
	
		<script src="js/mycsv.js"></script>
	<script src="js/table2excel.js" type="text/javascript"></script>
	<style type="text/css">
		table.gridTable{
			border:2px;
			border-color:black;
			border:2px;
			padding:10px;
			margin:10px;
			border-color:#666666;
			border-collapse:collapse;
			text-align:center;
			font-size:14px;
			width:85%;
		}
		table.gridTable th,td{
			padding:8px;
			border-style:solid;
		}
		.headerrow{
			background-color:#F6B4A5;
		}
		.datarow{
			background-color:white;
		}
	</style>
	
</head>

<%
	RootMDB root = (RootMDB)session.getAttribute("userBean");
		session.setAttribute("PhoneNo", root.getPhoneNo());
%>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

    if(session.getAttribute("userBean")==null){
   		response.sendRedirect(request.getContextPath() + "/OnlineSA");
	}
%>
<body ng-app="myApp" ng-controller="appCtrl" class="ng-cloak" ng-cloak>
<div>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Satsang America</span>Ishtavrity</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<svg class="glyph stroked male-user">
								<use xlink:href="#stroked-male-user"></use>
							</svg><%=root.getUserName()%> <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li>
								<a href="#">
									<svg class="glyph stroked male-user">
										<use xlink:href="#stroked-male-user"></use>
									</svg> Profile</a>
							</li>
							<li>
								<a href="#">
									<svg class="glyph stroked gear">
										<use xlink:href="#stroked-gear"></use>
									</svg> Settings</a>
							</li>
							<li>
								<a href="login.jsp">
									<svg class="glyph stroked cancel">
										<use xlink:href="#stroked-cancel"></use>
									</svg> Logout</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>

		</div>
		<!-- /.container-fluid -->
	</nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		</form>
		<ul class="nav menu">
			<li class="active">
				<a href="index.jsp">
					<svg class="glyph stroked dashboard-dial">
						<use xlink:href="#stroked-dashboard-dial"></use>
					</svg> Dashboard</a>
			</li>
			<li>
				<a href="searchIsht.jsp">
					<svg class="glyph stroked clipboard with paper">
					<use xlink:href="#stroked-clipboard-with-paper"/></svg>
					Ishtavrity Transcations</a>
			</li>
			<li role="presentation" class="divider"></li>
			<li>
				<a href="login.jsp">
					<svg class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use>
					</svg> Logout</a>
			</li>
		</ul>

	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li>
					<a href="#">
						<svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use>
						</svg>
					</a>
				</li>
			<li class="active">Report
			
				<input type="hidden" value=${PhoneNo} id="PhoneNo">
			</li>
			
			</ol>
		</div>
		<div>
			<table class="table table-striped table-bordered table-hover table-condensed patientHomeTable">
				<tr class="headerrow" > 
					<th style="text-align: center;">Family Code</th> 
					<th style="text-align: center;">Receipt No.</th>
					<th style="text-align: center;" colspan="2">To-Date    &nbsp;&nbsp; &nbsp;|&nbsp;&nbsp;&nbsp;    From date</th>
					<th style="text-align: center;">Search</th>
					<th style="text-align: center;">Export</th>
				</tr>
				<tr class="headerrow"> 
					<td><input type="search" ng-model="familyCode" id="familyCodeId" name="familyCode" class="form-control" placeholder="Family Code"></td> 
					<td><input type="text"  ng-model="receiptNo" id="receiptnoId" name="receiptno" class="form-control" placeholder="Receipt No."> </td>
					<td>
						<input ng-model="toDate" id="toDateId" type="date" name="toDate" class="form-control sm">
					</td>
					<td>	<input ng-model="fromDate" id="fromDateId" type="date" name="fromDate" class="form-control">
					</td>
					<td>
							<div>
								<button class="btn btn-primary btn-ef btn-ef-3 btn-ef-3c mb-10" ng-click="myReport()"><span class="glyphicon glyphicon-search"></span> Report </button>
							</div>
					</td>
					<td>
						<div>
							<button class="btn btn-primary" ng-click="exportReport()"><i class="fa fa-download" ></i>&nbsp;Export</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div style="overflow-x:auto;">
			 <table class="table table-bordered table-hover" id="reportTable">
			    <thead>
			      <tr>
			        <th>FAMILY ID</th>
			        <th>PERSONAL ID</th>
			        <th>FIRST NAME</th>
			        <th>MIDDLE NAME</th>
			        <th>LAST NAME</th>
			         <th>EMAIL</th>
			        <th>PHONE NO</th>
			        <th>ADDRESS</th>
			         <th>RITWIK ID</th>
			        <th>RITWIK STATUS</th>
			        <th>ACTIVE</th>
			         <th>CREATED ON</th>
			        <th>LAST UPDATED</th>
			        <th>USER TYPE</th>
			         <th>USER NAME</th>
			        <th>USER ROLE</th>
			        <th>R NAME</th>
			        
			         <th>PPRFLAG</th>
			        <th>PSERONALIZE</th>
			        <th>IND FAMILY CODE</th>
			           
			      </tr>
			    </thead>
			    <tbody id="reportTBody">
			     
			    </tbody>
			  </table>

		</div>
	<!--/.main-->
</div></div>
	<script>
		!function ($) {
		    $(document).on("click","ul.nav li.parent > a > span.icon", function(){          
		        $(this).find('em:first').toggleClass("glyphicon-minus");      
		    }); 
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>
</body>

</html>