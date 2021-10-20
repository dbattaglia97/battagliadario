/* Generated by AN DISI Unibo */ 
package it.unibo.outsonar

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Outsonar ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
					}
					 transition( edgeName="goto",targetState="working", cond=doswitch() )
				}	 
				state("working") { //this:State
					action { //it:State
						println("Sonar working")
					}
					 transition(edgeName="t040",targetState="startTimer",cond=whenEvent("caroutdoorarrival"))
				}	 
				state("startTimer") { //this:State
					action { //it:State
						println("Sonar startTimer")
						stateTimer = TimerActor("timer_startTimer", 
							scope, context!!, "local_tout_outsonar_startTimer", 10000.toLong() )
					}
					 transition(edgeName="t041",targetState="timeout",cond=whenTimeout("local_tout_outsonar_startTimer"))   
					transition(edgeName="t042",targetState="working",cond=whenEvent("carwithdrawn"))
				}	 
				state("timeout") { //this:State
					action { //it:State
						println("Sonar timeout")
						emit("timeout", "timeout" ) 
					}
					 transition( edgeName="goto",targetState="working", cond=doswitch() )
				}	 
			}
		}
}
