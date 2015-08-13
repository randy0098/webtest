'use strict';

/**
 * @ngdoc function
 * @name angulartestApp.controller:OrgQueryCtrl
 * @description # OrgQueryCtrl Controller of the angulartestApp
 */
angular.module('angulartestApp').controller(
		'OrgQueryCtrl',
		function($scope, orgService, $http, $modal, $log) {
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