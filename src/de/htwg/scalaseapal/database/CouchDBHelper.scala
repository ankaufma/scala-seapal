package de.htwg.scalaseapal.database

import org.ektorp.impl.StdCouchDbInstance
import org.ektorp.CouchDbConnector
import com.couchbase.cblite.CBLServer
import com.couchbase.cblite.CBLDatabase
import android.content.Context
import org.ektorp.http.HttpClient
import org.ektorp.ReplicationCommand
import com.couchbase.cblite.ektorp.CBLiteHttpClient
import android.util.Log

class CouchDBHelper(val dbName: String) {
  val TAG = "CouchDB"
  var dbInstance: StdCouchDbInstance = _
  var couchDBConnector: CouchDbConnector = _
  var tdDB: CBLDatabase = _

  def createDatabase(context: Context): Unit = {
    if (couchDBConnector != null) {
      return
    }

    Log.d("CBHelper", "Pfad: "+context.toString)
    Log.d("CBHelper", "Pfad: "+context.getFilesDir.toString)
    Log.d("CBHelper", "Pfad: "+context.getFilesDir.getAbsolutePath)
    val filesDir = context.getFilesDir.getAbsolutePath
    val server = new CBLServer(filesDir)
    val httpClient: HttpClient  = new CBLiteHttpClient(server)
    dbInstance = new StdCouchDbInstance(httpClient)
    couchDBConnector = dbInstance.createConnector(dbName, true)

    if(server != null) {
      tdDB = server.getDatabaseNamed(dbName)
    }
  }

  def getCouchDbConnector() = couchDBConnector

  def getTDDatabase() = tdDB

  def pullFromDatabase: Unit = {
    val pullReplicationCommand = new ReplicationCommand.Builder()
      .source("http://roroettg.iriscouch.com/" + dbName)
      .target(dbName).continuous(true).build

    dbInstance.replicate(pullReplicationCommand)
  }

  def pushToDatabase: Unit = {
    val pushReplicationCommand = new ReplicationCommand.Builder()
      .source(dbName)
      .target("http://roroettg.iriscouch.com/" + dbName)
      .continuous(true).build

    dbInstance.replicate(pushReplicationCommand)
  }
}