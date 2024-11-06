-- insert 10 drones

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'MIDDLEWEIGHT', 'SN1', 'IDLE', 400, 10000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'CRUISERWEIGHT', 'SN2', 'IDLE', 350, 15000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'LIGHTWEIGHT', 'SN3', 'IDLE', 400, 8000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'MIDDLEWEIGHT', 'SN4', 'IDLE', 400, 10000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'CRUISERWEIGHT', 'SN5', 'IDLE', 350, 15000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'LIGHTWEIGHT', 'SN6', 'IDLE', 400, 8000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'MIDDLEWEIGHT', 'SN7', 'IDLE', 400, 10000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'CRUISERWEIGHT', 'SN8', 'IDLE', 350, 15000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'LIGHTWEIGHT', 'SN9', 'IDLE', 400, 8000);

INSERT INTO drone (id, model, serial_number, state, weight_limit, battery_capacity)
VALUES (random_uuid(), 'MIDDLEWEIGHT', 'SN10', 'IDLE', 400, 10000);

-- insert 5 medications
INSERT INTO medication (id, name, weight, code)
VALUES (random_uuid(), 'Paracetamol', 0.1, 'PCT');

INSERT INTO medication (id, name, weight, code)
VALUES (random_uuid(), 'Ibuprofen', 0.2, 'IBP');

INSERT INTO medication (id, name, weight, code)
VALUES (random_uuid(), 'Aspirin', 0.05, 'ASR');

INSERT INTO medication (id, name, weight, code)
VALUES (random_uuid(), 'Amoxicillin', 0.1, 'AMX');

INSERT INTO medication (id, name, weight, code)
VALUES (random_uuid(), 'Ciprofloxacin', 0.15, 'CPF');