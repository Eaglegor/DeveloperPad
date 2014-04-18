
-- -----------------------------------------------------
-- Table `TaskTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TaskTypes` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `title` TEXT NOT NULL)
;

CREATE UNIQUE INDEX `TaskTypes_title_UNIQUE` ON `TaskTypes` (`title` ASC);


-- -----------------------------------------------------
-- Table `Statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Statuses` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `title` TEXT NOT NULL)
;

CREATE UNIQUE INDEX `Statuses_title_UNIQUE` ON `Statuses` (`title` ASC);


-- -----------------------------------------------------
-- Table `Priorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Priorities` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `value` INTEGER NOT NULL,
  `title` TEXT NOT NULL)
;

CREATE UNIQUE INDEX `Priorities_value_UNIQUE` ON `Priorities` (`value` ASC);

CREATE UNIQUE INDEX `Priorities_title_UNIQUE` ON `Priorities` (`title` ASC);


-- -----------------------------------------------------
-- Table `Tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tasks` (
  `_id` INTEGER NOT NULL,
  `title` TEXT NOT NULL,
  `type` INTEGER NOT NULL,
  `status` INTEGER NOT NULL,
  `parent` INTEGER NULL DEFAULT NULL,
  `deadline` INTEGER NULL DEFAULT NULL,
  `priority` INTEGER NOT NULL,
  `assignee` TEXT NULL DEFAULT NULL,
  `estimate` INTEGER NULL DEFAULT NULL,

  CONSTRAINT `Task_Type`
    FOREIGN KEY (`type`)
    REFERENCES `TaskTypes` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Task_Status`
    FOREIGN KEY (`status`)
    REFERENCES `Statuses` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Task_Parent`
    FOREIGN KEY (`parent`)
    REFERENCES `Tasks` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Task_Priority`
    FOREIGN KEY (`priority`)
    REFERENCES `Priorities` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX `Task_Type_idx` ON `Tasks` (`type` ASC);

CREATE INDEX `Task_Status_idx` ON `Tasks` (`status` ASC);

CREATE INDEX `Task_Parent_idx` ON `Tasks` (`parent` ASC);

CREATE INDEX `Task_Priority_idx` ON `Tasks` (`priority` ASC);


-- -----------------------------------------------------
-- Table `ChangeLogTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChangeLogTypes` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `title` TEXT NOT NULL,
  `classname` TEXT NOT NULL)
;

CREATE UNIQUE INDEX `ChangeLogTypes_title_UNIQUE` ON `ChangeLogTypes` (`title` ASC);

CREATE UNIQUE INDEX `ChangeLogTypes_classname_UNIQUE` ON `ChangeLogTypes` (`classname` ASC);


-- -----------------------------------------------------
-- Table `ChangeLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChangeLog` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `task` INTEGER NOT NULL,
  `type` INTEGER NOT NULL,
  `related_resource` INTEGER NULL,
  `text` TEXT NOT NULL,

  CONSTRAINT `ChangeLog_Type`
    FOREIGN KEY (`type`)
    REFERENCES `ChangeLogTypes` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ChangeLog_Task`
    FOREIGN KEY (`task`)
    REFERENCES `Tasks` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX `ChangeLog_Type_idx` ON `ChangeLog` (`type` ASC);

CREATE INDEX `ChangeLog_Task_idx` ON `ChangeLog` (`task` ASC);


-- -----------------------------------------------------
-- Table `WorkLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WorkLog` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `comment` TEXT NULL,
  `spent_time` INTEGER NOT NULL DEFAULT 0,
  `task` INTEGER NULL,

  CONSTRAINT `WorkLog_Task`
    FOREIGN KEY (`task`)
    REFERENCES `Tasks` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX `WorkLog_Task_idx` ON `WorkLog` (`task` ASC);


-- -----------------------------------------------------
-- Table `ResourceTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResourceTypes` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `title` INTEGER NOT NULL,
  `classname` TEXT NOT NULL)
;

CREATE UNIQUE INDEX `ResourceTypes_classname_UNIQUE` ON `ResourceTypes` (`classname` ASC);

CREATE UNIQUE INDEX `ResourceTypes_title_UNIQUE` ON `ResourceTypes` (`title` ASC);


-- -----------------------------------------------------
-- Table `Resources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Resources` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `type` INTEGER NOT NULL,
  `title` INTEGER NOT NULL,
  `uri` TEXT NOT NULL,

  CONSTRAINT `Resource_Type`
    FOREIGN KEY (`type`)
    REFERENCES `ResourceTypes` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE UNIQUE INDEX `Resources_title_UNIQUE` ON `Resources` (`title` ASC);

CREATE UNIQUE INDEX `Resources_uri_UNIQUE` ON `Resources` (`uri` ASC);

CREATE INDEX `Resource_Type_idx` ON `Resources` (`type` ASC);


-- -----------------------------------------------------
-- Table `TaskResources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TaskResources` (
  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `task` INTEGER NOT NULL,
  `resource` INTEGER NOT NULL,

  CONSTRAINT `TaskResource_Task`
    FOREIGN KEY (`task`)
    REFERENCES `Tasks` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TaskResource_Resource`
    FOREIGN KEY (`resource`)
    REFERENCES `Resources` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX `TaskResource_Task_idx` ON `TaskResources` (`task` ASC);

CREATE INDEX `TaskResource_Resource_idx` ON `TaskResources` (`resource` ASC);

CREATE UNIQUE INDEX `TaskResourcesTask_Resource_unique` ON `TaskResources` (`task` ASC, `resource` ASC);

