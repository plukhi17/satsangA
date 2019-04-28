<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  
    
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <title>Satsang America - Registration</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <!--Icons-->
    <script src="js/lumino.glyphs.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
    <script src="js/controller.js"></script>
</head>

<body ng-app="onlineSA">

		<%
			RootMDB root = new RootMDB();
			String applciationFlow  = OnlineSAConstants.PORTAL_USER;
			if(session.getAttribute("userBean")!=null){
			root = (RootMDB)session.getAttribute("userBean");
		    if (request.getParameter("applicationFlow") != null) {
		        applciationFlow = request.getParameter("applicationFlow");
		    	}
		    }
		%>
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
            </div>

        </div>
        <!-- /.container-fluid -->
    </nav>
    <!--sidebar-->
    <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
        <ul class="nav menu">
            <li class="active">
                <a href="register.jsp">
                    <svg class="glyph stroked male-user">
                        <use xlink:href="#stroked-male-user"></use>
                    </svg>
                    Register New User</a>
            </li>
            
			<% if(root.getUserType()!=null && root.getUserType().equalsIgnoreCase(OnlineSAConstants.ADMIN_USER)) { %>
            <li>
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
				<a href="login.html">
					<svg class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use>
					</svg> Logout</a>
			</li>
			<% } %>
			
        </ul>

    </div>
    <!--/.sidebar-->

    <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
        <div class="row">
            <ol class="breadcrumb">
                <li>
                    <a href="index.jsp">
                        <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
                    </a>
                </li>
                <li class="active">Register User</li>
            </ol>
        </div>
        <!--/.row sub navigation-->

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Istavrity Registration</h1>
            </div>
        </div>
        <!--/.row sub header-->

        <div class="row">
            <div class="col-md-8">

                <div class="panel panel-default">
                    <!-- <div class="panel-heading" id="accordion">ISTAVRITY DEPOSIT FORM</div>-->
                    <div class="panel-body">
                    <div id="dvErrAlert" class="alert alert-danger" style= "display:none">
					  <a class="close" href="#">×</a>
					  <p><strong>{{PostDataResponse}}</strong> 
					 </div>
                        <form name = "registrationForm" class="form-horizontal">
                            <fieldset>
                                <!-- Member Name -->
                                <div class="form-group ">
                                    <label class="control-label col-md-12" for="txtFirstName">Name:</label>
                                    <div class="col-md-4">
                                            <input id="txtFirstName" name="txtFirstName" type="text" class="form-control" ng-model="txtFirstName" placeholder="First Name" required>
                                    </div>
                                    <div role="alert">
      									<span class="error" ng-show="myForm.userName.$error.required">
       									Required!</span>
    								</div>
                                    <div class="col-md-4">
                                            <input id="txtMiddleName" name="txtMiddleName" type="text" class="form-control" ng-model="txtMiddleName" placeholder="Middle Name">
                                    </div>
                                    <div class="col-md-4">
                                            <input id="txtLastName" name="txtLastName" type="text" class="form-control" ng-model="txtLastName" placeholder="Last Name" required>
                                            <input id="txtApplicationFlow" name="txtApplicationFlow" type="hidden" value =<%= applciationFlow %>>
                                    </div>
                                </div>

                                <!-- Member Family# -->
                                <div class="form-group ">
                                    <label class="control-label col-md-12" for="stFamilyCode">India Family Code:</label>
                                    <div class="col-md-4">
 										<input id="stFamilyCode" name="stFamilyCode" placeholder="XXX-XX-XXX" type="text" ng-model ="stFamilyCode" class="form-control" >
                                     </div>
                                </div>

                                <!-- Member Address -->
                                <div class="form-group">
                                    <label class="col-md-12 control-label" for="stSenderAdd">Current Address:</label>
                                    <div class="col-md-7 row-group">
                                           <input id="stSenderAdd1" name="stSenderAdd" placeholder="Street Address" type="text" ng-model="stSenderAdd1" class="form-control" required>
                                    </div>
                                    <div class="col-md-5  row-group">
                                            <input id="stSenderAdd2" name="stSenderAdd2" placeholder="Apt. No., PO Boxes" type="text" ng-model="stSenderAdd2" class="form-control" required>
                                    </div>
                                    <!-- Member Location -->
                                    <!--<div class="col-md-12 row-group">-->
									
									<!-- Country -->
                                        <div class="col-md-3">
                                            <!--<label class="control-label" for="stCountry">Country:</label>-->
                                            <select name='country' id="country" class="form-control" ng-model="country" ng-options="country as country.CountryName for country in countries" required>
                							<option value="" disabled>Select Country</option>
            							</select>
                                        </div>
                                    <!--</div>-->
                                        
                                        <!-- State-->
                                        <div class="col-md-3">
                                        <select name='state' required id="state" class="form-control" ng-disabled="states.length == 0" ng-model="state" ng-options="state as state.StateName for state in states">
							                <option value="" disabled>Select State</option>
							            </select>
                                            
                                        </div>

										<!-- City -->
                                        <div class="col-md-3">
                                            <!--<label class="control-label" for="stSendercity">City:</label>-->
                                            <input id="stSenderCity" name="stSenderCity" placeholder="City" type="text" ng-model ="stSenderCity" class="form-control" required>
                                        </div>

                                        <!-- Zip Code -->
                                        <div class="col-md-3 col-xs-2">
                                            <!--<label class="control-label" for="stZipCode">Zip Code:</label>-->
                                            <input id="stZipCode" name="stZipCode" placeholder="1234" type="text" ng-model = "stZipCode" class="form-control" required>
                                        </div>
                                    
                                </div>

                                <!-- Member Contact Detail -->
                                <div class="form-group ">
                                    <label class="control-label col-md-12">Contact Informations:</label>
                                    <div class="col-md-4">
                                             <input id="txtPhoneNo" name="txtPhoneNo" placeholder="+1(123)456-789" type="number" ng-model="txtPhoneNo" class="form-control" required>
                                    </div>
                                    <div class="col-md-8 pull-right">
                                           <input id="txtEmailId" name="txtEmailId" placeholder="someid@mail.com" type="email" ng-model = "txtEmailId" class="form-control" required>
                                    </div>
                                </div>

                                <!-- Ritwik Name input-->
                                <div class="form-group ">
                                    <label class=" col-md-12 control-label" for="txtRtFirstName">Ritwik's Details:</label>
                                    <div class="col-md-5" ng-init="loadAllRitvik()">
                                            <input list="ritvikList" class="form-control" placeholder="Select Ritvik"  ng-model="txtRitvikName">
											<datalist id="ritvikList">
											           <option ng-repeat="x1 in ritvikList" value="{{x1.firstName +' '+ x1.lastName}}">{{x1.address}}</option>
											</datalist>
                                    </div>
                                    <div class="checkbox col-md-2">
                                        <label>
                                           <input id="chkIsDecessed" type="checkbox" ng-model="chkIsDecessed" value="Is Decessed" />Is Not Alive
                                        </label>
                                    </div>
                                </div>
									
                                <!-- Login Details -->
                                <div class="form-group col-md-6">
                                    <label class="control-label" for="stUserName">User ID (Same as your Contact Information): </label>{{txtPhoneNo}}<br><br>
                                   
                                    <label class="control-label" for="stPassword">Create Password:</label>
                                    <div class="row-group">
                                         <input id="stPassword" name="stPassword" placeholder="*******" type="password" ng-model="stPassword"  type="password" class="form-control" required>
                                    </div>
                                </div>

                                <div class="form-group col-md-12">
                                    <input class="btn btn-primary" type="submit" ng-click="registrationForm.$valid && registerUser($event)" value="Register" ng-disabled="registrationForm.$invalid" /> 
                                </div>

                            </fieldset>
                        </form>
                    </div>

                    <div class="panel-footer">
                        <small class="text-muted">Satsang America</small>
                    </div>
                </div>

            </div>
            <!--/.col - Left-->
        </div>
        <!--/.row content msg-->
    </div>
    <!--/.main-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap-table.js"></script>
    <script>
        !function ($) {
            $(document).on("click", "ul.nav li.parent > a > span.icon", function () {
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

        $(".checkbox").on("click", function () {
            var $checkbox = $(this).find(':checkbox');
            $checkbox.attr('checked', !$checkbox.attr('checked'));
        });

        var dtData = [{
            "slno": 0, "name": "Name 0", "rtwkname": "Ritwik 1", "swastyayani": 5, "istavrity": 10, "acharyavrity": 2, "dakshina": 14,
            "sangathani": 3, "pronami": 7, "surplus": 10, "ritwiki": 2
        }, {
            "slno": 0, "name": "Name 1", "rtwkname": "Ritwik 2", "swastyayani":
            5, "istavrity": 10, "acharyavrity": 2, "dakshina": 14, "sangathani": 3, "pronami": 7, "surplus": 10, "ritwiki": 2
        }];
        $('#tblIstavrity').bootstrapTable({ data: dtData });
        
        
	</script>
	</div>
</body>

</html>