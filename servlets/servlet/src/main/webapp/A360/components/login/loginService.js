(function () {
    'use strict';
    angular.module('a360').service('LoginService', LoginService);
    LoginService.$inject = ['$resource', '$q'];
    function LoginService($resource, $q) {
        var loginService = {};

        loginService.sendCredentials = function(username, password) {

        };

        return loginService;
    }
})();