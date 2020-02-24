(function () {
    "use strict";
    angular.module('a360').controller('FeedbackController', FeedbackController);
    FeedbackController.$inject = ['$scope', '$routeParams', 'FeedbackService'];

    function FeedbackController($scope, $routeParams, FeedbackService) {

        $scope.init = function () {

            $scope.title = "Feedback for ";
            $scope.participantMail = "";
            $scope.sessionId = "";
            $scope.questionText = "";
            $scope.questions = [];
            $scope.participantUId = $routeParams.uId;
            $scope.feedback = [];
            getParticipantByUid();


        };

        function getParticipantByUid() {

            FeedbackService.getParticipant($routeParams.uId).then(function (data) {
                console.log('GET participant  ' + data.email + data.sessionId);
                $scope.participantMail = data.email;
                $scope.sessionId = data.sessionId;
                $scope.getQuestions(data.sessionId)
            }, function (response) {
                alert('error');
            });
        }

        // function getQuestionsBySessionId(sessionId) {
        //
        //     FeedbackService.getQuestions(sessionId).then(function (response) {
        //         console.log('questions: ' + response);
        //         $scope.questions = response;
        //
        //     }, function (response) {
        //         alert('error questions');
        //     });
        // }
        $scope.getQuestions = function (sessionId) {
            FeedbackService.getQuestions(sessionId).then(function (data) {
                data.forEach(function (el) {
                    var question = {
                        questionId: el.question_id,
                        questionText: el.question_text,
                        questionType: el.question_type,
                        defaultAnswers: [],
                        answer: null
                    }
                    if(el.default_answers != null) {
                        el.default_answers.split(';').forEach(function (el) {
                            var answerValue = {
                                value: el
                            }
                            question.defaultAnswers.push(answerValue);

                        })
                        question.answer= question.defaultAnswers[0].value;
                    }
                    $scope.questions.push(question);
                });
                console.log($scope.questions);
            }, function (response) {
            });
        };
        $scope.sendFeedback = function () {
            $scope.questions.forEach(function (element) {
                var feedback = {
                    answerText: element.answer,
                    questionId: element.questionId,
                    participantUId: $scope.participantUId
                };
                    if(element.answer !=null) {
                    $scope.feedback.push(feedback);
                }



            })
            FeedbackService.send($scope.feedback).then(function (data) {
                console.log($scope.feedback);
            }, function (response) {

            });







        }
















    }
})();