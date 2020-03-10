(function () {
    "use strict";
    angular.module('a360').controller('MainController', MainController);
    MainController.$inject = ['$scope', '$window'];

    function MainController($scope, $window) {

        $scope.redirectToRegister  = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/register";
        };
    }
})();