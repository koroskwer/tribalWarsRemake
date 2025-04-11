CREATE TABLE spots_north_west PARTITION OF spots FOR VALUES IN ('north-west');
CREATE TABLE spots_north_east PARTITION OF spots FOR VALUES IN ('north-east');
CREATE TABLE spots_south_west PARTITION OF spots FOR VALUES IN ('south-west');
CREATE TABLE spots_south_east PARTITION OF spots FOR VALUES IN ('south-east');