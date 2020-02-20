(function() {
    "use strict";
    angular.module('a360').controller('FeedbackController', FeedbackController);
    FeedbackController.$inject = ['$scope'];
        function FeedbackController($scope, $routeParams, FeedbackService) {

            $scope.init = function () {
                $scope.title = "Feedback for ";
                $scope.participantMail = "";
                $scope.sessionId = "";
                getParticipantByUid();



            };
            function getParticipantByUid() {
                FeedbackService.getParticipant($routeParams.uId).then(function (data) {
                    console.log('GET participant by uid successful ' + data.email);
                    $scope.participantEmail = data.email;
                    $scope.sessionId = data.sessionId;
                }, function (response) {
                    alert('error');
                });
            }

    }
})();