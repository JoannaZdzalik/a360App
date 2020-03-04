(function () {
    'use strict';
    angular.module('a360').service('StatisticsService', StatisticsService);
    StatisticsService.$inject = ['$resource', '$q'];
    function StatisticsService($resource, $q) {
        var statisticsService = {};
        statisticsService.getAmountAnswers = function () {
            var url = '/servlet/a360/answers/getActive' ;
            var statisticsResource = $resource(url);
            var deferred = $q.defer();
            statisticsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (data) {
                    deferred.reject(data);
                });

            return deferred.promise;
        };
        statisticsService.getAllSessions = function () {
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

        return statisticsService;
    }
})();