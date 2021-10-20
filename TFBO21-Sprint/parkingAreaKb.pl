:- dynamic(slotFree/1).
:- dynamic(trolleyStatus/1).
:-dynamic(indoorfree/0).
:-dynamic(outdoorfree/0).
:-dynamic(token/1).


slotFree(1).
slotFree(2).
slotFree(3).
slotFree(4).
slotFree(5).
slotFree(6).

trolleyStatus(idle).

changeTrolleyStatus(working):- replaceRule(trolleyStatus(idle),trolleyStatus(working)),!.
changeTrolleyStatus(working):- replaceRule(trolleyStatus(stopped),trolleyStatus(working)).

changeTrolleyStatus(idle):-  replaceRule(trolleyStatus(working),trolleyStatus(idle)),!.
changeTrolleyStatus(idle):-  replaceRule(trolleyStatus(stopped),trolleyStatus(idle)).

changeTrolleyStatus(stopped):- replaceRule(trolleyStatus(working),trolleyStatus(stopped)),!.
changeTrolleyStatus(stopped):- replaceRule(trolleyStatus(idle),trolleyStatus(stopped)).


acceptIN :- indoorfree,parcheggioDisponibile,trolleyStatus(idle),!.
acceptIN :- indoorfree,parcheggioDisponibile,trolleyStatus(working).

acceptOUT:-outdoorfree,trolleyStatus(idle),!.
acceptOUT:-outdoorfree,trolleyStatus(working).

indoorfree.

outdoorfree.

parcheggioDisponibile :- slotFree(1),!.
parcheggioDisponibile :- slotFree(2),!.
parcheggioDisponibile :- slotFree(3),!.
parcheggioDisponibile :- slotFree(4),!.
parcheggioDisponibile :- slotFree(5),!.
parcheggioDisponibile :- slotFree(6),!.

indoorOccupata :- retract(indoorfree).

outdoorOccupata :- retract(outdoorfree).

indoorLiberata :- assert(indoorfree).

outdoorLiberata :- assert(outdoorfree).

occupaSlot(N):- retract(slotFree(N)),!.

liberaSlot(N):- assert(slotFree(N)),!.

addToken(T) :- assert(token(T)),!.

removeToken(T) :- retract(token(T)).
