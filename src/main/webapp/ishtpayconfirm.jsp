<%@page import="com.olsa.pojo.IshtMDB"%>
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

	<!--Icons-->
	<script src="js/lumino.glyphs.js"></script>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
	
	
</head>

<%
	RootMDB root = (RootMDB)session.getAttribute("userBean");
	IshtMDB ishtPay = (IshtMDB)session.getAttribute("ishtPay");
	
%>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

    if(session.getAttribute("userBean")==null){
   		response.sendRedirect(request.getContextPath() + "/");
	}
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
							</svg> <%=ishtPay.getName() %> <span class="caret"></span></a>
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
				<li class="active">Confirmation</li>
			</ol>
		</div>
		<!--/.row sub navigation-->



		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Confirmation</h1>
			</div>
		</div>
		<!--/.row sub header-->

		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">
						<svg class="glyph stroked empty-message">
							<use xlink:href="#stroked-empty-message"></use>
						</svg> Hello <%= ishtPay.getName() %> your ISTARGHYA details submission is successful.</div>
					<div class="panel-body">
						<div class="chat-body clearfix">
							<p>We recommend you to keep the below copy of transaction ref# for your records. The Arghya Praswasti (receipt) will be sent shortly to your email Id ,once your oblation has been verified and approved by Admin.</p>
						</div>
					</div>

					<div class="panel-footer">
						<div class="chat-body clearfix">
							<p><b>Confirmation</p>
							<table class="table table-hover clearfix">
						    <tbody>
								<tr>
									<td>Name </td>
									<td><%=ishtPay.getName() %></td>
									</tr>
									<tr>
									<td>Family Code</td>
									<td><%= ishtPay.getFamilyID()%></td>
									</tr>
									<tr>
									<td>User ID / Contact Information</td>
									<td><%= ishtPay.getPhoneNo() %> </td>
									</tr>
									<tr>
									<td>Receipt No</td>
									<td><%= ishtPay.getReceiptNo() %>
										<p id="transactionIdSuccess" style="padding: 0"></p>
										
									 </td>
									</tr>
									<tr>
										<td>Amount</td>
										<td>
											<p id="GradTotalAmount"> <%= ishtPay.getTotal() %></p>
										<!-- 	<script type="text/javascript">
											document.getElementById("GradTotalAmount").innerHTML = sessionStorage.getItem("GradTotalAmount");
											</script> -->
										</td>
									</tr>
							</tbody>
						</table>
							<div class="form-group col-md-12">
														<input class="btn btn-primary" id="goPayHome" rel="modal:open" type="button"
															value="Transactions"
															onclick="goTOTransactionLIst()" />


													</div>
						</div>
						
												
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
		
		function goTOTransactionLIst() {
	 window.location = 'searchIsht.jsp';
}
	</script>
</body>

</html>