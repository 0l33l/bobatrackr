app.controller("HeaderController", function($scope, $location) {

    $scope.isActive = function (viewLocation) {
        var active = (viewLocation === $location.path());
        if (!active) active = ($location.path().indexOf(viewLocation) == 0)
        return active;
    };

});