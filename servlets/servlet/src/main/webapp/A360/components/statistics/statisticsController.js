(function () {
    "use strict";
    angular.module('a360').controller('StatisticsController', StatisticsController);
    StatisticsController.$inject = ['$scope', '$filter', '$window', '$uibModal', 'toastr', 'StatisticsService'];

    function StatisticsController($scope, $filter, $window, $uibModal, toastr, StatisticsService) {
        $scope.init = function () {
            $scope.sectionTitles = ["Session manager", "Active sessions", "Inactive sessions", "Finished sessions"];
            $scope.tableHeaders = ["#", "Session name", "End date", "Number of participants", "Action"];
            $scope.deactivateTooltip = "Deactivating session means it will be finished immediately and no feedback will be sent to participants.";
            $scope.activateTooltip = "Activating session means participants will be enabled to give their feedbacks. If end date is past now, all gathered answers will be sent immediately";
           // getSessionsAndAnswers();
            getSessions();
            getParticipantsFromSession();
            findSessionsByStatus();
            getAllAnswers();
            findAnswersForParticipants();
            $scope.showLoader = false;
        };

        // function getSessionsAndAnswers() {
        //     getSessions();
        //     getAllAnswers()
        // }

        function getSessions() {
            StatisticsService.getAllSessions().then(function (data) {
                console.log('GET all sessions request succesful ');
                $scope.sessions = data;
               // getParticipantsFromSession();
               // findSessionsByStatus();
            }, function (response) {
                console.log('Error on getAllSessions request' + response);
            });
        }

        function findSessionsByStatus() {
            $scope.activeSessions = [];
            $scope.inactiveSessions = [];
            $scope.finishedSessions = [];
            $scope.sessions.forEach(function (session) {
                if (session.active)
                    $scope.activeSessions.push(session);
                else if (!session.active && !session.isSent) {
                    $scope.inactiveSessions.push(session);
                } else if (session.isSent) {
                    $scope.finishedSessions.push(session)
                }
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
              //  findAnswersForParticipants();
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
            $window.location.href = "http://localhost:8080/servlet/A360/#!/welcome";
        };

        $scope.delete = function (sessionId) {
            StatisticsService.removeSession(sessionId).then(function (data) {
                console.log('Session removed ' + data.sessionName);
                $window.location.reload();
                toastr.success("Session removed", 'Success');
            }, function () {
                console.log('Error on remove request ');
            });
        };

        $scope.setInactive = function (sessionName) {
            $scope.showLoader = true;
            if ($window.confirm("Do you want to set this session as inactive?  \n\n" + "- participants will no longer be able to fill feedback questionnaires,\n" +
                " -no feedback will be sent to participants \n\n" + "Are you sure?")) {
                StatisticsService.editSessionIsActive(sessionName, false).then(function (data) {
                    $scope.showLoader = false;
                    console.log('Session succesfully deactivated ');
                    $window.location.reload();
                }, function () {
                    $scope.showLoader = false;
                    console.log('Error: session not deactivated ');
                    toastr.error("Session not deactivated", 'Fail');
                });
            }
            $scope.showLoader = false;
        };

        $scope.setActive = function (sessionName) {
            $scope.showLoader = true;
            if ($window.confirm("Do you want to set this session as active?  \n\n" + "If end date is past now, participants will receive their feedback immediately.")) {
                StatisticsService.editSessionIsActive(sessionName, true).then(function (data) {
                    $scope.showLoader = false;
                    console.log('Session succesfully activated ');
                    $window.location.reload();
                }, function () {
                    $scope.showLoader = false;
                    console.log('Error: session not activated ');
                    toastr.error("Session not activated", 'Fail');
                });
            }
        };

        $scope.openModal = function (participants) {
            $scope.modalInstance = $uibModal.open({
                templateUrl: "components/statistics/modalSeeParticipants.html",
                controller: "ModalSeeParticipantsController",
                backdrop: 'static',
                resolve: {
                    participants: function () {
                        return participants;
                    },
                    feedback: function () {
                        return $scope.allFeedbacks;
                    }
                },
                size: 'modalSize70'
            }).result.then(function () {
                $window.location.reload();
            }, function (res) {
                if (!(res === 'cancel' || res === 'escape key press' || res === 'backdrop click')) {
                    return res;
                }
            });
        };

    }

})();
