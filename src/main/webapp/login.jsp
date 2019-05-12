<!DOCTYPE html>
<%@ page errorPage="error.jsp" %>  

<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Satsang America - Login</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
  
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!--<link href="css/datepicker3.css" rel="stylesheet">-->
	<link href="css/styles.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="js/controller.js"></script>
	<script src="js/Phone.js"></script>
	<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

  <!-- 
  <link rel="shortcut icon" type="image/x-icon" href="webapp/images/favicon.ico" />
     -->
 </head>
<%
	/*
    response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
    session.setAttribute("userBean",null);
  */
%>
<body ng-app="onlineSA">

<div ng-controller="onlineSAController">
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">Sign in - Satsang America </div>
				<div class="panel-body">
                    <div id="dvErrAlert" class="alert alert-danger" style= "display:none">
					  <a class="close" href="#">×</a>
					  <p><strong>{{PostDataResponse}}</strong> 
					</div>
					<form name="loginForm">
						<fieldset>
                            <div class="form-group has-error">
                                <!--<label class="control-label" for="txtFamilyCode">User ID:</label>-->
                                 <input class="form-control" placeholder="SA Family ID / Phone Number" id="userName" name="userName" autofocus="" ng-model="userName"
                               required>
                            </div>
							<div class="form-group">
                                <!--<label class="control-label" for="txtFamilyCode">Password:</label>-->
								<input class="form-control" placeholder="Password" name="password" type="password" value="" ng-model="password" required>
							</div>
							<!-- <div class="checkbox">
								<label>
									<input name="remember" type="checkbox" value="Remember Me"> Remember Me
								</label>
							</div>
							 -->
							<input class="btn btn-primary" type="submit" ng-click="loginForm.$valid && validateUser($event)" value="Sign In" />
                            <a href="register.jsp" class="btn btn-primary pull-right">Register User</a>
                            
                              <a style="margin-left: 10px;"  href="forgotpswd.jsp">Forgot Password</a>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<!-- /.col-->
	</div>
	<!-- /.row -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	<script>
	
	function formatPhone(userName){
	  var phoneStr = userName.value,
	  s2 = (""+phoneStr).replace(/\D/g, ''),
	  m = s2.match(/^(\d{3})?[- ]??[\s]?(\d{3})?[\s]?(\d{4})?(.*)?$/);
	  phoneObj.value = (!m) ? null : "(" + m[1] + ") " + m[2] + "-" + m[3]+ "x" + m[4];
	}
	
	
	</script>
	
	</div>
</body>

</html>