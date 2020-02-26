(function () {
    "use strict";
    angular.module('a360').controller('ActiveSessionController', ActiveSessionController);
    ActiveSessionController.$inject = ['$scope', 'ActiveSessionService', '$filter'];


    function ActiveSessionController($scope, ActiveSessionService, $filter) {
        $scope.init = function () {
            $scope.title = " All active sessions";
            $scope.allActiveSessions = [];
            $scope.getAllSession();
            $scope.i=1;


        };


        $scope.getAllSession = function () {
            ActiveSessionService.getAllSessions().then(function (data) {
                data.forEach(function (el) {
                    var session = {
                        number:$scope.i++,
                        sessionName: el.sessionName,
                        isSent: el.isSent,
                        endDate: $scope.formatDate(el.endDate),
                        amountParticipants: el.participantList.length
                    };

                    $scope.allActiveSessions.push(session);
                    console.log($scope.allActiveSessions);

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
