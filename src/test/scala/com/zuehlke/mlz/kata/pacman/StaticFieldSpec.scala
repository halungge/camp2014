package com.zuehlke.mlz.kata.pacman

import org.scalatest.WordSpecLike
import akka.testkit.ImplicitSender
import org.scalatest.Matchers
import akka.testkit.TestKit
import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.actor.ActorRef
import com.zuehlke.mlz.kata.pacman.model.Position

class StaticFieldSpec  extends TestKit(ActorSystem("StaticField-test"))
  with WordSpecLike
  with ImplicitSender
  with Matchers {
  
  
  
  "a StaticField " should {
    
    "calculate position in middle of the field correctly" in {
       val staticField = TestActorRef(new StaticField).underlyingActor
      staticField.size = 10
      staticField.calculatePosition(Position(4, 4), (0,1)) shouldEqual Position(4,5);
      staticField.calculatePosition(Position(4, 4), (0,-1)) shouldEqual Position(4,3);
      staticField.calculatePosition(Position(4, 4), (1,0)) shouldEqual Position(5,4);
      staticField.calculatePosition(Position(4, 4), (-1,0)) shouldEqual Position(3,4);
      
    }
    
    "calculate fold correctly in +y direction" in {
      val staticField = TestActorRef(new StaticField).underlyingActor
      staticField.size = 10
      staticField.calculatePosition(Position(8, 9), (0,1)) shouldEqual Position(8,0);
    }
     "calculate fold correctly in -y direction" in {
      val staticField = TestActorRef(new StaticField).underlyingActor
      staticField.size = 10
      staticField.calculatePosition(Position(8, 0), (0,-1)) shouldEqual Position(8,9);
    }
     "calculate fold correctly in -x direction" in {
      val staticField = TestActorRef(new StaticField).underlyingActor
      staticField.size = 10
      staticField.calculatePosition(Position(0, 7), (-1,0)) shouldEqual Position(9,7);
    }
      "calculate fold correctly in +x direction" in {
      val staticField = TestActorRef(new StaticField).underlyingActor
      staticField.size = 10
      staticField.calculatePosition(Position(9, 7), (1,0)) shouldEqual Position(0,7);
    }
      
  }
  
    

}