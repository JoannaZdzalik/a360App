(function () {
    "use strict";
    angular.module('a360').controller('ActiveSessionController', ActiveSessionController);
    ActiveSessionController.$inject = ['$scope', 'ActiveSessionService', '$filter'];


    function ActiveSessionController($scope, ActiveSessionService, $filter) {
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
            ActiveSessionService.getAmountAnswers().then(function (data) {
                data.forEach(function (element) {

                    if (element.sessionName == sessionName )
                        $scope.temp =element.amountOfAnswers;
                    console.log(element.amountOfAnswers);
                    // return answers


                })


            })

        }


        $scope.getAllSession = function () {
            ActiveSessionService.getAllSessions().then(function (data) {
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
            var convertedEndDate = $filter('date')(new Date(date), 'hh:mm dd MM yyyy ');
            return convertedEndDate
        };

    }



})();
