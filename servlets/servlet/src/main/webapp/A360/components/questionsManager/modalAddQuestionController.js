(function () {
    "use strict";
    angular.module('a360').controller('ModalAddQuestionController', ModalAddQuestionController);
    ModalAddQuestionController.$inject = ['$scope', '$uibModalInstance', 'QuestionsService'];

    function ModalAddQuestionController($scope, $uibModalInstance, QuestionsService) {
        $scope.questionTypeList = ["TEXT", "RADIO"];
        $scope.defaultAnswer = "";
        $scope.defaultAnswersList = [];
        $scope.isDefault = false;

        $scope.save = function () {
            convertDefaultAnswersToString();
            QuestionsService.sendQuestion($scope.questionText,
                $scope.inputQuestionType, $scope.defaultAnswersString, $scope.isDefault).then(function (data) {
                console.log('New question saved.');
                $uibModalInstance.close();
            }, function () {
                console.log('Question not saved. ');
            });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        };

        $scope.setInputQuestionType = function (type) {
            $scope.inputQuestionType = type;
        };

        function convertDefaultAnswersToString() {
            $scope.defaultAnswersString = $scope.defaultAnswersList.join(";");
        }

        $scope.addDefaultAnswer = function () {
            if (!isDuplicated()) {
                $scope.defaultAnswersList.push($scope.defaultAnswer);
                $scope.defaultAnswer = "";
                console.log("Answer added")
            } else {
                console.log("Duplicated default answer")
            }
        };

        function isDuplicated() {
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
        }

        $scope.removeDefaultAnswer = function ($index) {
            $scope.defaultAnswersList.splice($index, 1);
        };

        $scope.changeIsDefault = function () {
            $scope.isDefault = !$scope.isDefault;
        };

        $scope.canCreateQuestion = function () {
            return $scope.questionText === undefined || $scope.inputQuestionType === undefined ||
                ($scope.inputQuestionType === 'RADIO' && $scope.defaultAnswersList.length < 2)
        };
    }
})();