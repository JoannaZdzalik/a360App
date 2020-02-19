(function () {
    'use strict';
    angular.module('a360').service('SessionService', SessionService);
    SessionService.$inject = ['$resource', '$q'];
    function SessionService($resource, $q) {
        var sessionService = {};
        var sessionResource = $resource('/servlet/a360/sessions/create');
        sessionService.send = function(sessionName, endDate,  participants) {
            var deferred = $q.defer();
            sessionResource.save({'sessionName': sessionName, 'endDate': endDate, 'participantList': participants}).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };
        return sessionService;
    }
})()