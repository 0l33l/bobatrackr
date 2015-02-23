function IndexController($scope, NewsService) {

    $scope.newsEntries = NewsService.query();

    $scope.deleteEntry = function(newsEntry) {
        newsEntry.$remove(function() {
            $scope.newsEntries = NewsService.query();
        });
    };
}