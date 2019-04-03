package controllers.pets

import connectors.pets.{PetConnector, PetNotFoundException}
import utils.Logger

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait PetController {

  val connector: PetConnector
  val applyDiscount: Boolean

  def getPriceOfPet(id: String): Future[Double] = {
    connector.getPet(id).flatMap {
      pet =>
        connector.getPrice(pet).map{
          price =>
            if(applyDiscount) {
              price * 0.9
            } else price
        }
    } recover {
      case _: PetNotFoundException =>
        0
      case e: Exception =>
        throw e
    }
  }


  def getPriceOfPetWithFor(id: String): Future[Double] = {
    for {
      pet <- connector.getPet(id)
      price <- connector.getPrice(pet)
    } yield {
      if(applyDiscount) price * 0.9
      else price
    }
  } recover {
    case _: PetNotFoundException =>
      0
    case e: Exception =>
      Logger.error("An error occured", e)
      throw e
  }

}
