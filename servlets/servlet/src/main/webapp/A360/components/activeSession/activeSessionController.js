(function () {
    "use strict";
    angular.module('a360').controller('ActiveSessionController', ActiveSessionController);
    ActiveSessionController.$inject = ['$scope', 'ActiveSessionService', '$filter'];


    function ActiveSessionController($scope, ActiveSessionService, $filter) {
        $scope.init = function () {
            $scope.title = " All active sessions";
            $scope.allActiveSessions = [];
            $scope.getAllSession();


        };


        $scope.getAllSession = function () {
            ActiveSessionService.getAllSessions().then(function (data) {

                data.forEach(function (el) {
                    var session = {
                        sessionName1: el.sessionName,
                        isSent1: el.isSent,
                        endDate1: el.endDate


                    };
                    $scope.allActiveSessions.push(session);

                })

            }, function (response) {

            });

        };


    }


})();
