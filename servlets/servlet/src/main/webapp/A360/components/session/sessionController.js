(function() {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);

    SessionController.$inject = ['$scope'];


    function SessionController($scope) {
        $scope.title = "Create - Avenga 360 feedback session";
        $scope.participants = [];
        $scope.session = "";
        $scope.sessionName = "";
        $scope.inputEmail = null;
        $scope.endDate = new Date();
        $scope.minlength = 4;
        $scope.today = function() {
            $scope.endDate = new Date();
        };
        $scope.today();
        $scope.setDate = function(year, month, day) {

            $scope.endDate = new Date(year, month, day);
        };
        $scope.inlineOptions = {
            minDate: new Date(),
            startingDay: 1,
            showWeeks: true
        };

        $scope.open2 = function() {
            $scope.popup2.opened = true;
        };
        $scope.popup2 = {
            opened: false
        };

        $scope.add = function() {
            $scope.participants.push($scope.inputEmail);
            $scope.inputEmail = '';
        }
        $scope.remove = function($index) {
            $scope.participants.splice($index,1);
        }
        $scope.checkIfInputComplete = function () {
            return $scope.participants.length === 0 || $scope.session.inputName.$invalid || $scope.session.inputEndDate.$invalid
        }



    }




})();
