package controllers.userdetails

import connectors.userdetails.UserDetailsConnector

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object UserDetailsController {

  val id: String = "id1"

  val connector: UserDetailsConnector = UserDetailsConnector

  // Return formatted String with "<name> - <job role> - <salary>

  def getUserDetailsWithMap: Future[String] = {
    connector.getName(id).flatMap {
      name =>
        connector.getJobRole(id).flatMap {
          jobRole =>
            connector.getSalary(name).map {
              salary =>
                s"$name - $jobRole - $salary"
            }
        }
    }
  }

  def getUserDetailsWithFor: Future[String] = {
    for {
      name <- connector.getName(id)
      jobRole <- connector.getJobRole(id)
      salary <- connector.getSalary(name)
    } yield s"$name - $jobRole - $salary"
  }
}
