(function () {
    "use strict";
    angular.module('a360').controller('QuestionsManagerController', QuestionsManagerController);
    QuestionsManagerController.$inject = ['$scope', '$window', 'toastr', '$uibModal', 'QuestionsService'];

    function QuestionsManagerController($scope, $window, toastr, $uibModal, QuestionsService) {
        $scope.init = function () {
            $scope.sectionTitles = ["Question manager", "Default questions", "Other questions"];
            $scope.tableHeaders = ["Question", "Type", " "];
            $scope.showQuestionLoader = false;
            $scope.showNonDefaultQuestions = false;
            getAllQuestions();
            getQuestionTypes();
        };

        function getAllQuestions() {
            $scope.showQuestionLoader = true;
            QuestionsService.getAllQuestions().then(function (data) {
                $scope.questions = data;
                console.log(data);
                $scope.showQuestionLoader = false;
            }, function (response) {
                console.log('Error on getAllQuestions request' + response);
                $scope.showQuestionLoader = false;
            });
        }

        $scope.editQuestion = function (i) {
            var isDefault;
            var question = $scope.questions[i];
            var questionId = question.question_id;
            if (question.is_default === false) {
                isDefault = true;
            }
            if (question.is_default === true) {
                isDefault = false;
            }
            QuestionsService.editQuestion(questionId, isDefault).then(function (data) {
                $window.location.reload();
            }, function (response) {
                console.log('Error on edit Question' + response);
            });
        };

        $scope.openModal = function () {
            $scope.modalInstance = $uibModal.open({
                templateUrl: "components/questionsManager/modalAddQuestion.html",
                controller: "ModalAddQuestionController",
                backdrop: true,
                resolve: {
                    questionTypes: function () {
                        return $scope.questionTypeList;
                    }
                },
                size: 'lg'
            }).result.then(function () {
                $window.location.reload();
            }, function (res) {
                if (!(res === 'cancel' || res === 'escape key press' || res === 'backdrop click')) {
                    return res;
                }
            });
        };

        $scope.goToWelcomePage = function () {
            $window.location.href = "http://localhost:8080/servlet/A360/#!/welcome";
        };

        $scope.changeQuestionsVisibility = function () {
            $scope.showNonDefaultQuestions = !$scope.showNonDefaultQuestions;
        };

        function getQuestionTypes() {
            QuestionsService.getQuestionTypes().then(function (data) {
                $scope.questionTypeList = data;
                console.log(data);
            }, function (response) {
                console.log('Error on getQuestionTypes request' + response);
                $scope.showQuestionLoader = false;
            });
        }
    }
})();

