
var xAuthTokenHeaderName = 'x-auth-token';

angular.module('exampleApp', ['ngRoute', 'ngCookies', 'exampleApp.services'])
	.config(
		[ '$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {

			$routeProvider.when('/create', {
				templateUrl: 'partials/create.html',
				controller: NewsCreateController
			});

			$routeProvider.when('/location/create', {
				templateUrl: 'partials/createLocation.html',
				controller: LocationCreateController
			});

			$routeProvider.when('/edit/:id', {
				templateUrl: 'partials/edit.html',
				controller: NewsEditController
			});

            $routeProvider.when('/location/edit/:id', {
                templateUrl: 'partials/editLocation.html',
                controller: LocationEditController
            });

			$routeProvider.when('/login', {
				templateUrl: 'partials/login.html',
				controller: LoginController
			});

            $routeProvider.when('/location', {
				templateUrl: 'partials/location.html',
				controller: LocationController
			});

			$routeProvider.otherwise({
				templateUrl: 'partials/index.html',
				controller: IndexController
			});

			$locationProvider.hashPrefix('!');

			/* Intercept http errors */
			var interceptor = function ($rootScope, $q, $location) {

		        function success(response) {
		            return response;
		        }

		        function error(response) {

		            var status = response.status;
		            var config = response.config;
		            var method = config.method;
		            var url = config.url;

		            if (status == 401) {
		            	$location.path( "/login" );
		            } else {
		            	$rootScope.error = method + " on " + url + " failed with status " + status;
		            }

		            return $q.reject(response);
		        }

		        return function (promise) {
		            return promise.then(success, error);
		        };
		    };
		    $httpProvider.responseInterceptors.push(interceptor);

		} ]

	).run(function($rootScope, $http, $location, $cookieStore, LoginService) {

		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});

		$rootScope.hasRole = function(role) {

			if ($rootScope.user === undefined) {
				return false;
			}

			if ($rootScope.user.roles[role] === undefined) {
				return false;
			}

			return $rootScope.user.roles[role];
		};



		$rootScope.logout = function() {
			delete $rootScope.user;
			delete $http.defaults.headers.common[xAuthTokenHeaderName];
			$cookieStore.remove('user');
			$location.path("/login");
		};

		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		$location.path("/login");
		var user = $cookieStore.get('user');
		if (user !== undefined) {
			$rootScope.user = user;
			$http.defaults.headers.common[xAuthTokenHeaderName] = user.token;

			$location.path(originalPath);
		}

	});


function IndexController($scope, NewsService) {

	$scope.newsEntries = NewsService.query();

	$scope.deleteEntry = function(newsEntry) {
		newsEntry.$remove(function() {
			$scope.newsEntries = NewsService.query();
		});
	};
}

function LocationController($scope, LocationService) {

	$scope.locationEntries = LocationService.query();

	$scope.deleteEntry = function(locationEntry) {
        locationEntry.$remove(function() {
			$scope.locationEntries = LocationService.query();
		});
	};
}


function NewsEditController($scope, $routeParams, $location, NewsService) {

	$scope.newsEntry = NewsService.get({id: $routeParams.id});

	$scope.save = function() {
		$scope.newsEntry.$save(function() {
			$location.path('/');
		});
	};
}


function LocationEditController($scope, $routeParams, $location, LocationService) {

    $scope.locationEntry = LocationService.get({id: $routeParams.id});

    $scope.save = function() {
        $scope.locationEntry.$save(function() {
            $location.path('/location');
        });
    };
}

function NewsCreateController($scope, $location, NewsService) {

	$scope.newsEntry = new NewsService();

	$scope.save = function() {
		$scope.newsEntry.$save(function() {
			$location.path('/');
		});
	};
}

function LocationCreateController($scope, $location, LocationService) {

    $scope.locationEntry = new LocationService();

    $scope.save = function() {
        $scope.locationEntry.$save(function() {
            $location.path('/location');
        });
    };
}


function LoginController($scope, $rootScope, $location, $http, $cookieStore, LoginService) {

	$scope.login = function() {
		LoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function(user) {
			$rootScope.user = user;
			$http.defaults.headers.common[ xAuthTokenHeaderName ] = user.token;
			$cookieStore.put('user', user);
			$location.path("/");
		});
	};
}


var services = angular.module('exampleApp.services', ['ngResource']);

services.factory('LoginService', function($resource) {

	return $resource(':action', {},
			{
				authenticate: {
					method: 'POST',
					params: {'action' : 'authenticate'},
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				}
			}
		);
});

services.factory('NewsService', function($resource) {

	return $resource('news/:id', {id: '@id'});
});

services.factory('LocationService', function($resource) {

        return $resource('location/:id', {id: '@id'});
});