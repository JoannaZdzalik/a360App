(function() {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);

    SessionController.$inject = ['$scope'];


    function SessionController($scope) {
        $scope.title = "Create - Avenga 360 feedback session";
        $scope.today = function() {
            $scope.dt = new Date();
        };
        $scope.today();
        $scope.setDate = function(year, month, day) {
            $scope.dt = new Date(year, month, day);
        };

        $scope.inlineOptions = {
            customClass: getDayClass,
            minDate: new Date(),
            showWeeks: true
        };

        $scope.open2 = function() {
            $scope.popup2.opened = true;
        };
        $scope.popup2 = {
            opened: false
        };
        $scope.participants = [];
        $scope.add = function(newParticipant) {
            $scope.participants.push($scope.newParticipant);
            $scope.newParticipant = '';
        }
        $scope.remove = function($index) {
            $scope.participants.splice($index,1);
        }




    }

    function getDayClass(data) {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0,0,0,0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }

        return '';
    }



})();
