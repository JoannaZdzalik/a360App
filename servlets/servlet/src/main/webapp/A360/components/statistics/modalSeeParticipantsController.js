(function () {
    "use strict";
    angular.module('a360').controller('ModalSeeParticipantsController', ModalSeeParticipantsController);
    ModalSeeParticipantsController.$inject = ['$scope', 'participants', 'feedback', '$uibModalInstance'];

    function ModalSeeParticipantsController($scope, participants, feedback, $uibModalInstance) {
        $scope.participants = participants;
        $scope.allFeedbacks = feedback;

        $scope.cancel = function () {
            $uibModalInstance.dismiss("cancel");
        };
    }
})();