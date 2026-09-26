-- Datos minimos para que la app funcione localmente.
INSERT IGNORE INTO services (id, name, identifier, sms, short_code, product_identifier, descripcion_producto)
VALUES (1, 'md', '1', 'GANA @pin', 9090, 'COPA', 'test');

INSERT IGNORE INTO config (id, config_key, `value`, description) VALUES
    (1,  'silver-api-url',        'http://silverapi.hecticus.com/',                                   'URL base API silver'),
    (2,  'globality-url',         'http://ad.globadlity.com/wss2s.asmx/ConfirmS2S?trx_id=',           'URL globality'),
    (3,  'mobrain-url',           'http://mobrain.example/url/',                                      'URL mobrain'),
    (4,  'mobrain-token',         'change-me',                                                        'Token mobrain'),
    (5,  'spiralis-url',          'http://spiralis.example/url/',                                     'URL spiralis'),
    (6,  'mobusi-url',            'http://mobusi.example/url/',                                       'URL mobusi'),
    (7,  'logan-url',             'http://logan.example/url/',                                        'URL logan'),
    (8,  'armor-url',             'http://armor.example/url/',                                        'URL armor'),
    (9,  'current-amount',        '0.50',                                                             'Monto actual appland'),
    (10, 'appland-current-amount','0.50',                                                             'Monto actual ciudad juego'),
    (11, 'appland-date-amount',   '01/01/2026',                                                       'Fecha de marcaje ciudad juego'),
    (12, 'date-amount',           '01/01/2026',                                                       'Fecha de marcaje appland');
