(function() {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);

    SessionController.$inject = ['$scope','SessionService'];


    function SessionController($scope, SessionService) {


        $scope.init = function(){
            $scope.title = "Create - Avenga 360 feedback session";
            $scope.participants = [];
            $scope.participantEmail = "";
            $scope.sessionForm = "";
            $scope.sessionName = "";
            $scope.inputEmail = "";
            $scope.endDate = new Date();
            $scope.emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        }

        $scope.endDateOptions = {
            dateDisabled: function(data, mode) {
                var date = data.date;
                var mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            },
            formatYear: 'yy',
            maxDate: new Date(2090, 5, 22),
            minDate: new Date(),
            startingDay: 1
        };
        $scope.endDatePopup = {
            opened: false
        };
        $scope.openEndDatePopup = function() {
            $scope.endDatePopup.opened = true;
        };

        $scope.addParticipant = function() {
            if($scope.inputEmail==0 || $scope.participants.includes($scope.inputEmail)){
                $scope.inputEmail = '';
            }else{
                $scope.participants.push({"email":$scope.inputEmail});
                $scope.inputEmail = '';
            }



        }
        $scope.removeParticipant = function($index) {
            $scope.participants.splice($index,1);
        }
        $scope.canSendSession = function () {
            return $scope.participants.length === 0 || $scope.sessionForm.sessionName.$invalid || $scope.sessionForm.endDate.$invalid
        }




        $scope.dateParser = function () {
            let year = $scope.endDate.getFullYear();
            let month = $scope.endDate.getMonth()+1;
            let day = $scope.endDate.getDate();
            let hour = $scope.endDate.getHours();
            let min = $scope.endDate.getMinutes();
            if ($scope.endDate.getMonth()<10) {
                month = `0${$scope.endDate.getMonth()+1}`
            }
            if ($scope.endDate.getDate()<10) {
                day = `0${$scope.endDate.getDate()}`
            }
            if ($scope.endDate.getMinutes()<10) {
                min = `0${$scope.endDate.getMinutes()}`
            }
            return `${year}-${month}-${day} ${hour}:${min}`
        };
        $scope.createSession = function() {
            SessionService.send( $scope.sessionName, $scope.dateParser(), $scope.participants).then(function(data) {
                console.log('success ' + data);
            }, function(response) {
                alert('error');
            });
        };



    }




})();
