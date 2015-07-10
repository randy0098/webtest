'use strict';

/**
 * @ngdoc overview
 * @name angulartestApp
 * @description
 * # angulartestApp
 *
 * Main module of the application.
 */
angular
  .module('angulartestApp', [
    'ngRoute',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/org-query.html',
        controller: 'OrgQueryCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
//      .when('/org-query', {
//        templateUrl: 'views/org-query.html',
//        controller: 'OrgQueryCtrl'
//      })
//      .otherwise({
//        redirectTo: '/'
//      });
  });
