(function () {
    'use strict';
    angular.module('a360').service('AppParamsService', AppParamsService);
    AppParamsService.$inject = ['$resource', '$q'];
    function AppParamsService($resource, $q) {
        var appParamsService = {};

        appParamsService.editAppParams = function(auth, debug, from, host, port, username, password) {
            var appParamsResource = $resource('/servlet/a360/config/edit');
            var deferred = $q.defer();
            appParamsResource.save({'auth' : auth, 'debug':debug, 'from': from, 'host':host , 'port':port, 'username': username, 'password': password})
                .$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };

        appParamsService.getAppParams = function() {
            var appParamsResource = $resource('/servlet/a360/config');
            var deferred = $q.defer();
            appParamsResource.get().$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };
        return appParamsService;
    }
})();