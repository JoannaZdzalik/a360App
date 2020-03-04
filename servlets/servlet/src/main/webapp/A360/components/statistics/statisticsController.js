(function () {
    "use strict";
    angular.module('a360').controller('StatisticsController', StatisticsController);
    StatisticsController.$inject = ['$scope', 'StatisticsService', '$filter'];

    function StatisticsController($scope, $filter, StatisticsService ) {
        $scope.init = function () {
            $scope.title = " All active sessions";
            $scope.allActiveSessions = [];
            $scope.getAllSession();
            $scope.i = 1;
            $scope.temp = null;
        };

        $scope.answersForActiveSessions = function (sessionName)
        // function jakas(sessionName)
        {
            StatisticsService.getAmountAnswers().then(function (data) {
                data.forEach(function (element) {

                    if (element.sessionName === sessionName )
                        $scope.temp =element.amountOfAnswers;
                    console.log(element.amountOfAnswers);
                    // return answers
                })
            })
        };

        $scope.getAllSession = function () {
            StatisticsService.getAllSessions().then(function (data) {
                data.forEach(function (el) {
                    $scope.answersForActiveSessions(el.sessionName);

                    var session = {
                        number: $scope.i++,
                        sessionName: el.sessionName,
                        isSent: el.isSent,
                        endDate: $scope.formatDate(el.endDate),
                        amountParticipants: el.participantList.length,
                        amountOfAnswers: $scope.temp

                    };
                    console.log(session);
                    $scope.allActiveSessions.push(session);
                })

            }, function (response) {
            });
        };


        $scope.formatDate = function (date) {
            return $filter('date')(new Date(date), 'hh:mm dd MM yyyy ')
        };
    }

})();
