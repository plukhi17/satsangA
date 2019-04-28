

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'includeAdminMenu' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 		<%
		//RootMDB root = (RootMDB)session.getAttribute("userBean");
		System.out.println("Admin  Page -"+ root.getUserType());
      	%>
	
			<% if(root.getUserType()!=null && root.getUserType().equalsIgnoreCase(OnlineSAConstants.ADMIN_USER)) { %>
					
			<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down">
						<use xlink:href="#stroked-chevron-down"></use></svg></span>Admin
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a href="register.jsp?applicationFlow=adminFlow">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Add Primary Member
						</a>
					</li>
				<!-- 	<li>
						<a class="" href="addFamily.jsp">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Add Family Member
						</a>
					</li>
					-->
					<li>
						<a href="search.jsp">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Search User
						</a>
					</li>
					
					<li>
						<a href="ishtTrnDet.jsp">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Transaction Details
						</a>
					</li>
	
					<li>
						<a href="ledger.jsp">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Ishtavrity Ledger	</a>
					</li>
					
					<li>
						<a href="reportmech.jsp">
							<svg class="glyph stroked chevron-right">
								<use xlink:href="#stroked-chevron-right"></use>
							</svg> Collection Report</a>
					</li>
					
					<!-- -
					<li>
						<a href="ledger.jsp">
							<svg class="glyph stroked chevron-right">
							<use xlink:href="#stroked-chevron-right"/> 
							Ishtavrity Ledger
							</svg>
							</a>
					</li>
					
				<li>
					<a href="reportmech.jsp">
					<svg class="glyph stroked chevron-right">
					<use xlink:href="##stroked-chevron-right"/>
         			Report
         			</svg>
       			 </a>
			  </li>
			   -->
							
				</ul>
			</li>
			
			<% } %>
  </body>
</html>
