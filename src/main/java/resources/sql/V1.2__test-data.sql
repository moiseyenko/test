insert into contact (first_name, last_name, birth_date, version) values 
('Chris', 'Schaefer', '1981-03-05', 1),
('Scott', 'Tiger', '1990-11-02', 2),
('John',  'Smith', '1964-02-28', 2);
insert into contact_tel_detail (contact_id, tel_type, tel_number) values 
(1,  'MoЬile', '1234567890'),
(1,  'Home', '1234567890'),
(2,  'Home', '1234567890'); 
insert into hobby (hobby_id) values
('Swimming' ), 
('Jogging'), 
('Programming'), 
('Movies'),
('Reading' ); 
insert into contact_hobby_detail(contact_id,  hobby_id) values 
(1, 'Swimming'),
(1, 'Movies'),
(2, 'Swimming'); 