(function () {
    "use strict";
    angular.module('a360').controller('RegisterController', RegisterController);
    RegisterController.$inject = ['$scope', '$window', 'toastr', 'RegisterService'];

    function RegisterController($scope, $window, toastr, RegisterService) {
        $scope.init = function () {
            $scope.titles = ["Avenga A360 Feedback Application - create an account"];
            $scope.showLoader = false;
            $scope.passwordPatternTooltip = "Password must have at least 5 characters and contain 1 small character, 1 big character and 1 digit"
            $scope.passwordPattern = mediumRegex;

        };
        var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
        var mediumRegex = new RegExp("^((?=.*[a-z])(?=.*[A-Z]))(?=.{5,})");

        $scope.createUser = function () {
            $scope.showLoader = true;
            RegisterService.createUser($scope.login, $scope.password).then(function (data) {
                $scope.showLoader = false;
                goToRegisterSuccess();
            }, function (response) {
                $scope.showLoader = false;
                var status = response.data.status;
                var message = response.data.statusMessageList[0].message;
                toastr.error(message + "\nPlease choose another login.", status);
            });
        };

        $scope.canCreateUser = function () {
            return $scope.registerForm.login.$invalid || $scope.registerForm.password.$invalid || $scope.analyzeResult === 'Weak password'
        };

        $scope.goBackToMainPage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/main";
        };

        function goToRegisterSuccess() {
            $window.location.href = "http://localhost:81/servlet/A360/#!/registerSuccess";
        }

        $scope.analyzePassword = function (password) {
            $scope.passwordStrength = {};
            if (strongRegex.test(password)) {
                $scope.passwordStrength["color"] = "#60a361";
                $scope.analyzeResult = "Strong password"
            } else if (mediumRegex.test(password)) {
                $scope.passwordStrength["color"] = "#ffa139";
                $scope.analyzeResult = "Medium password"
            } else {
                $scope.passwordStrength["color"] = "#d13a3a";
                $scope.analyzeResult = "Weak password"
            }
        };
    }
})();