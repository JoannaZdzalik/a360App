(function () {
    'use strict';
    angular.module('a360').service('ActiveSessionService', ActiveSessionService);
    ActiveSessionService.$inject = ['$resource', '$q'];
    function ActiveSessionService($resource, $q) {
        var activeSessionService = {};
        activeSessionService.getAmountAnswers = function () {
            var url = '/servlet/a360/answers/getActive' ;
            var activeSessionsResource = $resource(url);
            var deferred = $q.defer();
            activeSessionsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (data) {
                    deferred.reject(data);
                });

            return deferred.promise;
        };
        activeSessionService.getAllSessions = function () {
            var url = '/servlet/a360/sessions/get' ;
            var activeSessionsResource = $resource(url);
            var deferred = $q.defer();
            activeSessionsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (data) {
                    deferred.reject(data);
                });

            return deferred.promise;
        };

        return activeSessionService;
    }
})();