angular.module('a360', ['ngRoute', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'toastr'], function($routeProvider) {
    $routeProvider.
    when('/main', {templateUrl: 'components/login/main.html', controller: 'MainController'}).
    when('/register', {templateUrl: 'components/register/register.html', controller: 'RegisterController'}).
    when('/registerSuccess', {templateUrl: 'components/register/registerSuccess.html', controller: 'RegisterController'}).
    when('/welcome', {templateUrl: 'components/welcome/welcome.html', controller: 'WelcomeController'}).

    when('/session', {templateUrl: 'components/session/createSession.html', controller: 'SessionController'}).
    when('/sessionCreated', {templateUrl: 'components/session/sessionCreated.html', controller: 'SessionController'}).
    when('/modalQues', {templateUrl: 'components/questions/questions.html', controller: 'ModalQuesController'}).
    when('/questions', {templateUrl: 'components/questions/questions.html', controller: 'QuestionsController'}).

    when('/feedback/:uId', {templateUrl: 'components/feedback/feedback.html', controller: 'FeedbackController'}).
    when('/feedbackCreated', {templateUrl: 'components/feedback/feedbackCreated.html'}).
    when('/invalidLink', {templateUrl: 'components/feedback/invalidLink.html', controller: 'FeedbackController'}).

    when('/statistics', {templateUrl: 'components/statistics/statistics.html', controller: 'StatisticsController'}).
    when('/modalSeeParticipants', {templateUrl: 'components/statistics/statistics.html', controller: 'ModalSeeParticipantsController'}).
    when('/questionsManager', {templateUrl: 'components/questionsManager/questionsManager.html', controller: 'QuestionsManagerController'}).
    when('/modalAddQuestion', {templateUrl: 'components/questionsManager/questionsManager.html', controller: 'ModalAddQuestionController'}).
    when('/users', {templateUrl: 'components/users/users.html', controller: 'UsersController'}).

    when('/config', {templateUrl: 'components/appParameters/appConfig.html', controller: 'AppParamsController'}).
    when('/modalEditConfig', {templateUrl: 'components/appParameters/appConfig.html', controller: 'ModalController'}).

    otherwise({ redirectTo: '/welcome' });
});

