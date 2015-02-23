function LocationCreateController($scope, $location, LocationService) {

    $scope.locationEntry = new LocationService();

    $scope.save = function() {
        $scope.locationEntry.$save(function() {
            $location.path('/location');
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


function LocationController($scope, LocationService) {

    $scope.locationEntries = LocationService.query();

    $scope.deleteEntry = function(locationEntry) {
        locationEntry.$remove(function() {
            $scope.locationEntries = LocationService.query();
        });
    };
}
