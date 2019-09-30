import scala.concurrent._
import duration.Duration
import ExecutionContext.Implicits.global



Future("doYourThing")


val futureInt: Future[Int] = Future.successful(10)
val failedFutureInt: Future[Int] = Future.failed(new Exception(""))
def futureInt2: Future[Int] = Future.successful(100)

val f = futureInt2
result{
  futureInt.flatMap {
    i1 =>
      f.map {
        i2 =>
          i1 + i2
      }
  }
}

result(
  (for {
    i1 <- futureInt if i1 < 1000
    i2 <- futureInt2
  } yield {
    i1 + i2
  })
)


























case class User(id: Int)
case class Ad(thing: String)

def getUser(id: Int): Future[User] = {
  Future.successful(User(10))
}

def getAds(): Future[List[Ad]] = {
  Future.successful(Nil)
}

def findBestAdsForUser(user: User, ads: List[Ad]): Future[List[Ad]] =
  Future.successful(Nil)

//Whats wrong with this?
val user = getUser(5)
val ads = getAds()
result(
  for {
    user <- user
    ads <- ads
    bestAds <- findBestAdsForUser(user, ads)
  } yield bestAds
)

def result[A](f: => Future[A]) = Await.ready(f, Duration.Inf)