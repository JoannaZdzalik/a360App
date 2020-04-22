(function () {
    "use strict";
    angular.module('a360').controller('QuestionsController', QuestionsController);
    QuestionsController.$inject = ['$scope', '$rootScope', '$window', 'toastr', '$uibModal', 'QuestionsService'];

    function QuestionsController($scope, $rootScope, $window, toastr, $uibModal, QuestionsService) {
        $scope.init = function () {
            $scope.showQuestionLoader = false;
            $scope.sectionTitles = ["Manage questions for your session", "Active questions", "Default questions"];
            $scope.tableHeaders = ["Question", "Type", " "];
            $scope.questions = [];
            getDefaultQuestions();
            getQuestionsInSession();
        };

        function getDefaultQuestions() {
            $scope.showQuestionLoader = true;
            QuestionsService.getDefaultQuestions().then(function (data) {
                $scope.defaultQuestions = data;
                $scope.availableDefaultQuestions = [];
                $scope.defaultQuestions.forEach(function (defaultQuestion) {
                    if ($scope.questionsInSession.indexOf(defaultQuestion) === -1) {
                        $scope.availableDefaultQuestions.push(defaultQuestion)
                    }
                });

                $scope.showQuestionLoader = false;
            }, function (response) {
                console.log('Error on getDefaultQuestions request' + response);
                $scope.showQuestionLoader = false;
            });
        }

        function getQuestionsInSession() {
            $scope.questionsInSession = QuestionsService.getListOfQuestionsInSession();
        }

        $scope.removeFromQuestionList = function ($index, question) {
            QuestionsService.removeFromList($index);
            $scope.availableDefaultQuestions.push(question)
        };

        $scope.uploadToQuestionList = function (question) {
            QuestionsService.addQuestionToList(question);
            $scope.availableDefaultQuestions.splice($scope.availableDefaultQuestions.indexOf(question), 1)
        };

        $scope.openModal = function () {
            $scope.modalInstance = $uibModal.open({
                templateUrl: "components/questions/modalQues.html",
                controller: "ModalQuesController",
                backdrop: true,
                resolve: {
                },
                size: 'lg'
            }).result.then(function () {
            }, function (res) {
                if (!(res === 'cancel' || res === 'escape key press' || res === 'backdrop click')) {
                    return res;
                }
            });
        };

        $scope.goToSessionPage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/session";
        };
    }
})();

