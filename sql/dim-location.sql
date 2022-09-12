CREATE TABLE IF NOT EXISTS public.dim_location (
    "location_key" INTEGER NOT NULL PRIMARY KEY,
    "county" VARCHAR(20) NOT NULL,
    "nuts_3" VARCHAR(20) NOT NULL,
    "province" VARCHAR(20) NOT NULL
);

INSERT INTO public.dim_location (location_key, county, nuts_3, province) VALUES
(1,'Carlow','South-East','Leinster'),
(2,'Cavan','Border','Leinster'),
(3,'Clare','Mid-West','Munster'),
(4,'Cork','South-West','Munster'),
(5,'Donegal','Border','Ulster'),
(6,'Dublin','Dublin','Leinster'),
(7,'Galway','West','Connacht'),
(8,'Kerry','South-West','Munster'),
(9,'Kildare','Mid-East','Leinster'),
(10,'Kilkenny','South-East','Leinster'),
(11,'Laois','Midlands','Leinster'),
(12,'Leitrim','Border','Connacht'),
(13,'Limerick','Mid-West','Munster'),
(14,'Longford','Midlands','Leinster'),
(15,'Louth','Mid-East','Leinster'),
(16,'Mayo','West','Connacht'),
(17,'Meath','Mid-East','Leinster'),
(18,'Monaghan','Border','Ulster'),
(19,'Offaly','Midlands','Leinster'),
(20,'Roscommon','West','Connacht'),
(21,'Sligo','Border','Connacht'),
(22,'Tipperary','Mid-West','Munster'),
(23,'Waterford','South-East','Munster'),
(24,'Westmeath','Midlands','Leinster'),
(25,'Wexford','South-East','Leinster'),
(26,'Wicklow','Mid-East','Leinster');