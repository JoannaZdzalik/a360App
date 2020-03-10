(function () {
    "use strict";
    angular.module('a360').controller('UsersController', UsersController);
    UsersController.$inject = ['$scope', '$window', 'toastr', 'UsersService'];

    function UsersController($scope, $window, toastr, UsersService) {
        $scope.init = function () {
            $scope.sectionTitles = ["Application users manager", "Admins", "Designers"];
            $scope.tableHeaders = ["#", "User login", "Role", "Action"];
            getAllUsers();
            $scope.showLoader1 = false;
            $scope.showLoader2 = false;
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
            findAdmins();
            if (role === 'DESIGNER' && $scope.admins.length === 1) {
                toastr.warning("There must be at least one admin", 'Invalid action');
            } else {
                showLoader(role);
                UsersService.editUserRole(login, role).then(function (data) {
                    hideLoaders();
                    $window.location.reload();
                    console.log('Edit role successful');
                }, function (response) {
                    hideLoaders();
                    console.log('Error edit role');
                    toastr.error("Role not changed", "Fail");
                });
            }

        };

        function findAdmins() {
            $scope.admins = [];
            $scope.users.forEach(function (element) {
                if (element.role === 'ADMIN') {
                    $scope.admins.push(element);
                }
            });
        }

        function showLoader(role) {
            if (role === 'DESIGNER') {
                $scope.showLoader1 = true;
            } else if (role === 'ADMIN') {
                $scope.showLoader2 = true;
            }
        }

        function hideLoaders() {
            $scope.showLoader1 = false;
            $scope.showLoader2 = false;
        }

    }
})();
