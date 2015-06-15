query.controller("queryCtrl", function($scope, $http) {
	$scope.searchBtn = function() {
//		$http.get("http://localhost:8080/webtest/rest/getOrgList").success(
//				function(response) {
//					$scope.orgs = response;
//				});
		var data = $("#queryForm").serialize();
		$http.post('http://localhost:8080/webtest/rest/getOrgList', data).success(
				function(response) {
					$scope.orgs = response;
				});
	};
	
	$scope.updateBtn = function() {
		var boxes = $("input:checked");
		var size = boxes.size();
		if (size == 0) {
			//$("#alertHint").html("��ѡ��һ����¼��Ϣ�����޸ģ�");
			//$("#alert").modal();
			bootbox.alert("��ѡ��һ����¼��Ϣ�����޸ģ�");
		} else if (size > 1) {
			//$("#alertHint").html("��ѡ��һ����¼��Ϣ�����޸ģ�");
			//$("#alert").modal();
			bootbox.alert("��ѡ��һ����¼��Ϣ�����޸ģ�");
		} else if (size == 1) {
			var id = $("input:checked").val();
			$http.get("http://localhost:8080/webtest/rest/getOneOrg/"+id).success(
					function(response) {
						//$("#org").val(response);
						$("#update_id").val(response.id);
						$("#update_name").val(response.name);
						$("#update_email").val(response.email);
						$("#updatePage").modal("show");
					});
		}
	};

	$scope.deleteBtn = function() {
		var boxes = $("input:checked");
		var ids = "";
		boxes.each(function() {
			ids = ids + "," + $(this).val();
		});
		// �ص���һ��","��
		ids = ids.substr(1, ids.length - 1);
		if (ids == "") {
			bootbox.alert("��ѡ��һ����¼��Ϣ����ɾ����");
		} else {
			bootbox.confirm("ȷ��ɾ����¼��?", function(result) {
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
	$scope.update = function() {
		var data = $("#updateForm").serialize();
		$http.post('http://localhost:8080/webtest/rest/updateOrg', data).success(
				function() {
					$scope.hint = "Save Complete";
				});
	};
});

// AngularJSֻ���Զ����ص�һ��ng-app����������Ҫ�ֶ����������ng-app��
angular.element(document).ready(function() {
	angular.bootstrap(insertPage, [ 'insert' ]);
});
angular.element(document).ready(function() {
	angular.bootstrap(updatePage, [ 'update' ]);
});