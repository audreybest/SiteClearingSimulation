# Design and Approach

## Overview
The project is a **land-clearing simulator** implemented in Java, using Maven for project management. The core functionality revolves around simulating commands to clear a site map and calculating the associated costs. The main components include command parsing, site map management, cost calculation, and simulation execution.

## Key Components

### Command Parsing (`ParsedCommand` Class)
- **Purpose:** To parse and validate commands input by the user.
- **Design:** Uses an `enum Command` to represent different commands (`advance`, `turn left`, `turn right`, `quit`). Includes methods to parse input strings and handle invalid commands by throwing `InvalidCommandException`.

### Site Map Management (`SquareState` Enum)
- **Purpose:** To represent the state of each square in the site map.
- **Design:** Includes different states (e.g., `plain land`, `protected tree`, `cleared`) and methods to normalize characters to enum values and retrieve associated fuel costs.

### Direction Handling (`Direction` Enum)
- **Purpose:** To manage the direction of the bulldozer.
- **Design:** Includes directions (e.g., `North`, `East`, `South`, `West`) and methods to handle left and right turns.

### Cost Calculation (`CostCalculator` Class)
- **Purpose:** To calculate the costs associated with the land-clearing operation.
- **Design:** Maintains counters for different cost factors (e.g., communication overhead, fuel usage, protected tree destruction, paint damage). Includes methods to update these counters and calculate the total cost, including uncleared squares.

### Simulation Execution (`SiteCleanupSimulatorImpl` Class)
- **Purpose:** To execute the simulation based on parsed commands and manage the state of the site map.
- **Design:** Initializes with a site map and processes commands to update the site map and calculate costs using the `CostCalculator`. Includes methods to execute commands, print the site map, and print a summary of the simulation.

## Testing

- **Unit Tests:** Provided for core classes (`ParsedCommand`, `SquareState`, `Direction`, `CostCalculator`) to ensure correct functionality.
- **Integration Tests:** For the `SiteCleanupSimulatorImpl` class to verify the overall simulation logic and cost calculation.

## Maven Configuration

- **Compiler Plugin:** Configured to use Java 11 for source and target compatibility.
- **Jar Plugin:** Configured to include the main class in the manifest for executable JAR creation.

## Execution

- **java -jar <path-to-folder>/target/simulator-project-1.0-SNAPSHOT.jar <path-to-folder>/src/test/resources/sitemap1.txt**