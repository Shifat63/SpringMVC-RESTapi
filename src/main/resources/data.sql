INSERT INTO category (name) VALUES ('American');
INSERT INTO category (name) VALUES ('Italian');
INSERT INTO category (name) VALUES ('Mexican');
INSERT INTO category (name) VALUES ('Fast Food');

INSERT INTO ingredient (name) VALUES ('Milk');
INSERT INTO ingredient (name) VALUES ('Onion');
INSERT INTO ingredient (name) VALUES ('Garlic');
INSERT INTO ingredient (name) VALUES ('Ginger');
INSERT INTO ingredient (name) VALUES ('Spice');
INSERT INTO ingredient (name) VALUES ('Rice');
INSERT INTO ingredient (name) VALUES ('Chicken');
INSERT INTO ingredient (name) VALUES ('Beef');

INSERT INTO recipe (name)
VALUES ('Perfect Guacamole');
INSERT INTO recipe (name)
VALUES ('Spicy Grilled Chicken Taco');

INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (1, 2);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (1, 4);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (1, 6);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (1, 7);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (2, 1);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (2, 3);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (2, 5);
INSERT INTO recipe_ingredient (recipe_id, ingredient_id) VALUES (2, 8);

INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 2);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (1, 4);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 1);
INSERT INTO recipe_category (recipe_id, category_id) VALUES (2, 3);

