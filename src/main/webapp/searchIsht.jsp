<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@page import="com.olsa.pojo.RootMDB"%>
<%@page import="com.olsa.utility.OnlineSAConstants"%>
<%@ page errorPage="error.jsp" %>  

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Satsang America - Istavrity</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--<link href="css/datepicker3.css" rel="stylesheet">-->
    <link href="css/styles.css" rel="stylesheet">
    <!--<link href="css/bootstrap-table.css" rel="stylesheet">-->
    <!--Icons-->
    <script src="js/lumino.glyphs.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
    <script src="js/controller.js"></script>

    <!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>
<%
		RootMDB root = (RootMDB)session.getAttribute("userBean");
%>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

    if(session.getAttribute("userBean")==null){
   		response.sendRedirect(request.getContextPath() + "/");
	}
%>
<body ng-app="onlineSA" ng-controller="onlineSAController">
	<%-- <%
		RootMDB root = (RootMDB)session.getAttribute("userBean");
	%>
	 --%>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    
        <div class="container-fluid" ng-init="loadIshtTran()">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%@include file="HeaderBar.jsp" %>
                	
                <a class="navbar-brand" href="#"><span>Satsang America</span> Ishtavrity</a>
                <ul class="user-menu">
                    <li class="dropdown pull-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <svg class="glyph stroked male-user">
                                <use xlink:href="#stroked-male-user"></use>
                            </svg><%=root.getUserName()%> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="#">
                                    <svg class="glyph stroked male-user">
                                        <use xlink:href="#stroked-male-user"></use>
                                    </svg>
                                    Profile</a>
                            </li>
                            <li>
                                <a href="#">
                                    <svg class="glyph stroked gear">
                                        <use xlink:href="#stroked-gear"></use>
                                    </svg>
                                    Settings</a>
                            </li>
                            <li>
                                <a href="login.jsp">
                                    <svg class="glyph stroked cancel">
                                        <use xlink:href="#stroked-cancel"></use>
                                    </svg>
                                    Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
        <!-- /.container-fluid -->
    </nav>
     <!--sidebar-->
    <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
        <ul class="nav menu">
            <li>
                <a href="index.jsp">
                    <svg class="glyph stroked dashboard-dial">
                        <use xlink:href="#stroked-dashboard-dial"></use>
                    </svg>
                    Dashboard</a>
            </li>
            <li>
                <a href="payisht.jsp">
                    <svg class="glyph stroked app-window-with-content">
                        <use xlink:href="#stroked-app-window-with-content"></use>
                    </svg>
                    Pay Ishtavrity</a>
            </li>
            
            <li class="active">
				<a href="searchIsht.jsp">
					<svg class="glyph stroked app-window-with-content">
						<use xlink:href="#stroked-app-window-with-content"></use>
					</svg> Search Ishtavrity</a>
			</li>
			<li><%@include file="includeAdminMenu.jsp" %></li>
			<li role="presentation" class="divider"></li>
			<li>
				<a href="login.jsp">
					<svg class="glyph stroked male-user">
						<use xlink:href="#stroked-male-user"></use>
					</svg> Logout</a>
			</li>
        </ul>

    </div>
    <!--/.sidebar-->

    <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
        <div class="row">
            <ol class="breadcrumb">
                <li>
                    <a href="#">
                        <svg class="glyph stroked home">
                            <use xlink:href="#stroked-home"></use>
                        </svg>
                    </a>
                </li>
                <li class="active">ISHTAVRITY TRANSACTIONS </li>
            </ol>
        </div>
        <!--/.row sub navigation-->

        <!--<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Ishtavrity Deposit Form</h1>
            </div>
            
        </div>-->
        <!--/.row sub header-->

        <div class="row">
            <div class="col-md-12">
           
           
           	<input type="hidden" id="phoneNo" value = <%= root.getPhoneNo()%> name="phoneNo" placeholder="your phone no" ><br>
                <div class="panel panel-default">
                    <div class="panel-heading" id="accordion">ISHTAVRITY TRANSCATIONS</div>
                    <div class="panel-body">
                        <form class="form-horizontal">
                            <fieldset>
                                <!-- Sample Table -->
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <div id="toolbar">
                                                    <!--<button id="remove" class="btn btn-danger" disabled>
                                                        <i class="glyphicon glyphicon-remove"></i> Delete
                                                    </button>-->
                                                </div>
                                                <table id="tblIshtTran" 
                                                       data-toolbar="#toolbar"
                                                       data-search="true"
                                                       data-show-refresh="false"
                                                       data-show-torconggle="false"
                                                       data-show-columns="true"
                                                       data-show-export="false"
                                                       data-detail-view="false"
                                                       data-minimum-count-columns="2"
                                                       data-show-pagination-switch="false"
                                                       data-pagination="true"
                                                       data-id-field="id"
                                                       data-show-footer="false">
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                    
                    <div class="panel-footer">
                     	<small class="text-muted"><%@include file="footer.jsp" %></small>
                    </div>
                </div>

            </div>
            <!--/.col - Left-->
        </div>
        <!--/.row content msg-->
    </div>
    <!--/.main-->

    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-table.js"></script>
    <script>
        var dtData = [{
            "slno": 0, "id": 1, "name": "Name 0", "rtwkname": "Ritwik 1", "swastyayani": 5, "istavrity": 10, "acharyavrity": 2, "dakshina": 14,
            "sangathani": 3, "pronami": 7, "surplus": 10, "ritwiki": 2
        }, {
            "slno": 1, "id": 2, "name": "Name 1", "rtwkname": "Ritwik 2", "swastyayani":
            5, "istavrity": 6, "acharyavrity": 9, "dakshina": 21, "sangathani": 3, "pronami": 1, "surplus": 10, "ritwiki": 2
        }];
        //$('#tblIstavrity').bootstrapTable({ data: dtData });

        $table = $("#tblSample");
        $table.bootstrapTable({
            columns: [
                    {
                        title: 'ID',
                        field: 'id',
                        //rowspan: 2,
                        align: 'center',
                        valign: 'middle',
                        sortable: true
                    }, {
                        title: 'Name',
                        field: 'name',
                        rowspan: 2,
                        align: 'center',
                        valign: 'middle',
                        sortable: true
                        //,inputbox: true,
                        //editable: true
                        //,footerFormatter: totalPriceFormatter
                    },{
                        title: 'Ritwik',
                        field: 'rtwkname',
                        align: 'center',
                        valign: 'middle',
                        sortable: true
                    },{
                        title: 'Swastyayani',
                        field: 'swastyayani',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem,
                        footerFormatter: totalTextFormatter
                    }, {
                        title: 'Istavrity',
                        field: 'istavrity',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Acharyavrity',
                        field: 'acharyavrity',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Dakshina',
                        field: 'dakshina',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Sangathani',
                        field: 'sangathani',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Pronami',
                        field: 'pronami',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Surplus',
                        field: 'surplus',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Ritwiki',
                        field: 'ritwiki',
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                        formatter: formatRowItem
                    }, {
                        title: 'Total',
                        align: 'center',
                        valign: 'middle',
                        sortable: true
                    }

            ]
        });

        $table.bootstrapTable('load', dtData);
        $table.bootstrapTable('hideLoading');

        function formatRowItem(val, row) {
            return '<input id="ritm_' + row.id + '" type="text" style="width:50px;" value="' + val + '" />';
        }

        function totalTextFormatter(data) {
            return 'Total';
        }
        function totalNameFormatter(data) {
            return data.length;
        }
        function totalPriceFormatter(data) {
            var total = 0;
            $.each(data, function (i, row) {
                total += +(row.price.substring(1));
            });
            return '$' + total;
        }
        function responseHandler(res) {
            $.each(res.rows, function (i, row) {
                row.state = $.inArray(row.id, selections) !== -1;
            });
            return res;
        }
        function detailFormatter(index, row) {
            var html = [];
            $.each(row, function (key, value) {
                html.push('<p><b>' + key + ':</b> ' + value + '</p>');
            });
            return html.join('');
        }
        
   
     </script>
</body>

</html>
