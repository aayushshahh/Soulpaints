BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT
);
CREATE TABLE IF NOT EXISTS "Items" (
	"_ID"	INTEGER NOT NULL,
	"paintingName"	TEXT,
	"description"	TEXT,
	"imgLocation"	TEXT,
	"price"	TEXT,
	PRIMARY KEY("_ID" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "locationImages" (
	"_ID"	INTEGER NOT NULL,
	"location"	TEXT,
	PRIMARY KEY("_ID" AUTOINCREMENT)
);
INSERT INTO "android_metadata" VALUES ('en_US');
INSERT INTO "Items" VALUES (1,'Madonna & Child','Beautifully preserved, this small picture epitomises the finest works produced in Bellini’s workshop for private devotion.','R.drawable.madonna','15000');
INSERT INTO "Items" VALUES (2,'Bighorn','Bighorn, from the Quadrupeds Series for Allen and Ginter Cigarettes','R.drawable.bighorn','12000');
INSERT INTO "Items" VALUES (3,'The Creation','No description','R.drawable.creation','20000');
INSERT INTO "Items" VALUES (4,'Design for a Stage','Design for a Stage Set at the Opera, Paris ','R.drawable.design','18000');
INSERT INTO "Items" VALUES (5,'Goyu','Asian Art ca. 1835 ','R.drawable.goyu','21000');
INSERT INTO "locationImages" VALUES (1,'R.drawable.madonna');
INSERT INTO "locationImages" VALUES (2,'R.drawable.bighorn');
INSERT INTO "locationImages" VALUES (3,'R.drawable.greek1');
INSERT INTO "locationImages" VALUES (4,'R.drawable.modern2');
INSERT INTO "locationImages" VALUES (5,'R.drawable.pattern');
COMMIT;
