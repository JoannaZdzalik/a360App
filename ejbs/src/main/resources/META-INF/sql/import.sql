INSERT INTO questions (question_text, question_type, default_answers) VALUES ('What do you value him/her for?', 'TEXT', NULL  ), ('Whats he or she doing wrong?', 'TEXT', NULL ), ('What can change to make working with him or her better?', 'TEXT', NULL), ('How his person affects the atmosphere in the team?', 'RADIO', 'Negative;Neutral;Positive')

INSERT INTO sessions (name, end_date) VALUES ('Avenga Spring edition', '2020-02-06 13:03:00')
INSERT INTO participants (uid, email, id_session) VALUES ('123456789012345','anna@avenga.com', 1), ('abc456789012345','asia@avenga.com', 1)
INSERT INTO sessions_questions (id_session, id_question) VALUES (1, 1), (1, 2), (1, 3), (1, 4)
INSERT INTO answers (id_question, id_participant, answer_text) VALUES (1, 1, 'For being nice'), (1, 1, 'She is bla bla'), (2, 1, 'Bad in making coffee'), (3, 1, 'nothing to change'), (4, 1, 'Positive'), (2,1, 'never talks')
INSERT INTO answers (id_question, id_participant, answer_text) VALUES (1, 2, 'good'), (1, 2, 'good 2 '), (2, 2, 'Bad in sth else'), (3, 2, 'a lot to change'), (4, 2, 'Newutral'), (2,2, 'never smiles')