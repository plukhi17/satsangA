/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-12-14 00:00:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class forgotpswd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("  \n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("\t<meta charset=\"utf-8\">\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\t<title>Satsang America - Forgot Password</title>\n");
      out.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\">\n");
      out.write("\n");
      out.write("\t<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\t<script src=\"js/operation.js\"></script>\n");
      out.write("\t<script src=\"js/jquery-1.11.1.min.js\"></script>\n");
      out.write("\t<script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\t#otpPasswordBody{\n");
      out.write("\t\t\tdisplay: none;\n");
      out.write("\t\t}\n");
      out.write("\t\t#newPasswordBody{\n");
      out.write("\t\t\tdisplay: none;\n");
      out.write("\t\t}\n");
      out.write("\t\t/* #submitOTPId{\n");
      out.write("\t\t\tdisplay:disabled;\n");
      out.write("\t\t\t\n");
      out.write("\t\t} */\n");
      out.write("\t</style>\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\t//$('#resendOTPId').hide();\n");
      out.write("\tfunction newPasswordReset(){\n");
      out.write("\t\t document.getElementById('txtNewPassword').value=\"\";\n");
      out.write("\t\t document.getElementById('txtMobileNumber').value=\"\";\n");
      out.write("\t}\n");
      out.write("\t\t\n");
      out.write("\tfunction forOTPNumber(event) {\n");
      out.write("\t\tvar key = window.event ? event.keyCode : event.which;\n");
      out.write("\t\tif (event.keyCode === 8) {\n");
      out.write("\t\t\t\treturn true;\n");
      out.write("\t\t} else if (key < 48 || key > 57) {\n");
      out.write("\t\t\treturn false;\n");
      out.write("\t\t} else {\n");
      out.write("\t\t\tif(event.target.value.length>5){\n");
      out.write("\t\t\t\treturn false;\n");
      out.write("\t\t\t}else{\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\tif(event.target.value.length==5){\n");
      out.write("\t\t\t\t\t//$('#submitOTPId').show();\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"submitOTPId\").disabled = false;\n");
      out.write("\t\t\t\t}else if(event.target.value.length<5){\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"submitOTPId\").disabled = true;\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\treturn true;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\tfunction backOTPBtn(){\n");
      out.write("\t\t$('#otpPasswordBody').hide();\n");
      out.write("\t\t$('#forgotPasswordBody').show();\n");
      out.write("\t\t $('#newPasswordBody').hide();\n");
      out.write("\t\t document.getElementById(\"responseMsg\").innerHTML=\"\";\n");
      out.write("\t\t document.getElementById(\"responseEmailId\").innerHTML=\"\";\n");
      out.write("\t\t document.getElementById('txtOTPNum').value=\"\";\n");
      out.write("\t}\n");
      out.write("\tfunction forgotPassword(){\n");
      out.write("\t\tvar emailId = document.getElementById(\"txtEmailId\").value;\n");
      out.write("\t\tif(emailId.trim().length > 0){\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar xhttp = new XMLHttpRequest();\n");
      out.write("\t\t\txhttp.onreadystatechange = function() {\n");
      out.write("\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n");
      out.write("\t\t\t\t\t//document.getElementById(\"deviceCode\").innerHTML = this.responseText;\n");
      out.write("\t\t\t\t\t//alert(this.responseText);\n");
      out.write("\t\t\t\t\tvar obj = JSON.parse(this.responseText);\n");
      out.write("\t\t\t\t\tif(obj.status==\"true\"){\n");
      out.write("\t\t\t\t\t\t//alert(\"true\")\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"responseEmailId\").innerHTML=obj.email;\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"responseMsg\").innerHTML=\"OTP Send your email id:\";\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"hidenEmail\").value=obj.email;\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"hideDate\").value=obj.date;\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t$('#forgotPasswordBody').hide();\n");
      out.write("\t\t\t\t\t\t$('#otpPasswordBody').show();\n");
      out.write("\t\t\t\t\t\tdocument.getElementById(\"txtEmailId\").value=\"\";\n");
      out.write("\t\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t\talert(\"Your email id: \"+obj.email+\",  \"+obj.responseMsg)\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t};\n");
      out.write("\t\t\txhttp.open(\"GET\", \"/OnlineSA/forgotpasswords.do?email=\"+emailId, true);\n");
      out.write("\t\t\txhttp.send();\n");
      out.write("\t\t\tdocument.getElementById(\"submitOTPId\").disabled = true;\n");
      out.write("\t\t\treturn true;\n");
      out.write("\t\t\t\n");
      out.write("\t\t}else{\n");
      out.write("\t\t\talert(\"Email id required\");\n");
      out.write("\t\t\treturn false;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t}//\n");
      out.write("\t\n");
      out.write("\tfunction verifyOTP(){\n");
      out.write("\t\tvar emailId = document.getElementById('hidenEmail').value;\n");
      out.write("\t\tvar dateMili = document.getElementById('hideDate').value;\n");
      out.write("\t\tvar txtOTPNum = document.getElementById('txtOTPNum').value;\n");
      out.write("\t\tvar xhttp = new XMLHttpRequest();\n");
      out.write("\t\txhttp.onreadystatechange = function() {\n");
      out.write("\t\t\tif (this.readyState == 4 && this.status == 200) {\n");
      out.write("\t\t\t\t//document.getElementById(\"deviceCode\").innerHTML = this.responseText;\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\tvar obj = JSON.parse(this.responseText);\n");
      out.write("\t\t\t\t if(obj.status==\"true\"){\n");
      out.write("\t\t\t\t\t $('#otpPasswordBody').hide();\n");
      out.write("\t\t\t\t\t $('#newPasswordBody').show();\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t}else{\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"responseEmailId\").innerHTML=\"\";\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"responseMsg\").innerHTML=obj.responseMsg;\n");
      out.write("\t\t\t\t\t//alert(obj.responseMsg);\n");
      out.write("\t\t\t\t}  \n");
      out.write("\t\t\t}\n");
      out.write("\t\t};\n");
      out.write("\t\txhttp.open(\"GET\", \"/OnlineSA/verifyotp.do?email=\"+emailId+\"&date=\"+dateMili+\"&OTPNum=\"+txtOTPNum, true);//\n");
      out.write("\t\txhttp.send();\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tfunction reSendOTP(){\n");
      out.write("\t\tvar emailId = document.getElementById('hidenEmail').value;\n");
      out.write("\t\tvar dateMili = document.getElementById('hideDate').value;\n");
      out.write("\t\tvar xhttp = new XMLHttpRequest();\n");
      out.write("\t\txhttp.onreadystatechange = function() {\n");
      out.write("\t\t\tif (this.readyState == 4 && this.status == 200) {\n");
      out.write("\t\t\t\t//document.getElementById(\"deviceCode\").innerHTML = this.responseText;\n");
      out.write("\t\t\t\tvar obj = JSON.parse(this.responseText);\n");
      out.write("\t\t\t\t if(obj.status==\"true\"){//responseMsg\n");
      out.write("\t\t\t\t\t document.getElementById(\"responseMsg\").innerHTML=obj.responseMsg;\n");
      out.write("\t\t\t\t\t document.getElementById(\"responseEmailId\").innerHTML=\"\";\n");
      out.write("\t\t\t\t\t document.getElementById(\"responseEmailId\").innerHTML=\"\";\n");
      out.write("\t\t\t\t}else{\n");
      out.write("\t\t\t\t} \n");
      out.write("\t\t\t}\n");
      out.write("\t\t};\n");
      out.write("\t\txhttp.open(\"GET\", \"/OnlineSA/resendotp.do?email=\"+emailId+\"&date=\"+dateMili, true);//\n");
      out.write("\t\txhttp.send();\n");
      out.write("\t}// close reSendOTP function \t\n");
      out.write("\t\n");
      out.write("\tfunction newPassword(){\n");
      out.write("\t\tvar mobileNumber = document.getElementById('txtMobileNumber').value;\n");
      out.write("\t\tvar newPassword = document.getElementById('txtNewPassword').value;\n");
      out.write("\t\tif(mobileNumber.trim().length > 0){\n");
      out.write("\t\t}else{\n");
      out.write("\t\t\talert(\"Mobile number required\");\n");
      out.write("\t\t\treturn false;\n");
      out.write("\t\t}\n");
      out.write("\t\tif(newPassword.trim().length > 0){\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar xhttp = new XMLHttpRequest();\n");
      out.write("\t\t\txhttp.onreadystatechange = function() {\n");
      out.write("\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n");
      out.write("\t\t\t\t\tvar obj = JSON.parse(this.responseText);\n");
      out.write("\t\t\t\t\t//alert(\"sssssssssssssss\"+this.responseText);\n");
      out.write("\t\t\t\t\t if(obj.status==\"true\"){//responseMsg\n");
      out.write("\t\t\t\t\t\t document.getElementById(\"responseNewPassword\").innerHTML=obj.responseMsg;\n");
      out.write("\t\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t\t document.getElementById(\"responseNewPassword\").innerHTML=obj.responseMsg;\n");
      out.write("\t\t\t\t\t} \n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t};\n");
      out.write("\t\t\txhttp.open(\"GET\", \"/OnlineSA/newpassword.do?mobileNumber=\"+mobileNumber+\"&newPassword=\"+newPassword, true);//\n");
      out.write("\t\t\txhttp.send();\n");
      out.write("\t\t\t\n");
      out.write("\t\t\treturn true;\n");
      out.write("\t\t}else{\n");
      out.write("\t\t\talert(\"New password required\");\n");
      out.write("\t\t\treturn false;\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("</head>\n");
      out.write("\n");

	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
    session.setAttribute("userBean",null);

      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\t<div class=\"row\" style=\"margin-top: 30px;\">\n");
      out.write("\t\t<div class=\"col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4\">\n");
      out.write("\t\t\t<div class=\"login-panel panel panel-default\">\n");
      out.write("\t\t\t\t<div class=\"panel-heading\">Ishtavrity - Forgot Password</div>\n");
      out.write("\t\t\t\t<div class=\"panel-body\" id=\"forgotPasswordBody\">\n");
      out.write("                    <div id=\"dvAlertErr\" class=\"alert-common bg-danger\" role=\"alert\">Invalid User ID / Password</div>\n");
      out.write("                    <div id=\"dvAlertWarning\" class=\"alert-common bg-success\" role=\"alert\">Your password has been emailed it to you.</div>\n");
      out.write("\t\t\t\t\t<form role=\"form\">\n");
      out.write("\t\t\t\t\t\t<fieldset>\n");
      out.write("                            <div class=\"form-group has-error\">\n");
      out.write("                                <!--<label class=\"control-label\" for=\"txtFamilyCode\">User ID:</label>-->\n");
      out.write("                                <input class=\"form-control\" placeholder=\"Enter your email id (someone@mail.com)\"\n");
      out.write("                                 id=\"txtEmailId\" name=\"txtEmailId\" required=\"required\">\n");
      out.write("                            </div>\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\"  onclick=\"return forgotPassword()\" class=\"btn btn-primary\">Submit</a>\n");
      out.write("                            <span class=\"pull-right\">Go back to <a href=\"/OnlineSA\">Login</a></span>\n");
      out.write("\t\t\t\t\t\t</fieldset>\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"panel-body\" id=\"otpPasswordBody\">\n");
      out.write("\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<p style=\"color: green;\"><span id=\"responseMsg\"></span> <span id=\"responseEmailId\"></span> </p> \n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-8 col-md-8\">\n");
      out.write("\t\t\t\t\t\t\t\t\t <input height=\"48\"  onkeypress='return forOTPNumber(event)' placeholder=\"437856\"\n");
      out.write("\t\t\t\t\t\t\t\t\t class=\"form-control\" id=\"txtOTPNum\" type=\"text\" style=\"font-size:30px;\">\n");
      out.write("\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t <input type=\"hidden\" id=\"hidenEmail\">\n");
      out.write("\t\t\t\t\t\t\t\t\t <input type=\"hidden\" id=\"hideDate\">\n");
      out.write("\t\t\t\t\t\t\t\t\t \n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\" style=\"margin-top: 10px;\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-3 col-md-3\">\n");
      out.write("\t\t\t\t\t\t\t\t<button style=\"font-size:20px;\" id=\"submitOTPId\" onclick=\"verifyOTP()\" class=\"btn btn-success btn-md\">Submit</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-3 col-md-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button style=\"font-size:20px; color: white; background-color: #428bca;\" \n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"btn btn-default\" onclick=\"reSendOTP()\">Re-Send OTP</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\" style=\"margin-top: 10px;\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-3 col-md-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<a  href=\"#\"  onclick=\"backOTPBtn()\">Back</a>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"panel-body\" id=\"newPasswordBody\">\n");
      out.write("\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t<p style=\"color: green;\"><span id=\"responseNewPassword\" ></span></p>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-4 col-md-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"cardinfo-label\" for=\"cvv\">Mobile Number:</label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-8 col-md-8\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" id=\"txtMobileNumber\" class=\"form-control\">\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\" style=\"margin-top: 10px;\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-4 col-md-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"cardinfo-label\" for=\"cvv\">New Password:</label>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-8 col-md-8\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"password\" id=\"txtNewPassword\" class=\"form-control\"> \n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\" style=\"margin-top: 20px; margin-bottom: 0;\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-2 col-md-2\"></div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-4 col-md-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button type=\"submit\" style=\"font-size:20px;\" onclick=\"return newPassword()\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"btn btn-success\">Submit\n");
      out.write("\t\t\t\t\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-4 col-md-4\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<button type=\"reset\" class=\"btn btn-danger\" style=\"font-size:20px;\" onclick=\"newPasswordReset()\">Reset</button>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-2 col-md-2\"></div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-12 col-md-12\" style=\"margin-top: 10px;\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"row\" style=\"margin-top: 10px;\">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-3 col-md-3\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<a  href=\"#\"  onclick=\"backOTPBtn()\">Back</a>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"col-xs-12 col-sm-9 col-md-9\">\n");
      out.write("\t\t\t\t\t\t\t\t\t <span class=\"pull-right\">Go back to <a href=\"/OnlineSA\">Login</a></span>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- /.col-->\n");
      out.write("\t</div>\n");
      out.write("\t<!-- /.row -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\n");
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
