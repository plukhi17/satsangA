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

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="css/styles.css" rel="stylesheet">
    <script src="js/jquery-2.1.4.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="js/lumino.glyphs.js"></script>
    <script src="js/controller.js"></script>
    <script src="js/bootstrap-table.js"></script>
  <!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>

<%
	RootMDB root = (RootMDB)session.getAttribute("userBean");
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
<body ng-app="onlineSA">
<div ng-controller="onlineSAController">
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
							</svg><%=root.getUserRole()%> <span class="caret"></span></a>
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
				<a href="searchIsht.jsp">
					<svg class="glyph stroked clipboard with paper">
					<use xlink:href="#stroked-clipboard-with-paper"/></svg>
					Ishtavrity Transcations</a>
			</li>
			
			<% if(root.getUserType()!=null && root.getUserType().equalsIgnoreCase(OnlineSAConstants.ADMIN_USER)) { %>
			    <input id="userRole" name="userRole" type="hidden" value =<%= root.getUserRole()%> >
			    
			   
			    
			<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down">
						<use xlink:href="#stroked-chevron-down"></use></svg></span>
						
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a href="register.jsp">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Add Primary Member
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Add Family Member
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Search User
						</a>
					</li>
				</ul>
			</li>
			<% } %>
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
			<li class="active">Search</li> 
			</ol>
		</div>
		<!--/.row sub navigation-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Welcome <%= root.getFirstName() %> !!!</h1>
			</div>
		</div>
		<!--/.row sub header-->

		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading" id="accordion">
						<svg class="glyph stroked male-user">
							<use xlink:href="#stroked-male-user"></use>
						</svg> Search Member</div>
						
					<div class="alert " ng-show="mainAlert.isShown">
					  <button type="button" class="close" ng-click="closeAlert()" aria-hidden="true">&times;</button>
					</div>	
					<div class="panel-body">
					<div id="dvErrAlert" class="alert alert-danger" style= "display:none" class="col-md-12">
						  <a class="close" href="#">×</a>
						  <p><strong>{{PostDataResponse}}</strong> 
					 	</div>
					 	
					 	
					  <div class="form-group ">
					  <form name = "registrationForm"  class="form-horizontal">
                   <fieldset>
                    <div class="col-md-2">
      				<label for="lPhno"> Phone No :</label> <br>
      					  <input type="number" id="phoneNo" name="phoneNo" placeholder="(123)456-789"  ng-model="phoneNo" class="form-control" >
      					  <input id="applicationFlow" name="applicationFlow" type="hidden" value = "adminFlow" >
      				</div>
      		
           			<div class="col-md-2">
      					<label for="pwd">Family Code :</label> <br>
     		  			<input type="text" id="familyCode" name="familyCode" placeholder="SA Family Code" ng-model="familyCode" class="form-control" >
    				</div>
    	
			    	<div class="col-md-2">
      					<label for="lFirstName"> First Name :</label> <br>
      				    <input type="text"id="firstName" name="firstName" placeholder="First Name"  ng-model="firstName" class="form-control" >
    				</div>
    	
    				<div class="col-md-2">
      					<label for="lLastNAme">Last Name :</label> <br>
      					<input type="text" id="lastName" name="lastName" placeholder="Last Name" ng-model="lastName" class="form-control" >
    				</div>
    	
    	  			<div class="col-md-2">
    	  				<label for="lLastNAme">Search  :</label> <br>
              			<button type="submit" class="btn btn-primary" ng-click="searchUser($event)" title="Search" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-search"></span></button> 
          			</div>
                    
                          
                        <!--   
                          <div class="col-md-8">
                                   <input id="phoneNo" name="phoneNo" placeholder="+1(123)456-789" type="number" ng-model="phoneNo" class="form-control" required>
                                   <input id="applicationFlow" name="applicationFlow" type="hidden" value = "adminFlow" >
                          </div>
                          <div class="col-md-4 pull-right">
                          	<button type="submit" class="btn btn-primary" ng-click="searchUser($event)" title="Search" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-search"></span></button> 
                          </div>
                           -->
                          </fieldset>
                         </form> 
                      </div>
                    </div>
                    
                    
                    
					<div class="panel-body">
					 <!-- Sample Table -->
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <table id="tblIshtTran" 
                                                       data-show-refresh="false"
                                                       data-show-toggle="false"
                                                       data-show-columns="false"
                                                       data-show-export="false"
                                                       data-detail-view="false"
                                                       data-minimum-count-columns="2"
                                                       data-show-pagination-switch="false"
                                                       data-pagination="false"
                                                       data-id-field="id"
                                                       data-show-footer="false">
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
					
					<div class="panel-footer">
					  
                    </div>
					</div>
					
			<!--/.col-->
		</div>
		<!--/.row content msg-->
	</div>
	<!--/.main-->
</div></div></div>
	<script>
		/* $('#calendar').datepicker({
		}); */

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