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