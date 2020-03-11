insert into sessions (session_name, end_date) values ('session 1', '2020-09-08 14:30:00');
insert into sessions (session_name, end_date) values ('session 2', '2020-03-19 11:30:00');
insert into sessions (session_name, end_date) values ('session 3', '2020-02-08 14:30:00');
insert into questions (question_text, question_type, default_answers) values ('What do you value him for ?', 'TEXT', null);
insert into questions (question_text, question_type, default_answers) values ('What''s he or she doing wrong ?', 'TEXT', null);
insert into questions (question_text, question_type, default_answers) values ('What can change to make working with him or her better ?', 'TEXT', null);
insert into questions (question_text, question_type, default_answers) values ('How his person affects the atmosphere in team ?', 'RADIO', 'Positive;Neutral;Negative');
insert into sessions_questions (id_session, id_question) values (1, 1);
insert into sessions_questions (id_session, id_question) values (1, 2);
insert into sessions_questions (id_session, id_question) values (1, 3);
insert into sessions_questions (id_session, id_question) values (1, 4);
insert into sessions_questions (id_session, id_question) values (2, 1);
insert into sessions_questions (id_session, id_question) values (2, 2);
insert into sessions_questions (id_session, id_question) values (2, 3);
insert into sessions_questions (id_session, id_question) values (2, 4);
insert into sessions_questions (id_session, id_question) values (3, 1);
insert into sessions_questions (id_session, id_question) values (3, 2);
insert into participants (email, id_session, uid) values ('a@a.com', 1, '12345');
insert into participants (email, id_session, uid) values ('b@a.com', 1, '123456');
insert into participants (email, id_session, uid) values ('c@a.com', 1, '12345633');
insert into participants (email, id_session, uid) values ('ax@a.com', 2, '145');
insert into participants (email, id_session, uid) values ('bx@a.com', 2, '1456');
insert into participants (email, id_session, uid) values ('cx@a.com', 2, '145633');
insert into participants (email, id_session, uid) values ('www@a.com', 3, '992');
insert into participants (email, id_session, uid) values ('rrr@a.com', 3, '990');
insert into participants (email, id_session, uid) values ('zzz@a.com', 3, '993');
insert into answers (answer_text, id_participant, id_question) values ('Good', 1, 1);
insert into answers (answer_text, id_participant, id_question) values ('Bad', 2, 2);
insert into answers (answer_text, id_participant, id_question) values ('The best', 2, 3);
insert into answers (answer_text, id_participant, id_question) values ('Bad', 1, 1);
insert into answers (answer_text, id_participant, id_question) values ('answer 2', 1, 2);
insert into answers (answer_text, id_participant, id_question) values ('answer 3', 1, 3);
insert into answers (answer_text, id_participant, id_question) values ('Super', 1, 1);
insert into users (login, password, role) values ('asia', 'asia', 'ADMIN');
insert into users (login, password, role) values ('iza', '55mojehaslo', 'DESIGNER');
insert into users (login, password, role) values ('olaf', 'haSLo1', 'DESIGNER');
insert into configuration (host, port, fromAdress, username, password, auth, debug) values ('localhost', 25, 'ja@a360session.com', 'admin', 'admin','true', 'true');