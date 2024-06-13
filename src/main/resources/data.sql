-- Insert Customers
INSERT INTO customer (id, username, name, address) VALUES (1, 'custuser1', 'Customer One', 'Address One');
INSERT INTO customer (id, username, name, address) VALUES (2, 'custuser2', 'Customer Two', 'Address Two');

-- Insert Rooms
INSERT INTO room (id, name, max_guests, equipment) VALUES (1, 'Room A', 50, 'Projector');
INSERT INTO room (id, name, max_guests, equipment) VALUES (2, 'Room B', 30, 'Whiteboard');

-- Insert Bookings
INSERT INTO booking (id, customer_id, number_of_guests, date, total_price_sek, total_price_euro, room_id)
VALUES (1, 1, 10, '2024-06-10', 1000, 100, 1);
INSERT INTO booking (id, customer_id, number_of_guests, date, total_price_sek, total_price_euro, room_id)
VALUES (2, 2, 5, '2024-05-15', 500, 50, 2);

-- Insert CustomerOrders
INSERT INTO customer_order (id, customer_id, total_price_sek, total_price_euro)
VALUES (1, 1, 200, 20);
INSERT INTO customer_order (id, customer_id, total_price_sek, total_price_euro)
VALUES (2, 2, 300, 30);

-- Insert Dishes
INSERT INTO dish (id, name, price_sek, price_euro, booking_id, order_id)
VALUES (1, 'Sushi Roll', 100, 10, 1, NULL);
INSERT INTO dish (id, name, price_sek, price_euro, booking_id, order_id)
VALUES (2, 'Sashimi', 150, 15, 2, NULL);
INSERT INTO dish (id, name, price_sek, price_euro, booking_id, order_id)
VALUES (3, 'Tempura', 120, 12, NULL, 1);
INSERT INTO dish (id, name, price_sek, price_euro, booking_id, order_id)
VALUES (4, 'Miso Soup', 80, 8, NULL, 2);

-- Associate Dishes with Bookings and CustomerOrders
-- Here the foreign key booking_id or order_id will ensure the association.
