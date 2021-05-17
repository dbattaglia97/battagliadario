/*
package it.unibo.wenv;
import it.unibo.annotations.ArilRobotSpec;
import it.unibo.annotations.IssProtocolSpec;
import it.unibo.consolegui.ConsoleGui;
import it.unibo.interaction.IssOperations;
import it.unibo.supports.IssCommSupport;
import it.unibo.supports.RobotApplicationStarter;

//@ArilRobotSpec
@IssProtocolSpec( configFile ="WebsocketBasicConfig.txt" )
public class ResumableBoundaryWalker {
    private RobotInputController controller;

    //Constructor
    public ResumableBoundaryWalker(IssOperations rs){
        IssCommSupport rsComm = (IssCommSupport)rs;
        controller = new RobotInputController(rsComm, true, true );
        rsComm.registerObserver( controller );
        System.out.println("ResumableBoundaryWalker | CREATED with rsComm=" + rsComm);
    }


    public RobotInputController getController(){
        return this.controller;
    }

    public static void main(String args[]){
        try {
            System.out.println("ResumableBoundaryWalker | main start n_Threads=" + Thread.activeCount());
            //crea istanza classe
            Object appl = RobotApplicationStarter.createInstance(ResumableBoundaryWalker.class);
            System.out.println("ResumableBoundaryWalker  | appl n_Threads=" + Thread.activeCount());

            //esegue l'applicazione
            new ConsoleGui(((ResumableBoundaryWalker)appl).getController());

            //System.out.println("ResumableBoundaryWalker | trip="   );
            //System.out.println( trip  );
            System.out.println("ResumableBoundaryWalker | main end n_Threads=" + Thread.activeCount());
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}*/
/*
===============================================================
ResumableBoundaryWalker.java
Use the aril language and the support specified in the
configuration file IssProtocolConfig.txt
The business logic is defined in RobotControllerArilBoundary
that is 'message-driven'
===============================================================
*/
package it.unibo.wenv;
import it.unibo.annotations.ArilRobotSpec;
import it.unibo.consolegui.ConsoleGui;
import it.unibo.interaction.IssOperations;
import it.unibo.supports.IssCommSupport;
import it.unibo.supports.RobotApplicationStarter;

@ArilRobotSpec
public class ResumableBoundaryWalker {
    private RobotInputController controller;

    public ResumableBoundaryWalker(IssOperations rs){
        IssCommSupport rsComm = (IssCommSupport)rs;
        controller = new RobotInputController(rsComm, true, true );
        rsComm.registerObserver( controller );
        System.out.println("ResumableBoundaryWalker | CREATED with rsComm=" + rsComm);
    }


    public RobotInputController getController(){
        return this.controller;
    }

    public static void main(String args[]){
        try {
            System.out.println("ResumableBoundaryWalker | main start n_Threads=" + Thread.activeCount());
            //crea istanza classe
            Object appl = RobotApplicationStarter.createInstance(ResumableBoundaryWalker.class);
            System.out.println("ResumableBoundaryWalker  | appl n_Threads=" + Thread.activeCount());

            //esegue l'applicazione
            new ConsoleGui(((ResumableBoundaryWalker)appl).getController());
            //System.out.println("ResumableBoundaryWalker | trip="   );
            //System.out.println( trip  );
            //System.out.println("ResumableBoundaryWalker | main end n_Threads=" + Thread.activeCount());
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}