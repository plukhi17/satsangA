<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@ page errorPage="error.jsp" %>  

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Satsang America - Istavrity</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--<link href="css/datepicker3.css" rel="stylesheet">-->
    <link href="css/styles.css" rel="stylesheet">
     <link href="css/apps.css" rel="stylesheet">
    <!--<link href="css/bootstrap-table.css" rel="stylesheet">-->
    <!--Icons-->
    <script src="js/lumino.glyphs.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="js/ledger.js"></script>

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
<body ng-app="onlineSA" ng-controller="onlineSAController">
	<%-- <%
		RootMDB root = (RootMDB)session.getAttribute("userBean");
	%>
	 --%>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    
        <div class="container-fluid" ng-init="loadLegder()">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><span>Satsang America</span> Ishtavrity</a>
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
                                    </svg>
                                    Profile</a>
                            </li>
                            <li>
                                <a href="#">
                                    <svg class="glyph stroked gear">
                                        <use xlink:href="#stroked-gear"></use>
                                    </svg>
                                    Settings</a>
                            </li>
                            <li>
                                <a href="login.jsp">
                                    <svg class="glyph stroked cancel">
                                        <use xlink:href="#stroked-cancel"></use>
                                    </svg>
                                    Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
        <!-- /.container-fluid -->
    </nav>
     <!--sidebar-->
    <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
        <ul class="nav menu">
            <li>
                <a href="index.jsp">
                    <svg class="glyph stroked dashboard-dial">
                        <use xlink:href="#stroked-dashboard-dial"></use>
                    </svg>
                    Dashboard</a>
            </li>
            <li>
                <a href="payisht.jsp">
                    <svg class="glyph stroked app-window-with-content">
                        <use xlink:href="#stroked-app-window-with-content"></use>
                    </svg>
                    Pay Ishtavrity</a>
            </li>
            
            <li class="active">
				<a href="searchIsht.jsp">
					<svg class="glyph stroked app-window-with-content">
						<use xlink:href="#stroked-app-window-with-content"></use>
					</svg> Search Ishtavrity</a>
			</li>
			<li><%@include file="includeAdminMenu.jsp" %></li>
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
                <li class="active">Ledger BalanceSheet</li>
            </ol>
        </div>
        <!--/.row sub navigation-->

        <!--<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Ishtavrity Deposit Form</h1>
            </div>
            
        </div>-->
        <!--/.row sub header-->

        <div class="row">
            <div class="col-md-12">
           
           
           	<input type="hidden" id="phoneNo" value = <%= root.getPhoneNo()%> name="phoneNo" placeholder="your phone no" ><br>
                <div class="panel panel-default">
                    <div class="panel-heading" id="accordion">
                       <div class="col-md-9">
                       		Balance Sheet
                       	</div>
                       	 <div class="col-md-3 cAlign-right">
                			<button class="btn btn-info btn-parmentForm "  ng-click="addCode()" > 
                					<span ng-if="codeBtn == 'Code'">
                						<i class="fa fa-plus"></i>
                					</span> {{codeBtn}} 
                			</button>
                				                       	</div>
                       </div>
                    <div class="panel-body">
                     
                        
                                <!-- Sample Table -->
                                <div class="form-group">
                                    <div class="col-md-12 ledgerWrapper">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <div id="toolbar">
                                                    <!--<button id="remove" class="btn btn-danger" disabled>
                                                        <i class="glyphicon glyphicon-remove"></i> Delete
                                                    </button>-->
                                                </div>
                                                <table id="tblLedger" 
                                                       data-toolbar="#toolbar"
                                                       data-search="true"
                                                       data-show-refresh="false"
                                                       data-show-toggle="false"
                                                       data-show-columns="true"
                                                       data-show-export="false"
                                                       data-detail-view="false"
                                                       data-minimum-count-columns="2"
                                                       data-show-pagination-switch="false"
                                                       data-pagination="true"
                                                       data-id-field="id"
                                                       data-show-footer="false">
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    
            
								<!-- Modal -->
								<div id="myModal" class="codeWrapper col-md-12"  >
								   
								    <div class="codeWrapper-body">
								    <div class="col-md-1">
								    </div>
								    	<div class="col-md-3">
							    		  <form id="addCodeFrm">
							    			 <div class="form-group">
												<label for="code">Code</label>
											    <input type="text"  ng-model="code" class="form-control" id="code" placeholder="Enter Code" name="code">
											  </div>
											  <div class="form-group">
											    <label for="codeDesc">Description</label>
											    <input type="text"  ng-model="codeDesc" class="form-control" id="codeDesc" placeholder="Enter Code Description" name="codeDesc">
											  </div>
											  <button type="button" id="addCodeBtn" class="btn btn-default" ng-click="addCodeFun()">Add</button>
											  </form>
								    	</div>
								    	 <div class="col-md-3">
								   		 </div>
								    	<div class="col-md-3">
								    	 <form id="addSubFrm">
								    	 	  <div class="form-group">
								    	 	  	<label for="cd">Select Code</label>
												  <select name='cd' required id="cd" style="text-transform:uppercase;" class="form-control input-sm"  ng-model="selectedCd"  placeholder="Select Code" ng-options="code as code.CodeName for code in allCodes">
							                			<option value="" selected>Select Code</option>
							                    </select>
											   
											  </div>
								    		  <div class="form-group">
												<label for="subCode">SubCode</label>
											    <input type="text" class="form-control" id="subCode" placeholder="Enter SubCode" name="subCode">
											  </div>
											  <div class="form-group">
											    <label for="subCodeDesc">Description</label>
											    <input type="text" class="form-control" id="subCodeDesc" placeholder="Enter SubCode Description" name="subCodeDesc">
											  </div>
											  <button type="submit" id="addSubCodeBtn" class="btn btn-default">Add</button>
											</form>
								    	</div>
							      	<div class="col-md-1">
								    </div>
										
											
										</div>
								    </div>
								    <div class="codeWrapper-footer">
								        <button class="btn" aria-hidden="true">Close</button>
								        <button class="btn btn-primary">Save changes</button>
								    </div>
								</div>
								<!-- Modal -->
            	
                                </div>
                       
                    </div>
                    
                    <div class="panel-footer">
                        <small class="text-muted">Satsang America,Inc </small>
                    </div>
                </div>

            </div>
            <!--/.col - Left-->
            
           
           
        </div>
        <!--/.row content msg-->
    </div>
    <!--/.main-->
    



    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-table.js"></script>
  
</body>

</html>
