<!DOCTYPE html>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@ page session="true" %>
<%@ page errorPage="error.jsp" %>  
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Satsang America - Forgot Password</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="js/operation.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<style type="text/css">
		#otpPasswordBody{
			display: none;
		}
		#newPasswordBody{
			display: none;
		}
		/* #submitOTPId{
			display:disabled;
			
		} */
	</style>
	<script type="text/javascript">
		//$('#resendOTPId').hide();
	function newPasswordReset(){
		 document.getElementById('txtNewPassword').value="";
		 //document.getElementById('txtMobileNumber').value="";
	}
		
	function forOTPNumber(event) {
		var key = window.event ? event.keyCode : event.which;
		if (event.keyCode === 8) {
				return true;
		} else if (key < 48 || key > 57) {
			return false;
		} else {
			if(event.target.value.length>5){
				return false;
			}else{
				if(event.target.value.length==5){
					//$('#submitOTPId').show();
					document.getElementById("submitOTPId").disabled = false;
				}else if(event.target.value.length<5){
					document.getElementById("submitOTPId").disabled = true;
				}
				return true;
			}
		}
	}
	function backOTPBtn(){
		$('#otpPasswordBody').hide();
		$('#forgotPasswordBody').show();
		 $('#Body').hide();
		 document.getElementById("responseMsg").innerHTML="";
		 document.getElementById("responseEmailId").innerHTML="";
		 document.getElementById('txtOTPNum').value="";
	}
	function forgotPassword(){
		var emailId = document.getElementById("txtEmailId").value;
		if(emailId.trim().length > 0){
			
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					//document.getElementById("deviceCode").innerHTML = this.responseText;
					//alert(this.responseText);
					var obj = JSON.parse(this.responseText);
					if(obj.status=="true"){
						//alert("true")
						document.getElementById("responseEmailId").innerHTML=obj.email;
						document.getElementById("responseMsg").innerHTML="Verification Code has been sent your registered email id and Phone Number:";
							
						document.getElementById("hidenEmail").value=obj.email;
						document.getElementById("hideDate").value=obj.date;
						
						$('#forgotPasswordBody').hide();
						$('#otpPasswordBody').show();
						document.getElementById("txtEmailId").value="";
					}else{
						alert("Your email id: "+obj.email+",  "+obj.responseMsg)
					}
				}
			};
			xhttp.open("GET", "/OnlineSA/forgotpasswords.do?email="+emailId, true);
			xhttp.send();
			document.getElementById("submitOTPId").disabled = true;
			return true;
			
		}else{
			alert("Email id required");
			return false;
		}
		
	}//
	
	function verifyOTP(){
		var emailId = document.getElementById('hidenEmail').value;
		var dateMili = document.getElementById('hideDate').value;
		var txtOTPNum = document.getElementById('txtOTPNum').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//document.getElementById("deviceCode").innerHTML = this.responseText;
				
				var obj = JSON.parse(this.responseText);
				 if(obj.status=="true"){
					 $('#otpPasswordBody').hide();
					 $('#newPasswordBody').show();
					
				}else{
					document.getElementById("responseEmailId").innerHTML="";
					document.getElementById("responseMsg").innerHTML=obj.responseMsg;
					//alert(obj.responseMsg);
				}  
			}
		};
		xhttp.open("GET", "/OnlineSA/verifyotp.do?email="+emailId+"&date="+dateMili+"&OTPNum="+txtOTPNum, true);//
		xhttp.send();
	}
	
	function reSendOTP(){
		var emailId = document.getElementById('hidenEmail').value;
		var dateMili = document.getElementById('hideDate').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//document.getElementById("deviceCode").innerHTML = this.responseText;
				var obj = JSON.parse(this.responseText);
				 if(obj.status=="true"){//responseMsg
					 document.getElementById("responseMsg").innerHTML=obj.responseMsg;
					 document.getElementById("responseEmailId").innerHTML="";
					 document.getElementById("responseEmailId").innerHTML="";
				}else{
				} 
			}
		};
		xhttp.open("GET", "/OnlineSA/resendotp.do?email="+emailId+"&date="+dateMili, true);//
		xhttp.send();
	}// close reSendOTP function 	
	
	function newPassword(){
		//var mobileNumber = document.getElementById('txtMobileNumber').value;
		var newPassword = document.getElementById('txtNewPassword').value;
		var confPassword = document.getElementById('txtconfPassword').value;
		if(newPassword.trim().length > 0 && confPassword.trim().length > 0 && newPassword == confPassword){
		}else{
			alert("Password and Confirm Password is not matching.");
			return false;
		}
		if(newPassword.trim().length > 0){
			
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var obj = JSON.parse(this.responseText);
					//alert("sssssssssssssss"+this.responseText);
					 if(obj.status=="true"){//responseMsg
						 document.getElementById("responseNewPassword").innerHTML=obj.responseMsg;
						 document.getElementById("newPwdSubmit").disabled =true;
					}else{
						 document.getElementById("responseNewPassword").innerHTML=obj.responseMsg;
					} 
				}
			};
			var familyId = document.getElementById('familyId').value;
			//alert(familyId);
			//xhttp.open("GET", "/OnlineSA/newpassword.do?mobileNumber="+mobileNumber+"&newPassword="+newPassword, true);//
			xhttp.open("GET", "/OnlineSA/newpassword.do?familyId="+familyId+"&newPassword="+newPassword, true);//
			//xhttp.open("GET", "/OnlineSA/newpassword.do?newPassword="+newPassword, true);//
			xhttp.send();
			
			return true;
		}else{
			alert("New password required");
			return false;
		}
	}
		
	</script>
</head>

<%
RootMDB root = (RootMDB)session.getAttribute("userBean");
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
    session.setAttribute("userBean",null);
%>
<body>

	<div class="row" style="margin-top: 30px;">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">Ishtavrity - Forgot Password</div>
				<div class="panel-body" id="forgotPasswordBody">
                   <!--  <div id="dvAlertErr" class="alert-common bg-danger" role="alert">Invalid User ID / Password</div> -->
                    <div id="dvAlertWarning" class="alert-common bg-success" role="alert">
                    A Verification Code will be sent out to your below registered email id and Phone Number.<br></div>
                    <br>
					<form role="form">
						<fieldset>
                            <div class="form-group has-error">
                                <!--<label class="control-label" for="txtFamilyCode">User ID:</label>-->
                                <input class="form-control" placeholder="Enter your email id (someone@mail.com)"
                                 id="txtEmailId" name="txtEmailId" value="<%= root!=null?root.getEmail():"" %>" required="required"  disabled="true"/> 
                            </div>
							<a href="#"  onclick="return forgotPassword()" class="btn btn-primary">Submit</a>
                            <span class="pull-right">Go back to <a href="/OnlineSA">Login</a></span>
						</fieldset>
					</form>
				</div>
				<div class="panel-body" id="otpPasswordBody">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<p style="color: green;"><span id="responseMsg"></span> <span id="responseEmailId"></span> </p> 
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-8 col-md-8">
									 <input height="48"  onkeypress='return forOTPNumber(event)' placeholder="437856"
									 class="form-control" id="txtOTPNum" type="text" style="font-size:30px;">
								
									 <input type="hidden" id="hidenEmail">
									  <input type="hidden" id="familyId" value="<%= root!=null?root.getFamilyID():"" %>">
									 <input type="hidden" id="hideDate">
									 
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<div class="col-xs-12 col-sm-4 col-md-4">
								<button style="font-size:15px; color: white; background-color: #428bca;" id="submitOTPId" onclick="verifyOTP()" class="btn btn-success btn-md">Submit</button>
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<button style="font-size:15px; color: white; background-color: #428bca;" 
									class="btn btn-default" onclick="reSendOTP()">Re-Send OTP</button>
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<div class="col-xs-12 col-sm-3 col-md-3">
									<a  href="#"  onclick="backOTPBtn()">Back</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body" id="newPasswordBody">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<p style="color: green;"><span id="responseNewPassword" ></span></p>
						</div>
				
						<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 10px;">
							<div class="row">
								<div class="col-xs-12 col-sm-4 col-md-4">
									<label class="cardinfo-label" for="txtNewPassword">New Password:</label>
								</div>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<input type="password" id="txtNewPassword" class="form-control"> 
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 10px;">
							 <div class="row">
								<div class="col-xs-12 col-sm-4 col-md-4">
									<label class="cardinfo-label" for="txtconfPassword">Confirm Password:</label>
								</div>
								<div class="col-xs-12 col-sm-8 col-md-8">
									<input type="password" id="txtconfPassword" class="form-control">
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 20px; margin-bottom: 0;">
							<div class="row">
								<div class="col-xs-12 col-sm-2 col-md-2"></div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<button type="submit" style="font-size:20px;" onclick="return newPassword()" id="newPwdSubmit"
										class="btn btn-success">Submit
									</button>
			
								</div>
								<div class="col-xs-12 col-sm-4 col-md-4">
									<button type="reset" class="btn btn-danger" style="font-size:20px;" onclick="newPasswordReset()">Reset</button>
								</div>
								<div class="col-xs-12 col-sm-2 col-md-2"></div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 10px;">
							<div class="row" style="margin-top: 10px;">
								<div class="col-xs-12 col-sm-3 col-md-3">
									<a  href="#"  onclick="backOTPBtn()">Back</a>
								</div>
								<div class="col-xs-12 col-sm-9 col-md-9">
									 <span class="pull-right">Go back to <a href="/OnlineSA">Login</a></span>
								</div>
								
							</div>
			
						</div>
			
					</div>
			
				</div>
				 <small class="text-muted"> <%@include file="footer.jsp" %> </small> 
			</div>
		</div>
		
		<!-- /.col-->
	</div>
	<!-- /.row -->



	
</body>

</html>