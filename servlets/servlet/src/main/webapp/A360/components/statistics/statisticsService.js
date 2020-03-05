(function () {
    'use strict';
    angular.module('a360').service('StatisticsService', StatisticsService);
    StatisticsService.$inject = ['$resource', '$q'];

    function StatisticsService($resource, $q) {
        var statisticsService = {};

        statisticsService.getAllSessions = function () {
            var url = '/servlet/a360/sessions/all';
            var allSessionsResource = $resource(url);

            var deferred = $q.defer();
            allSessionsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        statisticsService.getAllAnswers = function () {
            var url = '/servlet/a360/answers/all';
            var allAnswersResource = $resource(url);

            var deferred = $q.defer();
            allAnswersResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };
        return statisticsService;
    }

})();