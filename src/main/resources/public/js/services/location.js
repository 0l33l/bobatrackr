services.factory('LocationService', function($resource) {

    return $resource('location/:id', {id: '@id'});
});