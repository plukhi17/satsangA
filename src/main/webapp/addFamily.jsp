<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page errorPage="error.jsp" %>  
 
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Satsang America - Istavrity</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
  
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <!--Icons-->
    <script src="js/lumino.glyphs.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
    <script src="js/controller.js"></script>
</head>

<%
		String applciationFlow = OnlineSAConstants.PORTAL_USER;
		String phoneNo="";
		RootMDB root = (RootMDB)session.getAttribute("userBean");
		ArrayList<RootMDB>   rootList  = new ArrayList<RootMDB>();
	
	    if (request.getParameter("applicationFlow") != null && request.getParameter("applicationFlow").equalsIgnoreCase("adminFlow") ) {
	    	if(request.getParameter("phoneNo") != null) { 
	    		phoneNo=request.getParameter("phoneNo");
	    		applciationFlow= request.getParameter("applicationFlow");
	    	System.out.println("in addFamily applciationFlow :"+applciationFlow);
	    	
	     	rootList = (ArrayList<RootMDB>)session.getAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER);
	    	  for (RootMDB rootMdb : rootList) {
	    	       if(rootMdb.getPhoneNo().equalsIgnoreCase(phoneNo))
	    	          	root=rootMdb; 
	    	           session.setAttribute(OnlineSAConstants.USER_ROLE_SUPER_USER, root);
		     	 }
	    	}
	    	 System.out.println("in addFamily phoneNo: "+phoneNo);
	    }
	    else {
	    	//root = (RootMDB)session.getAttribute(OnlineSAConstants.PORTAL_USER);
	    	root = (RootMDB)session.getAttribute("userBean");
	    	System.out.println("else in addFamily applciationFlow :"+applciationFlow);
	     	if(request.getParameter("phoneNo") != null)
	     		System.out.println(" request else in addFamily phoneNo: "+phoneNo);
	     	else 
	     	   phoneNo=(String) root.getPhoneNo();
	     	  System.out.println(" root else in addFamily phoneNo: "+phoneNo);
	    }
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
            </div>

        </div>
        <!-- /.container-fluid -->
    </nav>
    <!--sidebar-->
    <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
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
			
			<!-- 
			<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down">
						<use xlink:href="#stroked-chevron-down"></use></svg></span>Admin
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
			 -->
				<%@include file="includeAdminMenu.jsp" %>
				
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
                    <a href="index.jsp">
                        <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
                    </a>
                </li>
                <li class="active">Register Family Member</li>
            </ol>
        </div>
        <!--/.row sub navigation-->

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Istavrity Family Registration</h1>
            </div>
        </div>
        <!--/.row sub header-->

        <div class="row">
            <div class="col-md-8">

                <div class="panel panel-default">
                    <!-- <div class="panel-heading" id="accordion">ISTAVRITY DEPOSIT FORM</div>-->
                    <div class="panel-body">
                        <form name = "familyRegistrationForm" class="form-horizontal">
                            <fieldset>
                                <!-- Member Name -->
                                <div class="form-group " background-color="lightblue"  >
                                    <label class="control-label col-md-12" for="txtFirstName">Name:</label>
                                    <div class="col-md-4">
                                    <%= root.getFirstName() +"  " +root.getLastName()%>       
                                    </div>
                                </div>

                                <!-- Member Family# -->
                                <div class="form-group ">
                                    <label class="control-label col-md-12" for="stFamiName:
                                    lyCode">Family Code:</label>
                                    <div class="col-md-4">
 										<%= root.getFamilyID()%>  
                                     </div>
                                </div>

                                <!-- Member Address -->
                                <div class="form-group">
                                    <label class="col-md-12 control-label" for="stSenderAdd">Current Address:</label>
                                    <div class="col-md-7 row-group">
                                           <%= root.getAddress().getAddressLine1()+ " "+ root.getAddress().getAddressLine2()%>
                                           <%= root.getAddress().getCity() + " "+ root.getAddress().getState()%>
                                           <%= root.getAddress().getCountry() + " "+ root.getAddress().getZipCode()%>  
                                    </div>
                                    <div class="col-md-5  row-group">
                                    </div>
                                </div>

                                <!-- Member Contact Detail -->
                                <div class="form-group ">
                                    <label class="control-label col-md-12">Contact Informations:</label>
                                    <div class="col-md-4">
                                            <%= root.getPhoneNo()%>
                                    </div>
                                    <div class="col-md-8 pull-right">
                                           <%= root.getEmail()%>
                                    </div>
                                </div>

                                
								 <div class="form-group ">
                                    <label class="control-label col-md-12" for="txtFirstName">Name:</label>
                                    <div class="col-md-4">
                                            <input id="txtFirstName" name="txtFirstName" type="text" style="text-transform:uppercase;" class="form-control" ng-model="txtFirstName" placeholder="First Name *" required>
                                    </div>
                                    
                                    <div class="col-md-4">
                                            <input id="txtMiddleName" name="txtMiddleName" type="text" style="text-transform:uppercase;" class="form-control" ng-model="txtMiddleName" placeholder="Middle Name">
                                    </div>
                                    <div class="col-md-4">
                                            <input id="txtLastName" name="txtLastName" type="text"  style="text-transform:uppercase;" class="form-control" ng-model="txtLastName" placeholder="Last Name *" required>
                                    </div>
                                </div>
                                    
                                  <!-- Ritwik Name input-->
                                <div class="form-group ">
                                    <label class=" col-md-12 control-label" for="txtRtFirstName">Ritwik's Details: <span ng-show="!chkNotInit"> * </span></label>
                                    <div class="col-md-5" ng-init="loadAllRitvik()">
                                            <input list="ritvikList" class="form-control" style="text-transform:uppercase;" placeholder="Select Ritvik"  ng-model="txtRitvikName" ng-required="!chkNotInit ">
											<datalist id="ritvikList">
											           <option ng-repeat="x1 in ritvikList" value="{{x1.firstName +' '+ x1.lastName +' | '+ x1.saID }}">{{x1.address}}</option>
							                 </datalist>
                                                
                                           
                                    </div>
                                    <div class="checkbox col-md-2">
                                        <label>
                                           <input id="chkIsDecessed" type="checkbox" ng-model="chkIsDecessed" value="Is Decessed" />Is Not Alive
                                        </label>
                                    </div>
                                     <div class="checkbox col-md-2">
                                        <label>
                                           <input id="chkNotInitiated" type="checkbox" ng-model="chkNotInit" value="nInitiated" />Not Initiated
                                        </label>
                                    </div>
                                </div>
                                
                                
                                <div class="form-group col-md-12">
                                    <input class="btn btn-primary" type="submit" ng-click="familyRegistrationForm.$valid && registerFamilyUser($event)" value="Add Member" ng-disabled="familyRegistrationForm.$invalid" />
                                    <input type="hidden" id="txtApplicationFlow" value="<%=applciationFlow%>" >
                                </div>

                            </fieldset>
                        </form>
                    </div>

                    <div class="panel-footer">
                        	<small class="text-muted"><%@include file="footer.jsp" %></small>
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
        
        function ShowPermanentAdd() {
            if ($("#chkIsCurrentAdd").is(':checked')) {
                $("#dvPermanentAdd").addClass("hidden");
            } else {
                $("#dvPermanentAdd").removeClass("hidden");
            }
        }
	</script>
	</div>
</body>

</html>