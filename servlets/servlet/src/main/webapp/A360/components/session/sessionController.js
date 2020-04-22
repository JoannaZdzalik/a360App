(function () {
    "use strict";
    angular.module('a360').controller('SessionController', SessionController);
    SessionController.$inject = ['$scope', '$rootScope', '$window', '$filter', 'toastr', 'SessionService', 'QuestionsService'];

    function SessionController($scope, $rootScope, $window, $filter, toastr, SessionService, QuestionsService) {
        $scope.init = function () {
            $scope.participants = [];
            $scope.sessionName = "";
            $scope.endDate = addDaysToDate(new Date, 1);
            $scope.participantEmail = "";
            $scope.showSendSessionLoader = false;
            getQuestionsInSession();

        };

        $scope.sectionTitle = ["Create - Avenga 360 feedback session", "Details", "Participants", "Questions"];
        $scope.emailFormat = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;

        var file = document.getElementById('docpicker');
        file.addEventListener('change', importFile);

        function importFile(evt) {
            $scope.allMails = [];
            var files = evt.target.files;
            if (files) {
                for (var i = 0, f; f = files[i]; i++) {
                    var r = new FileReader();
                    r.onload = (function () {
                        return function (e) {
                            var contents = processExcel(e.target.result);
                            $scope.allMails.push(contents[i]);
                        };
                    })(f, i + 1);
                    r.readAsBinaryString(f);
                }
            } else {
                console.log("Failed to load file(s)");
            }
        }

        function processExcel(data) {
            var workbook = XLSX.read(data, {
                type: 'binary'
            });
            var data = uploadResultToList(workbook);
            return data
        }

        function uploadResultToList(workbook) {
            workbook.Strings.forEach(function (participantEmail) {
                if ($scope.emailFormat.test(participantEmail.t)) {
                    var participantEmails = participantEmail.t;
                    $scope.allMails.push(participantEmails);
                }
            });
            return $scope.allMails;
        }

        $scope.uploadSelected = function () {
            $scope.allMails.forEach(function (participantEmail) {
                if (participantIsUnique(participantEmail)) {
                    $scope.participants.push(participantEmail)
                }
            });
        };

        function participantIsUnique(participantEmail) {
            return $scope.participants.indexOf(participantEmail) === -1
        }

        function getQuestionsInSession() {
            $scope.questionsInSession = QuestionsService.getListOfQuestionsInSession();
        }

        $scope.addParticipant = function () {
            if (inputEmailsAreMultiple()) {
                addMultipleFromOneInsert();
            } else {
                if (!participantIsUnique($scope.participantEmail)) {
                    toastr.warning("Duplicated email", 'Invalid input');
                } else {
                    $scope.participants.push($scope.participantEmail);
                    $scope.participantEmail = "";
                }
            }
        };

        $scope.addButtonDisabled = function () {
            if (!inputEmailsAreMultiple()) {
                if ($scope.participantEmail.length === 0 || !$scope.emailFormat.test($scope.participantEmail)) {
                    return true;
                }
            }
        };

        function addMultipleFromOneInsert() {
            $scope.participantEmail.split(/;|,/).forEach(function (el) {
                var singleMail = el.trim();
                if ($scope.emailFormat.test(singleMail) && participantIsUnique(singleMail)) {
                    $scope.participants.push(singleMail);
                    $scope.participantEmail = "";
                }
            });
        }

        function inputEmailsAreMultiple() {
            var separators = [";", ","];
            return separators.some(el => $scope.participantEmail.includes(el))
        }

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

        $scope.endDatePopup = {
            opened: false
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
                || $scope.questionsInSession.length < 1
        };

        $scope.createSession = function () {
            $scope.showSendSessionLoader = true;
            var convertedEndDate = $filter('date')(new Date($scope.endDate), 'yyyy-MM-dd hh:mm');
            var participants = $scope.convertParticipantEmailsToJson();
            var questions = convertQuestionsToJson();
            SessionService.sendSession($scope.sessionName, convertedEndDate, participants, questions).then(function (data) {
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

        function convertQuestionsToJson() {
            let questionsJson = [];
            for (let i = 0; i < $scope.questionsInSession.length; i++) {
                var questionJson =
                    {
                        default_answers: $scope.questionsInSession[i].default_answers,
                        is_default: false,
                        question_text: $scope.questionsInSession[i].question_text,
                        question_type: $scope.questionsInSession[i].question_type
                    };
                questionsJson.push(questionJson)
            }
            return questionsJson;
        }

        $scope.goToQuestionsPage = function () {
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

