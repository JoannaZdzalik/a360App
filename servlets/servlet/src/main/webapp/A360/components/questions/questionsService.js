(function () {
    'use strict';
    angular.module('a360').service('QuestionsService', QuestionsService);
    QuestionsService.$inject = ['$resource', '$q'];

    function QuestionsService($resource, $q) {
        var questionsService = {};

        var listOfQuestions = [];

        questionsService.addQuestionToList = function (newQuestion) {
            listOfQuestions.push(newQuestion);
        };

        questionsService.removeFromList = function(index){
            listOfQuestions.splice(index, 1);
        };

        questionsService.insertDefault = function (listOfDefault) { //ew do wywalenia
            listOfQuestions.push.apply(listOfQuestions, listOfDefault);
            console.log("From service: now list of questions looks has no of elements: " + listOfQuestions.length +
            " and list of default has: " + listOfDefault.length)
        };

        questionsService.getListOfQuestionsInSession = function () {
            return listOfQuestions;
        };

        questionsService.getDefaultQuestions = function () {
            var url = '/servlet/a360/questions/default';
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

        questionsService.sendQuestion = function (questionText, questionType, defaultAnswersString, isDefault) {
            var url = '/servlet/a360/questions/add';
            var questionsResource = $resource(url);
            var deferred = $q.defer();
            questionsResource.save({
                'question_text': questionText,
                'question_type': questionType,
                'default_answers': defaultAnswersString,
                'is_default': isDefault
            }).$promise.then(function (data) {
                deferred.resolve(data);
            }, function (response) {
                deferred.reject(response);
            });
            return deferred.promise;
        };

        questionsService.editQuestion = function (questionId, isDefault) {
            var url = '/servlet/a360/questions/edit';
            var questionsResource = $resource(url);
            var deferred = $q.defer();
            questionsResource.save({
                'question_id': questionId,
                'is_default': isDefault
            }).$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        questionsService.getQuestionTypes = function () {
            var url = '/servlet/a360/questions/questionTypes';
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
        return questionsService;
    }
})();