(function () {
    "use strict";
    angular.module('a360').controller('QuestionsController', QuestionsController);
    QuestionsController.$inject = ['$scope', '$window', 'toastr', 'QuestionsService'];

    function QuestionsController($scope, $window, toastr, QuestionsService) {
        $scope.init = function () {
            $scope.showQuestionLoader = false;
            $scope.showQuestionForm = false;
            $scope.sectionTitles = ["Question manager", "Active questions", "Inactive questions"];
            $scope.tableHeaders = [ "Question", "Type", " "];
            $scope.showEdit = false;
            $scope.questionTypeList = ["TEXT", "RADIO"];
            $scope.defaultAnswers = "";
            $scope.defaultAnswersList = [];
            getAllQuestions();

        };

        function getAllQuestions() {
            $scope.showQuestionLoader = true;
            QuestionsService.getAllQuestions().then(function (data) {
                console.log(data);
                $scope.questions = data;
                $scope.showQuestionLoader = false;
            }, function (response) {
                alert('Error on getAllSessions request' + response);
                $scope.showQuestionLoader = false;
            });
        }

        $scope.editQuestionStatus = function (i) {
            var isActive;
            var question = $scope.questions[i];
            var questionId = question.question_id;

            if (question.is_active === false) {
                isActive = true
            }
            if (question.is_active === true) {
                isActive = false
            }
            QuestionsService.editQuestionStatus(questionId, isActive).then(function (data) {
                console.log("Successful edit" + data);
                $window.location.reload();
            }, function (response) {
                alert('Error on edit Question' + response);
            });
        };

        $scope.displayAddForm = function () {
            $scope.showQuestionForm = true;
            $scope.showEdit = false;
        };

        $scope.createQuestion = function () {
            $scope.showQuestionLoader = true;
            convertDefaultAnswersToString();
            QuestionsService.sendQuestion($scope.questionText,
                $scope.inputQuestionType, $scope.defaultAnswersString).then(function (data) {
                $window.location.reload();
                handleSuccess(data)
            }, function (response) {
                handleFailure(response)
            });
        };

        $scope.canCreateQuestion = function () {
            return $scope.questionText === undefined || $scope.inputQuestionType === undefined;
        };

        function convertDefaultAnswersToString() {
            $scope.defaultAnswersString = $scope.defaultAnswersList.join(";");
        }

        $scope.addDefaultAnswer = function () {
            $scope.defaultAnswersList.push($scope.defaultAnswers);
            $scope.defaultAnswers = "";
        };

        function cleanUp() {
            $scope.showQuestionLoader = false;
            $scope.questionText = "";
            $scope.inputQuestionType = "";
            $scope.defaultAnswersList = []
        }

        // $scope.editItem = function ($index) {
        //     $scope.showEdit = true;
        //     $scope.questionToEdit = $scope.questions[$index];
        //     $scope.questionTextEdit = $scope.questionToEdit.question_text;
        //     $scope.inputQuestionTypeEdit = $scope.questionToEdit.question_type
        // };

        $scope.goToSessionPage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/session";
        };

        function handleSuccess(data) {
            toastr.success("Question created", "Success");
            cleanUp();
            console.log("success" + data);
          }

        function handleFailure(response) {
            $scope.showQuestionLoader = false;
            toastr.error("Question cannot be created", "Fail");
            console.log(response);
        }
    }
})();

