(function () {
    "use strict";
    angular.module('a360').controller('WelcomeController', WelcomeController);
    WelcomeController.$inject = ['$scope', '$window'];

    function WelcomeController($scope, $window) {

        $scope.redirectToStatistics  = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/statistics";
        };

        $scope.redirectToCreateSession  = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/session";
        };
    }
})();