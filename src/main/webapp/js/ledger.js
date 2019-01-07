var app = angular.module('onlineSA', []);
app.controller('onlineSAController', function($scope,$http,$rootScope) {
	
	$("#myModal").hide();
	$scope.codeBtn="Code";
$scope.loadIshtTran = function() {
  		  			 	var istPhone = $('#phoneNo').val();
  		  			 	var contextPath = "getIshtTran.do"+"?phoneNo="+ istPhone;
  	  				 	var $table = $("#tblIshtTran");
  	  				 	$table.bootstrapTable({
  	  		            columns: [
  	  		                  {        
	  		                        title: 'SA Family Id',
	  		                        field: 'familyID',
	  		                        align: 'center',
	  		                        valign: 'middle',
	  		                        sortable: true
	  		                    },
	  		                    
  	  		                    {
  	  		                        title: 'Receipt No',
  	  		                        field: 'receiptNo',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                    	title: 'Transcation Date',
  	  		                    	field: 'collectedOn',
  	  		                    	align: 'center',
  	  		                    	valign: 'middle',
  	  		                    	sortable: true
  	  		                    },
  	  		                {
  	  		                        title: 'Transcation #',
  	  		                        field: 'trnDetails',
  	  		                        align: 'center',
  	  		                        valign: 'middle',
  	  		                        sortable: true
  	  		                    },
  	  		                    {
  	  		                    	title: 'Cheque Issue Bank',
  	  		                    	field: 'chequeIssueBank',
  	  		                    	align: 'center',
  	  		                    	valign: 'middle',
  	  		                    	sortable: true
  	  		                    },
  	  		                    {
  	  		                    	title: 'Total',
  	  		                    	field: 'total',
  	  		                    	align: 'center',
  	  		                    	valign: 'middle',
  	  		                    	sortable: true,
  	  		                       formatter:totalCurrencyFormatter
  	  		                    },
  	  		                    {
  	  		                    	title: 'Approval Status',
  	  		                    	field: 'issuedFlag',
  	  		                    	align: 'center',
  	  		                    	valign: 'middle',
  	  		                    	sortable: true
  	  		                      
  	  		                    }
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
		  			
});

		  			
function showCode(){
	$('#myModal').show();
	$('.ledgerWrapper').hide();
	return true;
	
}