function NewsController($scope, NewsService) {

    $scope.newsEntries = NewsService.query();

    $scope.deleteEntry = function(newsEntry) {
        newsEntry.$remove(function() {
            $scope.newsEntries = NewsService.query();
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



function NewsCreateController($scope, $location, NewsService) {

    $scope.newsEntry = new NewsService();

    $scope.save = function() {
        $scope.newsEntry.$save(function() {
            $location.path('/news');
        });
    };
}