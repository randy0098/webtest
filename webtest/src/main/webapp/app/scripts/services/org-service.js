'use strict';

/**
 * @ngdoc service
 * @name angulartestApp.orgService
 * @description # orgService Service in the angulartestApp.
 */
angular.module('angulartestApp').factory('orgService',
		[ '$http', '$q', function($http, $q) {
			return {
				addOrg : function(org) {
					var deferred = $q.defer();
					$http({
						"method" : "POST",
						"url" : "http://localhost:8080/webtest/rest/addOrg",
						"data" : org
					}).success(function(result) {
						deferred.resolve(result);
					}).error(function(error) {
						console.log(error);
						deferred.reject(error);
					});
					return deferred.promise;
				}
			}
		} ]);