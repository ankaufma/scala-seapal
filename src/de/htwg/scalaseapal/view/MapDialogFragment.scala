package de.htwg.scalaseapal.view

/**
 * Created by AK on 02.02.14.
 */
import java.text.DecimalFormat

import com.google.android.gms.maps.model.LatLng

import android.app.{Dialog, Activity, AlertDialog, DialogFragment}
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import de.htwg.scalaseapal.utils.{ContextHolder, Position}
import android.location.Geocoder
import org.scaloid.common._


trait MapDialogListener {
  def onDialogSetMarkClick(dialog: DialogFragment)
  def onDialogSetRouteClick(dialog: DialogFragment)
  def onDialogcalcDistanceClick(dialog: DialogFragment)
  def onDialogSetTargetClick(dialog: DialogFragment)
  def onDialogDeleteClick(dialog: DialogFragment)
}

class MapDialogFragment extends DialogFragment {

  implicit val context = ContextHolder.myContext
  var mListener: MapDialogListener = _

  override def onAttach(activity: Activity): Unit = {
    super.onAttach(activity)
    mListener = activity.asInstanceOf[MapDialogListener]
  }


  override def onCreateDialog(savedInstanceState: Bundle): Dialog = {

    val builder: AlertDialog.Builder = new AlertDialog.Builder(getActivity())
    val inflater: LayoutInflater = getActivity().getLayoutInflater()
    val dialogView: View = inflater.inflate(R.layout.map_menu, null)
    val titleView: View = inflater.inflate(R.layout.map_menu_title, null)

    val setMark: ImageButton= dialogView.findViewById(R.id.setMark).asInstanceOf[ImageButton]
    val setRoute: ImageButton = dialogView.findViewById(R.id.setRoute).asInstanceOf[ImageButton]
    val calcDistance: ImageButton = dialogView.findViewById(R.id.calcDistance).asInstanceOf[ImageButton]
    val setTarget: ImageButton = dialogView.findViewById(R.id.makeFinish).asInstanceOf[ImageButton]
    val delete: ImageButton = dialogView.findViewById(R.id.delete).asInstanceOf[ImageButton]

    val t: TextView = titleView.findViewById(R.id.menuTitleLabel).asInstanceOf[TextView]

    val pos: LatLng = Position.position
    try {
    val geocoder = new Geocoder(ContextHolder.myContext)
    val adress =  geocoder.getFromLocation(pos.latitude, pos.longitude, 1)
    if(adress.size > 0) {
      val country = if(adress.get(0).getAddressLine(2) != null) adress.get(0).getAddressLine(2) else "Unknown"
      val city = if(adress.get(0).getAddressLine(1) != null) adress.get(0).getAddressLine(1) else "Unknown"
      t.setText("You are in "+country+ " in a City named " +city)
    } else {
      val lat: String = formatLatitude(pos.latitude);
      val lng: String = formatLongitude(pos.longitude);
      t.setText(lat + "       " +  lng);
    }
    } catch {
      case np: NullPointerException => toast("Error during establishing Internet Connection. Wait a few Seconds"); dismiss
      case _ => toast("Error during establishing Internet Connection. Wait a few Seconds"); dismiss
    }
    builder.setCustomTitle(titleView).setView(dialogView)

    setMark.setOnClickListener(new View.OnClickListener() {

      override def onClick(v: View) {
        dismiss()
        mListener.onDialogSetMarkClick(MapDialogFragment.this)
      }
    })

    setRoute.setOnClickListener(new View.OnClickListener() {

      override def onClick(v: View) {
        dismiss()
        mListener.onDialogSetRouteClick(MapDialogFragment.this)
      }
    })

    calcDistance.setOnClickListener(new View.OnClickListener() {

      override def onClick(v: View) {
        dismiss()
        mListener.onDialogcalcDistanceClick(MapDialogFragment.this)
      }
    })

    setTarget.setOnClickListener(new View.OnClickListener() {

      override def onClick(v: View) {
        dismiss()
        mListener.onDialogSetTargetClick(MapDialogFragment.this)
      }
    })

    delete.setOnClickListener(new View.OnClickListener() {

      override def onClick(v: View) {
        dismiss()
        mListener.onDialogDeleteClick(MapDialogFragment.this)
      }
    })


    return builder.create()
  }

  def formatLongitude(lng: Double): String = {
    var orientation: String = ""
    val df: DecimalFormat = new DecimalFormat("#0")
    if (lng >= 0)
      orientation = "E"
    else
      orientation = "W"
    var lngAbs = Math.abs(lng)
    val degrees: String = df.format(lng)
    lngAbs -= degrees.toDouble
    val minutes: String = df.format(Math.abs(((lngAbs * 60) % 60)))
    degrees + "°" + minutes + "'" + orientation
  }

  def formatLatitude(lat: Double): String = {
    var orientation: String = ""
    val df: DecimalFormat = new DecimalFormat("#0")
    if (lat >= 0)
      orientation = "N"
    else
      orientation = "S"
    var latAbs = Math.abs(lat)
    val degrees: String = df.format(lat)
    val minutes: String = df.format(Math.abs(((latAbs * 60) % 60)))
    degrees + "°" + minutes + "'" + orientation

  }
}
