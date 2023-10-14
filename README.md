# Macchiato
Object-oriented programming project
The Macchiato project is an implementation of a simple programming language that supports object-oriented programming.
It provides classes for creating and executing programs in the Macchiato language.
The project also includes a debugger for stepping through programs, inspecting variables and procedures during runtime.

The Macchiato language supports various features such as arithmetic expressions (addition, division, multiplication, modulo, subtraction),
for loops, conditional statements, and basic instructions like printing expressions and modifying variable values.

The project was developed incrementally, with version 1.0 primarily focused on building the debugger functionality.
In version 2.0, procedures and unit tests were added to enhance the language's capabilities.
Additionally, builder patterns were introduced in the later version to simplify program creation in the Macchiato language.

Overall, the Macchiato project aims to provide a convenient and extensible environment for programming in the Macchiato language,
combining object-oriented principles with language-specific features.

------------------------------------------------------------

## Macchiato syntax

#### Program 
  A Macchiato program consists of a single program block that contains all the instructions.
  The program block is the top-level structure that encapsulates the entire program. Programs also have names.
#### Block:
  Variables can be defined only at the beggining of a block.
  Macchiato uses blocks to group related instructions together.
  Each block starts and ends with begin/end block respectively.

#### Variables:
Macchiato supports the declaration and manipulation of variables. They can store integers. 
Variable declaration can be done only at the beggining of a block.

#### Expressions:
Macchiato allows the creation and evaluation of expressions.
Expressions can involve arithmetic operations such as addition, subtraction, multiplication, division, and modulo.
They can also include variables and constant values.

#### Control Flow:
Macchiato provides control flow structures, such as conditional statements and loops, to control the execution of program statements.
Conditional statements (if) allow for branching based on a condition, while loops (for) allow for repeated execution of a block of code.

#### Procedures:
Macchiato supports the definition and invocation of procedures. Procedures are similar to functions and can have zero or more parameters.
They are declared using a header specifying the procedure name and its parameters, followed by the procedure body containing a sequence of statements.

#### Scope:
Macchiato follows block-level scoping rules:
  - variables declared within a block are visible only within that block and any nested blocks.
  - variables declared in an outer block are not visible within inner blocks unless explicitly passed as parameters.

#### Dynamic Binding:
Macchiato utilizes dynamic binding for variable resolution. When a variable is referenced, the binding is determined at runtime based on the current environment. This means that the variable's value is determined by the most recent declaration visible within the current execution context.

#### Input/Output: Macchiato supports basic input/output operations. The print statement is used to display values on the output stream.
Input can be obtained from the user using appropriate methods or functions provided by the programming environmen

## Sample programs 
Sample programs can be found in Main class.
  
