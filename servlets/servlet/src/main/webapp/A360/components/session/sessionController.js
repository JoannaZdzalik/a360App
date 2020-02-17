(function() {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);
    SessionController.$inject = ['$scope'];
    function SessionController($scope) {
        $scope.title = "Create - Avenga 360 feedback session";
    }
})();