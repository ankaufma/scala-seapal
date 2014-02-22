package de.htwg.scalaseapal.view

import org.scaloid.common._
import android.view.{View, Gravity}
import android.graphics.Color

/**
 * Created by AK on 11.02.14.
 */
class TestView extends SActivity {
  onCreate {
contentView = new SVerticalLayout() {
  style {
    case b : SButton => b.textSize(25.dip).textColor(Color.BLACK)
    case t : STextView => t.textSize(25.dip).textColor(Color.BLACK)
  }
  this += new SVerticalLayout {
    STextView("Ueberschrift").backgroundColor(Color.CYAN).gravity(Gravity.CENTER)
  }
  this += new SVerticalLayout {
    STextView("Boot1").backgroundColor(Color.YELLOW)
    STextView("Boot2").backgroundColor(Color.YELLOW)
    STextView("Boot3").backgroundColor(Color.YELLOW)
    STextView("Boot4").backgroundColor(Color.YELLOW)
    STextView("Boot5").backgroundColor(Color.YELLOW)
  }
  this += new SVerticalLayout {
    SButton("AdButton").backgroundColor(Color.MAGENTA)
  }
}
  }
}
