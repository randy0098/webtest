query.controller("queryCtrl", function($scope, $http) {
	$scope.search = function() {
		$http.get("http://localhost:8080/webtest/rest/getOrgList").success(
				function(response) {
					$scope.orgs = response;
				});
	};
});

insert.controller("insertCtrl", function($scope, $http) {
//	$http.post('do-submit.php',myData)
//	.success(function(){
//	    // some code
//	});
//	$scope.insert = function() {
////		var data = $("#insertForm").serializeArray();
////		var data = $("#insertForm");
////		alert(data);
//		alert();
//	};
	alert();
});

//AngularJS只能自动启动第一个ng-app，所以这里要手动启动另外的ng-app。
angular.element(document).ready(function() {
    angular.bootstrap(insertPage, ['insert']);
});



