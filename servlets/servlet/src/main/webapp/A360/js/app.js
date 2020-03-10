angular.module('a360', ['ngRoute', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'toastr'], function($routeProvider) {
    $routeProvider.
    when('/session', {templateUrl: 'components/session/createSession.html', controller: 'SessionController'}).
    when('/sessionCreated', {templateUrl: 'components/session/sessionCreated.html', controller: 'SessionController'}).
    when('/feedback/:uId', {templateUrl: 'components/feedback/feedback.html', controller: 'FeedbackController'}).
    when('/feedbackCreated', {templateUrl: 'components/feedback/feedbackCreated.html'}).
    when('/statistics', {templateUrl: 'components/statistics/statistics.html', controller: 'StatisticsController'}).
    when('/welcome', {templateUrl: 'components/welcome/welcome.html', controller: 'WelcomeController'}).
    when('/questions', {templateUrl: 'components/questions/questions.html', controller: 'QuestionsController'}).
    when('/invalidLink', {templateUrl: 'components/feedback/invalidLink.html', controller: 'FeedbackController'}).
    when('/users', {templateUrl: 'components/users/users.html', controller: 'UsersController'}).
    when('/main', {templateUrl: 'components/login/main.html', controller: 'MainController'}).
    when('/register', {templateUrl: 'components/register/register.html', controller: 'RegisterController'}).
    otherwise({ redirectTo: '/welcome' });
});

