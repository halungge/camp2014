package com.zuehlke.mlz.kata.pacman

import akka.testkit.TestKit
import org.scalatest.Matchers
import akka.testkit.ImplicitSender
import akka.actor.ActorSystem
import org.scalatest.WordSpecLike
import akka.testkit.TestActorRef
import com.zuehlke.mlz.kata.pacman.model.Position
import com.zuehlke.mlz.kata.pacman.Pacman.UpdateDirection

class PacmanSpec extends TestKit(ActorSystem("Pacman-test"))
  with WordSpecLike
  with ImplicitSender
  with Matchers {
  
  "Pacman " should {
	  val positionController = TestActorRef(StaticField.props)
	  
    "send a Draw command back on every tick"  in {
		  val pacman = TestActorRef(new Pacman(positionController))
      pacman ! PacmanManager.Tick
      expectMsg(Pacman.Draw("Pacman", Position(1,1)))
    }
  
    "update position state when it receives a Position" in {
       val pacman = TestActorRef(new Pacman(positionController))
       pacman ! Position(0,2)
       pacman.underlyingActor.position shouldEqual Position(0,2)
      
    }
    
    "update direction state when it receives a UpdateDirection" in {
       val pacman = TestActorRef(new Pacman(positionController))
       pacman ! UpdateDirection((1,1))
       pacman.underlyingActor.direction shouldEqual (1,1)
       
      
    }
      
  }
  
}