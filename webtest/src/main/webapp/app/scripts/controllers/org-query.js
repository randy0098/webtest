'use strict';

/**
 * @ngdoc function
 * @name angulartestApp.controller:OrgQueryCtrl
 * @description # OrgQueryCtrl Controller of the angulartestApp
 */
angular.module('angulartestApp').controller(
		'OrgQueryCtrl',
		function($scope, $http, $modal, $log) {
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