INSERT INTO public.clientes (cpf) VALUES
	 ('70262120011');

INSERT INTO public.ingredientes (id,descricao) VALUES
	 ('d4880892-6b5d-490a-be48-eac8bc3370ba','hamburguer'),
	 ('80b3a73c-eb86-45ad-9f8b-599bf3f09a0e','morango');

INSERT INTO public.produtos (id,descricao,nome,preco,tipo) VALUES
	 ('6444030b-67ab-42d5-83a0-3f70ed358cdd','Lanche com Bacon','XBacon',8.00,'LANCHE'),
	 ('59b30987-dec4-4eee-a891-403366d472de','Vitamina de Morango','Suco de Morango',15.00,'BEBIDA'),
	 ('67d9da33-509f-4e13-b8d1-49d535c32cc3','Bolo de Morango','Bolo',15.00,'ACOMPANHAMENTO');

INSERT INTO public.produtos_ingredientes (produto_entity_id,ingredientes_id) VALUES
	 ('67d9da33-509f-4e13-b8d1-49d535c32cc3','80b3a73c-eb86-45ad-9f8b-599bf3f09a0e'),
	 ('6444030b-67ab-42d5-83a0-3f70ed358cdd','d4880892-6b5d-490a-be48-eac8bc3370ba'),
	 ('59b30987-dec4-4eee-a891-403366d472de','80b3a73c-eb86-45ad-9f8b-599bf3f09a0e');

INSERT INTO public.combos (id) VALUES
	 ('73f25cde-6e23-4c5c-a18f-e148bf2c9307');

INSERT INTO public.combos_produtos (combo_entity_id,produtos_id) VALUES
	 ('73f25cde-6e23-4c5c-a18f-e148bf2c9307','6444030b-67ab-42d5-83a0-3f70ed358cdd'),
	 ('73f25cde-6e23-4c5c-a18f-e148bf2c9307','59b30987-dec4-4eee-a891-403366d472de'),
	 ('73f25cde-6e23-4c5c-a18f-e148bf2c9307','67d9da33-509f-4e13-b8d1-49d535c32cc3');

INSERT INTO public.pedidos (id,payment_id,payment_qr_code,status,client_id) VALUES
	 ('60e54eb2-9a8e-49f7-8f69-a177abc4feb1','be9dba14-cd1c-4bf0-8bcb-fa734d70feb5','d2962ef7-0caa-44b2-acfe-ebd2354cac3e','ENTREGUE','70262120011');

INSERT INTO public.pedidos_produtos (pedido_entity_id,produtos_id) VALUES
	 ('60e54eb2-9a8e-49f7-8f69-a177abc4feb1','6444030b-67ab-42d5-83a0-3f70ed358cdd'),
	 ('60e54eb2-9a8e-49f7-8f69-a177abc4feb1','59b30987-dec4-4eee-a891-403366d472de');

