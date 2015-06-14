var query = angular.module("query", []);

var insert = angular.module("insert", []);
insert
		.config([
				'$httpProvider',
				function($httpProvider) {
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
				} ]);