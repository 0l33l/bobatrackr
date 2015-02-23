services.factory('NewsService', function($resource) {

    return $resource('news/:id', {id: '@id'});
});