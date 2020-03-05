(function () {
    "use strict";
    angular.module('a360').controller('StatisticsController', StatisticsController);
    StatisticsController.$inject = ['$scope', '$filter', '$window', 'StatisticsService'];

    function StatisticsController($scope, $filter, $window, StatisticsService) {
        $scope.init = function () {
            $scope.sectionTitles = ["Admin panel", "Active sessions", "Finished sessions"];
            $scope.tableHeaders = ["#", "Session name", "End date", "Number of participants", "Participants", "Answers received", "Action"];
            getSessionsAndAnswers();
        };

        function getSessionsAndAnswers() {
            getSessions();
            getAllAnswers()
        }

        function getSessions() {
            StatisticsService.getAllSessions().then(function (data) {
                console.log('GET all sessions request succesful ');
                $scope.sessions = data;
                getParticipantsFromSession();
            }, function (response) {
                console.log('Error on getAllSessions request' + response);
            });
        }

        function getParticipantsFromSession() {
            $scope.participantList = [];
            $scope.sessions.forEach(function (element) {
                for (let i = 0; i < element.participantList.length; i++) {
                    var singleParticipant = {
                        UId: element.participantList[i].UId,
                        email: element.participantList[i].email,
                        sessionId: element.participantList[i].sessionId
                    };
                    $scope.participantList.push(singleParticipant);
                }
            });
        }

        function getAllAnswers() {
            StatisticsService.getAllAnswers().then(function (data) {
                console.log('GET all answers request succesful ');
                $scope.answers = data;
                findAnswersForParticipants();
            }, function (response) {
                console.log('Error on getAllAnswerss request' + response);
            });
        }

        function findAnswersForParticipants() {
            $scope.allFeedbacks = [];
            $scope.participantList.forEach(function (element) {
                $scope.answersForParticipant = [];
                for (let i = 0; i < $scope.answers.length; i++) {
                    if ($scope.answers[i].participantUId === element.UId) {
                        var singleAnswer = {
                            answerText: $scope.answers[i].answerText,
                            participantId: $scope.answers[i].participantId,
                            participantUId: $scope.answers[i].participantUId,
                            questionId: $scope.answers[i].questionId
                        };
                        $scope.answersForParticipant.push(singleAnswer);
                    }
                }
                var singleFeedback = {
                    email: element.email,
                    participantUid: element.UId,
                    answers: $scope.answersForParticipant,
                };
                $scope.allFeedbacks.push(singleFeedback);
            });
        }

        $scope.formatEndDate = function (endDate) {
            return $filter('date')((endDate), 'yyyy-MM-dd hh:mm');
        };

         $scope.goBackToWelcomePage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/welcome";
        };

        $scope.deleteInactive = function (sessionId) {
            StatisticsService.removeSession(sessionId).then(function (data) {
                console.log('Session removed ' + data.sessionName);
                $window.location.reload();
            }, function () {
                console.log('Error on remove request ');
            });
        }
    }

})();
