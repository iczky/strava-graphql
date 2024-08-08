# Running Application API

## Overview

This project is a running application API built using Spring Boot (Java 21). It aims to provide functionalities similar to Strava, focusing on the running use case. Users can create accounts, log activities, set goals, and track their progress over time.

## User Stories

### User Account Management
- As a user, I want to create an account so that I can use the application.
- As a user, I want to log in to my account to access my personal data.
- As a user, I want to update my profile information (name, age, weight, etc.).

### Running Activities
- As a user, I want to record a new run activity with details such as distance, duration, and route.
- As a user, I want to view my run history to track my progress.
- As a user, I want to edit or delete a recorded run in case I made a mistake.
- As a user, I want to view a summary of my running statistics (total distance, average pace, etc.).

### Goals
- As a user, I want to set personal goals for my running activities.

## Setup Instructions

### Prerequisites
- Java 21
- Spring Boot
- Maven
- PostgreSQL

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/iczky/strava-graphql.git
    cd strava-graphql
    ```

2. Configure the database in `src/main/resources/application.yaml`:
    ```properties
    url=jdbc:postgresql://localhost:5432/yourdbname
    username=yourusername
    password=yourpassword
    ddl-auto=update
    ```

3. Build and run the application:
    ```bash
    docker-compose up -d --build --force-recreate
    ```

## GraphQL Schema

```graphql
type User {
  id: ID!
  username: String!
  email: String!
  name: String
  age: Int
  weight: Float
  runActivities: [RunActivity!]!
  runningStats: RunningStats!
}

type RunActivity {
  id: ID!
  user: User!
  distance: Float!
  duration: Int!
  date: String!
  route: String
  averagePace: Float
  calories: Int
}

type RunningStats {
  totalDistance: Float!
  totalDuration: Int!
  averagePace: Float!
  totalCalories: Int!
  totalActivities: Int!
}

type Goal {
  id: ID!
  user: User!
  type: GoalType!
  target: Float!
  deadline: String
  completed: Boolean!
}

enum GoalType {
  DISTANCE
  DURATION
  PACE
}

type Query {
  user(id: ID!): User
  runActivity(id: ID!): RunActivity
  runActivities(userId: ID!): [RunActivity!]!
  runningStats(userId: ID!): RunningStats!
  goals(userId: ID!): [Goal!]!
}

type Mutation {
  createUser(input: CreateUserInput!): User!
  updateUser(id: ID!, input: UpdateUserInput!): User!
  deleteUser(id: ID!): Boolean!

  createRunActivity(input: CreateRunActivityInput!): RunActivity!
  updateRunActivity(id: ID!, input: UpdateRunActivityInput!): RunActivity!
  deleteRunActivity(id: ID!): Boolean!

  createGoal(input: CreateGoalInput!): Goal!
  updateGoal(id: ID!, input: UpdateGoalInput!): Goal!
  deleteGoal(id: ID!): Boolean!
}

input CreateUserInput {
  username: String!
  email: String!
  password: String!
  name: String
  age: Int
  weight: Float
}

input UpdateUserInput {
  name: String
  age: Int
  weight: Float
}

input CreateRunActivityInput {
  userId: ID!
  distance: Float!
  duration: Int!
  date: String!
  route: String
}

input UpdateRunActivityInput {
  distance: Float
  duration: Int
  date: String
  route: String
}

input CreateGoalInput {
  userId: ID!
  type: GoalType!
  target: Float!
  deadline: String
}

input UpdateGoalInput {
  type: GoalType
  target: Float
  deadline: String
  completed: Boolean
}
