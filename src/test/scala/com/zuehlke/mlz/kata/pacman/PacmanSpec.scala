package com.zuehlke.mlz.kata.pacman

import akka.testkit.TestKit
import org.scalatest.Matchers
import akka.testkit.ImplicitSender
import akka.actor.ActorSystem
import org.scalatest.WordSpecLike
import akka.testkit.TestActorRef

class PacmanSpec extends TestKit(ActorSystem("Pacman-test"))
  with WordSpecLike
  with ImplicitSender
  with Matchers {
  
  "Pacman " should {
    "send a Draw command back on every tick"  in {
      
      val pacman = TestActorRef(Pacman.props)
      pacman ! PacmanManager.Tick
      expectMsg(Pacman.Draw("Pacman", (0,1)))
      
    }
  }
    //unit test for state
    "update position on every Tick" in {
      
  }
  
}