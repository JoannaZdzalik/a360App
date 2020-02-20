angular.module('a360', ['ngRoute', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap'], function($routeProvider) {
    $routeProvider.
          when('/session', {templateUrl: 'components/session/createSession.html', controller: 'SessionController'}).
          when('/feedback/:uId', {templateUrl: 'components/feedback/feedback.html', controller: 'FeedbackController'}).
                otherwise({ redirectTo: '/session' });
});

// angular.module('a360', ['ngRoute', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap'], function($routeProvider) {
//     $routeProvider.
//     when('/session', {templateUrl: 'components/session/createSession.html', controller: 'SessionController'}).
//     when('/feedback/:uid', {templateUrl: 'components/feedback/feedback.html', controller: 'FeedbackController'}).
//     otherwise({ redirectTo: '/session' });
// });