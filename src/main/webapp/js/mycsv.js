var app = angular.module('myApp', []);
			app.controller('appCtrl',function($scope,$http) {
			
	// Prepare Excel data:
	$scope.fileName = "report";
	$scope.exportData = [];
  // Headers:
	$scope.exportData.push(["sno", "Movie", "Actor"]);
  // Data:
	angular.forEach($scope.jsonToExport, function(value, key) {
		$scope.exportData.push([value.col1data, value.col2data, value.col3data]);
	
	});
	
	$scope.exportReport=function(){
		 $("#reportTable").table2excel({
             filename: "report.xls"
         });
	}
	$scope.getReceipt= function(receiptNo){
			var contextPath = "downloadReceipt.do?receiptNo="+receiptNo;
		
		$http({
				 method : "GET",
				 url : contextPath,
				 responseType: 'arraybuffer',
				 headers: {'Content-Type': 'application/json' ,'responseType':'arraybuffer'},
				
			 }).then(function mySucces(data) {
				 $scope.json = angular.toJson(data.data);
				  var obj = JSON.parse($scope.json);
				  var call = null;
				  var fileName = receiptNo+".pdf";
		            var a = document.createElement("a");
		            document.body.appendChild(a);
		    
		             
		                
		                var byteArray = new Uint8Array(data.data);
		                
		                file = new Blob([data.data], {type: 'application/pdf'});
		                var fileURL = window.URL.createObjectURL(file);
		                a.href = fileURL;
		                a.download = fileName;
		               a.click();
		               
		              
		              
			 },function myError(d) {
				 alert("fail  "+  d);
			 });
		
	} 
	$scope.myReport=function(){
		var removeTBody = document.getElementById("reportTBody");
		removeTBody.innerHTML = "";
		 var contextPath = "reports.do";
		 $scope.jsonToExport=[];
 		$http({
				 method : "POST",
				 url : contextPath,
				 data:{
					 "contact":document.getElementById("PhoneNo").value,
					"familyCode":$scope.familyCode,
					"receiptNo":$scope.receiptNo,
					"toDate":$scope.toDate,
					"fromDate":$scope.fromDate
				 },
				 headers: {'Content-Type': 'application/json'}
			 }).then(function mySucces(data) {
				 $scope.json = angular.toJson(data.data);
				  var obj = JSON.parse($scope.json);
				  var call = null;
				  for(var i=0;i<obj.length;i++){
					  call=
					  "<td scope='row'>"+obj[i].familyID +"</td>"+
					  "<td scope='row'>"+obj[i].receiptNo +"</td>"+
					  "<td scope='row'>"+(obj[i].dtIshtDate!=undefined ? obj[i].dtIshtDate : '-') +"</td>"+
					  "<td scope='row'>"+(obj[i].stTrnNo!=undefined ? obj[i].stTrnNo : '-') +"</td>"+
					  "<td scope='row'>"+(obj[i].stBankName!=undefined ? obj[i].stBankName : '-') +"</td>"+
					  "<td scope='row'>"+obj[i].total +"</td>"+
					  "<td scope='row'><i id='"+obj[i].receiptNo+"' class='fa fa-file-pdf-o cursror-pointer' onClick='getReceipt(this)'></i></td>";
					  $('#reportTBody').append('<tr align="center">' + call + '</tr>');
					  call = null;
				 
				  }
				  
				 
				  
				 /* var blob = new Blob([document.getElementById('reportTable').innerHTML], {
					  
					  type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
				  });
				  saveAs(blob, "Report.xlsx");*/
				  
			 },function myError(d) {
				 alert("fail  "+  d);
			 });
 		
 		 
	};//repoort method
	
	

	
	
	   $scope.download = function() {
       	function datenum(v, date1904) {
	          if(date1904) v+=1462;
	          var epoch = Date.parse(v);
	          return (epoch - new Date(Date.UTC(1899, 11, 30))) / (24 * 60 * 60 * 1000);
       	};
       	function getSheet(data, opts) {
       		var ws = {};
           	var range = {s: {c:10000000, r:10000000}, e: {c:0, r:0 }};
           	for(var R = 0; R != data.length; ++R) {
	            	for(var C = 0; C != data[R].length; ++C) {
	            		if(range.s.r > R) range.s.r = R;
	            		if(range.s.c > C) range.s.c = C;
	            		if(range.e.r < R) range.e.r = R;
	            		if(range.e.c < C) range.e.c = C;
	            		var cell = {v: data[R][C] };
	            		if(cell.v == null) continue;
	            		var cell_ref = XLSX.utils.encode_cell({c:C,r:R});
	
	            		if(typeof cell.v === 'number') cell.t = 'n';
	            		else if(typeof cell.v === 'boolean') cell.t = 'b';
	            		else if(cell.v instanceof Date) {
		            		cell.t = 'n'; cell.z = XLSX.SSF._table[14];
		            		cell.v = datenum(cell.v);
	            		}
	            		else cell.t = 's';
	            				
	            				ws[cell_ref] = cell;
	            	}//for 
           	}//for
           	if(range.s.c < 10000000) ws['!ref'] = XLSX.utils.encode_range(range);
           	return ws;
           };//getSheet
           	
           function Workbook() {
           	if(!(this instanceof Workbook)) return new Workbook();
           	this.SheetNames = [];
           	this.Sheets = {};
           }
           	 
           var wb = new Workbook(), ws = getSheet(scope.data());
           /* add worksheet to workbook */
           wb.SheetNames.push($scope.fileName);
           wb.Sheets[scope.fileName] = ws;
           var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});

           function s2ab(s) {
           		var buf = new ArrayBuffer(s.length);
           		var view = new Uint8Array(buf);
           		for (var i=0; i!=s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
           		return buf;
           }
           	
       	saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), scope.fileName+'.xlsx');
       		
       	};
	
	
	
	
});

/*app
  .directive('excelExport',
    function () {
      return {
        restrict: 'A',
        scope: {
        	fileName: "@",
            data: "&exportData"
        },
        replace: true,
        template: '<button class="btn btn-primary btn-ef btn-ef-3 btn-ef-3c mb-10" ng-click="myReport()"><span class="glyphicon glyphicon-search"></span> Report <i class="fa fa-download"></i></button>',
        link: function (scope, element) {
        
        	
       scope.myReport=function(){
    	   
    	   var familyCode = document.getElementById("familyCodeId").value;
    	   var receiptno = document.getElementById("receiptnoId").value;
    	   var toDate = document.getElementById("toDateId").value;
    	   var fromDate = document.getElementById("fromDateId").value;
    	 
    	   var contextPath = "reports.do";
    		http({
				 method : "POST",
				 url : contextPath,
				 data:{
				 },
				 headers: {'Content-Type': 'application/json'}
			 }).then(function mySucces(data) {
				alert("===========");
			 },function myError(d) {
				 alert("fail");
			 });
    	   
    	   
    	   
    	   
    	   
    	   dataValidationReport();
           //scope.download();
        };//close myReport
        
        scope.download = function() {
        	function datenum(v, date1904) {
	          if(date1904) v+=1462;
	          var epoch = Date.parse(v);
	          return (epoch - new Date(Date.UTC(1899, 11, 30))) / (24 * 60 * 60 * 1000);
        	};
        	function getSheet(data, opts) {
        		var ws = {};
            	var range = {s: {c:10000000, r:10000000}, e: {c:0, r:0 }};
            	for(var R = 0; R != data.length; ++R) {
	            	for(var C = 0; C != data[R].length; ++C) {
	            		if(range.s.r > R) range.s.r = R;
	            		if(range.s.c > C) range.s.c = C;
	            		if(range.e.r < R) range.e.r = R;
	            		if(range.e.c < C) range.e.c = C;
	            		var cell = {v: data[R][C] };
	            		if(cell.v == null) continue;
	            		var cell_ref = XLSX.utils.encode_cell({c:C,r:R});
	
	            		if(typeof cell.v === 'number') cell.t = 'n';
	            		else if(typeof cell.v === 'boolean') cell.t = 'b';
	            		else if(cell.v instanceof Date) {
		            		cell.t = 'n'; cell.z = XLSX.SSF._table[14];
		            		cell.v = datenum(cell.v);
	            		}
	            		else cell.t = 's';
	            				
	            				ws[cell_ref] = cell;
	            	}//for 
            	}//for
            	if(range.s.c < 10000000) ws['!ref'] = XLSX.utils.encode_range(range);
            	return ws;
            };//getSheet
            	
            function Workbook() {
            	if(!(this instanceof Workbook)) return new Workbook();
            	this.SheetNames = [];
            	this.Sheets = {};
            }
            	 
            var wb = new Workbook(), ws = getSheet(scope.data());
             add worksheet to workbook 
            wb.SheetNames.push(scope.fileName);
            wb.Sheets[scope.fileName] = ws;
            var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});

            function s2ab(s) {
            		var buf = new ArrayBuffer(s.length);
            		var view = new Uint8Array(buf);
            		for (var i=0; i!=s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
            		return buf;
            }
            	
        	saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), scope.fileName+'.xlsx');
        		
        	};
        
        }
      };
    }
 );

function dataValidationReport(){
	var familyCode = document.getElementById("familyCodeId").value;
	var receiptno = document.getElementById("receiptnoId").value;
	var toDate = document.getElementById("toDateId").value;
	var fromDate = document.getElementById("fromDateId").value;
	
	
	if (familyCode.trim().length > 0){
		document.getElementById("fromDateId").textContent="";
	}else{
		document.getElementById("fromDateId").textContent="";
		return false;
	}
}*/
			
function getReceipt(trnObj){
	
	var scope = angular.element('[ng-controller=appCtrl]').scope();
    scope.$apply(function () {
	   
	    scope.getReceipt(trnObj.id);
    });
}