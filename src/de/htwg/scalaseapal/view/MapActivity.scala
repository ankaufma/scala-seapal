package de.htwg.scalaseapal.view

import com.google.android.gms.maps._
import com.google.android.gms.maps.model._
import android.support.v4.app.FragmentActivity
import android.app.DialogFragment
import com.google.android.gms.maps.GoogleMap.{OnMarkerClickListener, OnMapLongClickListener, OnMapClickListener}
import android.widget.Toast
import android.graphics.Color
import de.htwg.scalaseapal.utils.{ContextHolder, PoorMansMapsScalaApi, Position}
import org.scaloid.common._

/**
 * Created by AK on 12.12.13.
 */
class MapActivity extends FragmentActivity with OnMapClickListener with OnMapLongClickListener
  with PoorMansMapsScalaApi with OnMarkerClickListener with MapDialogListener with SActivity {

 object SelectedOption extends Enumeration {
   type SelectedOption = Value
   val NONE, MARK, ROUTE, DISTANCE, GOAL = Value
  }

  import SelectedOption._

  ContextHolder.myContext = this
  var option: SelectedOption = SelectedOption.NONE
  var crosshairMarker: Marker = null
  var route: Polyline = null
  var calcDistance: Double = 0
  var lastPos: LatLng = null
  var calcDistanceMarker = List[Marker]()
  var routeMarker = List[Marker]()
  var posMarker = List[Marker]()
  var map: GoogleMap = _
  var calcDistanceRoute: Polyline = _
  var calclst: List[LatLng] = List()

  onCreate {
    setContentView(R.layout.map_activity)
    map = getSupportFragmentManager.findFragmentById(R.id.map).asInstanceOf[SupportMapFragment].getMap

    if(map != null) {
      map.setMyLocationEnabled(true)
      map.setOnMapClickListener(this)
      map.setOnMapLongClickListener(this)
      map.setOnMarkerClickListener(this)
    }
  }



  override def onMapClick(ll: LatLng) {
    option match {
      case ROUTE =>
        routeMarker ::= map.addMarker(new MarkerOptions()
        .position(ll)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ann_route)))
        val routelst: List[LatLng]  = route.getPoints().toScalaList :+ ll
        route.setPoints(routelst.toJavaList)
      case DISTANCE =>
        calcDistance += calcDistance(lastPos, ll)
        lastPos = ll
        calcDistanceMarker ::= (map.addMarker(new MarkerOptions().
        position(ll).
        icon(BitmapDescriptorFactory.fromResource(R.drawable.ann_distance))))
        val calclst: List[LatLng]  = calcDistanceRoute.getPoints().toScalaList :+ ll
        calcDistanceRoute.setPoints(calclst.toJavaList)
        Toast.makeText(getApplicationContext(), Math.round(calcDistance) + "KM", Toast.LENGTH_LONG).show()
      case _ =>
    }
  }

  override def onMapLongClick(arg0: LatLng): Unit = {
    option = SelectedOption.NONE
    if(crosshairMarker != null) crosshairMarker.remove
    route = null
    crosshairMarker = map.addMarker(new MarkerOptions()
    .position(arg0)
    .icon(BitmapDescriptorFactory.fromResource(R.drawable.haircross_active))
    .snippet(arg0.toString))
    Position.position = arg0

    crosshairMarker.showInfoWindow
  }

  override def onMarkerClick(arg0: Marker): Boolean = {
    val f: MapDialogFragment = new MapDialogFragment()
    f.show(getFragmentManager(), "dialog")
    false
  }

  override def onDialogSetMarkClick(dialog: DialogFragment): Unit = {
    option = SelectedOption.MARK
    posMarker ::= map.addMarker(new MarkerOptions().position(crosshairMarker.getPosition))
    crosshairMarker.remove
  }

  override def onDialogSetRouteClick(dialog: DialogFragment): Unit = {
    option = SelectedOption.ROUTE
    if(route != null) route = null
    routeMarker ::= map.addMarker(new MarkerOptions().
      position(crosshairMarker.getPosition()).
      icon(BitmapDescriptorFactory.fromResource(R.drawable.ann_route)))
    route = map.addPolyline(new PolylineOptions().
      add(crosshairMarker.getPosition()).
      width(5).color(Color.RED))
    crosshairMarker.remove
  }

  override def onDialogcalcDistanceClick (dialog: DialogFragment): Unit = {
    option = SelectedOption.DISTANCE
    if(calcDistanceRoute != null) {
      calcDistanceRoute.remove
      calcDistanceMarker.foreach(_.remove)
    }
    lastPos = crosshairMarker.getPosition
    calcDistance = 0.0
    calcDistanceMarker ::= (map.addMarker(new MarkerOptions().
      position(crosshairMarker.getPosition()).
      icon(BitmapDescriptorFactory.fromResource(R.drawable.ann_distance))))
    val ORANGE = "#FFBB0300"
    calcDistanceRoute = map.addPolyline(new PolylineOptions().add(lastPos).width(5).color(Color.parseColor(ORANGE)))
    crosshairMarker.remove
  }

  override def onDialogSetTargetClick (dialog: DialogFragment): Unit = {
    option = SelectedOption.GOAL
  }

  override def onDialogDeleteClick (dialog: DialogFragment): Unit = {
    option = SelectedOption.NONE
    calclst = List()
    if(route != null) route.remove
    if(calcDistanceRoute != null) calcDistanceRoute.remove
    routeMarker.foreach(_.remove)
    calcDistanceMarker.foreach(_.remove)
    posMarker.foreach(_.remove)
    crosshairMarker.remove
  }

  def calcDistance(lastPos: LatLng, actualPos: LatLng): Double = {
    val R = 6371 // km earth radius
    val dLat = Math.toRadians(actualPos.latitude - lastPos.latitude)
    val dLon = Math.toRadians(actualPos.longitude - lastPos.longitude)
    val lat1 = Math.toRadians(lastPos.latitude)
    val lat2 = Math.toRadians(actualPos.latitude)

    val a = Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))
    R * c
  }



}
