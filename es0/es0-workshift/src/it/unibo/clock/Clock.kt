/* Generated by AN DISI Unibo */ 
package it.unibo.clock

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Clock ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "start"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("start") { //this:State
					action { //it:State
						println("Orologio partito!")
					}
					 transition( edgeName="goto",targetState="morning", cond=doswitch() )
				}	 
				state("morning") { //this:State
					action { //it:State
						emit("switch", "switch(morning)" ) 
						delay(30000) 
					}
					 transition( edgeName="goto",targetState="afternoon", cond=doswitch() )
				}	 
				state("afternoon") { //this:State
					action { //it:State
						emit("switch", "switch(afternoon)" ) 
						delay(30000) 
					}
					 transition( edgeName="goto",targetState="night", cond=doswitch() )
				}	 
				state("night") { //this:State
					action { //it:State
						emit("switch", "switch(night)" ) 
						delay(30000) 
					}
					 transition( edgeName="goto",targetState="morning", cond=doswitch() )
				}	 
			}
		}
}