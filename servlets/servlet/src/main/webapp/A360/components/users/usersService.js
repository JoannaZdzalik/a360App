(function () {
    'use strict';
    angular.module('a360').service('UsersService', UsersService);
    UsersService.$inject = ['$resource', '$q'];

    function UsersService($resource, $q) {
        var usersService = {};

        usersService.getAllUsers = function () {
            var url = '/servlet/a360/users/all';
            var allUsersResource = $resource(url);
            var deferred = $q.defer();
            allUsersResource.query().$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };

        usersService.editUserRole = function (login, role) {
            var url = '/servlet/a360/users/editRole';
            var usersResource = $resource(url);
            var deferred = $q.defer();
            usersResource.save({
                'login' : login,
                'role' : role
            }).$promise.then(
                function (data) {
                    deferred.resolve(data);
                }, function (response) {
                    deferred.reject(response);
                });
            return deferred.promise;
        };
        return usersService;
    }

})();