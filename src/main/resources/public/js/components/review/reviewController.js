function ReviewCreateController($scope, $review, ReviewService) {

    $scope.reviewEntry = new ReviewService();

    $scope.save = function() {
        $scope.reviewEntry.$save(function() {
            $review.path('/review');
        });
    };
}

function ReviewEditController($scope, $routeParams, $review, ReviewService) {

    $scope.reviewEntry = ReviewService.get({id: $routeParams.id});

    $scope.save = function() {
        $scope.reviewEntry.$save(function() {
            $review.path('/review');
        });
    };
}


function ReviewController($scope, ReviewService) {

    $scope.reviewEntries = ReviewService.query();

    $scope.deleteEntry = function(reviewEntry) {
        reviewEntry.$remove(function() {
            $scope.reviewEntries = ReviewService.query();
        });
    };
}
