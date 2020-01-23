import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

    case class Wyslij(seq: Seq[Int],wykonawca: ActorRef)
    case class Sprawdz(seq: Seq[Int])

class Nadzorca extends Actor {
    def receive: Receive = {
        case Wyslij(seq,wykonawca) => 
        wykonawca ! Sprawdz(seq)
    }
}

class Wykonawca extends Actor {
    def receive: Receive = {
    case Sprawdz(seq) =>
        for(i <- seq) {
            if(i % 2 == 0) println(i + " parzyste")
            else println(i + " nieparzyste")
        }
    }
}

object Main extends App {
val system = ActorSystem("sys")
val nadzorca = system.actorOf(Props[Nadzorca],"nadzorca")
val wykonawca1 = system.actorOf(Props[Wykonawca],"wykonawca1")
val wykonawca2 = system.actorOf(Props[Wykonawca],"wykonawca2")
nadzorca ! Wyslij(Seq(1,2,3,4,6),wykonawca1)
nadzorca ! Wyslij(Seq(16,19,29,20),wykonawca2)
}