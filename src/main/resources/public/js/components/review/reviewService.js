services.factory('ReviewService', function($resource) {

    return $resource('review/:id', {id: '@id'});
});