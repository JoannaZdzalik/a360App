(function () {
    "use strict";
    angular.module('a360').controller('QuestionsManagerController', QuestionsManagerController);
    QuestionsManagerController.$inject = ['$scope', '$window', 'toastr', '$uibModal','QuestionsService'];

    function QuestionsManagerController($scope, $window, toastr, $uibModal, QuestionsService) {
        $scope.init = function () {
            $scope.sectionTitles = ["Question manager", "Default questions", "Other questions"];
            $scope.tableHeaders = ["Question", "Type", " "];
            $scope.questionTypeList = ["TEXT", "RADIO"];
            $scope.defaultAnswers = "";
            $scope.defaultAnswersList = [];
            $scope.showQuestionLoader = false;
            $scope.showNonDefaultQuestions = false;
            getAllQuestions();
        };

        function getAllQuestions() {
            $scope.showQuestionLoader = true;
            QuestionsService.getAllQuestions().then(function (data) {
                $scope.questions = data;
                console.log(data);
                $scope.showQuestionLoader = false;

            }, function (response) {
                alert('Error on getAllQuestions request' + response);
                $scope.showQuestionLoader = false;
            });
        }

        $scope.editQuestion = function (i) {
            var isDefault;
            var question = $scope.questions[i];
            var questionId = question.question_id;
            var isActive = question.is_active;
            if (question.is_default === false) {
                isDefault = true;
            }
            if (question.is_default === true) {
                isDefault = false;
            }
            QuestionsService.editQuestion(questionId, isActive, isDefault).then(function (data) {
                console.log("Successfully editted " + data);
                $window.location.reload();
            }, function (response) {
                alert('Error on edit Question' + response);
            });
        };

        $scope.openModal = function () {
            $scope.modalInstance = $uibModal.open({
                templateUrl: "components/questionsManager/modalAddQuestion.html",
                controller: "ModalAddQuestionController",
                backdrop: 'static',
                size: 'lg'
            });

            $scope.modalInstance.result.then(function () {
                $window.location.reload();
            });
        };

        $scope.goToWelcomePage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/welcome";
        };

        $scope.changeQuestionsVisibility = function () {
            $scope.showNonDefaultQuestions = ! $scope.showNonDefaultQuestions;
        }
    }
})();

