var app = angular.module('onlineSA', []);
app.controller('onlineSAController', function($scope,$http,$rootScope) {
	
	$("#myModal").hide();
	$("#myExpModal").hide();
	$('#ledgerModal').hide();
	$scope.codeBtn="Income Code";
	$scope.expCodeBtn="Expens Code"
	$scope.balanceSheetHeader="Balance Sheet";
	$scope.selectedHeadCd={};
	$scope.balanceHead='-1';
	$scope.ledGerBtn="Ledger";
	$scope.allCodes= [
			

		];
	
	$scope.allSubCodes= [
		

		];
	 
	 
		$scope.loadLedgerEntries = function() {
  		  			 	
  		  			 	var contextPath = "getLedgerEntries.do";
  	  				 	var $table = $("#tblLedger");
  	  				 	$table.bootstrapTable({
  	  		            columns: [
  	  		              
	  		                    {
  	  		                        title: 'Balance Head',
  	  		                        field: 'headType',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                        title: 'Code',
  	  		                        field: 'headCode',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                        title: 'Code Description',
  	  		                        field: 'headCodeDesc',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                        title: 'Sub Code',
  	  		                        field: 'headSubCode',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                        title: 'SubCode Description',
  	  		                        field: 'headSubCodeDesc',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
	  		                    
  	  		                    {
  	  		                        title: 'Amount',
  	  		                        field: 'amount',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                    	title: 'Description',
  	  		                    	field: 'amountDesc',
  	  		                    	align: 'center',
  	  		                    	valign: 'middle',
  	  		                    	sortable: true
  	  		                    },
  	  		                    {
  	  		                        title: 'Date',
  	  		                        field: 'submittedOn',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		               
  	  		               ]
  	  		        });
  	  				 	
			 	
		  			
		  			
  	  				$http({
							 method : "POST",
							 url : contextPath
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 $scope.ishtLine = returnObject.data.depositSmryJSONObject;
								 $scope.depSmrBal = returnObject.data.depositSmryBal;
							     $table.bootstrapTable('load', returnObject.data.depositSmryJSONObject);
							     $table.bootstrapTable('hideLoading');
							     //$table.tableEditor();
							 	$scope.balanceEntries();
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  		
  				$scope.balanceEntries = function() {
  		  			 	$scope.summaryDate=new Date();
  		  			 	var contextPath = "getBalanceSummary.do?summaryDate="+$scope.summaryDate;
  		  			 	$http({
							 method : "POST",
							 url : contextPath
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 
								 $scope.depSmrBal1 = returnObject.data.depositSmryBal;
								 $scope.incomeBalWrapper = JSON.parse(returnObject.data.incomeBalWrapper);
								 $scope.expenseBalWrapper = JSON.parse(returnObject.data.expenseBalWrapper);
								
							     //$table.tableEditor();
							 
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  			
		  			
		  			
		  			
		  			 $scope.addCode=function() {
		  				
		  				
		  				$scope.balanceSheetHeader="Income Head";
	  					$scope.getNextINCCode();
	  					$('#myModal').show();
		  				$('.ledgerWrapper').hide();
		  				$scope.codeHeadType="income";
			 		   delete $scope.addCodeRes;
		 			   delete $scope.addSubCardRes;
		  			
		  			};	
		  			
		  			 $scope.addExpCode=function() {
			  					$scope.balanceSheetHeader="Expense Head";
			  					$scope.codeHeadType="expense";
			  					$scope.getNextExpCode();
			  					$('#myExpModal').show();
				  				$('.ledgerWrapper').hide();
					 			delete $scope.addCodeRes;
				 			   delete $scope.addSubCardRes;
			  				
			  			
			  			};	
			  			
			  			 $scope.showEntryLedger=function() {
				  				
			  				 		$scope.balanceSheetHeader="Add Ledger";
			  				 		$scope.balanceHead="-1";
				  					$('#ledgerModal').show();
					  				$('.ledgerWrapper').hide();
						 		   delete $scope.addCodeRes;
					 			   delete $scope.addSubCardRes;
				  				
				  			
				  			};	
				  			
				  			$scope.backToLedger = function(){
				  				$scope.balanceSheetHeader="Balance Sheet";
			  					$('#ledgerModal').hide();
			  					$('#myExpModal').hide();
			  					$('#myModal').hide();
				  				$('.ledgerWrapper').show();
				  				$scope.codeHeadType="-1";
					 			
				  			};
		  			$scope.onChangeHead= function(){
						$scope.codeHeadType=$scope.balanceHead;
						$scope.getCodesFun();
						//$scope.grandTotal=0.0;
						
					};
		  			
		  			$scope.getNextINCCode= function(){

	  					var contextPath = "getNextIncCode.do";
  	  				
	  				 	$http({
						 method : "POST",
						 url : contextPath,
						 
					 }).then(function mySucces(data) {
						var returnObject = eval(data); // Parse Return Data
						if(returnObject.data.returnCode=='error') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
						 }else{
							 $scope.code = returnObject.data.INCCODE;
							 $scope.getCodesFun();
						 }
						 },function myError(d) {
						 alert("fail");
					 });
	  			
		  			};
		  			
		  			$scope.getNextExpCode= function(){

	  					var contextPath = "getNextExpCode.do";
  	  				
	  				 	$http({
						 method : "POST",
						 url : contextPath,
						 
					 }).then(function mySucces(data) {
						var returnObject = eval(data); // Parse Return Data
						if(returnObject.data.returnCode=='error') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
						 }else{
							 $scope.code = returnObject.data.EXPNCODE;
							 $scope.getCodesFun();
						 }
						 },function myError(d) {
						 alert("fail");
					 });
	  			
		  			};
	
		  	
		  			$scope.getNextSubCode= function(){
		  				var codeHeadType= $scope.codeHeadType;
	  		  		
	  					var contextPath = "getNextSubCode.do?codeType="+codeHeadType;
  	  				
	  				 	$http({
						 method : "POST",
						 url : contextPath,
						
						 
					 }).then(function mySucces(data) {
						var returnObject = eval(data); // Parse Return Data
						if(returnObject.data.returnCode=='error') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
						 }else{
							 $scope.subCodeName = returnObject.data.INCSUBCODE;
						
						 }
						 },function myError(d) {
						 alert("fail");
					 });
	  			
		  			};
	
		  			$scope.getCodesFun = function() {
  		  			
		  				var headType=$scope.codeHeadType;
		  				/*if(headType=="-1"){
		  					headType=$scope.codeHeadType;
		  				}*/
  		  			 	
  		  			 	var contextPath = "getCodes.do?headType="+ headType;
  	  				
  	  				 	$http({
							 method : "POST",
							 url : contextPath,
							 
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 $scope.allCodes = returnObject.data;
								 //$scope.getNextINCSubCode();
							   
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  	
		  			$scope.getSubCodebyCodeFun = function() {
  		  			
	  			 	 var codeSelected= $scope.selectedHeadCd.codeName;
	  			 	var codeHeadType= $scope.codeHeadType;
  		  			 var contextPath = "getSubCodeByCode.do"+"?codeName="+ codeSelected + "&codeType="+codeHeadType;
  	  				 	$http({
							 method : "POST",
							 url : contextPath,
							 
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 $scope.allSubCodes = returnObject.data.subCodes[0].subCodes;
								 //$scope.getNextINCSubCode();
							   
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  			$scope.addLedger = function() {
		  			//alert($scope.selectedHeadSubCd.split('-')[0].trim());
	  					if($scope.amount==undefined){
							return false;
						}
							var contextPath = "addLedger.do";
							$http({
								 method : "POST",
								 url : contextPath,
								 data:{
									 "headType":$scope.balanceHead,
									 "headCode":$scope.selectedHeadCd.codeName,
									 "headCodeDesc":$scope.selectedHeadCd.codeDesc,
									 "headSubCode":$scope.selectedHeadSubCd.split('-')[0].trim(),
									 "headSubCodeDesc":$scope.selectedHeadSubCd.split('-')[1].trim(),
									 "amount":$scope.amount,
									 "amountDesc":$scope.amountDesc,
									 "balance":	 $scope.depSmrBal,
								
								 },
								 headers: {'Content-Type': 'application/json'}
							 }).then(function mySucces(data) {
								  $scope.json = angular.toJson(data.data);
								  var obj = JSON.parse($scope.json);
								  var node = document.createElement("P");
						  		  var textnode = document.createTextNode(obj.responseMsg);
						  		  $scope.saveLedgerRes=obj.responseMsg;
						  		  $scope.loadLedgerEntries();
						  		delete $scope.amount;
						  		delete $scope.amountDesc;
						  	
									  
							 },function myError(d) {
								 console.log("Error:    "+d);
								 alert("Please contant system admin.");
							 });
				
					};
		  			
		  			
		  			$scope.addCodeFun = function() {
  		  			 	var code = $('#code').val();
  		  			 	var codeDesc = $('#codeDesc').val();
  		  			 	var codeHeadType= $scope.codeHeadType;
  		  			 	if(codeDesc==""){
  		  			 		alert("Please fill the Code description");
  		  			 		return false;
  		  			 	}
  		  			 	var codeDTO ={
  		  				   codeName:$scope.code,
  		  				   codeDesc:$scope.codeDesc,
  		  				   codeType:codeHeadType,
  		  				   };
  		  			 	var contextPath = "addCode.do";
  	  				
  	  				 	$http({
							 method : "POST",
							 url : contextPath,
							 data: codeDTO
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 	$scope.json = angular.toJson(data.data);
								    var obj = JSON.parse($scope.json);
								  
								    $scope.addCodeRes=obj.responseMsg;
								    
						  		    $scope.getCodesFun();
								  
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  			
		  			$scope.addSubCodeFun = function() {
		  				var subCode = $('#subCode').val();
  		  			 	var subCodeDesc = $('#subCodeDesc').val();
		  				if(subCodeDesc==""){
  		  			 		alert("Please fill the SubCode description");
  		  			 		return false;
  		  			 	}
  		  			 	var subCodeDTO ={
  		  			 			subCodeName: $scope.subCodeName,
  		  			 			subCodeDesc: $scope.subCodeDesc,
  		  			 			codeName:$scope.selectedCd.codeName
  		  				   };
  		  			 	var contextPath = "addSubCode.do";
  	  				
  	  				 	$http({
							 method : "POST",
							 url : contextPath,
							 data: subCodeDTO
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 	$scope.json = angular.toJson(data.data);
								    var obj = JSON.parse($scope.json);
								    $scope.addSubCardRes=obj.responseMsg;
								    var node = document.createElement("P");
						  		    var textnode = document.createTextNode(obj.responseMsg);
						  		    node.appendChild(textnode);
						  			node.style.color = "green";
						  			node.style.margin="20px";
						  	
								  
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  		
		  			
});

		  			
function showCode(){
	$('#myModal').show();
	$('.ledgerWrapper').hide();
	return true;
	
}