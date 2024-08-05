-- Insert brands
INSERT INTO brand (id, name)
VALUES
    (nextval('brand_seq'), 'Werner'),
    (nextval('brand_seq'), 'Stihl'),
    (nextval('brand_seq'), 'DeWalt'),
    (nextval('brand_seq'), 'Ridgid')
;

-- Insert tool types
INSERT INTO tool_type (id, name, daily_charge, is_weekday_charge, is_weekend_charge, is_holiday_charge)
VALUES
    (nextval('tool_type_seq'), 'Ladder', 1.99, true, true, false),
    (nextval('tool_type_seq'), 'Chainsaw', 1.49, true, false, true),
    (nextval('tool_type_seq'), 'Jackhammer', 2.99, true, false, false)
;

INSERT INTO tool (code, brand_id, tool_type_id)
VALUES
    ('CHNS',
     (SELECT id FROM brand WHERE name = 'Stihl'),
     (SELECT id FROM tool_type WHERE name = 'Chainsaw')),
    ('LADW',
     (SELECT id FROM brand WHERE name = 'Werner'),
     (SELECT id FROM tool_type WHERE name = 'Ladder')),
    ('JAKD',
     (SELECT id FROM brand WHERE name = 'DeWalt'),
     (SELECT id FROM tool_type WHERE name = 'Jackhammer')),
    ('JAKR',
     (SELECT id FROM brand WHERE name = 'Ridgid'),
     (SELECT id FROM tool_type WHERE name = 'Jackhammer'))
;