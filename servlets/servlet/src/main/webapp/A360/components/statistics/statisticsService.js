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

        statisticsService.removeSession = function (sessionId) {
            var url = '/servlet/a360/sessions/remove/' + sessionId;
            var deleteResource = $resource(url);
            var deferred = $q.defer();
            deleteResource.remove().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        statisticsService.editSessionIsActive = function (sessionName, isActive) {
            var url = '/servlet/a360/sessions/edit';
            var usersResource = $resource(url);
            var deferred = $q.defer();
            usersResource.save({
                'sessionName' : sessionName,
                'active' : isActive
            }).$promise.then(
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