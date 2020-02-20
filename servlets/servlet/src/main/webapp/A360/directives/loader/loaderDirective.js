(function () {
    'use strict';
    angular.module('a360').directive('a360Loading', LoadingDirective);
    angular.module('a360').controller('LoadingDirectiveController', LoadingDirectiveController);
    LoadingDirectiveController.$inject = ['$scope'];
    function LoadingDirectiveController($scope) {
        this.showLoader = function () {
            $scope.showLoader();
        };
        this.hideLoader = function () {
            $scope.hideLoader();
        };
    }
    function LoadingDirective() {
        return {
            restrict: 'A',
            replace: true,
            transclude: true,
            scope: {
                show: '='
            },
            controller: 'LoadingDirectiveController',
            templateUrl: 'directives/loader/loadingTemplate.html',
            link: function (scope, element, attrs) {
                if (attrs.show) {
                    scope.$watch('show', function (val) {
                        if (val != undefined) {
                            if (val) {
                                scope.showLoader();
                            } else {
                                scope.hideLoader();
                            }
                        }
                    });
                }
                scope.showLoader = function () {
                    $('.loading-overlay', element).show();
                };
                scope.hideLoader = function () {
                    $('.loading-overlay', element).hide();
                };
            }
        };
    }
})();