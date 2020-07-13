(function () {
    "use strict";
    angular.module('a360').controller('LoginController', LoginController);
    MainController.$inject = ['$scope', '$window'];

    function LoginController($scope, $window) {

        $scope.redirectToRegister  = function () {
            $window.location.href = "http://localhost:8080/servlet/A360/#!/register";
        };

        $scope.logIn = function (){
         LoginService.sendCredentials($scope.login, $scope.password).then(function (data) {
                       console.log('wyglada ze cos przeszlo ale nwm co ' + data);
                       // $window.location.href = "http://localhost:8080/servlet/A360/#!/welcome";
                    }, function (response) {
                       console.log('cos zlogowaniem nie tak, a oto response: ' + response)
                    });
        }


    }
})();