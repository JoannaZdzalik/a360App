(function () {
    'use strict';
    angular.module('a360').service('RegisterService', RegisterService);
    RegisterService.$inject = ['$resource', '$q'];
    function RegisterService($resource, $q) {
        var registerService = {};

        registerService.createUser = function(login, password) {
            var sessionResource = $resource('/servlet/a360/users/create');
            var deferred = $q.defer();
            sessionResource.save({'login': login, 'password': password}).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };
        return registerService;
    }
})();