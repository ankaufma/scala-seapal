package de.htwg.scalaseapal.model

/**
 * Created by AK on 30.01.14.
 */
case class Person (var firstname: String, var lastname: String, var birth: Long,
                   var registration: Long, var age: Integer, var nationality: String, var email: String,
                   var telephone: String, var mobile: String, var street: String, var postcode: Int,
                   var city: String, var country: String){
  def this() = this("", "", 0L, 0L, 0, "", "", "", "", "", 0, "", "")
}
