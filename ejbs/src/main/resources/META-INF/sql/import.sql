INSERT INTO sessions (name, end_date) VALUES ('Avenga Spring edition', '2020-02-06 13:03:00')
insert into sessions (name, end_date) values('sesja4', '2020-02-06 13:10:00')
insert into sessions (name, end_date) values('sesja2', '2020-02-06 13:20:00')

INSERT INTO participants (uid, email, id_session) VALUES ('123456789012345','anna@avenga.com', 1), ('abc456789012345','asia@avenga.com', 1), ('qwe456789012345','lukasz@avenga.com', 1), ('tyu456789012345','piotr@avenga.com', 1)
INSERT INTO questions (question_text, question_type, default_answers) VALUES ('What do you value him/her for', 'TEXT', NULL  ), ('Whats he or she doing wrong?', 'TEXT', NULL ), ('What can change to make working with him or her better?', 'TEXT', NULL), ('How his person affects the atmosphere in the team?', 'RADIO', 'Negative;Neutral;Positive')
INSERT INTO sessions_questions (id_session, id_question) VALUES (1, 1), (1, 2), (1, 3), (1, 4)
INSERT INTO answers (id_question, id_participant, answer_text) VALUES (1, 1, 'domyslny'), (1, 1, 'domyslny'), (2, 1, 'domyslny'), (3, 1, 'domyslny'), (4, 1, 'Positive'), (2,1, 'aaaa')