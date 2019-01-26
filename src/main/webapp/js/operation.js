function goBack() {
	$('.paymentForm').hide();
	$('.cardDdetailsPage').hide();
	$('.payReviewForm').show();
}

function addCardForm(){
	$('.paymentForm').show();
	$('.cardDdetailsPage').hide();
}

function addACHForm(){
	$('.achForm').show();
	$('.cardDdetailsPage').hide();
}
function goBackFrmACH() {
	$('.cardDdetailsPage').show();

//	var scope = angular.element('[ng-controller=ishtCtrl]').scope();
//    scope.$apply(function () {
//	    scope.viewACH();
//	});
	$('.achForm').hide();
}
function goBackToCardDetails(){
	$('.cardDdetailsPage').show();
//	var scope = angular.element('[ng-controller=ishtCtrl]').scope();
//    scope.$apply(function () {
//	    scope.viewACH();
//	});
	$('.paymentForm').hide();
	
}
function findValue(index) {
	var collections = new Array();
	
	collections.push($('#value' + index).val());
	collections.push($('#S' + (Number(index) + Number(1))).val());
	collections.push($('#I' + (Number(index) + Number(2))).val());
	collections.push($('#A' + (Number(index) + Number(3))).val());
	collections.push($('#D' + (Number(index) + Number(4))).val());
	collections.push($('#Sa' + (Number(index) + Number(5))).val());
	collections.push($('#P' + (Number(index) + Number(6))).val());
	collections.push($('#Su' + (Number(index) + Number(7))).val());
	collections.push($('#Pa' + (Number(index) + Number(8))).val());
	collections.push($('#Ri' + (Number(index) + Number(9))).val());
	return collections;
}

function addInText(index, temp) {
	$('#S' + (Number(index) + Number(1))).val(temp[1]);
	$('#I' + (Number(index) + Number(2))).val(temp[2]);
	$('#A' + (Number(index) + Number(3))).val(temp[3]);
	$('#D' + (Number(index) + Number(4))).val(temp[4]);
	$('#Sa' + (Number(index) + Number(5))).val(temp[5]);
	$('#P' + (Number(index) + Number(6))).val(temp[6]);
	$('#Su' + (Number(index) + Number(7))).val(temp[7]);
	$('#Pa' + (Number(index) + Number(8))).val(temp[8]);
	$('#Ri' + (Number(index) + Number(9))).val(temp[9]);

	newSumTotal(index,temp);
}
function newSumTotal(index, temp){
	
	findValueForTotal(index);
	grandTotal(index);
}

function grandTotal(index){
	var tempnum=(Number(index) + Number(10));
	var gTotal=document.getElementById ( "GTotal").value;
	var total=document.getElementById ( "tot"+tempnum).textContent;
	var tt=parseFloat(formatCurrency(gTotal))+parseFloat(formatCurrency(total));
	//alert(total+" "+tt);
	//document.getElementById ( "GTotal").value=tt;
	//
}

function findValueForTotal(index) {
	var collections = new Array();
	collections.push($('#S' + (Number(index) + Number(1))).val());
	collections.push($('#I' + (Number(index) + Number(2))).val());
	collections.push($('#A' + (Number(index) + Number(3))).val());
	collections.push($('#D' + (Number(index) + Number(4))).val());
	collections.push($('#Sa' + (Number(index) + Number(5))).val());
	collections.push($('#P' + (Number(index) + Number(6))).val());
	collections.push($('#Su' + (Number(index) + Number(7))).val());
	collections.push($('#Pa' + (Number(index) + Number(8))).val());
	collections.push($('#Ri' + (Number(index) + Number(9))).val());
	
	var totalAmt=0.0;
	for (var i = 0; i < collections.length; i++) {
			totalAmt=totalAmt+parseFloat(formatCurrency(collections[i]));
	}
	
	var tempnum=(Number(index) + Number(10));
	document.getElementById ( "tot"+tempnum).innerHTML=formatCurrency(totalAmt);
}



function selectedOperatoins(id) {

	var index = id.split(',')[1];

	var temp;
	var operation = document.getElementById(id).value;
	if (operation == "+") {
		addInText(index, temp = addition(findValue(index)));
	} else if (operation == "-") {
		addInText(index, temp = sub(findValue(index)));
	} else if (operation == "*") {
		addInText(index, temp = multi(findValue(index)));
	} else if (operation == "/") {
		addInText(index, temp = division(findValue(index)));
	}
	addGrandTotal();
	
}

function addGrandTotal(){
	var len = document.getElementById("mytbody").rows.length;
	var idVal=0;
	var totalTemp=0.00;
	var t;
	for(var i=0;i<len;i++){
		idVal=10+i;
		var total=document.getElementById("tot"+idVal).textContent;
		
		totalTemp=parseFloat(formatCurrency(total));
		t=parseFloat(formatCurrency(t))+parseFloat(formatCurrency(totalTemp));
		
	}
	
	document.getElementById("GTotal").value=formatCurrency(t);
}

function addition(collections) {
	for (var i = 0; i < collections.length; i++) {
		if (i != 0) {
			collections[i] = formatCurrency(add = parseFloat(formatCurrency(collections[i]))
					+ parseFloat(formatCurrency(collections[0])));
		}
	}
	return collections;
}

function sub(collections) {
	for (var i = 0; i < collections.length; i++) {
		if (i != 0) {
			if (parseFloat(formatCurrency(collections[i])) < parseFloat(formatCurrency(collections[0]))) {
				collections[i] = formatCurrency(0);
			} else {
				collections[i] = formatCurrency(add = parseFloat(formatCurrency(collections[i]))
						- parseFloat(formatCurrency(collections[0])));
			}
		}
	}
	return collections;
}

function multi(collections) {
	for (var i = 0; i < collections.length; i++) {
		if (i != 0) {
			collections[i] = formatCurrency(add = parseFloat(formatCurrency(collections[i]))
					* parseFloat(formatCurrency(collections[0])));
		}
	}
	return collections;
}

function division(collections) {
	for (var i = 0; i < collections.length; i++) {
		if (i != 0) {
			collections[i] = formatCurrency(add = parseFloat(formatCurrency(collections[i]))
					/ parseFloat(formatCurrency(collections[0])));
		}
	}
	return collections;
}



function reviewForm() {

	var tempCollection;
	var rows = $('#sum_table').children('tbody').children('tr');
	var GTotal = v = $('#GTotal').val();
	var call = null;
	for (var i = 0; i < rows.length; i++) {
		tempCollection = findValueForReview(i);
		for (var j = 0; j < tempCollection.length; j++) {
			if (j < 2) {
				call += "<td scope='row'><input type='text' style='text-align: center;width: 110px;' value="
						+ tempCollection[j] + " disabled></td>";
			} else {
				call += "<td><input type='text' style='text-align: center;width: 45px;' value="
						+ tempCollection[j] + " disabled></td>";
			}

		}
		// alert(call);
		$('#tbodyPre').append('<tr align="center">' + call + '</tr>');
		call = null;
	}

	$('#GTotalPre').val(GTotal);
	// GTotalPre

	$('.depositForm').hide();
	$('.payReviewForm').show();

}

function findValueForReview(index) {
	var collections = new Array();

	collections.push($('#id' + (Number(index) + Number(11))).val());
	collections.push($('#name' + (Number(index) + Number(12))).val());
	collections.push(document.getElementById('my_select,' + index).value);

	collections.push($('#value' + index).val());
	collections.push($('#S' + (Number(index) + Number(1))).val());
	collections.push($('#I' + (Number(index) + Number(2))).val());
	collections.push($('#A' + (Number(index) + Number(3))).val());
	collections.push($('#D' + (Number(index) + Number(4))).val());
	collections.push($('#Sa' + (Number(index) + Number(5))).val());
	collections.push($('#P' + (Number(index) + Number(6))).val());
	collections.push($('#Su' + (Number(index) + Number(7))).val());
	collections.push($('#Pa' + (Number(index) + Number(8))).val());
	collections.push($('#Ri' + (Number(index) + Number(9))).val());
	collections.push($('#tot' + (Number(index) + Number(10))).text());
	// alert(collections);
	return collections;
}

function editDepositForm() {
	$('.depositForm').show();
	$('.payReviewForm').hide();
	$("#tbodyPre").empty();
	document.getElementById("amountError").textContent = "";
}


function cardNumberFun(event) {
	var key = window.event ? event.keyCode : event.which;
	/*
	 * if(event.target.value.length==1){ cardNameChacking(event.target.value); }
	 */
	//$('').val(cardNumverFormat(event.target.value));
	
	document.getElementById("cardNumber").value=cardNumverFormat(event.target.value);
	if (event.keyCode === 8) {
		return true;
	} else if (key < 48 || key > 57) {
		return false;
	} else {
		return true;
	}
}

function bankRoutingKeyup(event) {
	var key = window.event ? event.keyCode : event.which;
	/*
	 * if(event.target.value.length==1){ cardNameChacking(event.target.value); }
	 */
	//$('').val(cardNumverFormat(event.target.value));

	
	var v = event.target.value.trim();
	var pattern= /^\d{9}$/;
	var re = new RegExp(pattern);
	var matches = re.test(v);

	return matches;
}


function achNoFun(event) {
	

	var v = event.target.value.trim();
	var pattern= /^\d{9}$/;
	var re = new RegExp(pattern);
	return re.test(v);
	
}


function reAchNoFun(event) {
	

	var v = event.target.value.trim();
	var pattern= /^[1-9]\d{12,16}$/;
	var re = new RegExp(pattern);
	return re.test(v);
	
}

function cardNumverFormat(value) {
	var v = value.replace(/\s+/g, '').replace(/[^0-9]/gi, '')
	var matches = v.match(/\d{4,20}/g);
	var match = matches && matches[0] || ''
	var parts = []

	for (i = 0, len = match.length; i < len; i += 4) {
		parts.push(match.substring(i, i + 4))
	}

	if (parts.length) {
		return parts.join(' ')
	} else {
		return value
	}
}

function expirationDateFun(event) {
	var key = window.event ? event.keyCode : event.which;
	
	if (event.keyCode === 8) {
		if (event.target.value.length > 6) {
			return false;
		} else {
			return true;
		}

	} else if (key < 47 || key > 57) {
		return false;
	} else {
		if (event.target.value.length > 6) {
			return false;
		} else {
			return true;
		}
	}
}

function expirationDateKeyUp(event) {
	var re1 = new RegExp("[0|1][0-9]\/[1-2][0-9]{3}");
	var element = document.getElementById("expirationDateId");
	if (event.target.value.length == 7||event.target.value.length == 0) {
		if (re1.test(new String(event.target.value))) {
			element.classList.remove("expirationdateError");
			return true;
		} else {
			// alert(element);
			element.classList.add("expirationdateError");
			return false;
		}
	}else{
		element.classList.add("expirationdateError");
		return false;
	}

}

function removeCss() {
	var element = document.getElementById("paymentFormBodyId");
	//element.classList.remove("payNow");
}

function removePayNowCss(){
	var element = document.getElementById('payNowButton');
	element.classList.remove("payNowButtonCls");
}

function addPayNowCss(){
	var element = document.getElementById('payNowButton');
	element.classList.add("payNowButtonCls");
}

function cvvFun(event) {
	var key = window.event ? event.keyCode : event.which;
	if (event.keyCode === 8) {
		if (event.target.value.length > 2) {
			return false;
		} else {
			return true;
		}
	} else if (key < 47 || key > 57) {
		return false;
	} else {
		if (event.target.value.length > 2) {
			 addPayNowCss();
			return false;
		} else {
			return true;
		}
	}
}


function paymentACHValidation(){
	
	var accName=document.getElementById("achName").value;
	var bankRoutingNo=document.getElementById("bankRoutingNo").value;
	var achNo=document.getElementById("bankChACCNo").value;
	var reAchNo=document.getElementById("reBankChACCNo").value;
	var dlNo=document.getElementById("dlNo").value;
	
	
	 
	var accNameEle = document.getElementById("achName");
	var bankRoutNoEle=document.getElementById("bankRoutingNo");
	var accNoEle=document.getElementById("bankChACCNo");
	var reAccNoEle = document.getElementById("reBankChACCNo");
	var dlNoEle=document.getElementById("dlNo");
	
	
	
	if (accName.trim().length > 0){
		accNameEle.classList.remove("errorText");
	}else{
		accNameEle.classList.add("errorText");
		return false;
	}

	
	if ( bankRoutingNo.trim().length > 0){
		var v =bankRoutingNo.trim();
		var pattern= /^\d{9}$/;
		var re = new RegExp(pattern);
		if(!re.test(v)){
			bankRoutNoEle.classList.add("errorText");
			return false;
		}else{
			bankRoutNoEle.classList.remove("errorText");
		}
		
	}else{
		bankRoutNoEle.classList.add("errorText");
		return false;
	}

	
	if (achNo.trim().length > 0){
		var v = achNo.trim();
		var pattern= /^\d{1,17}$/;
		var re = new RegExp(pattern);
		if(!re.test(v)){
			accNoEle.classList.add("errorText");
			return false;
		}else{
			accNoEle.classList.remove("errorText");
		}
	}else{
		accNoEle.classList.add("errorText");
		return false;
	}

	
	if (reAchNo.trim().length > 0){
		var v = reAchNo.trim();
		var pattern= /^\d{1,17}$/;
		var re = new RegExp(pattern);
		if(achNo != v){
			reAccNoEle.classList.add("errorText");
			return false;
		}else{
			if(!re.test(v)){
				reAccNoEle.classList.add("errorText");
				return false;
				
			}else{
				reAccNoEle.classList.remove("errorText");
			}
		}
		
	}else{
		reAccNoEle.classList.add("errorText");
		return false;
	}
	
	if (dlNo.trim().length > 0){
		dlNoEle.classList.remove("errorText");
	}else{
		dlNoEle.classList.add("errorText");
		return false;
	}
	
//	if (expireDate.trim().length > 0){
//		var rex = new RegExp("[0|1][0-9]\/[1-2][0-9]{3}");
//		if(rex.test(expireDate)){
//			expireDateElement.classList.remove("errorText");
//		}else{
//			cvvElement.classList.add("errorText");
//			return false;
//		}
//	}else{
//		expireDateElement.classList.add("errorText");
//		return false;
//	}
//	
//	if (cvvNum.trim().length > 0){
//		if(cvvNum.trim().length<3){
//			cvvElement.classList.add("errorText")
//			return true;
//		}else{
//			cvvElement.classList.remove("errorText");
//		}
//	}else{
//		cvvElement.classList.add("errorText");
//		return false;
//	}
	/*var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			
			console.log(this.responseText);
		}
	};
	xhttp.open("POST", "/OnlineSA/checkouts.do");
	xhttp.send();
	*/
	return true;
}
function paymentIstarghya(){
	
	var cardNumber=document.getElementById("cardNumber").value;
	var expireDate=document.getElementById("expirationDateId").value;
	var cvvNum=document.getElementById("cvvNum").value;
	
	 
	var cardElement = document.getElementById("cardNumber");
	var expireDateElement=document.getElementById("expirationDateId");
	var cvvElement=document.getElementById("cvvNum");
	
	
	
	if (cardNumber.trim().length > 0){
		cardElement.classList.remove("errorText");
	}else{
		cardElement.classList.add("errorText");
		return false;
	}
	
	if (expireDate.trim().length > 0){
		var rex = new RegExp("[0|1][0-9]\/[1-2][0-9]{3}");
		if(rex.test(expireDate)){
			expireDateElement.classList.remove("errorText");
		}else{
			cvvElement.classList.add("errorText");
			return false;
		}
	}else{
		expireDateElement.classList.add("errorText");
		return false;
	}
	
	if (cvvNum.trim().length > 0){
		if(cvvNum.trim().length<3){
			cvvElement.classList.add("errorText")
			return true;
		}else{
			cvvElement.classList.remove("errorText");
		}
	}else{
		cvvElement.classList.add("errorText");
		return false;
	}
	/*var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			
			console.log(this.responseText);
		}
	};
	xhttp.open("POST", "/OnlineSA/checkouts.do");
	xhttp.send();
	*/
	return true;
}


function validateExpriDate(val){
	var re1 = new RegExp("[0|1][0-9]\/[1-2][0-9]{3}");
	var element = document.getElementById("expirationDateId");
	if (val.length == 7||val.length == 0) {
		if (re1.test(new String(event.target.value))) {
			element.classList.remove("errorText");
			return true;
		} else {
			// alert(element);
			element.classList.add("errorText");
			return false;
		}
	}else{
		element.classList.add("errorText");
		return false;
	}
}


function cardNameChacking(num) {
	var element = document.getElementById("paymentFormBodyId");
	if (num == "5") {

		element.classList.add("masterCard");
	} else if (num == "4") {
		alert("visa")
		removeCss();
	} else if (num == "3") {
		alert("American Express")
	} else {
		alert("error")
	}
	
}




(function() {
	var courseApp = angular.module("ishtApp", [ "xeditable" ]);
	courseApp.controller('ishtCtrl',['$scope','$http','$filter','$compile',
		function($scope, $http, $filter,$compile) {
								$scope.spinerFlag=false;
								$scope.cardList;
								$scope.achList;
								$scope.termcb=false;
								/* 
								$scope.afterTransactionSuccess=function(id){
									var contextPath = "transactionsuccess.do";
									$http({
										 method : "POST",
										 url : contextPath,
										 data:{
											 	"transactionId":id
											 },
										 headers: {'Content-Type': 'application/json'}
									}).then(function mySucces(data) {
										 window.location = 'ishtpayconfirm.jsp';
									},function myError(d) {
										 console.log("Error:    "+d);
										 alert("fail");
										 $scope.spinerFlag=false;
									 });	 
								} */
									$scope.selPmtMethod="AUTO";
									$scope.ishtAmount=0.0;
									$scope.processIng=0.0;
									$scope.grandTotal=0.0;
									
									$scope.payByCardScope = function(myCard){
									
										$scope.cardNumberText=myCard.cardNumber;
										$scope.expirationDateText=myCard.expirationDate,
										$scope.cvvText="";
										//$scope.cvvText=myCard.cvv;
										//$scope.paymentFun(); 
										
										addCardForm();
										
									}
									$scope.payByACHScope = function(myACH){
										$scope.achName=myACH.accName;
										$scope.bankRoutingNo=myACH.routingNo,
										$scope.bankChACCNo=myACH.chAccNo;
										$scope.dlNo=myACH.dlNo;
										$scope.token;
										$scope.firstName=myACH.accName.split(" ")[0];
										$scope.lastName=myACH.accName.split(" ")[1];
										//$scope.cvvText=myCard.cvv;
										//$scope.paymentFun(); 
										
										addACHForm();
											 
									}
									
									
									
								
								$scope.suibMitPayment = function(){
									if($scope.selPmtMethod=='AUTO'){
										//alert ('suibMitPayment AUTO');
										$scope.parmentForm();
									}else{
										//alert ('suibMitPayment ishtPay called');
										
										$scope.ishtPay();
									
										
									}
								}
								
								 $scope.ishtPay = function() {
									   var data=[];
								        $("#sum_table").find("tr").each(function(){
								            //var id=$(this).attr("id");
								            var id="id";
								            var row={};
								            $(this).find("input,select,textarea").each(function(){
								            	if($(this).attr("name")!="action" && $(this).attr("name")!="textValue" ){
								            		row[$(this).attr("name")]=$(this).val();
								            	}
								                
								            });
								            data.push(row);
								        });
								        
							        	 var headerDetails ={
							        			
							        			stBankName:$scope.stBankName,
							   	 			  	stTrnNo:$scope.stTrnNo,
							   	 			  	stChqNo:$scope.stTrnNo,
							   	 			  	phoneNo:$('#phoneNo').val(),
							   	 			    pmtMethod:$scope.selPmtMethod,
							   	 		        chqDate:$scope.dtChqDate,
							   	 		        chAccNo:$scope.bankChACCNo,
							  				};
							        	 
							    
							   	 		
							   	 		var applicationFlow = $('#txtApplicationFlow').val();
							   	 		  //alert('Hi shyam 1'+headerDetails);
							   	 		//alert(applicationFlow);
							   	 	 	var contextPath = "saveIshtJSONData.do"+"?ishtLineData="+ JSON.stringify(data)+"&ishtHeaderData="+JSON.stringify(headerDetails)+"&applicationFlow="+applicationFlow;
							   	 		$http({
											 method : "POST",
											 url : contextPath,
											 dataType: 'json',
											 headers : {
								                    'Content-Type' : 'application/json'
								                }
										 }).then(function mySucces(data) {
											var returnObject = eval(data); // Parse Return Data
											//alert(returnObject.data.returnCode);
											if(returnObject.data.returnCode=='error') {
													 $scope.PostDataResponse = returnObject.data.returnMessage;
												 }else{
												 
												 if(returnObject.data.applicationFlow=='adminFlow') {
													 $scope.PostDataResponse = returnObject.data.returnMessage;
													 $("#dvErrAlert").show();
													 $("#submit_1").prop("disabled",true);
												 }else						 
													 window.location = 'ishtpayconfirm.jsp';
													 //window.location = 'payisht_reveiw_confirm.jsp';
													}
										 },function myError(d) {
											 alert("save failed");
										 });
							    	  };
//								$scope.submitManual=function(){
//									alert ('Something wrong happend');
//									removePNode();
//									var res=$scope.parmentForm();
//									if(res!=undefined && res.toString()=='false'){
//										alert("Fill this required field.");
//									}else{
//										$scope.spinerFlag=true;
//										var manPayObj = {
//												 "amount":document.getElementById("GTotalPre").value,
//												 "familyCode":document.getElementById("familyCode").value,
//												 "contact":document.getElementById("contact").value,
//												 "paymentMode":$scope.selPmtMethod,
//												 
//											 };
//										var contextPath = "manualTransactions.do"+"?manualPayDetails="+ JSON.stringify(manPayObj);
//					  					
//										$http({
//											 method : "POST",
//											 url : contextPath,
//											
//											 headers: {'Content-Type': 'application/json'}
//											 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
//										 }).then(function mySucces(data) {
//											 $scope.spinerFlag=false;
//											  $scope.json = angular.toJson(data.data);
//											  var obj = JSON.parse($scope.json);
//											  $scope.cardList=obj;
//											  	if(obj.status.toString()=="false"){
//											  		var node = document.createElement("P");
//											  		var failedTxt = document.createTextNode("Failed transaction");
//										  			node.appendChild(failedTxt); 
//											  		for(var i=0;i<obj.errorValidations.length;i++){
//											  		    var textnode = document.createTextNode(", "+obj.errorValidations[i].error);
//											  		    node.appendChild(textnode);
//											  			node.style.color = "red";
//											  			node.style.margin="22px";
//											  		    document.getElementById("paymentResponse").appendChild(node);
//											  		  $scope.spinerFlag=false;
//											  		  
//											  		}
//											  	}else{
//											  		/* var node = document.createElement("P");
//											  		var seccussTxt = document.createTextNode("Transaction ");
//										  		    var textnode = document.createTextNode("succussfully, Id: "+obj.trasactionId);
//										  			
//										  		    node.appendChild(seccussTxt); 
//										  		 	node.appendChild(textnode);
//										  		  
//										  			node.style.color = "green";
//										  			node.style.margin="22px";
//										  		    document.getElementById("paymentResponse").appendChild(node); */
//											  		
//										  		  $scope.spinerFlag=false;
//										  		//$scope.afterTransactionSuccess(obj.trasactionId);
//										  		
//										  		sessionStorage.setItem("transactionId", obj.trasactionId);
//										  		sessionStorage.setItem("GradTotalAmount", document.getElementById("GTotalPre").value);
//										  		//alert(sessionStorage.getItem("transactionId"));
//										  		window.location = 'ishtpayconfirm.jsp';
//											  	}
//											  $scope.spinerFlag=false;
//										 },function myError(d) {
//											 console.log("Error:    "+d);
//											 alert("fail");
//											 $scope.spinerFlag=false;
//										 });
//									}
//								};
								$scope.paymentFun=function(){
									removePNode();
									var res;
									
										res=paymentIstarghya();
										if(res!=undefined &&  res.toString()=='false'){
											alert("Fill this required field.");
											return;
										}
									
										$scope.spinerFlag=true;
										var contextPath = "transactions.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 data:{
												 "amount":document.getElementById("GTotal").value,
												 "familyCode":document.getElementById("familyCode").value,
												 "contact":document.getElementById("contact").value,
												 "cardNumber":$scope.cardNumberText,
												 "expirationDate":$scope.expirationDateText,
												 "cvv":$scope.cvvText
												 
											 },
											 headers: {'Content-Type': 'application/json'}
											 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
										 }).then(function mySucces(data) {
											 $scope.spinerFlag=false;
											  $scope.json = angular.toJson(data.data);
											  var obj = JSON.parse($scope.json);
											  //TODO: PARTH : Added ! below for testing else code block
											  
											  	if(obj.status.toString()=="false"){
											  		var node = document.createElement("P");
											  		var failedTxt = document.createTextNode("Failed transaction");
										  			node.appendChild(failedTxt); 
											  		for(var i=0;i<obj.errorValidations.length;i++){
											  		    var textnode = document.createTextNode(", "+obj.errorValidations[i].error);
											  		    node.appendChild(textnode);
											  			node.style.color = "red";
														node.style.margin="5px";
											  		    document.getElementById("paymentResponse").appendChild(node);
											  		  $scope.spinerFlag=false;
											  		  
											  		}
											  	}else{
											  		/* var node = document.createElement("P");
											  		var seccussTxt = document.createTextNode("Transaction ");
										  		    var textnode = document.createTextNode("succussfully, Id: "+obj.trasactionId);
										  			
										  		    node.appendChild(seccussTxt); 
										  		 	node.appendChild(textnode);
										  		  
										  			node.style.color = "green";
										  			node.style.margin="22px";
										  		    document.getElementById("paymentResponse").appendChild(node); */
											  		$scope.stTrnNo=obj.trasactionId;
											  		$scope.dtChqDate=obj.transactionDate;
											  	  $scope.ishtPay();
										  		  $scope.spinerFlag=false;
										  		//$scope.afterTransactionSuccess(obj.trasactionId);
										  		 
										  		sessionStorage.setItem("transactionId", obj.trasactionId);
										  		sessionStorage.setItem("GradTotalAmount", document.getElementById("GTotal").value);
										  		//alert(sessionStorage.getItem("transactionId"));
										  
										  		delete $scope.selectedCard;
										  		//window.location = 'ishtpayconfirm.jsp';
											  	}
											  $scope.spinerFlag=false;
										 },function myError(d) {
											 console.log("Error:    "+d);
											 alert("fail");
											 $scope.spinerFlag=false;
										 });
									
									
									
								};
								
								$scope.paymentAchFun=function(){
									removeACHNode();
									var res;
									
										res=paymentACHValidation();
										if(res!=undefined &&  res.toString()=='false'){
											alert("Fill this required field.");
											return;
										}
									
										$scope.spinerFlag=true;

										var contextPath = "getClientToken.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 headers: {'Content-Type': 'application/json'}
										}).then(function mySucces(data) {
										
											$scope.token=data.data;
											console.log('Token is '+$scope.token);
											braintree.client.create({
												  authorization: $scope.token
												}, function (clientErr, clientInstance) {
												  if (clientErr) {
												    console.error('There was an error creating the Client.');
												    throw clientErr;
												  }

												  braintree.usBankAccount.create({
												    client: clientInstance
												  }, function (usBankAccountErr, usBankAccountInstance) {
												    if (usBankAccountErr) {
												      console.error('There was an error creating the USBankAccount instance.');
												      throw usBankAccountErr;
												    }
											
												    var bankDetails = {
												    	    accountNumber: $scope.bankChACCNo,
												    	    routingNumber: $scope.bankRoutingNo,
												    	    accountType: 'checking',
												    	    ownershipType:'personal',
												    	    billingAddress: {
												    	        streetAddress:'Test Street1',
												    	        extendedAddress: 'Test Street2',
												    	        locality:'Test locality',
												    	        region: 'Test region',
												    	        postalCode: '10001',
												    	      }
												    	 
												    	  };

												    	  if (bankDetails.ownershipType === 'personal') {
												    	    bankDetails.firstName = $scope.firstName;
												    	    bankDetails.lastName = $scope.lastName;
												    	  } else {
												    	    bankDetails.businessName = 'PARTH L';
												    	  }

												      	  usBankAccountInstance.tokenize({
												    		    bankDetails: bankDetails, // or bankLogin: bankLogin
												    		    mandateText: 'By clicking ["Checkout"], I authorize Braintree, a service of PayPal, on behalf of [your business name here] (i) to verify my bank account information using bank information and consumer reports and (ii) to debit my bank account.'
														    	}, function (tokenizeErr, tokenizedPayload) {
														    		    if (tokenizeErr) {
														    		      console.error('There was an error tokenizing the bank details.');
														    		     // throw tokenizeErr;
														    		    }
														    		    
														    		    var contextPath = "achTransactions.do";
																		$http({
																			 method : "POST",
																			 url : contextPath,
																			 data:{
																				 "amount":document.getElementById("GTotal").value,
																				 "familyCode":document.getElementById("familyCode").value,
																				 "contact":document.getElementById("contact").value,
																				 "chAccNo":$scope.bankChACCNo,
																				 "nonce": 'fake-valid-nonce'
																			 },
																			 headers: {'Content-Type': 'application/json'}
																			 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
																		 }).then(function mySucces(data) {
																			 $scope.spinerFlag=false;
																			  $scope.json = angular.toJson(data.data);
																			  var obj = JSON.parse($scope.json);
																			  //TODO: PARTH : Added ! below for testing else code block
																			  
																			  	if(obj.status.toString()=="false"){
																			  		var node = document.createElement("P");
																			  		var failedTxt = document.createTextNode("Failed transaction");
																		  			node.appendChild(failedTxt); 
																		  			if(obj.errorValidations!=null){
																		  				for(var i=0;i<obj.errorValidations.length;i++){
																				  		    var textnode = document.createTextNode(", "+obj.errorValidations[i].error);
																				  		    node.appendChild(textnode);
																				  			node.style.color = "red";
																				  			node.style.margin="5px";
																				  		    document.getElementById("achADDResponse").appendChild(node);
																				  		 
																				  		  
																				  		}
																		  			}
																		  			if(obj.resMessage!=null){
																		  			    var textnode = document.createTextNode(", "+obj.resMessage);
																			  		    node.appendChild(textnode);
																			  			node.style.color = "red";
																						node.style.margin="5px";
																			  		    document.getElementById("achADDResponse").appendChild(node);
																		  			}
																		  	
																			  	}else{
																			  		/* var node = document.createElement("P");
																			  		var seccussTxt = document.createTextNode("Transaction ");
																		  		    var textnode = document.createTextNode("succussfully, Id: "+obj.trasactionId);
																		  			
																		  		    node.appendChild(seccussTxt); 
																		  		 	node.appendChild(textnode);
																		  		  
																		  			node.style.color = "green";
																		  			node.style.margin="22px";
																		  		    document.getElementById("paymentResponse").appendChild(node); */
																			  		$scope.stTrnNo=obj.trasactionId;
																			  		$scope.dtChqDate=obj.transactionDate;
																			  	  $scope.ishtPay();
																		  		  $scope.spinerFlag=false;
																		  		//$scope.afterTransactionSuccess(obj.trasactionId);
																		  		 
																		  		sessionStorage.setItem("transactionId", obj.trasactionId);
																		  		sessionStorage.setItem("GradTotalAmount", document.getElementById("GTotal").value);
																		  		//alert(sessionStorage.getItem("transactionId"));
																		  
																		  		delete $scope.selectedCard;
																		  		//window.location = 'ishtpayconfirm.jsp';
																			  	}
																			  $scope.spinerFlag=false;
																		 },function myError(d) {
																			 console.log("Error:    "+d);
																			 alert("fail");
																			 $scope.spinerFlag=false;
																		 });
														    		    
														    		    
														   });
												});
											
												});
										},function myError(d) {
											 console.log("Error:    "+d);
											 alert("fail");
											 $scope.spinerFlag=false;
										 });
									
								};
								
								
								
								function removePNode(){
									var length = document.getElementById("paymentResponse").childElementCount;
									var childNodeEle = document.getElementById("paymentResponse");   
									for(var i=0; i<length;i++){
										childNodeEle.removeChild(childNodeEle.childNodes[i]);
									}
								}
								
								function removeACHNode(){
									var length = document.getElementById("achADDResponse").childElementCount;
									var childNodeEle = document.getElementById("achADDResponse");   
									for(var i=0; i<length;i++){
										childNodeEle.removeChild(childNodeEle.childNodes[i]);
									}
								}
								
								$scope.addACH=function(){
									removeACHNode();
									var res=paymentACHValidation();
									if(res.toString()=='false'){
										alert("Fill this required field.");
									}else{
										var contextPath = "addACHs.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 data:{
												 "familyCode":document.getElementById("familyCode").value,
												 "contact":document.getElementById("contact").value,
												 "accName":$scope.achName,
												 "bankRoutingNo":$scope.bankRoutingNo,
												 "chAccNo":$scope.bankChACCNo,
												 "dlNo":$scope.dlNo,
											 },
											 headers: {'Content-Type': 'application/json'}
										 }).then(function mySucces(data) {
											  $scope.json = angular.toJson(data.data);
											  var obj = JSON.parse($scope.json);
											  var node = document.createElement("P");
									  		   var textnode = document.createTextNode(obj.responseMsg);
									  		    node.appendChild(textnode);
									  			node.style.color = "green";
									  			node.style.margin="20px";
									  		    document.getElementById("achADDResponse").appendChild(node);
											  
										 },function myError(d) {
											 console.log("Error:    "+d);
											 alert("ACH details save failed");
										 });
									}//else
								}//addCard
								
								$scope.addCard=function(){
									removePNode();
									var res=paymentIstarghya();
									if(res.toString()=='false'){
										alert("Fill this required field.");
									}else{
										var contextPath = "addcards.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 data:{
												 "familyCode":document.getElementById("familyCode").value,
												 "contact":document.getElementById("contact").value,
												 "cardNumber":$scope.cardNumberText,
												 "expirationDate":$scope.expirationDateText,
												 
											 },
											 headers: {'Content-Type': 'application/json'}
										 }).then(function mySucces(data) {
											  $scope.json = angular.toJson(data.data);
											  var obj = JSON.parse($scope.json);
											  var node = document.createElement("P");
									  		   var textnode = document.createTextNode(obj.responseMsg);
									  		    node.appendChild(textnode);
									  			node.style.color = "green";
									  			node.style.margin="20px";
									  		    document.getElementById("paymentResponse").appendChild(node);
											  
										 },function myError(d) {
											 console.log("Error:    "+d);
										 });
									}//else
								}//addCard
								
								$scope.removeCardScope= function(myCard){

									var contextPath = "removeCard.do";
									$scope.removedCard=myCard;
									$http({
										 method : "POST",
										 url : contextPath,
										 data:{
											"cardNumber":myCard.cardNumber,
										},
										 headers: {'Content-Type': 'application/json'}
									 }).then(function mySucces(data) {
										  $scope.json = angular.toJson(data.data);
										  var obj = JSON.parse($scope.json);
										  var node = document.createElement("P");
										  $scope.removeCardRes=obj.responseMsg;
										  $scope.removeByAttr($scope.cardList,'cardNumber', $scope.removedCard.cardNumber);
										  $scope.populateCard($scope.cardList);
								  		   var textnode = document.createTextNode(obj.responseMsg);
								  		    node.appendChild(textnode);
								  			node.style.color = "green";
								  			node.style.margin="20px";
								  		    document.getElementById("paymentResponse").appendChild(node);
										  
									 },function myError(d) {
										 console.log("Error:    "+d);
									 });
								
								}
								
								$scope.removeACHScope= function(myACH){

									var contextPath = "removeACH.do";
									$scope.removedACH=myACH;
									$http({
										 method : "POST",
										 url : contextPath,
										 data:{
											"chAccNo":myACH.chAccNo,
										},
										 headers: {'Content-Type': 'application/json'}
									 }).then(function mySucces(data) {
										  $scope.json = angular.toJson(data.data);
										  var obj = JSON.parse($scope.json);
										  var node = document.createElement("P");
										  $scope.removeCardRes=obj.responseMsg;
										  $scope.removeByAttr($scope.achList,'chAccNo', $scope.removedACH.chAccNo);
										  $scope.populateACH($scope.achList);
								  		   var textnode = document.createTextNode(obj.responseMsg);
								  		    node.appendChild(textnode);
								  			node.style.color = "green";
								  			node.style.margin="20px";
								  		    document.getElementById("paymentResponse").appendChild(node);
										  
									 },function myError(d) {
										 console.log("Error:    "+d);
									 });
								
								}
								
								$scope.removeByAttr = function(arr, attr, value){
								    var i = arr.length;
								    while(i--){
								       if( arr[i] 
								           && arr[i].hasOwnProperty(attr) 
								           && (arguments.length > 2 && arr[i][attr] === value ) ){ 

								           arr.splice(i,1);

								       }
								    }
								    return arr;
								};
								
								
								$scope.viewCard=function(){
									var cardTBody = document.getElementById("cardDetailsTBody");
									//removeTBody.innerHTML = "";
										var contextPath = "viewCards.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 data:{
												 "contact":document.getElementById("contact").value,
											 },
											 headers: {'Content-Type': 'application/json'}
											 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
										 }).then(function mySucces(data) {
											  $scope.json = angular.toJson(data.data);
											  var obj = JSON.parse($scope.json);
											  
											  $scope.populateCard(obj);
											 
											  //$compile($('#cardDetailsTBody'))($scope);
											$('.paymentForm').hide();
											if(obj.length>0){
												$('.cardDdetailsPage').show();
												$scope.viewACH();
											}else{
												$('.paymentForm').show();
											}
											
										 },function myError(d) {
											 console.log("Error:    "+d);
										 });
								}//viewCard
								
								  $scope.populateCard= function(obj){
									  $scope.cardList=obj;
									  $("#cardDetailsTBody").find("tr:gt(0)").remove();
									  var call = null;
									  for(var i=0;i<obj.length;i++){
										 var cardJson= JSON.stringify(obj[i]);
										  var cardType= creditCardTypeFromNumber(obj[i].cardNumber);
										  var imageName='images/'+cardType.toLowerCase()+'.jpg';
										  var cardDescription= cardType +' ending in '+obj[i].cardNumber.substr(obj[i].cardNumber.length - 4); 
										  call=
											
											'<td scope="row"><img  src="'+imageName+'" class="card-img">'+cardDescription +'</td>'+
											'<td scope="row">'+obj[i].expirationDate +'</td>'+
//											"<td scope='row'>"+obj[i].cvv +"</td>"+
											'<td scope="row"><a class="link-text" id="'+obj[i].cardNumber+'" onClick="payByCard(this)">Pay</a></td>'+
											'<td scope="row"><a class="link-text"  id="'+obj[i].cardNumber+'-r"   onClick="removeCard(this)">Remove</a></td>';
											
										  $('#cardDetailsTBody').append('<tr >' + call + '</tr>');
										 
										  call = null;
									  }
									  
									  
								  };
								 
								  $scope.populateACH= function(obj){
									  $scope.achList=obj;
									  
									  $("#achDetailsTBody").find("tr:gt(0)").remove();
									  var call = null;
									  for(var i=0;i<obj.length;i++){
										
										
										  var accDescription= 'Account ending in '+obj[i].chAccNo.substr(obj[i].chAccNo.length - 2); 
										  call=
											
											'<td scope="row">'+accDescription+'</td>'+
											'<td scope="row">'+obj[i].accName+'</td>'+
											'<td scope="row"><a class="link-text" id="'+obj[i].chAccNo+'" onClick="payByACH(this)">Pay</a></td>'+
											'<td scope="row"><a class="link-text" id="'+obj[i].chAccNo+'-r"  onClick="removeACH(this)">Remove</a></td>';
											
										  $('#achDetailsTBody').append('<tr >' + call + '</tr>');
										 
										  call = null;
									  }
								  };
								//View ACH: START
								$scope.viewACH=function(){
									var cardTBody = document.getElementById("achDetailsTBody");
									//removeTBody.innerHTML = "";
										var contextPath = "viewACHs.do";
										$http({
											 method : "POST",
											 url : contextPath,
											 data:{
												 "contact":document.getElementById("contact").value,
											 },
											 headers: {'Content-Type': 'application/json'}
											 //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
										 }).then(function mySucces(data) {
											  $scope.json = angular.toJson(data.data);
											  var obj = JSON.parse($scope.json);
											  $scope.populateACH(obj);
											  //$compile($('#cardDetailsTBody'))($scope);
											
											
											
										 },function myError(d) {
											 console.log("Error:    "+d);
										 });
								}//viewCard
								$scope.changeMe= function(){
									// alert('hello'+ $scope.selPmtMethod);
									$scope.grandTotal=0.0;
									$scope.processIng=0.0;
									$scope.ishtAmount=0.0;
								}
								

								
								$scope.parmentForm= function () {
									var amount=document.getElementById("GTotal").value;
									
									if (amount.toString()=="0.00"){
										document.getElementById("amountError").textContent = "Fill amount in ISTARGHYA DEPOSIT FORM.";
										return false;
									}else{
										document.getElementById("amountError").textContent = "";
									}
									
									var paymenType=$("#selPmtMethod").val();

									if(paymenType=='AUTO'){
										//$('.paymentForm').show();
										$('.payReviewForm').hide();
										$scope.viewCard();
										}
									
								}
								$(document)
										.ready(
												function() {
												
													var istPhone = $(
															'#phoneNo')
															.val();
													var applicationFlow = $(
															'#txtApplicationFlow')
															.val();
													var contextPath = "getIshtJSONObject.do"
															+ "?phoneNo="
															+ istPhone
															+ "&applicationFlow="
															+ applicationFlow;
													//var contextPath = "getIshtJSONObject.do"+"?phoneNo="+ istPhone;
													$http({
														method : "POST",
														url : contextPath
													})
															.then(
																	function mySucces(
																			data) {
																		var returnObject = eval(data); // Parse Return Data
																		if (returnObject.data.returnCode == 'error') {
																			$scope.PostDataResponse = returnObject.data.returnMessage;
																		} else {
																			$scope.ishtLine = returnObject.data.userJSONObject.line;
																			var $table=$("#sum_table");
																			//$table.bootstrapTable('load', returnObject.data.userJSONObject.line);
																			$table.bootstrapTable('hideLoading');
																			// $table.tableEditor();
																		}
																	},
																	function myError(
																			d) {
																		alert("failed to get Inside");
																	});
												});

								$(document)
										.on(
												'change',
												'#sum_table tr:not(.totalCol) input:text',
												function() {
													var $table = $(this).closest('table');
													var total = 0;
													var thisNumber = $(this)
															.attr('class')
															.match(/(\d+)/)[1];
													
													var count = 1;
													var mySelectOp = 0.00;
													var mytextValue = 0.00;
													var outVal = 0.00;
													var queryArr = [];

													$(this)
															.closest('tr')
															.find(
																	".mySelectOp")
															.each(
																	function() {
																		mySelectOp = this.value;
																	});
													$(this)
															.closest('tr')
															.find(
																	".mytextValue")
															.each(
																	function() {
																		mytextValue = this.value;
																	});

													$(this)
															.closest('tr')
															.find("input")
															.each(
																	function() {
																		if (count > 3
																				&& count < 13) {
																			//queryArr.push(this.value);
																			if (mySelectOp == "+") {
																				outVal = parseFloat(this.value)
																						+ parseFloat(mytextValue);
																				$(
																						this)
																						.val(
																								formatCurrency(outVal));
																				outVal = 0.00;
																			} else if (mySelectOp == "-") {
																				outVal = parseFloat(this.value)
																						- parseFloat(mytextValue);
																				if (outVal < 0) {
																					//alert(outVal);
																					$(
																							this)
																							.val(
																									formatCurrency(0.00));
																				} else {
																					$(
																							this)
																							.val(
																									formatCurrency(outVal));
																				}
																				outVal = 0.00;
																			} else if (mySelectOp == "*") {
																				outVal = parseFloat(mytextValue)
																						* parseFloat(this.value);
																				$(
																						this)
																						.val(
																								formatCurrency(outVal));
																				outVal = 0.00;
																			} else if (mySelectOp == "/") {
																				outVal = parseFloat(this.value)
																						/ parseFloat(mytextValue);
																				$(
																						this)
																						.val(
																								formatCurrency(outVal));
																				outVal = 0.00;
																			}
																			count++;
																		} else {
																			count++;
																		}
																	});

													/* var v= myOnFunction(queryArr);
													alert(v); */

													//alert('RowVal :'+$(this).attr('class').match(/(\d+)/)[1]);
													$table
															.find(
																	'tr:not(.totalCol) .sum'
																			+ thisNumber)
															.each(
																	function() {
																		total += parseFloat(
																				this.value)
																				.toFixed(
																						2);
																	});
													total = formatCurrency(total); //added b shyam

													$table
															.find(
																	'.totalCol td:nth-child('
																			+ thisNumber
																			+ ')')
															.html(total);

												});

								$(document).on('change', 'input', newSum);
								function newSum() {
									
									var sum = 0;
									var thisRow = $(this).closest('tr');
									var total = 0;
									var GrTotal = 0;
									var count = 0;
									var tempValue = 0;
									$(thisRow)
											.find("td:not(.total) input")
											.each(function() {
												if (!isNaN(this.value)) {
															$(this).closest('tr').find(".mytextValue")
																	.each(function() {
																				tempValue = this.value;
																	});
															sum += parseFloat(parseFloat(Math.round(this.value * 100) / 100)
																	.toFixed(2));
														}
													});

									sum = formatCurrency(sum - tempValue); //added b shyam
									$(thisRow).find(".total").html(sum);
									$scope.ishtAmount=0.0;
									$scope.processIng=0.0;
									$scope.grandTotal=0.0;
									$('.total').each(function() {
														GrTotal += parseFloat($(this)
																.html());
														
														//alert('GrTotal :'+GrTotal);
														//document.getElementById("GTotal").innerHTML = GrTotal;
												
													
													});

									if(GrTotal>=1){
										$scope.ishtPayForm.$valid = true;
										$scope.ishtPayForm.$invalid = false;
										$("#submit_1").prop('disabled', false);
										$scope.ishtAmount=GrTotal;
										if($scope.selPmtMethod=='AUTO'){
											$scope.$apply(function () {
												$scope.processIng=parseFloat(((GrTotal* 2.9/100) +0.30).toFixed(2));
											});
											GrTotal=$scope.ishtAmount + $scope.processIng;
											$("#GTotal").val(GrTotal.toFixed(2));
										}
							
									}else{
										$scope.ishtPayForm.$valid = false;
										$scope.ishtPayForm.$invalid = true;
										$("#submit_1").prop('disabled', true);
									}
									
									
									
									$scope.$apply(function () {
										$scope.grandTotal=GrTotal;
									});
									$("#GTotal").val(GrTotal.toFixed(2));
									
								}

								$scope.loadUser = function() {
									var istPhone = $('#phoneNo').val();
									var applicationFlow = $(
											'#txtApplicationFlow').val();
									var contextPath = "getIshtJSONObject.do"
											+ "?phoneNo="
											+ istPhone
											+ "&applicationFlow="
											+ applicationFlow;
									$http({
										method : "POST",
										url : contextPath
									})
											.then(
													function mySucces(data) {
														var returnObject = eval(data); // Parse Return Data
														alert(returnObject.data.returnCode);
														if (returnObject.data.returnCode == 'error') {
															$scope.PostDataResponse = returnObject.data.returnMessage;
														} else {
															$scope.ishtLine = returnObject.data.userJSONObject.line;
														}
													},
													function myError(d) {
														alert("failed to load");
													});
								};
							} ]);

})();

function CurrencyFormatted(amount, val) {
	//alert('VAL:'+val);
	var i = parseFloat(amount);
	if (isNaN(i)) {
		i = 0.00;
	}
	var minus = '';
	if (i < 0) {
		minus = '-';
	}
	i = Math.abs(i);
	i = parseInt((i + .005) * 100);
	i = i / 100;
	s = new String(i);
	if (s.indexOf('.') < 0) {
		s += '.00';
	}
	if (s.indexOf('.') == (s.length - 2)) {
		s += '0';
	}
	s = minus + s;
	//document.getElementById("One").value=s;
	document.getElementById(val).value = s;
	
	return s;
}
function formatCurrency(amount) {
	var i = parseFloat(amount);
	if (isNaN(i)) {
		i = 0.00;
	}
	var minus = '';
	if (i < 0) {
		minus = '-';
	}
	i = Math.abs(i);
	i = parseInt((i + .005) * 100);
	i = i / 100;
	s = new String(i);
	if (s.indexOf('.') < 0) {
		s += '.00';
	}
	if (s.indexOf('.') == (s.length - 2)) {
		s += '0';
	}
	s = minus + s;
	return s;
}
$(document).ready(function() {
	$("#submit_1").prop('disabled', true);
	$('.payMANReviewForm').hide();
	$('.cardDdetailsPage').hide();
	$('.achForm').hide();
	
	$("dtChqDate").focus(function() {
		//alert('Hi');
		var dtToday = new Date();
		var month = dtToday.getMonth() + 1;
		var day = dtToday.getDate();
		var year = dtToday.getFullYear();

		if (month < 10)
			month = '0' + month.toString();
		if (day < 10)
			day = '0' + day.toString();

		var maxDate = year + '-' + month + '-' + day;
		document.getElementById("dtChqDate").setAttribute("max", maxDate);
		//$('#txtDate').attr('max', maxDate);
	});

	
	

		
});



	

function checkDate() {
	var selectedText = document.getElementById('dtChqDate').value;
	var selectedDate = new Date(selectedText);

   // alert("selectedDate :"+selectedDate);
    
	var selYear = selectedDate.getFullYear().toString();
	var salMonth = (selectedDate.getMonth() + 101).toString().substring(1);
	var salDay = (selectedDate.getDate() + 100).toString().substring(1);

	//alert("selYear :"+selYear);
	//alert("salMonth :"+salMonth);
	//alert("salDay :"+salDay);
	
	var now = new Date();

	var nowYear = now.getFullYear().toString();
	var nowMonth = (now.getMonth() + 101).toString().substring(1);
	var nowDay = (now.getDate() + 100).toString().substring(1);

	//alert("nowYear :"+nowYear);
	//alert("nowMonth :"+nowMonth);
	//alert("nowDay :"+nowDay);
	
	if( (selYear>=nowYear)&&(salMonth>=nowMonth)&&(salDay>=nowDay) ){
		alert("Transaction date should not be later than today's date.");
		document.getElementById("dtChqDate").value = "DD-MM-YYYY";
	}else{
	
	}
	
	/* var salactDate = Date.parse("" + salDay + "-" + salMonth + "-"
			+ selYear + "");
	var now = Date.parse("" + nowDay + "-" + nowMonth + "-" + nowYear + "");
	//alert(salactDate+"                    "+now);
	if (salactDate > now) {
		alert("select valied date");
		document.getElementById('dtChqDate').value = null;
	} */
} 


function creditCardTypeFromNumber(num) {
	   // first, sanitize the number by removing all non-digit characters.
		if(num!=null){
	
			   num = num.replace(/[^\d]/g,'');
			   // now test the number against some regexes to figure out the card type.
			   if (num.match(/^5[1-5]\d{14}$/)) {
			     return 'MasterCard';
			   } else if (num.match(/^4\d{15}/) || num.match(/^4\d{12}/)) {
			     return 'Visa';
			   } else if (num.match(/^3[47]\d{13}/)) {
			     return 'AmEx';
			   } else if (num.match(/^6011\d{12}/)) {
			     return 'Discover';
			   }
		}
	   return 'UNKNOWN';
	 }




function payByCard(cardObj){
		var scope = angular.element('[ng-controller=ishtCtrl]').scope();
	    scope.$apply(function () {
		    var selectedCard=searchCardNumber(cardObj.id,scope.cardList);
		    scope.selectedCard=selectedCard;
		    scope.payByCardScope(selectedCard);
	    });
}

function payByACH(achObj){
		var scope = angular.element('[ng-controller=ishtCtrl]').scope();
	    scope.$apply(function () {
		    var selectedACH=searchACH(achObj.id,scope.achList);
		    scope.selectedACH=selectedACH;
		    scope.payByACHScope(selectedACH);
	    });
}


function removeCard(cardObj){
	var scope = angular.element('[ng-controller=ishtCtrl]').scope();
    scope.$apply(function () {
	    var selectedCard=searchCardNumber(cardObj.id.split("-")[0],scope.cardList);
	    scope.selectedCard=selectedCard;
	    scope.removeCardScope(selectedCard);
    });
}

function removeACH(achObj){
	var scope = angular.element('[ng-controller=ishtCtrl]').scope();
    scope.$apply(function () {
	    var selectedACH=searchACH(achObj.id.split("-")[0],scope.achList);
	    scope.selectedACH=selectedACH;
	    scope.removeACHScope(selectedACH);
    });
}



function searchCardNumber(nameKey, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].cardNumber  === nameKey) {
            return myArray[i];
        }
    }
}

function searchACH(nameKey, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].chAccNo  === nameKey) {
            return myArray[i];
        }
    }
}