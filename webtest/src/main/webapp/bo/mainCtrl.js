query.controller("queryCtrl", function($scope, $http) {
	$scope.queryBtn = function() {
		var bootstrapValidator = $('#queryForm').data('bootstrapValidator');
		var result = bootstrapValidator.isValid();
		if(result == true){
			var data = $("#queryForm").serialize();
			$http.post('http://localhost:8080/webtest/rest/getOrgList', data).success(
					function(response) {
						$scope.orgs = response;
					});
		}
	};
	
	$scope.updateBtn = function() {
		var boxes = $("input:checked");
		var size = boxes.size();
		if (size == 0) {
			//$("#alertHint").html("请选中一条记录信息进行修改！");
			//$("#alert").modal();
			bootbox.alert("请选中一条记录信息进行修改！");
		} else if (size > 1) {
			//$("#alertHint").html("请选中一条记录信息进行修改！");
			//$("#alert").modal();
			bootbox.alert("请选中一条记录信息进行修改！");
		} else if (size == 1) {
			var id = $("input:checked").val();
			$http.get("http://localhost:8080/webtest/rest/getOneOrg/"+id).success(
					function(response) {
						//$("#org").val(response);
						$("#update_id").val(response.id);
						$("#update_name").val(response.name);
						$("#update_email").val(response.email);
						$("#updatePage").modal({
							backdrop: 'static',
							show: true
						});
					});
		}
	};

	$scope.deleteBtn = function() {
		var boxes = $("input:checked");
		var ids = "";
		boxes.each(function() {
			ids = ids + "," + $(this).val();
		});
		//截掉第一个","。
		ids = ids.substr(1, ids.length - 1);
		if (ids == "") {
			bootbox.alert("请选中一条记录信息进行删除！");
		} else {
			bootbox.confirm("确认删除记录吗？", function(result) {
				if (result == true) {
					$http.get(
							"http://localhost:8080/webtest/rest/deleteOrgs/"
									+ ids).success(function(response) {

					});
				}
			});
		}
	};
});

insert.controller("insertCtrl", function($scope, $http) {
	$scope.showFlag = false;
	$scope.insertBtn = function() {
		var bootstrapValidator = $('#insertForm').data('bootstrapValidator');
		var result = bootstrapValidator.isValid();
		if (result == true) {
			var data = $("#insertForm").serialize();
			$http.post('http://localhost:8080/webtest/rest/addOrg', data)
					.success(
							function() {
								$scope.showFlag = true;
								$scope.hint = "保存成功！";
								$(':input', '#insertForm').not(
										':button, :submit, :reset, :hidden')
										.val('').removeAttr('checked')
										.removeAttr('selected');
								bootstrapValidator.resetForm();
							});
		}
	};
});

update.controller("updateCtrl", function($scope, $http) {
	$scope.update = function() {
		var data = $("#updateForm").serialize();
		$http.post('http://localhost:8080/webtest/rest/updateOrg', data).success(
				function() {
					$scope.hint = "保存成功！";
				});
	};
});

//AngularJS默认只装载第一个ng-app，所以这里要手动装载其他的ng-app！
angular.element(document).ready(function() {
	angular.bootstrap(insertPage, [ 'insert' ]);
});
angular.element(document).ready(function() {
	angular.bootstrap(updatePage, [ 'update' ]);
});