DELETE FROM messages;
DELETE FROM users;
INSERT INTO users ("id", "avatar", "username") VALUES ("id1", "avatarLink", "name");
INSERT INTO messages ("channel_name", "subtype", "text", "ts", "type", "user_id")
VALUES ("testChannel", "subtype", "this is message", "1492708134", "type", "id1");