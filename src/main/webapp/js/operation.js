function goBack() {
	$('.paymentForm').hide();
	$('.payReviewForm').show();
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
function parmentForm() {
	var amount=document.getElementById("GTotalPre").value;
	
	if (amount.toString()=="0.00"){
		document.getElementById("amountError").textContent = "Fill amount in ISTARGHYA DEPOSIT FORM.";
		return false;
	}else{
		document.getElementById("amountError").textContent = "";
	}
	

	$('.paymentForm').show();
	$('.payReviewForm').hide();
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
