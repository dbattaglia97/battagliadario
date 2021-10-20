/* Generated by AN DISI Unibo */ 
package it.unibo.parkingmanagerservice

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Parkingmanagerservice ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
			var prog= 0
			var SLOTNUM=-1
			var CARSLOTNUM=-1
			var TOKENIN= -1
			var boolIN=false
			var boolOUT=false
			var weightOK=false
			var peso=-1
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						solve("consult('sysRules.pl')","") //set resVar	
						solve("consult('parkingAreaKb.pl')","") //set resVar	
						println("Park System START | SERVICE")
					}
					 transition( edgeName="goto",targetState="checkIndoor", cond=doswitch() )
				}	 
				state("checkIndoor") { //this:State
					action { //it:State
						solve("changeTrolleyStatus(idle)","") //set resVar	
						solve("acceptIN","") //set resVar	
						if( currentSolution.isSuccess() ) {boolIN=true 
						}
						else
						{boolIN=false 
						}
					}
					 transition( edgeName="goto",targetState="checkOutdoor", cond=doswitchGuarded({boolIN==true 
					}) )
					transition( edgeName="goto",targetState="checkOnlyOutdoor", cond=doswitchGuarded({! (boolIN==true 
					) }) )
				}	 
				state("checkOutdoor") { //this:State
					action { //it:State
						solve("acceptOUT","") //set resVar	
						if( currentSolution.isSuccess() ) {boolOUT=true 
						}
						else
						{boolOUT=false 
						}
					}
					 transition( edgeName="goto",targetState="allReady", cond=doswitchGuarded({boolOUT==true 
					}) )
					transition( edgeName="goto",targetState="indoorOnlyReady", cond=doswitchGuarded({! (boolOUT==true 
					) }) )
				}	 
				state("checkOnlyOutdoor") { //this:State
					action { //it:State
						solve("acceptOUT","") //set resVar	
						if( currentSolution.isSuccess() ) {boolOUT=true 
						}
						else
						{boolOUT=false 
						}
					}
					 transition( edgeName="goto",targetState="outdoorOnlyReady", cond=doswitchGuarded({boolOUT==true 
					}) )
					transition( edgeName="goto",targetState="waiting", cond=doswitchGuarded({! (boolOUT==true 
					) }) )
				}	 
				state("indoorOnlyReady") { //this:State
					action { //it:State
						boolOUT=false 
						boolIN=false 
						stateTimer = TimerActor("timer_indoorOnlyReady", 
							scope, context!!, "local_tout_parkingmanagerservice_indoorOnlyReady", 5000.toLong() )
					}
					 transition(edgeName="t044",targetState="moveToHome",cond=whenTimeout("local_tout_parkingmanagerservice_indoorOnlyReady"))   
					transition(edgeName="t045",targetState="acceptin",cond=whenRequest("reqenter"))
				}	 
				state("outdoorOnlyReady") { //this:State
					action { //it:State
						boolIN=false 
						boolOUT=false 
						stateTimer = TimerActor("timer_outdoorOnlyReady", 
							scope, context!!, "local_tout_parkingmanagerservice_outdoorOnlyReady", 5000.toLong() )
					}
					 transition(edgeName="t046",targetState="moveToHome",cond=whenTimeout("local_tout_parkingmanagerservice_outdoorOnlyReady"))   
					transition(edgeName="t047",targetState="handlepickup",cond=whenRequest("pickup"))
				}	 
				state("waiting") { //this:State
					action { //it:State
						stateTimer = TimerActor("timer_waiting", 
							scope, context!!, "local_tout_parkingmanagerservice_waiting", 5000.toLong() )
					}
					 transition(edgeName="t048",targetState="moveToHome",cond=whenTimeout("local_tout_parkingmanagerservice_waiting"))   
				}	 
				state("moveToHome") { //this:State
					action { //it:State
						println("Moving Trolley to HOME")
					}
					 transition( edgeName="goto",targetState="checkIndoor", cond=doswitch() )
				}	 
				state("allReady") { //this:State
					action { //it:State
						solve("changeTrolleyStatus(idle)","") //set resVar	
						println("parkingmanagerservice waiting ...")
						stateTimer = TimerActor("timer_allReady", 
							scope, context!!, "local_tout_parkingmanagerservice_allReady", 5000.toLong() )
					}
					 transition(edgeName="t049",targetState="moveToHome",cond=whenTimeout("local_tout_parkingmanagerservice_allReady"))   
					transition(edgeName="t050",targetState="acceptin",cond=whenRequest("reqenter"))
					transition(edgeName="t051",targetState="handlepickup",cond=whenRequest("pickup"))
				}	 
				state("acceptin") { //this:State
					action { //it:State
						solve("slotFree(S)","") //set resVar	
						 SLOTNUM = getCurSol("S").toString().toInt() 
						println("Reply to reqenter with $SLOTNUM  | SERVICE")
						answer("reqenter", "enter", "enter($SLOTNUM)"   )  
						solve("changeTrolleyStatus(working)","") //set resVar	
						println("Trolley is moving to Indoor")
						solve("indoorOccupata","") //set resVar	
					}
					 transition(edgeName="t052",targetState="carenter",cond=whenRequest("carenter"))
				}	 
				state("carenter") { //this:State
					action { //it:State
						 prog++ 
						println("carindoorarrival emitted in order to be processed by  WEIGHT SENSOR   | SERVICE")
						emit("carindoorarrival", "cia(car_arrived)" ) 
					}
					 transition(edgeName="t053",targetState="weightCheck",cond=whenEvent("weight"))
				}	 
				state("weightCheck") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("weight(W)"), Term.createTerm("weight(W)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 
												peso = payloadArg(0)
												println("Weight: " + peso)
												if(peso>=500){ weightOK=true}
												else{ weightOK=false}
						}
					}
					 transition( edgeName="goto",targetState="moveToSlotIn", cond=doswitchGuarded({weightOK==true 
					}) )
					transition( edgeName="goto",targetState="weightNotOK", cond=doswitchGuarded({! (weightOK==true 
					) }) )
				}	 
				state("weightNotOK") { //this:State
					action { //it:State
						println("There isn't a car in the indoor")
					}
					 transition( edgeName="goto",targetState="moveToHome", cond=doswitch() )
				}	 
				state("moveToSlotIn") { //this:State
					action { //it:State
						println("SLOTNUM IS $SLOTNUM")
						solve("occupaSlot($SLOTNUM)","") //set resVar	
						println("Trolley moves from entrance to slot $SLOTNUM | SERVICE")
						stateTimer = TimerActor("timer_moveToSlotIn", 
							scope, context!!, "local_tout_parkingmanagerservice_moveToSlotIn", 4000.toLong() )
					}
					 transition(edgeName="t054",targetState="receipt",cond=whenTimeout("local_tout_parkingmanagerservice_moveToSlotIn"))   
				}	 
				state("receipt") { //this:State
					action { //it:State
						solve("indoorLiberata","") //set resVar	
						 var TOKENID = "$prog$SLOTNUM" 
						solve("addToken($TOKENID)","") //set resVar	
						println("REPLY TO CARENTER WITH RECEIPT $TOKENID | SERVICE")
						answer("carenter", "receipt", "receipt($TOKENID)"   )  
						updateResourceRep( "{\"receipt\":\"$TOKENID\"}"  
						)
						stateTimer = TimerActor("timer_receipt", 
							scope, context!!, "local_tout_parkingmanagerservice_receipt", 1000.toLong() )
					}
					 transition(edgeName="t055",targetState="checkIndoor",cond=whenTimeout("local_tout_parkingmanagerservice_receipt"))   
				}	 
				state("handlepickup") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("pickup(TOKENID)"), Term.createTerm("pickup(TOKENIN)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 TOKENIN = payloadArg(0).toInt()  
						}
						println("TOKEN FORNITO DAL CLIENT PER IL PICKUP $TOKENIN")
						solve("token($TOKENIN)","") //set resVar	
						if( currentSolution.isSuccess() ) {println("Elaborazione token OK, token= tokenIN| SERVICE")
						answer("pickup", "pickupAccepted", "pickupAccepted($TOKENIN)"   )  
						}
						else
						{println("TOKEN NON PRESENTE")
						}
					}
					 transition( edgeName="goto",targetState="picking", cond=doswitch() )
				}	 
				state("picking") { //this:State
					action { //it:State
						println("Trolley picking car | SERVICE")
						var CARSLOTNUM= TOKENIN%10 
						println("Trolley picking car from slot $CARSLOTNUM result from $TOKENIN % 10 | SERVICE")
						delay(3000) 
						solve("liberaSlot($CARSLOTNUM)","") //set resVar	
						solve("removeToken($TOKENIN)","") //set resVar	
						solve("outdoorOccupata","") //set resVar	
						println("Car is in Outdoor area | SERVICE")
						emit("caroutdoorarrival", "coa(car_outdoor)" ) 
					}
					 transition(edgeName="t056",targetState="withdrawn",cond=whenEvent("carwithdrawn"))
					transition(edgeName="t057",targetState="timeout",cond=whenEvent("timeout"))
				}	 
				state("withdrawn") { //this:State
					action { //it:State
						solve("outdoorLiberata","") //set resVar	
						println("Car withdrawn!")
					}
					 transition( edgeName="goto",targetState="checkIndoor", cond=doswitch() )
				}	 
				state("timeout") { //this:State
					action { //it:State
						println("%%%% TIMEOUT %%%%")
						emit("alarm", "timeout(alarm)" ) 
					}
				}	 
			}
		}
}
