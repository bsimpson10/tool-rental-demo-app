# Tool Rental Demo App

## Overview

This is a demo application that simulates a two-tier backend (there are no controllers).

There is a service layer, and behind that there is a data layer that uses JPA entities/repositories.

The service layer orchestrates the creation of a rental agreement for a large tool, and uses a 
business rules engine for the actual business logic. This has resulted in a clean service implementation.

## Decisions of Note
- I opted to use Easy Rules to handle business logic instead of coding that directly
  in the service layer. The idea is that if future holidays were to be added, it could
  be done with minimal difficulty. 
- There is no persistence for the rental agreement, but this would not be difficult to
  do if required. 
- H2 in-memory database is used for running tests, with the DB bootstrapped using data.sql