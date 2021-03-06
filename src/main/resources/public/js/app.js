'use strict';

var xAuthTokenHeaderName = 'x-auth-token';

var app = angular.module('bobaApp', ['ngRoute', 'ngCookies', 'bobaApp.services'])
	.config(
		[ '$routeProvider', '$locationProvider', '$httpProvider', '$provide', function($routeProvider, $locationProvider, $httpProvider, $provide) {

			$routeProvider.when('/news/create', {
				templateUrl: 'partials/news/create.html',
				controller: NewsCreateController
			});

			$routeProvider.when('/location/create', {
				templateUrl: 'partials/location/create.html',
				controller: LocationCreateController
			});

			$routeProvider.when('/news/edit/:id', {
				templateUrl: 'partials/news/edit.html',
				controller: NewsEditController
			});

            $routeProvider.when('/location/edit/:id', {
                templateUrl: 'partials/location/edit.html',
                controller: LocationEditController
            });

			$routeProvider.when('/login', {
				templateUrl: 'partials/login.html',
				controller: LoginController
			});

            $routeProvider.when('/location', {
				templateUrl: 'partials/location/location.html',
				controller: LocationController
			});

            $routeProvider.when('/news', {
				templateUrl: 'partials/news/news.html',
				controller: NewsController
			});

			$routeProvider.otherwise({
				templateUrl: 'partials/index.html',
				controller: IndexController
			});

			$locationProvider.hashPrefix('!');

            // Intercept http calls.
            $provide.factory('MyHttpInterceptor', function ($q, $location) {
                return {
                    // On request success
                    request: function (config) {
                        // console.log(config); // Contains the data about the request before it is sent.

                        // Return the config or wrap it in a promise if blank.
                        return config || $q.when(config);
                    },

                    // On request failure
                    requestError: function (rejection) {
                        // console.log(rejection); // Contains the data about the error on the request.

                        // Return the promise rejection.
                        return $q.reject(rejection);
                    },

                    // On response success
                    response: function (response) {
                        // console.log(response); // Contains the data from the response.

                        // Return the response or promise.
                        return response || $q.when(response);
                    },

                    // On response failure
                    responseError: function error(response) {
                            var status = response.status;
                            var config = response.config;
                            var method = config.method;
                            var url = config.url;

                            if (status == 401) {
                               $location.path("/login");
                           } else if (status == 403) {
                               $location.path("/login");
                            } else {
                            	$rootScope.error = method + " on " + url + " failed with status " + status;
                            }

                            return $q.reject(response);
                        }
                };
            });
            $httpProvider.interceptors.push('MyHttpInterceptor');

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

var services = angular.module('bobaApp.services', ['ngResource']);