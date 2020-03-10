(function () {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);
    SessionController.$inject = ['$scope', '$window', '$filter', 'toastr', 'SessionService'];

    function SessionController($scope, $window, $filter, toastr, SessionService) {
        $scope.init = function () {
            $scope.participants = [];
            $scope.sessionName = "";
            $scope.endDate = addDaysToDate(new Date, 1);
            $scope.participantEmail = "";
            $scope.showSendSessionLoader = false;
            getAllActiveQuestions();
        };

        $scope.sectionTitle = ["Create - Avenga 360 feedback session", "Details", "Participants", "Questions"];
        $scope.emailFormat = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;

        function getAllActiveQuestions() {
            SessionService.getActiveQuestions().then(function (response) {
                console.log('GET questions successful ' + response);
                $scope.questions = response;
            }, function () {
                alert('GET question request failed');
            });
        }

        $scope.endDatePopup = {
            opened: false
        };

        $scope.addParticipant = function () {
            if ($scope.participants.indexOf($scope.participantEmail) !== -1) {
                toastr.warning("Duplicated email", 'Invalid input');
            } else if ($scope.participantEmail.length === 0) {
                toastr.warning("Please enter valid participant email", 'Invalid input');
            } else {
                $scope.participants.push($scope.participantEmail);
                $scope.participantEmail = "";
            }
        };

        $scope.removeParticipant = function ($index) {
            if ($window.confirm("Do you want to delete this email from participant list?")) {
                $scope.participants.splice($index, 1);
            }
        };

        $scope.endDateOptions = {
            dateDisabled: function (data, mode) {
                var date = data.date;
                mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            },
            formatYear: 'yy',
            maxDate: new Date(2199, 5, 22),
            minDate: addDaysToDate(new Date, 1),
            startingDay: 1
        };

        function addDaysToDate(date, days) {
            var result = new Date(date);
            result.setDate(result.getDate() + days);
            return result;
        }

        $scope.openEndDatePopup = function () {
            $scope.endDatePopup.opened = true;
        };

        $scope.canCreateSession = function () {
            return $scope.participants.length < 3 || $scope.sessionForm.sessionName.$invalid || $scope.sessionForm.endDate.$invalid
        };

        $scope.createSession = function () {
            $scope.showSendSessionLoader = true;
            var convertedEndDate = $filter('date')(new Date($scope.endDate), 'yyyy-MM-dd hh:mm');
            var participants = $scope.convertParticipantEmailsToJson();
            SessionService.sendSession($scope.sessionName, convertedEndDate, participants).then(function (data) {
                $scope.showSendSessionLoader = false;
                $scope.handleSuccess(data);
            }, function (response) {
                $scope.showSendSessionLoader = false;
                $scope.handleFailure(response);
            });
        };

        $scope.handleSuccess = function (data) {
            var success = data.statusMessageList[0].message;
            var status = data.status;
            console.log(status + ': ' + success);
            goToSuccessPage();
        };

        $scope.handleFailure = function (response) {
            var error = response.data.statusMessageList[0].message;
            var status = response.data.status;
            console.log(status + ': ' + error);
            toastr.error(error, 'Fail');
        };

        $scope.convertParticipantEmailsToJson = function () {
            let partArray = [];
            for (let i = 0; i < $scope.participants.length; i++) {
                let email = {email: $scope.participants[i]};
                partArray.push(email)
            }
            return partArray;
        };

        $scope.goToEditPage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/questions";
        };

        $scope.goBackToWelcomePage = function () {
            $window.location.href = "http://localhost:81/servlet/A360/#!/welcome";
        };

        function goToSuccessPage() {
            $window.location.href = "http://localhost:81/servlet/A360/#!/sessionCreated";
        }
    }
})();

