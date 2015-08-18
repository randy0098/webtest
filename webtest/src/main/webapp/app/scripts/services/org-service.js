'use strict';

/**
 * @ngdoc service
 * @name angulartestApp.orgService
 * @description # orgService Service in the angulartestApp.
 */
angular.module('angulartestApp').factory('orgService',
		[ '$http', '$q', function($http, $q) {
			return {
				queryOrgPage : function(form) {
					//解决rest端空字符串不能自动转换成json对象的问题
					if(form==null || form==""){
						form = "{}";
					}
					
					var deferred = $q.defer();
					$http({
						"method" : "POST",
						"url" : "http://localhost:8080/webtest/rest/getOrgListPage",
						"data" : form
					}).success(function(result) {
						deferred.resolve(result);
					}).error(function(error) {
						console.log(error);
						deferred.reject(error);
					});
					return deferred.promise;
				},
				queryOrg : function(form) {
					//解决rest端空字符串不能自动转换成json对象的问题
					if(form==null || form==""){
						form = "{}";
					}
					
					var deferred = $q.defer();
					$http({
						"method" : "POST",
						"url" : "http://localhost:8080/webtest/rest/getOrgList",
						"data" : form
					}).success(function(result) {
						deferred.resolve(result);
					}).error(function(error) {
						console.log(error);
						deferred.reject(error);
					});
					return deferred.promise;
				},
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
				},
				getOneOrg : function(id) {
					var deferred = $q.defer();
					$http({
						"method" : "GET",
						"url" : "http://localhost:8080/webtest/rest/getOneOrg/"+id
					}).success(function(result) {
						console.log(result);
						deferred.resolve(result);
					}).error(function(error) {
						console.log(error);
						deferred.reject(error);
					});
					return deferred.promise;
				},
				updateOrg : function(org) {
					var deferred = $q.defer();
					$http({
						"method" : "POST",
						"url" : "http://localhost:8080/webtest/rest/updateOrg",
						"data" : org
					}).success(function(result) {
						deferred.resolve(result);
					}).error(function(error) {
						console.log(error);
						deferred.reject(error);
					});
					return deferred.promise;
				},
				deleteOrg : function(ids) {
					var deferred = $q.defer();
					$http({
						"method" : "GET",
						"url" : "http://localhost:8080/webtest/rest/deleteOrgs/"+ids
					}).success(function(result) {
						console.log(result);
						deferred.resolve(result);
					}).error(function(error) {
						console.log(error);
						deferred.reject(error);
					});
					return deferred.promise;
				}
			}
		} ]);