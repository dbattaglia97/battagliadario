%====================================================================================
% carparking description   
%====================================================================================
context(ctxcarparking, "localhost",  "TCP", "8092").
 qactor( parkingservicegui1, ctxcarparking, "it.unibo.parkingservicegui1.Parkingservicegui1").
  qactor( parkingservicegui2, ctxcarparking, "it.unibo.parkingservicegui2.Parkingservicegui2").
  qactor( parkingservicegui3, ctxcarparking, "it.unibo.parkingservicegui3.Parkingservicegui3").
  qactor( parkingservicegui4, ctxcarparking, "it.unibo.parkingservicegui4.Parkingservicegui4").
  qactor( parkingservicegui5, ctxcarparking, "it.unibo.parkingservicegui5.Parkingservicegui5").
  qactor( parkingservicegui6, ctxcarparking, "it.unibo.parkingservicegui6.Parkingservicegui6").
  qactor( parkingservicegui7, ctxcarparking, "it.unibo.parkingservicegui7.Parkingservicegui7").
  qactor( parkingservicegui8, ctxcarparking, "it.unibo.parkingservicegui8.Parkingservicegui8").
  qactor( outsonar, ctxcarparking, "it.unibo.outsonar.Outsonar").
  qactor( weightsensor, ctxcarparking, "it.unibo.weightsensor.Weightsensor").
  qactor( parkingmanagerservice, ctxcarparking, "it.unibo.parkingmanagerservice.Parkingmanagerservice").
