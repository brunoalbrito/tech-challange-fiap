-- Enable the uuid-ossp extension
CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

-- Insert data into 'ingredientes' table
INSERT INTO public.ingredientes (id, descricao)
VALUES (uuid_generate_v4(), 'Ingredient 1'),
       (uuid_generate_v4(), 'Ingredient 2');
-- Add more rows as needed;

-- Insert data into 'produtos' table
INSERT INTO public.produtos (id, descricao, nome, preco, tipo)
VALUES (uuid_generate_v4(), 'Product 1', 'Product 1 Name', 10.99, 'LANCHE'),
       (uuid_generate_v4(), 'Product 2', 'Product 2 Name', 15.99, 'BEBIDA');
-- Add more rows as needed;

-- Insert data into 'produtos_ingredientes' table
INSERT INTO public.produtos_ingredientes (produto_entity_id, ingredientes_id)
VALUES ((SELECT id FROM public.produtos ORDER BY random() LIMIT 1),
       (SELECT id FROM public.ingredientes ORDER BY random() LIMIT 1) );
-- Add more rows as needed;

-- Insert data into 'combos' table
INSERT INTO public.combos (id)
VALUES (uuid_generate_v4());
-- Add more rows as needed;

-- Insert data into 'combos_produtos' table
INSERT INTO public.combos_produtos (combo_entity_id, produtos_id)
VALUES ((SELECT id FROM public.combos ORDER BY random() LIMIT 1),
       (SELECT id FROM public.produtos ORDER BY random() LIMIT 1) )
-- Add more rows as needed;
