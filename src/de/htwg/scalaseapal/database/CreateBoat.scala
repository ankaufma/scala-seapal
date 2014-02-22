package de.htwg.scalaseapal.database


import android.content.Context
import android.database.sqlite._
;
/**
 * Created with IntelliJ IDEA.
 * User: AK
 * Date: 28.11.13
 * Time: 09:57
 * To change this template use File | Settings | File Templates.
 */

class CreateBoat (context: Context, NameOfDB: String, dbCursorFac: SQLiteDatabase.CursorFactory, VersionOfDB: Int)
  extends SQLiteOpenHelper(context, NameOfDB, null, VersionOfDB) {

  def this(context: Context) = this(context, DB_Param.DB_NAME, null, DB_Param.DB_Version)

  val TABLE_BOATS = "Boat"
  val COLUMN_ID = "_id"
  val COLUMN_BOAT = "BoatName"
  val COLUMN_RegisterNr = "RegisterNr"
  val COLUMN_SailSign = "SailSign"
  val COLUMN_HomePort = "HomePort"
  val COLUMN_YachtClub = "YachtClub"
  val COLUMN_Owner = "Owner"
  val COLUMN_Insucrance = "Insurance"
  val COLUMN_CallSign = "CallSign"
  val COLUMN_Type = "Type"
  val COLUMN_Constructor = "Constructor"
  val COLUMN_Length = "Length"
  val COLUMN_Width = "Width"
  val COLUMN_Draft = "Draft"
  val COLUMN_MastHeight = "MastHeight"
  val COLUMN_Displacement = "Displacement"
  val COLUMN_Rigging = "Rigging"
  val COLUMN_YearOfConstruction = "YearOfConstruction"
  val COLUMN_Motor = "Motor"
  val COLUMN_TankSize = "TankSize"
  val COLUMN_WasteWaterTankSize = "WasteWaterTankSize"
  val COLUMN_FreshWaterTankSize = "FreshWaterTankSize"
  val COLUMN_MainSailSize = "MainSailSize"
  val COLUMN_GenuaSize = "GenuaSize"
  val COLUMN_SpiSize = "SpiSize"

  val createTableBoats = "CREATE TABLE " + TABLE_BOATS  +" (" +
    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
    COLUMN_BOAT + " TEXT NOT NULL," +
    COLUMN_RegisterNr + " TEXT NOT NULL," +
    COLUMN_SailSign + " TEXT NOT NULL," +
    COLUMN_HomePort + " TEXT NOT NULL," +
    COLUMN_YachtClub + " TEXT NOT NULL," +
    COLUMN_Owner + " TEXT NOT NULL," +
    COLUMN_Insucrance + " TEXT NOT NULL," +
    COLUMN_CallSign + " TEXT NOT NULL," +
    COLUMN_Type + " TEXT NOT NULL," +
    COLUMN_Constructor  + " TEXT NOT NULL," +
    COLUMN_Length + " REAL NOT NULL," +
    COLUMN_Width + " REAL NOT NULL," +
    COLUMN_Draft + " REAL NOT NULL," +
    COLUMN_MastHeight + " REAL NOT NULL," +
    COLUMN_Displacement + " REAL NOT NULL," +
    COLUMN_Rigging + " TEXT NOT NULL," +
    COLUMN_YearOfConstruction + " INTEGER NOT NULL," +
    COLUMN_Motor + " TEXT NOT NULL," +
    COLUMN_TankSize + " REAL NOT NULL," +
    COLUMN_WasteWaterTankSize + " REAL NOT NULL," +
    COLUMN_FreshWaterTankSize + " REAL NOT NULL," +
    COLUMN_MainSailSize + " REAL NOT NULL," +
    COLUMN_GenuaSize + " REAL NOT NULL," +
    COLUMN_SpiSize + " REAL NOT NULL);"

  override def onCreate (db: SQLiteDatabase) = db.execSQL(createTableBoats)

  override def onUpgrade (db: SQLiteDatabase, oldVerison: Int, newVersion: Int) = {
    db.execSQL("DROP TABLE IF EXISTS " + createTableBoats + ";")
    onCreate(db)
  }
}
