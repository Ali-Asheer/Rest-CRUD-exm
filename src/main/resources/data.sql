-- Insert Channel
INSERT INTO channel (name) VALUES ('Sports');
INSERT INTO channel (name) VALUES ('News');
INSERT INTO channel (name) VALUES ('Music');

-- Insert Messages
INSERT INTO messages (name, channel_id) VALUES ('Welcome to the Sports channel!', 1);
INSERT INTO messages (name, channel_id) VALUES ('Latest sports updates.', 1);
INSERT INTO messages (name, channel_id) VALUES ('Breaking news in the world.', 2);
INSERT INTO messages (name, channel_id) VALUES ('Top hits of the week!', 3);
