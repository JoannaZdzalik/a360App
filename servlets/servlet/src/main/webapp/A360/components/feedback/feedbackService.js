(function () {
        'use strict';
        angular.module('a360').service('FeedbackService', FeedbackService);
        FeedbackService.$inject = ['$resource', '$q'];

        function FeedbackService($resource, $q) {
            var feedbackService = {};

            feedbackService.getParticipant = function (uId) {
                var url = '/servlet/a360/participants/' + uId;
                var feedbackParticipantResource = $resource(url);
                var deferred = $q.defer();
                feedbackParticipantResource.get().$promise.then(
                    function (data) {
                        deferred.resolve(data);
                    }, function (response) {
                        deferred.reject(response);
                    });
                return deferred.promise;
            };
            return feedbackService;

        }
    }

);