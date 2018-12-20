/**
 */

var app = angular.module('onlineSA', []);
app.controller('onlineSAController', function($scope,$http,$rootScope) {
		$scope.isEdit = true;    	
	   	$scope.country = {};
	    $scope.state = {};

	    var allCountries = [{
	        Id: "US",
	        CountryName: "USA"
	    }];
	  
	    $scope.countries = allCountries;
	    
	    var allStates = [
			{CountryId:"US","StateName":"Alabama","StateCode":"AL" },
			{CountryId:"US","StateName":"Alaska","StateCode":"AK" },
			{CountryId:"US","StateName":"Arizona","StateCode":"AZ" },
			{CountryId:"US","StateName":"Arkansas" ,"StateCode":"AR"},
			{CountryId:"US","StateName":"California","StateCode":"CA" },
			{CountryId:"US","StateName":"Colorado" ,"StateCode":"CO"},
			{CountryId:"US","StateName":"Connecticut","StateCode":"CT" },
			{CountryId:"US","StateName":"District of Columbia","StateCode":"DC" },
			{CountryId:"US","StateName":"Delaware","StateCode":"DE"},
			{CountryId:"US","StateName":"Florida","StateCode":"FL"},
			{CountryId:"US","StateName":"Georgia","StateCode":"GA"},
			{CountryId:"US","StateName":"Hawaii","StateCode":"HI"},
			{CountryId:"US","StateName":"Idaho","StateCode":"ID"},
			{CountryId:"US","StateName":"Illinois","StateCode":"IL" },
			{CountryId:"US","StateName":"Indiana","StateCode":"IN" },
			{CountryId:"US","StateName":"Iowa","StateCode":"IA" },
			{CountryId:"US","StateName":"Kansas","StateCode":"KS" },
			{CountryId:"US","StateName":"Kentucky","StateCode":"KY" },
			{CountryId:"US","StateName":"Louisiana","StateCode":"LA" },
			{CountryId:"US","StateName":"Maine","StateCode":"ME" },
			{CountryId:"US","StateName":"Maryland","StateCode":"MD" },
			{CountryId:"US","StateName":"Massachusetts","StateCode":"MA" },
			{CountryId:"US","StateName":"Michigan","StateCode":"MI" },
			{CountryId:"US","StateName":"Minnesota","StateCode":"MN" },
			{CountryId:"US","StateName":"Mississippi","StateCode":"MS" },
			{CountryId:"US","StateName":"Missouri","StateCode":"MO" },
			{CountryId:"US","StateName":"Montana","StateCode":"MT" },
			{CountryId:"US","StateName":"Nebraska","StateCode":"NE" },
			{CountryId:"US","StateName":"Nevada","StateCode":"NV" },
			{CountryId:"US","StateName":"New Hampshire","StateCode":"NH" },
			{CountryId:"US","StateName":"New Jersey","StateCode":"NJ" },
			{CountryId:"US","StateName":"New Mexico","StateCode":"NM" },
			{CountryId:"US","StateName":"New York","StateCode":"NY" },
			{CountryId:"US","StateName":"North Carolina","StateCode":"NC" },
			{CountryId:"US","StateName":"North Dakota","StateCode":"ND" },
			{CountryId:"US","StateName":"Ohio","StateCode":"OH" },
			{CountryId:"US","StateName":"Oklahoma","StateCode":"OK" },
			{CountryId:"US","StateName":"Oregon","StateCode":"OR" },
			{CountryId:"US","StateName":"Pennsylvania","StateCode":"PA" },
			{CountryId:"US","StateName":"Rhode Island","StateCode":"PR" },
			{CountryId:"US","StateName":"South Carolina","StateCode":"RI" },
			{CountryId:"US","StateName":"South Dakota","StateCode":"SC" },
			{CountryId:"US","StateName":"Tennessee","StateCode":"TN" },
			{CountryId:"US","StateName":"Texas","StateCode":"TX" },
			{CountryId:"US","StateName":"Utah","StateCode":"UT" },
			{CountryId:"US","StateName":"Vermont","StateCode":"VT" },
			{CountryId:"US","StateName":"Virginia","StateCode":"VA" },
			{CountryId:"US","StateName":"Washington","StateCode":"WA" },
			{CountryId:"US","StateName":"West Virginia","StateCode":"WV" },
			{CountryId:"US","StateName":"Wisconsin","StateCode":"WI" },
			{CountryId:"US","StateName":"Wyoming","StateCode":"WY" },
			{CountryId:"US","StateName":"American Samoa" ,"StateCode":"AS"},
			{CountryId:"US","StateName":"Guam","StateCode":"GU" },
			{CountryId:"US","StateName":"Northern Mariana Islands","StateCode":"MP" },
			{CountryId:"US","StateName":"Puerto Rico","StateCode":"PR" },
			{CountryId:"US","StateName":"United States Minor Outlying Islands","StateCode":"IL" },
			{CountryId:"US","StateName":"Virgin Islands","StateCode":"VI" }
		];
	   	    
	    $scope.$watch('country', function () {
	        $scope.states = allStates.filter(function (s) {
	            return s.CountryId == $scope.country.Id;
	        });
	        $scope.city = {};
	        $scope.state = {};
	        
	    });
	    
	    
	  $scope.registerUser = function() {
		  
		  var applicationFlow = $('#txtApplicationFlow').val();
		  //alert(applicationFlow);
	 	  var primaryUserDetails ={
			   firstName:$scope.txtFirstName,
			   middleName:$scope.txtMiddleName,
			   lastName:$scope.txtLastName,
			   familyCode:$scope.stFamilyCode,
			   add1:$scope.stSenderAdd1,
			   add2:$scope.stSenderAdd2,
			   add3:$scope.stSenderAdd3,
			   city:$scope.stSenderCity,
			   state:$scope.state,
			   zipCode:$scope.stZipCode,
			   country:$scope.country,
			   phoneNo:$scope.txtPhoneNo,
			   emailID:$scope.txtEmailId,
			   rName:$scope.txtRitvikName,
			   rActive:$scope.chkIsDecessed,
			   pwd:$scope.stPassword
			   };
 		   var contextPath = "addPrimaryUser.do"+"?primaryUserDetails="+ JSON.stringify(primaryUserDetails)+"&applicationFlow="+applicationFlow;
				$http({
					 method : "POST",
					 url : contextPath
				 }).then(function mySucces(data) {
					 var returnObject = eval(data); // Parse Return Data
					 if(returnObject.data.returnCode=='error') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
							 $("#dvErrAlert").show();
					 }else{
						 if(returnObject.data.applicationFlow=='adminFlow') {
							 $scope.PostDataResponse = returnObject.data.returnMessage;
							 $("#dvErrAlert").show();
						 }else{
							 window.location = 'confirmation.jsp';
						 }
					 }
				 },function myError(data) {
					 var returnObject = eval(data); // Parse Return Data
					 if(returnObject.data.returnCode=='error') {
						$scope.PostDataResponse = returnObject.data.returnMessage;
						$("#dvErrAlert").show();
					 }
				 });
  			};
  			
  			
  		
  			$scope.registerFamilyUser = function() {
  		 		 var familyUserDetails ={
  					   firstName:$scope.txtFirstName,
  					   middleName:$scope.txtMiddleName,
  					   lastName:$scope.txtLastName,
  					   rName:$scope.txtRitvikName,
  					   rActive:$scope.chkIsDecessed,
  					   };
  		 		  var applicationFlow = $('#txtApplicationFlow').val();
  		 		   var contextPath = "addFamilyUser.do"+"?familyUserDetails="+ JSON.stringify(familyUserDetails)+"&applicationFlow="+applicationFlow;
  		 		  // alert(contextPath);
  						$http({
  							 method : "POST",
  							 url : contextPath
  						 }).then(function mySucces(data) {
  							 var returnObject = eval(data); // Parse Return Data
  							 if(returnObject.data.returnCode=='error') {
  								 $scope.PostDataResponse = returnObject.data.returnMessage;
  							 }else{
  								 window.location = 'familyConfirmation.jsp';
  							 }
  						 },function myError(data) {
  							 var returnObject = eval(data); // Parse Return Data
  							 if(returnObject.data.returnCode=='error') {
  								$scope.PostDataResponse = returnObject.data.returnMessage;
  							 }
  						 });
  		  			};
  		  			
  		  		$('#dvErrAlert .close').on('click', function () {
  		  		  $("#dvErrAlert").hide();
                 });
  		  	    
  		  	 $scope.validateUser = function() {
  				 var userDetails ={
  					   userName:$scope.userName,
  					   password:$scope.password
  					   };
  		 		   var contextPath = "validateUser.do"+"?userDetails="+ JSON.stringify(userDetails);
  						$http({
  							 method : "POST",
  							 url : contextPath
  						 }).then(function mySucces(data) {
  							var returnObject = eval(data); // Parse Return Data
  							if(returnObject.data.returnCode=='error') {
  	 		  	        		 $scope.PostDataResponse = returnObject.data.returnMessage;
  	 		  	        		 $("#dvErrAlert").show();
  							 }else{
  								 //$rootScope.PostDataResponse = returnObject.data.userJSONObject.phoneNo;
  								 window.location = 'index.jsp';
  							 }
  						 },function myError(d) {
  							 alert("fail");
  						 });
  		  			};
  		  			
  		  			
  		  			
  		  		$scope.searchUser = function() {	
  		  			
  		  			  var istPhone = $('#phoneNo').val();
  		  			  var familyCode = $('#familyCode').val();
  		  			  var firstName = $('#firstName').val();
  		  			  var lastName = $('#lastName').val();
  		  			  var applicationFlow = $('#applicationFlow').val();
  		  			  var userRole = $('#userRole').val();
  		  			  var contextPath = "getSearchUserObject.do"+"?phoneNo="+ istPhone+"&familyCode="+familyCode+"&firstName="+firstName+"&lastName="+lastName+"&applicationFlow="+applicationFlow;
  		  			  var $table = $("#tblIshtTran");
  		  			if (userRole=='superUserRole'){
  		  			   $table.bootstrapTable({
  		  	            columns: [
  		  	                    {
  		  	                        title: 'Family Code',
  		  	                        align: 'center',
		  	                        valign: 'middle',
  		  	                        field: 'familyID',
  		  	                        sortable: true
  		  	                    },
  		  	                    {
  		  	                        title: 'Name',
  		  	                        align: 'center',
  		  	                        valign: 'middle',
  		  	                        field: 'userName',
  		  	                        sortable: true
  		  	                    },
  		  	                   {
  		  	                        title: 'Phone No',
  		  	                        align: 'center',
		  	                        valign: 'middle',
  		  	                        field: 'phoneNo',
  		  	                        sortable: true
  		  	                    },
  		  	                    {
  		  	                        title: 'Action',
  		  	                        align: 'center',
  		  	                        valign: 'middle',
  		  	                        events: operateEvents,
  		  	                        formatter: operateFormatterSearchSuper
  		  	                    }
  		  	                    
  		  	            ]});
  		  			}
  		  			else{
  		  			$table.bootstrapTable({
  		  	            columns: [
  		  	                    {
  		  	                        title: 'Family Code',
  		  	                        align: 'center',
		  	                        valign: 'middle',
  		  	                        field: 'familyID',
  		  	                        sortable: true
  		  	                    },
  		  	                    {
  		  	                        title: 'Name',
  		  	                        align: 'center',
		  	                        valign: 'middle',
  		  	                        field: 'userName',
  		  	                        sortable: true
  		  	                    },
  		  	                    {
  		  	                        title: 'Phone No',
  		  	                        align: 'center',
		  	                        valign: 'middle',
  		  	                        field: 'phoneNo',
  		  	                        sortable: true
  		  	                    },
  		  	                    {
  		  	                        title: 'Action',
  		  	                        align: 'center',
		  	                        valign: 'middle',
  		  	                        formatter: operateFormatterAdminRole
  		  	                    }
  		  	            ]});
  		  			}
  	  				$http({
							 method : "POST",
							 url : contextPath
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
 								 $scope.PostDataResponse = returnObject.data.returnMessage;
 							 }else{
 								$scope.PostDataResponse = returnObject.data.returnMessage;
 								$scope.ishtLine = returnObject.data.userJSONObject.line;
 								
 								$scope.userObject = returnObject.data.userJSONObject;
 								
 								$scope.name = returnObject.data.userJSONObject.userName;
 								
 								$scope.familyID = returnObject.data.userJSONObject.familyID;
 								
 								$table.bootstrapTable('load', returnObject.data.userJSONObject);
 								
							    $table.bootstrapTable('hideLoading');
							    $table.tableEditor();
							 }
						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  			
		  			window.operateEvents = {
		  		  	        'click .adminAccess': function (e, value, row, index) {
				  		  	        var istPhone = $('#phoneNo').val();
						  			 	var applicationFlow = $('#applicationFlow').val();
					  				 	//var contextPath = "grantAdminAccess.do"+"?phoneNo="+ istPhone+"&applicationFlow="+applicationFlow;
						  			 	var contextPath = "grantAdminAccess.do"+"?phoneNo="+row.phoneNo +"&applicationFlow="+applicationFlow;
					  				$http({
										 method : "POST",
										 url : contextPath
									 }).then(function mySucces(data) {
										var returnObject = eval(data); // Parse Return Data
										if(returnObject.data.returnCode=='error') {
												 $scope.PostDataResponse = returnObject.data.returnMessage;
											 }else{
												 //$rootScope.PostDataResponse = returnObject.data.userJSONObject.phoneNo;
												$scope.PostDataResponse = returnObject.data.returnMessage;
												$scope.ishtLine = returnObject.data.userJSONObject.line;
												$scope.name = returnObject.data.userName;
												$scope.familyID = returnObject.data.userJSONObject.familyID;
												$("#dvErrAlert").show();
											 }
									 },function myError(d) {
										 alert("fail");
									 });
		  		  	        },
		  		  	       'click .payIstavrity': function (e, value, row, index) {
		  		  	           var istPhone =row.phoneNo; //$('#phoneNo').val();
				  			 	var applicationFlow = $('#applicationFlow').val();
				  			 	//alert('istPhone :'+istPhone);
			  				 	//var contextPath = "grantAdminAccess.do"+"?phoneNo="+ istPhone+"&applicationFlow="+applicationFlow;
				  			 	var contextPath = "payisht.jsp?applicationFlow=adminFlow&phoneNo="+row.phoneNo+""; //phoneNo="+row.phoneNo; 
			  				$http({
								 method : "POST",
								 url : contextPath
							 }).then(function mySucces(data) {
								var returnObject = eval(data); // Parse Return Data
								if(returnObject.data.returnCode=='error') {
										 $scope.PostDataResponse = returnObject.data.returnMessage;
									 }else{
										 //$rootScope.PostDataResponse = returnObject.data.userJSONObject.phoneNo;
										$scope.PostDataResponse = returnObject.data.returnMessage;
										$scope.ishtLine = returnObject.data.userJSONObject.line;
										$scope.name = returnObject.data.userName;
										$scope.familyID = returnObject.data.userJSONObject.familyID;
										$("#dvErrAlert").show();
									 }
							 },function myError(d) {
								 alert("fail");
							 });
  		  	        },
		   	        'click .removeAccess': function (e, value, row, index) {
		  		  	        	alert('You click like icon, row: ' + JSON.stringify(index));
		  		  	   },
		  		  	    'click .addFamilyMember': function (e, value, row, index) {
		  		  	    var istPhone =row.phoneNo; //$('#phoneNo').val();
		  			 	var applicationFlow = $('#applicationFlow').val();
		  			 	//alert('istPhone :'+istPhone);
	  				 	var contextPath = "addFamily.jsp?applicationFlow=adminFlow&phoneNo="+row.phoneNo+""; //phoneNo="+row.phoneNo; 
	  				$http({
						 method : "POST",
						 url : contextPath
					 }).then(function mySucces(data) {
						var returnObject = eval(data); // Parse Return Data
						if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 //$rootScope.PostDataResponse = returnObject.data.userJSONObject.phoneNo;
								$scope.PostDataResponse = returnObject.data.returnMessage;
								$scope.ishtLine = returnObject.data.userJSONObject.line;
								$scope.name = returnObject.data.userName;
								$scope.familyID = returnObject.data.userJSONObject.familyID;
								$("#dvErrAlert").show();
							 }
					 },function myError(d) {
						 alert("fail");
					 });
	  				
	  		  	        }
		  		  	        
		  		  	    };
		  		  		
		  		  	function operateFormatterSearchSuper(value, row, index) {
		  		        return [
		  		                		
		  		            //   '<button type="submit" class="btn btn-primary addFamilyMember" title="Add Family Member" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-plus-sign"></span></button>&nbsp;',
		  		              '<a href="addFamily.jsp?applicationFlow=adminFlow&phoneNo='+row.phoneNo+'" class="btn btn-primary" title="Pay Istavrity" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-plus-sign"></span></a>&nbsp;',
		  		              '<a href="payisht.jsp?applicationFlow=adminFlow&phoneNo='+row.phoneNo+'" class="btn btn-primary" title="Pay Istavrity" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-check"></span></a>&nbsp;', 
		  		             //'<button type="submit" class="btn btn-primary payIstavrity" title="Pay Istavrity" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-check"></span></button>&nbsp;',
		  		            '<button type="submit" class="btn btn-primary adminAccess" title="Admin Access" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-user"></span></button>&nbsp;',
		  		            '<button type="submit" class="btn btn-danger removeAccess" title="Remove Access" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-trash"></span></button>&nbsp;'
		                ].join('');
		  		    }; 
		  		    
		  			function operateFormatterAdminRole(value, row, index) {
		  		        return [
		  		            '<a href="payisht.jsp?applicationFlow=adminFlow&phoneNo='+row.phoneNo+'" class="btn btn-primary" title="Pay Istavrity" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-check"></span></a>'
		  		            // '<button type="submit" class="btn btn-primary payIstavrity" title="Pay Istavrity" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-check"></span></button>&nbsp;'
		  		        ].join('');
		  		    };
		  			
		  			$scope.grantAdminAccess = function() {
  		  			 	var istPhone = $('#phoneNo').val();
  		  			 	var applicationFlow = $('#applicationFlow').val();
  		  			 	//alert(applicationFlow);
  	  				 	var contextPath = "grantAdminAccess.do"+"?phoneNo="+ istPhone+"&applicationFlow="+applicationFlow;
  	  				$http({
							 method : "POST",
							 url : contextPath
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
 								 $scope.PostDataResponse = returnObject.data.returnMessage;
 							 }else{
 								 //$rootScope.PostDataResponse = returnObject.data.userJSONObject.phoneNo;
 								$scope.PostDataResponse = returnObject.data.returnMessage;
 								$scope.ishtLine = returnObject.data.userJSONObject.line;
 								$scope.name = returnObject.data.userName;
 								$scope.familyID = returnObject.data.userJSONObject.familyID;
 								$("#dvErrAlert").show();

 							 }
						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  			
		  			$scope.removeAdminAccess = function() {
  		  			 	var istPhone = $('#phoneNo').val();
  		  			 	var applicationFlow = $('#applicationFlow').val();
  		  			 	//alert(applicationFlow);
  	  				 	var contextPath = "grantAdminAccess.do"+"?phoneNo="+ istPhone+"&applicationFlow="+applicationFlow;
  	  				$http({
							 method : "POST",
							 url : contextPath
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
 								 $scope.PostDataResponse = returnObject.data.returnMessage;
 							 }else{
 								 //$rootScope.PostDataResponse = returnObject.data.userJSONObject.phoneNo;
 								$scope.PostDataResponse = returnObject.data.returnMessage;
 								$scope.ishtLine = returnObject.data.userJSONObject.line;
 								$scope.name = returnObject.data.userName;
 								$scope.familyID = returnObject.data.userJSONObject.familyID;
 							 }
						 },function myError(d) {
							 alert("fail");
						 });
		  			};
		  				
		  			$scope.loadAllRitvik = function() {
		  				//alert('loadAllRitvik');
  		  			 	var contextPath = "getAllRitvik.do";
  	  				$http({
							 method : "POST",
							 url : contextPath
						 }).then(function mySucces(data) {
							var returnObject = eval(data); // Parse Return Data
							if(returnObject.data.returnCode=='error') {
								 $scope.PostDataResponse = returnObject.data.returnMessage;
							 }else{
								 $scope.ritvikList = returnObject.data.ritvikJSONObject.ritvikList;
							 }
 						 },function myError(d) {
							 alert("loadAllRitvik - Failed");
						 });
		  			};

		  			
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

		  			$scope.loadIshtTranAdmin = function() {
		  				var istPhone = $('#phoneNo').val();
		  				var userRole = $('#userRole').val();
		  				var contextPath = "getIshtTranAdmin.do"+"?phoneNo="+ istPhone;
		  				var $table = $("#tblIshtTran");
		  				
		  			if (userRole=='superUserRole'){
		  				$table.bootstrapTable({
		  					columns: [
									 {        
										    title: 'Family Code',
										     field: 'familyID',
										     align: 'center',
										     valign: 'middle',
										     sortable: true
									  },
		  					          {
		  					        	  title: 'Name',
		  					        	  field: 'name',
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
		  					        	  title: 'Tran Ref#',
		  					        	  field: 'trnDetails',
		  					        	  align: 'center',
		  					        	  valign: 'middle',
		  					        	  sortable: true
		  					          },
		  					          {
		  					        	  title: 'Bank',
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
		  					          },
		  					          {
		  					        	  title: 'Collected On',
		  					        	  field: 'collectedOn',
		  					        	  align: 'center',
		  					        	  valign: 'middle',
		  					        	  sortable: true,
		  					        	  formatter : formatRowItem 
		  					          },
		  		  	                    {
		  		  	                        title: 'Action',
		  		  	                        align: 'center',
		  		  	                        events: operateEventsAdmin,
		  		  	                        formatter: operateFormatterAdmin
		  		  	                    }
		  					          ]
		  				});
		  			} else {
		  				$table.bootstrapTable({
		  					columns: [
		  					           {        
										      title: 'Family Code',
										      field: 'familyID',
										      align: 'center',
										      valign: 'middle',
										      sortable: true
									  },
		  					          {
		  					        	  title: 'Name',
		  					        	  field: 'name',
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
		  					        	  title: 'Tran Ref#',
		  					        	  field: 'trnDetails',
		  					        	  align: 'center',
		  					        	  valign: 'middle',
		  					        	  sortable: true
		  					          },
		  					          {
		  					        	  title: 'Bank',
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
		  					          },
		  					          {
		  					        	  title: 'Collected On',
		  					        	  field: 'collectedOn',
		  					        	  align: 'center',
		  					        	  valign: 'middle',
		  					        	  sortable: true,
		  					              //footerFormatter: totalNameFormatter	  
		  					          },
		  		  	                    {
		  		  	                        title: 'Action',
		  		  	                        align: 'center',
		  		  	                        formatter: operateFormatter
		  		  	                    }
		  					          ]
		  				});	
		  				
		  				
		  			}
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
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  			window.operateEventsAdmin = {
		  		  	        'click .approve': function (e, value, row, index) {
				  		  	        var istPhone = $('#phoneNo').val();
						  			 var applicationFlow = $('#applicationFlow').val();
						  			   var collecctedOn = $('#col_'+row.index).val();
						  			  //var collecctedOn = $('#col_'+row).val();
						  			    // alert('row 6 :'+index);
						  			       //alert('collecctedOn :'+collecctedOn);
						  			    //  alert('value :'+value);
						  			     //alert(Date.parse(new Date()));
						  			     //alert(Date.parse(collecctedOn));
						  			    if (Date.parse(new Date()) >= Date.parse(collecctedOn)) { 
								        }
						  			   else {
						  				 alert('Either collected On date is invalid or its greater than current date. please change');
						  				 return;
						  			   } 
						  			   
						  		var contextPath = "approveIshtTran.do"+"?receiptNo="+ row.receiptNo+"&applicationFlow="+applicationFlow+"&phoneNo="+istPhone+"&collectedOn="+collecctedOn;
					  				$http({
										 method : "POST",
										 url : contextPath
									 }).then(function mySucces(data) {
										var returnObject = eval(data); // Parse Return Data
										if(returnObject.data.returnCode=='error') {
												 $scope.PostDataResponse = returnObject.data.returnMessage;
											 }else{
												//alert(returnObject.data.returnMessage); 
												$scope.PostDataResponse = returnObject.data.returnMessage;
												$("#dvErrAlert").show();
												  var table = $("#tblIshtTran");
								  		  	         var arrayIds = [];
								  		  	         arrayIds.push(row.receiptNo);
								  		  	         table.bootstrapTable('remove', {
								  		  	        	field: 'receiptNo',
								  		  	        	values: arrayIds
								  		  	    });
											 }
									 },function myError(d) {
										 alert("fail");
									 });
		  		  	        },
		  		  	        'click .reject': function (e, value, row, index){
		  		  	         var table = $("#tblIshtTran");
		  		  	         var arrayIds = [];
		  		  	         arrayIds.push(row.receiptNo);
		  		  	         table.bootstrapTable('remove', {
		  		  	        	field: 'receiptNo',
		  		  	        	values: arrayIds
		  		  	    });
		  		  	  }
		  		  	 };
		  		  		
		  		  	function operateFormatterAdmin(value, row, index) {
		  		  	  
		  		        return [
		  		            '<button type="submit" class="btn btn-primary approve" title="Approve Transcation" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-ok"></span></button>&nbsp;',
		  		            '<button type="submit" class="btn btn-danger reject" title="Reject Transcation" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-remove"></span></button>&nbsp;'
		                ].join('');
		  		    };
		   		function operateFormatter(value, row, index) {
		  		        return [
		  		           // '<button type="submit" class="btn btn-primary approve" title="Approve Transcation" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-ok"></span></button>&nbsp;',
		  		            //'<button type="submit" class="btn btn-danger reject" title="Reject Transcation" onmouseenter="$(this).tooltip("show")"><span class="glyphicon glyphicon-remove"></span></button>&nbsp;'
		                ].join('');
		  		    };
		  		    
		  		 function totalNameFormatter(data) {
		  	        return data.length;
		  	    };
		  		 
		  		function formatRowItem(val, row,index) {
		        	 return '<input id="col_'+row.index+'" name="col_'+row.index+'"  type="date" style="width:100pt;" value="" />';
		        	 //return '<input id="col_'+row+'" name="col_'+row+'"  type="date" style="width:100pt;" value="" />';
		        	// return '<input id="col" name="col"  type="date" style="width:100pt;" value="" />';
		        };
		        
		        function checkDate () {
		        	if (Date.parse(new Date()) < Date.parse($("#EndDate").val())) { 
		            //condition satisfied for today date too.
		        	}
			};
		        
			function totalCurrencyFormatter(data) {
				var total = '<b>'+'$ '+data.toFixed(2)+'</b>';
				
				/* var total = 0;
				  if (data.length > 0) {
				    var field = this.field;
				    total = data.reduce(function(sum, row) {
				      return sum + (+row[field]);
				    }, 0);
				    var num = '$' + total.toFixed(0).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
				    return num;
				  }*/

				  return total;
				};   
				
				
				
		});
