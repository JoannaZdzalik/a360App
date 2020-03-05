(function () {
    "use strict";
    angular.module('a360').controller('UsersController', UsersController);
    UsersController.$inject = ['$scope', '$window', 'UsersService'];

    function UsersController($scope, $window, UsersService) {
        $scope.init = function () {
            $scope.sectionTitles = ["Application users manager", "Admins", "Designers"];
            $scope.tableHeaders = ["#", "User login", "Role", "Action"];
            getAllUsers();
        };

        function getAllUsers() {
            UsersService.getAllUsers().then(function (data) {
                console.log('GetAllUsers successful');
                $scope.users = data;
            }, function (response) {
                console.log('Error on getAllUsers request');
            });
        }

        $scope.goBackToWelcomePage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/welcome";
        };

        $scope.changeRoleType = function (login, role) {
            UsersService.editUserRole(login, role).then(function (data) {
                console.log('Edit role successful');
               // $window.location.reload();
            }, function (response) {
                console.log('Error edit role');
            });
        }

    }

})();
