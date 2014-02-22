package de.htwg.scalaseapal.utils

import com.google.android.gms.maps.model.LatLng
import java.util
import android.util.Log

/**
 * Created by AK on 17.12.13.
 */
object Position {
  var position: LatLng = new LatLng(-33.867, 151.206)
}

trait PoorMansMapsScalaApi {

  implicit class ToJava(list: List[LatLng]) {
    def toJavaList = {
      var javaList: java.util.List[LatLng] = new util.ArrayList[LatLng]()
      for (latLng <- list) {
        javaList.add(latLng)
      }
      javaList
    }
  }

  implicit class ToScala(list: java.util.List[LatLng]) {
    def toScalaList = {
      var scalaList: List[LatLng] = List[LatLng]()
      for(i <- 0 to list.size()-1) {
        scalaList ::= list.get(i)
      }
      scalaList.reverse
    }
  }
}
