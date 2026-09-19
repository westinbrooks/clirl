<div align=center>

# Project Outline

The objective of this project is to develop a Roguelike<sup>[1](#Footnotes)</sup> game using Java-based Object-Oriented Programming (OOP)<sup>[2](#footnotes)</sup>.

</div>

### Required Features
The following must be implemented and triggered in your program.
- <b>Endless loop</b>: Game only ends upon either user request and/or death
- <b>Randomized enemy encounters</b>: 2+ enemies that have an equal (or otherwise defined) chance to be encountered
- <b>Inventory</b>: Contains a finite amount of usable items, such as food or potions, that can be depleted (replenishment is not required)
- <b>Factions</b>: User-chosen, includes unique weapons, stats, etc. (avoid calling them 'Classes', as this can easily be confused with the Java definition of `Classes`.)

### Optional Features
The following are optional, but may contribute to your implementation of the *required* features.
- <b>Tutorial</b>: Explaining how to play your game, resulting in an overall smoother learning curve
- <b>Title / Lore</b>: Adds some personality to your game, which may facilitate your inclination to expand upon it
- <b>Passive effects</b>: Effects that trigger loosely or entirely independent of user action, such as hunger or regeneration
- <b>Multi-turn actions</b>: Functions that are activated by the user, but don't trigger until the following turn or later, forcing you to design the program to remember user choices and store them under an independent timer to automatically trigger later
- <b>Custom functions</b>: Hand-crafted functions, such as variable print speed, which force you to conceptually understand core Java components like `System.out.*` by customizing their usage

### Java Concepts
By the end of developing your game, you should have learned / used many of the following Java concepts or functions. You may also use this list as a loose guideline of the methods of implementing the previously described required and optional features. Feel free to look up examples of these concepts, while avoiding directly copying third-party code into your program. ***Don't use AI!***
- <b>Classes and Objects</b>: Classes define the structure and behavior of objects, which  are individual instances of the class
- <b>Constructors</b>: Initialize an object's parameters when one is created
- <b>Modifiers</b>: `public`, `private`, `static`, `final`, etc., which affect the functionality of variables and other fields
- <b>Encapsulation</b>: Hides internal data and allows accessing it through methods called `getters`
- <b>Composition</b>: Classes containing objects of other classes for mutual functionality
- <b>Inheritance</b>: Classes acquiring features from another class, which can be either direct (using `extends`) or indirect
- <b>Mutable Object States</b>: Fields that can be changed while the program runs
- <b>Methods</b>: Blocks of reusable code with varying parameters and return values
- <b>Method Overloading</b>: When a class has multiple methods with the same name but different parameters
- <b>Method Overriding</b>: When a subclass provides its own method inherited from its parent class
- <b>Collections</b>: Data structures, such as `ArrayList` or `HashMap`, which store lists of objects dynamically
- <b>Getters</b>: Methods that return private variables without directly exposing those fields
- <b>Factory Methods</b>: Methods that create and return preconfigured objects
- <b>Type Casting</b>: Converts a value from one data type to another, such as converting from a `double` to an int
- <b>Conditional Statements</b>: `if`, `else if`, `else`, `switch` statements; `while`, `for`, enhanced `for` loops, which allow for systematic code execution depending on live conditions
- <b>StringBuilder</b>: Constructs strings by using `.append()` to repeatedly add substrings (powerful when used with conditional statements to make adaptable strings without an excessive amount of `if` / `else` statements)
- <b>String Processing</b>: Using methods such as `.equals()`, `.equalsIgnoreCase`, `.isEmpty()`, `.trim()`, `.toString()`, etc. to manipulate and compare strings
- <b>Utilities</b>: Packages, such as `Scanner`, `Random`, or `Math`, which may be called into the program with `import` statements
- <b>Exceptions</b>: Checked exceptions, such as `InterruptedException` or `IOException`, which are declared using `throws`
- <b>Thread Sleeping</b>: Using `Thread.sleep()` to pause execution for a specified time in milliseconds

### Footnotes
1. Turn-based RPG with permadeath and procedural encounters, such as [Slay the Spire 2](https://store.steampowered.com/app/2868840/Slay_the_Spire_2/) or [Balatro](https://store.steampowered.com/app/2379780/Balatro/).
2. Programming based on objects, encapsulating data and functions to interact with one another.