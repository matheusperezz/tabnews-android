package com.miwis.tabnewskt.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL(
      "CREATE TABLE IF NOT EXISTS `PostDetails` ( " +
          "`id` TEXT NOT NULL, " +
          "`owner_id` TEXT, " +
          "`parend_id` TEXT, " +
          "`slug` TEXT NOT NULL, " +
          "`title` TEXT, " +
          "`status` TEXT, " +
          "`body` TEXT NOT NULL, " +
          "`source_url` TEXT, " +
          "`created_at` TEXT NOT NULL, " +
          "`updated_at` TEXT, " +
          "`published_at` TEXT, " +
          "`deleted_at` TEXT, " +
          "`tabcoins` INTEGER NOT NULL, " +
          "`owner_username` TEXT NOT NULL, " +
          "`children_deep_count` INTEGER, " +
          "PRIMARY KEY(`id`))"
    )
  }
}