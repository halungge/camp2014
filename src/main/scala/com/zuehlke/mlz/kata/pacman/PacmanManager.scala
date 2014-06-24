package com.zuehlke.mlz.kata.pacman

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging
import akka.actor.ActorRef
import scala.concurrent.duration._
import scala.actors.threadpool.Executors.RunnableAdapter
import sun.security.provider.certpath.ForwardBuilder




object PacmanManager{
  def props(uiActor:ActorRef):Props = Props(new PacmanManager(uiActor))
  
  /**
   * Protocol
   */
  //incoming: corresponds to public interface!
  case object StartUp
  case object MoveRight
  case object MoveLeft
  case object MoveUp
  case object MoveDown
  
  //outgoing
  case class SetupField(size:Int)
  case object Tick
}

class PacmanManager(uiActor: ActorRef) extends Actor with ActorLogging{
  import PacmanManager._
  
  val scheduler = context.system.scheduler
  
  import scala.concurrent.ExecutionContext.Implicits.global
  scheduler.schedule(Duration.Zero, 2.seconds, new Runnable(){
    override def run(){
      self!Tick
    }
  } )
  
  val staticField = context.system.actorOf(StaticField.props)
  
  
  val field = context.system.actorOf(StaticField.props)
  val pacman = context.system.actorOf(Pacman.props(field))
  
  def receive: Receive = {
    case StartUp => {
      staticField.tell(SetupField(10), sender)
      
    }
    case Tick => {
      pacman ! Tick
    }
    case Pacman.Draw(who, where) => {
      uiActor ! Pacman.Draw
    }
    case MoveRight => {
       pacman ! Pacman.UpdateDirection((1,0))
    }
    case MoveLeft => {
       pacman ! Pacman.UpdateDirection((-1,0))
    }
     case MoveUp => {
       pacman ! Pacman.UpdateDirection((0,-1))
    }
    case MoveDown => {
       pacman ! Pacman.UpdateDirection((0,1))
    }
    
    
   
  }
}