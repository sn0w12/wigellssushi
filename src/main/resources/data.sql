-- Insert Customers
INSERT INTO sushi_customers (id, username, name, address) VALUES (1, 'custuser1', 'Customer One', 'Address One');
INSERT INTO sushi_customers (id, username, name, address) VALUES (2, 'custuser2', 'Customer Two', 'Address Two');

-- Insert Rooms
INSERT INTO sushi_rooms (id, name, max_guests, equipment) VALUES (1, 'Room A', 8, 'Projector');
INSERT INTO sushi_rooms (id, name, max_guests, equipment) VALUES (2, 'Room B', 5, null);

-- Insert CustomerOrders
INSERT INTO sushi_customerorders (id, customer_id, total_price_sek, total_price_euro, takeaway) VALUES (1, 1, 200, 20, true);
INSERT INTO sushi_customerorders (id, customer_id, total_price_sek, total_price_euro, takeaway) VALUES (2, 2, 300, 30, false);

-- Insert Bookings
INSERT INTO sushi_bookings (id, customer_id, number_of_guests, date, total_price_sek, total_price_euro, room_id, customer_order_id)
VALUES (1, 1, 8, '2024-06-10', 1000, 100, 1, 1);
INSERT INTO sushi_bookings (id, customer_id, number_of_guests, date, total_price_sek, total_price_euro, room_id, customer_order_id)
VALUES (2, 2, 5, '2024-05-15', 500, 50, 2, 2);

-- Insert Dishes
INSERT INTO sushi_dishes (id, name, price_sek, price_euro) VALUES (1, 'Sushi Roll', 100, 8.9);
INSERT INTO sushi_dishes (id, name, price_sek, price_euro) VALUES (2, 'Sashimi', 150, 13.35);
INSERT INTO sushi_dishes (id, name, price_sek, price_euro) VALUES (3, 'Tempura', 120, 10.68);
INSERT INTO sushi_dishes (id, name, price_sek, price_euro) VALUES (4, 'Miso Soup', 80, 7.12);

-- Insert Order_Dish relationships
INSERT INTO sushi_order_dish (order_id, dish_id) VALUES (1, 1);
INSERT INTO sushi_order_dish (order_id, dish_id) VALUES (2, 2);
INSERT INTO sushi_order_dish (order_id, dish_id) VALUES (1, 3);
INSERT INTO sushi_order_dish (order_id, dish_id) VALUES (2, 4);
