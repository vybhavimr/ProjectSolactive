drop table if exists `instrument_detail`;
CREATE TABLE `instrument_detail` (
  `id` varchar(50) NOT NULL,
  `price` double(20) NOT NULL,
  `givenTimestamp` numeric(40) NOT NULL
);