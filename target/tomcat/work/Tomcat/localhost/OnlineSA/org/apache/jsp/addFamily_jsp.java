/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-12-05 05:37:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.olsa.utility.OnlineSAConstants;
import com.olsa.pojo.RootMDB;
import java.util.ArrayList;

public final class addFamily_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/includeAdminMenu.jsp", Long.valueOf(1529096318889L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("   \n");
      out.write(" \n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <title>Satsang America - Istavrity</title>\n");
      out.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\">\n");
      out.write("  \n");
      out.write("    <link href=\"css/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"css/styles.css\" rel=\"stylesheet\">\n");
      out.write("    <!--Icons-->\n");
      out.write("    <script src=\"js/lumino.glyphs.js\"></script>\n");
      out.write("\t<script src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js\"></script>\n");
      out.write("    <script src=\"js/controller.js\"></script>\n");
      out.write("</head>\n");
      out.write("\n");

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
	
      out.write('\n');

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

    if(session.getAttribute("userBean")==null){
   		response.sendRedirect(request.getContextPath() + "/");
	}

      out.write("\n");
      out.write("<body ng-app=\"onlineSA\">\n");
      out.write("   <div ng-controller=\"onlineSAController\">\n");
      out.write("    <nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("            <div class=\"navbar-header\">\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#sidebar-collapse\">\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </button>\n");
      out.write("                <a class=\"navbar-brand\" href=\"#\"><span>Satsang America</span>Ishtavrity</a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <!-- /.container-fluid -->\n");
      out.write("    </nav>\n");
      out.write("    <!--sidebar-->\n");
      out.write("    <div id=\"sidebar-collapse\" class=\"col-sm-3 col-lg-2 sidebar\">\n");
      out.write("\t<ul class=\"nav menu\">\n");
      out.write("\t\t\t<li class=\"active\">\n");
      out.write("\t\t\t\t<a href=\"index.jsp\">\n");
      out.write("\t\t\t\t\t<svg class=\"glyph stroked dashboard-dial\">\n");
      out.write("\t\t\t\t\t\t<use xlink:href=\"#stroked-dashboard-dial\"></use>\n");
      out.write("\t\t\t\t\t</svg> Dashboard</a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"payisht.jsp\">\n");
      out.write("\t\t\t\t\t<svg class=\"glyph stroked app-window-with-content\">\n");
      out.write("\t\t\t\t\t\t<use xlink:href=\"#stroked-app-window-with-content\"></use>\n");
      out.write("\t\t\t\t\t</svg> Pay Ishtavrity</a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<!-- \n");
      out.write("\t\t\t<li class=\"parent \">\n");
      out.write("\t\t\t\t<a href=\"#\">\n");
      out.write("\t\t\t\t\t<span data-toggle=\"collapse\" href=\"#sub-item-1\"><svg class=\"glyph stroked chevron-down\">\n");
      out.write("\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-down\"></use></svg></span>Admin\n");
      out.write("\t\t\t\t</a>\n");
      out.write("\t\t\t\t<ul class=\"children collapse\" id=\"sub-item-1\">\n");
      out.write("\t\t\t\t\t<li>\n");
      out.write("\t\t\t\t\t\t<a href=\"register.jsp\">\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\n");
      out.write("\t\t\t\t\t\t\t</svg> Add Primary Member\n");
      out.write("\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t<li>\n");
      out.write("\t\t\t\t\t\t<a class=\"\" href=\"#\">\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\n");
      out.write("\t\t\t\t\t\t\t</svg> Add Family Member\n");
      out.write("\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t<li>\n");
      out.write("\t\t\t\t\t\t<a class=\"\" href=\"#\">\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\n");
      out.write("\t\t\t\t\t\t\t</svg> Search User\n");
      out.write("\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t -->\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>My JSP 'includeAdminMenu' starting page</title>\r\n");
      out.write("    \r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t<!--\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n");
      out.write("\t-->\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("  \t\r\n");
      out.write("\t\t");

		//RootMDB root = (RootMDB)session.getAttribute("userBean");
	
		System.out.println("Admin  Page -"+ root.getUserType());
      	
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t");
 if(root.getUserType()!=null && root.getUserType().equalsIgnoreCase(OnlineSAConstants.ADMIN_USER)) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t<li class=\"parent \">\r\n");
      out.write("\t\t\t\t<a href=\"#\">\r\n");
      out.write("\t\t\t\t\t<span data-toggle=\"collapse\" href=\"#sub-item-1\"><svg class=\"glyph stroked chevron-down\">\r\n");
      out.write("\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-down\"></use></svg></span>Admin\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<ul class=\"children collapse\" id=\"sub-item-1\">\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"register.jsp?applicationFlow=adminFlow\">\r\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\r\n");
      out.write("\t\t\t\t\t\t\t</svg> Add Primary Member\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<!-- \t<li>\r\n");
      out.write("\t\t\t\t\t\t<a class=\"\" href=\"addFamily.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\r\n");
      out.write("\t\t\t\t\t\t\t</svg> Add Family Member\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t-->\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"search.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\r\n");
      out.write("\t\t\t\t\t\t\t</svg> Search User\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"ishtTrnDet.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<svg class=\"glyph stroked chevron-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<use xlink:href=\"#stroked-chevron-right\"></use>\r\n");
      out.write("\t\t\t\t\t\t\t</svg> Transaction Details\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t");
 } 
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
      out.write("\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t<li role=\"presentation\" class=\"divider\"></li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"login.jsp\">\n");
      out.write("\t\t\t\t\t<svg class=\"glyph stroked male-user\">\n");
      out.write("\t\t\t\t\t\t<use xlink:href=\"#stroked-male-user\"></use>\n");
      out.write("\t\t\t\t\t</svg> Logout</a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <!--/.sidebar-->\n");
      out.write("\n");
      out.write("    <div class=\"col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main\">\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <ol class=\"breadcrumb\">\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"index.jsp\">\n");
      out.write("                        <svg class=\"glyph stroked home\">\n");
      out.write("                            <use xlink:href=\"#stroked-home\"></use>\n");
      out.write("                        </svg>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"active\">Register Family Member</li>\n");
      out.write("            </ol>\n");
      out.write("        </div>\n");
      out.write("        <!--/.row sub navigation-->\n");
      out.write("\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-lg-12\">\n");
      out.write("                <h1 class=\"page-header\">Istavrity Family Registration</h1>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!--/.row sub header-->\n");
      out.write("\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-md-8\">\n");
      out.write("\n");
      out.write("                <div class=\"panel panel-default\">\n");
      out.write("                    <!-- <div class=\"panel-heading\" id=\"accordion\">ISTAVRITY DEPOSIT FORM</div>-->\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <form name = \"familyRegistrationForm\" class=\"form-horizontal\">\n");
      out.write("                            <fieldset>\n");
      out.write("                                <!-- Member Name -->\n");
      out.write("                                <div class=\"form-group \" background-color=\"lightblue\"  >\n");
      out.write("                                    <label class=\"control-label col-md-12\" for=\"txtFirstName\">Name:</label>\n");
      out.write("                                    <div class=\"col-md-4\">\n");
      out.write("                                    ");
      out.print( root.getFirstName() +"  " +root.getLastName());
      out.write("       \n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <!-- Member Family# -->\n");
      out.write("                                <div class=\"form-group \">\n");
      out.write("                                    <label class=\"control-label col-md-12\" for=\"stFamilyCode\">Family Code:</label>\n");
      out.write("                                    <div class=\"col-md-4\">\n");
      out.write(" \t\t\t\t\t\t\t\t\t\t");
      out.print( root.getFamilyID());
      out.write("  \n");
      out.write("                                     </div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <!-- Member Address -->\n");
      out.write("                                <div class=\"form-group\">\n");
      out.write("                                    <label class=\"col-md-12 control-label\" for=\"stSenderAdd\">Current Address:</label>\n");
      out.write("                                    <div class=\"col-md-7 row-group\">\n");
      out.write("                                           ");
      out.print( root.getAddress().getAddressLine1()+ " "+ root.getAddress().getAddressLine2());
      out.write("\n");
      out.write("                                           ");
      out.print( root.getAddress().getCity() + " "+ root.getAddress().getState());
      out.write("\n");
      out.write("                                           ");
      out.print( root.getAddress().getCountry() + " "+ root.getAddress().getZipCode());
      out.write("  \n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-md-5  row-group\">\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                <!-- Member Contact Detail -->\n");
      out.write("                                <div class=\"form-group \">\n");
      out.write("                                    <label class=\"control-label col-md-12\">Contact Informations:</label>\n");
      out.write("                                    <div class=\"col-md-4\">\n");
      out.write("                                            ");
      out.print( root.getPhoneNo());
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-md-8 pull-right\">\n");
      out.write("                                           ");
      out.print( root.getEmail());
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                                \n");
      out.write("\t\t\t\t\t\t\t\t <div class=\"form-group \">\n");
      out.write("                                    <label class=\"control-label col-md-12\" for=\"txtFirstName\">Name:</label>\n");
      out.write("                                    <div class=\"col-md-4\">\n");
      out.write("                                            <input id=\"txtFirstName\" name=\"txtFirstName\" type=\"text\" style=\"text-transform:uppercase;\" class=\"form-control\" ng-model=\"txtFirstName\" placeholder=\"First Name\" required>\n");
      out.write("                                    </div>\n");
      out.write("                                    \n");
      out.write("                                    <div class=\"col-md-4\">\n");
      out.write("                                            <input id=\"txtMiddleName\" name=\"txtMiddleName\" type=\"text\" style=\"text-transform:uppercase;\" class=\"form-control\" ng-model=\"txtMiddleName\" placeholder=\"Middle Name\">\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"col-md-4\">\n");
      out.write("                                            <input id=\"txtLastName\" name=\"txtLastName\" type=\"text\"  style=\"text-transform:uppercase;\" class=\"form-control\" ng-model=\"txtLastName\" placeholder=\"Last Name\" required>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                    \n");
      out.write("                                  <!-- Ritwik Name input-->\n");
      out.write("                                <div class=\"form-group \">\n");
      out.write("                                    <label class=\" col-md-12 control-label\" for=\"txtRtFirstName\">Ritwik's Details:</label>\n");
      out.write("                                    <div class=\"col-md-5\" ng-init=\"loadAllRitvik()\">\n");
      out.write("                                            <input list=\"ritvikList\" class=\"form-control\" style=\"text-transform:uppercase;\" placeholder=\"Select Ritvik\"  ng-model=\"txtRitvikName\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<datalist id=\"ritvikList\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t           <option ng-repeat=\"x1 in ritvikList\" value=\"{{x1.firstName +' '+ x1.lastName +' | '+ x1.saID }}\">{{x1.address}}</option>\n");
      out.write("\t\t\t\t\t\t\t                 </datalist>\n");
      out.write("                                                \n");
      out.write("                                           \n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"checkbox col-md-2\">\n");
      out.write("                                        <label>\n");
      out.write("                                           <input id=\"chkIsDecessed\" type=\"checkbox\" ng-model=\"chkIsDecessed\" value=\"Is Decessed\" />Is Not Alive\n");
      out.write("                                        </label>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                \n");
      out.write("                                \n");
      out.write("                                <div class=\"form-group col-md-12\">\n");
      out.write("                                    <input class=\"btn btn-primary\" type=\"submit\" ng-click=\"familyRegistrationForm.$valid && registerFamilyUser($event)\" value=\"Add Member\" ng-disabled=\"familyRegistrationForm.$invalid\" />\n");
      out.write("                                    <input type=\"hidden\" id=\"txtApplicationFlow\" value=\"");
      out.print(applciationFlow);
      out.write("\" >\n");
      out.write("                                </div>\n");
      out.write("\n");
      out.write("                            </fieldset>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"panel-footer\">\n");
      out.write("                        <small class=\"text-muted\">Satsang America</small>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <!--/.col - Left-->\n");
      out.write("        </div>\n");
      out.write("        <!--/.row content msg-->\n");
      out.write("    </div>\n");
      out.write("    <!--/.main-->\n");
      out.write("\t <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n");
      out.write("    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("    <script src=\"js/bootstrap.js\"></script>\n");
      out.write("    <script src=\"js/bootstrap-table.js\"></script>\n");
      out.write("  \n");
      out.write("    <script>\n");
      out.write("        !function ($) {\n");
      out.write("            $(document).on(\"click\", \"ul.nav li.parent > a > span.icon\", function () {\n");
      out.write("                $(this).find('em:first').toggleClass(\"glyphicon-minus\");\n");
      out.write("            });\n");
      out.write("            $(\".sidebar span.icon\").find('em:first').addClass(\"glyphicon-plus\");\n");
      out.write("        }(window.jQuery);\n");
      out.write("\n");
      out.write("        $(window).on('resize', function () {\n");
      out.write("            if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')\n");
      out.write("        })\n");
      out.write("        $(window).on('resize', function () {\n");
      out.write("            if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')\n");
      out.write("        })\n");
      out.write("\n");
      out.write("        $(\".checkbox\").on(\"click\", function () {\n");
      out.write("            var $checkbox = $(this).find(':checkbox');\n");
      out.write("            $checkbox.attr('checked', !$checkbox.attr('checked'));\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        var dtData = [{\n");
      out.write("            \"slno\": 0, \"name\": \"Name 0\", \"rtwkname\": \"Ritwik 1\", \"swastyayani\": 5, \"istavrity\": 10, \"acharyavrity\": 2, \"dakshina\": 14,\n");
      out.write("            \"sangathani\": 3, \"pronami\": 7, \"surplus\": 10, \"ritwiki\": 2\n");
      out.write("        }, {\n");
      out.write("            \"slno\": 0, \"name\": \"Name 1\", \"rtwkname\": \"Ritwik 2\", \"swastyayani\":\n");
      out.write("            5, \"istavrity\": 10, \"acharyavrity\": 2, \"dakshina\": 14, \"sangathani\": 3, \"pronami\": 7, \"surplus\": 10, \"ritwiki\": 2\n");
      out.write("        }];\n");
      out.write("        $('#tblIstavrity').bootstrapTable({ data: dtData });\n");
      out.write("        \n");
      out.write("        function ShowPermanentAdd() {\n");
      out.write("            if ($(\"#chkIsCurrentAdd\").is(':checked')) {\n");
      out.write("                $(\"#dvPermanentAdd\").addClass(\"hidden\");\n");
      out.write("            } else {\n");
      out.write("                $(\"#dvPermanentAdd\").removeClass(\"hidden\");\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\t</script>\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
