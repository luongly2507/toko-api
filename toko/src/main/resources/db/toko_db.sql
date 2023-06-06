PGDMP     7                     {            toko_db    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16768    toko_db    DATABASE     �   CREATE DATABASE toko_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE toko_db;
                postgres    false            �            1259    77828    album    TABLE     �   CREATE TABLE public.album (
    book_id uuid NOT NULL,
    image_source character varying(255) NOT NULL,
    is_presentation boolean DEFAULT false NOT NULL
);
    DROP TABLE public.album;
       public         heap    postgres    false            �            1259    77839    book    TABLE     �  CREATE TABLE public.book (
    id uuid NOT NULL,
    cost numeric(10,2) NOT NULL,
    description text,
    edition character varying(150) NOT NULL,
    price numeric(10,2) NOT NULL,
    publishcation_date date NOT NULL,
    quantity integer NOT NULL,
    sub_title character varying(150),
    title character varying(150) NOT NULL,
    category_id uuid,
    publisher_id uuid,
    language_id character varying(255),
    language character varying(150) NOT NULL,
    publisher character varying(255) NOT NULL,
    authors character varying(255),
    CONSTRAINT book_cost_check CHECK ((cost >= (0)::numeric)),
    CONSTRAINT book_price_check CHECK ((price >= (0)::numeric)),
    CONSTRAINT book_quantity_check CHECK ((quantity >= 0))
);
    DROP TABLE public.book;
       public         heap    postgres    false            �            1259    77854    category    TABLE     r   CREATE TABLE public.category (
    id uuid NOT NULL,
    name character varying(100) NOT NULL,
    parent uuid
);
    DROP TABLE public.category;
       public         heap    postgres    false                      0    77828    album 
   TABLE DATA           G   COPY public.album (book_id, image_source, is_presentation) FROM stdin;
    public          postgres    false    214   �                 0    77839    book 
   TABLE DATA           �   COPY public.book (id, cost, description, edition, price, publishcation_date, quantity, sub_title, title, category_id, publisher_id, language_id, language, publisher, authors) FROM stdin;
    public          postgres    false    215   D                 0    77854    category 
   TABLE DATA           4   COPY public.category (id, name, parent) FROM stdin;
    public          postgres    false    216   �       q           2606    77833    album album_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.album
    ADD CONSTRAINT album_pkey PRIMARY KEY (book_id, image_source);
 :   ALTER TABLE ONLY public.album DROP CONSTRAINT album_pkey;
       public            postgres    false    214    214            s           2606    77848    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    215            y           2606    77858    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    216            u           2606    85890     book uk6u15t3xvnur06cug4wwwstyy5 
   CONSTRAINT     e   ALTER TABLE ONLY public.book
    ADD CONSTRAINT uk6u15t3xvnur06cug4wwwstyy5 UNIQUE (title, edition);
 J   ALTER TABLE ONLY public.book DROP CONSTRAINT uk6u15t3xvnur06cug4wwwstyy5;
       public            postgres    false    215    215            {           2606    77887 %   category uk_46ccwnsi9409t36lurvtyljak 
   CONSTRAINT     `   ALTER TABLE ONLY public.category
    ADD CONSTRAINT uk_46ccwnsi9409t36lurvtyljak UNIQUE (name);
 O   ALTER TABLE ONLY public.category DROP CONSTRAINT uk_46ccwnsi9409t36lurvtyljak;
       public            postgres    false    216            w           2606    77885     book ukpbywfetppjilk5bu41ujnknxb 
   CONSTRAINT     p   ALTER TABLE ONLY public.book
    ADD CONSTRAINT ukpbywfetppjilk5bu41ujnknxb UNIQUE (title, sub_title, edition);
 J   ALTER TABLE ONLY public.book DROP CONSTRAINT ukpbywfetppjilk5bu41ujnknxb;
       public            postgres    false    215    215    215            |           2606    77894 !   album fk7v1mx4vmaytyruore5vkfnlq3    FK CONSTRAINT        ALTER TABLE ONLY public.album
    ADD CONSTRAINT fk7v1mx4vmaytyruore5vkfnlq3 FOREIGN KEY (book_id) REFERENCES public.book(id);
 K   ALTER TABLE ONLY public.album DROP CONSTRAINT fk7v1mx4vmaytyruore5vkfnlq3;
       public          postgres    false    215    214    3187            }           2606    77899     book fkam9riv8y6rjwkua1gapdfew4j    FK CONSTRAINT     �   ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkam9riv8y6rjwkua1gapdfew4j FOREIGN KEY (category_id) REFERENCES public.category(id);
 J   ALTER TABLE ONLY public.book DROP CONSTRAINT fkam9riv8y6rjwkua1gapdfew4j;
       public          postgres    false    215    3193    216               �   x���M
�0�us�)�߻�I�� �p��0b�����v/`�S���u��b�R�Z@Lk�#c&�2��0�~�$|9�_,���K����MO������mQ����m�h��;kGE;)ڄ��`����4�¯�ek�)�8�.�1� h!         �  x�mUMO�V]�_q@9|�.a3�+Ģb���"~��sDfbQUh�U�&Mif@�@Ua/X<��a~IϽv"@E�����s�[r\�Y_�W��Be��=WQ��U\o��\r����b�����8���O�YzD�+�А����Àt˷E���L'K����ғ��Gx��Nvю��C�ő:����׺EO�A�ļ"K5y1yx���Ha}\�!Vv��X���*�Xb�ء�pY2
i'�~�P%�t���]}�?�
�D�N;�-���Ԛ@��B��4m��ZYr��f|C~dx�jE
���ȋ4JH�w��d���y�OĻ� #����;����eɗ�zv@m;�(�(��}׭25����蚛����6�]T�QЈ��6�&�+DZ���w�����9nNx���ގ��#��ɵ#2h?�د1�\kKr�����ߑ<����E�iK��r���=A��!z�Q�W-Z��@ N�BL�G��iܻ�uX�U<�C��< 5���{9㕆>"���^T�`��=�9רJ+�y�^�A�ꞽ�8B���O��^�e�Y�;��y|B��H�g�D}��ɪ;-x�2eX�
�ÙL �����1���F�c�Ȉ$��/��"�c�aD�|�������aXH��\�Bg��Z����U�>!�Aƚ�����K�4 �����y�6�"X�?��T��8�S�%���#O��y��D��Uq�6Q:��4v��|s0�
Ċ[��6��݀�ދY�������X0þ��t�5O�Iȭ$�F$�(�$X��˘�yD�7��N��U\��-�)㫾���|mYR����c/�IC���Ğ��N��&��;E�6�0SL�XX�p ���s�G���L�o�%e�}05Y�&c'q�������D�������l�o�Ȁ6���:]�_?��A:y�y�n�1��N��k8��+#cy����("�)�3��b�}
^�k�C2&a����4�&�P]q�W��6��G.QOK�����餿�l1���k �&ɘ~�rye̃� ��sKi˖S�E&���X�B��˶t�Ts���<���U�~�tJ[�%a~̯�̯
�`��4?��g]UYr��ygn��������v�����oC�lѦL~��h�m|��P�~���5w"j4iSu�Q^�ug��333�Fpz         .  x��W��$E���" V�~��hZ�a�܈�Q���QO56�j��@ijF+-�H�P%�Qh�#����=,'q���3o�8��s����rd�8���8��OBer�*�ک�������|���-&�[VJ#b1x!�/�$VlWAr6�Y�䌰�ԏR�b�Ӟ�v�{��5����J'5�g���9���1K�e5�*J�� |�u}�V�:�b?���q}5<�t�*]S�B遤���HIr �C�n�j-�h#�V�Z��>�P�s�����+=呟�%��*����"�Ϣ�TS�Dڴ��fX^�k#���G.�M"��"I錋R�w�7L㲂K���yڗM�����~���+�X'��H���V��:YíDb�ޙ�?�������t�d\w�d�c�$k�&��jUh�ٓ��g�R��P�(��
Т�d֪�$�hս7��~3�]���e�j?,ӏrvf 	-3ԮtIx���N��)���t������������?t��d�B����D�d��d��h�j	�Ԏ��t��;����2xK��w�V{�RU-��C��ͫo_����گ��~�������n��qL|����V�����%k�=��_�r����dC׸�/�gĉ����Z��9>�.G�8�V�~sɻ�E��i?���sh���.�9D���ACB35n9�rY�bرķ�V�@�`U��/�h6��n���?�댪/��O�av`����I[8HRݣӋ[�������ؗ���Y��d:~1t�}�Rx
��<�$xlLs��ɕ1���J�:��A��eT1%m3w����b�Rw����� �ni�,c�	C��[AGv.�y�6�������~���7��0�
�Ìˢ�0��0\��� ����*��Ɩ>gl��_�>�e2d��Ѻ�C�yV�6����-������5�����:!y�t���p��Rh�%tI�)��;�d�����ߡ�C�?�%�~ŏ�1�Qo1#�9�$� ���&1&cw�>}���!�E����3x��飅��%�R6��� �g��t/I�bڞ�� �D�*X#�F�Ya5�  �)@�E\c@/g�o�>��[��%�������ߞ���K�'i�l��ö�����k��)͚x13|7+xv��r�Cp��@D:���&����R�ɛ���7�W�m|�Wf�-3�V�#a;�5&\�o����_O�dO�{q���ܜ- ��ha�^t�����%၊=��Y�7k�I��[��6D��
S$r)6��w��W��ck���,�]ܞ�0h����~V�p���%�ɳۊ���&q���t����9�����%8?�n�LeK�ҽ.�=<��1���8�gg���3Tg�-��l?ӏ�t�����_�:.�ǔ�SN��cmH�h�Ii���0�֛9b����f�����`	�o�� �p�O�AG�:p����~=���챨�\R򍭠y4 pq@z��B���o���e�5v
��?�cDTJw. �Sm�Į������_V��/�D�     