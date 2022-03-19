1) Sprint Goal:
  - Get as much progress done as possible implementing the user stories from sprint 0.
  - Debugging any issues that occur during our implementations.
  - As we implement the features using six design patterns, identify any parts where we can make the code more efficient, by designing aspects using patterns, etc.
  - Near the end of the sprint, develop stories for sprint 2 including unfinished work from sprint 1
  
2) Stories for this sprint:
  - 0.1: Strategy pattern to add custom movement behaviours.
  - 0.2: Null Object Pattern to create null object for MovesCounter.
  - 0.3: Use the Visitor Pattern to implement the Mystery Box feature. 
  - 0.4: Singleton Pattern to track the number of moves left in challenge mode.
  - 0.5: Factory pattern to instantiate pieces at runtime.
  - 0.6: Command pattern to buy guards/sell credits during gameplay.
  - 1.5: New story. Will implemeant an addCredit feature for the mystery box.
  - 1.7 New story. Using Singleton Pattern to implement the accessibility to the credits.

3) A current assessment of team capacity: 
  - We expect to be complete around 60% of the work assuming we have no emergencies during each teammates plan to work.
  
4) Participants in the sprint process:
  - Aneeq (HassanA01)
    - Implemented the command pattern which will be used to buy guard pieces/sell credits during the game (Still under development).
    - Implemented the factory pattern which will be used in runtime to instantiate pieces with specific movement behaviours.
    - Introduced Story 1.7 which will be developed in between this sprint and the next sprint deadline (Credits).
    - Updated UML diagrams in designpatterns.pdf due to changes in the implementation of the patterns.
    - Reviewed Null Object Pattern and Singleton pattern code.
  - Mahmoud (mamdoohK)
    - Implemented the strategy pattern that will be used by the user to create custom movement behaviours.
    - Implemented the Null Object pattern that will be used by the program to have a "do nothing" version of MovesCounter.
    - Fixed UML diagram for Singleton Pattern after changes were done to the code.
    - Reviewed factory pattern and command pattern code.
  - Faisal (faisalt14)
    - Implemented classes part of the Visitor Pattern (MysteryBox, Visitor, Visitable, AddMoves, AddGuard, AddMusketeer, SkipTurn). 
    - Updated the Visitor Pattern UML diagram to included newly added data members for classes part of the Visitor pattern. 
    - Updated the board file to include another implementation of the "Move()" method that does not change turns at the end. This is used for the SkipTurn feature. 
    - Reviewed the Strategy Pattern for Custom Movement Behaviours (DEV01). 
    - Reviewed the Singleton Pattern for the Moves Counter feature. (DEV04). 
  - Harshil (Harshil-Patel28)
    - Implemented Singleton Pattern (MovesCounter), which will be used to keep track of the number of moves left in the game.
    - Updated the ThreeMusketeers file to ask the user if they would like to play with a limited number of moves or not.
    - Updated the Board file, so the number of moves is updated after each user turn is made.
    - Made changes to my code based on code reviews since this class will be used be others too.
    - Reviewed the Vistor Pattern for the Mystery Box feature. (DEV03)
5)  Breakdown of tasks completed:
  - Strategy pattern to add custom movement behaviours. (0.1) 
  - Null Object Pattern to create null object for MovesCounter. (0.2) 
  - Singleton Pattern to track the number of moves left in challenge mode. (0.4)
