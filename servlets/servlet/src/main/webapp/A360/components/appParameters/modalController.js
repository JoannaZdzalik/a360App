(function () {
    "use strict";
    angular.module('a360').controller('ModalController', ModalController);
    ModalController.$inject = ['$scope', 'appParams', '$uibModalInstance', 'toastr', 'AppParamsService'];

    function ModalController($scope, appParams, $uibModalInstance, toastr, AppParamsService) {
        $scope.originalAppParams = appParams;
        $scope.from = $scope.originalAppParams.from;
        $scope.host = $scope.originalAppParams.host;
        $scope.port = $scope.originalAppParams.port;
        $scope.auth = $scope.originalAppParams.auth;
        $scope.debug = $scope.originalAppParams.debug;

        $scope.changeDebugStatus = function () {
            $scope.originalAppParams.debug = !$scope.originalAppParams.debug;
            $scope.debug = $scope.originalAppParams.debug
        };

        $scope.changeAuthStatus = function () {
            $scope.originalAppParams.auth = !$scope.originalAppParams.auth;
            $scope.auth = $scope.originalAppParams.auth;
        };

        $scope.save = function () {
            AppParamsService.editAppParams($scope.auth, $scope.debug, $scope.from, $scope.host, $scope.port).then(function (data) {
                console.log('Application parameters successfully edited' + $scope.from);
                $uibModalInstance.close();
            }, function () {
                console.log('Application parameters not editted');
            });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss();
        };
    }
})();