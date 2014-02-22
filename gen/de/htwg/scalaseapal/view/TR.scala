package de.htwg.scalaseapal.view

import scala.language.implicitConversions
import android.app.{Activity,Dialog}
import android.view.{View,ViewGroup,LayoutInflater}

case class TypedResource[A](id: Int)
case class TypedLayout[A](id: Int)

object TR {
  val `setRoute` = TypedResource[android.widget.ImageButton](R.id.`setRoute`)
  val `menuTitleLabel` = TypedResource[android.widget.TextView](R.id.`menuTitleLabel`)
  val `aimLabel` = TypedResource[android.widget.TextView](R.id.`aimLabel`)
  val `calcDistance` = TypedResource[android.widget.ImageButton](R.id.`calcDistance`)
  val `makeFinish` = TypedResource[android.widget.ImageButton](R.id.`makeFinish`)
  val `routeLabel` = TypedResource[android.widget.TextView](R.id.`routeLabel`)
  val `markImage` = TypedResource[android.widget.ImageView](R.id.`markImage`)
  val `deleteImage` = TypedResource[android.widget.ImageView](R.id.`deleteImage`)
  val `deleteLabel` = TypedResource[android.widget.TextView](R.id.`deleteLabel`)
  val `setMark` = TypedResource[android.widget.ImageButton](R.id.`setMark`)
  val `menuTitle` = TypedResource[android.widget.ImageView](R.id.`menuTitle`)
  val `distanceImage` = TypedResource[android.widget.ImageView](R.id.`distanceImage`)
  val `routeImage` = TypedResource[android.widget.ImageView](R.id.`routeImage`)
  val `delete` = TypedResource[android.widget.ImageButton](R.id.`delete`)
  val `aimImage` = TypedResource[android.widget.ImageView](R.id.`aimImage`)
  val `markLabel` = TypedResource[android.widget.TextView](R.id.`markLabel`)
  val `distanceLabel` = TypedResource[android.widget.TextView](R.id.`distanceLabel`)

  object layout {
    val `map_menu` = TypedLayout[android.widget.RelativeLayout](R.layout.`map_menu`)
    val `map_menu_title` = TypedLayout[android.widget.RelativeLayout](R.layout.`map_menu_title`)
    val `activity_main` = TypedLayout[android.widget.RelativeLayout](R.layout.`activity_main`)
  }
}

trait TypedViewHolder {
  def findViewById(id: Int): View
  def findView[A](tr: TypedResource[A]): A =
    findViewById(tr.id).asInstanceOf[A]
}

class TypedLayoutInflater(l: LayoutInflater) {
  def inflate[A](tl: TypedLayout[A], c: ViewGroup, b: Boolean) =
    l.inflate(tl.id, c, b).asInstanceOf[A]
  def inflate[A](tl: TypedLayout[A], c: ViewGroup) =
    l.inflate(tl.id, c).asInstanceOf[A]
}

object TypedResource {
  implicit def viewToTyped(v: View) = new TypedViewHolder {
    def findViewById(id: Int) = v.findViewById(id)
  }
  implicit def activityToTyped(a: Activity) = new TypedViewHolder {
    def findViewById(id: Int) = a.findViewById(id)
  }
  implicit def dialogToTyped(d: Dialog) = new TypedViewHolder {
    def findViewById(id: Int) = d.findViewById(id)
  }
  implicit def layoutInflaterToTyped(l: LayoutInflater) =
    new TypedLayoutInflater(l)
}
