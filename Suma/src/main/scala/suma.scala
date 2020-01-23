import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import scala.io.StdIn

    case class Wynik(liczba1: Double,liczba2: Double)
    case object Zmien

class Pracownik extends Actor {
    def receive: Receive = {
        case Wynik(liczba1,liczba2) =>
        println(liczba1 + liczba2)

        case Zmien => 
        context.become(iloczyn)

    //    if
    //    context.become(iloczyn(liczba1,liczba2))
    }

    def iloczyn: Receive = {        
        case Wynik(liczba1,liczba2) =>
        println(liczba1 * liczba2)
        
        case Zmien =>
        context.become(receive)
    }
}



object Main extends App {
val system = ActorSystem("sys")
val pracownik = system.actorOf(Props[Pracownik],"pracownik")
pracownik ! Wynik(5,8)
pracownik ! Zmien
pracownik ! Wynik(5,8)
pracownik ! Zmien
pracownik ! Wynik(5,8)
}