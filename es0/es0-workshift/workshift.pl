%====================================================================================
% workshift description   
%====================================================================================
context(ctxworkshift, "localhost",  "TCP", "8095").
 qactor( workshiftmanager, ctxworkshift, "it.unibo.workshiftmanager.Workshiftmanager").
  qactor( clock, ctxworkshift, "it.unibo.clock.Clock").
  qactor( sender, ctxworkshift, "it.unibo.sender.Sender").
