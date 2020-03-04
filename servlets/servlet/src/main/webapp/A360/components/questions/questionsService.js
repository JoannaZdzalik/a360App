(function () {
    'use strict';
    angular.module('a360').service('QuestionsService', QuestionsService);
    QuestionsService.$inject = ['$resource', '$q'];

    function QuestionsService($resource, $q) {
        var questionsService = {};

        questionsService.getAllQuestions = function () {
            var url = '/servlet/a360/questions/all';
            var questionsResource = $resource(url);
            var deferred = $q.defer();
            questionsResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        questionsService.sendQuestion = function (questionText, questionType, defaultAnswersString) {
            var url = '/servlet/a360/questions/add';
            var questionsResource = $resource(url);
            var deferred = $q.defer();
            questionsResource.save({
                'question_text': questionText,
                'question_type': questionType,
                'default_answers': defaultAnswersString
            }).$promise.then(function(data) {
                deferred.resolve(data);
            }, function(response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };

        questionsService.editQuestionStatus = function (questionId, isActive) {
            var url = '/servlet/a360/questions/edit';
            var questionsResource = $resource(url);
            var deferred = $q.defer();
            questionsResource.save({
                'question_id' : questionId,
                'is_active' : isActive
            }).$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        return questionsService;
    }
})();