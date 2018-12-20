<%@page import="com.olsa.pojo.FamilyMDB"%>
<%@page import="java.util.List"%>
<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@ page errorPage="error.jsp" %>  
<%@ page session="true" %>

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

<body>
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
		<!-- /.container-fluid -->
	</nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
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
				<a href="payisht.html">
					<svg class="glyph stroked app-window-with-content">
						<use xlink:href="#stroked-app-window-with-content"></use>
					</svg> Pay Ishtavrity</a>
			</li>
			
				<%@include file="includeAdminMenu.jsp" %>
				
		 <!-- 			
			<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down">
						<use xlink:href="#stroked-chevron-down"></use></svg></span>Admin
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Sub Item 1
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Sub Item 2
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Sub Item 3
						</a>
					</li>
				</ul>
			</li>
			
			-->
			
			<li role="presentation" class="divider"></li>
			<li>
				<a href="login.html">
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
				<li class="active">Confirmation</li>
			</ol>
		</div>
		<!--/.row sub navigation-->



		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Your family member data has been updated successfully!!!</h1>
			</div>
		</div>
		<!--/.row sub header-->

		<div class="row">
	<div class="col-md-8">
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
						    </tr>
						    </thead>
					    	<%
					    	if (familyList!=null){
					    		for(int i=0; i<familyList.size();i++){
					    	%>
					        <tr>
					            <td><%= familyList.get(i).getFirstName()+"  "+familyList.get(i).getLastName()%></td>  
					            <td><%= familyList.get(i).getPersonalID() %></td>  
					        </tr>
					      	<%
					      		}}
					      	%>
						</table>
					</div>

					
					<div class="panel-footer">
						<small class="text-muted">Satsang America</small>
					</div>
				</div>
			</div>
			<!--/.col - Left-->

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
	</script>
</body>

</html>