(function () {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);

    SessionController.$inject = ['$scope', 'SessionService', '$filter'];


    function SessionController($scope, SessionService, $filter) {


        $scope.init = function () {
            $scope.title = "Create - Avenga 360 feedback session";
            $scope.participants = [];
            $scope.sessionForm = "";
            $scope.sessionName = "";
            $scope.inputEmail = "";
            $scope.showAlertPositive = false;
            $scope.showAlertError = false;
            $scope.endDate = "";
            $scope.showAlert = "";
            $scope.cleanFields();


        }
        $scope.emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        $scope.endDateOptions = {
            dateDisabled: function (data, mode) {
                var date = data.date;
                var mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            },
            formatYear: 'yy',
            maxDate: new Date(2090, 5, 22),
            minDate: addDaysToDate(new Date(), 1),
            startingDay: 1
        };

        function addDaysToDate(date, days) {
            var newDate = new Date(date);
            newDate.setDate(newDate.getDate() + days)
            return newDate

        }

        $scope.endDatePopup = {
            opened: false
        };
        $scope.openEndDatePopup = function () {
            $scope.endDatePopup.opened = true;
        };

        $scope.addParticipant = function () {
            if ($scope.inputEmail == 0 || $scope.participants.includes($scope.inputEmail)) {
                $scope.inputEmail = '';
            } else {
                $scope.participants.push({"email": $scope.inputEmail});
                $scope.inputEmail = '';
            }


        }
        $scope.removeParticipant = function ($index) {
            $scope.participants.splice($index, 1);
        }
        $scope.canSendSession = function () {
            return $scope.participants.length === 0 || $scope.sessionForm.sessionName.$invalid || $scope.sessionForm.endDate.$invalid
        }
        $scope.cleanFields = function () {
            $scope.sessionName = "";
            $scope.endDate = "";
            $scope.participants = []
            $scope.inputEmail = "";
        }


        $scope.formatDate = function () {
            var convertedEndDate = $filter('date')(new Date($scope.endDate), 'yyyy-MM-dd hh:mm');
            return convertedEndDate
        };
        $scope.createSession = function () {
            SessionService.send($scope.sessionName, $scope.formatDate(), $scope.participants).then(function (data) {
                $scope.cleanFields();
                $scope.showAlert = "Success!";
            },
                function (response) {
                $scope.showAlert = 'Error!';
            });
        };


    }


})();
