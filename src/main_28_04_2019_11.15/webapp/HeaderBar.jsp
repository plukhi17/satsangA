<%@ page isELIgnored="false" language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">
    
    <title>My JSP 'HeaderBar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
	
	<script type="text/javascript">
		
	$(document).ready(function(){
		 $.ajax({url: "demo_test.txt", success: function(result){
		     
		    }});
	});
	
	</script>
  </head>
  
  <body>
  			<a class="navbar-brand" href="#"><span>Satsang America Ishtarghya Deposit System</span> </a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<svg class="glyph stroked male-user">
								<use xlink:href="#stroked-male-user"></use>
							</svg>${userBean.firstName} ${userBean.lastName}<span class="caret"></span></a>
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
									
									
									<h1></h1>											
							</li>
						</ul>
					</li>
				</ul>
			</div>

		</div>
		
		<!-- /.container-fluid -->
	</nav>
  </body>
</html>
