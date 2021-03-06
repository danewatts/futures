package connectors.userdetails

import models.Name

import scala.concurrent.Future

trait UserDetailsConnector {

  def getName(id: String): Future[Name] = {
    Future.successful(Name("Luke", "Skywalker"))
  }

  def getJobRole(id: String): Future[String] = {
    Future.successful("Jedi")
  }

  def getSalary(name: Name): Future[Int] = {
    Future.successful(0)
  }
}

object UserDetailsConnector extends UserDetailsConnector
