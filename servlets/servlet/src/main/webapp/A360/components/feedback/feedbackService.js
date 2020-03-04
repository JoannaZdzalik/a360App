(function () {
    'use strict';
    angular.module('a360').service('FeedbackService', FeedbackService);
    FeedbackService.$inject = ['$resource', '$q'];
    function FeedbackService($resource, $q) {
        var feedbackService = {};

        feedbackService.getParticipant = function (participantUid) {
            var url = '/servlet/a360/participants/' + participantUid;
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

        feedbackService.getQuestions = function (sessionId) {
            var link = '/servlet/a360/questions/' + sessionId;
            var feedbackQuestionByResource = $resource(link);
            var deferred = $q.defer();
            feedbackQuestionByResource.query().$promise.then(
                function (response) {
                   deferred.resolve(response);
                }, function (response) {deferred.reject(response);
                });
            return deferred.promise;
        };

        feedbackService.getSessionByParticipantUid = function (participantUid) {
            var link = '/servlet/a360/sessions/get/' + participantUid;
            var feedbackQuestionByResource = $resource(link);
            var deferred = $q.defer();
            feedbackQuestionByResource.get().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {deferred.reject(response);
                });
            return deferred.promise;
        };

        feedbackService.send = function(feedback) {
            var feedbackResource = $resource('/servlet/a360/answers/create');
            var deferred = $q.defer();
            feedbackResource.save(feedback).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };
        return feedbackService;
    }
})();