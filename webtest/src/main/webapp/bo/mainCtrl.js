query.controller("queryCtrl", function($scope, $http) {
	$scope.search = function() {
		$http.get("http://localhost:8080/webtest/rest/getOrgList").success(
				function(response) {
					$scope.orgs = response;
				});
	};
	
	$scope.update = function() {
		var boxes = $("input:checked");
		var size = boxes.size();
		if (size == 0) {
			$("#alertHint").html("请选中一条记录信息进行修改！");
			$("#alert").modal();
		} else if (size > 1) {
			$("#alertHint").html("请选中一条记录信息进行修改！");
			$("#alert").modal();
		} else if (size == 1) {
			var id = $("input:checked").val();
			$http.get("http://localhost:8080/webtest/rest/getOneOrg/"+id).success(
					function(response) {
						//$("#org").val(response);
						$("#update_name").val(response.name);
						$("#update_email").val(response.email);
						$("#updatePage").modal("show");
					});
		}
	};

});

insert.controller("insertCtrl", function($scope, $http) {
	$scope.insert = function() {
		// var data = $("#insertForm").serializeArray();
		var data = $("#insertForm").serialize();
		$http.post('http://localhost:8080/webtest/rest/addOrg', data).success(
				function() {
					$scope.hint = "Save Complete";
				});
	};
});

update.controller("updateCtrl", function($scope, $http) {
	
});

// AngularJS只能自动加载第一个ng-app，所以这里要手动加载另外的ng-app。
angular.element(document).ready(function() {
	angular.bootstrap(insertPage, [ 'insert' ]);
});
angular.element(document).ready(function() {
	angular.bootstrap(updatePage, [ 'update' ]);
});