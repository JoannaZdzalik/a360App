(function () {
    'use strict';
    angular.module('a360').service('ActiveSessionService', ActiveSessionService);
    ActiveSessionService.$inject = ['$resource', '$q'];
    function ActiveSessionService($resource, $q) {
        var activeSessionService = {};
        activeSessionService.getAllSessions = function () {
            var url = '/servlet/a360/sessions/get' ;
            var activeSessionsResource = $resource(url);

            var deferred = $q.defer();
            activeSessionsResource.get().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };
        return activeSessionService;
    }
})();