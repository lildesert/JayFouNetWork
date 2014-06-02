


Network Working Group                                          J. Bistoquet
Request for Comments: 232708                                      A. Codron
Category: Informational                                            J. Lucas
                                                               M1 MIAGE App
                                                                  June 2014


                         The JayFouNetWork Protocol

Status of this Memo

  This document specifies a generic referee protocol for board games.
  Distribution of this memo is unlimited.

Copyright Notice

  Copyright (C) The Internet Society (2014).  All Rights Reserved.

Table of Contents

  1. Purpose  . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 2
  2. MESSAGES format  . . . . . . . . . . . . . . . . . . . . . . . . . . 2
  3. MESSAGES definitions . . . . . . . . . . . . . . . . . . . . . . . . 2


Bistoquet & Codron & Lucas      Informational                      [Page 1]

 
RFC 232708                          JFNWP                         June 2014


1. Purpose
  
  The aim of the JFNWP protocol is to provide a referee for different types
  of board games like “rock, scissor, paper, lizard, spock”, “checkers” or
  “connect four”.
  
  The protocol manages operations and communications between clients
  (players) and server (referee) and use TCP for transport.
  Because of his genericity, JFNWP can be implemented as a referee for
  different games.
  
  In any situation, it always works in the same way. First, clients connect
  to the referee and choose the type of board game. Next, the referee sends
  messages to let the game begin if connection is ok.
  
  Now it depends of the type of game, on the one hand we’ve got
  “synchronised” board games (“rock, scissor, paper, lizard, spock”) and on
  the other hand we have turn by turn games (“chess”).
  
  The role of the referee is almost the same for both types, it has to
  handle the clients choices, then to apply the rules of the game and
  finally send back the results to clients. After it waits for the next
  turn if the game is not over.
  
  The difference in turn by turn game is that the referee doesn’t wait to
  have response from all clients.

2. MESSAGES format

  The JFNWP defines a model that will be used for all messages:

    2 bytes   n bytes
  ---------------------
   |  Id   |   Data  |
  ---------------------

3. MESSAGES definitions

  Connection part:
     connect 
       Id: 1
       Arguments:
         Name: nickname of the client
       Possible responses (Ok / Nok: server full / Wait: on waiting list)
      start
        Id: 2
        Arguments:
          Opponent: nickname of the opponent
          FirstPlayer: in case of turn by turn game, the name of the player
          who begin 
      end
        Id: 3


Bistoquet & Codron & Lucas      Informational                      [Page 2]

 
RFC 232708                          JFNWP                         June 2014


  Game part:

    Requests send by server:
      dmdMove
        Id: 4
        Arguments: 
          MoveID: ID to recognize the move 
          TimeOut: max time to receive a response
          OpponentMove: last move played by the opponent
          Warning: not present if the game is not turn by turn  
      result:
        Id: 5
        Arguments:
          code:
            1: the first player won the game
            2: the second player won the game
            3: equality
         nbMove: number of moves
         time: game time


  Requests send by client:
     sendMove
       Id: 6
       Arguments: 
          IDMove: ID to recognize the move
          Move: the value of the move (depend of the game)
        Possible Responses: OK (movement is apply), IMP (movement doesn’t
        respect the rules)
     surrender
       Id: 7
       Possible response: OK. (follow by the result command)

  Chat part
    getAddresses
      Id: 8
      Response: sendAddresses
    sendAddresses
      Id: 9
      Arguments:
        address list

  Generic messages:
    Ok
      Id: 10
    Nok
     Id: 11
    Wait
      Id: 12
    IMP
      Id: 13


Bistoquet & Codron & Lucas      Informational                      [Page 3]

