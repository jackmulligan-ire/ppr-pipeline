-- PROCEDURE: public.populate_dim_date()

-- DROP PROCEDURE IF EXISTS public.populate_dim_date();

CREATE OR REPLACE PROCEDURE public.populate_dim_date(
	)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN

INSERT INTO 
dim_location(location_key, location_county, location_nuts_3, location_province)
VALUES 
(1, 'Carlow', 'South-East', 'Leinster'),
(2, 'Cavan', 'Border', 'Ulster'),
(3, 'Clare', 'Mid-West', 'Munster'),
(4, 'Cork', 'South-West', 'Munster'),
(5, 'Donegal', 'Border', 'Ulster'),
(6, 'Dublin', 'Dublin', 'Leinster'),
(7, 'Galway', 'West', 'Connacht'),
(8, 'Kerry', 'South-West', 'Munster'),
(9, 'Kildare', 'Mid-East', 'Leinster'),
(10, 'Kilkenny', 'South-East', 'Leinster'),
(11, 'Laois', 'Midlands', 'Leinster'),
(12, 'Leitrim', 'Border', 'Connacht'),
(13, 'Limerick', 'Mid-West', 'Munster'),
(14, 'Longford', 'Midlands', 'Leinster'),
(15, 'Louth', 'Mid-East', 'Leinster'),
(16, 'Mayo', 'West', 'Connacht'),
(17, 'Meath', 'Mid-East', 'Leinster'),
(18, 'Monaghan', 'Border', 'Ulster'),
(19, 'Offaly', 'Midlands', 'Leinster'),
(20, 'Roscommon', 'West', 'Connacht'),
(21, 'Sligo', 'Border', 'Connacht'),
(22, 'Tipperary', 'Mid-West', 'Munster'),
(23, 'Waterford', 'South-East', 'Munster'),
(24, 'Westmeath', 'Midlands', 'Leinster'),
(25, 'Wexford', 'South-East', 'Leinster'),
(26, 'Wicklow', 'Mid-East', 'Leinster');
END;
$BODY$;
ALTER PROCEDURE public.populate_dim_date()
    OWNER TO postgres;
