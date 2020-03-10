(function () {
    "use strict";
    angular.module('a360').controller('RegisterController', RegisterController);
    RegisterController.$inject = ['$scope', '$window', 'toastr', 'RegisterService'];

    function RegisterController($scope, $window, toastr, RegisterService) {
        $scope.init = function () {
            $scope.titles = ["Register form"]
        };

        function goToMainPage() {
            $window.location.href = "http://localhost:81/servlet/A360/#!/main";
        }
    }
})();