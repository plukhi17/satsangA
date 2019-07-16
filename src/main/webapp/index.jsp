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
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Satsang America - Dashboard</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	<script src="js/angular.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!--Icons-->
	<script src="js/lumino.glyphs.js"></script>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>

<%
	RootMDB root = (RootMDB)session.getAttribute("userBean");
	List<FamilyMDB> familyList = root.getFamily();
	

  	
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
<body ng-app="onlineSA">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<%@include file="HeaderBar.jsp" %>
				
				<!-- 
				<a class="navbar-brand" href="#"><span>Satsang America Ishtarghya Deposit System</span> </a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<svg class="glyph stroked male-user">
								<use xlink:href="#stroked-male-user"></use>
							</svg> User <span class="caret"></span></a>
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
								<a href="#">
									<svg class="glyph stroked cancel">
										<use xlink:href="#stroked-cancel"></use>
									</svg> Logout</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>

		</div>
		
	</nav>
 -->
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar" ng-controller="onlineSAController">
		<!--<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>-->
		<ul class="nav menu">
			<li class="active">
				<a href="index.jsp">
					<svg class="glyph stroked dashboard-dial">
						<use xlink:href="#stroked-dashboard-dial"></use>
					</svg> Dashboard</a>
			</li>
			<li>
				<a href="payisht.jsp">
					<svg class="glyph stroked app-window-with-content">
						<use xlink:href="#stroked-app-window-with-content"></use>
					</svg> Pay Ishtavrity</a>
			</li>
			
			<li>
				<a href="searchIsht.jsp">
					<svg class="glyph stroked clipboard with paper"><use xlink:href="#stroked-clipboard-with-paper"/></svg>
					Ishtavrity Transcations</a>
			</li>
			
				<% if(root.getUserType()!=null && root.getUserType().equalsIgnoreCase(OnlineSAConstants.ADMIN_USER)) { %>
					<%@include file="includeAdminMenu.jsp" %>
				<% } %>
			
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
				<li class="active">Dashboard</li>
			</ol>
		</div>
		<!--/.row sub navigation-->

		<div class="row">
			<div class="col-lg-8">
				<h4 class="page-header">Welcome <%= root.getFirstName() %> !!!</h4>
			</div>
		</div>
		<!--/.row sub header-->

		<div class="row">
			<div class="col-md-8">

				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">
						<svg class="glyph stroked empty-message">
							<use xlink:href="#stroked-empty-message"></use>
						</svg> Important Communication</div>
					<div class="panel-body">
						<div class="chat-body clearfix">
							<div class="header">
								<strong class="primary-font">Communication 1</strong> <small class="text-muted">11-Oct-2016</small>
							</div>
							<p>
								Satsang America's purpose is to promote Dharma, which upholds the existence and growth, to every individual irrespective
								of age, gender, race, religion, national origin or any other factors that may discriminate in any way.
							</p>
						</div>
						<div class="chat-body clearfix">
							<div class="header">
								<strong class="primary-font">Communication 2</strong> <small class="text-muted">10-Mar-2016</small>
							</div>
							<p>
								Satsang America's purpose is to promote Dharma, which upholds the existence and growth, to every individual irrespective
								of age, gender, race, religion, national origin or any other factors that may discriminate in any way.
							</p>
						</div>
					</div>
										
					<div class="panel-footer">
						<small class="text-muted"><%@include file="footer.jsp" %></small>
					</div>
				</div>
				
			</div>
			<!--/.col - Left-->
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<svg class="glyph stroked clipboard-with-paper">
							<use xlink:href="#stroked-clipboard-with-paper"></use>
						</svg>Family Member(s) Details</div>
					<div class="panel-body">
						<table class="table table-hover clearfix">
						    <thead>
						    <tr>
						        <th>Name</th>
						        <th>Personal ID</th>
					         	<th></th>
						    </tr>
						    </thead>
						    <tr>
							    <td><%= root.getFirstName() +" "+ root.getLastName() %> *</td>
							    <td><%= root.getFamilyID() %></td>
						      	<td> <i id="<%= root.getFamilyID() %>" class="fa fa-edit cursror-pointer" onClick="modifyFamily(this)"></i></td>
						    </tr>
						   <%
					    	if (familyList!=null){
					    		for(int i=0; i<familyList.size();i++){
					    	%>
					        <tr>
					            <td><%= familyList.get(i).getFirstName()+"  "+familyList.get(i).getLastName()%></td>  
					            <td><%= familyList.get(i).getPersonalID() %></td>
			                 	<td> <i id="<%= familyList.get(i).getPersonalID() %>" class="fa fa-edit cursror-pointer" onClick="modifyFamily(this)"></i></td>  
					        </tr>
					      	<%
					      		}}
					      	%>
						</table>
					</div>
					
					<div class="panel-footer">
						<div class="input-group">
							<span class="input-group-btn">
								<a href="addFamily.jsp" class="btn btn-primary">Add Member</a>
							</span>
						</div>
					</div>
				</div>

			</div>
			<!--/.col-->
		</div>
		<!--/.row content msg-->
	</div>
	<!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script>
		$('#calendar').datepicker({
		});

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
		
		function modifyFamily(event){
			if(event.id !=null){
			
		  		window.location.href = "addFamily.jsp?famId="+event.id; 
			}
			
			
      
			  
		}
	</script>
</body>

</html>