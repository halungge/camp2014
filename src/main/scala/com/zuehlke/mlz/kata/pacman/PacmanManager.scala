package com.zuehlke.mlz.kata.pacman

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging
import akka.actor.ActorRef
import scala.concurrent.duration._
import scala.actors.threadpool.Executors.RunnableAdapter




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
  case class Field(val size: Int)
  case object SetupField
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
  
  val fieldGenerator = context.system.actorOf(FieldGenerator.props)
  val pacman = context.system.actorOf(Pacman.props)
  
  def receive: Receive = {
    case StartUp => {
      fieldGenerator.tell(SetupField, sender)
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