(function () {
    "use strict";
    angular.module('a360').controller('AppParamsController', AppParamsController);
    AppParamsController.$inject = ['$scope', '$window', 'toastr', '$uibModal', 'AppParamsService'];

    function AppParamsController($scope, $window, toastr, $uibModal, AppParamsService) {
        $scope.init = function () {
            $scope.titles = ["Application parameters"];
            $scope.showLoader = false;
            getAppParams();
        };

        function getAppParams() {
            AppParamsService.getAppParams().then(function (data) {
                $scope.appParams = data;
                console.log('GET Application parameters successfull  ');
            }, function (response) {
                console.log("Fail on GET app parameters")
            });
        }

        $scope.editAppParams = function () {
            $scope.modalInstance = $uibModal.open({
                templateUrl: "components/appParameters/modalEditConfig.html",
                controller: "ModalController",
                backdrop: 'static',
                resolve: {
                    appParams: function () {
                        return $scope.appParams;
                    }
                },
                size: 'modalSize'
            });

            $scope.modalInstance.result.then(function () {
                $window.location.reload();
            });

        };

        $scope.redirectToWelcome = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/welcome";
        };

    }
})();