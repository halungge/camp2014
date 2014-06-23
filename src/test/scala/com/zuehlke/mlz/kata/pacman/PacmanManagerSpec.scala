package com.zuehlke.mlz.kata.pacman

import com.zuehlke.mlz.helpers.AkkaTestKitSpec
import akka.actor.Props
import akka.testkit.TestKit
import org.scalatest.Matchers
import akka.testkit.ImplicitSender
import akka.actor.ActorSystem
import org.scalatest.WordSpecLike
import scala.concurrent.duration._
import org.scalatest.BeforeAndAfterAll
import akka.testkit.TestActorRef

class PacmanManagerSpec extends TestKit(ActorSystem("Pacman-test"))
  with Matchers 
  with WordSpecLike
  with BeforeAndAfterAll 
  with ImplicitSender {
  
  

  "PacmanManager" should {
	  val pacmanManager = TestActorRef(PacmanManager.props(testActor))
	      
    "request new Field when receiving StartUp" in {
        pacmanManager ! PacmanManager.StartUp
        expectMsg(PacmanManager.Field)
    }
	  
	 
	  "pass Draw unto the uiManager upon each tick" in {
	    pacmanManager ! PacmanManager.Tick
	    expectMsg(Pacman.Draw("test", (1, 2)))
	  }
	  
  }
  
  
  override def afterAll() = {
     system.shutdown()
  }

  
}