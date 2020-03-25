(function () {
    "use strict";
    angular.module('a360').controller('ModalQuesController', ModalQuesController);
    ModalQuesController.$inject = ['$scope', 'activeQuestions', '$uibModalInstance', 'toastr', 'QuestionsService'];

    function ModalQuesController($scope, activeQuestions, $uibModalInstance, toastr, QuestionsService) {
        $scope.originalActiveQuestions = activeQuestions; //a może default?
        $scope.question_text = $scope.originalActiveQuestions.question_text;

        $scope.save = function () {
                QuestionsService.editQuestion().then(function (data) { //tu ogar
                    console.log('Questions for this session sucesfully saved');
                    $uibModalInstance.close();
                }, function () {
                    console.log('Questions for this session not editted');
                });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        };
    }
})();