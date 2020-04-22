(function () {
    "use strict";
    angular.module('a360').controller('ModalQuesController', ModalQuesController);
    ModalQuesController.$inject = ['$scope', '$uibModalInstance', 'QuestionsService'];

    function ModalQuesController($scope, $uibModalInstance, QuestionsService) {
        $scope.init = function () {
            getQuestionTypes();
            $scope.defaultAnswer = "";
            $scope.defaultAnswersList = [];
            $scope.isDefault = false;
        };

        $scope.save = function () {
            convertDefaultAnswersToString();
            addQuestionToSession();
            $uibModalInstance.close("New question saved");
        };

        function getQuestionTypes() {
            QuestionsService.getQuestionTypes().then(function (data) {
                $scope.questionTypeList = data;
            }, function (response) {
                console.log('Error on getQuestionTypes request' + response);
            });
        }

        function addQuestionToSession() {
            var question = {
                default_answers: $scope.defaultAnswersString,
                is_default: false,
                question_text: $scope.questionText,
                question_type: $scope.inputQuestionType
            };
            QuestionsService.addQuestionToList(question);
        }

        $scope.cancel = function () {
            $uibModalInstance.dismiss("cancel");
        };

        $scope.setInputQuestionType = function (type) {
            $scope.inputQuestionType = type;
        };

        $scope.addDefaultAnswer = function () {
            if (!$scope.isDuplicated()) {
                $scope.defaultAnswersList.push($scope.defaultAnswer);
                $scope.defaultAnswer = "";
                console.log("Answer added")
            } else {
                $scope.analyzeInput();
                console.log("Duplicated default answer")
            }
        };

        $scope.isDuplicated = function () {
            var temp = [];
            if ($scope.defaultAnswersList.length === 0) {
                return false;
            } else {
                $scope.defaultAnswersList.forEach(function (element) {
                    var el = element.toLowerCase();
                    temp.push(el);
                });
                var compared = $scope.defaultAnswer.toLowerCase();
                return temp.indexOf(compared) !== -1;
            }
        };

        $scope.removeDefaultAnswer = function ($index) {
            $scope.defaultAnswersList.splice($index, 1);
        };

        function convertDefaultAnswersToString() {
            $scope.defaultAnswersString = $scope.defaultAnswersList.join(";");
        }

        $scope.analyzeInput = function () {
            $scope.duplicateInfo = {};
            if ($scope.isDuplicated()) {
                $scope.duplicateInfo["color"] = "#d13a3a";
                $scope.analyzeResult = "Duplicated value!";
                return true;
            }
        };

        $scope.unableToCreateQuestion = function () {
            return $scope.questionText === undefined || $scope.inputQuestionType === undefined ||
                ($scope.inputQuestionType === 'RADIO' && $scope.defaultAnswersList.length < 2)
        };
    }
})();