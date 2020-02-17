(function() {
    "use strict";
    angular.module('a360').controller('FeedbackController', FeedbackController);
    FeedbackController.$inject = ['$scope'];
    function FeedbackController($scope) {
        $scope.title = "Feedback for ";
    }
})();