var app = angular.module('onlineSA', []);
app.controller('onlineSAController', function($scope,$http,$rootScope) {
	
	$("#myModal").hide();
	$scope.codeBtn="Code";
	$scope.allCodes= [
			{"CodeName":"US","CodeDesc":"This is the code for US" },

		];
	 
	 
		$scope.loadLedgerEntries = function() {
  		  			 	
  		  			 	var contextPath = "getLedgerEntries.do";
  	  				 	var $table = $("#tblLedger");
  	  				 	$table.bootstrapTable({
  	  		            columns: [
  	  		                  {        
	  		                        title: 'Head Type',
	  		                        field: 'headTpe',
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
								 $scope.ishtLine = returnObject.data.userJSONObject.trnList;
							     $table.bootstrapTable('load', returnObject.data.userJSONObject.trnList);
							     $table.bootstrapTable('hideLoading');
							     $table.tableEditor();
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  		
		  			$scope.addCode=function() {
		  				if(	$scope.codeBtn=="Back"){
		  					$('#myModal').hide();
			  				$('.ledgerWrapper').show();
				 			$scope.codeBtn="Code";
		  				}else{
		  					$('#myModal').show();
			  				$('.ledgerWrapper').hide();
				 			$scope.codeBtn="Back";
		  				}
		  			
		  			};	
		  		
		  			$scope.getCodesFun = function() {
  		  			
  		  			 	
  		  			 	var contextPath = "getCodes.do";
  	  				
  	  				 	$http({
							 method : "POST",
							 url : contextPath,
							 
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 $scope.ishtLine = returnObject.data.userJSONObject.trnList;
							     $table.bootstrapTable('load', returnObject.data.userJSONObject.trnList);
							     $table.bootstrapTable('hideLoading');
							     $table.tableEditor();
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  	
		  			$scope.addLedger = function() {
						
							var contextPath = "addLedger.do";
							$http({
								 method : "POST",
								 url : contextPath,
								 data:{
									 "headType":$scope.balanceHead,
									 "amount":$scope.amount,
									 "amountDesc":$scope.amountDesc
								 },
								 headers: {'Content-Type': 'application/json'}
							 }).then(function mySucces(data) {
								  $scope.json = angular.toJson(data.data);
								  var obj = JSON.parse($scope.json);
								  var node = document.createElement("P");
						  		   var textnode = document.createTextNode(obj.responseMsg);
						  		  $scope.saveLedgerRes=obj.responseMsg;
//						  		    node.appendChild(textnode);
//						  			node.style.color = "green";
//						  			node.style.margin="20px";
//						  		    document.getElementById("paymentResponse").appendChild(node);
								  
							 },function myError(d) {
								 console.log("Error:    "+d);
								 alert("Please contant system admin.");
							 });
				
					};
		  			
		  			
		  			$scope.addCodeFun = function() {
  		  			 	var code = $('#code').val();
  		  			 	var codeDesc = $('#codeDesc').val();
  		  			 	var codeDTO ={
  		  				   codeName:$scope.code,
  		  				   codeDesc:$scope.codeDesc,
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
								    $scope.addCardRes=obj.responseMsg;
								    var node = document.createElement("P");
						  		    var textnode = document.createTextNode(obj.responseMsg);
						  		    node.appendChild(textnode);
						  			node.style.color = "green";
						  			node.style.margin="20px";
						  		    document.getElementById("codeResponse").appendChild(node);
								  
							 }
 						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  			
		  			$scope.addSubCodeFun = function() {
  		  			 
		  				var subCode=[{
		  						subCodeName: $scope.subCodeName,
		  						subCodeDesc: $scope.subCodeDesc
		  				}];
  		  			 	var codeDTO ={
  		  			 			codeName:$scope.selectedCd,
  		  			 			subCodes: subCode,
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