import de.htwg.scalaseapal.controller.TBoatController
import de.htwg.scalaseapal.database.TBoatDatabase
import de.htwg.scalaseapal.model.{Route, BoatModel}
import org.scalatest.FlatSpec

/**
 * Created by AK on 21.02.14.
 */

class TestController extends FlatSpec {
  object MockInjection extends TBoatController with TBoatDatabase {
    override val boatDatabase = new DatabaseMock()
  }
  val boatController = MockInjection.boatController
  val emptyBoat = new BoatModel()

  "The isNotEmpty method" should "return false if input type isn't a String" in {
    assert (!boatController.isNotEmpty(null))
    assert (!boatController.isNotEmpty(0))
    assert (!boatController.isNotEmpty(new Route()))
    assert (!boatController.isNotEmpty(200.40))
  }

  "The isNotEmpty method" should "return false if a String is empty" in {
    assert (!boatController.isNotEmpty(""))
  }

  "The isNotEmpty method" should "return true on a regular String input" in {
    assert (boatController.isNotEmpty("abc"))
  }

  "The makeFlatStringAttList" should "return a list of size 24 when a BoatModel is inserted" in {
    assert (boatController.makeFlatStringAttList(emptyBoat.productIterator.toList).size === 24)
  }

  "The makeFlatStringAttList" should "return a List of 6 Strings when input a List of 2 Tuple with 3 Double and 3 String" in {
    val listTest = List((0.0,4.4,3.3),("Hi","Ha","Ho"))
    assert (boatController.makeFlatStringAttList(listTest)(0) === "0.0")
    assert (boatController.makeFlatStringAttList(listTest)(1) === "4.4")
    assert (boatController.makeFlatStringAttList(listTest)(2) === "3.3")
    assert (boatController.makeFlatStringAttList(listTest)(3) === "Hi")
    assert (boatController.makeFlatStringAttList(listTest)(4) === "Ha")
    assert (boatController.makeFlatStringAttList(listTest)(5) === "Ho")
  }
}
