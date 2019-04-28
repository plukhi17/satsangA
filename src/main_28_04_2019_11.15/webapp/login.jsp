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
                                 <input class="form-control" placeholder="SA Family ID / Phone Number" id="userName" name="userName" autofocus="" ng-model="userName" required>
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
	
	
//<!--This script is based on the javascript code of Roman Feldblum (web.developer@programmer.net)-->
//<!-- Original script : http://javascript.internet.com/forms/format-phone-number.html -->
//<!-- Original script is revised by Eralper Yilmaz (http://www.eralper.com) -->
//<!-- Revised script : http://www.kodyaz.com/content/HowToAutoFormatTelephoneNumber.aspx -->

var zChar = new Array(' ', '(', ')', '-', '.');
var maxphonelength = 13;
var phonevalue1;
var phonevalue2;
var cursorposition;

function ParseForNumber1(object){
	phonevalue1 = ParseChar(object.value, zChar);
}
function ParseForNumber2(object){
	phonevalue2 = ParseChar(object.value, zChar);
}

function backspacerUP(object,e) { 
	if(e){ 
		e = e 
	} else {
		e = window.event 
	} 
	if(e.which){ 
		var keycode = e.which 
	} else {
		var keycode = e.keyCode 
	}

	ParseForNumber1(object)

	if(keycode >= 48){
		ValidatePhone(object)
	}
}

function backspacerDOWN(object,e) { 
	if(e){ 
		e = e 
	} else {
		e = window.event 
	} 
	if(e.which){ 
		var keycode = e.which 
	} else {
		var keycode = e.keyCode 
	}
	ParseForNumber2(object)
} 

function GetCursorPosition(){
    
	var t1 = phonevalue1;
	var t2 = phonevalue2;
	var bool = false
    for (i=0; i<t1.length; i++)
    {
    	if (t1.substring(i,1) != t2.substring(i,1)) {
    		if(!bool) {
    			cursorposition=i
    			bool=true
    		}
    	}
    }
}

function ValidatePhone(object){
	
	var p = phonevalue1
	
	p = p.replace(/[^\d]*/gi,"")

	if (p.length < 3) {
		object.value=p
	} else if(p.length==3){
		pp=p;
		d4=p.indexOf('(')
		d5=p.indexOf(')')
		if(d4==-1){
			pp="("+pp;
		}
		if(d5==-1){
			pp=pp+")";
		}
		object.value = pp;
	} else if(p.length>3 && p.length < 7){
		p ="(" + p;	
		l30=p.length;
		p30=p.substring(0,4);
		p30=p30+")"

		p31=p.substring(4,l30);
		pp=p30+p31;

		object.value = pp;	
		
	} else if(p.length >= 7){
		p ="(" + p;	
		l30=p.length;
		p30=p.substring(0,4);
		p30=p30+")"
		
		p31=p.substring(4,l30);
		pp=p30+p31;
		
		l40 = pp.length;
		p40 = pp.substring(0,8);
		p40 = p40 + "-"
		
		p41 = pp.substring(8,l40);
		ppp = p40 + p41;
		
		object.value = ppp.substring(0, maxphonelength);
	}
	
	GetCursorPosition()
	
	if(cursorposition >= 0){
		if (cursorposition == 0) {
			cursorposition = 2
		} else if (cursorposition <= 2) {
			cursorposition = cursorposition + 1
		} else if (cursorposition <= 5) {
			cursorposition = cursorposition + 2
		} else if (cursorposition == 6) {
			cursorposition = cursorposition + 2
		} else if (cursorposition == 7) {
			cursorposition = cursorposition + 4
			e1=object.value.indexOf(')')
			e2=object.value.indexOf('-')
			if (e1>-1 && e2>-1){
				if (e2-e1 == 4) {
					cursorposition = cursorposition - 1
				}
			}
		} else if (cursorposition < 11) {
			cursorposition = cursorposition + 3
		} else if (cursorposition == 11) {
			cursorposition = cursorposition + 1
		} else if (cursorposition >= 12) {
			cursorposition = cursorposition
		}

        var txtRange = object.createTextRange();
        txtRange.moveStart( "character", cursorposition);
		txtRange.moveEnd( "character", cursorposition - object.value.length);
        txtRange.select();
    }

}

function ParseChar(sStr, sChar)
{
    if (sChar.length == null) 
    {
        zChar = new Array(sChar);
    }
    else zChar = sChar;
    
    for (i=0; i<zChar.length; i++)
    {
        sNewStr = "";
    
        var iStart = 0;
        var iEnd = sStr.indexOf(sChar[i]);
    
        while (iEnd != -1)
        {
            sNewStr += sStr.substring(iStart, iEnd);
            iStart = iEnd + 1;
            iEnd = sStr.indexOf(sChar[i], iStart);
        }
        sNewStr += sStr.substring(sStr.lastIndexOf(sChar[i]) + 1, sStr.length);
        
        sStr = sNewStr;
    }
    
    return sNewStr;
}
	
	
	</script>
	
	</div>
</body>

</html>