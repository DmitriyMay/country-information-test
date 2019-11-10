create table translation(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`de` varchar(100),
`es` varchar(100),
`fr` varchar(100),
`ja` varchar(100),
`it` varchar(100),
`br` varchar(100),
`pt` varchar(100),
`nl` varchar(100),
`hr` varchar(100),
`fa` varchar(100),
primary key (`id`))
ENGINE=InnoDB AUTO_INCREMENT=1;

create table country(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`name` varchar(100),
`population` bigint,
`translation_id` integer unsigned,
`flag` varchar(100),
primary key (`id`),
constraint `trns_fk` foreign key (`translation_id`) references `translation` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;


create table level_domain(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`cou_id` integer unsigned,
`def` varchar(100),
primary key (`id`),
 constraint `lvld_cou_fk` foreign key (`cou_id`) references `country` (`id`) ON DELETE CASCADE
)
ENGINE=InnoDB AUTO_INCREMENT=1;

create table currency(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`cou_id` integer unsigned,
`code` varchar(100),
`name` varchar(100),
`symbol` varchar(100),
primary key (`id`),
constraint `crnc_cou_fk` foreign key (`cou_id`) references `country` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;

create table language(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`cou_id` integer unsigned,
`iso639_1` varchar(100),
`iso639_2` varchar(100),
`name` varchar(100),
`native_name` varchar(100),
primary key (`id`),
constraint `lang_cou_fk` foreign key (`cou_id`) references `country` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;

create table regional_bloc(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`cou_id` integer unsigned,
`name` varchar(100),
`acronym` varchar(100),
primary key (`id`),
constraint `reg_b_cou_fk` foreign key (`cou_id`) references `country` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;

create table other_acronym(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`regional_bloc_id` integer unsigned,
`other_acronym` varchar(100),
primary key (`id`),
constraint `ot_acr_reg_b_fk` foreign key (`regional_bloc_id`) references `regional_bloc` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;

create table other_name(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`regional_bloc_id` integer unsigned,
`other_name` varchar(100),
primary key (`id`),
constraint `ot_name_reg_b_fk` foreign key (`regional_bloc_id`) references `regional_bloc` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;

create table latlng(`id` integer unsigned NOT NULL AUTO_INCREMENT,
`cou_id` integer unsigned,
`latlng_v` double,
primary key (`id`),
constraint `latlng_cou_fk` foreign key (`cou_id`) references `country` (`id`) ON DELETE CASCADE)
ENGINE=InnoDB AUTO_INCREMENT=1;
