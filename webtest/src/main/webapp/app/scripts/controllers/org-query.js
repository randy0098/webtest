'use strict';

/**
 * @ngdoc function
 * @name angulartestApp.controller:OrgQueryCtrl
 * @description # OrgQueryCtrl Controller of the angulartestApp
 */
angular.module('angulartestApp').controller(
		'OrgQueryCtrl',
		function($scope, orgService, $http, $modal, $log) {
			
//			$scope.selectCheckbox = function(e) {
//				var checkboxObj = $(e).find("input[name='grid_checkbox']");
//				alert(checkboxObj);
//				var value = checkboxObj.is(":checked");
//				alert(value);
//				if(value == true){
//					checkboxObj.prop("checked",false);
//					$(e).css("background", "");
//				}else{
//					checkboxObj.prop("checked",true);
//					$(e).css("background", "#fbec87");
//				}
//			};
			
			$scope.queryBtn = function() {
				var data = $("#queryForm").serialize();
				$http
						.get('http://localhost:8080/webtest/rest/getOrgList',
								data).success(function(response) {
							$scope.orgs = response;
						}).error(function(data, status, headers, config) {

						});
			};

			$scope.addBtn = function() {
				var modalInstance = $modal.open({
					animation : true,
					templateUrl : 'views/org-add.html',
					controller : 'addOrgCtrl',
					size : 'lg'
				});

				modalInstance.result.then(function() {
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};

			$scope.updateBtn = function() {
				var checkboxes = $("input[type='checkbox']:checked");
				if (checkboxes.size() == 0) {
					alert("请选中一条记录！");
				}
				if (checkboxes.size() > 1) {
					alert("只能选中一条记录进行修改！");
				}
				if (checkboxes.size() == 1) {
					var id = checkboxes[0].value;
					var modalInstance = $modal.open({
						animation : true,
						templateUrl : 'views/org-update.html',
						controller : 'updateOrgCtrl',
						size : 'lg',
						resolve : {
							org : function() {
								return orgService.getOneOrg(id);
							}
						}
					});

					modalInstance.result.then(function() {
					}, function() {
						$log.info('Modal dismissed at: ' + new Date());
					});
				}
			};

			$scope.deleteBtn = function() {
				var checkboxes = $("input[type='checkbox']:checked");
				if (checkboxes.size() == 0) {
					alert("请选中至少一条记录！");
				} else {
					if (confirm("确定要删除记录吗？")) {
						var ids = "";
						checkboxes.each(function(i, box) {
							ids = ids + "," + box.value;
						});
						ids = ids.substr(1, ids.length - 1);
						orgService.deleteOrg(ids).then(function(result) {
							alert("删除成功！");
						});
					}
				}
			};
		});

angular.module('angulartestApp').controller('addOrgCtrl',
		function($scope, orgService, $modalInstance) {
			$scope.ok = function() {
				orgService.addOrg($scope.org).then(function(result) {
					$modalInstance.close();
				});
			};

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};
		});

angular.module('angulartestApp').controller('updateOrgCtrl',
		function($scope, orgService, $modalInstance, org) {
			$scope.org = org;

			$scope.ok = function() {
				orgService.updateOrg($scope.org).then(function(result) {
					$modalInstance.close();
				});
			};

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};
		});